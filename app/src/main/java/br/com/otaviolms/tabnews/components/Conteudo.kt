package br.com.otaviolms.tabnews.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.otaviolms.tabnews.databinding.ComponenteConteudoBinding
import br.com.otaviolms.tabnews.extensions.makeGone
import br.com.otaviolms.tabnews.extensions.makeVisible
import br.com.otaviolms.tabnews.implementations.callbacks.ConteudoCallbacks
import br.com.otaviolms.tabnews.models.responses.PostResponseModel
import br.com.otaviolms.tabnews.utils.montarStringTempoPassado
import io.noties.markwon.Markwon
import io.noties.markwon.image.glide.GlideImagesPlugin

class Conteudo(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    private val bnd = ComponenteConteudoBinding.inflate(LayoutInflater.from(context), this, true)

    fun renderPost(conteudo: PostResponseModel, callback: ConteudoCallbacks) {
        with(conteudo) {
            bnd.txvAutor.text = autor
            bnd.txvData.text = montarStringTempoPassado(context, publishedAt)
            bnd.txvTabCoins.text = tabcoins.toString()
            bnd.txvConteudo.text = body
            bnd.txvTitulo.text = titulo

            if(parentId != null) bnd.txvTitulo.makeVisible()
            else bnd.txvTitulo.makeGone()

            bnd.imvBaixo.setOnClickListener { callback.onBaixo() }
            bnd.imvCima.setOnClickListener { callback.onCima() }
            bnd.txvAutor.setOnClickListener { callback.onAutor(autor) }

            Markwon.builder(context)
                .usePlugin(GlideImagesPlugin.create(context))
                .build()
                .setMarkdown(bnd.txvConteudo, body ?: "")
        }
    }

}