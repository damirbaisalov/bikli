package kz.bfgroup.bikli.data

import kz.bfgroup.bikli.main_window.promotion_fragment.models.ResponseApiData
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {

    @GET("index.php?method=akcii.SpisokAkcii")
    fun getPromotions2(): Call<ResponseApiData>
}