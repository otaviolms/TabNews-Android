package br.com.otaviolms.tabnews.views

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import br.com.otaviolms.tabnews.R
import br.com.otaviolms.tabnews.databinding.ActivityMainBinding
import br.com.otaviolms.tabnews.databinding.FragmentPostsBinding
import br.com.otaviolms.tabnews.extensions.colocarUnderline
import br.com.otaviolms.tabnews.extensions.removerUnderline
import br.com.otaviolms.tabnews.implementations.Sessao
import br.com.otaviolms.tabnews.views.login.LoginUiState
import br.com.otaviolms.tabnews.views.login.LoginViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var bnd: ActivityMainBinding

    private val vm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bnd = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bnd.root)
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
            when(uiState) {
                is LoginUiState.Sucesso -> { vm.carregarUsuario() }
                is LoginUiState.SucessoUsuario -> {

                }
                else -> {}
            }
        }
    }

}