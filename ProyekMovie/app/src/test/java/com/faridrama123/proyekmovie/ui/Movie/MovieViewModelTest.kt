package com.faridrama123.proyekmovie.ui.Movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.faridrama123.proyekmovie.data.ProyekRepository
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity
import com.faridrama123.proyekmovie.ui.movies.MovieViewModel
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var proyekRepository: ProyekRepository

    @Mock
    private lateinit var observer: Observer<List<ResultsMovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(proyekRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = DataDummy.generateDummyMovie()
        val movie = MutableLiveData<List<ResultsMovieEntity>>()
        movie.value = dummyMovie

        `when`(proyekRepository.getMovie()).thenReturn(movie)
        val movieEntities = viewModel.getMovie().value
        verify(proyekRepository).getMovie()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}