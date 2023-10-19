package com.example.traveler.uis.home.reservations

import com.example.traveler.R
import com.example.traveler.data.db.entities.ResevationWithTrainDetails
import com.example.traveler.databinding.ItemReservationBinding
import com.xwray.groupie.databinding.BindableItem

class ReservationItem(
    val reservation: ResevationWithTrainDetails
) : BindableItem<ItemReservationBinding>(){
// one reservation item class
    override fun getLayout() = R.layout.item_reservation
    override fun bind(viewBinding: ItemReservationBinding, position: Int) {
        viewBinding.setReservation(reservation)
    }

}