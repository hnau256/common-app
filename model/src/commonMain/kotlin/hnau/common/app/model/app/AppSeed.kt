package hnau.common.app.model.app

import hnau.common.app.model.goback.GoBackHandler
import hnau.common.app.model.theme.Hue
import hnau.common.app.model.theme.ThemeBrightness
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.KSerializer

data class AppSeed<M, S>(
    val defaultBrightness: ThemeBrightness? = null,
    val fallbackHue: Hue,
    val skeletonSerializer: KSerializer<S>,
    val createDefaultSkeleton: () -> S,
    val createModel: (CoroutineScope, AppContext, S) -> M,
    val extractGoBackHandler: (M) -> GoBackHandler,
)