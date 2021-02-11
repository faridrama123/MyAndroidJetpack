package com.faridrama123.proyekmovie.api

import MoviePopularResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun moviePopular(
            @Query ("api_key") api_key : String,
            @Query ("language") language : String,
            @Query ("page") page : String

    ): Call<MoviePopularResponse>
}