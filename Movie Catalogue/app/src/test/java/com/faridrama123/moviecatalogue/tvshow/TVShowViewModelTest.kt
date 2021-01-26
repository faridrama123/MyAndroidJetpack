package com.faridrama123.moviecatalogue.tvshow

import com.faridrama123.moviecatalogue.ui.TVShow.TVShowViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TVShowViewModelTest {
    private lateinit var viewModel: TVShowViewModel

    @Before
    fun setUp() {
        viewModel = TVShowViewModel()
    }


    @Test
    fun courses() {
        val tvEntities = viewModel.gettv()
        Assert.assertNotNull(tvEntities)
        Assert.assertEquals(10, tvEntities.size.toLong())
    }
}