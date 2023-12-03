package me.ashutoshkk.blogapp.data.remote.dto

import android.icu.text.SimpleDateFormat
import android.text.Html
import com.google.gson.annotations.SerializedName
import me.ashutoshkk.blogapp.domain.model.Blog
import java.text.ParseException
import java.util.Locale

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

fun BlogDto.toBlog() = Blog(
    id = id,
    title = Html.fromHtml(title.rendered, Html.FROM_HTML_MODE_COMPACT).toString(),
    imageUrl = jetpack_featured_media_url,
    url = link,
    date = date.formatDate()
)

fun String.formatDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())

    return try {
        val inputDate = inputFormat.parse(this)
        val outputDate = outputFormat.format(inputDate)

        println(outputDate)
        outputDate
    } catch (e: ParseException) {
        this
    }
}