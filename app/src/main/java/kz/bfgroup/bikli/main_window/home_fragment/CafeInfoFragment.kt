package kz.bfgroup.bikli.main_window.home_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.data.ApiRetrofit
import kz.bfgroup.bikli.main_window.home_fragment.models.*
import kz.bfgroup.bikli.main_window.home_fragment.presentation.view.CafeAdapter
import kz.bfgroup.bikli.main_window.home_fragment.presentation.view.CafeCategoryAdapter
import kz.bfgroup.bikli.main_window.home_fragment.presentation.view.CafeMenuAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CafeInfoFragment: Fragment() {

    private lateinit var rootView: View
    private lateinit var cafeCategoryRecyclerView: RecyclerView
    private var cafeCategoryAdapter: CafeCategoryAdapter?=null
    private lateinit var cafeMenuRecyclerView: RecyclerView
    private var cafeMenuAdapter: CafeMenuAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_cafe_info, container, false)
        initViews()

        loadCategoryListData()
        loadMenuListData()

        return rootView
    }

    private fun initViews() {
        cafeCategoryRecyclerView = rootView.findViewById(R.id.recyclerview_category_list)
        cafeCategoryRecyclerView.layoutManager = LinearLayoutManager(rootView.context, LinearLayoutManager.HORIZONTAL, false)
        cafeCategoryAdapter = CafeCategoryAdapter()
        cafeCategoryRecyclerView.adapter = cafeCategoryAdapter

        cafeMenuRecyclerView = rootView.findViewById(R.id.recyclerview_menu_list)
        cafeMenuRecyclerView.layoutManager = LinearLayoutManager(rootView.context, LinearLayoutManager.VERTICAL, false)
        cafeMenuAdapter = CafeMenuAdapter()
        cafeMenuRecyclerView.adapter = cafeMenuAdapter
    }

    private fun loadCategoryListData() {
        ApiRetrofit.getApiClient().getCafeCategories("1").enqueue(object : Callback<ResponseCategory> {
            override fun onResponse(call: Call<ResponseCategory>, response: Response<ResponseCategory>) {
                Log.d("category_list", response.body()!!.response.toString())
                Log.d("category", response.body()!!.toString())

                if (response.isSuccessful) {

                    val cafeCategoryApiDataResponseList: MutableList<CafeCategoryApiData> = mutableListOf()
                    val list = response.body()!!

                    cafeCategoryApiDataResponseList.addAll(list.response)

                    cafeCategoryAdapter?.setList(cafeCategoryApiDataResponseList)

                }
            }

            override fun onFailure(call: Call<ResponseCategory>, t: Throwable) {
                Log.d("category_list_failure", t.message.toString())
                Toast.makeText(rootView.context, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun loadMenuListData() {
        ApiRetrofit.getApiClient().getCafeMenuByRating("1").enqueue(object : Callback<ResponseCafeMenu> {
            override fun onResponse(call: Call<ResponseCafeMenu>, response: Response<ResponseCafeMenu>) {
                Log.d("menu_list", response.body()!!.response.toString())
                Log.d("menu", response.body()!!.toString())

                if (response.isSuccessful) {

                    val cafeMenuApiDataResponseList: MutableList<CafeMenuApiData> = mutableListOf()
                    val list = response.body()!!

                    cafeMenuApiDataResponseList.addAll(list.response)

                    cafeMenuAdapter?.setList(cafeMenuApiDataResponseList)

                }
            }

            override fun onFailure(call: Call<ResponseCafeMenu>, t: Throwable) {
                Log.d("menu_list_failure", t.message.toString())
                Toast.makeText(rootView.context, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}