package kz.bfgroup.bikli.main_window.home_fragment.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.data.ApiRetrofit
import kz.bfgroup.bikli.main_window.cart_fragment.presentation.CartFragment
import kz.bfgroup.bikli.main_window.home_fragment.CafeInfoFragment
import kz.bfgroup.bikli.main_window.home_fragment.models.CafeApiData
import kz.bfgroup.bikli.main_window.home_fragment.models.ResponseCafeApiData
import kz.bfgroup.bikli.main_window.home_fragment.presentation.view.CafeAdapter
import kz.bfgroup.bikli.main_window.home_fragment.presentation.view.CafeClickListener
import kz.bfgroup.bikli.main_window.user_fragment.presentation.MY_APP_USER_FRAGMENT
import kz.bfgroup.bikli.main_window.user_fragment.presentation.USER_ID
import kz.bfgroup.bikli.main_window.user_fragment.presentation.UserAddressUpdate
import kz.bfgroup.bikli.main_window.user_fragment.presentation.view.AddressClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val MY_APP_HOME_FRAGMENT = "MY_APP_HOME_FRAGMENT"
const val CAFE_ID = "CAFE_ID"
class HomeFragment: Fragment() {

    private lateinit var viewHome: View

    private lateinit var bikliHelperOn: LinearLayout
    private lateinit var bikliHelperOff: LinearLayout

    private lateinit var recyclerView: RecyclerView
    private var cafeAdapter: CafeAdapter?=null
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var searchView: SearchView
    private var searchingCafeList: List<CafeApiData> = listOf()

    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private lateinit var nestedScrollView: NestedScrollView

    private lateinit var cafeInfoFragment: CafeInfoFragment
    private lateinit var tuneImageButton: ImageButton
    private val bundleData = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewHome = inflater.inflate(R.layout.fragment_home, container, false)

        initViews()

        swipeRefreshLayout.setOnRefreshListener {
            cafeAdapter?.clearAll()

            shimmerFrameLayout.startShimmerAnimation()
            shimmerFrameLayout.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE

            loadApiData()
        }

        loadApiData()

        queryInSearchView()

        tuneImageButton.setOnClickListener {
            loadFragments(cafeInfoFragment)
        }


        return viewHome
    }


    private fun initViews() {

        swipeRefreshLayout = viewHome.findViewById(R.id.cafe_swipe_refresh)

        recyclerView = viewHome.findViewById(R.id.home_fragment_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(viewHome.context, LinearLayoutManager.VERTICAL, false)
        cafeAdapter = CafeAdapter(getCafeClickListener())
        recyclerView.adapter = cafeAdapter

        searchView = viewHome.findViewById(R.id.home_fragment_search_view)

        shimmerFrameLayout = viewHome.findViewById(R.id.home_fragment_shimmer_layout)
        nestedScrollView = viewHome.findViewById(R.id.fragment_home_nested_scroll)

        tuneImageButton = viewHome.findViewById(R.id.home_fragment_tune_image_button)
        cafeInfoFragment = CafeInfoFragment()

        bikliHelperOn = viewHome.findViewById(R.id.fragment_home_bikli_helper_linear_layout)
        bikliHelperOff = viewHome.findViewById(R.id.fragment_home_hand_help_linear_layout)

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

                shimmerFrameLayout.stopShimmerAnimation()
                shimmerFrameLayout.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                if (response.isSuccessful) {

                    val cafeApiDataResponseList: MutableList<CafeApiData> = mutableListOf()
                    val list = response.body()!!

                    cafeApiDataResponseList.addAll(list.response)
                    searchingCafeList = list.response

                    cafeAdapter?.setList(cafeApiDataResponseList)

                }
            }

            override fun onFailure(call: Call<ResponseCafeApiData>, t: Throwable) {
                Log.d("cafe_list_failure", t.message.toString())
                Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
                swipeRefreshLayout.isRefreshing = false
                shimmerFrameLayout.visibility = View.GONE
            }
        })
    }

    private fun queryInSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchView.clearFocus()
                val queryText = p0?.lowercase()

                cafeAdapter?.filter(queryText!!)

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                val queryText = p0?.lowercase()

//                cafeAdapter?.filter(queryText!!)
//
//                if (queryText?.isEmpty()!!)
//                    cafeAdapter?.setList(searchingCafeList)

                val newCafeList : MutableList<CafeApiData> = mutableListOf()
                for (q in searchingCafeList) {
                    if (q.name?.lowercase()?.contains(queryText!!)!!) {
                        newCafeList.add(q)
                    }
                }
                cafeAdapter?.setList(newCafeList)

                return false
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

    private fun loadFragments(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_window_frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun getCafeClickListener(): CafeClickListener {
        return object: CafeClickListener {
            override fun onCafeClick(id: String?) {
                Toast.makeText(viewHome.context, id, Toast.LENGTH_LONG).show()
                bundleData.putString("cafe_id", id)
//                saveCafeId(id)
                cafeInfoFragment.arguments = bundleData
                loadFragments(cafeInfoFragment)
            }
        }
    }

    private fun saveCafeId(cafeId: String?) {
        val sharedPref = viewHome.context.getSharedPreferences(MY_APP_HOME_FRAGMENT, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(CAFE_ID, cafeId)
        editor.apply()

    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmerAnimation()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }
}