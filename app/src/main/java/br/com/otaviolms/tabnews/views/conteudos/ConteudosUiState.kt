package br.com.otaviolms.tabnews.views.conteudos

import br.com.otaviolms.tabnews.models.ItemConteudoResponseModel

sealed class ConteudosUiState {
    data class Sucesso(val conteudo: ArrayList<ItemConteudoResponseModel>): ConteudosUiState()
    data class SucessoNovaPagina(val conteudo: ArrayList<ItemConteudoResponseModel>): ConteudosUiState()
    data class Erro(val mensagem: String): ConteudosUiState()
}
