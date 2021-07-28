package com.faridrama123.app.ui.moviefavorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.faridrama123.app.data.CatalogueRepository
import com.faridrama123.app.data.source.local.entity.MovieEntity
import com.faridrama123.app.vo.Resource


class MovieFavoritesViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getMovie(): LiveData<PagedList<MovieEntity>> = catalogueRepository.getFavoriteMovie()

    fun setFavorites(movie: MovieEntity) {
        val newState = !movie.isFavorite
        catalogueRepository.setFavoriteMovie(movie, newState)
    }
}

