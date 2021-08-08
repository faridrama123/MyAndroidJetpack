package com.faridrama123.app.movieapp.ui.tvshowfavorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.faridrama123.app.movieapp.data.CatalogueRepository
import com.faridrama123.app.movieapp.data.source.local.entity.TVShowEntity


class TVShowFavoritesViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {


    fun getTVShow(): LiveData<PagedList<TVShowEntity>> = catalogueRepository.getFavoriteTVShow()

    fun setFavorites(tvshow: TVShowEntity) {
        val newState = !tvshow.isFavorite
        catalogueRepository.setFavoriteTVShow(tvshow, newState)
    }


}

