package com.example.taskthreetabwithviewpager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskthreetabwithviewpager.databinding.FolderLayoutBinding
import com.example.taskthreetabwithviewpager.models.FolderModel

class FolderAdapter(val context: Context, val folderList : ArrayList<FolderModel>): RecyclerView.Adapter<FolderAdapter.FolderViewHolder>(){


    inner class FolderViewHolder(binding: FolderLayoutBinding): RecyclerView.ViewHolder(binding.root){
        val folderName = binding.tvFolderTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = FolderViewHolder(FolderLayoutBinding.inflate(LayoutInflater.from(context), parent, false))

        return view
    }

    override fun getItemCount(): Int {
        return folderList.size
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        val folders = folderList[position]

        holder.folderName.text = folders.folderName
    }
}