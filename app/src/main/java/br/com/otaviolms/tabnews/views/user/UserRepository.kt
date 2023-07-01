package br.com.otaviolms.tabnews.views.user

import br.com.otaviolms.tabnews.api.ConteudosAPI
import br.com.otaviolms.tabnews.connections.RetrofitBuilder
import br.com.otaviolms.tabnews.implementations.bases.BaseRepository

class UserRepository : BaseRepository {

    private val api: ConteudosAPI = RetrofitBuilder.retrofit.create(ConteudosAPI::class.java)

    suspend fun listarPostsUsuario(username: String) =
        chamarApi { api.listarPostsUsuario(username) }

}