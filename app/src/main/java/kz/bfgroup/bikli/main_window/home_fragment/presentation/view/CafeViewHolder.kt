package kz.bfgroup.bikli.main_window.home_fragment.presentation.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.home_fragment.models.CafeApiData
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder
import java.util.*

class CafeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val cafeItemLayout: ConstraintLayout = itemView.findViewById(R.id.cafe_item_constraint_layout)

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


//      val formatter = DateTimeFormatter.ofPattern("yy-mm-dd HH:mm:ss")

        val cafeEndTime = cafeApiData.timeend?.substring(11,19)
        val cafeNowTime = cafeApiData.now?.substring(11,19)

//        val cafeEndTimeParsed = ZonedDateTime.parse(cafeEndTime, formatter)
//        val cafeNowTimeParsed = ZonedDateTime.parse(cafeNowTime, formatter)

        if (cafeApiData.status.equals("close")) {
                changeCardState()
        }

    }

    private fun changeCardState() {
        cafeItemLayout.setBackgroundResource(R.drawable.bg_cafe_item_closed)
//      ranchEvent.alpha = 0.5F - alternative background
//      eventState.alpha= 1F
    }

//    private fun isCafeClosed(cafeEndTime : String?, cafeNowTime : String?): Boolean {
//        val timeList: List<String> = cafeEndTime?.split(":")!!
//        val timeNowList: List<String> = cafeNowTime?.split(":")!!
//
//        val hour = timeList[0].trim().toInt()
//        val min = timeList[1].trim().toInt()
//
//        val nowHour = timeNowList[0].trim().toInt()
//        val nowMin = timeNowList[0].trim().toInt()
//
////        val currentTime = Calendar.getInstance()
////        val currentHour: Int = currentTime.get(Calendar.HOUR_OF_DAY)
////        val currentMinutes: Int = currentTime.get(Calendar.MINUTE)
//
//        return (nowHour>=hour && nowMin>=min)
//    }
}