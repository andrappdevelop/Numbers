package com.example.numbers.numbers.data.cloud

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

interface CloudModule {

    fun <T> service(clasz: Class<T>): T

    class Mock : CloudModule {
        override fun <T> service(clasz: Class<T>): T {
            return MockNumbersService() as T
        }
    }

    class Base : CloudModule {

        override fun <T> service(clasz: Class<T>): T {
            val interceptor = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(1, TimeUnit.MINUTES)
                .callTimeout(1, TimeUnit.MINUTES)
                .build()
            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
            return retrofit.create(clasz)
        }

        companion object {
            private const val BASE_URL: String = "http://numbersapi.com/"
        }
    }
}