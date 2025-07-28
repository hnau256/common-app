package hnau.common.app.model.app

import hnau.common.app.model.file.File
import hnau.common.app.model.preferences.Preference
import hnau.common.app.model.theme.Hue
import hnau.common.app.model.theme.ThemeBrightness

data class AppContext(
    val brightness: Preference<ThemeBrightness?>,
    val tryUseSystemHue: Preference<Boolean>,
    val fallbackHue: Preference<Hue>,
    val filesDir: File,
)
