package com.faridrama123.app.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.app.databinding.FragmentMovieBinding
import com.faridrama123.app.utils.JsonHelper
import com.faridrama123.app.viewmodel.ViewModelFactory
import com.faridrama123.app.vo.Status


/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()

            viewModel.getMovie().observe(this, { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding?.progressBar?.visibility = View.GONE
                            movieAdapter.submitList(movie.data)
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding?.rvMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieBinding = null
    }
}