package com.faridrama123.app.movieapp.utils

import android.content.Context
import com.faridrama123.app.movieapp.data.source.remote.response.ResultsMovieResponse
import com.faridrama123.app.movieapp.data.source.remote.response.ResultsTVShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

class JsonHelper(private val context: Context) {



    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovie(): List<ResultsMovieResponse> {
        val list = ArrayList<ResultsMovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val overview =  movie.getString("overview")
                val originalLanguage=  movie.getString("original_language")
                val originalTitle=  movie.getString("original_title")
                val video=  movie.getBoolean("video")
                val title =  movie.getString("title")
                val genreIds = movie.getJSONArray("genre_ids")
                val genreIdsList = ArrayList<Int>()
                for (genre in 0 until genreIds.length()) {
                    genreIdsList.add(genreIds.get(genre) as Int)
                }
                val posterPath=  movie.getString("poster_path")
                val backdropPath=  movie.getString("backdrop_path")
                val releaseDate=  movie.getString("release_date")
                val popularity=  movie.getDouble("popularity")
                val voteAverage=  movie.getDouble("vote_average")
                val id  =  movie.getInt("id")
                val adult =  movie.getBoolean("adult")
                val voteCount=  movie.getInt("vote_count")

                val movieRespone = ResultsMovieResponse(overview, originalLanguage, originalTitle, video, title, genreIdsList ,posterPath,backdropPath,releaseDate,popularity,voteAverage,id,adult,voteCount)

                list.add(movieRespone)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

        fun loadMovieById (idmovie : String) : List<ResultsMovieResponse> {
        var list =  ArrayList<ResultsMovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("results")

            for ( i in 0 until listArray.length()){
                val movie = listArray.getJSONObject(i)
                val movieid =  movie.getInt("id").toString()

                if (idmovie.equals(movieid)) {
                    val overview =  movie.getString("overview")
                    val originalLanguage=  movie.getString("original_language")
                    val originalTitle=  movie.getString("original_title")
                    val video=  movie.getBoolean("video")
                    val title =  movie.getString("title")
                    val genreIds = movie.getJSONArray("genre_ids")
                    val genreIdsList = ArrayList<Int>()
                    for (genre in 0 until genreIds.length()) {
                        genreIdsList.add(genreIds.get(genre) as Int)
                    }
                    val posterPath=  movie.getString("poster_path")
                    val backdropPath=  movie.getString("backdrop_path")
                    val releaseDate=  movie.getString("release_date")
                    val popularity=  movie.getDouble("popularity")
                    val voteAverage=  movie.getDouble("vote_average")
                    val id  =  movie.getInt("id")
                    val adult =  movie.getBoolean("adult")
                    val voteCount=  movie.getInt("vote_count")

                    val movieResponse = ResultsMovieResponse  (overview, originalLanguage, originalTitle, video, title, genreIdsList ,posterPath,backdropPath,releaseDate,popularity,voteAverage,id,adult,voteCount)
                    list.add(movieResponse)
                }


            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return  list

    }

    fun loadTVShow(): List<ResultsTVShowResponse> {
        val list = ArrayList<ResultsTVShowResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TVShowResponses.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tv = listArray.getJSONObject(i)

                val firstAirDate = tv.getString("first_air_date")
                val overview = tv.getString("overview")
                val originalLanguage = tv.getString("original_language")
                val genreIds = tv.getJSONArray("genre_ids")
                val genreIdsList = ArrayList<Int>()
                for (genre in 0 until genreIds.length()) {
                    genreIdsList.add(genreIds.get(genre) as Int)
                }
                val posterPath = tv.getString("poster_path")
                val originCountry = tv.getJSONArray("origin_country")
                val originCountryList = ArrayList<String>()
                for (origincountry in 0 until originCountry.length()) {
                    originCountryList.add(originCountry.get(origincountry) as String)
                }
                val backdropPath = tv.getString("backdrop_path")
                val originalName = tv.getString("original_name")
                val popularity = tv.getDouble("popularity")
                val voteAverage = tv.getDouble("vote_average")
                val name = tv.getString("name")
                val id = tv.getInt("id")
                val voteCount = tv.getInt("vote_count")


                val TVShowRespone = ResultsTVShowResponse(
                        firstAirDate, overview, originalLanguage, genreIdsList, posterPath, originCountryList, backdropPath, originalName,
                        popularity, voteAverage, name, id, voteCount)

                list.add(TVShowRespone)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }



    fun loadTVShowById(idtvshow : String): List<ResultsTVShowResponse> {
        val list = ArrayList<ResultsTVShowResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TVShowResponses.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tv = listArray.getJSONObject(i)
                val idtv  =  tv.getInt("id")

                if (idtv.equals(idtvshow)) {
                    val firstAirDate = tv.getString("first_air_date")
                    val overview = tv.getString("overview")
                    val originalLanguage = tv.getString("original_language")
                    val genreIds = tv.getJSONArray("genre_ids")
                    val genreIdsList = ArrayList<Int>()
                    for (genre in 0 until genreIds.length()) {
                        genreIdsList.add(genreIds.get(genre) as Int)
                    }
                    val posterPath = tv.getString("poster_path")
                    val originCountry = tv.getJSONArray("origin_country")
                    val originCountryList = ArrayList<String>()
                    for (origincountry in 0 until originCountry.length()) {
                        originCountryList.add(originCountry.get(origincountry) as String)
                    }
                    val backdropPath = tv.getString("backdrop_path")
                    val originalName = tv.getString("original_name")
                    val popularity = tv.getDouble("popularity")
                    val voteAverage = tv.getDouble("vote_average")
                    val name = tv.getString("name")
                    val id = tv.getInt("id")
                    val voteCount = tv.getInt("vote_count")

                    val TVShowRespone = ResultsTVShowResponse(
                            firstAirDate, overview, originalLanguage, genreIdsList, posterPath, originCountryList, backdropPath, originalName,
                            popularity, voteAverage, name, id, voteCount)

                    list.add(TVShowRespone)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

}

