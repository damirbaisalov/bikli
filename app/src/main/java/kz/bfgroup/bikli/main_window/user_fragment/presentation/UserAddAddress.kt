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
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAddAddress : AppCompatActivity() {

    private lateinit var addressNameEditText: EditText
    private lateinit var homeEditText: EditText
    private lateinit var flatNumEditText: EditText
    private lateinit var floorEditText: EditText
    private lateinit var entranceEditText: EditText

    private lateinit var saveButton : Button
    private lateinit var backButton: ImageView

    private lateinit var fields : Map<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_add_address)

        initViews()

        Toast.makeText(this, intent.getStringExtra("user_id_for_add_address").toString(), Toast.LENGTH_LONG).show()

        backButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
            fields = mutableMapOf(
                "user_id" to intent.getStringExtra("user_id_for_add_address").toString(),
                "apartment" to flatNumEditText.text.toString(),
                "adress" to addressNameEditText.text.toString(),
                "floor" to floorEditText.text.toString(),
                "street" to homeEditText.text.toString(),
                "entrance" to entranceEditText.text.toString()
            )
            addAddressData()
        }
    }

    private fun initViews() {
        backButton = findViewById(R.id.address_toolbar_back_image_view)

        addressNameEditText = findViewById(R.id.activity_add_address_name_edit_text)
        homeEditText = findViewById(R.id.activity_add_address_home_edit_text)
        flatNumEditText = findViewById(R.id.activity_add_address_flat_num_edit_text)
        floorEditText = findViewById(R.id.activity_add_address_floor_edit_text)
        entranceEditText = findViewById(R.id.activity_add_address_entrance_edit_text)

        saveButton = findViewById(R.id.activity_add_address_save_button)
    }

    private fun addAddressData() {
        ApiRetrofit.getApiClient().userAddAddress(fields).enqueue(object :
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("user_add_address_data", response.body().toString())

                if (response.isSuccessful) {
//                    finish()
                    val dialog = SuccessInfoDialogFragment()
                    dialog.show(supportFragmentManager, "successInfoDialog")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("user_add_adr_failure", t.message.toString())
                Toast.makeText(this@UserAddAddress,t.message.toString()+"NOT OK", Toast.LENGTH_LONG).show()
            }
        })
    }
}