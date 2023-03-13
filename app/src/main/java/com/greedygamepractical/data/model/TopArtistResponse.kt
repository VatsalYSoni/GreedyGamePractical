package com.greedygamepractical.data.model

data class TopArtistResponse(
    val topartists: Topartists
)

data class Topartists(
    val artist: List<Artist>
)

data class Artist(
    val mbid: String,
    val name: String,
    val url: String,
    val image: List<Image>
)