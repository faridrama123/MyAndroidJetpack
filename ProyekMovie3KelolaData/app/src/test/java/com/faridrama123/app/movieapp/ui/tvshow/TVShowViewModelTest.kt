package com.faridrama123.app.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.faridrama123.app.data.CatalogueRepository
import com.faridrama123.app.data.source.local.entity.MovieEntity
import com.faridrama123.app.data.source.local.entity.TVShowEntity
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
class TVShowViewModelTest {

    private lateinit var viewModel: TVShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TVShowEntity>>>

    @Before
    fun setUp() {
        viewModel = TVShowViewModel(catalogueRepository)
    }

    @Test
    fun `getTVShow should be success`() {
        val tvshow = PagedTestDataSources.snapshot(DataDummy.generateDummyTVShow())
        val expected = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        expected.value = Resource.success(tvshow)

        `when`(catalogueRepository.getTVShow()).thenReturn(expected)

        viewModel.getTVShow().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTVShow().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getTVShow should be success but data is empty`() {
        val tvshow = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        expected.value = Resource.success(tvshow)

        `when`(catalogueRepository.getTVShow()).thenReturn(expected)

        viewModel.getTVShow().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getTVShow().value?.data?.size
       assertTrue("size of data should be 0, actual is $actualValueDataSize", actualValueDataSize == 0)
    }

    @Test
    fun `getTVShow should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        `when`(catalogueRepository.getTVShow()).thenReturn(expected)

        viewModel.getTVShow().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualMessage = viewModel.getTVShow().value?.message
        assertEquals(expectedMessage, actualMessage)
    }

    class PagedTestDataSources private constructor(private val items: List<TVShowEntity>) : PositionalDataSource<TVShowEntity>() {
        companion object {
            fun snapshot(items: List<TVShowEntity> = listOf()): PagedList<TVShowEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                        .setNotifyExecutor(Executors.newSingleThreadExecutor())
                        .setFetchExecutor(Executors.newSingleThreadExecutor())
                        .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TVShowEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TVShowEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}