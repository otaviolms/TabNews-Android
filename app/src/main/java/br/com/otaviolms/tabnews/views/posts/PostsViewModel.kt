package br.com.otaviolms.tabnews.views.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.otaviolms.tabnews.connections.RetrofitBuilder
import br.com.otaviolms.tabnews.enums.StrategyEnum
import br.com.otaviolms.tabnews.implementations.BaseViewModel
import br.com.otaviolms.tabnews.views.post.PostUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostsViewModel(
    private val repository: PostsRepository = PostsRepository(retrofit = RetrofitBuilder.getInstance())
): BaseViewModel<PostsUiState>() {

    fun listarPosts(page: Int, perPage: Int = 10, strategy: StrategyEnum = StrategyEnum.RECENTES, novaPagina: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { repository.listarConteudos(page, perPage, strategy) }
                .onSuccess {
                    if(novaPagina) setState(PostsUiState.SucessoNovaPagina(it))
                    else setState(PostsUiState.Sucesso(it))
                }
                .onFailure {
                    Log.e("LogTabNews", it.message ?: "Ocorreu um erro!")
                    Log.e("LogTabNews", it.stackTraceToString())
                    setState(PostsUiState.Erro(it.message ?: "Ocorreu um erro"))
                }
        }
    }

}