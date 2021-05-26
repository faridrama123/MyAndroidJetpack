package com.faridrama123.app.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.faridrama123.app.data.source.local.entity.MovieEntity
import com.faridrama123.app.data.source.local.entity.TVShowEntity
import com.faridrama123.app.data.source.local.room.CatalogueDao


class LocalDataSource private constructor(private val mCatalogueDao: CatalogueDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mCatalogueDao: CatalogueDao): LocalDataSource =
                INSTANCE ?: LocalDataSource(mCatalogueDao).apply {
                    INSTANCE = this
                }

    }


    fun getMovie(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getMovie()

    fun insertMovie(movie: List<MovieEntity>) = mCatalogueDao.insertMovie(movie)

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getFavoriteMovie()

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mCatalogueDao.setFavoriteMovie(movie)
    }

    fun getMovieById(id: String): LiveData<MovieEntity> = mCatalogueDao.getMovieById(id)




    fun getTVShow(): DataSource.Factory<Int, TVShowEntity> = mCatalogueDao.getTVShow()

    fun insertTVShow(tvshow: List<TVShowEntity>) = mCatalogueDao.insertTVShow(tvshow)

    fun getFavoriteTVShow(): DataSource.Factory<Int, TVShowEntity> = mCatalogueDao.getFavoriteTVShow()

    fun setFavoriteTVshow(tvshow: TVShowEntity, newState: Boolean) {
        tvshow.isFavorite = newState
        mCatalogueDao.setFavoriteTVShow(tvshow)
    }

    fun getTVShowById(id: String): LiveData<TVShowEntity> = mCatalogueDao.getTVShowById(id)




}