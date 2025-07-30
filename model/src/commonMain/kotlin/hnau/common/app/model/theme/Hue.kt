package hnau.common.app.model.theme

import hnau.common.kotlin.mapper.Mapper
import hnau.common.kotlin.serialization.MappingKSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline

@Serializable(Hue.Serializer::class)
@JvmInline
value class Hue(
    val degrees: Double,
) {

    object Serializer: MappingKSerializer<Double, Hue>(
        base = Double.serializer(),
        mapper = doubleMapper,
    )

    companion object {

        val doubleMapper: Mapper<Double, Hue> =
            Mapper(::Hue, Hue::degrees)
    }
}