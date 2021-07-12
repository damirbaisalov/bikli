package kz.bfgroup.bikli.main_window.promotion_fragment.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PromotionApiData(
    @JsonProperty("idcafe")
    val idcafe: String?,
    @JsonProperty("idCat")
    val idCat: String?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("opis")
    val opis: String?,
    @JsonProperty("img")
    val img: String?,
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("ver_stat")
    val verStat: String?
)