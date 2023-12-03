package me.ashutoshkk.blogapp.domain.model

data class Blog (
    val id: Int,
    val title: String,
    val imageUrl: String,
    val date: String,
    val url: String,
)