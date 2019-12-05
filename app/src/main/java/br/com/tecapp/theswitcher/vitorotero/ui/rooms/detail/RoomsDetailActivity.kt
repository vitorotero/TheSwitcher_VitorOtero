package br.com.tecapp.theswitcher.vitorotero.ui.rooms.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.tecapp.theswitcher.vitorotero.R
import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import kotlinx.android.synthetic.main.room_detail_screen.*

class RoomsDetailActivity : AppCompatActivity(), RoomsDetailContract.View {

    private var presenter: RoomsDetailContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.room_detail_screen)

        bindView()
        setupView()
    }

    override fun showRoomSelected(room: Rooms) {
        val text = if (room.status) {
            getString(R.string.light_on)
        } else {
            getString(R.string.light_off)
        }

        tvMessage.text = getString(R.string.detail_room_message, text)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun bindView() {
        presenter = RoomsDetailPresenter(this)
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.extras?.let { bundle ->
            val roomSelected = bundle.getSerializable(ROOM_SELECTED_ARGS) as? Rooms
            roomSelected?.let { room ->
                presenter?.setRoomSelected(room)
            }
        }
    }

    companion object {
        private const val ROOM_SELECTED_ARGS = "ROOM_SELECTED_ARGS"

        fun getStartIntent(context: Context, roomSelected: Rooms): Intent {
            val intent = Intent(context, RoomsDetailActivity::class.java)
            intent.putExtra(ROOM_SELECTED_ARGS, roomSelected)
            return intent
        }
    }
}
