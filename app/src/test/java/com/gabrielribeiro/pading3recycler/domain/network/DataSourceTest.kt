package com.gabrielribeiro.pading3recycler.domain.network

import androidx.paging.PagingSource
import com.gabrielribeiro.pading3recycler.domain.model.PixabayHitsResponse
import io.mockk.coEvery
import io.mockk.spyk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import retrofit2.Response


@ExperimentalCoroutinesApi
class DataSourceTest {

    private var mockAAA = spyk<PixaBayApi>()
    private val dataSource = DataSource(mockAAA, null)

    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfPixabaySource() {


    }

}