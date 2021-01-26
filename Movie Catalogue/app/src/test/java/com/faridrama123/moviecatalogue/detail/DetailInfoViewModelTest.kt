package com.faridrama123.moviecatalogue.detail

import com.faridrama123.moviecatalogue.ui.detail.DetailInfoViewModel
import com.faridrama123.moviecatalogue.utils.DataDummy.generateDummyMovie
import com.faridrama123.moviecatalogue.utils.DataDummy.generateDummyTv
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailInfoViewModelTest {
    private lateinit var viewModel: DetailInfoViewModel
    private lateinit var viewModel2: DetailInfoViewModel
    private val dummyMovie = generateDummyMovie()[0]
    private val idMovie = dummyMovie.id
    private val dummyTv = generateDummyTv()[0]
    private val idTv = dummyTv.id
    @Before
    fun setUp() {
        viewModel = DetailInfoViewModel()
        viewModel.setSelectedCourse(idMovie)
        viewModel2 = DetailInfoViewModel()
        viewModel2.setSelectedCourse(idTv)
    }

    @Test
    fun movie() {
            viewModel.setSelectedCourse(dummyMovie.id)
            val movieEntity = viewModel.getmovie()
            Assert.assertNotNull(movieEntity)
            Assert.assertEquals(dummyMovie.id, movieEntity.id)
            Assert.assertEquals(dummyMovie.title, movieEntity.title)
            Assert.assertEquals(dummyMovie.rilis, movieEntity.rilis)
            Assert.assertEquals(dummyMovie.genre, movieEntity.genre)
            Assert.assertEquals(dummyMovie.rating, movieEntity.rating)
            Assert.assertEquals(dummyMovie.desc, movieEntity.desc)
            Assert.assertEquals(dummyMovie.img, movieEntity.img)
        }

    @Test
    fun tv(){
            viewModel2.setSelectedCourse(dummyTv.id)
            val tvEntity = viewModel.gettv()
            Assert.assertNotNull(tvEntity)
            Assert.assertEquals(dummyTv.id, tvEntity.id)
            Assert.assertEquals(dummyTv.title, tvEntity.title)
            Assert.assertEquals(dummyTv.rilis, tvEntity.rilis)
            Assert.assertEquals(dummyTv.genre, tvEntity.genre)
            Assert.assertEquals(dummyTv.rating, tvEntity.rating)
            Assert.assertEquals(dummyTv.desc, tvEntity.desc)
            Assert.assertEquals(dummyTv.img, tvEntity.img)
        }
}