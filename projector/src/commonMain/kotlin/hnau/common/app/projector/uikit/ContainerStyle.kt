package hnau.common.app.projector.uikit

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

fun interface ContainerStyle {

    data class Colors(
        val container: Color,
        val content: Color,
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
            )
        }

        val primary: ContainerStyle = extract {
            Colors(
                container = primaryContainer,
                content = onPrimaryContainer,
            )
        }

        val secondary: ContainerStyle = extract {
            Colors(
                container = secondaryContainer,
                content = onSecondaryContainer,
            )
        }

        val tertiary: ContainerStyle = extract {
            Colors(
                container = tertiaryContainer,
                content = onTertiaryContainer,
            )
        }

        val error: ContainerStyle = extract {
            Colors(
                container = errorContainer,
                content = onErrorContainer,
            )
        }
    }
}