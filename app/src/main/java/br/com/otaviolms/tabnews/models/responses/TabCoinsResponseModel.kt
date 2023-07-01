package br.com.otaviolms.tabnews.models.responses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TabCoinsResponseModel(
    @JsonProperty("tabcoins") val tabcoins: Int,
)