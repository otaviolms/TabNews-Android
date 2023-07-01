package br.com.otaviolms.tabnews.views.posts

import br.com.otaviolms.tabnews.api.ConteudosAPI
import br.com.otaviolms.tabnews.connections.RetrofitBuilder
import br.com.otaviolms.tabnews.enums.StrategyEnum
import br.com.otaviolms.tabnews.implementations.bases.BaseRepository

class PostsRepository : BaseRepository {

    private val api: ConteudosAPI = RetrofitBuilder.retrofit.create(ConteudosAPI::class.java)

    suspend fun listarConteudos(
        page: Int,
        perPage: Int,
        strategy: StrategyEnum
    ) = chamarApi { api.listarConteudos(page, perPage, strategy.nome) }

}