package com.faridrama123.proyekmovie.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.faridrama123.proyekmovie.data.ProyekRepository
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity
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
    private lateinit var viewModel: DetailInfoViewModel
    private val dummyMovie = DataDummy.generateDummyMovie()[0]
    private val movieId = dummyMovie.id


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var proyekRepository: ProyekRepository

    @Mock
    private lateinit var movieObserver: Observer<ResultsMovieEntity>


    @Before
    fun setUp() {
        viewModel = DetailInfoViewModel(proyekRepository)
        viewModel.setSelectedCourse(movieId.toString())
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<ResultsMovieEntity>()
        movie.value = dummyMovie

        `when`(proyekRepository.getMovieById(movieId.toString())).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value as ResultsMovieEntity
        verify(proyekRepository).getMovieById(movieId.toString())
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)

        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.genreIds, movieEntity.genreIds)
        assertEquals(dummyMovie.voteAverage.toString(), movieEntity.voteAverage.toString())
        assertEquals(dummyMovie.originalLanguage, movieEntity.originalLanguage)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }


}