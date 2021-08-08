package com.faridrama123.app.movieapp.ui.moviefavorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faridrama123.app.movieapp.R
import com.faridrama123.app.movieapp.databinding.FragmentFavMovieBinding
import com.faridrama123.app.movieapp.ui.movie.MovieAdapter
import com.faridrama123.app.movieapp.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 */
class MovieFavoritesFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentFavMovieBinding? = null
    private val binding get() = _fragmentMovieBinding
    private lateinit var adapter : MovieAdapter
    private lateinit var viewModel : MovieFavoritesViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _fragmentMovieBinding = FragmentFavMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieFavoritesViewModel::class.java]
            adapter = MovieAdapter()

            viewModel.getMovie().observe(this){
                binding?.progressBar?.visibility = View.GONE

                adapter.submitList(it)
            }
            binding?.rvFavmovie?.layoutManager = LinearLayoutManager(context)
            binding?.rvFavmovie?.setHasFixedSize(true)
            binding?.rvFavmovie?.adapter = adapter


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieBinding = null
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val entity = adapter.getSwipedData(swipedPosition)
                entity?.let { viewModel.setFavorites(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    entity?.let { viewModel.setFavorites(it) }
                }
                snackbar.show()
            }
        }
    })
}