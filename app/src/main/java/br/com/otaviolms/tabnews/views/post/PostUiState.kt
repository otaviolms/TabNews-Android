package br.com.otaviolms.tabnews.views.post

import br.com.otaviolms.tabnews.models.responses.PostResponseModel

sealed class PostUiState {
    data class Sucesso(val post: PostResponseModel): PostUiState()
    data class SucessoComentarios(val comentarios: ArrayList<PostResponseModel>): PostUiState()
    data class Erro(val mensagem: String): PostUiState()
    data class ErroComentarios(val mensagem: String): PostUiState()
}
