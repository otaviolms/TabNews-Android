package br.com.otaviolms.tabnews.singletons

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.otaviolms.tabnews.implementations.PreferencesController
import br.com.otaviolms.tabnews.implementations.criptografia.CryptHelper
import br.com.otaviolms.tabnews.models.responses.UsuarioResponseModel

object Sessao {
    private var mToken: String? = null
    var token: String
        set(value) { mToken = value }
        get() = mToken ?: ""

    val sessionId: String
        get() = "session_id=$mToken"

    private var mutableUsuario: MutableLiveData<UsuarioResponseModel?> = MutableLiveData()
    val usuario: LiveData<UsuarioResponseModel?> = mutableUsuario

    fun salvarUsuarioSessao(usuario: UsuarioResponseModel) {
        mutableUsuario.postValue(usuario)
    }

    fun salvarUsuario(context: Context, email: String, senha: String) {
        val emailCriptografado = CryptHelper.encrypt(email)
        PreferencesController.salvarString(context, "email", emailCriptografado.first)
        PreferencesController.salvarString(context, "emailIV", emailCriptografado.second)
        val senhaCriptografada = CryptHelper.encrypt(senha)
        PreferencesController.salvarString(context, "senha", senhaCriptografada.first)
        PreferencesController.salvarString(context, "senhaIV", senhaCriptografada.second)
    }

    fun recuperarUsuario(context: Context): Pair<String, String> {
        var email = ""
        var senha = ""

        val emailIV = PreferencesController.obterString(context, "emailIV")
        val emailCriptografado = PreferencesController.obterString(context, "email")

        val senhaIV = PreferencesController.obterString(context, "senhaIV")
        val senhaCriptografada = PreferencesController.obterString(context, "senha")

        if(emailIV.isNotEmpty() || emailCriptografado.isNotEmpty() || senhaIV.isNotEmpty() || senhaCriptografada.isNotEmpty()) {
            email = CryptHelper.decrypt(emailCriptografado, emailIV)
            senha = CryptHelper.decrypt(senhaCriptografada, senhaIV)
        }
        return email to senha
    }
}
