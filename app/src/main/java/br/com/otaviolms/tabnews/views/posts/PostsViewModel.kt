package br.com.otaviolms.tabnews.views.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.otaviolms.tabnews.connections.RetrofitBuilder
import br.com.otaviolms.tabnews.enums.StrategyEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostsViewModel(
    private val repository: PostsRepository = PostsRepository(retrofit = RetrofitBuilder.getInstance())
): ViewModel() {

    private val _uiState: MutableLiveData<PostsUiState> = MutableLiveData()
    val uiState: LiveData<PostsUiState> = _uiState

    private fun setState(state: PostsUiState) { viewModelScope.launch { _uiState.postValue(state) } }

    fun listarPosts(page: Int, perPage: Int = 10, strategy: StrategyEnum = StrategyEnum.NOVOS, novaPagina: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { repository.listarConteudos(page, perPage, strategy) }
                .onSuccess {
                    if(novaPagina) setState(PostsUiState.SucessoNovaPagina(it))
                    else setState(PostsUiState.Sucesso(it))
                }
                .onFailure {
                    Log.e("Conteudos", it.message ?: "Ocorreu um erro!")
                    Log.e("Conteudos", it.stackTraceToString())
                    setState(PostsUiState.Erro(it.message ?: "Ocorreu um erro"))
                }
        }
    }

}