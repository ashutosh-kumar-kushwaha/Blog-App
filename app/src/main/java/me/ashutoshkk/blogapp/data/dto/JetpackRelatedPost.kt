package me.ashutoshkk.blogapp.data.dto

data class JetpackRelatedPost(
    val author: String,
    val block_context: BlockContext,
    val classes: List<Any>,
    val context: String,
    val date: String,
    val excerpt: String,
    val format: Boolean,
    val id: Int,
    val img: Img,
    val rel: String,
    val title: String,
    val url: String,
    val url_meta: UrlMeta
)