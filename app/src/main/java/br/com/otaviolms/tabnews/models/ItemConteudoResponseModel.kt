package br.com.otaviolms.tabnews.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ItemConteudoResponseModel(
    @JsonProperty("children_deep_count") val childrenDeepCount: Int,
    @JsonProperty("created_at") val createdAt: String,
    @JsonProperty("deleted_at") val deletedAt: Any?,
    @JsonProperty("id") val id: String,
    @JsonProperty("owner_id") val ownerId: String,
    @JsonProperty("owner_username") val ownerUsername: String,
    @JsonProperty("parent_id") val parentId: Any?,
    @JsonProperty("published_at") val publishedAt: String,
    @JsonProperty("slug") val slug: String,
    @JsonProperty("source_url") val sourceUrl: Any?,
    @JsonProperty("status") val status: String,
    @JsonProperty("tabcoins") val tabcoins: Int,
    @JsonProperty("title") val title: String,
    @JsonProperty("updated_at") val updatedAt: String
)