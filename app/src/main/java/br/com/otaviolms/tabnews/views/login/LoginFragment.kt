package br.com.otaviolms.tabnews.views.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.otaviolms.tabnews.R
import br.com.otaviolms.tabnews.connections.RetrofitBuilder
import br.com.otaviolms.tabnews.databinding.FragmentLoginBinding
import br.com.otaviolms.tabnews.databinding.FragmentPostBinding
import br.com.otaviolms.tabnews.implementations.BaseFragment
import br.com.otaviolms.tabnews.implementations.CryptHelper
import br.com.otaviolms.tabnews.implementations.Sessao
import br.com.otaviolms.tabnews.utils.calcularHorasPassadas
import br.com.otaviolms.tabnews.views.post.PostUiState
import br.com.otaviolms.tabnews.views.post.PostViewModel
import retrofit2.Retrofit

class LoginFragment: BaseFragment<FragmentLoginBinding>() {

    private val vm: LoginViewModel by viewModels()

    private val emailDigitado: String
        get() = bnd.edtEmail.text.toString()
    private val senhaDigitada: String
        get() = bnd.edtSenha.text.toString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return bnd.root
    }

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
