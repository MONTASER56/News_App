package com.example.test23.reopsitry

import android.util.Log
import com.example.test23.network.ApiServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(

    private val apiServices: ApiServices,
) {

    suspend fun getNews(page: Int) = apiServices.getNews(page = page)
    suspend fun getSearchNews(search: String, page: Int) =
        wrapWithFlow { apiServices.getSearchNews(search, page = page) }

    suspend fun getFootBallNews() = wrapWithFlow { apiServices.getSearchNews("footBall", 1) }


}

private fun <T> wrapWithFlow(function: suspend () -> Response<T>): Flow<ResponseStats<T?>> {
    return flow {
        emit(ResponseStats.loading)
        try {

            val result = function()
            if (result.isSuccessful) {
                emit(ResponseStats.Success(result.body()))
                Log.d("TAG1", "${result.body().toString()}()): ")
            } else {
                emit(ResponseStats.Error(result.message()))
                Log.d("TAG2", "$result.message()): ")
            }
        } catch (e: Exception) {
            emit(ResponseStats.Error(e.message.toString()))
            Log.d("TAG3", "$e.message: ")
        }
    }
}