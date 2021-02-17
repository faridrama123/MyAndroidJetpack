package com.faridrama123.proyekmovie.data

import ResultsMovieResponse
import ResultsTVShowResponse
import android.os.Handler
import android.os.Looper
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity
import com.faridrama123.proyekmovie.utils.JsonHelper

class RemoteDataSource  private constructor(private  val jsonHelper: JsonHelper ){

       private val handler = Handler(Looper.getMainLooper())

    companion object{
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private  var instance : RemoteDataSource? = null
        fun getInstance(helper: JsonHelper ): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(helper)
            }

    }

    fun getMovie(callback: LoadMovieCallback){
        handler.postDelayed({ callback.onMovieReceived(jsonHelper.loadMovie()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMovieCallback {
        fun onMovieReceived(movieResponses: List<ResultsMovieResponse>)
    }

    fun getTVShow(callback: LoadTVShowCallback){
        handler.postDelayed({ callback.onTVShowReceived(jsonHelper.loadTVShow()) }, SERVICE_LATENCY_IN_MILLIS)
    }
    interface LoadTVShowCallback {
        fun onTVShowReceived(tvShowResponse : List<ResultsTVShowResponse>)
    }
}