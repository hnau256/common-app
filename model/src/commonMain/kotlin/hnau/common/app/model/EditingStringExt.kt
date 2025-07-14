package hnau.common.app.model

fun String.toEditingString(): EditingString =
    EditingString(text = this)
