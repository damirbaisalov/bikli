package kz.bfgroup.bikli.registration.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.data.ApiRetrofit
import kz.bfgroup.bikli.main_window.presentation.MainWindow
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseUserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registration: AppCompatActivity() {

    private lateinit var nextButton : Button
    private lateinit var switchCompat: SwitchCompat
    private lateinit var flatNumberTextView: TextView
    private lateinit var flatFloorTextView: TextView

    private lateinit var userNameEditText: EditText
    private lateinit var userPhoneNumberEditText: EditText
    private lateinit var userAddressEditText: EditText
    private lateinit var userHomeEditText: EditText
    private lateinit var flatNumberEditText: EditText
    private lateinit var flatFloorEditText: EditText

    private lateinit var fields: Map<String,String>

    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        bindViews()

        switchCompat.setOnClickListener {
            homeOrFlat()
        }

        nextButton.setOnClickListener {
            startActivity(Intent(this, MainWindow::class.java))
            finish()
        }

        registerButton.setOnClickListener {
            fields = mutableMapOf(
                "phone_user" to userPhoneNumberEditText.text.toString(),
                "name_user" to userNameEditText.text.toString(),
                "adress_user" to userAddressEditText.text.toString(),
                "apartment_user" to userHomeEditText.text.toString(),
                "floor" to flatFloorEditText.text.toString(),
                "street" to flatNumberEditText.text.toString()
            )
            registerUser()
            Toast.makeText(this@Registration, fields.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun bindViews() {

        registerButton = findViewById(R.id.activity_registration_register_button)

        switchCompat = findViewById(R.id.activity_registration_switch_button)
        switchCompat.isChecked = true
        flatNumberTextView = findViewById(R.id.activity_registration_flat_num_text_view)
        flatNumberEditText = findViewById(R.id.activity_registration_flat_num_edit_text)
        flatFloorEditText = findViewById(R.id.activity_registration_flooredit_text)
        flatFloorTextView = findViewById(R.id.activity_registration_floor_text_view)
        turnOffVisibility(flatFloorEditText)
        turnOffVisibility(flatFloorTextView)
        turnOffVisibility(flatNumberEditText)
        turnOffVisibility(flatNumberTextView)
        nextButton = findViewById(R.id.activity_registration_next_button)

        userNameEditText = findViewById(R.id.activity_registration_name_edit_text)
        userPhoneNumberEditText = findViewById(R.id.activity_registration_telephone_edit_text)
        userAddressEditText = findViewById(R.id.activity_registration_address_edit_text)
        userHomeEditText = findViewById(R.id.activity_registration_home_edit_text)
    }

    private fun registerUser() {
        ApiRetrofit.getApiClient().registerUser(fields).enqueue(object : Callback<ResponseUserInfo> {
            override fun onResponse(call: Call<ResponseUserInfo>, response: Response<ResponseUserInfo>) {
                Log.d("user_info_response", response.body().toString())

                if (response.isSuccessful) {
//                    Toast.makeText(this@Registration, response.body().toString() + " OK OCCURRED", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseUserInfo>, t: Throwable) {
                Log.d("user_info_failure", t.message.toString())
//                Toast.makeText(this@Registration, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun homeOrFlat() {

        if (switchCompat.isChecked) {
            turnOffVisibility(flatFloorEditText)
            turnOffVisibility(flatFloorTextView)
            turnOffVisibility(flatNumberEditText)
            turnOffVisibility(flatNumberTextView)
        } else {
            turnOnVisibility(flatFloorEditText)
            turnOnVisibility(flatFloorTextView)
            turnOnVisibility(flatNumberEditText)
            turnOnVisibility(flatNumberTextView)
        }
    }

    private fun turnOffVisibility(v : View) {
        v.visibility = View.GONE
        if (v is EditText)
        {
            v.text.clear()
        }
    }

    private fun turnOnVisibility(v : View) {
        v.visibility = View.VISIBLE
    }
}