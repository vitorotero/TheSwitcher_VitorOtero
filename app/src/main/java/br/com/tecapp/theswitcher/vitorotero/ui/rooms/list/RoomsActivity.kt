package br.com.tecapp.theswitcher.vitorotero.ui.rooms.list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.tecapp.theswitcher.vitorotero.R
import br.com.tecapp.theswitcher.vitorotero.shared.interactor.RoomsInteractor
import br.com.tecapp.theswitcher.vitorotero.shared.interactor.RoomsInteractorImp
import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import br.com.tecapp.theswitcher.vitorotero.ui.rooms.list.adapter.RoomAdapter
import br.com.tecapp.theswitcher.vitorotero.ui.rooms.list.adapter.RoomAdapterListener
import kotlinx.android.synthetic.main.rooms_screen.*

class RoomsActivity : AppCompatActivity(), RoomsContract.View, RoomAdapterListener {

    private var presenter: RoomsPresenter? = null
    private lateinit var adapter: RoomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rooms_screen)

        bindView()
        setupView()

        presenter?.getAllRooms()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    override fun setRooms(rooms: List<Rooms>) {
        adapter.setList(rooms)
    }

    override fun onItemClick(item: Rooms) {
        Toast.makeText(this, item.name, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onStatusChange(item: Rooms) {
    }

    private fun bindView() {
        val interactor: RoomsInteractor = RoomsInteractorImp()
        presenter = RoomsPresenter(interactor, this)

        adapter = RoomAdapter(this)
    }

    private fun setupView() {
        rvRooms.adapter = adapter
        rvRooms.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvRooms.setHasFixedSize(false)
    }

}
