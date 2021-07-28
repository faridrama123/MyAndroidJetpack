package com.faridrama123.app.data.source.remote.response

data class TVShowPopularResponse(

	val page: Int,

	val totalPages: Int,

	val results: List<ResultsTVShowResponse>,

	val totalResults: Int
)

data class ResultsTVShowResponse(

	val firstAirDate: String,
	val overview: String,
	val originalLanguage: String,
	val genreIds: List<Int>,
	val posterPath: String,
	val originCountry: List<String>,
	val backdropPath: String,
	val originalName: String,
	val popularity: Double,
	val voteAverage: Double,
	val name: String,
	val id: Int,
	val voteCount: Int
)

