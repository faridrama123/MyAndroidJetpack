package com.faridrama123.proyekmovie.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.faridrama123.proyekmovie.data.ProyekRepository
import com.faridrama123.proyekmovie.data.local.entity.ResultsMovieEntity
import com.faridrama123.proyekmovie.data.local.entity.ResultsTVShowEntity
import com.faridrama123.proyekmovie.ui.TVShow.TVShowViewModel
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
class TVShowViewModelTest {

    private lateinit var viewModel: TVShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var proyekRepository: ProyekRepository

    @Mock
    private lateinit var observer: Observer<List<ResultsTVShowEntity>>

    @Before
    fun setUp() {
        viewModel = TVShowViewModel(proyekRepository)
    }

    @Test
    fun getTVshow() {
        val dummyTVshow = DataDummy.generateDummyTVShow()
        val tvshow = MutableLiveData<List<ResultsTVShowEntity>>()
        tvshow.value = dummyTVshow

        `when`(proyekRepository.getTVShow()).thenReturn(tvshow)
        val tvShowEntities = viewModel.getTVShow().value
        verify(proyekRepository).getTVShow()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.size)

        viewModel.getTVShow().observeForever(observer)
        verify(observer).onChanged(dummyTVshow)
    }
}