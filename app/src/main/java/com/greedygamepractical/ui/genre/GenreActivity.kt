package com.greedygamepractical.ui.genre

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.chip.Chip
import com.greedygamepractical.GreedyGamePractical
import com.greedygamepractical.R
import com.greedygamepractical.data.model.Toptags
import com.greedygamepractical.databinding.ActivityGenreBinding
import com.greedygamepractical.di.component.DaggerActivityComponent
import com.greedygamepractical.di.module.ActivityModule
import com.greedygamepractical.ui.base.UiState
import com.greedygamepractical.ui.genreDetails.GenreDetailsActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class GenreActivity : AppCompatActivity() {

    @Inject
    lateinit var genreViewModel: GenreViewModel

    private lateinit var binding: ActivityGenreBinding
    private var isExpended: Boolean = false
    private lateinit var toptags: Toptags

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityGenreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObserver()
        binding.toggleLayout.setOnClickListener(listener)
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                genreViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            toptags = it.data
                            setUpChipGroup()
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@GenreActivity, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun setUpChipGroup() {
        if (isExpended) {
            binding.toggleIcon.setImageResource(R.drawable.ic_arrow_up_24)
        } else {
            binding.toggleIcon.setImageResource(R.drawable.ic_arrow_down_24)
        }

        binding.cgGenre.removeAllViews()
        for (i in 0 until toptags.tag.size) {
            if (i == 10 && !isExpended) {
                break
            }
            val tag = toptags.tag[i]
            val chip = Chip(this)
            chip.text = tag.name

            chip.setOnClickListener {
                val intent = Intent(this, GenreDetailsActivity::class.java)
                intent.putExtra("tag", tag.name)
                startActivity(intent)
            }
            binding.cgGenre.addView(chip)
        }
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as GreedyGamePractical).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

    private val listener = View.OnClickListener { view ->
        when (view.id) {
            R.id.toggleLayout -> {
                isExpended = !isExpended
                setUpChipGroup()
            }
        }
    }
}