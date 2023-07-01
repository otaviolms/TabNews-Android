package br.com.otaviolms.tabnews.implementations.callbacks

import br.com.otaviolms.tabnews.enums.TipoVoteEnum
import br.com.otaviolms.tabnews.models.responses.PostResponseModel

interface ConteudoCallbacks {
    fun onAutor(autor: String)
    fun onVote(vote: TipoVoteEnum, posicao: Int)
    fun onResponder()
    fun onClick(post: PostResponseModel)
}