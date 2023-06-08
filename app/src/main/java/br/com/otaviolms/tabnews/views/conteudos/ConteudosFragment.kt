package br.com.otaviolms.tabnews.views.conteudos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.otaviolms.tabnews.databinding.FragmentConteudosBinding
import br.com.otaviolms.tabnews.adapters.ConteudosAdapter
import br.com.otaviolms.tabnews.extensions.makeGone
import br.com.otaviolms.tabnews.extensions.makeVisible
import br.com.otaviolms.tabnews.implementations.BaseFragment
import br.com.otaviolms.tabnews.implementations.PaginationListener

class ConteudosFragment: BaseFragment<FragmentConteudosBinding>() {

    private val vm: ConteudosViewModel by viewModels()

    private val conteudosAdapter by lazy { ConteudosAdapter(context = requireContext()) }
    private val linearLayoutManager by lazy { LinearLayoutManager(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConteudosBinding.inflate(inflater, container, false)
        return bnd.root
    }

    override fun setupView() {
        with(bnd.rcvConteudos) {
            adapter = conteudosAdapter
            layoutManager = linearLayoutManager
        }
    }

    override fun setupListeners() {
        bnd.srlConteudos.setOnRefreshListener { this.loadData() }
        bnd.rcvConteudos.addOnScrollListener(
            PaginationListener(layoutManager = linearLayoutManager) { pagina ->
                vm.listarConteudos(page = pagina + 1, novaPagina = true)
            }
        )
    }

    override fun setupUiState() {
        vm.uiState.observe(viewLifecycleOwner) { uiState ->
            when(uiState) {
                is ConteudosUiState.Sucesso -> {
                    bnd.txvFalha.makeGone()
                    bnd.srlConteudos.isRefreshing = false
                    conteudosAdapter.definirConteudo(uiState.conteudo)
                }
                is ConteudosUiState.SucessoNovaPagina -> {
                    bnd.txvFalha.makeGone()
                    bnd.srlConteudos.isRefreshing = false
                    conteudosAdapter.adicionarConteudo(uiState.conteudo)
                }
                is ConteudosUiState.Erro -> {
                    bnd.srlConteudos.makeGone()
                    bnd.txvFalha.makeVisible()
                }
            }
        }
    }

    override fun loadData() { vm.listarConteudos(page = 1, perPage = PER_PAGE) }

    companion object {
        const val PER_PAGE = 20
    }

}