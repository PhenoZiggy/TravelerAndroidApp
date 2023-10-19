package com.example.traveler.uis.home.reservations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveler.R
import com.example.traveler.data.db.entities.ResevationWithTrainDetails
import com.example.traveler.util.Coroutines
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ReservationFragment : Fragment() , KodeinAware{
    override val kodein by kodein()
    private val factory : ReservationViewModelFactory by instance()

    private lateinit var viewModel: ReservationViewModel
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reservation, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewReserve)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this , factory).get(ReservationViewModel::class.java)
//        Coroutines.main {
//            val reservations = viewModel.reservation.await()
//            reservations.observe(viewLifecycleOwner, Observer {
//                context?.toast(it.size.toString())
//            })
//        }
        bindUI()
        }
    //call reservation from repository
    private fun bindUI() = Coroutines.main {
        viewModel.reservation.await().observe(viewLifecycleOwner, Observer {
            initRecyclerView(it.toReservationItem())
        })
    }

    //bind reservation layout
    private fun initRecyclerView(toReservationItem: List<ReservationItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(toReservationItem)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    //MAP reservations to ReservationItem view
    private fun List<ResevationWithTrainDetails>.toReservationItem() : List<ReservationItem>{
        return this.map {
            ReservationItem(it)
        }
    }
}
