package com.example.traveler.uis.home.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBindings
import com.example.traveler.R
import com.example.traveler.data.db.entities.User
import com.example.traveler.data.network.responses.AuthResponse
import com.example.traveler.databinding.FragmentProfileBinding
import com.example.traveler.uis.auth.AuthListener
import com.example.traveler.util.hide
import com.example.traveler.util.show
import com.example.traveler.util.snackbar
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment() ,KodeinAware, AuthListener {
    override val kodein by kodein()
    private lateinit var viewModel: ProfileViewModel
    private val factory : ProfileViewModelFactory by instance()

    private lateinit var rootLayout: FrameLayout
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentProfileBinding = DataBindingUtil.inflate(inflater ,R.layout.fragment_profile , container,false )
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        viewModel.authListener = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        rootLayout = binding.rootLayout
        progressBar = binding.progressBar
        return binding.root

    }

    override fun onStarted() {
        progressBar.show()
    }

    override fun onSuccess(user: User?) {
        progressBar.hide()
        rootLayout.snackbar("${user?.name} Updated!")
    }

    override fun onFailure(message: String) {
        progressBar.hide()
        rootLayout.snackbar(message)
    }

}