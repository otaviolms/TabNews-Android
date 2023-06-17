package br.com.otaviolms.tabnews.views.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.otaviolms.tabnews.R
import br.com.otaviolms.tabnews.adapters.ConteudosAdapter
import br.com.otaviolms.tabnews.databinding.FragmentPostsBinding
import br.com.otaviolms.tabnews.enums.StrategyEnum
import br.com.otaviolms.tabnews.extensions.makeGone
import br.com.otaviolms.tabnews.extensions.makeVisible
import br.com.otaviolms.tabnews.extensions.pegarDrawable
import br.com.otaviolms.tabnews.extensions.removerFundo
import br.com.otaviolms.tabnews.implementations.BaseFragment
import br.com.otaviolms.tabnews.implementations.CryptHelper
import br.com.otaviolms.tabnews.implementations.Sessao
import br.com.otaviolms.tabnews.models.responses.UsuarioResponseModel
import com.github.javafaker.Crypto

class PostsFragment: BaseFragment<FragmentPostsBinding>() {

    private val vm: PostsViewModel by viewModels()

    private val conteudosAdapter by lazy {
        ConteudosAdapter(context = requireContext()) {
//            findNavController().navigate(PostsFragmentDirections.abrirPost(it.autor, it.slug))
            abrirDeeplink("${it.autor}/${it.slug}")
        }
    }

    private val args: PostsFragmentArgs by navArgs()

    private val strategy by lazy { if(args.strategy == "recentes") StrategyEnum.RECENTES else StrategyEnum.RELEVANTES }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return bnd.root
    }

    override fun setupHeader() {
        bnd.imvLogoApp.setOnClickListener { abrirDeeplink() }
        bnd.txvRelevantes.setOnClickListener { abrirDeeplink() }
        bnd.txvRecentes.setOnClickListener { abrirDeeplink("recentes") }
        usuario?.let {
            bnd.llTabinfos.makeVisible()
            bnd.txvEntrar.makeGone()
            bnd.txvTabcash.text = it.tabcash.toString()
            bnd.txvTabcoins.text = it.tabcoins.toString()
        } ?: esconderContaToolbar()
    }

    private fun esconderContaToolbar() {
        bnd.llTabinfos.makeGone()
        bnd.txvEntrar.setOnClickListener { abrirDeeplink("login") }
    }

    override fun setupView() {
        if(strategy == StrategyEnum.RECENTES) definirAbaRecentes() else definirAbaRelevantes()
        bnd.rcvConteudos.adapter = conteudosAdapter
    }

    override fun setupListeners() {
        bnd.srlConteudos.setOnRefreshListener { this.loadData() }
//        bnd.rcvConteudos.addOnScrollListener(
//            PaginationListener(layoutManager = linearLayoutManager) { pagina ->
//                vm.listarPosts(page = pagina + 1, novaPagina = true)
//            }
//        )
    }

    override fun setupUiState() {
        vm.uiState.observe(viewLifecycleOwner) { uiState ->
            when(uiState) {
                is PostsUiState.Sucesso -> {
                    bnd.txvFalha.makeGone()
                    bnd.srlConteudos.isRefreshing = false
                    conteudosAdapter.definirConteudo(uiState.conteudo)
                }
                is PostsUiState.SucessoNovaPagina -> {
                    bnd.txvFalha.makeGone()
                    bnd.srlConteudos.isRefreshing = false
                    conteudosAdapter.adicionarConteudo(uiState.conteudo)
                }
                is PostsUiState.Erro -> {
                    bnd.srlConteudos.makeGone()
                    bnd.txvFalha.makeVisible()
                }
            }
        }
    }

    override fun setupObservers() {
        Sessao.usuario.observe(viewLifecycleOwner) { setupHeader() }
    }

    private fun definirAbaRelevantes() {
        bnd.txvRelevantes.background = requireContext().pegarDrawable(R.drawable.fundo_menu)
        bnd.txvRecentes.removerFundo()
    }

    private fun definirAbaRecentes() {
        bnd.txvRecentes.background = requireContext().pegarDrawable(R.drawable.fundo_menu)
        bnd.txvRelevantes.removerFundo()
    }

    override fun setupBack() { requireActivity().finish() }

    override fun loadData() { vm.listarPosts(page = 1, perPage = PER_PAGE, strategy = strategy) }

    companion object {
        const val PER_PAGE = 20
    }

}