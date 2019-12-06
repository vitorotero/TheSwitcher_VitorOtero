package br.com.tecapp.theswitcher.vitorotero.ui.rooms.detail

import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import br.com.tecapp.theswitcher.vitorotero.ui.base.BasePresenter
import br.com.tecapp.theswitcher.vitorotero.ui.base.BaseView

interface RoomsDetailContract {

    interface View : BaseView {
        fun showRoomSelected(room: Rooms)
    }

    interface Presenter : BasePresenter {
        fun setRoomSelected(room: Rooms)
    }

}