package kz.bfgroup.bikli.data

import kz.bfgroup.bikli.main_window.home_fragment.models.ResponseCafeApiData
import kz.bfgroup.bikli.main_window.home_fragment.models.ResponseCafeMenu
import kz.bfgroup.bikli.main_window.home_fragment.models.ResponseCategory
import kz.bfgroup.bikli.main_window.promotion_fragment.models.ResponseApiData
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseAddress
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseAddressUpdate
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseUser
import kz.bfgroup.bikli.main_window.user_fragment.models.ResponseUserInfo
import kz.bfgroup.bikli.registration.models.ResponseUserTokenInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {

    @GET("index.php?method=akcii.SpisokAkcii")
    fun getPromotions(): Call<ResponseApiData>

    @GET("index.php?method=cafe.cafeList")
    fun getCafesList(): Call<ResponseCafeApiData>

    @FormUrlEncoded
    @POST("index.php?method=cafe.cafeCategories")
    fun getCafeCategories(@Field("id") id: String) : Call<ResponseCategory>

    @FormUrlEncoded
    @POST("index.php?method=cafe.cafeMenuCategoriesOrderByRating")
    fun getCafeMenuByRating(@Field("id") id: String) : Call<ResponseCafeMenu>

    @FormUrlEncoded
    @POST("index.php?method=cafe.cafeInfo")
    fun getCafeInfo(@Field("id") id: String) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("index.php?method=user.send.sms")
    fun registerUser(@FieldMap fields: Map<String, String>) : Call<ResponseUserInfo>

    @FormUrlEncoded
    @POST("index.php?method=user.login")
    fun loginUser(@FieldMap fields: Map<String, String>) : Call<ResponseUserTokenInfo>

    @FormUrlEncoded
    @POST("index.php?method=user.get.data")
    fun userGetData(@Field("access_token") access_token:  String) : Call<ResponseUser>

    @FormUrlEncoded
    @POST("index.php?method=user.set.data")
    fun userSetData(@FieldMap fields: Map<String, String>) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("index.php?method=user.adress.get")
    fun userGetAddress(@Field("user_id") user_id: String) : Call<ResponseAddress>

    @FormUrlEncoded
    @POST("index.php?method=user.adress.add")
    fun userAddAddress(@FieldMap fields: Map<String, String>) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("index.php?method=user.adress.getCurrent")
    fun userGetAddressById(@Field("id") id: String) : Call<ResponseAddressUpdate>

    @FormUrlEncoded
    @POST("index.php?method=user.set.adress")
    fun userSetAddress(@FieldMap fields: Map<String, String>) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("index.php?method=user.adress.delete")
    fun userDeleteAddress(@Field("id") id: String) : Call<ResponseBody>

}