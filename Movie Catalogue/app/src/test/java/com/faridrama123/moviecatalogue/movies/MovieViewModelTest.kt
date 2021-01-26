package com.faridrama123.moviecatalogue.movies

import com.faridrama123.moviecatalogue.data.MOVIEEntity
import com.faridrama123.moviecatalogue.ui.movies.MovieViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun courses()
    {
            val movieEntities: List<MOVIEEntity> = viewModel.getmovies()
            Assert.assertNotNull(movieEntities)
            Assert.assertEquals(10, movieEntities.size.toLong())
        }
}