package com.greedygamepractical.data.repository

import com.greedygamepractical.data.api.NetworkService
import com.greedygamepractical.data.model.Toptags
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepository @Inject constructor(private val networkService: NetworkService) {

//    fun getTopTags(): Call<TopTagResponse> {
//
//        lateinit var tagList: List<Tag>
//
//        val response = networkService.getTopTags()
//
//        response.enqueue(object : retrofit2.Callback<TopTagResponse>{
//            override fun onResponse(
//                call: Call<TopTagResponse>,
//                response: Response<TopTagResponse>
//            ) {
//                if (response.body() != null) {
//                    Log.e("response", response.body().toString())
//
//                    val topTags = (response.body())!!
//                    tagList = topTags.toptags.tag
//
//
//                }
//            }
//
//            override fun onFailure(call: Call<TopTagResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//
//
//        return networkService.getTopTags()
//    }


    fun getTags(): Flow<Toptags> {
        return flow {
            emit(networkService.getTopTags())
        }.map { it.toptags }
    }
}

