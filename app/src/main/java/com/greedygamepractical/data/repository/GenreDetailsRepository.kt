package com.greedygamepractical.data.repository

import com.greedygamepractical.data.model.TagInfoResponseTag
import com.greedygamepractical.data.api.NetworkService
import com.greedygamepractical.data.model.TopAlbumResponse
import com.greedygamepractical.data.model.TopArtistResponse
import com.greedygamepractical.data.model.TopTrackResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreDetailsRepository @Inject constructor(private val networkService: NetworkService) {


    fun getTagInfo(tag:String): Flow<TagInfoResponseTag> {
        return flow {
            emit(networkService.getTagInfo(tag))
        }.map { it.tag }
    }

    fun getTopAlbum(tag:String): Flow<TopAlbumResponse> {
        return flow {
            emit(networkService.getTopAlbums(tag))
        }.map { it }
    }

    fun getTopArtist(tag:String): Flow<TopArtistResponse> {
        return flow {
            emit(networkService.getTopArtist(tag))
        }.map { it }
    }

    fun getTopTrack(tag:String): Flow<TopTrackResponse> {
        return flow {
            emit(networkService.getTopTrack(tag))
        }.map { it }
    }
}