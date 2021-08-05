package kz.bfgroup.bikli.main_window.user_fragment.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.data.ApiRetrofit
import kz.bfgroup.bikli.registration.presentation.CodeDialogFragment
import kz.bfgroup.bikli.registration.presentation.GENERATED_ACCESS_TOKEN
import kz.bfgroup.bikli.registration.presentation.MY_APP
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserSettings : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var saveButton: Button

    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var homeEditText: EditText
    private lateinit var flatNumEditText: EditText
    private lateinit var floorEditText: EditText

    private lateinit var fields : Map<String, String>
    private lateinit var token : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)

        initViews()

        backButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {

            fields = mutableMapOf(
                "access_token" to token,
                "phone" to phoneEditText.text.toString(),
                "name" to nameEditText.text.toString(),
                "apartment" to flatNumEditText.text.toString(),
                "adress" to addressEditText.text.toString(),
                "floor" to floorEditText.text.toString(),
                "street" to homeEditText.text.toString()
            )
            setUserData()
        }
    }

    private fun initViews() {

        token = getSavedToken()
        Toast.makeText(this,token, Toast.LENGTH_LONG).show()

        backButton = findViewById(R.id.settings_toolbar_back_image_view)
        saveButton = findViewById(R.id.activity_settings_save_button)

        nameEditText = findViewById(R.id.activity_user_settings_name_edit_text)
        phoneEditText = findViewById(R.id.activity_settings_telephone_edit_text)
        addressEditText = findViewById(R.id.activity_settings_address_edit_text)
        homeEditText = findViewById(R.id.activity_settings_home_edit_text)
        flatNumEditText = findViewById(R.id.activity_settings_flat_num_edit_text)
        floorEditText = findViewById(R.id.activity_settings_flooredit_text)

        nameEditText.setText(intent.getStringExtra("name").toString())
        phoneEditText.setText(intent.getStringExtra("phone").toString())
        addressEditText.setText(intent.getStringExtra("address").toString())
        homeEditText.setText(intent.getStringExtra("home").toString())
        flatNumEditText.setText(intent.getStringExtra("flatNum").toString())
        floorEditText.setText(intent.getStringExtra("floor").toString())
    }

    private fun setUserData() {
        ApiRetrofit.getApiClient().userSetData(fields).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("user_set_data", response.body().toString())

                if (response.isSuccessful) {
//                    finish()
                    val dialog = SuccessInfoDialogFragment()
                    dialog.show(supportFragmentManager, "successInfoDialog")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("user_set_data_failure", t.message.toString())
                Toast.makeText(this@UserSettings,t.message.toString()+"NOT OK", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getSavedToken(): String {
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            MY_APP,
            Context.MODE_PRIVATE
        )

        return sharedPreferences.getString(GENERATED_ACCESS_TOKEN, "default") ?: "default"
    }
}