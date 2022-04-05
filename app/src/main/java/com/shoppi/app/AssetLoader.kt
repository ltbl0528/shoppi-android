package com.shoppi.app

import android.content.Context

class AssetLoader {

    //getOrNull로 null을 반환할 수 있으므로 nullable
    fun getJsonString(context: Context, fileName: String): String? {
        return kotlin.runCatching {
            loadAsset(context, fileName)
        }.getOrNull()
    }

    private fun loadAsset(context: Context, fileName: String): String {
        return context.assets.open(fileName).use { inputStream ->
            val size = inputStream.available() //data가 실제 존재하는지
            val bytes = ByteArray(size)
            inputStream.read(bytes)
            String(bytes)
        }
    }
}