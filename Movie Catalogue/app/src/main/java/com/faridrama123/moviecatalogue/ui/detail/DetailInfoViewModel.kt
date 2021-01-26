package com.faridrama123.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.faridrama123.moviecatalogue.data.MOVIEEntity
import com.faridrama123.moviecatalogue.data.TvSHOWEntity
import com.faridrama123.moviecatalogue.utils.DataDummy

class DetailInfoViewModel : ViewModel() {
    private var Id: String? = null
    fun setSelectedCourse(Id: String?) {
        this.Id = Id
    }

    fun getmovie(): MOVIEEntity
         {
            lateinit var movie: MOVIEEntity
            val movieEntities = DataDummy.generateDummyMovie()
            for (movieEntity in movieEntities) {
                if (movieEntity.id == Id) {
                    movie = movieEntity
                }
            }
            return movie
        }
    fun gettv(): TvSHOWEntity
        {
            lateinit var tv: TvSHOWEntity
            val tvEntities = DataDummy.generateDummyTv()
            for (tvEntity in tvEntities) {
                if (tvEntity.id == Id) {
                    tv = tvEntity
                }
            }
            return tv
        }
}