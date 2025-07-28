package hnau.common.app.projector.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import hnau.common.app.model.theme.ThemeBrightness

val ThemeBrightness.Companion.system: ThemeBrightness
    @Composable
    get() = when (isSystemInDarkTheme()) {
        true -> ThemeBrightness.Dark
        false -> ThemeBrightness.Light
    }