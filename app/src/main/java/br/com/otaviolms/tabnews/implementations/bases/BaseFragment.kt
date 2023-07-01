package br.com.otaviolms.tabnews.implementations.bases

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import br.com.otaviolms.tabnews.implementations.annotations.Binding
import br.com.otaviolms.tabnews.models.responses.UsuarioResponseModel
import br.com.otaviolms.tabnews.singletons.Sessao
import kotlin.reflect.full.findAnnotation

/***
 * Sobreescreva os métodos que forem necessários e eles serão chamados na seguinte ordem:
 * - setupHeader
 * - setupView
 * - setupListeners
 * - setBackClickListeners
 * - setupUiState
 * - loadData
 */
abstract class BaseFragment<BINDING: ViewBinding>: Fragment() {

    protected lateinit var bnd: BINDING
        private set

    protected val usuario: UsuarioResponseModel?
        get() = Sessao.usuario.value

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return this::class.findAnnotation<Binding>()?.let {
            val method = it.binding.java.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.java
            )
            bnd = method.invoke(null, inflater, container, false) as BINDING
            return bnd.root
        } ?: bnd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupHeader()
        this.setupView()
        this.setupListeners()
        this.setupObservers()
        this.setBackClickListeners()
        this.setupUiState()
    }

    protected open fun setupHeader() {}
    protected open fun setupView() {}
    protected open fun setupListeners() {}
    protected open fun setupObservers() {}
    protected open fun setupBack() {
        this.voltar()
    }

    protected open fun setupUiState() {}
    protected open fun loadData() {}

    override fun onResume() {
        super.onResume()
        this.loadData()
    }

    private fun setBackClickListeners() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setupBack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    protected fun voltar() { findNavController().popBackStack() }

    protected fun abrirDeeplink(deeplink: String = "") {
        Log.i("LogTabNews", "Abrir deeplink: $BASE_URL$deeplink")
        findNavController().navigate(NavDeepLinkRequest.Builder.fromUri("$BASE_URL$deeplink".toUri()).build())
    }

    companion object {
        const val BASE_URL = "https://www.tabnews.com.br/"
    }
}