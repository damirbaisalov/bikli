package kz.bfgroup.bikli.registration.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResponseUserToken(
    @JsonProperty("token")
    val token: String?,

    @JsonProperty("user_id")
    val user_id: String?,

    @JsonProperty("check")
    val check: Boolean?,
)