package br.com.otaviolms.tabnews.views.login

import br.com.otaviolms.tabnews.models.responses.LoginResponseModel
import br.com.otaviolms.tabnews.models.responses.UsuarioResponseModel

sealed class LoginUiState {
    object Sucesso: LoginUiState()
    data class SucessoUsuario(val usuario: UsuarioResponseModel): LoginUiState()
    data class Erro(val mensagem: String): LoginUiState()
}
