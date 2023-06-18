package br.com.otaviolms.tabnews.views.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.otaviolms.tabnews.adapters.ConteudosAdapter
import br.com.otaviolms.tabnews.databinding.FragmentUserBinding
import br.com.otaviolms.tabnews.extensions.makeGone
import br.com.otaviolms.tabnews.implementations.BaseFragment
import br.com.otaviolms.tabnews.implementations.Sessao


class UserFragment: BaseFragment<FragmentUserBinding>() {

    private val vm: UserViewModel by viewModels()

    private val args: UserFragmentArgs by navArgs()

    private val conteudosAdapter by lazy {
        ConteudosAdapter(context = requireContext()) { abrirDeeplink("${it.autor}/${it.slug}") }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return bnd.root
    }

    override fun setupHeader() {
        bnd.imvLogoApp.setOnClickListener { voltar() }
        bnd.txvUsername.text = args.username
    }

    override fun setupView() {
        bnd.rcvConteudos.adapter = conteudosAdapter
    }

    override fun setupListeners() {
    }

    override fun setupUiState() {
        vm.uiState.observe(viewLifecycleOwner) { uiState ->
            when(uiState) {
                is UserUiState.Sucesso -> {
                    conteudosAdapter.definirConteudo(uiState.conteudo)
                }
                is UserUiState.Erro -> {
                }
            }
        }
    }

    override fun setupObservers() {
        Sessao.usuario.observe(viewLifecycleOwner) { setupView() }
    }

    override fun loadData() { vm.listarPostsUsuario(username = args.username) }

}
