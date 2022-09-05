package com.shoppi.app.network

import com.shoppi.app.model.Category
import com.shoppi.app.model.CategoryDetail
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

//어떤 주소와 통신할 것인지 선언
interface ApiClient {

    @GET("categories.json")
    suspend fun getCategories(): List<Category>

    /* TODO 추후 다른 카테고리에 대한 데이터도 추가하기 위해 categoryId를 메소드의 인자로 받아줌
    * retrofit 에서는 메소드의 인자로 url의 path정보를 받을 때 Path 어노테이션 제공
    @GET("{categoryId}.json")
    suspend fun getCategoryDetail(@Path("categoryId") categoryId: String): CategoryDetail
    *
    * retrofit에서 url의 쿼리를 만드는 방식도 지원함 --> 추후 스스로 학습하기
    * */

    @GET("fashion_female.json")
    suspend fun getCategoryDetail(): CategoryDetail

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