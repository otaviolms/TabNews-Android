package br.com.otaviolms.tabnews.implementations

import android.content.Context
import android.content.SharedPreferences

object PreferencesController {

    // Função para salvar uma String nas SharedPreferences
    fun salvarString(context: Context, chave: String, valor: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(chave, valor).apply()
    }

    // Função para ler uma String das SharedPreferences
    fun obterString(context: Context, chave: String, valorPadrao: String = ""): String {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString(chave, valorPadrao) ?: valorPadrao
    }

}
