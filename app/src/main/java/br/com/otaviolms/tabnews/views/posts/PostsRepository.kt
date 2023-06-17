package br.com.otaviolms.tabnews.views.posts

import br.com.otaviolms.tabnews.api.ConteudosAPI
import br.com.otaviolms.tabnews.enums.StrategyEnum
import br.com.otaviolms.tabnews.implementations.BaseRepository
import retrofit2.Retrofit

class PostsRepository(
    retrofit: Retrofit
): BaseRepository {

    private val api: ConteudosAPI = retrofit.create(ConteudosAPI::class.java)

    suspend fun listarConteudos(
        page: Int,
        perPage: Int,
        strategy: StrategyEnum
    ) = chamarApi { api.listarConteudos(page, perPage, strategy.nome) }

}