package com.shoppi.app.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppi.app.model.Category
import com.shoppi.app.repository.category.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<Category>>()
    val items: LiveData<List<Category>> = _items

    private val _openCategoryEvent = MutableLiveData<Category>()
    val openCategoryEvent: LiveData<Category> = _openCategoryEvent

    init {
        loadCategory()
    }

    //사용자가 category item을 선택하면 update
    fun openCategoryDetail(category: Category) {
        _openCategoryEvent.value = category
    }

    private fun loadCategory() {
        // 코루틴 실행
        viewModelScope.launch {
            val categories = categoryRepository.getCategories()
            _items.value = categories
        }

    }
}