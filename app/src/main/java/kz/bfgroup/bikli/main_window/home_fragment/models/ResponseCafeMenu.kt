package kz.bfgroup.bikli.main_window.home_fragment.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResponseCafeMenu(
    @JsonProperty("response")
    var response: List<CafeMenuApiData>
)