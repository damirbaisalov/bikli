package kz.bfgroup.bikli.data

import com.fasterxml.jackson.databind.JsonNode
import kz.bfgroup.bikli.main_window.promotion_fragment.models.PromotionApiData
import kz.bfgroup.bikli.main_window.promotion_fragment.models.ResponseApiData
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {

    @GET("index.php?method=akcii.SpisokAkcii")
    fun getPromotionsList(): Call<List<PromotionApiData>>

    @GET("index.php?method=akcii.SpisokAkcii")
    fun getPromotionJson(): Call<JsonNode>

    @GET("index.php?method=akcii.SpisokAkcii")
    fun getPromotions2(): Call<ResponseApiData>
}