package com.faridrama123.proyekmovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.faridrama123.proyekmovie.data.ProyekRepository
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity


class DetailInfoViewModel (private val proyekRepository: ProyekRepository) : ViewModel() {
    lateinit var Id: String
    fun setSelectedCourse(Id: String) {
        this.Id = Id
    }

    fun getMovie(): LiveData<ResultsMovieEntity> = proyekRepository.getMovieById(Id)

//    fun gettv(): TvSHOWEntity
//        {
//            lateinit var tv: TvSHOWEntity
//            val tvEntities = DataDummy.generateDummyTv()
//            for (tvEntity in tvEntities) {
//                if (tvEntity.id == Id) {
//                    tv = tvEntity
//                }
//            }
//            return tv
//        }
}