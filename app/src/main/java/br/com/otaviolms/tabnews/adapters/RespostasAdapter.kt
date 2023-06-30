package br.com.otaviolms.tabnews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.otaviolms.tabnews.databinding.ItemConteudoBinding
import br.com.otaviolms.tabnews.implementations.callbacks.ConteudoCallbacks
import br.com.otaviolms.tabnews.models.responses.PostResponseModel

class RespostasAdapter(
    private var dataSet: ArrayList<PostResponseModel> = arrayListOf(),
    private val callback: ConteudoCallbacks
): Adapter<RespostasAdapter.RespostaItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RespostaItemViewHolder(ItemConteudoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RespostaItemViewHolder, position: Int) {
        holder.bnd.conteudo.renderPost(conteudo = dataSet[position], callback = callback)
    }

    override fun getItemCount() = dataSet.size

    inner class RespostaItemViewHolder(val bnd: ItemConteudoBinding): RecyclerView.ViewHolder(bnd.root)

    fun definirConteudo(itens: ArrayList<PostResponseModel>) {
        dataSet = itens
        notifyDataSetChanged()
    }

}