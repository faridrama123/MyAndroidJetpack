package com.faridrama123.proyekmovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.faridrama123.proyekmovie.data.ProyekRepository
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity
import com.faridrama123.proyekmovie.data.local.entity.ResultsTVShowEntity


class DetailInfoViewModel (private val proyekRepository: ProyekRepository) : ViewModel() {
    lateinit var Id: String
    fun setSelectedCourse(Id: String) {
        this.Id = Id
    }

    fun getMovie(): LiveData<ResultsMovieEntity> = proyekRepository.getMovieById(Id)
    fun getTVShow(): LiveData<ResultsTVShowEntity> = proyekRepository.getTVShowById(Id)


}

