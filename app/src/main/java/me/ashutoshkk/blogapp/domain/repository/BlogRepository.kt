package me.ashutoshkk.blogapp.domain.repository

import me.ashutoshkk.blogapp.data.remote.dto.BlogDto

interface BlogRepository {
    suspend fun getBlogs(): List<BlogDto>
}