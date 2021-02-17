package com.faridrama123.proyekmovie.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.proyekmovie.ViewModelFactory
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity
import com.faridrama123.proyekmovie.databinding.FragmentMovieBinding

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()

            fragmentMovieBinding.progressBar.visibility = View.VISIBLE

            viewModel.getMovie().observe(this,{
                fragmentMovieBinding.progressBar.visibility = View.GONE
                movieAdapter.setCourses(it)
                movieAdapter.notifyDataSetChanged()

            })
            with(fragmentMovieBinding.rvAcademy) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

        }
    }
}