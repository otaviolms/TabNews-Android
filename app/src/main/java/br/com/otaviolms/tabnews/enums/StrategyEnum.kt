package br.com.otaviolms.tabnews.enums

enum class StrategyEnum(
    val nome: String
) {
    RECENTES(nome = "new"),
    ANTIGOS(nome = "old"),
    RELEVANTES(nome = "relevant"),
}