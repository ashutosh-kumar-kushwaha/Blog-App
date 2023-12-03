package me.ashutoshkk.blogapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Links(
    val about: List<me.ashutoshkk.blogapp.data.remote.dto.About>,
    val author: List<me.ashutoshkk.blogapp.data.remote.dto.Author>,
    val collection: List<me.ashutoshkk.blogapp.data.remote.dto.Collection>,
    val curies: List<me.ashutoshkk.blogapp.data.remote.dto.Cury>,
    @SerializedName("predecessor-version") val predecessorVersion: List<me.ashutoshkk.blogapp.data.remote.dto.PredecessorVersion>,
    val replies: List<me.ashutoshkk.blogapp.data.remote.dto.Reply>,
    val self: List<me.ashutoshkk.blogapp.data.remote.dto.Self>,
    @SerializedName("version-history") val versionHistory: List<me.ashutoshkk.blogapp.data.remote.dto.VersionHistory>,
    @SerializedName("wp:attachment") val wpAttachment: List<me.ashutoshkk.blogapp.data.remote.dto.WpAttachment>,
    @SerializedName("wp:featuredmedia") val wpFeaturedmedia: List<me.ashutoshkk.blogapp.data.remote.dto.WpFeaturedmedia>,
    @SerializedName("wp:term") val wpTerm: List<me.ashutoshkk.blogapp.data.remote.dto.WpTerm>
)