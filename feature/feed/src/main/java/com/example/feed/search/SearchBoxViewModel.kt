package com.example.feed.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class SearchBoxViewModel @Inject constructor() : ViewModel(), SearchBoxEventListener {

    private val searchTextFlow = MutableStateFlow("")
    val searchText = searchTextFlow.asLiveData()

    val uiStateFlow = getSearchBoxUiStateFlow()

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast: LiveData<Event<String>>
        get() = _showToast

    private fun getSearchBoxUiStateFlow() = searchTextFlow
        .map { searchText ->
            withResult {
                SearchBoxUiState(searchText = searchText)
            }
        }
        .onErrorOrCatch { error ->
            error.printStackTrace()
            _showToast.emit(error.message ?: "error")
        }
        .filterSuccess()
        .mapToData()

    override fun onSearchTextChanged(text: String) {
        searchTextFlow.value = text
    }

    override fun onClearSearchTextButtonClick() {
        searchTextFlow.value = ""
    }
}