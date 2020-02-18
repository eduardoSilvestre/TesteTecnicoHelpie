package com.eduardo.desafiotecnico.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.eduardo.desafiotecnico.R
import com.eduardo.desafiotecnico.model.Photos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photo_cell.view.*

class PhotosAdapter(val context: Context, var photos: List<Photos>): RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.photo_cell, parent, false)
        return PhotosViewHolder(layout)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    class PhotosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val url: ImageView = itemView.photoImageView

        fun bind(photo: Photos){

            Picasso.get().load(photo.photoUrl).into(url)
        }

    }

}