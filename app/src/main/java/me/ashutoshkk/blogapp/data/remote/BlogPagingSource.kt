package me.ashutoshkk.blogapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.ashutoshkk.blogapp.data.remote.dto.BlogDto
import me.ashutoshkk.blogapp.data.remote.dto.toBlog
import me.ashutoshkk.blogapp.domain.model.Blog

class BlogPagingSource(
    private val blogApi: BlogApi
) : PagingSource<Int, Blog>() {
    override fun getRefreshKey(state: PagingState<Int, Blog>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Blog> {
        return try {
            val page = params.key ?: 1
            val response = blogApi.getBlogs(page, params.loadSize).map {
                it.toBlog()
            }
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}