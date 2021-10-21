package com.example.feed.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
internal fun SearchBox(
    uiState: SearchBoxUiState?,
    eventListener: SearchBoxEventListener,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .zIndex(1f),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                singleLine = true,
                value = uiState?.searchText ?: "",
                onValueChange = { eventListener.onSearchTextChanged(it) },
            )
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = eventListener::onClearSearchTextButtonClick
                    )
                    .padding(horizontal = 8.dp),
                imageVector = Icons.Rounded.Close,
                contentDescription = "",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun SearchBoxPreview() {
    SearchBox(
        uiState = SearchBoxUiState(searchText = "사과"),
        eventListener = object : SearchBoxEventListener {},
    )
}