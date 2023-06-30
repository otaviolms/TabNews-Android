package br.com.otaviolms.tabnews.views

import android.app.Application
import br.com.otaviolms.tabnews.modules.obterModulesKoin
import org.koin.core.context.GlobalContext.startKoin

class TabNewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { modules(obterModulesKoin()) }
    }
}