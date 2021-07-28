package com.faridrama123.app.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.faridrama123.app.data.CatalogueRepository
import com.faridrama123.app.data.source.local.entity.MovieEntity
import com.faridrama123.app.utils.DataDummy
import com.faridrama123.app.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun `getMovie should be success`() {
        val movie = PagedTestDataSources.snapshot(DataDummy.generateDummyMovie())
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.success(movie)

        `when`(catalogueRepository.getMovie()).thenReturn(expected)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMovie().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getMovie should be success but data is empty`() {
        val movie = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.success(movie)

        `when`(catalogueRepository.getMovie()).thenReturn(expected)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getMovie().value?.data?.size
       assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    @Test
    fun `getMovie should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        `when`(catalogueRepository.getMovie()).thenReturn(expected)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualMessage = viewModel.getMovie().value?.message
        assertEquals(expectedMessage, actualMessage)
    }

    class PagedTestDataSources private constructor(private val items: List<MovieEntity>) : PositionalDataSource<MovieEntity>() {
        companion object {
            fun snapshot(items: List<MovieEntity> = listOf()): PagedList<MovieEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MovieEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}