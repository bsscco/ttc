package com.example.data.subway

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SubwayResponse(
    @SerialName("result_msg") val resultMessage: String,
    @SerialName("result_code") val resultCode: Int,
    @SerialName("version") val version: Int,
    @SerialName("subway_stations") val subwayStations: List<Station>,
    @SerialName("subway_lines") val subwayLines: List<Line>,
)
