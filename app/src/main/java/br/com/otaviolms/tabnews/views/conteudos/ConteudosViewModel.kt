package br.com.otaviolms.tabnews.views.conteudos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.otaviolms.tabnews.connections.RetrofitBuilder
import br.com.otaviolms.tabnews.enums.StrategyEnum
import br.com.otaviolms.tabnews.repository.MainActivityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConteudosViewModel(
    private val repository: MainActivityRepository = MainActivityRepository(retrofit = RetrofitBuilder.getInstance())
): ViewModel() {

    private val _uiState: MutableLiveData<ConteudosUiState> = MutableLiveData()
    val uiState: LiveData<ConteudosUiState> = _uiState

    private fun setState(state: ConteudosUiState) { viewModelScope.launch { _uiState.postValue(state) } }

    fun listarConteudos(page: Int, perPage: Int = 10, strategy: StrategyEnum = StrategyEnum.NOVOS, novaPagina: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching { repository.listarConteudos(page, perPage, strategy) }
                .onSuccess {
                    if(novaPagina) setState(ConteudosUiState.SucessoNovaPagina(it))
                    else setState(ConteudosUiState.Sucesso(it))
                }
                .onFailure {
                    Log.e("Conteudos", it.message ?: "Ocorreu um erro!")
                    Log.e("Conteudos", it.stackTraceToString())
                    setState(ConteudosUiState.Erro(it.message ?: "Ocorreu um erro"))
                }
        }
    }

}