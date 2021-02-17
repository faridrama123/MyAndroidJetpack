package com.faridrama123.proyekmovie.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.faridrama123.proyekmovie.data.ProyekRepository
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity

class MovieViewModel(private val proyekRepository: ProyekRepository) : ViewModel() {

    fun getMovie(): LiveData<List<ResultsMovieEntity>> = proyekRepository.getMovie()

}

