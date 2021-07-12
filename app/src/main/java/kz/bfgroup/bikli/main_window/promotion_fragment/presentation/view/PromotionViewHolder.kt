package kz.bfgroup.bikli.main_window.promotion_fragment.presentation.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.promotion_fragment.models.PromotionApiData

class PromotionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

//        private val promotionItemLinearLayout: LinearLayout =
//            itemView.findViewById(R.id.promotion_item_linear_layout)

        private val imagePromotionImageView: ImageView = itemView.findViewById(R.id.promotion_image)
        private val promotionNameTextView: TextView = itemView.findViewById(R.id.promotion_name)
        private val promotionDescriptionTextView: TextView =
            itemView.findViewById(R.id.promotion_description)

    fun onBind(promotionApiData: PromotionApiData) {

        promotionNameTextView.text = promotionApiData.name
        promotionDescriptionTextView.text = promotionApiData.opis

            Glide
                .with(itemView.context)
                .load("http://bikli.kz/LogoShares/" + promotionApiData.img)
                .into(imagePromotionImageView)

    }
}