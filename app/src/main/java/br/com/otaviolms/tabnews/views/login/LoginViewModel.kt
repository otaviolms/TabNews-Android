package br.com.otaviolms.tabnews.views.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import br.com.otaviolms.tabnews.implementations.bases.BaseViewModel
import br.com.otaviolms.tabnews.singletons.Sessao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository = LoginRepository()
) : BaseViewModel<LoginUiState>() {

    fun realizarLogin(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { repository.realizarLogin(email, password) }
                .onSuccess {
                    Sessao.token = it.token
                    setState(LoginUiState.Sucesso)
                }
                .onFailure {
                    Log.e("LogTabNews", it.message ?: "Ocorreu um erro!")
                    Log.e("LogTabNews", it.stackTraceToString())
                    setState(LoginUiState.Erro(it.message ?: "Erro ao realizar login"))
                }
        }
    }

    fun carregarUsuario() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { repository.carregarUsuario() }
                .onSuccess {
                    Sessao.salvarUsuarioSessao(it)
                    setState(LoginUiState.SucessoUsuario(it))
                }
                .onFailure {
                    Log.e("LogTabNews", it.message ?: "Ocorreu um erro!")
                    Log.e("LogTabNews", it.stackTraceToString())
                    setState(LoginUiState.Erro(it.message ?: "Erro ao carregar usuário"))
                }
        }
    }

}