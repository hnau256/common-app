package hnau.common.app.projector.uikit.table.utils

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import hnau.common.app.projector.uikit.table.TableCorners
import hnau.common.app.projector.uikit.table.TableOrientation
import hnau.common.app.projector.uikit.table.TableScope

internal class TableScopeImpl(
    override val orientation: TableOrientation,
    override val corners: TableCorners,
    private val applyWeight: Modifier.(weight: Float, fill: Boolean) -> Modifier,
) : TableScope {

    private var atLeastOneCellAdded = false

    fun reset() {
        atLeastOneCellAdded = false
    }

    @Composable
    override fun Cell(
        isLast: Boolean,
        content: @Composable (TableCorners.(Modifier) -> Unit),
    ) {
        corners
            .close(
                orientation = orientation,
                startOrTop = atLeastOneCellAdded,
                endOrBottom = !isLast,
            )
            .content(
                orientation.fold(
                    ifHorizontal = { Modifier.fillMaxHeight() },
                    ifVertical = { Modifier.fillMaxWidth() },
                )
            )
        atLeastOneCellAdded = true
    }

    override fun Modifier.weight(
        weight: Float,
        fill: Boolean,
    ): Modifier = applyWeight(weight, fill)
}

@Composable
private fun TableScope.Companion.remember(
    orientation: TableOrientation,
    corners: TableCorners,
    applyWeight: Modifier.(weight: Float, fill: Boolean) -> Modifier,
): TableScopeImpl = remember(orientation, corners, applyWeight) {
    TableScopeImpl(
        orientation = orientation,
        corners = corners,
        applyWeight = applyWeight,
    )
}

@Composable
internal fun TableScope.Companion.remember(
    orientation: TableOrientation,
    corners: TableCorners,
    columnScope: ColumnScope,
): TableScopeImpl = remember(
    orientation = orientation,
    corners = corners,
) { weight, fill ->
    with(columnScope) {
        weight(
            weight = weight,
            fill = fill,
        )
    }
}

@Composable
internal fun TableScope.Companion.remember(
    orientation: TableOrientation,
    corners: TableCorners,
    rowScope: RowScope,
): TableScopeImpl = remember(
    orientation = orientation,
    corners = corners,
) { weight, fill ->
    with(rowScope) {
        weight(
            weight = weight,
            fill = fill,
        )
    }
}