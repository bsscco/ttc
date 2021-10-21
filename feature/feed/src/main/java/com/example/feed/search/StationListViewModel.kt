package com.example.feed.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.subway.LoadStationsUseCase
import com.example.domain.subway.LoadStationsUseCase.LoadStationsParams.LoadStationsNameMatchedParams
import com.example.domain.subway.SaveFavoriteStationUseCase
import com.example.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
internal class StationListViewModel @Inject constructor(
    private val loadStationsUseCase: LoadStationsUseCase,
    private val saveFavoriteStationUseCase: SaveFavoriteStationUseCase,
) : ViewModel(), StationListUiState.StationEventListener {

    private val searchTextFlow = MutableStateFlow<String?>(null)

    val uiStateFlow = getStationListUiStateFlow()

    private val _closeScreen = MutableLiveData<Event<Unit>>()
    val closeScreen: LiveData<Event<Unit>>
        get() = _closeScreen

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast: LiveData<Event<String>>
        get() = _showToast

    fun onSearchTextChange(text: String) {
        searchTextFlow.value = text
    }

    private fun getStationListUiStateFlow() = searchTextFlow
        .filterNotNull()
        .filter { it.isNotBlank() }
        .flatMapLatest { searchText ->
            combine(
                flowOf(Result.Success(searchText)),
                loadStationsUseCase(LoadStationsNameMatchedParams(searchText)),
            ) { a, b -> a to b }
        }
        .mapSuccessDataToPairResult()
        .mapSuccessData { (searchText, stations) ->
            withResult { StationListUiStateCreator.create(searchText, stations) }
        }
        .onErrorOrCatch { error ->
            error.printStackTrace()
            _showToast.emit(error.message ?: "error")
        }
        .filterSuccess()
        .mapToData()

    override fun onStationClick(uiState: StationListUiState.Station) {
        viewModelScope.launch {
            val result = saveFavoriteStationUseCase(SaveFavoriteStationUseCase.SaveFavoriteStationParams(uiState.stationIdx))
            if (result.succeeded) {
                _showToast.emit("${uiState.name}을 좋아요 목록에 추가하였습니다.")
                _closeScreen.emit()
            } else {
                val error = (result as Result.Error).exception
                error.printStackTrace()
                _showToast.emit(error.message ?: "error")
            }
        }
    }
}