package br.com.tecapp.theswitcher.vitorotero.ui.rooms.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.tecapp.theswitcher.vitorotero.R
import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms

interface RoomAdapterListener {
    fun onItemClick(item: Rooms)
}

class RoomAdapter(private val listener: RoomAdapterListener) :
    RecyclerView.Adapter<RoomsViewHolder>() {

    private val rooms: List<Rooms> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rooms_list_item, parent, false)
        return RoomsViewHolder(view, listener)
    }

    override fun getItemCount(): Int = rooms.size

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.bind(rooms[position])
    }

}

class RoomsViewHolder(itemView: View, private val listener: RoomAdapterListener) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(item: Rooms) {

    }

}