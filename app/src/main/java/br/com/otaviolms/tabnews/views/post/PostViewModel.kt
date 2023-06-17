package br.com.otaviolms.tabnews.views.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.otaviolms.tabnews.connections.RetrofitBuilder
import br.com.otaviolms.tabnews.implementations.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository = PostRepository(retrofit = RetrofitBuilder.getInstance())
): BaseViewModel<PostUiState>() {

    fun carregarPost(autor: String, slug: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { repository.carregarPost(autor, slug) }
                .onSuccess { setState(PostUiState.Sucesso(it)) }
                .onFailure {
                    Log.e("LogTabNews", it.message ?: "Ocorreu um erro!")
                    Log.e("LogTabNews", it.stackTraceToString())
                    setState(PostUiState.Erro(it.message ?: "Ocorreu um erro"))
                }
        }
    }

}