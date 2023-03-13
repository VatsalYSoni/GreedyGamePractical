package com.greedygamepractical.ui.genreDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greedygamepractical.data.model.Artist
import com.greedygamepractical.databinding.ArtistLayoutBinding

class ArtistAdapter(private val artist: ArrayList<Artist>) :
    RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    class ViewHolder(val binding: ArtistLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ArtistLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artist = artist[position]

        holder.binding.tvName.text = artist.name
        Glide.with(holder.binding.ivImage).load(artist.image[artist.image.size - 1].text)
            .into(holder.binding.ivImage)

    }

    override fun getItemCount(): Int {
        return artist.size
    }
}