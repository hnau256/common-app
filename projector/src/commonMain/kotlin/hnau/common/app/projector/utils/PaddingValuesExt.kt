package hnau.common.app.projector.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.takeOrElse

@Composable
operator fun PaddingValues.plus(
    other: PaddingValues,
): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        start = calculateStartPadding(layoutDirection) + other.calculateStartPadding(layoutDirection),
        top = calculateTopPadding() + other.calculateTopPadding(),
        end = calculateEndPadding(layoutDirection) + other.calculateEndPadding(layoutDirection),
        bottom = calculateBottomPadding() + other.calculateBottomPadding(),
    )
}

@Composable
inline fun PaddingValues.map(
    start: (Dp) -> Dp = { Dp.Unspecified },
    top: (Dp) -> Dp = { Dp.Unspecified },
    end: (Dp) -> Dp = { Dp.Unspecified },
    bottom: (Dp) -> Dp = { Dp.Unspecified },
): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        start = calculateStartPadding(layoutDirection).let { startValue ->
            start(startValue).takeOrElse { startValue }
        },
        top = calculateTopPadding().let { topValue ->
            top(topValue).takeOrElse { topValue }
        },
        end = calculateEndPadding(layoutDirection).let { endValue ->
            end(endValue).takeOrElse { endValue }
        },
        bottom = calculateBottomPadding().let { bottomValue ->
            bottom(bottomValue).takeOrElse { bottomValue }
        },
    )
}

@Composable
fun PaddingValues.copy(
    start: Dp = Dp.Unspecified,
    top: Dp = Dp.Unspecified,
    end: Dp = Dp.Unspecified,
    bottom: Dp = Dp.Unspecified,
): PaddingValues = map(
    start = { current -> start.takeOrElse { current } },
    top = { current -> top.takeOrElse { current } },
    end = { current -> end.takeOrElse { current } },
    bottom = { current -> bottom.takeOrElse { current } },
)