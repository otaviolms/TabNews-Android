package br.com.otaviolms.tabnews.models.responses

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PostResponseModel(
    @JsonProperty("body") val body: String? = null,
    @JsonProperty("children_deep_count") val comentarios: Int,
    @JsonProperty("created_at") val createdAt: String,
    @JsonProperty("deleted_at") val deletedAt: Any?,
    @JsonProperty("id") val id: String,
    @JsonProperty("owner_id") val ownerId: String,
    @JsonProperty("owner_username") val autor: String,
    @JsonProperty("parent_id") val parentId: Any?,
    @JsonProperty("published_at") val publishedAt: String,
    @JsonProperty("slug") val slug: String,
    @JsonProperty("source_url") val sourceUrl: Any?,
    @JsonProperty("status") val status: String,
    @JsonProperty("tabcoins") var tabcoins: Int,
    @JsonProperty("title") val titulo: String?,
    @JsonProperty("updated_at") val updatedAt: String,
    @JsonProperty("nivel") var nivel: Int = 0
)