package br.com.otaviolms.tabnews.models.responses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class UsuarioResponseModel(
    @JsonProperty("id") val id: String,
    @JsonProperty("username") val username: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("notifications") val notifications: Boolean,
    @JsonProperty("features") val features: List<String>,
    @JsonProperty("tabcoins") val tabcoins: Int,
    @JsonProperty("tabcash") val tabcash: Int,
    @JsonProperty("created_at") val createdAt: String,
    @JsonProperty("updated_at") val updatedAt: String,
)