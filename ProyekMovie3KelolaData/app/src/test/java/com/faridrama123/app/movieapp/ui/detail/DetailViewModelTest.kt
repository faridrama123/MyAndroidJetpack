package com.faridrama123.app.movieapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.faridrama123.app.movieapp.data.CatalogueRepository
import com.faridrama123.app.movieapp.data.source.local.entity.MovieEntity
import com.faridrama123.app.movieapp.data.source.local.entity.TVShowEntity
import com.faridrama123.app.movieapp.utils.DataDummy
import com.faridrama123.app.movieapp.vo.Resource
import com.faridrama123.proyekmovie.ui.detail.DetailInfoViewModel

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
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
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var observerTVShow: Observer<Resource<TVShowEntity>>

    @Before
    fun setUp() {
        viewModelMovie = DetailInfoViewModel(catalogueRepository)
        viewModelMovie.setSelected(movieId.toString())

        viewModelTVShow = DetailInfoViewModel(catalogueRepository)
        viewModelTVShow.setSelected(tvShowId.toString())
    }



    @Test
    fun `setSelectedMovie should be success`() {
        val expected = MutableLiveData<Resource<MovieEntity>>()
        expected.value = Resource.success(DataDummy.generateDummyMovie()[0])

        `when`(catalogueRepository.getMovieById(movieId.toString())).thenReturn(expected)

        viewModelMovie.getMovieById.observeForever(observerMovie)

        verify(observerMovie).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModelMovie.getMovieById.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setFavoritesMovie verify succes add`() {
        viewModelTVShow.setFavoriteMovie(dummyMovie,true)
        verify(catalogueRepository).setFavoriteMovie(dummyMovie, true)
    }

    @Test
    fun `setFavoritesMovie verify succes remove`() {
        viewModelTVShow.setFavoriteMovie(dummyMovie,false)
        verify(catalogueRepository).setFavoriteMovie(dummyMovie, false)
    }

    @Test
    fun `setSelectedTVShow should be success`() {
        val expected = MutableLiveData<Resource<TVShowEntity>>()
        expected.value = Resource.success(DataDummy.generateDummyTVShow()[0])

        `when`(catalogueRepository.getTVShowById(tvShowId.toString())).thenReturn(expected)

        viewModelTVShow.getTVShowById.observeForever(observerTVShow)

        verify(observerTVShow).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModelTVShow.getTVShowById.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setFavoritesTVShow verify succes add`() {

        viewModelTVShow.setFavoriteTVShow(dummyTVShow,true)
        verify(catalogueRepository).setFavoriteTVShow(dummyTVShow, true)
    }

    @Test
    fun `setFavoritesTVShow verify succes remove`() {

        viewModelTVShow.setFavoriteTVShow(dummyTVShow,false)
        verify(catalogueRepository).setFavoriteTVShow(dummyTVShow, false)
    }
}