package hnau.common.app.projector.utils.theme

import dynamiccolor.ColorSpec
import dynamiccolor.ColorSpecs
import dynamiccolor.DynamicScheme
import dynamiccolor.Variant
import hct.Hct
import hnau.common.app.model.theme.Hue
import hnau.common.app.model.theme.ThemeBrightness

fun DynamicScheme(
    primaryHue: Hue,
    brightness: ThemeBrightness,
    contrastLevel: Double = 1.0,
    variant: Variant = Variant.VIBRANT,
): DynamicScheme {

    val tone = when (brightness) {
        ThemeBrightness.Light -> 40.0
        ThemeBrightness.Dark -> 80.0
    }
    val primary = Hct.from(
        /* hue = */ primaryHue.hue * 360.0,
        /* chroma = */ 48.0,
        /* tone = */ tone,
    )

    val isDark = when (brightness) {
        ThemeBrightness.Light -> false
        ThemeBrightness.Dark -> true
    }

    val specVersion = ColorSpec.SpecVersion.SPEC_2025
    val platform = DynamicScheme.Platform.PHONE
    return DynamicScheme(
        /* sourceColorHct = */ primary,
        /* variant = */ variant,
        /* isDark = */ isDark,
        /* contrastLevel = */ contrastLevel,
        /* platform = */ platform,
        /* specVersion = */ specVersion,
        /* primaryPalette = */ ColorSpecs.get(specVersion)
            .getPrimaryPalette(variant, primary, isDark, platform, contrastLevel),
        /* secondaryPalette = */ ColorSpecs.get(specVersion)
            .getSecondaryPalette(variant, primary, isDark, platform, contrastLevel),
        /* tertiaryPalette = */ ColorSpecs.get(specVersion)
            .getTertiaryPalette(variant, primary, isDark, platform, contrastLevel),
        /* neutralPalette = */ ColorSpecs.get(specVersion)
            .getNeutralPalette(variant, primary, isDark, platform, contrastLevel),
        /* neutralVariantPalette = */ ColorSpecs.get(specVersion)
            .getNeutralVariantPalette(variant, primary, isDark, platform, contrastLevel),
        /* errorPalette = */ ColorSpecs.get(specVersion)
            .getErrorPalette(variant, primary, isDark, platform, contrastLevel)
    )
}