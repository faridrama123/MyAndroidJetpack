package com.faridrama123.app.ui.tvshowfavorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.faridrama123.app.data.CatalogueRepository
import com.faridrama123.app.data.source.local.entity.MovieEntity
import com.faridrama123.app.data.source.local.entity.TVShowEntity
import com.faridrama123.app.vo.Resource


class TVShowFavoritesViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {


    fun getTVShow(): LiveData<PagedList<TVShowEntity>> = catalogueRepository.getFavoriteTVShow()

    fun setFavorites(tvshow: TVShowEntity) {
        val newState = !tvshow.isFavorite
        catalogueRepository.setFavoriteTVShow(tvshow, newState)
    }


}

