package me.ashutoshkk.blogapp.data.remote

import me.ashutoshkk.blogapp.data.remote.dto.BlogDto
import retrofit2.http.GET

interface BlogApi {
    @GET("wp-json/wp/v2/posts?per_page=10&page=1")
    suspend fun getBlogs(): List<BlogDto>
}