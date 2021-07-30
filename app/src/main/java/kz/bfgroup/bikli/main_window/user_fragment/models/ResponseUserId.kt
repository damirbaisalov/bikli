package kz.bfgroup.bikli.main_window.user_fragment.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResponseUserId(
    @JsonProperty("user_id")
    val user_id: String?
)