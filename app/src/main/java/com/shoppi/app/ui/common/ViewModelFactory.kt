package com.shoppi.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shoppi.app.AssetLoader
import com.shoppi.app.repository.HomeAssetDataSource
import com.shoppi.app.repository.HomeRepository
import com.shoppi.app.ui.home.HomeViewModel

class ViewModelFactory(private val context: Context) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //제네릭 타입으로 전달받은 인자의 타입이 HomeViewModel인지 체크
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            //의존 관계가 생김 ViewModel은 Repository가 필요 -> Repository는 AssetDataSource
                // AssetDataSource는 AssetLoader가 필요 (의존성 주입)
            val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
            return HomeViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}