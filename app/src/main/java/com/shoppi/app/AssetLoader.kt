package com.shoppi.app

import android.content.Context

class AssetLoader(private val context: Context) {

    //getOrNull로 null을 반환할 수 있으므로 nullable
    fun getJsonString(fileName: String): String? {
        return kotlin.runCatching {
            loadAsset(fileName)
        }.getOrNull()
    }

    private fun loadAsset(fileName: String): String {
        return context.assets.open(fileName).use { inputStream ->
            val size = inputStream.available() //data가 실제 존재하는지
            val bytes = ByteArray(size)
            inputStream.read(bytes)
            String(bytes)
        }
    }
}