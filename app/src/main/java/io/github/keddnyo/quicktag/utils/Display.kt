package io.github.keddnyo.quicktag.utils

import android.content.Context
import android.widget.Toast

class Display {
    fun showToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}