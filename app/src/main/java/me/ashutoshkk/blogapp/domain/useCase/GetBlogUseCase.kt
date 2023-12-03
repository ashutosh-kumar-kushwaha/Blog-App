package me.ashutoshkk.blogapp.domain.useCase

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
    operator fun invoke(): Flow<Resource<List<Blog>>> = flow {
        try {
            emit(Resource.Loading())
            val blogs = blogRepository.getBlogs().map {
                it.toBlog()
            }
            emit(Resource.Success(blogs))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            e.printStackTrace()
        } catch (e: Exception) {
            emit(Resource.Error("Couldn't reach the server. Check your internet connection"))
            e.printStackTrace()
        }
    }
}