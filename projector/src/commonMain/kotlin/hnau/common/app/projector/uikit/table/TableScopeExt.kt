package hnau.common.app.projector.uikit.table

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun TableScope.Subtable(
    isLast: Boolean,
    configModifier: (Modifier) -> Modifier = { it },
    content: @Composable TableScope.() -> Unit,
) {
    Cell(
        isLast = isLast,
    ) { modifier ->
        Table(
            modifier = configModifier(modifier),
            orientation = orientation.opposite,
            corners = this,
            content = content,
        )
    }
}

@Composable
fun TableScope.CellBox(
    isLast: Boolean,
    configModifier: (Modifier) -> Modifier = { it },
    backgroundColor: Color = TableDefaults.cellColor,
    contentAlignment: Alignment = Alignment.Center,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.(Shape) -> Unit,
) {
    Cell(
        isLast = isLast,
    ) { modifier ->
        val shape = shape
        Box(
            modifier = modifier
                .let(configModifier)
                .background(
                    color = backgroundColor,
                    shape = shape,
                ),
            contentAlignment = contentAlignment,
            propagateMinConstraints = propagateMinConstraints,
        ) {
            content(shape)
        }
    }
}