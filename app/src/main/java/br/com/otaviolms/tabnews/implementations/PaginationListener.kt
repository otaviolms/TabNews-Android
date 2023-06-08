package br.com.otaviolms.tabnews.implementations

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PaginationListener(
    private val layoutManager: LinearLayoutManager,
    private val carregarMais: (pagina: Int) -> Unit
) : RecyclerView.OnScrollListener() {

    private var isLoading = false

    private var paginaAtual = 1

    private var offset = 3

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItens: Int = layoutManager.childCount
        val ultimoItemVisivel: Int = layoutManager.findLastCompletelyVisibleItemPosition()

        if (ultimoItemVisivel >= totalItens - offset) carregarMaisItens()
    }

    private fun carregarMaisItens() {
        isLoading = true
        paginaAtual++
        carregarMais.invoke(paginaAtual)
    }

}