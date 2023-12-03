package me.ashutoshkk.blogapp.data.repository

import me.ashutoshkk.blogapp.data.remote.BlogApi
import me.ashutoshkk.blogapp.domain.repository.BlogRepository
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(
    private val blogApi: BlogApi
) : BlogRepository {
    override suspend fun getBlogs() = blogApi.getBlogs()
}