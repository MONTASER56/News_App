package com.example.test23.ui.fragments.search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.test23.data.Article
import com.example.test23.paging.SearchNewsPaging
import com.example.test23.reopsitry.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(private val homeRepository: HomeRepository):ViewModel() {
    val search= MutableStateFlow("")
    var searchList:Flow<PagingData<Article>>? = null


fun setSearchText(query:String){
    viewModelScope.launch {    search.emit(query)}

}

     @OptIn(FlowPreview::class)
     fun getSearchNews(){
        viewModelScope.launch {

            search.debounce(300).distinctUntilChanged().collect {
                searchList = Pager(PagingConfig(10)) {
                    SearchNewsPaging(homeRepository, search = it)

                }.flow.cachedIn(viewModelScope)
            }
        }

}}