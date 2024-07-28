package com.example.theweather.ui.clothes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theweather.R
import com.example.theweather.ui.clothes.models.ClothesTypeUI

class ClothesTypesRecyclerAdapter (private val types: List<ClothesTypeUI>) : RecyclerView
.Adapter<ClothesTypesRecyclerAdapter.ClothesTypesViewHolder>() {

    class ClothesTypesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.text_title)
        val imageView: ImageView = itemView.findViewById(R.id.imageModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesTypesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.li_clothes_type_item, parent, false)
        return ClothesTypesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClothesTypesViewHolder, position: Int) {
        holder.titleTextView.text = types[position].title
        holder.imageView.setImageResource(types[position].imageResId)
    }

    override fun getItemCount() = types.size
}