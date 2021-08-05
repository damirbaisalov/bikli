package kz.bfgroup.bikli.main_window.home_fragment.presentation.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.home_fragment.models.CafeCategoryApiData

class CafeCategoryAdapter: RecyclerView.Adapter<CafeCategoryViewHolder>()  {

    private var dataList: MutableList<CafeCategoryApiData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeCategoryViewHolder {
        return CafeCategoryViewHolder(View.inflate(parent.context, R.layout.category_item_list, null))
    }

    override fun onBindViewHolder(holder: CafeCategoryViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun setList(cafeCategoryApiDataList: List<CafeCategoryApiData>) {
        dataList.clear()
        dataList.addAll(cafeCategoryApiDataList)
        notifyDataSetChanged()
    }


}