package me.ashutoshkk.blogapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.ashutoshkk.blogapp.common.Constants
import me.ashutoshkk.blogapp.data.remote.BlogApi
import me.ashutoshkk.blogapp.data.repository.BlogRepositoryImpl
import me.ashutoshkk.blogapp.domain.repository.BlogRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun providesBlogApi() = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BlogApi::class.java)

    @Singleton
    @Provides
    fun providesBlogRepository(blogApi: BlogApi): BlogRepository = BlogRepositoryImpl(blogApi)
}