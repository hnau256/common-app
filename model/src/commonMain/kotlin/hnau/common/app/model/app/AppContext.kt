package hnau.common.app.model.app

import hnau.common.app.model.ThemeBrightness
import hnau.common.app.model.color.material.MaterialHue
import hnau.common.app.model.preferences.Preference
import hnau.common.app.model.file.File

data class AppContext(
    val brightness: Preference<ThemeBrightness?>,
    val tryUseSystemHue: Preference<Boolean>,
    val fallbackHue: Preference<MaterialHue>,
    val filesDir: File,
)
