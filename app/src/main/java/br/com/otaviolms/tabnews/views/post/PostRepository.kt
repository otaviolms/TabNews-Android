package br.com.otaviolms.tabnews.views.post

import br.com.otaviolms.tabnews.api.TabNewsAPI
import br.com.otaviolms.tabnews.enums.StrategyEnum
import br.com.otaviolms.tabnews.implementations.BaseRepository
import br.com.otaviolms.tabnews.models.PostResponseModel
import com.github.javafaker.Faker
import retrofit2.Retrofit

class PostRepository(
    retrofit: Retrofit
): BaseRepository {

    private val api: TabNewsAPI = retrofit.create(TabNewsAPI::class.java)

    suspend fun carregarPost(
        autor: String,
        slug: String,
    ) = chamarApi { api.carregarPost(autor, slug) }

}