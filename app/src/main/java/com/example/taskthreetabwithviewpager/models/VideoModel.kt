package com.example.taskthreetabwithviewpager.models

import android.net.Uri


data class VideoModel(
    val id: Long,
    val title: String,
    val duration: Long = 0,
    val path: String,
    val artUri: Uri,
    val folderName: String,
    val size: Int

//    val name: String,
//    val duration: String,
//    val size: Int,
//    val toString: Any
)

data class FolderModel(
    val folderId : String,
    val folderName : String
)

