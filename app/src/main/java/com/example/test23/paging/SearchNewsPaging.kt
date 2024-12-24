package com.example.test23.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.test23.data.Article
import com.example.test23.reopsitry.HomeRepository
import retrofit2.HttpException

class SearchNewsPaging(private val homeRepository: HomeRepository, private val search:String):PagingSource<Int,Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
       return try {
           val page=params.key?:1

           val searchNewsList= mutableListOf<Article>()
           homeRepository.getSearchNews(search = search,page=page).collect{
               it.toData()?.let { it1 -> searchNewsList.addAll(it1.articles) }
           }
           LoadResult.Page(searchNewsList, prevKey = if (page==1) null else page-1, nextKey = page+1)
       }catch (e: Exception) {
           LoadResult.Error(e)
       } catch (e: HttpException) {
           LoadResult.Error(e)
       }
    }
}