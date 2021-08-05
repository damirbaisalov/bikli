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
import kz.bfgroup.bikli.main_window.home_fragment.models.CafeApiData
import kz.bfgroup.bikli.main_window.home_fragment.models.CafeCategoryApiData
import kz.bfgroup.bikli.main_window.home_fragment.models.ResponseCafeApiData
import kz.bfgroup.bikli.main_window.home_fragment.models.ResponseCategory
import kz.bfgroup.bikli.main_window.home_fragment.presentation.view.CafeAdapter
import kz.bfgroup.bikli.main_window.home_fragment.presentation.view.CafeCategoryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CafeInfoFragment: Fragment() {

    private lateinit var rootView: View
    private lateinit var cafeCategoryRecyclerView: RecyclerView
    private var cafeCategoryAdapter: CafeCategoryAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_cafe_info, container, false)
        initViews()

        loadApiData()


        return rootView
    }

    private fun initViews() {
        cafeCategoryRecyclerView = rootView.findViewById(R.id.recyclerview_category_list)
        cafeCategoryRecyclerView.layoutManager = LinearLayoutManager(rootView.context, LinearLayoutManager.HORIZONTAL, false)
        cafeCategoryAdapter = CafeCategoryAdapter()

        cafeCategoryRecyclerView.adapter = cafeCategoryAdapter
    }

    private fun loadApiData() {
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

}