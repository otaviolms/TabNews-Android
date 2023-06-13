package br.com.otaviolms.tabnews.views.posts

import br.com.otaviolms.tabnews.models.PostResponseModel

sealed class PostsUiState {
    data class Sucesso(val conteudo: ArrayList<PostResponseModel>): PostsUiState()
    data class SucessoNovaPagina(val conteudo: ArrayList<PostResponseModel>): PostsUiState()
    data class Erro(val mensagem: String): PostsUiState()
}
