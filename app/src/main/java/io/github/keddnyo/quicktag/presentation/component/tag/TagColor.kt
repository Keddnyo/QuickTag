package io.github.keddnyo.quicktag.presentation.component.tag

import androidx.compose.ui.graphics.Color

sealed class TagColor(val lightColor: Color, val darkColor: Color) {
    object CUR : TagColor(lightColor = Color(color = 0xFF00943B), darkColor = Color(color = 0xFF004F1F))
    object MOD : TagColor(lightColor = Color(color = 0xFF6060FF), darkColor = Color(color = 0xFF3B3F9F))
    object EX : TagColor(lightColor = Color(color = 0xFFFF6060), darkColor = Color(color = 0xFF9F3B3B))
}