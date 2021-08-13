package kz.bfgroup.bikli.main_window.home_fragment.presentation.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.home_fragment.models.CafeMenuApiData

class CafeMenuViewHolder(
    itemView: View,
    private val productClickListener: ProductClickListener
): RecyclerView.ViewHolder(itemView) {

    private val menuItemConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.menu_item_layout)
    private val menuNameEdaTextView: TextView = itemView.findViewById(R.id.menu_name_eda)
    private val menuCenaEdaTextView: TextView = itemView.findViewById(R.id.menu_cena_eda)
    private val menuImageImageView: ImageView = itemView.findViewById(R.id.menu_image)

    fun onBind(cafeMenuApiData: CafeMenuApiData) {
        val cena = "Цена "
        val tenge = " ₸"
        menuNameEdaTextView.text = cafeMenuApiData.nameeda
        menuCenaEdaTextView.text = (cena+cafeMenuApiData.cenaeda+tenge)

        Glide
            .with(itemView.context)
            .load("http://bikli.kz/imgProduct/" + cafeMenuApiData.image)
            .into(menuImageImageView)

        menuImageImageView.setOnClickListener {
            productClickListener.onProductClick(cafeMenuApiData.id)
        }
    }
}