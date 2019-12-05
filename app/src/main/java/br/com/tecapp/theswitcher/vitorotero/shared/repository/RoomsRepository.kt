package br.com.tecapp.theswitcher.vitorotero.shared.repository

import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import io.reactivex.Single
import io.realm.Realm

interface RoomsRepository : AppDatabase {

//    fun findAll(): Single<List<Rooms>>

}

class RoomsRepositoryImp(realm: Realm) : AppDatabaseImp<Realm>(realm), RoomsRepository {

//    override fun findAll(): Single<List<Rooms>> {
//        val roomsResults = realm.where(Rooms::class.java)
//            .findAll()
//
//        return roomsResults?.let {
//            return@let realm.copyFromRealm(it).let { rooms ->
//                Single.just(rooms)
//            }
//        }
//
//
//    }


}