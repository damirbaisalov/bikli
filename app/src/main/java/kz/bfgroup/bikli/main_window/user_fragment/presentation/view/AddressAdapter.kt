package kz.bfgroup.bikli.main_window.user_fragment.presentation.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseAddressBody

class AddressAdapter(
    private val addressClickListener: AddressClickListener
): RecyclerView.Adapter<AddressViewHolder>() {

    private val dataList: MutableList<ResponseAddressBody> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {

        return AddressViewHolder(
            View.inflate(parent.context, R.layout.address_card, null),
            addressClickListener
        )
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    fun setList(responseAddressApiData: List<ResponseAddressBody>) {
        dataList.clear()
        dataList.addAll(responseAddressApiData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = dataList.size
}