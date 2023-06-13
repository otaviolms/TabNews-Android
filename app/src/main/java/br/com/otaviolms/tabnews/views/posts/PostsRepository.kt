package br.com.otaviolms.tabnews.views.posts

import br.com.otaviolms.tabnews.api.TabNewsAPI
import br.com.otaviolms.tabnews.enums.StrategyEnum
import br.com.otaviolms.tabnews.implementations.BaseRepository
import br.com.otaviolms.tabnews.models.PostResponseModel
import com.github.javafaker.Faker
import retrofit2.Retrofit

class PostsRepository(
    retrofit: Retrofit
): BaseRepository {

    private val api: TabNewsAPI = retrofit.create(TabNewsAPI::class.java)

    suspend fun listarConteudos(
        page: Int,
        perPage: Int,
        strategy: StrategyEnum
    ) = chamarApi { api.listarConteudos(page, perPage, strategy.nome) }

}