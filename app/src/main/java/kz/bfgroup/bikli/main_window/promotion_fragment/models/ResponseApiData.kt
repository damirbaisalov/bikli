package kz.bfgroup.bikli.main_window.promotion_fragment.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResponseApiData(
    @JsonProperty("response")
    var response: List<PromotionApiData>
)