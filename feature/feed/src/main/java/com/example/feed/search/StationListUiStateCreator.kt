package com.example.feed.search

import com.example.domain.subway.Station

internal object StationListUiStateCreator {

    fun create(searchText: String, stations: List<Station>) = StationListUiState(
        searchText = searchText,
        stations = stations.map { station ->
            StationListUiState.Station(
                stationIdx = station.idx,
                name = station.name,
                lines = station.lines.map { line ->
                    StationListUiState.Station.Line(
                        name = line.name,
                        colorCode = line.colorCode,
                    )
                }
            )
        }
    )
}