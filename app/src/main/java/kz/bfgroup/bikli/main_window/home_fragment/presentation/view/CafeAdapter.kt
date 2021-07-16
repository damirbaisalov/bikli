package kz.bfgroup.bikli.main_window.home_fragment.presentation.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.home_fragment.models.CafeApiData

class CafeAdapter: RecyclerView.Adapter<CafeViewHolder>() {

    private var dataList: MutableList<CafeApiData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {

        return CafeViewHolder(View.inflate(parent.context, R.layout.cafe_items, null))
    }

    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun setList(cafeApiDataList: List<CafeApiData>) {
        dataList.clear()
        dataList.addAll(cafeApiDataList)
        notifyDataSetChanged()
    }

    fun clearAll() {
        (dataList as? ArrayList<CafeApiData>)?.clear()
        notifyDataSetChanged()
    }

    fun filter(name: String) {
        val temp : MutableList<CafeApiData> = mutableListOf()
        for (d in dataList) {
            if (d.name?.lowercase()?.contains(name)!!) {
                temp.add(d)
            }
        }
        updateList(temp)
    }

    private fun updateList(list : List<CafeApiData>) {
        dataList = list as MutableList<CafeApiData>
        notifyDataSetChanged()
    }

}