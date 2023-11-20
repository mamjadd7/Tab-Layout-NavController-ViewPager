package com.example.taskthreetabwithviewpager.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskthreetabwithviewpager.MediaActivity
import com.example.taskthreetabwithviewpager.adapters.FolderAdapter
import com.example.taskthreetabwithviewpager.adapters.VideoAdapter
import com.example.taskthreetabwithviewpager.databinding.FragmentFolderBinding
import com.example.taskthreetabwithviewpager.models.FolderModel
import com.example.taskthreetabwithviewpager.models.VideoModel
import java.io.File

class FolderFragment : Fragment() {
    private val binding by lazy { FragmentFolderBinding.inflate(layoutInflater) }
    lateinit var folder : ArrayList<FolderModel>
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }


        val getFolder = getAllFolder()
        binding.rvVideo.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVideo.setHasFixedSize(true)
        binding.rvVideo.setItemViewCacheSize(10)
        binding.rvVideo.adapter = FolderAdapter(requireContext(), getFolder)

        return binding.root
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun showFolder(context: Context) {
//        folder = (context as MediaActivity).getAllFolders()
//        binding.rvVideo.layoutManager = LinearLayoutManager(requireContext())
//        binding.rvVideo.setHasFixedSize(true)
//        binding.rvVideo.setItemViewCacheSize(10)
//        binding.rvVideo.adapter = FolderAdapter(requireActivity(), folder)
//
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("Recycle", "Range")
    private fun getAllFolder(): ArrayList<FolderModel>{
        val tempList = ArrayList<VideoModel>()
        val tempListString = ArrayList<String>()
        val tempFolderList = ArrayList<FolderModel>()
        val projection = arrayOf(
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.BUCKET_ID)

        val cursor = context?.contentResolver?.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection, null, null, MediaStore.Video.Media.DATE_ADDED + " DESC")

        if (cursor != null) {
            if (cursor.moveToNext()){
                do {
                    val titleC = cursor.getString(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media.TITLE) })
                    val idC = cursor.getLong(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media._ID) })
                    val folderC = cursor.getString(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media.BUCKET_DISPLAY_NAME) })
                    val folderIdC = cursor.getString(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media.BUCKET_ID) })
                    val sizeC = cursor.getInt(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media.SIZE) })
                    val pathC = cursor.getString(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media.DATA) })
                    val durationC = cursor.getString(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media.DURATION) }).toLong()

                    try {
                        val file = File(pathC)
                        val artUriC = Uri.fromFile(file)
                        val video = VideoModel(
                            title = titleC, id = idC, folderName = folderC, duration = durationC, size = sizeC,
                            path = pathC, artUri = artUriC)
                        if (file.exists()) tempList.add(video)

                        // for adding folder
                        if (!tempListString.contains(folderC)){
                            tempListString.add(folderC)
                            tempFolderList.add(FolderModel(folderId = folderIdC , folderName = folderC))

                        }

                    }catch (e: Exception){

                    }

                }while (cursor.moveToNext())
                cursor?.close()
            }
        }
        return tempFolderList
    }
}