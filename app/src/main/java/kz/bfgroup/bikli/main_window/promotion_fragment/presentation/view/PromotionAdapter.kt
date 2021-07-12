package kz.bfgroup.bikli.main_window.promotion_fragment.presentation.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.promotion_fragment.models.PromotionApiData

class PromotionAdapter : RecyclerView.Adapter<PromotionViewHolder>() {

    private val dataList: MutableList<PromotionApiData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionViewHolder {

        return PromotionViewHolder(View.inflate(parent.context, R.layout.promotion_items_list, null))
    }

    override fun onBindViewHolder(holder: PromotionViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun setList(promotionApiDataList: List<PromotionApiData>) {
        dataList.clear()
        dataList.addAll(promotionApiDataList)
        notifyDataSetChanged()
    }

    fun clearAll() {
        (dataList as? ArrayList<PromotionApiData>)?.clear()
        notifyDataSetChanged()
    }

}