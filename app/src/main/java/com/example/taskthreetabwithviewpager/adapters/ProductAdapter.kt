package com.example.taskthreetabwithviewpager.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskthreetabwithviewpager.R
import com.example.taskthreetabwithviewpager.databinding.ProductLayoutBinding
import com.example.taskthreetabwithviewpager.models.ProductModel

class ProductAdapter(private val context: Context, private val productList: ArrayList<ProductModel>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(binding: ProductLayoutBinding): RecyclerView.ViewHolder(binding.root){
        val img = binding.ivProduct
        val title = binding.tvProductTitle
        val desc = binding.tvProductDesc
        val price = binding.tvProductPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val rootView = ProductViewHolder(ProductLayoutBinding.inflate(LayoutInflater.from(context), parent, false))

        return rootView
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val products = productList[position]

        holder.img.setImageResource(R.drawable.android_party)
        holder.title.text = products.productTitle
        holder.desc.text = products.productDesc
        holder.price.text = "$ "+products.productPrice

    }
}