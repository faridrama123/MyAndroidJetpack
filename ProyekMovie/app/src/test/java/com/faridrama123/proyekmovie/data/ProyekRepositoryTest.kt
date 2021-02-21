package com.faridrama123.proyekmovie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.faridrama123.proyekmovie.utils.DataDummy
import com.faridrama123.proyekmovie.utils.LiveDataTestUtil

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer

class ProyekRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val proyekRepository = FakeProyekRepository(remote)

    private val movieResponses = DataDummy.generateRemoteDummyMovie()
    private val movieId = movieResponses[0].id.toString()

    private val tvShowResponses = DataDummy.generateRemoteDummyTVShow()
    private val tvShowId = tvShowResponses[0].id.toString()


    @Test
    fun getMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback)
                    .onMovieReceived(movieResponses)
            null
        }.`when`(remote).getMovie(any())
        val movieEntity = LiveDataTestUtil.getValue(proyekRepository.getMovie())
        verify(remote).getMovie(any())
        assertNotNull(movieEntity)
        assertEquals(movieResponses.size.toLong(), movieEntity.size.toLong())
    }

    @Test
    fun getMovieById() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onMovieReceived(movieResponses)
            null
        }.`when`(remote).getMovie(any())

        val movieEntity = LiveDataTestUtil.getValue(proyekRepository.getMovieById(movieId))

        verify(remote).getMovie(any())

        assertNotNull(movieEntity)
        assertNotNull(movieEntity.title)
        assertEquals(movieResponses[0].title, movieEntity.title)
    }

    @Test
    fun getTVShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTVShowCallback)
                .onTVShowReceived(tvShowResponses)
            null
        }.`when`(remote).getTVShow(any())
        val tvShowEntity = LiveDataTestUtil.getValue(proyekRepository.getTVShow())
        verify(remote).getTVShow(any())
        assertNotNull(tvShowEntity)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntity.size.toLong())
    }

    @Test
    fun getTVShowById() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTVShowCallback)
                .onTVShowReceived(tvShowResponses)
            null
        }.`when`(remote).getTVShow(any())

        val tvShowEntity = LiveDataTestUtil.getValue(proyekRepository.getTVShowById(tvShowId))

        verify(remote).getTVShow(any())

        assertNotNull(tvShowEntity)
        assertNotNull(tvShowEntity.name)
        assertEquals(tvShowResponses[0].name, tvShowEntity.name)
    }

}