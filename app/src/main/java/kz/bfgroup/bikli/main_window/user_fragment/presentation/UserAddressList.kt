package kz.bfgroup.bikli.main_window.user_fragment.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.data.ApiRetrofit
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseAddress
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseAddressBody
import kz.bfgroup.bikli.main_window.user_fragment.presentation.view.AddressAdapter
import kz.bfgroup.bikli.main_window.user_fragment.presentation.view.AddressClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAddressList : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var addAddressButton: Button

    private lateinit var recyclerView: RecyclerView
    private var addressAdapter: AddressAdapter?=null

    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_address_list)

        initViews()

        backButton.setOnClickListener {
            finish()
        }

        addAddressButton.setOnClickListener {
            val intent = Intent(this, UserAddAddress::class.java)
            intent.putExtra("user_id_for_add_address", userId)
            startActivity(intent)
        }

        loadUserAddressList()
    }

    private fun initViews() {
        backButton = findViewById(R.id.address_toolbar_back_image_view)
        addAddressButton = findViewById(R.id.activity_user_address_list_add_address)

        recyclerView = findViewById(R.id.activity_user_address_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        addressAdapter = AddressAdapter(getAddressClickListener())
        recyclerView.adapter = addressAdapter

        userId = intent.getStringExtra("user_id").toString()
        Toast.makeText(this, userId, Toast.LENGTH_LONG).show()
    }

    private fun loadUserAddressList() {
        ApiRetrofit.getApiClient().userGetAddress(userId).enqueue(object :
            Callback<ResponseAddress> {
            override fun onResponse(call: Call<ResponseAddress>, response: Response<ResponseAddress>) {
                Log.d("user_address_get_data", response.body().toString())

                if (response.isSuccessful) {

                    val addressApiDataResponseList: MutableList<ResponseAddressBody> = mutableListOf()
                    val list = response.body()!!

                    addressApiDataResponseList.addAll(list.response)
                    addressAdapter?.setList(addressApiDataResponseList)
                }
            }

            override fun onFailure(call: Call<ResponseAddress>, t: Throwable) {
                Log.d("user_adr_data_failure", t.message.toString())
                Toast.makeText(this@UserAddressList,t.message.toString()+"NOT OK", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getAddressClickListener(): AddressClickListener{
        return object: AddressClickListener{
            override fun onAddressClick(id: String?) {
                val intent = Intent(this@UserAddressList, UserAddressUpdate::class.java)
                intent.putExtra("address_id_for_update", id)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadUserAddressList()
    }

}