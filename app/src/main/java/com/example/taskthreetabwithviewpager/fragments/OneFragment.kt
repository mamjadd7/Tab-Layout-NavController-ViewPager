package com.example.taskthreetabwithviewpager.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.taskthreetabwithviewpager.MainActivity
import com.example.taskthreetabwithviewpager.R
import com.example.taskthreetabwithviewpager.databinding.FragmentOneBinding


class OneFragment : Fragment() {
    private val binding by lazy { FragmentOneBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.btnSignIn.setOnClickListener {
            signIn()

        }
        binding.btnSignUp.setOnClickListener {
            signUp()
        }
        return binding.root
    }
    private fun signIn() {
        val userName = binding.etUserName.text.toString()
        val password = binding.etPassword.text.toString()

        (activity as MainActivity).setSignInData(userName, password)
        binding.etUserName.setText("")
        binding.etPassword.setText("")

    }
    private fun signUp() {
        (activity as MainActivity).signUpForm()
    }

    fun getUserDetail(name: String,userName: String,  password: String) {

        binding.etUserName.setText(userName)
        binding.etPassword.setText(password)

    }
}