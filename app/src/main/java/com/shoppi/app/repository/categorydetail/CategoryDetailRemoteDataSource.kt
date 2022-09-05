package com.shoppi.app.repository.categorydetail

import com.shoppi.app.model.CategoryDetail
import com.shoppi.app.network.ApiClient

// API의 데이터를 요청해야 하므로 생성자로 ApiClient를 잡음
class CategoryDetailRemoteDataSource(private val api: ApiClient) : CategoryDetailDataSource {
    override suspend fun getCategoryDetail(): CategoryDetail {
        return api.getCategoryDetail()
    }
}