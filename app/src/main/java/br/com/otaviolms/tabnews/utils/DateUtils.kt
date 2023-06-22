package br.com.otaviolms.tabnews.utils

import android.content.Context
import br.com.otaviolms.tabnews.R
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun calcularHorasPassadas(data: String): Long {
    val formato = DateTimeFormatter.ISO_DATE_TIME
    val dataFornecida = ZonedDateTime.parse(data, formato)
    val dataAtual = LocalDateTime.now().atZone(dataFornecida.zone)
    val diferenca = Duration.between(dataFornecida, dataAtual)
    val resultado = diferenca.toHours().toInt()
    return if(resultado == 0 || resultado <= 0) 0
    else diferenca.toHours()
}

fun calcularDiasPassados(data: String): Int {
    val formato = DateTimeFormatter.ISO_DATE_TIME
    val dataFornecida = ZonedDateTime.parse(data, formato)
    val dataAtual = LocalDateTime.now().atZone(dataFornecida.zone)
    val diferenca = Duration.between(dataFornecida, dataAtual)
    val resultado = diferenca.toDays().toInt()
    return if(resultado == 0 || resultado <= 0) 0
    else diferenca.toDays().toInt()
}

fun montarStringTempoPassado(context: Context, data: String): String {
    val horasPassadas = calcularHorasPassadas(data)
    return when{
        horasPassadas < 1.0 -> context.resources.getQuantityString(R.plurals.horasPlural, 0, 0)
        horasPassadas < 24.0 -> context.resources.getQuantityString(R.plurals.horasPlural, horasPassadas.toInt(), horasPassadas.toInt())
        else -> {
            val diasPassados = calcularDiasPassados(data)
            context.resources.getQuantityString(R.plurals.diasPlural, diasPassados, diasPassados)
        }
    }
}