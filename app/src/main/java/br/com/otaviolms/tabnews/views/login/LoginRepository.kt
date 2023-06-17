package br.com.otaviolms.tabnews.views.login

import br.com.otaviolms.tabnews.api.ConteudosAPI
import br.com.otaviolms.tabnews.api.LoginAPI
import br.com.otaviolms.tabnews.implementations.BaseRepository
import br.com.otaviolms.tabnews.models.requests.LoginRequestModel
import retrofit2.Retrofit

class LoginRepository(
    retrofit: Retrofit
): BaseRepository {

    private val api: LoginAPI = retrofit.create(LoginAPI::class.java)

    suspend fun realizarLogin(email: String, password: String) =
        chamarApi { api.realizarLogin(LoginRequestModel(email, password)) }

    suspend fun carregarUsuario() =
        chamarApi { api.carregarUsuario() }

}