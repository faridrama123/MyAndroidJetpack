package com.faridrama123.app.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.faridrama123.app.data.source.local.LocalDataSource
import com.faridrama123.app.data.source.local.entity.MovieEntity
import com.faridrama123.app.data.source.local.entity.TVShowEntity
import com.faridrama123.app.data.source.remote.ApiResponse
import com.faridrama123.app.data.source.remote.RemoteDataSource
import com.faridrama123.app.data.source.remote.response.ResultsMovieResponse
import com.faridrama123.app.data.source.remote.response.ResultsTVShowResponse
import com.faridrama123.app.utils.AppExecutors
import com.faridrama123.app.vo.Resource


class CatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
)
    : CatalogueDataSource {

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): CatalogueRepository =
                instance ?: synchronized(this) {
                    CatalogueRepository(remoteData, localData, appExecutors).apply { instance = this }
                }
    }

    override fun getMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<ResultsMovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(20)
                        .setPageSize(20)
                        .build()
                return LivePagedListBuilder(localDataSource.getMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ResultsMovieResponse>>> =
                    remoteDataSource.getMovie()

            public override fun saveCallResult(data: List<ResultsMovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                            response.overview, response.originalLanguage,
                            response.originalTitle, response.video,
                            response.title, response.genreIds,
                            response.posterPath, response.backdropPath,
                            response.releaseDate, response.popularity,
                            response.voteAverage, response.id,
                            response.adult, response.voteCount, false)

                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, state)
        }

    }

    override fun getMovieById(id: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, List<ResultsMovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<MovieEntity> =
                 localDataSource.getMovieById(id)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                 data == null

            public override fun createCall(): LiveData<ApiResponse<List<ResultsMovieResponse>>> =
                    remoteDataSource.getMovieById(id)

            public override fun saveCallResult(data: List<ResultsMovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                            response.overview, response.originalLanguage,
                            response.originalTitle, response.video,
                            response.title, response.genreIds,
                            response.posterPath, response.backdropPath,
                            response.releaseDate, response.popularity,
                            response.voteAverage, response.id,
                            response.adult, response.voteCount, false)

                    movieList.add(movie)
                }

            }


        }.asLiveData()
    }


    override fun getTVShow(): LiveData<Resource<PagedList<TVShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TVShowEntity>, List<ResultsTVShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TVShowEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(20)
                        .setPageSize(20)
                        .build()
                return LivePagedListBuilder(localDataSource.getTVShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TVShowEntity>?): Boolean =
                    data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ResultsTVShowResponse>>> =
                    remoteDataSource.getTVShow()

            public override fun saveCallResult(data: List<ResultsTVShowResponse>) {
                val tvshowList = ArrayList<TVShowEntity>()
                for (response in data) {
                    val tvshow = TVShowEntity(
                            response.firstAirDate, response.overview,
                            response.originalLanguage, response.genreIds,
                            response.posterPath, response.originCountry,
                            response.backdropPath, response.originalName,
                            response.popularity, response.voteAverage,
                            response.name, response.id,
                            response.voteCount,  false)

                    tvshowList.add(tvshow)
                }
                localDataSource.insertTVShow(tvshowList)
            }
        }.asLiveData()
    }

    override fun getFavoriteTVShow(): LiveData<PagedList<TVShowEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTVShow(), config).build()
    }

    override fun setFavoriteTVShow(tvshow: TVShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTVshow(tvshow, state)
        }

    }

    override fun getTVShowById(id: String): LiveData<Resource<TVShowEntity>> {
        return object : NetworkBoundResource<TVShowEntity, List<ResultsTVShowResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<TVShowEntity> =
                    localDataSource.getTVShowById(id)

            override fun shouldFetch(data: TVShowEntity?): Boolean =
                    data == null

            public override fun createCall(): LiveData<ApiResponse<List<ResultsTVShowResponse>>> =
                    remoteDataSource.getTVShowById(id)

            public override fun saveCallResult(data: List<ResultsTVShowResponse>) {
                val tvShowList = ArrayList<TVShowEntity>()
                for (response in data) {
                    val tvshow = TVShowEntity(
                            response.firstAirDate, response.overview,
                            response.originalLanguage, response.genreIds,
                            response.posterPath, response.originCountry,
                            response.backdropPath, response.originalName,
                            response.popularity, response.voteAverage,
                            response.name, response.id,
                            response.voteCount,  false)


                    tvShowList.add(tvshow)
                }

                localDataSource.insertTVShow(tvShowList)
            }


        }.asLiveData()
    }
}