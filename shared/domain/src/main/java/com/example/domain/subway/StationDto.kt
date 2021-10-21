package com.example.domain.subway

import androidx.annotation.Keep

@Keep
data class StationDto(
    val idx: Int,
    val name: String,
    val lines: List<Line>,
) {
    @Keep
    data class Line(
        val idx: Int,
        val shortName: String,
        val colorCode: String,
    )
}