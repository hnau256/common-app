package hnau.common.app.projector.uikit.table

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.util.fastForEachIndexed
import hnau.common.kotlin.foldNullable
import hnau.common.kotlin.ifFalse
import hnau.common.app.projector.uikit.table.layout.TableLayout
import hnau.common.app.projector.uikit.utils.Dimens
import kotlinx.collections.immutable.ImmutableList

@Composable
fun Table(
    orientation: TableOrientation,
    cells: ImmutableList<Cell>,
    modifier: Modifier = Modifier,
    corners: TableCorners = TableCorners.opened,
) {
    TableLayout(
        orientation = orientation,
        modifier = modifier,
        separationPx = with(LocalDensity.current) { Dimens.chipsSeparation.roundToPx() },
    ) {
        cells.fastForEachIndexed { i, cell ->
            val closeStartOrTop = i > 0
            val closeEndOrBottom = i < cells.lastIndex
            val cellScope = remember(corners, orientation, closeStartOrTop, closeEndOrBottom) {
                CellScope(
                    orientation = orientation,
                    corners = corners.close(
                        orientation = orientation,
                        startOrTop = closeStartOrTop,
                        endOrBottom = closeEndOrBottom,
                    )
                )
            }
            cell.content(cellScope)
        }
    }
}