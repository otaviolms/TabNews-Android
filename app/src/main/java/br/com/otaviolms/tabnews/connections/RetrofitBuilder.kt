package br.com.otaviolms.tabnews.connections

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitBuilder {

    private const val baseUrl = "https://www.tabnews.com.br/api/v1/"

    val retrofit: Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(BASIC)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(CookieInterceptor())
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                JacksonConverterFactory.create(
                    ObjectMapper().apply {
                        configure(
                            DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
                            true
                        )
                    }
                )
            )
            .client(okHttpClient)
            .build()
    }

}