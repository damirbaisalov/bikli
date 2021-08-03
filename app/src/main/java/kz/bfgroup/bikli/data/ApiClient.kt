package kz.bfgroup.bikli.data

import kz.bfgroup.bikli.main_window.home_fragment.models.ResponseCafeApiData
import kz.bfgroup.bikli.main_window.promotion_fragment.models.ResponseApiData
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseUser
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseUserInfo
import kz.bfgroup.bikli.registration.models.ResponseUserTokenInfo
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {

    @GET("index.php?method=akcii.SpisokAkcii")
    fun getPromotions(): Call<ResponseApiData>

    @GET("index.php?method=cafe.cafeList")
    fun getCafesList(): Call<ResponseCafeApiData>

    @FormUrlEncoded
    @POST("index.php?method=user.send.sms")
    fun registerUser(@FieldMap fields: Map<String, String>) : Call<ResponseUserInfo>

    @FormUrlEncoded
    @POST("index.php?method=user.login")
    fun loginUser(@FieldMap fields: Map<String, String>) : Call<ResponseUserTokenInfo>

    @FormUrlEncoded
    @POST("index.php?method=user.get.data")
    fun userGetData(@Field("access_token") access_token:  String) : Call<ResponseUser>

}