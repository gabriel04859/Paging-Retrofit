package com.gabrielribeiro.pading3recycler.domain.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gabrielribeiro.pading3recycler.domain.model.PixabayHitsResponse
import retrofit2.HttpException
import java.io.IOException

const val DEFAULT_FIRST_KEY = 1
class DataSource(private val api: PixaBayApi): PagingSource<Int, PixabayHitsResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PixabayHitsResponse> {
        val currentPageList = params.key ?: DEFAULT_FIRST_KEY
        return  try {
            val response = api.getPhotos(currentPageList)
            val data = response.body()?.pixabayImage ?: emptyList()
            val prevKey = if (currentPageList == DEFAULT_FIRST_KEY) null else currentPageList - 1
            val nextKey = if (data.isEmpty()) null else currentPageList.plus(1)
            LoadResult.Page(data,
                prevKey,
                nextKey
            )
        }catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PixabayHitsResponse>): Int? {
       return state.anchorPosition?.let { anchorPosition ->
           state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
               ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
       }
    }

}