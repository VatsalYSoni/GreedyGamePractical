package com.greedygamepractical.ui.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greedygamepractical.data.model.Toptags
import com.greedygamepractical.data.repository.GenreRepository
import com.greedygamepractical.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class GenreViewModel(private val genreRepository: GenreRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Toptags>>(UiState.Loading)

    val uiState: StateFlow<UiState<Toptags>> = _uiState

    init {
        fetchTopTags()
    }

    private fun fetchTopTags() {
        viewModelScope.launch {
            genreRepository.getTags()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}