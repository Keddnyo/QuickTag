package io.github.keddnyo.quicktag.data.local.repository

import android.content.Context
import io.github.keddnyo.quicktag.R

class BoardRuleLocalRepository(private val context: Context) {
    fun getInitialBoardRules(): List<String> {
        val res = context.resources.openRawResource(R.raw.board_rules)
        val text = res.bufferedReader().readText()
        return text.split("\n\r\n")
    }
}