package com.example.taskthreetabwithviewpager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskthreetabwithviewpager.MainActivity
import com.example.taskthreetabwithviewpager.R
import com.example.taskthreetabwithviewpager.adapters.ProductAdapter
import com.example.taskthreetabwithviewpager.databinding.FragmentThreeBinding
import com.example.taskthreetabwithviewpager.models.ProductModel


class ThreeFragment : Fragment() {
    private val binding by lazy { FragmentThreeBinding.inflate(layoutInflater) }
    lateinit var tempList : ArrayList<ProductModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        tempList = ArrayList()

        tempList.add(ProductModel(R.drawable.android_party, "laptop", "use for coding and development", 4000.0))
        tempList.add(ProductModel(R.drawable.android_party, "phone", "use for testing app", 3200.0))
        tempList.add(ProductModel(R.drawable.android_party, "cable", "use for change phone", 200.0))
        tempList.add(ProductModel(R.drawable.android_party, "cup", "use for tea", 100.0))
        tempList.add(ProductModel(R.drawable.android_party, "mouse", "use for move the cursor", 300.0))
        tempList.add(ProductModel(R.drawable.android_party, "mouse", "use for move the cursor", 300.0))
        tempList.add(ProductModel(R.drawable.android_party, "mouse", "use for move the cursor", 300.0))
        tempList.add(ProductModel(R.drawable.android_party, "mouse", "use for move the cursor", 300.0))
        tempList.add(ProductModel(R.drawable.android_party, "mouse", "use for move the cursor", 300.0))

        binding.rvProductList.setHasFixedSize(true)
        binding.rvProductList.setItemViewCacheSize(10)
        binding.rvProductList.layoutManager = LinearLayoutManager(context)
        binding.rvProductList.adapter = ProductAdapter(requireContext(), tempList)

        return binding.root
    }

    fun getData(userName: String, password: String) {


    }
}