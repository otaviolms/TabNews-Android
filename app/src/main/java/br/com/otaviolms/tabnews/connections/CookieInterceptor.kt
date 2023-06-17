package br.com.otaviolms.tabnews.connections

import br.com.otaviolms.tabnews.implementations.Sessao
import okhttp3.Interceptor
import okhttp3.Response

class CookieInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (Sessao.usuario != null) {
            val modifiedRequest = originalRequest.newBuilder()
                .addHeader("Cookie", Sessao.sessionId)
                .build()
            return chain.proceed(modifiedRequest)
        }
        return chain.proceed(originalRequest)
    }

}
