package me.ashutoshkk.blogapp.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.ashutoshkk.blogapp.domain.model.Blog

interface BlogRepository {
    suspend fun getBlogs(): Flow<PagingData<Blog>>
}