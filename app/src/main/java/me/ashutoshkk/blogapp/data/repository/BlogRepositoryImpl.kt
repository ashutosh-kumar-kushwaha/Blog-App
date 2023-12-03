package me.ashutoshkk.blogapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import me.ashutoshkk.blogapp.data.remote.BlogApi
import me.ashutoshkk.blogapp.data.remote.BlogPagingSource
import me.ashutoshkk.blogapp.domain.repository.BlogRepository
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(
    private val blogApi: BlogApi
) : BlogRepository {
    override suspend fun getBlogs() = Pager(
        config = PagingConfig(
            pageSize = 10
        ),
        pagingSourceFactory = {
            BlogPagingSource(blogApi)
        }
    ).flow
}