package com.greedygamepractical.ui.genreDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greedygamepractical.data.model.TagInfoResponseTag
import com.greedygamepractical.data.model.TopAlbumResponse
import com.greedygamepractical.data.model.TopArtistResponse
import com.greedygamepractical.data.model.TopTrackResponse
import com.greedygamepractical.data.repository.GenreDetailsRepository
import com.greedygamepractical.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class GenreDetailsViewModel(private val genreDetailsRepository: GenreDetailsRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<TagInfoResponseTag>>(UiState.Loading)
    val uiState: StateFlow<UiState<TagInfoResponseTag>> = _uiState

    private val _uiState_top_album = MutableStateFlow<UiState<TopAlbumResponse>>(UiState.Loading)
    val uiState_top_album: StateFlow<UiState<TopAlbumResponse>> = _uiState_top_album

    private val _uiState_top_artist = MutableStateFlow<UiState<TopArtistResponse>>(UiState.Loading)
    val uiState_top_artist: StateFlow<UiState<TopArtistResponse>> = _uiState_top_artist

    private val _uiState_top_track = MutableStateFlow<UiState<TopTrackResponse>>(UiState.Loading)
    val uiState_top_track: StateFlow<UiState<TopTrackResponse>> = _uiState_top_track

    var tag: String? = null

    fun setGenreTag(tag: String) {
        this.tag = tag
        fetchTagInfo()
        fetchTopAlbum()
        fetchTopArtist()
        fetchTopTrack()
    }

    private fun fetchTagInfo() {

        viewModelScope.launch {
            genreDetailsRepository.getTagInfo(tag!!)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    private fun fetchTopAlbum() {

        viewModelScope.launch {
            genreDetailsRepository.getTopAlbum(tag!!)
                .catch { e ->
                    _uiState_top_album.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState_top_album.value = UiState.Success(it)
                }
        }
    }

    private fun fetchTopArtist() {

        viewModelScope.launch {
            genreDetailsRepository.getTopArtist(tag!!)
                .catch { e ->
                    _uiState_top_artist.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState_top_artist.value = UiState.Success(it)
                }
        }
    }

    private fun fetchTopTrack() {

        viewModelScope.launch {
            genreDetailsRepository.getTopTrack(tag!!)
                .catch { e ->
                    _uiState_top_track.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState_top_track.value = UiState.Success(it)
                }
        }
    }
}