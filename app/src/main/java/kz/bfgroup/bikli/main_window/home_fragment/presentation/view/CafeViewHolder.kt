package kz.bfgroup.bikli.main_window.home_fragment.presentation.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.home_fragment.models.CafeApiData

class CafeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val imageCafeImageView: ImageView = itemView.findViewById(R.id.cafe_logo)
    private val cafeNameTextView: TextView = itemView.findViewById(R.id.cafe_name)
    private val cafeMinSumTextView: TextView =
        itemView.findViewById(R.id.cafe_min_sum)
    private val cafeRaitingTextView: TextView = itemView.findViewById(R.id.cafe_raiting)
    private val cafeTimeDeliveryTextView: TextView = itemView.findViewById(R.id.cafe_delivery_time)

    fun onBind(cafeApiData: CafeApiData) {

        cafeNameTextView.text = cafeApiData.name
        val minSumText = "от " + cafeApiData.minsumma
        cafeMinSumTextView.text = minSumText

        cafeRaitingTextView.text = cafeApiData.rating.toString()
        val deliveryTimeText = cafeApiData.vremya + " мин"
        cafeTimeDeliveryTextView.text = deliveryTimeText

        Glide
            .with(itemView.context)
            .load("http://bikli.kz/LogoCafe/" + cafeApiData.logo)
            .into(imageCafeImageView)
    }

}