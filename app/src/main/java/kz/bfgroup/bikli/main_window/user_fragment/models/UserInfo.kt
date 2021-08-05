package kz.bfgroup.bikli.main_window.user_fragment.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserInfo(
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("phone")
    val phone: String?,
    @JsonProperty("password")
    val password: String?,
    @JsonProperty("adress")
    val adress: String?,
    @JsonProperty("apartment")
    val apartment: String?,
    @JsonProperty("street")
    val street: String?,
    @JsonProperty("floor")
    val floor: String?,
    @JsonProperty("entrance")
    val entrance: String?,
    @JsonProperty("cashback")
    val cashback: String?,
    @JsonProperty("balans")
    val balans: String?,
    @JsonProperty("referalKey")
    val referalKey: String?,
    @JsonProperty("countReferalUser")
    val countReferalUser: String?,
    @JsonProperty("superAdmin")
    val superAdmin: String?,
)