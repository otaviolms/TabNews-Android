package br.com.otaviolms.tabnews.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.otaviolms.tabnews.databinding.ComponenteConteudoBinding
import br.com.otaviolms.tabnews.databinding.ItemHierarquiaBinding
import br.com.otaviolms.tabnews.enums.TipoVoteEnum
import br.com.otaviolms.tabnews.extensions.makeGone
import br.com.otaviolms.tabnews.extensions.makeVisible
import br.com.otaviolms.tabnews.implementations.callbacks.ConteudoCallbacks
import br.com.otaviolms.tabnews.models.responses.PostResponseModel
import br.com.otaviolms.tabnews.utils.montarStringTempoPassado
import io.noties.markwon.Markwon
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.ext.tasklist.TaskListPlugin
import io.noties.markwon.image.glide.GlideImagesPlugin


class Conteudo(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    private val bnd = ComponenteConteudoBinding.inflate(LayoutInflater.from(context), this, true)

    fun renderPost(conteudo: PostResponseModel, callback: ConteudoCallbacks, posicao: Int) {
        with(conteudo) {
            bnd.txvAutor.text = autor
            bnd.txvData.text = montarStringTempoPassado(context, publishedAt)
            bnd.txvTabCoins.text = tabcoins.toString()
            bnd.txvTitulo.text = titulo

            if (parentId == null) bnd.txvTitulo.makeVisible()
            else bnd.txvTitulo.makeGone()

            bnd.imvBaixo.setOnClickListener { callback.onVote(TipoVoteEnum.DEBITO, posicao) }
            bnd.imvCima.setOnClickListener { callback.onVote(TipoVoteEnum.CREDITO, posicao) }
            bnd.txvAutor.setOnClickListener { callback.onAutor(autor) }

            if (this.nivel > 0) {
                bnd.llHierarquia.makeVisible()
                renderHierarquia(nivel = nivel, parent = bnd.llHierarquia)
            } else bnd.llHierarquia.makeGone()

            Markwon.builder(context)
                .usePlugin(GlideImagesPlugin.create(context))
                .usePlugin(TablePlugin.create(context))
                .usePlugin(TaskListPlugin.create(context))
                .build()
                .setMarkdown(bnd.txvConteudo, body ?: "")
        }
    }

    private fun renderHierarquia(nivel: Int, parent: ViewGroup) {
        parent.removeAllViews()
        for (i in 0 until nivel) ItemHierarquiaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            true
        )
    }

}