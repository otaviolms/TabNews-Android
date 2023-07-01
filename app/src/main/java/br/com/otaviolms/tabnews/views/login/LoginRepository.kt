package br.com.otaviolms.tabnews.views.login

import br.com.otaviolms.tabnews.api.LoginAPI
import br.com.otaviolms.tabnews.connections.RetrofitBuilder
import br.com.otaviolms.tabnews.implementations.bases.BaseRepository
import br.com.otaviolms.tabnews.models.requests.LoginRequestModel

class LoginRepository : BaseRepository {

    private val api: LoginAPI = RetrofitBuilder.retrofit.create(LoginAPI::class.java)

    suspend fun realizarLogin(email: String, password: String) =
        chamarApi { api.realizarLogin(LoginRequestModel(email, password)) }

    suspend fun carregarUsuario() =
        chamarApi { api.carregarUsuario() }

}