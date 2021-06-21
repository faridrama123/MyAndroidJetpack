package com.faridrama123.proyekmovie.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.faridrama123.proyekmovie.data.ProyekRepository
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity
import com.faridrama123.proyekmovie.data.local.entity.ResultsTVShowEntity
import com.faridrama123.proyekmovie.utils.DataDummy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModelMovie: DetailInfoViewModel
    private val dummyMovie = DataDummy.generateDummyMovie()[0]
    private val movieId = dummyMovie.id

    private lateinit var viewModelTVShow: DetailInfoViewModel
    private val dummyTVShow = DataDummy.generateDummyTVShow()[0]
    private val tvShowId = dummyTVShow.id


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var proyekRepository: ProyekRepository

    @Mock
    private lateinit var movieObserver: Observer<ResultsMovieEntity>

    @Mock
    private lateinit var tvshowObserver: Observer<ResultsTVShowEntity>



    @Before
    fun setUp() {
        viewModelMovie = DetailInfoViewModel(proyekRepository)
        viewModelMovie.setSelectedCourse(movieId.toString())

        viewModelTVShow = DetailInfoViewModel(proyekRepository)
        viewModelTVShow.setSelectedCourse(tvShowId.toString())
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<ResultsMovieEntity>()
        movie.value = dummyMovie

        `when`(proyekRepository.getMovieById(movieId.toString())).thenReturn(movie)
        val movieEntity = viewModelMovie.getMovie().value as ResultsMovieEntity
        verify(proyekRepository).getMovieById(movieId.toString())
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)

        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.genreIds, movieEntity.genreIds)
        assertEquals(dummyMovie.voteAverage.toString(), movieEntity.voteAverage.toString())
        assertEquals(dummyMovie.originalLanguage, movieEntity.originalLanguage)

        viewModelMovie.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTVshow() {
        val tvshow = MutableLiveData<ResultsTVShowEntity>()
        tvshow.value = dummyTVShow

        `when`(proyekRepository.getTVShowById(tvShowId.toString())).thenReturn(tvshow)
        val tvShowEntity = viewModelTVShow.getTVShow().value as ResultsTVShowEntity
        verify(proyekRepository).getTVShowById(tvShowId.toString())
        assertNotNull(tvShowEntity)
        assertEquals(dummyTVShow.id, tvShowEntity.id)
        assertEquals(dummyTVShow.originalName, tvShowEntity.originalName)

        assertEquals(dummyTVShow.firstAirDate, tvShowEntity.firstAirDate)
        assertEquals(dummyTVShow.genreIds, tvShowEntity.genreIds)
        assertEquals(dummyTVShow.voteAverage.toString(), tvShowEntity.voteAverage.toString())
        assertEquals(dummyTVShow.originalLanguage, tvShowEntity.originalLanguage)

        viewModelTVShow.getTVShow().observeForever(tvshowObserver)
        verify(tvshowObserver).onChanged(dummyTVShow)
    }


}