package kz.bfgroup.bikli.main_window.home_fragment.presentation

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
import kz.bfgroup.bikli.main_window.home_fragment.models.CafeApiData
import kz.bfgroup.bikli.main_window.home_fragment.models.ResponseCafeApiData
import kz.bfgroup.bikli.main_window.home_fragment.presentation.view.CafeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment: Fragment() {

    private lateinit var bikliHelperOn: LinearLayout
    private lateinit var bikliHelperOff: LinearLayout

    private lateinit var recyclerView: RecyclerView
    private var cafeAdapter: CafeAdapter?=null
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewHome = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = viewHome.findViewById(R.id.home_fragment_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(viewHome.context, LinearLayoutManager.VERTICAL, false)
        cafeAdapter = CafeAdapter()
        recyclerView.adapter = cafeAdapter
        progressBar = viewHome.findViewById(R.id.cafe_progress_bar)
        progressBar.visibility = View.VISIBLE

        swipeRefreshLayout = viewHome.findViewById(R.id.cafe_swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
            cafeAdapter?.clearAll()
            loadApiData()
        }

        bikliHelperOn = viewHome.findViewById(R.id.fragment_home_bikli_helper_linear_layout)
        bikliHelperOff = viewHome.findViewById(R.id.fragment_home_hand_help_linear_layout)
        initViews()

        loadApiData()

        return viewHome
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
        ApiRetrofit.getApiClient().getCafesList().enqueue(object : Callback<ResponseCafeApiData> {
            override fun onResponse(call: Call<ResponseCafeApiData>, response: Response<ResponseCafeApiData>) {
                Log.d("cafe_list", response.body()!!.response.toString())
                Log.d("cafe_list", response.body()!!.toString())
                swipeRefreshLayout.isRefreshing = false
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {

                    val cafeApiDataResponseList: MutableList<CafeApiData> = mutableListOf()
                    val list = response.body()!!

                    cafeApiDataResponseList.addAll(list.response)
                    cafeAdapter?.setList(cafeApiDataResponseList)

                }
            }

            override fun onFailure(call: Call<ResponseCafeApiData>, t: Throwable) {
                Log.d("cafe_list_failure", t.message.toString())
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