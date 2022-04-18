package com.shoppi.app.network

import com.shoppi.app.model.Category
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//어떤 주소와 통신할 것인지 선언
interface ApiClient {

    @GET("categories.json")
    suspend fun getCategories(): List<Category>

    //TODO API가 추가되면 위와 같이 선언

    companion object {

        private const val baseUrl = "https://shoppi-b06a4-default-rtdb.asia-southeast1.firebasedatabase.app/"

        fun create(): ApiClient {

            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)
        }
    }
}