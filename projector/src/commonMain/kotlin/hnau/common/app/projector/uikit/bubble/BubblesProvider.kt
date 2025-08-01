package hnau.common.app.projector.uikit.bubble

import kotlinx.coroutines.flow.StateFlow

interface BubblesProvider {

    val visibleBubble: StateFlow<Bubble?>
}
