package com.faridrama123.app.movieapp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.faridrama123.app.movieapp.data.CatalogueRepository
import com.faridrama123.app.movieapp.data.source.local.entity.MovieEntity
import com.faridrama123.app.movieapp.vo.Resource


class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> =
        catalogueRepository.getMovie()
}

