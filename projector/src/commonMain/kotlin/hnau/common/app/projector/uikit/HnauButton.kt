package hnau.common.app.projector.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import hnau.common.app.projector.uikit.shape.HnauShape
import hnau.common.app.projector.uikit.utils.Dimens
import hnau.common.app.projector.utils.clickableOption

@Composable
fun HnauButton(
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    style: ContainerStyle = ContainerStyle.neutral.rememberContainerStyle(),
    shape: Shape = HnauShape(),
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(style.container)
            .clickableOption(onClick)
            .padding(horizontal = Dimens.separation)
            .heightIn(min = 48.dp),
        contentAlignment = Alignment.Center,
    ) {
        val alpha = when (onClick) {
            null -> 0.75f
            else -> 1f
        }
        val contentColor = style.content.copy(alpha = alpha)
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
        ) {
            content()
        }
    }
}

