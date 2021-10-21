package com.example.util

import androidx.compose.ui.graphics.Color

fun String.toColor() = Color(android.graphics.Color.parseColor(this))