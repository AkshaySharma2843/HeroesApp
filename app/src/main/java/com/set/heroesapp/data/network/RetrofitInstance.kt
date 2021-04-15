package com.set.heroesapp.data.network

import android.content.Context
import com.set.heroesapp.BuildConfig
import com.set.heroesapp.utils.LiveDataCallAdapterFactoryForRetrofit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {


    companion object {

        fun createRetrofitInstance(): Retrofit {

            return Retrofit.Builder()
                    .client(OkHttpClient.Builder()
                            .connectTimeout(60L, TimeUnit.SECONDS)
                            .readTimeout(60L, TimeUnit.SECONDS)
                            .build())
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactoryForRetrofit())
                    .build()
        }
    }
}