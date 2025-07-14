package hnau.common.app.model.app.utils

import arrow.core.getOrElse
import arrow.core.toOption
import hnau.common.kotlin.mapper.Mapper
import hnau.common.kotlin.mapper.nameToEnum
import hnau.common.kotlin.mapper.nullable
import hnau.common.kotlin.mapper.stringToBoolean
import hnau.common.app.model.ThemeBrightness
import hnau.common.app.model.app.AppContext
import hnau.common.app.model.color.material.MaterialHue
import hnau.common.app.model.file.File
import hnau.common.app.model.file.plus
import hnau.common.app.model.preferences.impl.FileBasedPreferences
import hnau.common.app.model.preferences.map
import hnau.common.app.model.preferences.mapOption
import hnau.common.app.model.preferences.withDefault
import kotlinx.coroutines.CoroutineScope

internal suspend fun AppContext(
    scope: CoroutineScope,
    defaultBrightness: ThemeBrightness?,
    defaultTryUseSystemHue: Boolean,
    fallbackHue: MaterialHue,
    filesDir: File,
): AppContext {
    val preferences = FileBasedPreferences
        .Factory(
            preferencesFile = filesDir + "common_preferences.txt"
        )
        .createPreferences(
            scope = scope,
        )
    return AppContext(
        brightness = preferences["brightness"]
            .mapOption(
                scope = scope,
                mapper = Mapper
                    .nameToEnum<ThemeBrightness>()
                    .nullable
                    .let { mapper ->
                        Mapper(
                            direct = { nameOrNone ->
                                nameOrNone
                                    .map(mapper.direct)
                                    .getOrElse { defaultBrightness }
                            },
                            reverse = { brightnessOrNull ->
                                brightnessOrNull
                                    ?.let(mapper.reverse)
                                    .toOption()
                            },
                        )
                    },
            ),
        tryUseSystemHue = preferences["try_use_system_hue"]
            .map(
                scope = scope,
                mapper = Mapper.stringToBoolean,
            )
            .withDefault(
                scope = scope
            ) { defaultTryUseSystemHue },
        fallbackHue = preferences["fallback_hue"]
            .map(
                scope = scope,
                mapper = Mapper
                    .nameToEnum<MaterialHue>(),
            )
            .withDefault(
                scope = scope
            ) { fallbackHue },
        filesDir = filesDir,
    )
}