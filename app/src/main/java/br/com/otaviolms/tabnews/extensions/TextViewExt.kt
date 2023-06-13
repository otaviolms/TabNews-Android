package br.com.otaviolms.tabnews.extensions

import android.widget.TextView

fun TextView.colocarUnderline() {
    this.paint.isUnderlineText = true
}

fun TextView.removerUnderline() {
    this.paint.isUnderlineText = false
}