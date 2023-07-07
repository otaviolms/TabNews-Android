package br.com.otaviolms.tabnews.views.post

import br.com.otaviolms.tabnews.enums.TipoVoteEnum
import br.com.otaviolms.tabnews.models.responses.PostResponseModel

sealed class PostUiState {
    data class Sucesso(val conteudos: ArrayList<PostResponseModel>) : PostUiState()
    data class SucessoVote(val tabcoins: Int, val posicao: Int, val tipoVote: TipoVoteEnum) :
        PostUiState()
    object Erro: PostUiState()
    data class ErroVote(val mensagem: String) : PostUiState()
}
