package me.ashutoshkk.blogapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.ashutoshkk.blogapp.common.Resource
import me.ashutoshkk.blogapp.domain.model.Blog
import me.ashutoshkk.blogapp.domain.useCase.GetBlogsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBlogsUseCase: GetBlogsUseCase
): ViewModel() {

    private val _blogs = MutableStateFlow<List<Blog>>(listOf())
    val blogs = _blogs.asStateFlow()

    init {
        getBlogs()
    }

    private fun getBlogs() {
        getBlogsUseCase().onEach {
            when(it){
                is Resource.Success -> {
                    _blogs.value = it.data!!
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}