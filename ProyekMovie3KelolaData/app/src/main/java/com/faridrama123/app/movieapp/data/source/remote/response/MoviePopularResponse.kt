package com.faridrama123.app.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class MoviePopularResponse(

		val page: Int,

		val totalPages: Int,

		val results: List<ResultsMovieResponse>,

		val totalResults: Int
)

@Parcelize
data class ResultsMovieResponse(

		val overview: String,

		val originalLanguage: String,

		val originalTitle: String,

		val video: Boolean,

		val title: String,

		val genreIds: List<Int>,

		val posterPath: String,

		val backdropPath: String,

		val releaseDate: String,

		val popularity: Double,

		val voteAverage: Double,

		val id: Int,

		val adult: Boolean,

		val voteCount: Int,


) : Parcelable

