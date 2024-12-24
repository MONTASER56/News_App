package com.example.test23.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.test23.data.Article
import com.example.test23.reopsitry.HomeRepository
import retrofit2.HttpException

class FootBallPaging (private val homeRepository: HomeRepository) : PagingSource<Int, Article>() {


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, Article> {
        return try {
            val searchar_ticles = mutableListOf<Article>()

            val currentPage = params.key ?: 1

            val response = homeRepository.getFootBallNews().collect{
                it.toData()?.let { it1 -> searchar_ticles.addAll(it1.articles) }


            }


            PagingSource.LoadResult.Page(
                data = searchar_ticles,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = currentPage + 1

            )


        } catch (e: Exception) {
            PagingSource.LoadResult.Error(e)
        } catch (e: HttpException) {
            PagingSource.LoadResult.Error(e)
        }
    }
}