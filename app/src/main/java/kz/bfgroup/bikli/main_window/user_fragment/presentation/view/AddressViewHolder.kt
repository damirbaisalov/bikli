package kz.bfgroup.bikli.main_window.user_fragment.presentation.view

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseAddressBody

class AddressViewHolder(
    itemView: View,
    private val addressClickListener: AddressClickListener
): RecyclerView.ViewHolder(itemView) {

    private val addressCardLayout: LinearLayout = itemView.findViewById(R.id.address_card_linear_layout)

    private val addressName: TextView = itemView.findViewById(R.id.address_card_address_name)
    private val home: TextView = itemView.findViewById(R.id.address_card_home)

    fun onBind(addressApiData: ResponseAddressBody) {

        addressName.text = addressApiData.adr
        home.text = addressApiData.street

        addressCardLayout.setOnClickListener {
            addressClickListener.onAddressClick(addressApiData.id)
        }
    }
}