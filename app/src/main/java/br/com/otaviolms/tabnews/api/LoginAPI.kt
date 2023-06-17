package br.com.otaviolms.tabnews.api

import br.com.otaviolms.tabnews.implementations.Sessao
import br.com.otaviolms.tabnews.models.requests.LoginRequestModel
import br.com.otaviolms.tabnews.models.responses.LoginResponseModel
import br.com.otaviolms.tabnews.models.responses.UsuarioResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface LoginAPI {

    @POST("sessions")
    suspend fun realizarLogin(@Body login: LoginRequestModel) : Response<LoginResponseModel>

    @GET("user")
    suspend fun carregarUsuario(@Header("Cookie") cookie: String = Sessao.sessionId) : Response<UsuarioResponseModel>

}