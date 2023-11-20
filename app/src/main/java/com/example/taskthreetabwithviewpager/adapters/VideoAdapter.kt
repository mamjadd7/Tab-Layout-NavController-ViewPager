package com.example.taskthreetabwithviewpager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskthreetabwithviewpager.R
import com.example.taskthreetabwithviewpager.databinding.VideoLayoutBinding
import com.example.taskthreetabwithviewpager.models.VideoModel

class VideoAdapter(private val context: Context, private val videoList: ArrayList<VideoModel>):
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(val binding: VideoLayoutBinding): RecyclerView.ViewHolder(binding.root){
        val videoTitle = binding.tvVideoTitle
        val videoDesc = binding.tvDuration

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val rootView = VideoViewHolder(VideoLayoutBinding.inflate(LayoutInflater.from(context), parent, false))

        return rootView
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videos = videoList[position]

        holder.videoTitle.text = videos.title

        val dur = videos.duration.toLong()
        val second = (dur.div(1000).rem(60))
        val minutes = (dur.div(1000*60).rem(60))
        val hour = (dur.div(1000*60*60))

        if (dur >= 3600000.0){
            val durations = String.format("%02d:%02d:%02d", hour, minutes, second)
            holder.binding.tvDuration.text = durations
        }else {
            val durations = String.format("%02d:%02d", minutes, second)
            holder.binding.tvDuration.text = durations
        }


        Glide
            .with(context)
            .load(videos.artUri)
            .centerCrop()
            .placeholder(R.drawable.all_videos_icon)
            .into(holder.binding.ivVideo)

    }
}