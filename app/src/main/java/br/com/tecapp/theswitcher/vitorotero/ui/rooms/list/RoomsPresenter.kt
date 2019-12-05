package br.com.tecapp.theswitcher.vitorotero.ui.rooms.list

import br.com.tecapp.theswitcher.vitorotero.shared.interactor.RoomsInteractor
import br.com.tecapp.theswitcher.vitorotero.ui.base.BasePresenterImp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RoomsPresenter(private val roomsInteractor: RoomsInteractor, view: RoomsContract.View) :
    BasePresenterImp<RoomsContract.View>(view), RoomsContract.Presenter {

    override fun getAllRooms() {
        addDisposable(
            roomsInteractor.getAllRooms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { rooms -> view?.setRooms(rooms) },
                    { })
        )
    }


}