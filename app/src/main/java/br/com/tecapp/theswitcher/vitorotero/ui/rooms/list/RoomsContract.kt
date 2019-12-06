package br.com.tecapp.theswitcher.vitorotero.ui.rooms.list

import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import br.com.tecapp.theswitcher.vitorotero.ui.base.BasePresenter
import br.com.tecapp.theswitcher.vitorotero.ui.base.BaseView

interface RoomsContract {

    interface View : BaseView {
        fun setRooms(rooms: List<Rooms>)
        fun showEmptyView()
        fun showMessageStatus(status: Boolean)
    }

    interface Presenter : BasePresenter {
        fun getAllRooms()
        fun updateRoom(room: Rooms)
    }

}