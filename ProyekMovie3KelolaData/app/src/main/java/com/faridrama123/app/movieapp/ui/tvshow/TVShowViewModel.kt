package com.faridrama123.app.movieapp.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.faridrama123.app.movieapp.data.CatalogueRepository
import com.faridrama123.app.movieapp.data.source.local.entity.TVShowEntity
import com.faridrama123.app.movieapp.vo.Resource


class TVShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getTVShow () : LiveData<Resource<PagedList<TVShowEntity>>> = catalogueRepository.getTVShow()


}

