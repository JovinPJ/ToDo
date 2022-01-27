package com.learn.todoapp.data.api

import com.learn.todoapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val REST_API_BASE_URL = "https://reqres.in/api/"

fun provideRetrofit(): Retrofit {
    val client = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(logging)
    }

    return Retrofit.Builder().client(client.build())
        .baseUrl(REST_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideTodoApi(retrofit: Retrofit): TodoApi = retrofit.create(TodoApi::class.java)