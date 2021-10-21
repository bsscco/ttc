package com.example.feed.search

import com.example.util.NoAction

internal data class SearchBoxUiState(
    val searchText: String,
)

internal interface SearchBoxEventListener {

    fun onSearchTextChanged(text: String) = NoAction

    fun onClearSearchTextButtonClick() = NoAction
}