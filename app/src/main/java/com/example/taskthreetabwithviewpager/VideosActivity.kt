package com.example.taskthreetabwithviewpager

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskthreetabwithviewpager.adapters.VideoAdapter
import com.example.taskthreetabwithviewpager.databinding.ActivityVideosBinding
import com.example.taskthreetabwithviewpager.helperclasses.GetVideo
import com.example.taskthreetabwithviewpager.models.VideoModel

class VideosActivity : AppCompatActivity() {
    lateinit var videoList: ArrayList<VideoModel>
    private val binding by lazy { ActivityVideosBinding.inflate(layoutInflater) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.ivBack.setOnClickListener { finish() }


        // call run time permission
        if (GetVideo().runTimePermission(this)) {
//            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show()
        }
        else{
            binding.btnPermission.visibility = View.GONE
            displayVideo()
        }



    }

    private fun displayVideo() {

        val videos = GetVideo().getAllVideos(this)
        binding.rvVideo.setHasFixedSize(true)
        binding.rvVideo.setItemViewCacheSize(10)
        binding.rvVideo.layoutManager = LinearLayoutManager(this)
        binding.rvVideo.adapter = VideoAdapter(this@VideosActivity, videos)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                binding.btnPermission.visibility = View.GONE
                videoList = GetVideo().getAllVideos(this)
            } else {
                binding.btnPermission.visibility = View.VISIBLE
                binding.btnPermission.setOnClickListener {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        100
                    )
                }
            }
        }
    }
}

//    fun getAllVideos() : ArrayList<VideoModel> {
//        videoList = ArrayList()
////        videoList.add(VideoModel("1", "2", "30:00:00", "4", "5"))
////        videoList.add(VideoModel("1", "2", "30:00", "4", "5"))
////        videoList.add(VideoModel("1", "2", "3", "4", "5"))
////        videoList.add(VideoModel("1", "2", "3", "4", "5"))
//
//        val projection = arrayOf(
//            MediaStore.Video.Media._ID,
//            MediaStore.Video.Media.DISPLAY_NAME,
//            MediaStore.Video.Media.DURATION,
//            MediaStore.Video.Media.SIZE,
//            MediaStore.Video.Media.DATA
//        )
//        val sortOrder = "${MediaStore.Video.Media._ID} ASC"
//
//        val query = this.contentResolver.query(
//            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
//            projection,
//            null,
//            null,
//            sortOrder
//        )
//        query?.use { cursor ->
//            // Cache column indices.
//            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
//            val nameColumn =
//                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
//            val durationColumn =
//                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
//            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
//            val pathColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
//
//            while (cursor.moveToNext()) {
//                // Get values of columns for a given video.
//                val id = cursor.getLong(idColumn)
//                val name = cursor.getString(nameColumn)
//                val duration = cursor.getInt(durationColumn)
//                val size = cursor.getInt(sizeColumn)
//                val path = cursor.getString(pathColumn)
//
//                val contentUri: Uri = ContentUris.withAppendedId(
//                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
//                    id
//                )
//
//                // Stores column values and the contentUri in a local object
//                // that represents the media file.
//                videoList += VideoModel(name, duration.toString(), path, contentUri)
//            }
//
//        }
//        return videoList
//    }