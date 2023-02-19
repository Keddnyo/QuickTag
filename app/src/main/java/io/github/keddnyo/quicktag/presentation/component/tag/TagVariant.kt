package io.github.keddnyo.quicktag.presentation.component.tag

sealed class TagVariant(val symbol: String, val color: TagColor, val tagWrapper: String) {
    object CUR : TagVariant(symbol = "К", color = TagColor.CUR, tagWrapper = "cur")
    object MOD : TagVariant(symbol = "М", color = TagColor.MOD, tagWrapper = "mod")
    object EX : TagVariant(symbol = "!", color = TagColor.EX, tagWrapper = "ex")
}