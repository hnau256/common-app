package hnau.common.app.model.app

import hnau.common.app.model.ThemeBrightness
import hnau.common.app.model.color.material.MaterialHue
import hnau.common.app.model.goback.GoBackHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.KSerializer

data class AppSeed<M, S>(
    val defaultBrightness: ThemeBrightness? = null,
    val fallbackHue: MaterialHue,
    val skeletonSerializer: KSerializer<S>,
    val createDefaultSkeleton: () -> S,
    val createModel: (CoroutineScope, AppContext, S) -> M,
    val extractGoBackHandler: (M) -> GoBackHandler,
)