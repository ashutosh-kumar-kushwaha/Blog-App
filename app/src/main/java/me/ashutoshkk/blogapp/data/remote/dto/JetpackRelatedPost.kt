package me.ashutoshkk.blogapp.data.remote.dto

data class JetpackRelatedPost(
    val author: String,
    val block_context: me.ashutoshkk.blogapp.data.remote.dto.BlockContext,
    val classes: List<Any>,
    val context: String,
    val date: String,
    val excerpt: String,
    val format: Boolean,
    val id: Int,
    val img: me.ashutoshkk.blogapp.data.remote.dto.Img,
    val rel: String,
    val title: String,
    val url: String,
    val url_meta: me.ashutoshkk.blogapp.data.remote.dto.UrlMeta
)