package com.example.test23.ui.fragments.football

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.test23.paging.FootBallPaging
import com.example.test23.reopsitry.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FootBallViewModel @Inject constructor(private val homeRepository: HomeRepository)

    :ViewModel() {

        val loading=MutableLiveData<Boolean>()

    val footBallList=Pager(PagingConfig(10)){
        FootBallPaging(homeRepository)
    }.flow.cachedIn(viewModelScope)
}