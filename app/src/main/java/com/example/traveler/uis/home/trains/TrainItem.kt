package com.example.traveler.uis.home.trains

import com.example.traveler.R
import com.example.traveler.data.db.entities.Train
import com.example.traveler.databinding.ItemTrainBinding
import com.xwray.groupie.databinding.BindableItem

class TrainItem(
    private val train : Train
) : BindableItem<ItemTrainBinding>(){

    override fun getLayout() = R.layout.item_train
    override fun bind(viewBinding: ItemTrainBinding, position: Int) {
        viewBinding.setTrain(train)

    }

}
