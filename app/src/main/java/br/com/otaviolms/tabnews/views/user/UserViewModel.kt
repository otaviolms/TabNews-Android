package br.com.otaviolms.tabnews.views.user

import android.util.Log
import androidx.lifecycle.viewModelScope
import br.com.otaviolms.tabnews.implementations.bases.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository = UserRepository()
): BaseViewModel<UserUiState>() {

    fun listarPostsUsuario(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { repository.listarPostsUsuario(username) }
                .onSuccess { setState(UserUiState.Sucesso(it)) }
                .onFailure {
                    Log.e("LogTabNews", it.message ?: "Ocorreu um erro!")
                    Log.e("LogTabNews", it.stackTraceToString())
                    setState(UserUiState.Erro(it.message ?: "Ocorreu um erro"))
                }
        }
    }

}