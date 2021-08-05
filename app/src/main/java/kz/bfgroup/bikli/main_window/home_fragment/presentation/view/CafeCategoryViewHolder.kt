package kz.bfgroup.bikli.main_window.home_fragment.presentation.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.home_fragment.models.CafeCategoryApiData

class CafeCategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val categoryNameTextView : TextView = itemView.findViewById(R.id.category_item_category_name)

    fun onBind(cafeCategoryApiData: CafeCategoryApiData) {

        categoryNameTextView.text = cafeCategoryApiData.namecategory
    }
}