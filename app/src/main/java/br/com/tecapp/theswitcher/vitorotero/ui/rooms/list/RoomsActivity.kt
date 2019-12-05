package br.com.tecapp.theswitcher.vitorotero.ui.rooms.list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.tecapp.theswitcher.vitorotero.R
import br.com.tecapp.theswitcher.vitorotero.shared.interactor.RoomsInteractor
import br.com.tecapp.theswitcher.vitorotero.shared.interactor.RoomsInteractorImp
import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import br.com.tecapp.theswitcher.vitorotero.shared.repository.SwitcherDatabase
import br.com.tecapp.theswitcher.vitorotero.ui.rooms.detail.RoomsDetailActivity
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

    override fun showEmptyView() {
        Toast.makeText(this, getString(R.string.rooms_list_empty_list), Toast.LENGTH_SHORT)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    override fun setRooms(rooms: List<Rooms>) {
        adapter.setList(rooms)
    }

    override fun onItemClick(item: Rooms) {
        startActivity(RoomsDetailActivity.getStartIntent(this, item))
    }

    override fun onStatusChange(item: Rooms) {
        presenter?.updateRoom(item)
    }

    override fun showMessageStatus(status: Boolean) {
        val message = if (status) {
            getString(R.string.rooms_list_light_turn_on)
        } else {
            getString(R.string.rooms_list_light_turn_off)
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun bindView() {
        val database = SwitcherDatabase.getInstance(this)
        val interactor: RoomsInteractor = RoomsInteractorImp(database.roomsDao())
        presenter = RoomsPresenter(interactor, this)

        adapter = RoomAdapter(this)
    }

    private fun setupView() {
        rvRooms.adapter = adapter
        rvRooms.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvRooms.setHasFixedSize(false)
    }

}
