package com.example.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.subway.DeleteFavoriteStationUseCase
import com.example.domain.subway.DeleteFavoriteStationUseCase.DeleteFavoriteStationParams
import com.example.domain.subway.LoadStationsUseCase
import com.example.domain.subway.LoadStationsUseCase.LoadStationsParams.LoadFavoriteStationsParams
import com.example.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavoriteListViewModel @Inject constructor(
    private val loadStationsUseCase: LoadStationsUseCase,
    private val deleteFavoriteStationUseCase: DeleteFavoriteStationUseCase,
) : ViewModel(), FavoriteListUiState.StationEventListener {

    val uiStateFlow = getFavoriteListUiStateFlow()

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast: LiveData<Event<String>>
        get() = _showToast

    private fun getFavoriteListUiStateFlow() = loadStationsUseCase(LoadFavoriteStationsParams)
        .mapSuccessData { stations ->
            withResult { FavoriteListUiStateCreator.create(stations) }
        }
        .onErrorOrCatch { error -> error.printStackTrace() }
        .filterSuccess()
        .mapToData()

    override fun onDeleteButtonClick(uiState: FavoriteListUiState.Station) {
        viewModelScope.launch {
            val result = deleteFavoriteStationUseCase(DeleteFavoriteStationParams(uiState.stationIdx))
            if (result.succeeded) {
                _showToast.emit("${uiState.name}의 좋아요가 취소되었습니다.")
            } else {
                val error = (result as Result.Error).exception
                error.printStackTrace()
                _showToast.emit(error.message ?: "error")
            }
        }
    }
}