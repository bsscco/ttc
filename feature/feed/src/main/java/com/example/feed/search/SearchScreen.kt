package com.example.feed.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun SearchScreen(
    searchBoxUiState: SearchBoxUiState?,
    searchBoxEventListener: SearchBoxEventListener,
    stationListUiState: StationListUiState?,
    stationEventListener: StationListUiState.StationEventListener,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        SearchBox(
            uiState = searchBoxUiState,
            eventListener = searchBoxEventListener
        )
        Box {
            if (searchBoxUiState?.searchText.isNullOrBlank()) {
                EmptySearchText()
            } else {
                StationList(
                    uiState = stationListUiState,
                    eventListener = stationEventListener,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun SearchScreenPreview() {
    MaterialTheme {
        SearchScreen(
            searchBoxUiState = SearchBoxUiState(searchText = ""),
            searchBoxEventListener = object : SearchBoxEventListener {},
            stationListUiState = StationListUiState(
                searchText = "",
                stations = listOf(
                    StationListUiState.Station(
                        stationIdx = 1,
                        name = "바나나역",
                        lines = (0..1).map { lineIdx ->
                            StationListUiState.Station.Line(
                                name = "$lineIdx",
                                colorCode = "#ffaaff"
                            )
                        },
                    ),
                    StationListUiState.Station(
                        stationIdx = 2,
                        name = "사과역",
                        lines = (0..1).map { lineIdx ->
                            StationListUiState.Station.Line(
                                name = "$lineIdx",
                                colorCode = "#ffaaff"
                            )
                        },
                    ),
                ),
            ),
            stationEventListener = object : StationListUiState.StationEventListener {},
        )
    }
}