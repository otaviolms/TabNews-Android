package br.com.otaviolms.tabnews.connections

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitBuilder {

    private val baseUrl = "https://www.tabnews.com.br/api/v1/"

    private var instance: Retrofit? = null

    fun getInstance(): Retrofit {
        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(
                    JacksonConverterFactory.create(
                        ObjectMapper().apply { configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true) }
                    )
                )
                .client(OkHttpClient.Builder().build())
                .build()
        }
        return instance!!
    }

}