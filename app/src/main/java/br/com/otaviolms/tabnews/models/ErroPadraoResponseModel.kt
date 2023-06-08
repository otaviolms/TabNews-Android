package br.com.otaviolms.tabnews.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ErroPadraoResponseModel(
    @JsonProperty("action") val action: String,
    @JsonProperty("error_id") val errorId: String,
    @JsonProperty("error_location_code") val errorLocationCode: String,
    @JsonProperty("key") val key: String,
    @JsonProperty("message") val message: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("request_id") val requestId: String,
    @JsonProperty("status_code") val statusCode: Int,
    @JsonProperty("type") val type: String
)