package com.example.feed

import com.example.util.NoAction

internal interface SearchBoxEventListener {

    fun onSearchBoxClick() = NoAction

    fun onClearFavoritesButtonClick() = NoAction
}