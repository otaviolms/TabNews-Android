package br.com.otaviolms.tabnews.implementations.bases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel<UISTATE>: ViewModel() {

    private val _uiState: MutableLiveData<UISTATE> = MutableLiveData()
    val uiState: LiveData<UISTATE> = _uiState

    protected fun setState(state: UISTATE) {
        viewModelScope.launch { state?.let { _uiState.postValue(it) } }
    }

}