package br.com.otaviolms.tabnews.models.requests

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class UpDownVoteRequestModel(
    @JsonProperty("transaction_type") val transaction_type: String,
)