package com.faridrama123.moviecatalogue.ui.TVShow

import androidx.lifecycle.ViewModel
import com.faridrama123.moviecatalogue.data.TvSHOWEntity
import com.faridrama123.moviecatalogue.utils.DataDummy

class TVShowViewModel : ViewModel() {
    fun gettv(): List<TvSHOWEntity>
      = DataDummy.generateDummyTv()
}