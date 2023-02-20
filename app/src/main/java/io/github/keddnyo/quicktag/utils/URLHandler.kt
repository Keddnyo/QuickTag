package io.github.keddnyo.quicktag.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import io.github.keddnyo.quicktag.common.Constants.FOUR_PDA_FORUM_URL
import io.github.keddnyo.quicktag.common.Constants.FOUR_PDA_PACKAGE_NAME

fun Context.openWebPage(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}

fun Context.openFourPDA() {
    try {
        val pm = packageManager
        val packageName = FOUR_PDA_PACKAGE_NAME
        val intent = pm.getLaunchIntentForPackage(packageName)
        startActivity(intent)
    } catch (e: Exception) {
        val url = FOUR_PDA_FORUM_URL
        openWebPage(url)
    }
}