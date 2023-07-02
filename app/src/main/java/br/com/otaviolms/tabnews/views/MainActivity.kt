package br.com.otaviolms.tabnews.views

import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navDeepLink
import br.com.otaviolms.tabnews.databinding.ActivityMainBinding
import br.com.otaviolms.tabnews.implementations.annotations.Binding
import br.com.otaviolms.tabnews.implementations.bases.BaseActivity
import br.com.otaviolms.tabnews.implementations.bases.BaseFragment
import br.com.otaviolms.tabnews.singletons.Sessao
import br.com.otaviolms.tabnews.views.login.LoginUiState
import br.com.otaviolms.tabnews.views.login.LoginViewModel
import org.koin.android.ext.android.inject

@Binding(ActivityMainBinding::class)
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val vm: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verificarDirecionamento()
        setupUiState()
        tentarRecuperarCredenciais()
    }

    private fun verificarDirecionamento() {
        intent.getStringExtra("path")?.let {
            if(it.isEmpty()) return
            val navHostFragment = supportFragmentManager.findFragmentById(bnd.fragmentContainerView.id) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(NavDeepLinkRequest.Builder.fromUri("${BaseFragment.BASE_URL}$it".toUri()).build())
        }
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