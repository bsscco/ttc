package com.example.domain.subway

import androidx.annotation.Keep

@Keep
data class Station(
    val idx: Int,
    val name: String,
    val lines: List<Line>,
) {
    @Keep
    data class Line(
        val idx: Int,
        val name: String,
        val colorCode: String,
    )
}