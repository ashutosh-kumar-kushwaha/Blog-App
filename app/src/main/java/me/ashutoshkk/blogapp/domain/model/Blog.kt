package me.ashutoshkk.blogapp.domain.model

data class Blog (
    val id: Int,
    val title: String,
    val image: String,
    val dateModified: String,
    val link: String,
)