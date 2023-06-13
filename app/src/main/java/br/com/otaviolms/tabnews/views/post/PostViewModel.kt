package br.com.otaviolms.tabnews.views.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.otaviolms.tabnews.connections.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository = PostRepository(retrofit = RetrofitBuilder.getInstance())
): ViewModel() {

    private val _uiState: MutableLiveData<PostUiState> = MutableLiveData()
    val uiState: LiveData<PostUiState> = _uiState

    private fun setState(state: PostUiState) { viewModelScope.launch { _uiState.postValue(state) } }

    fun carregarPost(autor: String, slug: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { repository.carregarPost(autor, slug) }
                .onSuccess { setState(PostUiState.Sucesso(it)) }
                .onFailure {
                    Log.e("Conteudos", it.message ?: "Ocorreu um erro!")
                    Log.e("Conteudos", it.stackTraceToString())
                    setState(PostUiState.Erro(it.message ?: "Ocorreu um erro"))
                }
        }
    }

}