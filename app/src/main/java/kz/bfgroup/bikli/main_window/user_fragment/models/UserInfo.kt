package kz.bfgroup.bikli.main_window.user_fragment.models

import retrofit2.http.Field

data class UserInfo(
    @Field("phone_user")
    val phone_user: String?,
    @Field("name_user")
    val name_user: String?,
    @Field("adress_user")
    val adress_user: String?,
    @Field("apartment_user")
    val apartment_user: String?,
    @Field("floor")
    val floor: String?,
    @Field("street")
    val street: String?
)