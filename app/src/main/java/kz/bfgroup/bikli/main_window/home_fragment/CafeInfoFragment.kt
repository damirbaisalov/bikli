package kz.bfgroup.bikli.main_window.home_fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.data.ApiRetrofit
import kz.bfgroup.bikli.main_window.home_fragment.models.*
import kz.bfgroup.bikli.main_window.home_fragment.presentation.CAFE_ID
import kz.bfgroup.bikli.main_window.home_fragment.presentation.MY_APP_HOME_FRAGMENT
import kz.bfgroup.bikli.main_window.home_fragment.presentation.view.CafeCategoryAdapter
import kz.bfgroup.bikli.main_window.home_fragment.presentation.view.CafeMenuAdapter
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CafeInfoFragment: Fragment() {

    private lateinit var rootView: View
    private lateinit var cafeCategoryRecyclerView: RecyclerView
    private var cafeCategoryAdapter: CafeCategoryAdapter?=null
    private lateinit var cafeMenuRecyclerView: RecyclerView
    private var cafeMenuAdapter: CafeMenuAdapter?=null

    private lateinit var cafeInfoNameTextView: TextView
    private lateinit var cafeInfoLogoImageView: ImageView
    private lateinit var cafeInfoMinSumTextView: TextView
    private lateinit var cafeInfoDeliveryTimeTextView: TextView
    private lateinit var cafeInfoDeliveryCostTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_cafe_info, container, false)
        initViews()

        loadCafeInfo()
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

        cafeInfoNameTextView = rootView.findViewById(R.id.cafe_info_name)
        cafeInfoLogoImageView = rootView.findViewById(R.id.cafe_info_logo)
        cafeInfoMinSumTextView = rootView.findViewById(R.id.cafe_info_min_sum)
        cafeInfoDeliveryTimeTextView = rootView.findViewById(R.id.cafe_info_delivery_time)
        cafeInfoDeliveryCostTextView = rootView.findViewById(R.id.cafe_info_delivery_cost)
    }

    private fun loadCafeInfo() {
        ApiRetrofit.getApiClient().getCafeInfo(getSavedCafeId()).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("cafe_info", response.toString())

                if (response.isSuccessful) {

                    val responseBody = response.body()!!.string()
                    val responseToObject = JSONObject(responseBody)
                    val cafeInfoApiData = parseCafeInfoResponseJsonObject(responseToObject)

                    cafeInfoNameTextView.text = cafeInfoApiData.response?.responseBody?.name!!
                    cafeInfoMinSumTextView.text = ("от "+ cafeInfoApiData.response.responseBody.minsumma!! + " тг.")
                    cafeInfoDeliveryTimeTextView.text = (cafeInfoApiData.response.responseBody.vremya!!+" мин")
                    cafeInfoDeliveryCostTextView.text = (cafeInfoApiData.response.responseBody.cenadost+" тг.")
                    Glide
                        .with(rootView.context)
                        .load("http://bikli.kz/LogoCafe/" +cafeInfoApiData.response.responseBody.logo)
                        .into(cafeInfoLogoImageView)

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("cafe_info_failure", t.message.toString())
                Toast.makeText(rootView.context, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun parseCafeInfoResponseJsonObject(cafeInfoResponseJSONObject: JSONObject): CafeInfoResponse{
        val responseJsonObject: JSONObject? = (cafeInfoResponseJSONObject.get("response") as? JSONObject)
        var cafeInfoResponseBodyData : CafeInfoResponseBody?=null

        responseJsonObject?.let {
            cafeInfoResponseBodyData = parseCafeInfoResponseBodyJsonObject(it)
        }

        return CafeInfoResponse(
            response = cafeInfoResponseBodyData
        )
    }

    private fun parseCafeInfoResponseBodyJsonObject(cafeInfoResponseBodyJSONObject: JSONObject): CafeInfoResponseBody {
        val responseBodyJsonObject = (cafeInfoResponseBodyJSONObject.get(getSavedCafeId()) as? JSONObject)
        var cafeInfoApiData : CafeInfoApiData?=null

        responseBodyJsonObject?.let {
            cafeInfoApiData = parseCafeInfoApiDataJsonObject(it)
        }

        return CafeInfoResponseBody(
            responseBody = cafeInfoApiData
        )
    }

    private fun parseCafeInfoApiDataJsonObject(cafeInfoApiDataJsonObject: JSONObject): CafeInfoApiData {
        val name = cafeInfoApiDataJsonObject.getString("name")
        val minsumma = cafeInfoApiDataJsonObject.getString("minsumma")
        val logo = cafeInfoApiDataJsonObject.getString("logo")
        val vremya = cafeInfoApiDataJsonObject.getString("vremya")
        val cenadost = cafeInfoApiDataJsonObject.getString("cenadost")

        return CafeInfoApiData(
            name = name,
            minsumma = minsumma,
            logo = logo,
            vremya = vremya,
            cenadost = cenadost
        )
    }

    private fun loadCategoryListData() {
        ApiRetrofit.getApiClient().getCafeCategories(getSavedCafeId()).enqueue(object : Callback<ResponseCategory> {
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
        ApiRetrofit.getApiClient().getCafeMenuByRating(getSavedCafeId()).enqueue(object : Callback<ResponseCafeMenu> {
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

    private fun getSavedCafeId(): String {
        val sharedPreferences: SharedPreferences = rootView.context.getSharedPreferences(
            MY_APP_HOME_FRAGMENT,
            Context.MODE_PRIVATE
        )

        return sharedPreferences.getString(CAFE_ID, "default") ?: "default"
    }

}