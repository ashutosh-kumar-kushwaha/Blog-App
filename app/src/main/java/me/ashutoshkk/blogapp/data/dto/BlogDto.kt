package me.ashutoshkk.blogapp.data.dto

import com.google.gson.annotations.SerializedName

data class BlogDto(
    val _links: Links,
    val aioseo_notices: List<Any>,
    val author: Int,
    val categories: List<Int>,
    val comment_status: String,
    val content: Content,
    val date: String,
    val date_gmt: String,
    val excerpt: Excerpt,
    val featured_media: Int,
    val format: String,
    val guid: Guid,
    val id: Int,
    @SerializedName("jetpack-related-posts") val jetpackRelatedPosts: List<JetpackRelatedPost>,
    val jetpack_featured_media_url: String,
    val jetpack_likes_enabled: Boolean,
    val jetpack_publicize_connections: List<Any>,
    val jetpack_sharing_enabled: Boolean,
    val jetpack_shortlink: String,
    val link: String,
    val meta: Meta,
    val modified: String,
    val modified_gmt: String,
    val ping_status: String,
    val slug: String,
    val status: String,
    val sticky: Boolean,
    val tags: List<Int>,
    val template: String,
    val title: Title,
    val type: String
)