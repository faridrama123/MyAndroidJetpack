package com.faridrama123.moviecatalogue.ui.movies

import androidx.lifecycle.ViewModel
import com.faridrama123.moviecatalogue.data.MOVIEEntity
import com.faridrama123.moviecatalogue.utils.DataDummy
import java.util.*

class MovieViewModel : ViewModel() {
    fun getmovies(): ArrayList<MOVIEEntity>
        = DataDummy.generateDummyMovie()
}