package com.greedygamepractical.data.api

import com.greedygamepractical.data.model.*
import com.greedygamepractical.utils.AppConstant
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("/2.0")
    suspend fun getTopTags(
        @Query("method") method: String = AppConstant.METHOD_GET_TOP_TAGS,
        @Query("api_key") apiKey: String = AppConstant.API_KEY1,
        @Query("format") format: String = AppConstant.FORMATE
    ): TopTagResponse

    @GET("/2.0")
    suspend fun getTagInfo(
        @Query("tag") tag: String,
        @Query("method") method: String = AppConstant.METHOD_GET_INFO,
        @Query("api_key") apiKey: String = AppConstant.API_KEY1,
        @Query("format") format: String = AppConstant.FORMATE
    ): TagInfoResponse

    @GET("/2.0")
    suspend fun getTopAlbums(
        @Query("tag") tag: String,
        @Query("method") method: String = AppConstant.METHOD_GET_TOP_ALBUMS,
        @Query("api_key") apiKey: String = AppConstant.API_KEY1,
        @Query("format") format: String = AppConstant.FORMATE
    ): TopAlbumResponse

    @GET("/2.0")
    suspend fun getTopArtist(
        @Query("tag") tag: String,
        @Query("method") method: String = AppConstant.METHOD_GET_TOP_ARTISTS,
        @Query("api_key") apiKey: String = AppConstant.API_KEY1,
        @Query("format") format: String = AppConstant.FORMATE
    ): TopArtistResponse

    @GET("/2.0")
    suspend fun getTopTrack(
        @Query("tag") tag: String,
        @Query("method") method: String = AppConstant.METHOD_GET_TOP_TRACKS,
        @Query("api_key") apiKey: String = AppConstant.API_KEY1,
        @Query("format") format: String = AppConstant.FORMATE
    ): TopTrackResponse


}