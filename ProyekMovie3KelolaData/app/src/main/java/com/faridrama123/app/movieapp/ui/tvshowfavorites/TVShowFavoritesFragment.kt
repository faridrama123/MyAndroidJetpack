package com.faridrama123.app.movieapp.ui.tvshowfavorites

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
import com.faridrama123.app.movieapp.databinding.FragmentFavTvshowBinding

import com.faridrama123.app.movieapp.ui.tvshow.TVShowAdapter
import com.faridrama123.app.movieapp.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 */
class TVShowFavoritesFragment : Fragment() {

    private var _fragmentTVShowBinding: FragmentFavTvshowBinding? = null
    private val binding get() = _fragmentTVShowBinding
    private lateinit var viewModel : TVShowFavoritesViewModel
    private lateinit var adapter : TVShowAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _fragmentTVShowBinding = FragmentFavTvshowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TVShowFavoritesViewModel::class.java]
            adapter = TVShowAdapter()

            viewModel.getTVShow().observe(this){
                binding?.progressBar?.visibility = View.GONE

                adapter.submitList(it)
            }

            binding?.rvFavtvshow?.layoutManager = LinearLayoutManager(context)
            binding?.rvFavtvshow?.setHasFixedSize(true)
            binding?.rvFavtvshow?.adapter = adapter


        }
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

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTVShowBinding = null
    }
}