package hnau.common.app.model.utils

import hnau.common.kotlin.mapper.Mapper
import hnau.common.kotlin.serialization.MappingKSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlin.jvm.JvmInline
import kotlin.math.absoluteValue

@Serializable(Hue.Serializer::class)
@JvmInline
value class Hue(
    val degrees: Int,
) {

    object Serializer : MappingKSerializer<Int, Hue>(
        base = Int.serializer(),
        mapper = intMapper,
    )

    companion object {

        val intMapper: Mapper<Int, Hue> =
            Mapper(::Hue, Hue::degrees)

        fun calcDefault(
            hash: Int,
        ): Hue = Hue(
            degrees = (hash % 360).absoluteValue,
        )
    }
}