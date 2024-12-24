package com.example.test23.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.test23.data.NewsResponse
import com.example.test23.paging.PagingSource
import com.example.test23.reopsitry.HomeRepository
import com.example.test23.reopsitry.ResponseStats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
)


    : ViewModel() {
    val search = MutableStateFlow("")


    val newsList = MutableStateFlow<ResponseStats<NewsResponse?>?>(null)
    val newsList2 = Pager(
        PagingConfig(1)) {
        PagingSource(homeRepository)
    }.flow.cachedIn(viewModelScope)


    fun getNews() {
        viewModelScope.launch {
            newsList2.collect {

            }
        }
    }


    val _footBallList = MutableStateFlow<ResponseStats<NewsResponse?>?>(null)
    val FootBallList: StateFlow<ResponseStats<NewsResponse?>?> = _footBallList
    fun getFootBallNews() {
        viewModelScope.launch {
            homeRepository.getFootBallNews().collect {
                _footBallList.emit(it)
            }
        }
    }

    fun getSearchNews() {
        viewModelScope.launch {
            search.collect {
                homeRepository.getSearchNews(it, 1).distinctUntilChanged()

                    .debounce(3000).collect { data ->
                        newsList.value = data
                    }
            }
        }
    }


}









