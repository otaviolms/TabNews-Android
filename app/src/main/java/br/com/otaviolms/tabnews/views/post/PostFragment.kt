package br.com.otaviolms.tabnews.views.post

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.otaviolms.tabnews.R
import br.com.otaviolms.tabnews.databinding.FragmentPostBinding
import br.com.otaviolms.tabnews.extensions.makeGone
import br.com.otaviolms.tabnews.implementations.BaseFragment
import br.com.otaviolms.tabnews.implementations.Sessao
import br.com.otaviolms.tabnews.utils.calcularHorasPassadas
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import io.noties.markwon.Markwon
import io.noties.markwon.image.AsyncDrawable
import io.noties.markwon.image.glide.GlideImagesPlugin
import io.noties.markwon.image.glide.GlideImagesPlugin.GlideStore


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
        var previousScrollY = 0
        bnd.scvConteudo.viewTreeObserver.addOnScrollChangedListener {
            val scrollY = bnd.scvConteudo.scrollY
            if (scrollY < previousScrollY && bnd.fab.visibility == View.GONE) bnd.fab.show()
            else if (scrollY > previousScrollY && bnd.fab.visibility == View.VISIBLE) bnd.fab.hide()
            previousScrollY = scrollY
        }
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

                        bnd.txvAutor.setOnClickListener { abrirDeeplink(autor) }

                        Markwon.builder(requireContext())
                            .usePlugin(GlideImagesPlugin.create(requireContext()))
                            .build()
                            .setMarkdown(bnd.txvConteudo, body ?: "")
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
