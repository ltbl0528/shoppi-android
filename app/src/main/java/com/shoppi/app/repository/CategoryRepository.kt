package com.shoppi.app.repository

import com.shoppi.app.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(
    private val remoteDataSource: CategoryRemoteDataSource
) {

    //코루틴 스콥이 아니면 실행할 수 없는 상태가 됨
    suspend fun getCategories(): List<Category> {
        return remoteDataSource.getCategories()
    }
}