package br.com.otaviolms.tabnews.views

import android.os.Bundle
import android.util.Log
import br.com.otaviolms.tabnews.databinding.ActivityMainBinding
import br.com.otaviolms.tabnews.implementations.annotations.Binding
import br.com.otaviolms.tabnews.implementations.bases.BaseActivity
import br.com.otaviolms.tabnews.singletons.Sessao
import br.com.otaviolms.tabnews.views.login.LoginUiState
import br.com.otaviolms.tabnews.views.login.LoginViewModel
import org.koin.android.ext.android.inject

@Binding(ActivityMainBinding::class)
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val vm: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUiState()
        tentarRecuperarCredenciais()
    }

    private fun tentarRecuperarCredenciais() {
        runCatching { Sessao.recuperarUsuario(this) }
            .onSuccess { vm.realizarLogin(email = it.first, password = it.second) }
            .onFailure { Log.e("LogTabNews", "Falha ao recuperar as credenciais") }
    }

    private fun setupUiState() {
        vm.uiState.observe(this) { uiState ->
            when (uiState) {
                is LoginUiState.Sucesso -> {
                    vm.carregarUsuario()
                }

                is LoginUiState.SucessoUsuario -> {}
                else -> {}
            }
        }
    }

}