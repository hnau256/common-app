package hnau.common.app.projector.uikit

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import hnau.common.app.model.ThemeBrightness
import hnau.common.app.model.color.material.MaterialColors
import hnau.common.app.model.color.material.MaterialHue
import hnau.common.app.model.color.material.MaterialLightness
import hnau.common.app.model.color.material.get
import hnau.common.app.projector.uikit.color.compose
import hnau.common.app.projector.utils.system

fun interface ContainerStyle {

    data class Colors(
        val container: Color,
        val content: Color,
        val single: Color
    )

    @Composable
    fun rememberColors(): Colors

    companion object {

        fun extract(
            extractColors: ColorScheme.() -> Colors,
        ): ContainerStyle = ContainerStyle {
            val colorScheme = MaterialTheme.colorScheme
            remember(colorScheme, extractColors) {
                colorScheme.extractColors()
            }
        }

        val neutral: ContainerStyle = extract {
            Colors(
                container = surfaceContainer,
                content = onSurface,
                single = onSurface,
            )
        }

        val primary: ContainerStyle = extract {
            Colors(
                container = primaryContainer,
                content = onPrimaryContainer,
                single = primary,
            )
        }

        val secondary: ContainerStyle = extract {
            Colors(
                container = secondaryContainer,
                content = onSecondaryContainer,
                single = secondary,
            )
        }

        val tertiary: ContainerStyle = extract {
            Colors(
                container = tertiaryContainer,
                content = onTertiaryContainer,
                single = tertiary,
            )
        }

        val error: ContainerStyle = extract {
            Colors(
                container = errorContainer,
                content = onErrorContainer,
                single = error,
            )
        }

        fun create(
            hue: MaterialHue,
        ): ContainerStyle = ContainerStyle {
            val brightness = ThemeBrightness.system
            val colors = MaterialColors[hue]
            remember(brightness, colors) {
                Colors(
                    container = colors
                        .get(
                            lightness = when (brightness) {
                                ThemeBrightness.Light -> MaterialLightness.V100
                                ThemeBrightness.Dark -> MaterialLightness.V900
                            }
                        )
                        .compose,
                    content = colors
                        .get(
                            lightness = when (brightness) {
                                ThemeBrightness.Light -> MaterialLightness.V900
                                ThemeBrightness.Dark -> MaterialLightness.V100
                            }
                        )
                        .compose,
                    single = colors
                        .get(
                            lightness = when (brightness) {
                                ThemeBrightness.Light -> MaterialLightness.V700
                                ThemeBrightness.Dark -> MaterialLightness.V300
                            }
                        )
                        .compose,
                )
            }
        }
    }
}