package com.example.feed.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.util.textDp
import com.example.util.toColor

@Composable
internal fun StationList(
    uiState: StationListUiState?,
    eventListener: StationListUiState.StationEventListener,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        uiState?.stations?.let { stations ->
            items(
                items = stations,
                key = { station -> station.stationIdx }
            ) { station ->
                Station(
                    emphasizeText = uiState.searchText,
                    uiState = station,
                    eventListener = eventListener,
                )
            }
        }
    }
}

@Composable
internal fun Station(
    emphasizeText: String,
    uiState: StationListUiState.Station,
    eventListener: StationListUiState.StationEventListener,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { eventListener.onStationClick(uiState) })
            .padding(horizontal = 16.dp)
            .height(48.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = buildAnnotatedString {
            if (uiState.name.contains(emphasizeText)) {
                append(uiState.name.substringBefore(emphasizeText))
                withStyle(SpanStyle(Color.Green)) { append(emphasizeText) }
                append(uiState.name.substringAfter(emphasizeText))
            } else {
                append(emphasizeText)
            }
        })
        Spacer(modifier = Modifier.weight(1f))
        uiState.lines.map { line ->
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .border(1.dp, line.colorCode.toColor(), RoundedCornerShape(percent = 100))
                    .padding(horizontal = 4.dp)
            ) {
                Text(
                    text = line.name,
                    fontSize = 10.textDp,
                    color = line.colorCode.toColor(),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun StationListPreview() {
    StationList(
        uiState = StationListUiState(
            searchText = "나나",
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
                    lines = listOf(
                        StationListUiState.Station.Line(
                            name = "신분당선",
                            colorCode = "#aaffff"
                        ),
                    ),
                ),
            )
        ),
        eventListener = object : StationListUiState.StationEventListener {},
    )
}