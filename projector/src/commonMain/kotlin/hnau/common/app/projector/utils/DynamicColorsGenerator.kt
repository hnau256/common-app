package hnau.common.app.projector.utils

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import hnau.common.app.model.ThemeBrightness

fun interface DynamicColorsGenerator {

    @Composable
    fun generateDynamicColors(
        brightness: ThemeBrightness,
    ): ColorScheme

    companion object
}