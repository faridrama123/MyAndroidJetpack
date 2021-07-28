package com.faridrama123.app.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.faridrama123.app.data.CatalogueRepository
import com.faridrama123.app.data.source.local.entity.MovieEntity
import com.faridrama123.app.data.source.local.entity.TVShowEntity
import com.faridrama123.app.vo.Resource


class TVShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getTVShow () : LiveData<Resource<PagedList<TVShowEntity>>> = catalogueRepository.getTVShow()


}

