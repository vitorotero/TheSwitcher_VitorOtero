package br.com.tecapp.theswitcher.vitorotero.ui.rooms.list

import br.com.tecapp.theswitcher.vitorotero.ui.base.BaseView

interface RoomsContract {

    interface View : BaseView {}

    interface Presenter {
        fun getAllRooms()
    }

}