package br.com.otaviolms.tabnews.enums

enum class StrategyEnum(
    val nome: String
) {
    NOVOS(nome = "new"),
    ANTIGOS(nome = "old"),
    RELEVANTES(nome = "relevant"),
}