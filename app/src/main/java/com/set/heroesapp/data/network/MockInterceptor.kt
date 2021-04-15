package com.set.heroesapp.data.network

import android.content.Context
import okhttp3.*

class   MockInterceptor(private val context: Context) : Interceptor {

    companion object {
        private val JSON_MEDIA_TYPE = MediaType.parse("application/json")
        private const val MOCK = "mock"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val header = request.header(MOCK)
        if (header != null) {
            val filename = request.url().pathSegments().last()
            return Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .message("")
                    .code(200)
                    .body(ResponseBody.create(JSON_MEDIA_TYPE,context.readFileFromAssets("heroes.json")))
                    .build()
        }

        return chain.proceed(request.newBuilder().removeHeader(MOCK).build())
        }


    fun Context.readFileFromAssets(filePath: String): String {
        return resources.assets.open(filePath).bufferedReader().use {
            it.readText()
        }
    }
    }

