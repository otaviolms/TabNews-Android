package br.com.otaviolms.tabnews.extensions

import android.content.Context
import androidx.annotation.DrawableRes
import br.com.otaviolms.tabnews.R

fun Context.pegarDrawable(@DrawableRes drawable: Int) = this.resources.getDrawable(R.drawable.fundo_menu, this.theme)
