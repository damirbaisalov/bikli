package kz.bfgroup.bikli.main_window.home_fragment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.data.ApiClient
import kz.bfgroup.bikli.data.ApiRetrofit
import kz.bfgroup.bikli.main_window.home_fragment.models.CafeMenuApiData
import kz.bfgroup.bikli.main_window.home_fragment.models.ResponseCafeMenu
import kz.bfgroup.bikli.main_window.home_fragment.models.ResponseProduct
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductInfoActivity : AppCompatActivity() {

    private lateinit var viewPager1TextView: TextView
    private lateinit var viewPager2TextView: TextView
    private lateinit var viewPager3TextView: TextView

    private lateinit var descriptionLinearLayout: LinearLayout
    private lateinit var obzorLinearLayout: LinearLayout
    private lateinit var otzivLinearLayout: LinearLayout

    private lateinit var productInfoNameTextView: TextView
    private lateinit var productInfoImageView: ImageView
    private lateinit var productInfoDescriptionTextView: TextView
    private lateinit var productInfoCenaTextButton: Button

    private lateinit var backToPrevious: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_info)

        initViews()
        switchBetweenWindow()

        loadProductApiData()

        backToPrevious.setOnClickListener {
            onBackPressed()
        }

    }

    private fun initViews() {
        backToPrevious = findViewById(R.id.activity_product_info_back_image_view)
        viewPager1TextView = findViewById(R.id.activity_product_info_view_pager_1)
        viewPager2TextView = findViewById(R.id.activity_product_info_view_pager_2)
        viewPager3TextView = findViewById(R.id.activity_product_info_view_pager_3)

        descriptionLinearLayout = findViewById(R.id.activity_product_info_description)
        obzorLinearLayout = findViewById(R.id.activity_product_info_obzor)
        otzivLinearLayout = findViewById(R.id.activity_product_info_otziv)

        productInfoNameTextView = findViewById(R.id.activity_product_info_toolbar_product_name)
        productInfoImageView = findViewById(R.id.activity_product_info_image)
        productInfoDescriptionTextView = findViewById(R.id.activity_product_info_product_description_text_view)
        productInfoCenaTextButton = findViewById(R.id.activity_product_info_product_add_to_cart_button)
    }

    private fun switchBetweenWindow() {
        viewPager1TextView.setOnClickListener {
            viewPager1TextView.setTextColor(Color.parseColor("#FBA745"))
            viewPager2TextView.setTextColor(Color.parseColor("#717172"))
            viewPager3TextView.setTextColor(Color.parseColor("#717172"))
            descriptionLinearLayout.visibility = View.VISIBLE
            obzorLinearLayout.visibility = View.GONE
            otzivLinearLayout.visibility = View.GONE
        }
        viewPager2TextView.setOnClickListener {
            viewPager1TextView.setTextColor(Color.parseColor("#717172"))
            viewPager2TextView.setTextColor(Color.parseColor("#FBA745"))
            viewPager3TextView.setTextColor(Color.parseColor("#717172"))
            descriptionLinearLayout.visibility = View.GONE
            obzorLinearLayout.visibility = View.VISIBLE
            otzivLinearLayout.visibility = View.GONE
        }
        viewPager3TextView.setOnClickListener {
            viewPager1TextView.setTextColor(Color.parseColor("#717172"))
            viewPager2TextView.setTextColor(Color.parseColor("#717172"))
            viewPager3TextView.setTextColor(Color.parseColor("#FBA745"))
            descriptionLinearLayout.visibility = View.GONE
            obzorLinearLayout.visibility = View.GONE
            otzivLinearLayout.visibility = View.VISIBLE
        }
    }

    private fun loadProductApiData() {
        ApiRetrofit.getApiClient().getProductInfo(intent.getStringExtra("product_id").toString()).enqueue(object : Callback<ResponseProduct> {
            override fun onResponse(call: Call<ResponseProduct>, response: Response<ResponseProduct>) {

                if (response.isSuccessful) {

                    val responseBody = response.body()!!.response[1]
                    productInfoNameTextView.text = responseBody.nameeda
                    productInfoCenaTextButton.text = ("Добавить " + responseBody.cenaeda + " ₸")
                    productInfoDescriptionTextView.text = responseBody.sostav
                    Glide
                        .with(this@ProductInfoActivity)
                        .load("http://bikli.kz/imgProduct/" + responseBody.image)
                        .into(productInfoImageView)
                }
            }

            override fun onFailure(call: Call<ResponseProduct>, t: Throwable) {
                Toast.makeText(this@ProductInfoActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}