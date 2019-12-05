package br.com.tecapp.theswitcher.vitorotero.shared.interactor

import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import io.reactivex.Single

interface RoomsInteractor {
    fun getAllRooms(): Single<List<Rooms>>
}

class RoomsInteractorImp : RoomsInteractor {

    override fun getAllRooms(): Single<List<Rooms>> {
        return Single.just(emptyList())
    }

}