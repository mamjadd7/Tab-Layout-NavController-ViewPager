package com.example.taskthreetabwithviewpager.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskthreetabwithviewpager.MediaActivity
import com.example.taskthreetabwithviewpager.adapters.VideoAdapter
import com.example.taskthreetabwithviewpager.databinding.FragmentVideoBinding
import com.example.taskthreetabwithviewpager.models.VideoModel

class VideoFragment : Fragment() {
    lateinit var videos: ArrayList<VideoModel>

    private val binding by lazy { FragmentVideoBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.ivBack.setOnClickListener {
            activity?.finish()
        }


        return binding.root
    }

    fun showVideo(context: Context) {
        videos = (context as MediaActivity).getAllVideos()
        binding.rvVideo.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVideo.setHasFixedSize(true)
        binding.rvVideo.setItemViewCacheSize(10)
        binding.rvVideo.adapter = VideoAdapter(requireActivity(), videos)

    }

//    fun getVideo() {
//        videos = (context as MediaActivity).getAllVideos()
//        binding.rvVideo.layoutManager = LinearLayoutManager(requireContext())
//        binding.rvVideo.setHasFixedSize(true)
//        binding.rvVideo.setItemViewCacheSize(10)
//        binding.rvVideo.adapter = VideoAdapter(requireActivity(), videos)
//    }
}