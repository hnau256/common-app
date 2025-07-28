package hnau.common.app.model.theme

import hnau.common.kotlin.mapper.Mapper
import hnau.common.kotlin.serialization.MappingKSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline

@Serializable(Hue.Serializer::class)
@JvmInline
value class Hue(
    val hue: Float,
) {

    object Serializer: MappingKSerializer<Float, Hue>(
        base = Float.serializer(),
        mapper = floatMapper,
    )

    companion object {

        val floatMapper: Mapper<Float, Hue> =
            Mapper(::Hue, Hue::hue)
    }
}