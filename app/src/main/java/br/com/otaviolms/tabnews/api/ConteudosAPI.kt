package br.com.otaviolms.tabnews.api

import br.com.otaviolms.tabnews.models.responses.PostResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ConteudosAPI {

    @GET("contents")
    suspend fun listarConteudos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("strategy") strategy: String,
    ) : Response<ArrayList<PostResponseModel>>

    @GET("contents/{autor}/{slug}")
    suspend fun carregarPost(
        @Path("autor") autor: String,
        @Path("slug") slug: String,
    ) : Response<PostResponseModel>

    @GET("contents/{autor}/{slug}/children")
    suspend fun carregarComentarios(
        @Path("autor") autor: String,
        @Path("slug") slug: String,
    ) : Response<ArrayList<PostResponseModel>>

    @GET("contents/{username}")
    suspend fun listarPostsUsuario(
        @Path("username") username: String,
    ) : Response<ArrayList<PostResponseModel>>

}