package com.faridrama123.proyekmovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.faridrama123.app.movieapp.data.CatalogueRepository
import com.faridrama123.app.movieapp.data.source.local.entity.MovieEntity
import com.faridrama123.app.movieapp.data.source.local.entity.TVShowEntity
import com.faridrama123.app.movieapp.vo.Resource


class DetailInfoViewModel (private val catalogueRepository: CatalogueRepository) : ViewModel() {
    val id = MutableLiveData<String>()
    fun setSelected(id: String) {
        this.id.value = id
    }

    var getMovieById: LiveData<Resource<MovieEntity>> = Transformations.switchMap(id) { id ->
        catalogueRepository.getMovieById(id)
    }

    fun setFavoriteMovie(item: MovieEntity, newStatus : Boolean){
        return catalogueRepository.setFavoriteMovie(item,newStatus)
    }


    var getTVShowById: LiveData<Resource<TVShowEntity>> = Transformations.switchMap(id) { id ->
        catalogueRepository.getTVShowById(id)
    }

    fun setFavoriteTVShow(item: TVShowEntity, newStatus : Boolean){
        return catalogueRepository.setFavoriteTVShow(item,newStatus)
    }

}

