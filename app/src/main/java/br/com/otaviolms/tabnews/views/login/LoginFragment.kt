package br.com.otaviolms.tabnews.views.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import br.com.otaviolms.tabnews.databinding.FragmentLoginBinding
import br.com.otaviolms.tabnews.implementations.bases.BaseFragment
import br.com.otaviolms.tabnews.singletons.Sessao
import br.com.otaviolms.tabnews.implementations.annotations.Binding

@Binding(FragmentLoginBinding::class)
class LoginFragment: BaseFragment<FragmentLoginBinding>() {

    private val vm: LoginViewModel by viewModels()

    private val emailDigitado: String
        get() = bnd.edtEmail.text.toString()
    private val senhaDigitada: String
        get() = bnd.edtSenha.text.toString()

    override fun setupListeners() {
        bnd.txvCriarConta.setOnClickListener { Toast.makeText(requireContext(), "Direcionar para criar conta", Toast.LENGTH_SHORT).show() }
        bnd.txvCliqueAqui.setOnClickListener { Toast.makeText(requireContext(), "Direcionar para recuperar senha", Toast.LENGTH_SHORT).show() }

        bnd.button.setOnClickListener {
            vm.realizarLogin(
                email = emailDigitado,
                password = senhaDigitada
            )
        }
    }

    override fun setupUiState() {
        vm.uiState.observe(viewLifecycleOwner) { uiState ->
            when(uiState) {
                is LoginUiState.Sucesso -> {
                    Sessao.salvarUsuario(requireContext(), email = emailDigitado, senha = senhaDigitada)
                    vm.carregarUsuario()
                }
                is LoginUiState.SucessoUsuario -> voltar()
                is LoginUiState.Erro -> Toast.makeText(requireContext(), uiState.mensagem, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
