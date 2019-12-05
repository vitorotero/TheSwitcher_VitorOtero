package br.com.tecapp.theswitcher.vitorotero

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class TheSwitcherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)

        val config = RealmConfiguration.Builder()
            .build()
        Realm.setDefaultConfiguration(config)
    }
}