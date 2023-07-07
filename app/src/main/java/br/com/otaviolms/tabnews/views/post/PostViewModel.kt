package br.com.otaviolms.tabnews.views.post

import androidx.lifecycle.viewModelScope
import br.com.otaviolms.tabnews.enums.TipoVoteEnum
import br.com.otaviolms.tabnews.extensions.calcularNiveis
import br.com.otaviolms.tabnews.implementations.bases.BaseViewModel
import br.com.otaviolms.tabnews.models.responses.PostResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository = PostRepository()
): BaseViewModel<PostUiState>() {

    fun upDownVote(autor: String, slug: String, tipo: TipoVoteEnum, posicao: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { repository.upDownVote(autor, slug, tipo) }
                .onSuccess { setState(PostUiState.SucessoVote(it.tabcoins, posicao, tipo)) }
                .onFailure { setState(PostUiState.ErroVote(it.message ?: "Falha!")) }
        }
    }

    fun carregarPost(autor: String, slug: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { repository.carregarPost(autor, slug) }
                .onSuccess { carregarComentarios(it, autor, slug) }
                .onFailure { setState(PostUiState.Erro) }
        }
    }

    private fun carregarComentarios(post: PostResponseModel, autor: String, slug: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { repository.carregarComentarios(autor, slug) }
                .onSuccess {
                    with(arrayListOf(post)) {
                        addAll(it)
                        calcularNiveis()
                        setState(PostUiState.Sucesso(this))
                    }
                }
                .onFailure { setState(PostUiState.Erro) }
        }
    }

}