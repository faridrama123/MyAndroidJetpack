package com.faridrama123.proyekmovie.data

import ResultsItem
import androidx.lifecycle.LiveData
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity

interface ProyekDataSource {

    fun getMovie(): LiveData<List<ResultsMovieEntity>>
    fun getMovieById(id : String): LiveData<ResultsMovieEntity>


}