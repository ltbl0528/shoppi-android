package com.shoppi.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shoppi.app.AssetLoader
import com.shoppi.app.network.ApiClient
import com.shoppi.app.repository.category.CategoryRemoteDataSource
import com.shoppi.app.repository.category.CategoryRepository
import com.shoppi.app.repository.categorydetail.CategoryDetailRemoteDataSource
import com.shoppi.app.repository.categorydetail.CategoryDetailRepository
import com.shoppi.app.repository.home.HomeAssetDataSource
import com.shoppi.app.repository.home.HomeRepository
import com.shoppi.app.ui.category.CategoryViewModel
import com.shoppi.app.ui.categorydetail.CategoryDetailViewModel
import com.shoppi.app.ui.home.HomeViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //제네릭 타입으로 전달받은 인자의 타입이 HomeViewModel인지 체크
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                //의존 관계가 생김 ViewModel은 Repository가 필요 -> Repository는 AssetDataSource
                // AssetDataSource는 AssetLoader가 필요 (의존성 주입)
                val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                val repository = CategoryRepository(CategoryRemoteDataSource(ApiClient.create()))
                CategoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryDetailViewModel::class.java) -> {
                val repository =
                    CategoryDetailRepository(CategoryDetailRemoteDataSource(ApiClient.create()))
                CategoryDetailViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}