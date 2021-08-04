package kz.bfgroup.bikli.main_window.user_fragment.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.data.ApiRetrofit
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseAddress
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseAddressUpdate
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAddressUpdate : AppCompatActivity() {

    private lateinit var addressNameEditText: EditText
    private lateinit var homeEditText: EditText
    private lateinit var flatNumEditText: EditText
    private lateinit var floorEditText: EditText
    private lateinit var entranceEditText: EditText

    private lateinit var saveButton : Button
    private lateinit var deleteButton: Button
    private lateinit var backButton: ImageView

    private lateinit var addressId : String

    private lateinit var fields : Map<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_address_update)

        initViews()

        saveButton.setOnClickListener {
            fields = mutableMapOf(
                "id" to addressId,
                "apartment" to flatNumEditText.text.toString(),
                "adress" to addressNameEditText.text.toString(),
                "floor" to floorEditText.text.toString(),
                "street" to homeEditText.text.toString(),
                "entrance" to entranceEditText.text.toString()
            )
            setUserAddress()
        }

        deleteButton.setOnClickListener {
            deleteUserAddress()
        }

        Toast.makeText(this, intent.getStringExtra("address_id_for_update").toString(),Toast.LENGTH_LONG).show()

        loadAddressById()
    }

    private fun initViews() {
        backButton = findViewById(R.id.address_toolbar_back_image_view)

        addressNameEditText = findViewById(R.id.activity_update_address_name_edit_text)
        homeEditText = findViewById(R.id.activity_update_address_home_edit_text)
        flatNumEditText = findViewById(R.id.activity_update_address_flat_num_edit_text)
        floorEditText = findViewById(R.id.activity_update_address_floor_edit_text)
        entranceEditText = findViewById(R.id.activity_update_address_entrance_edit_text)

        saveButton = findViewById(R.id.activity_update_address_save_button)
        deleteButton = findViewById(R.id.activity_update_address_delete_button)

        addressId = intent.getStringExtra("address_id_for_update").toString()
    }

    private fun loadAddressById() {
        ApiRetrofit.getApiClient().userGetAddressById(addressId).enqueue(object :
            Callback<ResponseAddressUpdate> {
            override fun onResponse(call: Call<ResponseAddressUpdate>, response: Response<ResponseAddressUpdate>) {
                Log.d("user_adr_by_id_data", response.body().toString())

                if (response.isSuccessful) {
                    addressNameEditText.setText(response.body()?.response?.adr)
                    homeEditText.setText(response.body()?.response?.street)
                    flatNumEditText.setText(response.body()?.response?.apartments)
                    floorEditText.setText(response.body()?.response?.floor)
                    entranceEditText.setText(response.body()?.response?.entrance)
                }
            }

            override fun onFailure(call: Call<ResponseAddressUpdate>, t: Throwable) {
                Log.d("user_adr_id_failure", t.message.toString())
                Toast.makeText(this@UserAddressUpdate,t.message.toString()+"NOT OK", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setUserAddress() {
        ApiRetrofit.getApiClient().userSetAddress(fields).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("user_set_address_data", response.body().toString())

                if (response.isSuccessful) {
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("user_set_adr_failure", t.message.toString())
                Toast.makeText(this@UserAddressUpdate,t.message.toString()+"NOT OK", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun deleteUserAddress() {
        ApiRetrofit.getApiClient().userDeleteAddress(addressId).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("user_del_address_data", response.body().toString())

                if (response.isSuccessful) {
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("user_del_adr_failure", t.message.toString())
                Toast.makeText(this@UserAddressUpdate,t.message.toString()+"NOT OK", Toast.LENGTH_LONG).show()
            }
        })
    }
}