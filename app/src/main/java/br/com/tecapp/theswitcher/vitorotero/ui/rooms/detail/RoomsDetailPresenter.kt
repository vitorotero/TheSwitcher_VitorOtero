package br.com.tecapp.theswitcher.vitorotero.ui.rooms.detail

import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import br.com.tecapp.theswitcher.vitorotero.ui.base.BasePresenterImp

class RoomsDetailPresenter(view: RoomsDetailContract.View) :
    BasePresenterImp<RoomsDetailContract.View>(view), RoomsDetailContract.Presenter {

    private lateinit var roomSelected: Rooms

    override fun setRoomSelected(room: Rooms) {
        roomSelected = room
        view?.showRoomSelected(roomSelected)
    }

}