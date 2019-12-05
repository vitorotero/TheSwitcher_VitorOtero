package br.com.tecapp.theswitcher.vitorotero.shared.interactor

import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import br.com.tecapp.theswitcher.vitorotero.shared.repository.RoomsDao
import io.reactivex.Completable
import io.reactivex.Single

interface RoomsInteractor {
    fun getAllRooms(): Single<List<Rooms>>
    fun updateRoom(room: Rooms): Completable
}

class RoomsInteractorImp(private val roomsDao: RoomsDao) : RoomsInteractor {

    override fun getAllRooms(): Single<List<Rooms>> {
        return Single.just(roomsDao.getAllRooms())
    }

    override fun updateRoom(room: Rooms): Completable {
        return roomsDao.update(room)
    }

}