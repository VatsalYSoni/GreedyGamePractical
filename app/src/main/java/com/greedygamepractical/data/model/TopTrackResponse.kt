package com.greedygamepractical.data.model

import com.google.gson.annotations.SerializedName

data class TopTrackResponse(
    val tracks: Tracks
)

data class Tracks(
    val track: List<Track>
)

data class Track(
    val artist: ArtistTrack,
    val duration: String,
    val image: List<ImageTrack>,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)

data class ImageTrack(
    @SerializedName("#text") val text: String,
    val size: String
)

data class ArtistTrack(
    val mbid: String,
    val name: String,
    val url: String
)

data class Streamable(
    @SerializedName("#text")  val text: String,
    val fulltrack: String
)