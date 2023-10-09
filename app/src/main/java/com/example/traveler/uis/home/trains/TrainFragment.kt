package com.example.traveler.uis.home.trains

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.traveler.R
import com.example.traveler.util.Coroutines
import com.example.traveler.util.toast
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class TrainFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val factory : TrainViewModelFactory by instance()

    private lateinit var viewModel: TrainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_train, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(TrainViewModel::class.java)
        Coroutines.main {
           val trains =  viewModel.trains.await()
            trains.observe(viewLifecycleOwner, Observer {
                context?.toast(it.size.toString())
            })
        }
    }
}