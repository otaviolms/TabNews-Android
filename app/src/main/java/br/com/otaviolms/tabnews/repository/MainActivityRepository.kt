package br.com.otaviolms.tabnews.repository

import br.com.otaviolms.tabnews.api.TabNewsAPI
import br.com.otaviolms.tabnews.enums.StrategyEnum
import br.com.otaviolms.tabnews.implementations.BaseRepository
import br.com.otaviolms.tabnews.models.ItemConteudoResponseModel
import com.github.javafaker.Faker
import retrofit2.Retrofit

class MainActivityRepository(
    retrofit: Retrofit
): BaseRepository {

    val api = retrofit.create(TabNewsAPI::class.java)

//    suspend fun listarConteudos(
//        page: Int,
//        perPage: Int,
//        strategy: StrategyEnum
//    ) = chamarApi { api.listarConteudos(page, perPage, strategy.nome) }

    suspend fun listarConteudos(
        page: Int,
        perPage: Int,
        strategy: StrategyEnum
    ) = gerarListaItensConteudoResponseModel(perPage)

    fun gerarListaItensConteudoResponseModel(quantidade: Int): ArrayList<ItemConteudoResponseModel> {
        val faker = Faker()
        val listaItens = ArrayList<ItemConteudoResponseModel>()

        for (i in 0 until quantidade) {
            val item = ItemConteudoResponseModel(
                childrenDeepCount = faker.number().randomDigit(),
                createdAt = faker.date().toString(),
                deletedAt = null,
                id = faker.random().toString(),
                ownerId = faker.random().toString(),
                ownerUsername = faker.name().username(),
                parentId = null,
                publishedAt = faker.date().toString(),
                slug = faker.lorem().word(),
                sourceUrl = null,
                status = faker.lorem().word(),
                tabcoins = faker.number().randomDigit(),
                title = faker.lorem().sentence(),
                updatedAt = faker.date().toString()
            )

            listaItens.add(item)
        }

        return listaItens
    }


}