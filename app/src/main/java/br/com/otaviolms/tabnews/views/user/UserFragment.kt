package br.com.otaviolms.tabnews.views.user

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.otaviolms.tabnews.adapters.ConteudosAdapter
import br.com.otaviolms.tabnews.databinding.FragmentUserBinding
import br.com.otaviolms.tabnews.implementations.bases.BaseFragment
import br.com.otaviolms.tabnews.singletons.Sessao
import br.com.otaviolms.tabnews.implementations.annotations.Binding

@Binding(FragmentUserBinding::class)
class UserFragment: BaseFragment<FragmentUserBinding>() {

    private val vm: UserViewModel by viewModels()

    private val args: UserFragmentArgs by navArgs()

    private val conteudosAdapter by lazy {
        ConteudosAdapter(context = requireContext()) { abrirDeeplink("${it.autor}/${it.slug}") }
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
