package br.com.tecapp.theswitcher.vitorotero.shared.repository

import io.realm.Realm

interface AppDatabase {
    fun detach()
}

open class AppDatabaseImp<B : Realm>(val realm: B) : AppDatabase {

    override fun detach() {
        realm.close()
    }

}