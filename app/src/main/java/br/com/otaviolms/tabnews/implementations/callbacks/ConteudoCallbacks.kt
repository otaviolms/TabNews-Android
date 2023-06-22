package br.com.otaviolms.tabnews.implementations.callbacks

import br.com.otaviolms.tabnews.models.responses.PostResponseModel

interface ConteudoCallbacks {
    fun onAutor(autor: String)
    fun onBaixo()
    fun onCima()
    fun onResponder()
    fun onClick(post: PostResponseModel)
}