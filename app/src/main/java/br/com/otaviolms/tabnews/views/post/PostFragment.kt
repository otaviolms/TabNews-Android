package br.com.otaviolms.tabnews.views.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.otaviolms.tabnews.R
import br.com.otaviolms.tabnews.databinding.FragmentPostBinding
import br.com.otaviolms.tabnews.extensions.makeGone
import br.com.otaviolms.tabnews.extensions.makeVisible
import br.com.otaviolms.tabnews.extensions.pegarDrawable
import br.com.otaviolms.tabnews.implementations.BaseFragment
import br.com.otaviolms.tabnews.implementations.Sessao
import br.com.otaviolms.tabnews.models.responses.UsuarioResponseModel
import br.com.otaviolms.tabnews.utils.calcularHorasPassadas
import io.noties.markwon.Markwon

class PostFragment: BaseFragment<FragmentPostBinding>() {

    private val vm: PostViewModel by viewModels()

    private val args: PostFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return bnd.root
    }

    override fun setupHeader() {
        bnd.imvLogoApp.setOnClickListener { voltar() }
    }

    override fun setupView() {
        usuario?.let { bnd.fab.show() } ?: bnd.fab.makeGone()
    }

    override fun setupListeners() {
    }

    override fun setupUiState() {
        vm.uiState.observe(viewLifecycleOwner) { uiState ->
            when(uiState) {
                is PostUiState.Sucesso -> {
                    with(uiState.post) {
                        bnd.txvAutor.text = autor
                        bnd.txvData.text = resources.getQuantityString(R.plurals.horasPlural, calcularHorasPassadas(publishedAt), calcularHorasPassadas(publishedAt))
                        bnd.txvTitulo.text = titulo
                        bnd.txvConteudo.text = body

                        val markwon = Markwon.create(requireContext())
                        markwon.setMarkdown(bnd.txvConteudo, body ?: "")
                    }
                }
                is PostUiState.Erro -> {
                }
            }
        }
    }

    override fun setupObservers() {
        Sessao.usuario.observe(viewLifecycleOwner) { setupView() }
    }

    override fun loadData() { vm.carregarPost(autor = args.autor, slug = args.slug) }

}
