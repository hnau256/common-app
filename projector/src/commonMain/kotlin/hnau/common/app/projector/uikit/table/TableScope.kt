package hnau.common.app.projector.uikit.table

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface TableScope {

    val orientation: TableOrientation

    val corners: TableCorners

    @Composable
    fun Cell(
        isLast: Boolean,
        content: @Composable TableCorners.(Modifier) -> Unit,
    )

    fun Modifier.weight(
        @FloatRange(from = 0.0, fromInclusive = false) weight: Float,
        fill: Boolean = true,
    ): Modifier

    companion object
}