package br.com.otaviolms.tabnews.extensions

import br.com.otaviolms.tabnews.models.responses.PostResponseModel


fun ArrayList<PostResponseModel>.calcularNiveis(): ArrayList<PostResponseModel> {
    val postsComNiveis = ArrayList<PostResponseModel>()

    // Mapear cada postagem inicial
    this.filter { it.parentId == null }.forEach { postagem ->
        postagem.nivel = 0 // Nível inicial para postagens principais
        postsComNiveis.add(postagem)
        calcularNiveisRecursivamente(postagem, this, postsComNiveis, 0)
    }

    return postsComNiveis
}

private fun calcularNiveisRecursivamente(
    postagem: PostResponseModel,
    listaOriginal: ArrayList<PostResponseModel>,
    listaComNiveis: ArrayList<PostResponseModel>,
    nivel: Int
) {
    // Filtrar os comentários que têm parentId igual ao ID da postagem atual
    val comentarios = listaOriginal.filter { it.parentId == postagem.id }

    // Atualizar o nível dos comentários e adicioná-los à lista
    comentarios.forEach { comentario ->
        comentario.nivel = nivel + 1
        listaComNiveis.add(comentario)
        calcularNiveisRecursivamente(comentario, listaOriginal, listaComNiveis, nivel + 1)
    }
}

