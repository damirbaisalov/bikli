package kz.bfgroup.bikli.main_window.user_fragment.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResponseAddressBody(
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("idclient")
    val idclient: String?,
    @JsonProperty("adr")
    val adr: String?,
    @JsonProperty("street")
    val street: String?,
    @JsonProperty("apartmetnts")
    val apartments: String?,
    @JsonProperty("floor")
    val floor: String?,
    @JsonProperty("entrance")
    val entrance: String?,
)