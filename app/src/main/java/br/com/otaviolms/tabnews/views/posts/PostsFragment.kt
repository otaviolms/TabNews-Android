package br.com.otaviolms.tabnews.views.posts

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.otaviolms.tabnews.R
import br.com.otaviolms.tabnews.adapters.ConteudosAdapter
import br.com.otaviolms.tabnews.databinding.FragmentPostsBinding
import br.com.otaviolms.tabnews.enums.StrategyEnum
import br.com.otaviolms.tabnews.extensions.makeGone
import br.com.otaviolms.tabnews.extensions.makeVisible
import br.com.otaviolms.tabnews.extensions.pegarDrawable
import br.com.otaviolms.tabnews.extensions.removerFundo
import br.com.otaviolms.tabnews.implementations.bases.BaseFragment
import br.com.otaviolms.tabnews.implementations.listeners.PaginationListener
import br.com.otaviolms.tabnews.singletons.Sessao
import br.com.otaviolms.tabnews.implementations.annotations.Binding

@Binding(FragmentPostsBinding::class)
class PostsFragment: BaseFragment<FragmentPostsBinding>() {

    private val vm: PostsViewModel by viewModels()

    private val args: PostsFragmentArgs by navArgs()
    private val strategy by lazy { if(args.strategy == "recentes") StrategyEnum.RECENTES else StrategyEnum.RELEVANTES }

    private val paginationListener = PaginationListener { pagina ->
        vm.listarPosts(page = pagina, perPage = PER_PAGE, strategy = strategy, novaPagina = true)
    }

    private val conteudosAdapter by lazy {
        ConteudosAdapter(context = requireContext()) { abrirDeeplink("${it.autor}/${it.slug}") }
    }

    override fun setupHeader() {
        bnd.imvLogoApp.setOnClickListener { abrirDeeplink() }
        bnd.txvRelevantes.setOnClickListener { abrirDeeplink() }
        bnd.txvRecentes.setOnClickListener { abrirDeeplink("?strategy=recentes") }
        usuario?.let {
            bnd.llTabinfos.makeVisible()
            bnd.imvAvatar.makeVisible()
            bnd.txvEntrar.makeGone()
            bnd.txvTabcash.text = it.tabcash.toString()
            bnd.txvTabcoins.text = it.tabcoins.toString()
        } ?: esconderContaToolbar()
    }

    private fun esconderContaToolbar() {
        bnd.llTabinfos.makeGone()
        bnd.imvAvatar.makeGone()
        bnd.txvEntrar.makeVisible()
        bnd.txvEntrar.setOnClickListener { abrirDeeplink("login") }
    }

    override fun setupView() {
        if(strategy == StrategyEnum.RECENTES) definirAbaRecentes() else definirAbaRelevantes()
        bnd.rcvConteudos.adapter = conteudosAdapter
    }

    override fun setupListeners() {
        bnd.srlConteudos.setOnRefreshListener { this.loadData() }
        bnd.rcvConteudos.addOnScrollListener(paginationListener)
        bnd.imvAvatar.setOnClickListener {
            usuario?.username?.let { username -> abrirDeeplink(username) }
        }
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
                    paginationListener.liberarGatilho()
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