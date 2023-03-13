package com.greedygamepractical.ui.genreDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greedygamepractical.data.model.Album
import com.greedygamepractical.databinding.AlbumLayoutBinding

class AlbumAdapter(private val album: ArrayList<Album>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    class ViewHolder(val binding: AlbumLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AlbumLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val album = album[position]
        holder.binding.tvTitle.text = album.name
        holder.binding.tvArtist.text = album.artist.name
        Glide.with(holder.binding.ivImage).load(album.image[album.image.size - 1].text)
            .into(holder.binding.ivImage)
    }

    override fun getItemCount(): Int {
        return album.size
    }

}