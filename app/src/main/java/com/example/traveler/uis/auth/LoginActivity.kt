package com.example.traveler.uis.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.traveler.R
import com.example.traveler.data.network.responses.AuthResponse
import com.example.traveler.databinding.ActivityLoginBinding
import com.example.traveler.util.toast

class MainActivity : ComponentActivity(),AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingAuth : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        bindingAuth.viewmodel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
        toast("Loggin Started")
    }

    override fun onSuccess(user: AuthResponse?) {
       toast("${user?.nic} is Logged in")
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}