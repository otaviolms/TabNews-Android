package br.com.otaviolms.tabnews.views.post

import br.com.otaviolms.tabnews.api.ConteudosAPI
import br.com.otaviolms.tabnews.connections.RetrofitBuilder
import br.com.otaviolms.tabnews.enums.TipoVoteEnum
import br.com.otaviolms.tabnews.implementations.bases.BaseRepository
import br.com.otaviolms.tabnews.models.requests.UpDownVoteRequestModel

class PostRepository : BaseRepository {

    private val api: ConteudosAPI = RetrofitBuilder.retrofit.create(ConteudosAPI::class.java)

    suspend fun upDownVote(
        autor: String,
        slug: String,
        tipo: TipoVoteEnum
    ) = chamarApi {
        api.upDownVote(
            autor = autor,
            slug = slug,
            tipo = UpDownVoteRequestModel(tipo.nome)
        )
    }

    suspend fun carregarPost(
        autor: String,
        slug: String,
    ) = chamarApi { api.carregarPost(autor, slug) }

    suspend fun carregarComentarios(
        autor: String,
        slug: String,
    ) = chamarApi { api.carregarComentarios(autor, slug) }

}