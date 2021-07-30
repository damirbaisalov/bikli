package kz.bfgroup.bikli.main_window.user_fragment.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResponseUserInfo(
    @JsonProperty("response")
    val response: ResponseUserId?
)