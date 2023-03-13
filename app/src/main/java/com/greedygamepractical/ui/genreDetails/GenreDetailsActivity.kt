package com.greedygamepractical.ui.genreDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.greedygamepractical.GreedyGamePractical
import com.greedygamepractical.data.model.Album
import com.greedygamepractical.data.model.Artist
import com.greedygamepractical.data.model.Track
import com.greedygamepractical.databinding.ActivityGenreDetailsBinding
import com.greedygamepractical.di.component.DaggerActivityComponent
import com.greedygamepractical.di.module.ActivityModule
import com.greedygamepractical.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class GenreDetailsActivity : AppCompatActivity() {


    @Inject
    lateinit var genreDetailsViewModel: GenreDetailsViewModel

    lateinit var binding: ActivityGenreDetailsBinding
    lateinit var tagName: String

    var albums: ArrayList<Album> = ArrayList()
    var artists: ArrayList<Artist> = ArrayList()
    var tracks: ArrayList<Track> = ArrayList()
    private val albumAdapter: AlbumAdapter = AlbumAdapter(albums)
    private var artistAdapter: ArtistAdapter = ArtistAdapter(artists)
    private var trackAdapter: TrackAdapter = TrackAdapter(tracks)

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityGenreDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        setupObserver()
        getTopAlbum()
        getTopArtist()
        getTopTrack()
    }


    private fun setUpView() {
        tagName = intent.getStringExtra("tag")!!
        binding.tvTagTitle.text = tagName

        binding.rvTabs.layoutManager = GridLayoutManager(this, 2)
        binding.rvTabs.adapter = albumAdapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.text!! == "ALBUMS") {
                    binding.rvTabs.adapter = albumAdapter
                } else if (tab.text!! == "ARTISTS") {
                    binding.rvTabs.adapter = artistAdapter
                } else if (tab.text!! == "TRACKS") {
                    binding.rvTabs.adapter = trackAdapter
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setupObserver() {


        lifecycleScope.launch {

            this@GenreDetailsActivity.genreDetailsViewModel.setGenreTag(tagName)
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                genreDetailsViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvTagDescription.text = it.data.wiki.summary
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Log.e("Error", it.message)
                            Toast.makeText(this@GenreDetailsActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun getTopAlbum() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                genreDetailsViewModel.uiState_top_album.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            albums.addAll(it.data.albums.album)
                            albumAdapter.notifyDataSetChanged()
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Log.e("Error", it.message)
                            Toast.makeText(this@GenreDetailsActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun getTopArtist() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                genreDetailsViewModel.uiState_top_artist.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Log.e("Error", it.data.toString())
                            artists.addAll(it.data.topartists.artist)
                            artistAdapter.notifyDataSetChanged()
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Log.e("Error", it.message)
                            Toast.makeText(this@GenreDetailsActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun getTopTrack() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                genreDetailsViewModel.uiState_top_track.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Log.e("Error", it.data.toString())
                            tracks.addAll(it.data.tracks.track)
                            trackAdapter.notifyDataSetChanged()
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Log.e("Error", it.message)
                            Toast.makeText(this@GenreDetailsActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as GreedyGamePractical).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}