package kz.bfgroup.bikli.main_window.user_fragment.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.data.ApiRetrofit
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseUser
import kz.bfgroup.bikli.registration.presentation.GENERATED_ACCESS_TOKEN
import kz.bfgroup.bikli.registration.presentation.MY_APP
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFragment: Fragment() {

    private lateinit var viewUser : View

    private lateinit var registrationButton: Button
    private lateinit var authorizationButton: Button

    private lateinit var userNameTextView: TextView
    private lateinit var cashbackTextView: TextView
    private lateinit var balansTextView: TextView

    private lateinit var token : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewUser = inflater.inflate(R.layout.fragment_user, container, false)

        initViews()

        registrationButton.setOnClickListener {

        }

        authorizationButton.setOnClickListener {

        }
        token = getSavedToken()
        Toast.makeText(viewUser.context,token,Toast.LENGTH_LONG).show()
        loadUserData()

        return viewUser
    }

    private fun initViews() {
        registrationButton = viewUser.findViewById(R.id.fragment_user_reg_button)
        authorizationButton = viewUser.findViewById(R.id.fragment_user_auth_button)

        userNameTextView = viewUser.findViewById(R.id.fragment_user_username)
        cashbackTextView = viewUser.findViewById(R.id.fragment_user_cashback)
        balansTextView = viewUser.findViewById(R.id.fragment_user_balans)
    }

    private fun loadUserData() {
        ApiRetrofit.getApiClient().userGetData(token).enqueue(object :
            Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                Log.d("user_get_data", response.body().toString())

                if (response.isSuccessful) {
                    val nameResult = response.body()?.response?.user?.name!!
                    val cashBackResult = response.body()?.response?.user?.cashback!! + "%"
                    val balansResult = response.body()?.response?.user?.balans!!

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
}