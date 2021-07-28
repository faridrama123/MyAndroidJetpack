
package com.faridrama123.app.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")

data class MovieEntity(

	@ColumnInfo(name="overview")
	val overview: String,

	@ColumnInfo(name="original_language")
	val originalLanguage: String,

	@ColumnInfo(name="original_title")
	val originalTitle: String,

	@ColumnInfo(name="video")
	val video: Boolean,

	@ColumnInfo(name="title")
	val title: String,

	@ColumnInfo(name="genre_ids")
	val genreIds: List<Int>,

	@ColumnInfo(name="poster_path")
	val posterPath: String,

	@ColumnInfo(name="backdrop_path")
	val backdropPath: String,

	@ColumnInfo(name="release_date")
	val releaseDate: String,

	@ColumnInfo(name="popularity")
	val popularity: Double,

	@ColumnInfo(name="vote_average")
	val voteAverage: Double,

	@PrimaryKey
	@NonNull
	@ColumnInfo(name="id")
	val id: Int,

	@ColumnInfo(name="adult")
	val adult: Boolean,

	@ColumnInfo(name="vote_count")
	val voteCount: Int,

	@ColumnInfo(name="isFavorite")
	var isFavorite: Boolean
)

