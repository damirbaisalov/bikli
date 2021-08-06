package kz.bfgroup.bikli.main_window.home_fragment.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CafeMenuApiData(
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("nameeda")
    val nameeda: String?,
    @JsonProperty("idcafe")
    val idcafe: String?,
    @JsonProperty("idcategory")
    val idcategory: String?,
    @JsonProperty("cenaeda")
    val cenaeda: String?,
    @JsonProperty("image")
    val image: String?,
    @JsonProperty("sostav")
    val sostav: String?,
    @JsonProperty("cenaeda_old")
    val cenaeda_old: String?,
    @JsonProperty("rating")
    val rating: String?
)