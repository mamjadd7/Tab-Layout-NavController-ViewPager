package com.example.taskthreetabwithviewpager.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskthreetabwithviewpager.R
import com.example.taskthreetabwithviewpager.databinding.ImagesLayoutBinding
import com.example.taskthreetabwithviewpager.models.ImageModel

class ImageAdapter(private val context: Context, private val imageList: ArrayList<ImageModel>): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: ImagesLayoutBinding): RecyclerView.ViewHolder(binding.root){
        val imgPath = binding.ivImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ImagesLayoutBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageLists = imageList[position]



        Glide
            .with(holder.itemView.context)
            .load(imageLists.imagePath)
            .centerCrop()
            .placeholder(R.drawable.about_icon)
            .into(holder.imgPath)
    }
}