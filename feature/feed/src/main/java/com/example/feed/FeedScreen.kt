package com.example.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun FeedScreen(
    searchBoxEventListener: SearchBoxEventListener,
    favoriteListUiState: FavoriteListUiState?,
    stationEventListener: FavoriteListUiState.StationEventListener,
) {
    Column {
        SearchBox(eventListener = searchBoxEventListener)
        FavoriteList(
            uiState = favoriteListUiState,
            eventListener = stationEventListener,
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun FeedScreenPreview() {
    MaterialTheme {
        FeedScreen(
            searchBoxEventListener = object : SearchBoxEventListener {},
            favoriteListUiState = FavoriteListUiState(
                stations = listOf(
                    FavoriteListUiState.Station(
                        stationIdx = 1,
                        name = "바나나역",
                        lines = (0..1).map { lineIdx ->
                            FavoriteListUiState.Station.Line(
                                name = "$lineIdx",
                                colorCode = "#ffaaff"
                            )
                        },
                    ),
                    FavoriteListUiState.Station(
                        stationIdx = 2,
                        name = "사과역",
                        lines = (0..1).map { lineIdx ->
                            FavoriteListUiState.Station.Line(
                                name = "$lineIdx",
                                colorCode = "#ffaaff"
                            )
                        },
                    ),
                ),
            ),
            stationEventListener = object : FavoriteListUiState.StationEventListener {},
        )
    }
}