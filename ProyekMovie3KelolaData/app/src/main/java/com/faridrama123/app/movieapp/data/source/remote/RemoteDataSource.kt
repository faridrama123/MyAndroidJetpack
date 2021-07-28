package com.faridrama123.app.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faridrama123.app.data.source.remote.response.ResultsMovieResponse
import com.faridrama123.app.data.source.remote.response.ResultsTVShowResponse
import com.faridrama123.app.movieapp.utils.EspressoIdlingResource
import com.faridrama123.app.movieapp.utils.JsonHelper


class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    RemoteDataSource(helper).apply { instance = this }
                }
    }


    fun getMovie () : LiveData<ApiResponse<List<ResultsMovieResponse>>> {

        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<ResultsMovieResponse>>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.loadMovie())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultMovie
    }


    fun getMovieById (id: String) : LiveData<ApiResponse<List<ResultsMovieResponse>>> {

        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<ResultsMovieResponse>>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.loadMovieById(id))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultMovie
    }


    fun getTVShow () : LiveData<ApiResponse<List<ResultsTVShowResponse>>> {

        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<ResultsTVShowResponse>>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadTVShow())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)

        return result
    }


    fun getTVShowById (id: String) : LiveData<ApiResponse<List<ResultsTVShowResponse>>> {

        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<ResultsTVShowResponse>>>()
        handler.postDelayed({
            result.value = ApiResponse.success(jsonHelper.loadTVShowById(id))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)

        return result
    }




}

