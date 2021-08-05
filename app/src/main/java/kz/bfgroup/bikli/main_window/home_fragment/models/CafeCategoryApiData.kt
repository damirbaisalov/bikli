package kz.bfgroup.bikli.main_window.home_fragment.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CafeCategoryApiData(
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("idcafe")
    val idcafe: String?,
    @JsonProperty("namecategory")
    val namecategory: String?,
    @JsonProperty("position")
    val position: String?,
    @JsonProperty("sub")
    val sub: String?,
    @JsonProperty("adult")
    val adult: String?,
    @JsonProperty("st")
    val st: String?,
)