package br.com.otaviolms.tabnews.implementations

import android.util.Log
import retrofit2.Response
import kotlin.jvm.Throws

interface BaseRepository {

    @Throws(Exception::class)
    suspend fun <RESPONSE> chamarApi(apiCall: suspend () -> Response<RESPONSE>): RESPONSE {
        val response = apiCall.invoke()
        if(response.isSuccessful) return response.body()!!
        else throw Exception(response.message())
    }

}