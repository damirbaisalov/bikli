package kz.bfgroup.bikli.main_window.user_fragment.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.data.ApiRetrofit
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseUser
import kz.bfgroup.bikli.registration.presentation.GENERATED_ACCESS_TOKEN
import kz.bfgroup.bikli.registration.presentation.MY_APP
import kz.bfgroup.bikli.registration.presentation.Registration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFragment: Fragment() {

    private lateinit var viewUser : View

    private lateinit var fragmentUserLinearLayoutForm: LinearLayout
    private lateinit var fragmentUserGetDataLayout: LinearLayout

    private lateinit var registrationButton: Button
    private lateinit var authorizationButton: Button

    private lateinit var userNameTextView: TextView
    private lateinit var cashbackTextView: TextView
    private lateinit var balansTextView: TextView

    private lateinit var goToUserSettingsButton: LinearLayout
    private lateinit var goToUserAddressListButton: LinearLayout

    private lateinit var nameResult : String
    private lateinit var phoneResult : String
    private lateinit var addressResult : String
    private lateinit var homeResult : String
    private lateinit var flatNumResult : String
    private lateinit var floorResult : String
    private lateinit var userId: String

    private lateinit var token : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewUser = inflater.inflate(R.layout.fragment_user, container, false)

        initViews()

        if (getSavedToken()=="default") {
            fragmentUserLinearLayoutForm.visibility = View.VISIBLE
            fragmentUserGetDataLayout.visibility = View.GONE
        } else {
            fragmentUserLinearLayoutForm.visibility = View.GONE
            fragmentUserGetDataLayout.visibility = View.VISIBLE
        }

        registrationButton.setOnClickListener {
            val intent = Intent(viewUser.context, Registration::class.java)
            startActivity(intent)
        }

        authorizationButton.setOnClickListener {

        }

        goToUserSettingsButton.setOnClickListener {
            val intent = Intent(viewUser.context, UserSettings::class.java)
            intent.putExtra("name", nameResult)
            intent.putExtra("phone", phoneResult)
            intent.putExtra("address", addressResult)
            intent.putExtra("home", homeResult)
            intent.putExtra("flatNum", flatNumResult)
            intent.putExtra("floor", floorResult)
            startActivity(intent)
        }

        goToUserAddressListButton.setOnClickListener {
            val intent = Intent(viewUser.context, UserAddressList::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
        }

        token = getSavedToken()
//        Toast.makeText(viewUser.context,token,Toast.LENGTH_LONG).show()
        loadUserData()

        return viewUser
    }

    private fun initViews() {

        fragmentUserLinearLayoutForm = viewUser.findViewById(R.id.fragment_user_linear_layout_form)
        fragmentUserGetDataLayout = viewUser.findViewById(R.id.fragment_user_get_data_layout)

        registrationButton = viewUser.findViewById(R.id.fragment_user_reg_button)
        authorizationButton = viewUser.findViewById(R.id.fragment_user_auth_button)

        userNameTextView = viewUser.findViewById(R.id.fragment_user_username)
        cashbackTextView = viewUser.findViewById(R.id.fragment_user_cashback)
        balansTextView = viewUser.findViewById(R.id.fragment_user_balans)

        goToUserSettingsButton = viewUser.findViewById(R.id.fragment_user_settings_button)
        goToUserAddressListButton = viewUser.findViewById(R.id.fragment_user_address_list_button)
    }

    private fun loadUserData() {
        ApiRetrofit.getApiClient().userGetData(token).enqueue(object :
            Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                Log.d("user_get_data", response.body().toString())

                if (response.isSuccessful) {
                    nameResult = response.body()?.response?.user?.name!!
                    val cashBackResult = response.body()?.response?.user?.cashback!! + "%"
                    val balansResult = response.body()?.response?.user?.balans!!

                    phoneResult = response.body()?.response?.user?.phone!!
                    addressResult = response.body()?.response?.user?.adress!!
                    homeResult = response.body()?.response?.user?.street!!
                    flatNumResult = response.body()?.response?.user?.apartment!!
                    floorResult = response.body()?.response?.user?.floor!!
                    userId = response.body()?.response?.user?.id!!

                    userNameTextView.text = nameResult
                    cashbackTextView.text = cashBackResult
                    balansTextView.text = balansResult

                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Log.d("user_get_data_failure", t.message.toString())
                Toast.makeText(viewUser.context,t.message.toString()+"NOT OK", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getSavedToken(): String {
        val sharedPreferences: SharedPreferences = viewUser.context.getSharedPreferences(
            MY_APP,
            Context.MODE_PRIVATE
        )

        return sharedPreferences.getString(GENERATED_ACCESS_TOKEN, "default") ?: "default"
    }

    override fun onStart() {
        super.onStart()
        loadUserData()
    }
}