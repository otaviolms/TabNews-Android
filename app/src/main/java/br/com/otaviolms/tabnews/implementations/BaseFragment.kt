package br.com.otaviolms.tabnews.implementations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<BINDING: ViewBinding>: Fragment() {

    protected var _binding: BINDING? = null
    protected val bnd
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupView()
        this.setupListeners()
        this.setupUiState()
        this.loadData()
    }

    protected open fun setupView() {}
    protected open fun setupListeners() {}
    protected abstract fun setupUiState()
    protected abstract fun loadData()

}