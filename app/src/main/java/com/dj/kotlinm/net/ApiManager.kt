package com.dj.kotlinm.net

import com.dj.kotlinm.config.Constans
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * @author djc
 *
 * @time 2023/5/18/018  23:15
 *
 * @desc the api manager
 *
 **/
object ApiManager {

    fun <T> createDjWanAndroidApi(api: Class<T>): T {

        val client = OkHttpClient().newBuilder()
            .readTimeout(2000, TimeUnit.SECONDS)
            .writeTimeout(3000, TimeUnit.SECONDS)
            .connectTimeout(5000, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constans.BASE_URL1)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(api)
    }
}