package com.faridrama123.proyekmovie.data


import androidx.lifecycle.LiveData
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity
import com.faridrama123.proyekmovie.data.local.entity.ResultsTVShowEntity

interface ProyekDataSource {

    fun getMovie(): LiveData<List<ResultsMovieEntity>>
    fun getMovieById(id : String): LiveData<ResultsMovieEntity>

    fun getTVShow(): LiveData<List<ResultsTVShowEntity>>
    fun getTVShowById(id : String): LiveData<ResultsTVShowEntity>



}