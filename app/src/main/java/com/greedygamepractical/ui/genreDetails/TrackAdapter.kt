package com.greedygamepractical.ui.genreDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greedygamepractical.data.model.Track
import com.greedygamepractical.databinding.TrackLayoutBinding

class TrackAdapter(private val track: ArrayList<Track>) :
    RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    class ViewHolder(val binder: TrackLayoutBinding) : RecyclerView.ViewHolder(binder.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TrackLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track = track[position]

        holder.binder.tvTitle.text = track.name
        holder.binder.tvArtist.text = track.artist.name
        Glide.with(holder.binder.ivImage).load(track.image[track.image.size - 1].text)
            .into(holder.binder.ivImage)
    }

    override fun getItemCount(): Int {
        return track.size
    }
}