package com.example.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.util.textDp
import com.example.util.toColor
import com.google.accompanist.flowlayout.FlowRow

@Composable
internal fun FavoriteList(
    uiState: FavoriteListUiState?,
    eventListener: FavoriteListUiState.StationEventListener,
) {
    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
    ) {
        uiState?.stations?.let { stations ->
            stations.map { station ->
                Station(uiState = station, eventListener = eventListener)
            }
        }
    }
}

@Composable
internal fun Station(
    uiState: FavoriteListUiState.Station,
    eventListener: FavoriteListUiState.StationEventListener,
) {
    Row(
        modifier = Modifier
            .padding(vertical = 6.dp, horizontal = 4.dp)
            .wrapContentWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(percent = 100))
            .border(1.dp, Color.Black, RoundedCornerShape(percent = 100))
            .clickable(onClick = { eventListener.onDeleteButtonClick(uiState) }),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.width(8.dp))
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
        Spacer(Modifier.width(4.dp))
        Text(text = uiState.name)
        Spacer(Modifier.width(4.dp))
        Icon(
            modifier = Modifier.padding(end = 8.dp),
            imageVector = Icons.Rounded.Delete,
            contentDescription = "",
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun StationListPreview() {
    FavoriteList(
        uiState = FavoriteListUiState(
            stations = (0 until 10).map {
                FavoriteListUiState.Station(
                    stationIdx = 1,
                    name = "${it}번역",
                    lines = (0..1).map { lineIdx ->
                        FavoriteListUiState.Station.Line(
                            name = "$lineIdx",
                            colorCode = "#ffaaff"
                        )
                    }
                )
            }
        ),
        eventListener = object : FavoriteListUiState.StationEventListener {},
    )
}