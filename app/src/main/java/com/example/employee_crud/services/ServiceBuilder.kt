package com.example.employee_crud.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL = "https://5ae7-2c0f-fe38-2328-fee7-f6a5-d43a-b4b2-f784.ngrok-free.app/api/v1/"

    //logging
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //okhttpclient
    private val  okHttp = OkHttpClient
        .Builder()
        .addInterceptor(logger)

    //retrofit builder
    private val builder = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    //retrofit instance
    private val retrofit = builder.build()

    //function to accept a generic class
    fun <T> buildService(serviceType: Class<T>): T{
        return retrofit.create(serviceType)
    }
}