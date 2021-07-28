package com.faridrama123.app.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.faridrama123.app.data.source.local.entity.MovieEntity
import com.faridrama123.app.data.source.local.entity.TVShowEntity
import com.faridrama123.app.movieapp.vo.Resource


interface CatalogueDataSource {

    fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getFavoriteMovie():LiveData<PagedList<MovieEntity>>
    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun getMovieById(id: String): LiveData<Resource<MovieEntity>>



    fun getTVShow(): LiveData<Resource<PagedList<TVShowEntity>>>
    fun getFavoriteTVShow():LiveData<PagedList<TVShowEntity>>
    fun setFavoriteTVShow(movie: TVShowEntity, state: Boolean)
    fun getTVShowById(id: String): LiveData<Resource<TVShowEntity>>




}