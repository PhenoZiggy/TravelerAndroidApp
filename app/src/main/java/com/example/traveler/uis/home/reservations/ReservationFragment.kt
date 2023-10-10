package com.example.traveler.uis.home.reservations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.traveler.R
import com.example.traveler.util.Coroutines
import com.example.traveler.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ReservationFragment : Fragment() , KodeinAware{
    override val kodein by kodein()
    private val factory : ReservationViewModelFactory by instance()

    private lateinit var viewModel: ReservationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reservation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , factory).get(ReservationViewModel::class.java)
        Coroutines.main {
            val reservations = viewModel.reservation.await()
            reservations.observe(viewLifecycleOwner, Observer {
                context?.toast(it.size.toString())
            })
        }

    }

}