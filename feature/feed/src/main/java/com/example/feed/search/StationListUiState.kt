package com.example.feed.search

import com.example.util.NoAction

internal data class StationListUiState(
    val searchText: String,
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
        fun onStationClick(uiState: Station) = NoAction
    }
}