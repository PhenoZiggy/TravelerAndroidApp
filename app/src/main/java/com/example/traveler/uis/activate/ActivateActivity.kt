package com.example.traveler.uis.activate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.traveler.R
import com.example.traveler.data.db.entities.User
import com.example.traveler.databinding.ActivityActivateBinding
import com.example.traveler.databinding.ActivityLoginBinding
import com.example.traveler.databinding.ActivitySignupBinding
import com.example.traveler.uis.auth.AuthListener
import com.example.traveler.uis.auth.AuthViewModel
import com.example.traveler.uis.home.profile.ActivateViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ActivateActivity : ComponentActivity(), AuthListener, KodeinAware {
    override val kodein by kodein()
    private val factory : ActivateViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityActivateBinding = DataBindingUtil.setContentView(this, R.layout.activity_activate)
        val viewModel = ViewModelProvider(this, factory).get(ActivateViewModel::class.java)
        binding.activate = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {

    }

    override fun onSuccess(user: User?) {

    }

    override fun onFailure(message: String) {

    }
}