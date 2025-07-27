package hnau.common.app.projector.uikit

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

data class ContainerStyle(
    val container: Color,
    val content: Color,
) {

    fun interface Factory {

        @Composable
        fun rememberContainerStyle(): ContainerStyle
    }

    companion object {

        val neutral = Factory {
            val container = MaterialTheme.colorScheme.surfaceContainer
            val content = MaterialTheme.colorScheme.onSurface
            remember(container, content) {
                ContainerStyle(
                    container = container,
                    content = content,
                )
            }
        }

        val primary = Factory {
            val container = MaterialTheme.colorScheme.primaryContainer
            val content = MaterialTheme.colorScheme.onPrimaryContainer
            remember(container, content) {
                ContainerStyle(
                    container = container,
                    content = content,
                )
            }
        }

        val secondary = Factory {
            val container = MaterialTheme.colorScheme.secondaryContainer
            val content = MaterialTheme.colorScheme.onSecondaryContainer
            remember(container, content) {
                ContainerStyle(
                    container = container,
                    content = content,
                )
            }
        }

        val tertiary = Factory {
            val container = MaterialTheme.colorScheme.tertiaryContainer
            val content = MaterialTheme.colorScheme.onTertiaryContainer
            remember(container, content) {
                ContainerStyle(
                    container = container,
                    content = content,
                )
            }
        }

        val error = Factory {
            val container = MaterialTheme.colorScheme.errorContainer
            val content = MaterialTheme.colorScheme.onErrorContainer
            remember(container, content) {
                ContainerStyle(
                    container = container,
                    content = content,
                )
            }
        }
    }
}