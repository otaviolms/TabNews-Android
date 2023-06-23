package br.com.otaviolms.tabnews.implementations.listeners

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PaginationListener(
    private val carregarMais: (proximaPagina: Int) -> Unit
) : RecyclerView.OnScrollListener() {

    private var loadProximaPagina = false
    private var paginaAtual = 1
    private var offset = 5

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount

        if (totalItemCount - lastVisibleItemPosition <= offset && !loadProximaPagina) {
            loadProximaPagina = true
            carregarMais.invoke(++paginaAtual)
        }
    }

    fun liberarGatilho() { loadProximaPagina = false }

}