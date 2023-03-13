package com.greedygamepractical.data.model

import com.google.gson.annotations.SerializedName

data class TopAlbumResponse(
    val albums: TopAlbumResponseAlbums
)

data class TopAlbumResponseAlbums(
    @SerializedName("@attr") val attr: Attr,
    val album: List<Album>
)

data class Album(
    val artist: Artist,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val url: String
)

data class Image(
    @SerializedName("#text") val text: String,
    val size: String
)


data class Attr(
    val page: String,
    val perPage: String,
    val tag: String,
    val total: String,
    val totalPages: String
)