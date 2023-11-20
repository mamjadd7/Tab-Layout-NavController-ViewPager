package com.example.taskthreetabwithviewpager

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskthreetabwithviewpager.databinding.ActivityMediaBinding
import com.example.taskthreetabwithviewpager.fragments.VideoFragment
import com.example.taskthreetabwithviewpager.helperclasses.GetVideo
import com.example.taskthreetabwithviewpager.models.VideoModel


class MediaActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMediaBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        if (GetVideo().runTimePermission(this)) {
            binding.btnPermission.visibility = View.VISIBLE
        } else {
            getData()
            binding.btnPermission.visibility = View.GONE
        }


    }

    fun getAllVideos(): ArrayList<VideoModel> {
        return GetVideo().getAllVideos(this)
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getAllFolders(): ArrayList<FolderModel> {
//        return GetVideo().getAllFolder(this)
//    }



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
                getData()



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



    fun getData() {
        navController = this.findNavController(R.id.navHostFragment)
        binding.bottomNavView.setupWithNavController(navController)
        val navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment)
        val fragment = navHost!!.childFragmentManager.fragments[0] as VideoFragment
//        val fragmentFolder = navHost!!.childFragmentManager.fragments[1] as FolderFragment
        fragment.showVideo(this)
//        fragmentFolder.showFolder(this)
    }
}