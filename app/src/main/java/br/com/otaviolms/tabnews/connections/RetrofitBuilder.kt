package br.com.otaviolms.tabnews.connections

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitBuilder {

    private val baseUrl = "https://www.tabnews.com.br/api/v1/"

    private var instance: Retrofit? = null

    private var cookieInterceptor = CookieInterceptor()

    fun getInstance(): Retrofit {
        if(instance == null) {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(cookieInterceptor)
                .build()

            instance = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
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

    fun destroyInstance() { instance = null }

}