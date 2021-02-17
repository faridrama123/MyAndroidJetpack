package com.faridrama123.proyekmovie.ui.TVShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.faridrama123.proyekmovie.data.ProyekRepository
import com.faridrama123.proyekmovie.data.local.entity.ResultsTVShowEntity

class TVShowViewModel (private val proyekRepository: ProyekRepository): ViewModel() {
    fun getTVShow(): LiveData<List<ResultsTVShowEntity>> = proyekRepository.getTVShow()
}