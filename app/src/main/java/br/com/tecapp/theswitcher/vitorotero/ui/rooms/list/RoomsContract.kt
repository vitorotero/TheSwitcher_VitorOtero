package br.com.tecapp.theswitcher.vitorotero.ui.rooms.list

import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import br.com.tecapp.theswitcher.vitorotero.ui.base.BaseView

interface RoomsContract {

    interface View : BaseView {
        fun setRooms(rooms: List<Rooms>)
    }

    interface Presenter {
        fun getAllRooms()
    }

}