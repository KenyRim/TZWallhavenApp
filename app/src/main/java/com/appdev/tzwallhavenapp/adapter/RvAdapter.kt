package com.appdev.tzwallhavenapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.appdev.tzwallhavenapp.R
import com.appdev.tzwallhavenapp.listener.ClickListener
import com.bumptech.glide.Glide

class RvAdapter(var items:List<Item>, val callback: ClickListener.Click):RecyclerView.Adapter <RvAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

       private val photo: ImageView = itemView.findViewById(R.id.movie_photo)

        fun bind(item: Item){

            val imageSmall = Item(
                item.imageUrl.toString()
            ).small

            Glide.with(itemView.context).load(imageSmall).into(photo)

            itemView.setOnClickListener {

                if (adapterPosition != RecyclerView.NO_POSITION) {
                    callback.onItemClicked(items[adapterPosition],
                        adapterPosition,item.originalImageUrl.toString(),photo.drawable)
                }
            }
        }

    }

}