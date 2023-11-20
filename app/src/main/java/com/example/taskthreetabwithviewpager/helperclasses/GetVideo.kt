package com.example.taskthreetabwithviewpager.helperclasses

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.taskthreetabwithviewpager.models.FolderModel
import com.example.taskthreetabwithviewpager.models.VideoModel
import java.io.File

class GetVideo {
    lateinit var videoList: ArrayList<VideoModel>

    fun getAllVideos(context: Context): ArrayList<VideoModel> {
        videoList = ArrayList()

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.BUCKET_ID,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME
        )
        val sortOrder = "${MediaStore.Video.Media._ID} ASC"

        val query = context.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )
        query?.use { cursor ->
            // Cache column indices.
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val durationColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            val pathColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            val folderIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID)
            val folderNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME)

            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val duration = cursor.getLong(durationColumn)
                val size = cursor.getInt(sizeColumn)
                val path = cursor.getString(pathColumn)
                val folderName = cursor.getString(folderNameColumn)


                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                videoList += VideoModel(id, name, duration, path, contentUri, folderName, size)

            }

        }
        return videoList
    }


    // check runtime permission
    fun runTimePermission(context: Context): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                100
            )
            return true
        } else {
//            Toast.makeText(context, "no need permission", Toast.LENGTH_SHORT).show()
//            binding.btnPermission.visibility = View.GONE
//            displayImages()
            return false
        }
    }


//code for folders
//    @RequiresApi(Build.VERSION_CODES.O)
//    @SuppressLint("Recycle", "Range")
//fun getAllFolder(context : Context): ArrayList<FolderModel>{
//        val tempList = ArrayList<VideoModel>()
//        val tempListString = ArrayList<String>()
//        val tempFolderList = ArrayList<FolderModel>()
//        val projection = arrayOf(
//            MediaStore.Video.Media.TITLE,
//            MediaStore.Video.Media.SIZE,
//            MediaStore.Video.Media._ID,
//            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
//            MediaStore.Video.Media.DATA,
//            MediaStore.Video.Media.DATE_ADDED,
//            MediaStore.Video.Media.DURATION,
//            MediaStore.Video.Media.BUCKET_ID)
//
//        val cursor = context?.contentResolver?.query(
//            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
//            projection, null, null, MediaStore.Video.Media.DATE_ADDED + " DESC")
//
//        if (cursor != null) {
//            if (cursor.moveToNext()){
//                do {
//                    val titleC = cursor.getString(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media.TITLE) })
//                    val idC = cursor.getLong(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media._ID) })
//                    val folderC = cursor.getString(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media.BUCKET_DISPLAY_NAME) })
//                    val folderIdC = cursor.getString(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media.BUCKET_ID) })
//                    val sizeC = cursor.getInt(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media.SIZE) })
//                    val pathC = cursor.getString(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media.DATA) })
//                    val durationC = cursor.getString(with(cursor) { getColumnIndex(android.provider.MediaStore.Video.Media.DURATION) }).toLong()
//
//                    try {
//                        val file = File(pathC)
//                        val artUriC = Uri.fromFile(file)
//                        val video = VideoModel(
//                            title = titleC, id = idC, folderName = folderC, duration = durationC, size = sizeC,
//                            path = pathC, artUri = artUriC)
//                        if (file.exists()) tempList.add(video)
//
//                        // for adding folder
//                        if (!tempListString.contains(folderC)){
//                            tempListString.add(folderC)
//                            tempFolderList.add(FolderModel(folderId = folderIdC , folderName = folderC))
//
//                        }
//
//                    }catch (e: Exception){
//
//                    }
//
//                }while (cursor.moveToNext())
//                cursor?.close()
//            }
//        }
//        return tempFolderList
//    }
}
