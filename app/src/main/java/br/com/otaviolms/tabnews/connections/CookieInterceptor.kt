package br.com.otaviolms.tabnews.connections

import android.util.Log
import br.com.otaviolms.tabnews.singletons.Sessao
import okhttp3.Interceptor
import okhttp3.Response

class CookieInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        Log.i("LogTabNews", "Cookie Interceptor chamado")
        Log.i("LogTabNews", "Sessao.token: ${Sessao.token}")
        if (Sessao.token.isNotEmpty()) {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("Cookie", Sessao.sessionId)
                    .build()
            )
        } else proceed(request())
    }

//    override fun intercept(chain: Interceptor.Chain): Response {
//        Log.i("LogTabNews", "Cookie Interceptor chamado")
//        Log.i("LogTabNews", "Sessao.token: ${Sessao.token}")
//        val originalRequest = chain.request()
//        return if (Sessao.token.isNotEmpty()) {
//            val modifiedRequest = originalRequest.newBuilder()
//                .addHeader("Cookie", Sessao.sessionId)
//                .build()
//            chain.proceed(modifiedRequest)
//        } else chain.proceed(originalRequest)
//    }

}
