package br.com.otaviolms.tabnews.views.post

import android.util.Log
import androidx.lifecycle.viewModelScope
import br.com.otaviolms.tabnews.connections.RetrofitBuilder
import br.com.otaviolms.tabnews.extensions.calcularNiveis
import br.com.otaviolms.tabnews.implementations.bases.BaseViewModel
import br.com.otaviolms.tabnews.models.responses.PostResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository = PostRepository(retrofit = RetrofitBuilder.getInstance())
): BaseViewModel<PostUiState>() {

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