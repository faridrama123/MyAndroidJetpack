package com.faridrama123.proyekmovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridrama123.proyekmovie.data.ProyekRepository
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity
import com.faridrama123.proyekmovie.data.local.entity.ResultsTVShowEntity


class DetailInfoViewModel (private val proyekRepository: ProyekRepository) : ViewModel() {
    private lateinit var id : String
    fun setSelected(id: String) {
        this.id = id
    }

    fun getMovie () :  LiveData<ResultsMovieEntity> = proyekRepository.getMovieById(id)
    fun getTVShow(): LiveData<ResultsTVShowEntity> = proyekRepository.getTVShowById(id)


}

