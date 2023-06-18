package br.com.otaviolms.tabnews.views.user

import br.com.otaviolms.tabnews.models.responses.PostResponseModel

sealed class UserUiState {
    data class Sucesso(val conteudo: ArrayList<PostResponseModel>): UserUiState()
    data class Erro(val mensagem: String): UserUiState()
}
