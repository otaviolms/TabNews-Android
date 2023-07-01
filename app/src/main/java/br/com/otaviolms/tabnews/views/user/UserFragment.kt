package br.com.otaviolms.tabnews.views.user

import androidx.navigation.fragment.navArgs
import br.com.otaviolms.tabnews.adapters.ConteudosAdapter
import br.com.otaviolms.tabnews.databinding.FragmentUserBinding
import br.com.otaviolms.tabnews.implementations.annotations.Binding
import br.com.otaviolms.tabnews.implementations.bases.BaseFragment
import br.com.otaviolms.tabnews.singletons.Sessao
import org.koin.android.ext.android.inject

@Binding(FragmentUserBinding::class)
class UserFragment: BaseFragment<FragmentUserBinding>() {

    private val vm: UserViewModel by inject()

    private val args: UserFragmentArgs by navArgs()

    private val conteudosAdapter by lazy {
        ConteudosAdapter(context = requireContext()) { abrirDeeplink("${it.autor}/${it.slug}") }
    }

    override fun setupHeader() {
        bnd.imvBack.setOnClickListener { voltar() }
        bnd.txvUsername.text = args.username
    }

    override fun setupView() {
        bnd.rcvConteudos.adapter = conteudosAdapter
    }

    override fun setupUiState() {
        vm.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UserUiState.Sucesso -> conteudosAdapter.definirConteudo(uiState.conteudo)
                is UserUiState.Erro -> {}
            }
        }
    }

    override fun setupObservers() {
        Sessao.usuario.observe(viewLifecycleOwner) { setupView() }
    }

    override fun loadData() { vm.listarPostsUsuario(username = args.username) }

}
