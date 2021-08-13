package kz.bfgroup.bikli.main_window.home_fragment.presentation.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.home_fragment.models.CafeMenuApiData

class CafeMenuAdapter(
    private val productClickListener: ProductClickListener
): RecyclerView.Adapter<CafeMenuViewHolder>() {

    private var dataList: MutableList<CafeMenuApiData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeMenuViewHolder {
        return CafeMenuViewHolder(
            View.inflate(parent.context, R.layout.cafe_menu_item_list, null),
            productClickListener
        )
    }

    override fun onBindViewHolder(holder: CafeMenuViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun setList(cafeMenuApiDataList: List<CafeMenuApiData>) {
        dataList.clear()
        dataList.addAll(cafeMenuApiDataList)
        notifyDataSetChanged()
    }
}