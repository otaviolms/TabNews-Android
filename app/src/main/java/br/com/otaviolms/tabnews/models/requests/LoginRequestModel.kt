package br.com.otaviolms.tabnews.models.requests

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginRequestModel(
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String
)