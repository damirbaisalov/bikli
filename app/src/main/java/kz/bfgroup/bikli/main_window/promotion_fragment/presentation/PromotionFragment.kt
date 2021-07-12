package kz.bfgroup.bikli.main_window.promotion_fragment.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.data.ApiRetrofit
import kz.bfgroup.bikli.main_window.promotion_fragment.models.PromotionApiData
import kz.bfgroup.bikli.main_window.promotion_fragment.models.ResponseApiData
import kz.bfgroup.bikli.main_window.promotion_fragment.presentation.view.PromotionAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PromotionFragment: Fragment() {

    private lateinit var bikliHelperOn: LinearLayout
    private lateinit var bikliHelperOff: LinearLayout

    private lateinit var recyclerView: RecyclerView
    private var promotionAdapter: PromotionAdapter?=null
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewPromotions = inflater.inflate(R.layout.fragment_promotion, container, false)

        recyclerView = viewPromotions.findViewById(R.id.promotion_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(viewPromotions.context, LinearLayoutManager.VERTICAL, false)
        promotionAdapter = PromotionAdapter()
        recyclerView.adapter = promotionAdapter
        progressBar = viewPromotions.findViewById(R.id.promotion_progress_bar)
        progressBar.visibility = View.VISIBLE

        swipeRefreshLayout = viewPromotions.findViewById(R.id.promotion_swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
            promotionAdapter?.clearAll()
            loadApiData()
        }

        bikliHelperOn = viewPromotions.findViewById(R.id.fragment_promotion_bikli_helper_linear_layout)
        bikliHelperOff = viewPromotions.findViewById(R.id.fragment_promotion_hand_help_linear_layout)
        initViews()
        loadApiData()
        return viewPromotions
    }

    private fun initViews() {

        bikliHelperOn.visibility = View.GONE
        bikliHelperOff.setOnClickListener {
            checkVisibility()
        }

        bikliHelperOn.setOnClickListener {
            checkVisibility()
        }
    }

    private fun loadApiData() {
        swipeRefreshLayout.isRefreshing = true
        ApiRetrofit.getApiClient().getPromotions().enqueue(object : Callback<ResponseApiData> {
            override fun onResponse(call: Call<ResponseApiData>, response: Response<ResponseApiData>) {
                Log.d("promotion_list", response.body()!!.response.toString())
                Log.d("promotion_list", response.body()!!.toString())
                swipeRefreshLayout.isRefreshing = false
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {

                    val promotionApiDataResponseList: MutableList<PromotionApiData> = mutableListOf()
                    val list = response.body()!!

                    promotionApiDataResponseList.addAll(list.response)
                    promotionAdapter?.setList(promotionApiDataResponseList)

                }
            }

            override fun onFailure(call: Call<ResponseApiData>, t: Throwable) {
                Log.d("promotion_list_failure", t.message.toString())
                Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                progressBar.visibility = View.VISIBLE
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun checkVisibility() {
        if (bikliHelperOff.visibility == View.VISIBLE && bikliHelperOn.visibility == View.GONE) {
            bikliHelperOff.visibility = View.GONE
            bikliHelperOn.visibility = View.VISIBLE
        } else if (bikliHelperOff.visibility == View.GONE && bikliHelperOn.visibility == View.VISIBLE) {
            bikliHelperOff.visibility = View.VISIBLE
            bikliHelperOn.visibility = View.GONE
        }
    }

}