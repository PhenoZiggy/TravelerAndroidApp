package com.example.traveler.uis.home.trains

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.traveler.R

class TrainFragment : Fragment() {

    companion object {
        fun newInstance() = TrainFragment()
    }

    private lateinit var viewModel: TrainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_train, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TrainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}