package com.faridrama123.moviecatalogue.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.moviecatalogue.data.MOVIEEntity
import com.faridrama123.moviecatalogue.databinding.FragmentAcademyBinding
import com.faridrama123.moviecatalogue.ui.movies.MovieViewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {
    private var fragmentAcademyBinding: FragmentAcademyBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentAcademyBinding = FragmentAcademyBinding.inflate(inflater, container, false)
        return fragmentAcademyBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, NewInstanceFactory()).get(MovieViewModel::class.java)
            val movies: List<MOVIEEntity> = viewModel.getmovies()
            val academyAdapter = MovieAdapter()
            academyAdapter.setCourses(movies)
            fragmentAcademyBinding!!.rvAcademy.layoutManager = LinearLayoutManager(context)
            fragmentAcademyBinding!!.rvAcademy.setHasFixedSize(true)
            fragmentAcademyBinding!!.rvAcademy.adapter = academyAdapter
        }
    }
}