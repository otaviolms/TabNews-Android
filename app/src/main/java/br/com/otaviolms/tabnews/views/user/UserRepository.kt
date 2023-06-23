package br.com.otaviolms.tabnews.views.user

import br.com.otaviolms.tabnews.api.ConteudosAPI
import br.com.otaviolms.tabnews.implementations.bases.BaseRepository
import retrofit2.Retrofit

class UserRepository(
    retrofit: Retrofit
): BaseRepository {

    private val api: ConteudosAPI = retrofit.create(ConteudosAPI::class.java)

    suspend fun listarPostsUsuario(username: String) =
        chamarApi { api.listarPostsUsuario(username) }

}