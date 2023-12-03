package me.ashutoshkk.blogapp.domain.useCase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.ashutoshkk.blogapp.common.Resource
import me.ashutoshkk.blogapp.data.remote.dto.toBlog
import me.ashutoshkk.blogapp.domain.model.Blog
import me.ashutoshkk.blogapp.domain.repository.BlogRepository
import retrofit2.HttpException
import javax.inject.Inject

class GetBlogsUseCase @Inject constructor(
    private val blogRepository: BlogRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Blog>> = blogRepository.getBlogs()
}