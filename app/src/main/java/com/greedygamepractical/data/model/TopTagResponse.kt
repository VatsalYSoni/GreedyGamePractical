package com.greedygamepractical.data.model

data class TopTagResponse(
    val toptags: Toptags
)

data class Toptags(
    val tag: List<Tag>
)

data class Tag(
    val count: Int,
    val name: String,
    val url: String
)