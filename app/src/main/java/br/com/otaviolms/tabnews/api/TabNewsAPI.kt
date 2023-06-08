package br.com.otaviolms.tabnews.api

import br.com.otaviolms.tabnews.models.ItemConteudoResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface TabNewsAPI {

    @GET("contents")
    suspend fun listarConteudos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("strategy") strategy: String,
    ) : Response<ArrayList<ItemConteudoResponseModel>>

}