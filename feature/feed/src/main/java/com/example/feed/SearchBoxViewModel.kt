package com.example.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.subway.ClearFavoriteStationsUseCase
import com.example.util.Event
import com.example.util.Result
import com.example.util.emit
import com.example.util.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchBoxViewModel @Inject constructor(
    private val clearFavoriteStationsUseCase: ClearFavoriteStationsUseCase,
) : ViewModel(), SearchBoxEventListener {

    private val _navigateToSearch = MutableLiveData<Event<Unit>>()
    val navigateToSearch: LiveData<Event<Unit>>
        get() = _navigateToSearch

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast: LiveData<Event<String>>
        get() = _showToast

    override fun onSearchBoxClick() = _navigateToSearch.emit()

    override fun onClearFavoritesButtonClick() {
        viewModelScope.launch {
            val result = clearFavoriteStationsUseCase(Unit)
            if (result.succeeded) {
                _showToast.emit("모든 좋아요가 취소되었습니다.")
            } else {
                val error = (result as Result.Error).exception
                error.printStackTrace()
                _showToast.emit(error.message ?: "error")
            }
        }
    }
}