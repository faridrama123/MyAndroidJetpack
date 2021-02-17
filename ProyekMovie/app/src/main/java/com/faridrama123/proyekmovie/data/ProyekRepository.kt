package com.faridrama123.proyekmovie.data

import ResultsMovieResponse
import ResultsTVShowResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity
import com.faridrama123.proyekmovie.data.local.entity.ResultsTVShowEntity
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class ProyekRepository private constructor(private  val remoteDataSource: RemoteDataSource) : ProyekDataSource{

    companion object {
        @Volatile
        private var instance: ProyekRepository? = null

        fun getInstance(remoteData: RemoteDataSource): ProyekRepository =
            instance ?: synchronized(this) {
                instance ?: ProyekRepository(remoteData)
            }
    }
    override fun getMovie(): LiveData<List<ResultsMovieEntity>> {
        val movieResult  = MutableLiveData<List<ResultsMovieEntity>>()
        remoteDataSource.getMovie(object : RemoteDataSource.LoadMovieCallback{
            override fun onMovieReceived(movieResponses: List<ResultsMovieResponse>) {
                val movieList  = ArrayList<ResultsMovieEntity>()
                for(response in movieResponses){
                    val movie = ResultsMovieEntity(
                        response.overview, response.originalLanguage,
                        response.originalTitle, response.video,
                        response.title, response.genreIds,
                        response.posterPath, response.backdropPath,
                        response.releaseDate, response.popularity,
                        response.voteAverage, response.id,
                        response.adult, response.voteCount)
                    movieList.add(movie)
                }
                movieResult.postValue(movieList)
            }
        })
        return movieResult
     }

    override fun getMovieById(id : String): LiveData<ResultsMovieEntity> {
        val movieResult  = MutableLiveData<ResultsMovieEntity>()
        remoteDataSource.getMovie(object : RemoteDataSource.LoadMovieCallback{
            override fun onMovieReceived(movieResponses: List<ResultsMovieResponse>) {
                lateinit var movie : ResultsMovieEntity
                for(response in movieResponses){
                    if (response.id.toString() == id) {
                         movie = ResultsMovieEntity(
                                response.overview, response.originalLanguage,
                                response.originalTitle, response.video,
                                response.title, response.genreIds,
                                response.posterPath, response.backdropPath,
                                response.releaseDate, response.popularity,
                                response.voteAverage, response.id,
                                response.adult, response.voteCount)
                    }
                }
                movieResult.postValue(movie)
            }
        })
        return movieResult
    }

    override fun getTVShow(): LiveData<List<ResultsTVShowEntity>> {
        val result = MutableLiveData<List<ResultsTVShowEntity>>()
        remoteDataSource.getTVShow(object : RemoteDataSource.LoadTVShowCallback
        {
            override fun onTVShowReceived(tvShowResponse: List<ResultsTVShowResponse>) {
                val list = ArrayList<ResultsTVShowEntity>()
                for (response in tvShowResponse){
                    val tvShow = ResultsTVShowEntity (
                            response.firstAirDate, response.overview,
                            response.originalLanguage,  response.genreIds,
                            response.posterPath, response.originCountry,
                            response.backdropPath, response.originalName,
                            response.popularity, response.voteAverage,
                            response.name,  response.id,
                            response.voteCount)
                    list.add(tvShow)
                }
                result.postValue(list)
            }
        })
        return  result
    }

    override fun getTVShowById(id: String): LiveData<ResultsTVShowEntity> {
        val result = MutableLiveData<ResultsTVShowEntity>()
        remoteDataSource.getTVShow(object : RemoteDataSource.LoadTVShowCallback
        {
            override fun onTVShowReceived(tvShowResponse: List<ResultsTVShowResponse>) {
                 lateinit var tvshow : ResultsTVShowEntity
                for (response in tvShowResponse){
                    if (response.id.equals(id)) {
                        tvshow = ResultsTVShowEntity(
                                response.firstAirDate, response.overview,
                                response.originalLanguage, response.genreIds,
                                response.posterPath, response.originCountry,
                                response.backdropPath, response.originalName,
                                response.popularity, response.voteAverage,
                                response.name, response.id,
                                response.voteCount)
                    }
                }
                result.postValue(tvshow)
            }
        })
        return  result
    }


}



