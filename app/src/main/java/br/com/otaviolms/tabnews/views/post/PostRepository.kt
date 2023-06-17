package br.com.otaviolms.tabnews.views.post

import br.com.otaviolms.tabnews.api.ConteudosAPI
import br.com.otaviolms.tabnews.implementations.BaseRepository
import retrofit2.Retrofit

class PostRepository(
    retrofit: Retrofit
): BaseRepository {

    private val api: ConteudosAPI = retrofit.create(ConteudosAPI::class.java)

    suspend fun carregarPost(
        autor: String,
        slug: String,
    ) = chamarApi { api.carregarPost(autor, slug) }

}