package com.faridrama123.app.movieapp.ui.moviefavorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.faridrama123.app.movieapp.data.CatalogueRepository
import com.faridrama123.app.movieapp.data.source.local.entity.MovieEntity


class MovieFavoritesViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getMovie(): LiveData<PagedList<MovieEntity>> = catalogueRepository.getFavoriteMovie()

    fun setFavorites(movie: MovieEntity) {
        val newState = !movie.isFavorite
        catalogueRepository.setFavoriteMovie(movie, newState)
    }
}

