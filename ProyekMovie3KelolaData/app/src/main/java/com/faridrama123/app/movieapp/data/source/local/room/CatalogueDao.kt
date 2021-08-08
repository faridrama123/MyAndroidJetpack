package com.faridrama123.app.movieapp.data.source.local.room



import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.faridrama123.app.movieapp.data.source.local.entity.MovieEntity
import com.faridrama123.app.movieapp.data.source.local.entity.TVShowEntity

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movieentities ORDER BY title ASC")
    fun getMovie (): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Query("SELECT * FROM movieentities where isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Update
    fun setFavoriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM movieentities WHERE id = :id")
    fun getMovieById(id: String): LiveData<MovieEntity>



    @Query("SELECT * FROM tvshowentities ORDER BY originalName ASC")
    fun getTVShow (): DataSource.Factory<Int, TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVShow(tvshow: List<TVShowEntity>)

    @Query("SELECT * FROM tvshowentities where isFavorite = 1")
    fun getFavoriteTVShow(): DataSource.Factory<Int, TVShowEntity>

    @Update
    fun setFavoriteTVShow(tvshow: TVShowEntity)

    @Query("SELECT * FROM tvshowentities WHERE id = :id")
    fun getTVShowById(id: String): LiveData<TVShowEntity>


}
