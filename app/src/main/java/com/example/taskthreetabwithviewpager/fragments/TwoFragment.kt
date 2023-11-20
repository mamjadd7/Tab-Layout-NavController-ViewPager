package com.example.taskthreetabwithviewpager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskthreetabwithviewpager.MainActivity
import com.example.taskthreetabwithviewpager.R
import com.example.taskthreetabwithviewpager.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    private val binding by lazy { FragmentTwoBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.btnRegister.setOnClickListener {
            setRegisterData()
        }

        return binding.root
    }


    private fun setRegisterData() {
        val name = binding.etName.text.toString()
        val userName = binding.etUserNamef2.text.toString()
        val password = binding.etPasswordf2.text.toString()

        (activity as MainActivity).setRegisterData(name, userName, password)
    }

}