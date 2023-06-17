package br.com.otaviolms.tabnews.models.responses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginResponseModel(
    @JsonProperty("created_at") val createdAt: String,
    @JsonProperty("expires_at") val expiresAt: String,
    @JsonProperty("id") val id: String,
    @JsonProperty("token") val token: String,
    @JsonProperty("updated_at") val updatedAt: String
)