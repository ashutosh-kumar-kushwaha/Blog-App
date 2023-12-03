package me.ashutoshkk.blogapp.data.remote.dto

import android.text.Html
import com.google.gson.annotations.SerializedName
import me.ashutoshkk.blogapp.domain.model.Blog

data class BlogDto(
    val _links: me.ashutoshkk.blogapp.data.remote.dto.Links,
    val aioseo_notices: List<Any>,
    val author: Int,
    val categories: List<Int>,
    val comment_status: String,
    val content: me.ashutoshkk.blogapp.data.remote.dto.Content,
    val date: String,
    val date_gmt: String,
    val excerpt: me.ashutoshkk.blogapp.data.remote.dto.Excerpt,
    val featured_media: Int,
    val format: String,
    val guid: me.ashutoshkk.blogapp.data.remote.dto.Guid,
    val id: Int,
    @SerializedName("jetpack-related-posts") val jetpackRelatedPosts: List<me.ashutoshkk.blogapp.data.remote.dto.JetpackRelatedPost>,
    val jetpack_featured_media_url: String,
    val jetpack_likes_enabled: Boolean,
    val jetpack_publicize_connections: List<Any>,
    val jetpack_sharing_enabled: Boolean,
    val jetpack_shortlink: String,
    val link: String,
    val meta: me.ashutoshkk.blogapp.data.remote.dto.Meta,
    val modified: String,
    val modified_gmt: String,
    val ping_status: String,
    val slug: String,
    val status: String,
    val sticky: Boolean,
    val tags: List<Int>,
    val template: String,
    val title: me.ashutoshkk.blogapp.data.remote.dto.Title,
    val type: String
)

fun me.ashutoshkk.blogapp.data.remote.dto.BlogDto.toBlog() = Blog(
    id = id,
    title = Html.fromHtml(title.rendered, Html.FROM_HTML_MODE_COMPACT).toString(),
    imageUrl = jetpack_featured_media_url,
    url = link,
    date = date
)