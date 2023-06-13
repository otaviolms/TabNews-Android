package br.com.otaviolms.tabnews.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.otaviolms.tabnews.R
import br.com.otaviolms.tabnews.databinding.ItemConteudoBinding
import br.com.otaviolms.tabnews.models.PostResponseModel
import br.com.otaviolms.tabnews.utils.calcularHorasPassadas

class ConteudosAdapter(
    private val context: Context,
    private var dataSet: ArrayList<PostResponseModel> = arrayListOf(),
    private val onClick: (PostResponseModel) -> Unit
): Adapter<ConteudosAdapter.ConteudoItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ConteudoItemViewHolder(ItemConteudoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ConteudoItemViewHolder, position: Int) {
        with(dataSet[position]) {
            val stringComentarios = context.resources.getQuantityString(R.plurals.comentariosPlural, comentarios, comentarios)
//            val stringHora = "HORAS ATRÁS"
            val stringHora = context.resources.getQuantityString(R.plurals.horasPlural, calcularHorasPassadas(publishedAt), calcularHorasPassadas(publishedAt))

            holder.bnd.txvNumero.text = context.getString(R.string.numeroConteudo, (position + 1))
            holder.bnd.txvTitle.text = titulo
            holder.bnd.txvInfos.text = context.getString(
                R.string.subtituloConteudo,
                tabcoins, // TABCOINS
                stringComentarios, // COMENTÁRIOS
                autor, // USERNAME
                stringHora // HORAS
            )

            holder.bnd.root.setOnClickListener { onClick.invoke(this) }
        }

    }

    override fun getItemCount() = dataSet.size

    inner class ConteudoItemViewHolder(val bnd: ItemConteudoBinding): RecyclerView.ViewHolder(bnd.root)

    fun definirConteudo(itens: ArrayList<PostResponseModel>) {
        dataSet = itens
        notifyDataSetChanged()
    }

    fun adicionarConteudo(itens: ArrayList<PostResponseModel>) {
        val tamanhoAntes = dataSet.lastIndex
        dataSet.addAll(itens)
        notifyItemRangeInserted(tamanhoAntes, itens.size)
    }

}