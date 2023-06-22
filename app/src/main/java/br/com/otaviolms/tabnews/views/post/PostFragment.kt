package br.com.otaviolms.tabnews.views.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.otaviolms.tabnews.adapters.ConteudosAdapter
import br.com.otaviolms.tabnews.adapters.RespostasAdapter
import br.com.otaviolms.tabnews.databinding.FragmentPostBinding
import br.com.otaviolms.tabnews.extensions.makeGone
import br.com.otaviolms.tabnews.extensions.makeVisible
import br.com.otaviolms.tabnews.implementations.BaseFragment
import br.com.otaviolms.tabnews.implementations.Sessao
import br.com.otaviolms.tabnews.implementations.callbacks.ConteudoCallbacks
import br.com.otaviolms.tabnews.models.responses.PostResponseModel
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Shape
import java.util.concurrent.TimeUnit


class PostFragment: BaseFragment<FragmentPostBinding>() {

    private val vm: PostViewModel by viewModels()

    private val args: PostFragmentArgs by navArgs()

    private val callbacks by lazy {
        object : ConteudoCallbacks {
            override fun onAutor(autor: String) { abrirDeeplink(autor) }
            override fun onBaixo() {}
            override fun onCima() { bnd.confeti.start(party) }
            override fun onResponder() {}
            override fun onClick(post: PostResponseModel) { abrirDeeplink("${post.autor}/${post.slug}") }
        }
    }

    private val respostasAdapter by lazy { RespostasAdapter(callback = callbacks) }

    private val party = Party(
        speed = 0f,
        maxSpeed = 30f,
        damping = 0.9f,
        spread = 360,
        colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
        emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
        position = Position.Relative(0.5, 0.2),
        shapes = listOf(Shape.Square)
    )

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentPostBinding.inflate(inflater, container, false)

    override fun setupHeader() {
        bnd.imvLogoApp.setOnClickListener { voltar() }
    }

    override fun setupView() {
        usuario?.let { bnd.btnResponder.makeVisible() } ?: bnd.btnResponder.makeGone()
        bnd.rcvComentarios.adapter = respostasAdapter
    }

    override fun setupListeners() {
        bnd.btnResponder.setOnClickListener { bnd.confeti.start(party) }
    }

    override fun setupUiState() {
        vm.uiState.observe(viewLifecycleOwner) { uiState ->
            when(uiState) {
                is PostUiState.Sucesso -> {
                    bnd.conteudo.renderPost(conteudo = uiState.post, callback = callbacks)
                }
                is PostUiState.SucessoComentarios -> {
                    respostasAdapter.definirConteudo(uiState.comentarios)
                }
                is PostUiState.ErroComentarios,
                is PostUiState.Erro -> {
                }
            }
        }
    }

    override fun setupObservers() {
        Sessao.usuario.observe(viewLifecycleOwner) { setupView() }
    }

    override fun loadData() {
        vm.carregarPost(autor = args.autor, slug = args.slug)
        vm.carregarComentarios(autor = args.autor, slug = args.slug)
    }

}
