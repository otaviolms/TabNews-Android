package br.com.otaviolms.tabnews.implementations

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<BINDING: ViewBinding>: Fragment() {

    protected var _binding: BINDING? = null
    protected val bnd
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupHeader()
        this.setupView()
        this.setupListeners()
        this.setBackClickListeners()
        this.setupUiState()
        this.loadData()
    }

    protected open fun setupHeader() {}
    protected open fun setupView() {}
    protected open fun setupListeners() {}
    protected open fun setupBack() {}
    protected abstract fun setupUiState()
    protected abstract fun loadData()

    private fun setBackClickListeners() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setupBack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}