package kz.bfgroup.bikli.registration.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResponseUserTokenInfo(
    @JsonProperty("response")
    val response: ResponseUserToken
)