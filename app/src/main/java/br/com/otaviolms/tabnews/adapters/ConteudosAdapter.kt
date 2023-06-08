package br.com.otaviolms.tabnews.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.otaviolms.tabnews.R
import br.com.otaviolms.tabnews.databinding.ItemConteudoBinding
import br.com.otaviolms.tabnews.models.ItemConteudoResponseModel
import br.com.otaviolms.tabnews.utils.calcularHorasPassadas
import java.util.Date

class ConteudosAdapter(
    private val context: Context,
    private var dataSet: ArrayList<ItemConteudoResponseModel> = arrayListOf()
): Adapter<ConteudosAdapter.ConteudoItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ConteudoItemViewHolder(ItemConteudoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ConteudoItemViewHolder, position: Int) {
        with(dataSet[position]) {
            val stringTabcoins = context.resources.getQuantityString(R.plurals.tabcoinsPlural, tabcoins)
            val stringComentarios = context.resources.getQuantityString(R.plurals.comentariosPlural, childrenDeepCount)
            val stringHora = "HORAS ATRÁS"
//            val stringHora = context.resources.getQuantityString(R.plurals.horasPlural, calcularHorasPassadas(publishedAt))

            holder.bnd.txvNumero.text = context.getString(R.string.numeroConteudo, position)
            holder.bnd.txvTitle.text = title
            holder.bnd.txvInfos.text = context.getString(
                R.string.subtituloConteudo,
                stringTabcoins, // TABCOINS
                stringComentarios, // COMENTÁRIOS
                ownerUsername, // USERNAME
                stringHora // HORAS
            )
        }

    }

    override fun getItemCount() = dataSet.size

    inner class ConteudoItemViewHolder(val bnd: ItemConteudoBinding): RecyclerView.ViewHolder(bnd.root)

    fun definirConteudo(itens: ArrayList<ItemConteudoResponseModel>) {
        dataSet = itens
        notifyDataSetChanged()
    }

    fun adicionarConteudo(itens: ArrayList<ItemConteudoResponseModel>) {
        val tamanhoAntes = dataSet.lastIndex
        dataSet.addAll(itens)
        notifyItemRangeInserted(tamanhoAntes, itens.size)
    }

}