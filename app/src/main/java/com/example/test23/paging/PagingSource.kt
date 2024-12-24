package com.example.test23.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.test23.data.Article
import com.example.test23.reopsitry.HomeRepository
import retrofit2.HttpException

class PagingSource(private val homeRepository: HomeRepository) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            val page=state.closestPageToPosition(it)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val currentPage = params.key ?: 1
        return try {




            val response = homeRepository.getNews(currentPage).articles




            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else currentPage-1,
                nextKey =   currentPage+1

            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}