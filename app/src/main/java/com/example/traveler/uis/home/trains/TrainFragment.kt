package com.example.traveler.uis.home.trains

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
import com.example.traveler.data.db.entities.Train
import com.example.traveler.util.Coroutines
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class TrainFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val factory : TrainViewModelFactory by instance()

    private lateinit var viewModel: TrainViewModel
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_train, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(TrainViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        viewModel.trains.await().observe(viewLifecycleOwner, Observer {
            initRecyclerView(it.toTrainItem(viewModel))
        })
    }

    private fun initRecyclerView(toTrainItem: List<TrainItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(toTrainItem)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun List<Train>.toTrainItem(viewModel: TrainViewModel): List<TrainItem> {
        return this.map { train ->
            TrainItem(train, viewModel)
        }
    }


}