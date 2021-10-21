package com.example.feed

import com.example.util.NoAction

internal data class FavoriteListUiState(
    val stations: List<Station>,
) {
    data class Station(
        val stationIdx: Int,
        val name: String,
        val lines: List<Line>,
    ) {
        data class Line(
            val name: String,
            val colorCode: String,
        )
    }

    interface StationEventListener {
        fun onDeleteButtonClick(uiState: Station) = NoAction
    }
}