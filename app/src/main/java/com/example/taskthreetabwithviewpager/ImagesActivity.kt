package com.example.taskthreetabwithviewpager

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.taskthreetabwithviewpager.adapters.ImageAdapter
import com.example.taskthreetabwithviewpager.databinding.ActivityImagesBinding
import com.example.taskthreetabwithviewpager.helperclasses.GetVideo
import com.example.taskthreetabwithviewpager.models.ImageModel

class ImagesActivity : AppCompatActivity() {
    private val binding by lazy { ActivityImagesBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener { finish() }

        // call run time permission
        val permission = GetVideo().runTimePermission(this)
        if (permission == true) {
            binding.btnPermission.visibility = View.VISIBLE
        } else {
            binding.btnPermission.visibility = View.GONE
            displayImages()
        }
    }


    // getImages from gallery
    private fun displayImages() {
        // Now you can use the MediaStore to query images
        val images = getImagesFromMediaStore()
        // Display images as needed
        // For example, use RecyclerView and an adapter
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvImages.layoutManager = layoutManager
        binding.rvImages.adapter = ImageAdapter(this@ImagesActivity, images)
    }

    // Implement getImagesFromMediaStore function to retrieve image data
    private fun getImagesFromMediaStore(): ArrayList<ImageModel> {
        // Use a ContentResolver to query the images
        // For simplicity, let's assume you want to get all images in this example
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )
        val images = ArrayList<ImageModel>()
        cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            while (it.moveToNext()) {
                val imagePath = it.getString(columnIndex)
                val imageModel = ImageModel(imagePath)
                images.add(imageModel)
            }
        }

        return images
    }


    // check runtime permission
//    private fun runTimePermission() {
//        if (ActivityCompat.checkSelfPermission(this@ImagesActivity,
//            WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE), 100)
//        }
//        else{
//            Toast.makeText(this@ImagesActivity, "no need permission", Toast.LENGTH_SHORT).show()
//            binding.btnPermission.visibility = View.GONE
//            displayImages()
//        }
//    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@ImagesActivity, "Permission Granted", Toast.LENGTH_SHORT).show()
                binding.btnPermission.visibility = View.GONE
                displayImages()
            } else {
                binding.btnPermission.visibility = View.VISIBLE
                binding.btnPermission.setOnClickListener {
                    ActivityCompat.requestPermissions(
                        this@ImagesActivity,
                        arrayOf(WRITE_EXTERNAL_STORAGE),
                        100
                    )
                }
            }
        }

    }


}