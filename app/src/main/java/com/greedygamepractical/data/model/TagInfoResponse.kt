package com.greedygamepractical.data.model

data class TagInfoResponse(
    val tag: TagInfoResponseTag
)

data class TagInfoResponseTag(
    val name: String,
    val reach: Int,
    val total: Int,
    val wiki: Wiki
)

data class Wiki(
    val content: String,
    val summary: String
)