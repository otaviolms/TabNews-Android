package br.com.otaviolms.tabnews.utils

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