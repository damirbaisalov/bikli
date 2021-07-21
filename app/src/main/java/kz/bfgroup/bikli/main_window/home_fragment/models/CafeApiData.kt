package kz.bfgroup.bikli.main_window.home_fragment.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CafeApiData(
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("vremya")
    val vremya: String?,
    @JsonProperty("cenadost")
    val cenadost: String?,
    @JsonProperty("cenadost_mikro")
    val cenadost_mikro: String?,
    @JsonProperty("akciya")
    val akciya: String?,
    @JsonProperty("timestart")
    val timestart: String?,
    @JsonProperty("timeend")
    val timeend: String?,
    @JsonProperty("logo")
    val logo: String?,
    @JsonProperty("kafehead")
    val kafehead: String?,
    @JsonProperty("opis")
    val opis: String?,
    @JsonProperty("minsumma")
    val minsumma: String?,
    @JsonProperty("reiting")
    val reiting: String?,
    @JsonProperty("catalog")
    val catalog: String?,
    @JsonProperty("rating")
    val rating: Int?,
    @JsonProperty("now")
    val now: String?,
    @JsonProperty("status")
    val status: String?
)