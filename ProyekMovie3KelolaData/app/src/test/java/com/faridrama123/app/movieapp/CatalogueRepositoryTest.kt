package com.faridrama123.app.movieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.faridrama123.app.movieapp.data.FakeCatalogueRepository
import com.faridrama123.app.movieapp.data.source.local.LocalDataSource
import com.faridrama123.app.movieapp.data.source.local.entity.MovieEntity
import com.faridrama123.app.movieapp.data.source.local.entity.TVShowEntity
import com.faridrama123.app.movieapp.data.source.remote.RemoteDataSource
import com.faridrama123.app.movieapp.utils.AppExecutors
import com.faridrama123.app.movieapp.utils.DataDummy
import com.faridrama123.app.movieapp.utils.PagedListUtil
import com.faridrama123.app.movieapp.vo.Resource

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import java.util.concurrent.Executor
import kotlin.text.Typography.times

class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val executor = Executor { it.run() }
    private val appExecutors = AppExecutors(executor, executor, executor)

    private val catalogueRepository = FakeCatalogueRepository(remote, local, appExecutors)

    private val movieResponse = DataDummy.generateRemoteDummyMovie()
    private val movieId = movieResponse[0].id

    private val tvShowResponse = DataDummy.generateRemoteDummyTVShow()
    private val tvshowId = tvShowResponse[0].id

    private val dummyMovie = DataDummy.generateDummyMovie()[0]
    private val dummyTVShow = DataDummy.generateDummyTVShow()[0]





    @Test
    fun getMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovie()).thenReturn(dataSourceFactory)
        catalogueRepository.getMovie()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getMovie()
        assertNotNull(movieEntity.data)
        assertEquals(movieResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieFavorites() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
       `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavoriteMovie()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getFavoriteMovie()
        assertNotNull(movieEntity.data)
        assertEquals(movieResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }


    @Test
    fun getMovieById() {
        val dataMovie = MutableLiveData<MovieEntity>()
        `when`(local.getMovieById(movieId.toString())).thenReturn(dataMovie)
        catalogueRepository.getMovieById(movieId.toString())

        val movieEntity = Resource.success(DataDummy.generateDummyMovie()[0])
        verify(local).getMovieById(movieId.toString())
        assertNotNull(movieEntity.data)
        assertNotNull(movieEntity.data?.title)
        assertEquals(movieResponse[0].title, movieEntity.data?.title)
    }

    @Test
    fun updateFavMovie() {
        val dummy = DataDummy.generateDummyMovie()[0]
        catalogueRepository.setFavoriteMovie(dummy, true)
        verify(local, times(1)).setFavoriteMovie(dummy, true)

        catalogueRepository.setFavoriteMovie(dummy, false)
        verify(local, times(1)).setFavoriteMovie(dummy, false)
    }
    @Test
    fun getTVShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        `when`(local.getTVShow()).thenReturn(dataSourceFactory)
        catalogueRepository.getTVShow()

        val tvEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTVShow()))
        verify(local).getTVShow()
        assertNotNull(tvEntity.data)
        assertEquals(tvShowResponse.size.toLong(), tvEntity.data?.size?.toLong())
    }

    @Test
    fun getTVShowFavorites() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        `when`(local.getFavoriteTVShow()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavoriteTVShow()

        val tvEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTVShow()))
        verify(local).getFavoriteTVShow()
        assertNotNull(tvEntity.data)
        assertEquals(tvShowResponse.size.toLong(), tvEntity.data?.size?.toLong())
    }


    @Test
    fun getTVShowById() {
        val dataTVShow = MutableLiveData<TVShowEntity>()
        `when`(local.getTVShowById(tvshowId.toString())).thenReturn(dataTVShow)
        catalogueRepository.getTVShowById(tvshowId.toString())

        val tvEntity = Resource.success(DataDummy.generateDummyTVShow()[0])
        verify(local).getTVShowById(tvshowId.toString())
        assertNotNull(tvEntity.data)
        assertNotNull(tvEntity.data?.originalName)
        assertEquals(tvShowResponse[0].originalName, tvEntity.data?.originalName)
    }

    @Test
    fun updateFavTVShow() {
        val dummy = DataDummy.generateDummyTVShow()[0]
        catalogueRepository.setFavoriteTVShow(dummy, true)
        verify(local, times(1)).setFavoriteTVshow(dummy, true)

        catalogueRepository.setFavoriteTVShow(dummy, false)
        verify(local, times(1)).setFavoriteTVshow(dummy, false)
    }


}