package br.com.otaviolms.tabnews.utils

import android.content.Context
import br.com.otaviolms.tabnews.R
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun calcularHorasPassadas(data: String): Int {
    val formato = DateTimeFormatter.ISO_DATE_TIME
    val dataFornecida = ZonedDateTime.parse(data, formato)
    val dataAtual = LocalDateTime.now().atZone(dataFornecida.zone)
    val diferenca = Duration.between(dataFornecida, dataAtual)
    val resultado = diferenca.toHours().toInt()
    return if(resultado == 0 || resultado <= 0) 0
    else diferenca.toHours().toInt()
}

fun calcularDiasPassados(data: String): Int {
    val formato = DateTimeFormatter.ISO_DATE_TIME
    val dataFornecida = ZonedDateTime.parse(data, formato)
    val dataAtual = LocalDateTime.now().atZone(dataFornecida.zone)
    val diferenca = Duration.between(dataFornecida, dataAtual)
    val resultado = diferenca.toDays().toInt()
    return if(resultado == 0 || resultado <= 0) 0
    else diferenca.toHours().toInt()
}

fun montarStringTempoPassado(context: Context, data: String): String {
    val horasPassadas = calcularHorasPassadas(data)
    if(horasPassadas < 24) {
        return context.resources.getQuantityString(R.plurals.horasPlural, horasPassadas, horasPassadas)
    } else {
        val diasPassados = calcularDiasPassados(data)
        return context.resources.getQuantityString(R.plurals.diasPlural, diasPassados, diasPassados)
    }

}