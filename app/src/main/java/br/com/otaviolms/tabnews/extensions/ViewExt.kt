package br.com.otaviolms.tabnews.extensions

import android.view.View

fun View.makeVisible() { this.visibility = View.VISIBLE }
fun View.makeGone() { this.visibility = View.GONE }
fun View.makeInvisible() { this.visibility = View.INVISIBLE }

fun View.removerFundo() { this.background = null }