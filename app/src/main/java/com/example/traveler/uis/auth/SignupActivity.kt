package com.example.traveler.uis.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.traveler.R
import com.example.traveler.data.db.entities.User
import com.example.traveler.data.network.responses.AuthResponse
import com.example.traveler.databinding.ActivitySignupBinding
import com.example.traveler.uis.home.HomeActivity
import com.example.traveler.util.hide
import com.example.traveler.util.show
import com.example.traveler.util.snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : ComponentActivity(), AuthListener, KodeinAware {
    override val kodein by kodein()
    private val factory :AuthViewModelFactory by instance()

    private lateinit var rootLayout: CoordinatorLayout
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingAuth : ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        bindingAuth.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedUser().observe(this, Observer  { user->
            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    // need to start as a fresh activity
                    //if user press back button user can see the login again
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        // Initialize views after setContentView
        rootLayout = findViewById(R.id.root_layout)
        progressBar = findViewById(R.id.progress_bar)
    }

    override fun onStarted() {
        progressBar.show()
    }
    override fun onSuccess(user: User?) {
        progressBar.hide()
        rootLayout.snackbar("${user?.nic} is Logged in")
    }

    override fun onFailure(message: String) {
        progressBar.hide()
        rootLayout.snackbar(message)
    }
}