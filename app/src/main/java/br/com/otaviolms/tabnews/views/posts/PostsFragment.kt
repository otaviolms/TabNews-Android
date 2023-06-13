package br.com.otaviolms.tabnews.views.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
import br.com.otaviolms.tabnews.implementations.PaginationListener

class PostsFragment: BaseFragment<FragmentPostsBinding>() {

    private val vm: PostsViewModel by viewModels()

    private val conteudosAdapter by lazy {
        ConteudosAdapter(context = requireContext()) {
            findNavController().navigate(PostsFragmentDirections.abrirPost(it.autor, it.slug))
        }
    }
    private val linearLayoutManager by lazy { LinearLayoutManager(requireContext()) }

    private val args: PostsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return bnd.root
    }

    override fun setupHeader() {
        bnd.txvRelevantes.setOnClickListener { findNavController().navigate(PostsFragmentDirections.abrirListaPosts(relevantes = true)) }
        bnd.txvRecentes.setOnClickListener { findNavController().navigate(PostsFragmentDirections.abrirListaPosts(relevantes = false)) }
    }

    override fun setupView() {
        if(args.relevantes) definirAbaRelevantes() else definirAbaRecentes()
        with(bnd.rcvConteudos) {
            adapter = conteudosAdapter
            layoutManager = linearLayoutManager
        }
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

    private fun definirAbaRelevantes() {
        bnd.txvRelevantes.background = requireContext().pegarDrawable(R.drawable.fundo_menu)
        bnd.txvRecentes.removerFundo()
    }

    private fun definirAbaRecentes() {
        bnd.txvRecentes.background = requireContext().pegarDrawable(R.drawable.fundo_menu)
        bnd.txvRelevantes.removerFundo()
    }

    override fun loadData() {
        val strategy = if(args.relevantes) StrategyEnum.RELEVANTES else StrategyEnum.NOVOS
        vm.listarPosts(page = 1, perPage = PER_PAGE, strategy = strategy)
    }

    companion object {
        const val PER_PAGE = 20
    }

}