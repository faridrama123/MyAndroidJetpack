package com.faridrama123.proyekmovie

import MoviePopularResponse
import ResultsItem
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faridrama123.proyekmovie.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private  val _resultitem = MutableLiveData<List <ResultsItem>>()
    val resultitem : LiveData<List<ResultsItem>> = _resultitem


    companion object{
        private const val TAG = "MovieViewModel"
        private const val APIKEY = "2bf82f509f8de04b0defa6b183061330"
        private const val LANGUANGE = "en-US"
        private const val PAGE = "1"
    }

    fun getMovie (){
        val client = ApiConfig.getApiService().moviePopular(APIKEY,LANGUANGE,PAGE)
        client.enqueue(object : Callback<MoviePopularResponse>{
            override fun onResponse(call: Call<MoviePopularResponse>, response: Response<MoviePopularResponse>) {

                if (response.isSuccessful){
                    _resultitem.value = response.body()?.results

                }else
                {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MoviePopularResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })




    }

}