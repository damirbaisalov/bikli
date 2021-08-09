package kz.bfgroup.bikli.main_window.home_fragment.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductInfoApiData(
    @JsonProperty("nameeda")
    val nameeda: String?,
    @JsonProperty("cenaeda")
    val cenaeda: String?,
    @JsonProperty("image")
    val image: String?,
    @JsonProperty("sostav")
    val sostav: String?
)