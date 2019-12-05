package br.com.tecapp.theswitcher.vitorotero.shared.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "rooms_table")
data class Rooms(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "status") var status: Boolean
) : Serializable {

    companion object {
        fun generateBaseRooms(): List<Rooms> {
            return listOf(
                Rooms(UUID.randomUUID().toString(), "Kitchen", false),
                Rooms(UUID.randomUUID().toString(), "Living Room", false),
                Rooms(UUID.randomUUID().toString(), "Master Bedroom", false),
                Rooms(UUID.randomUUID().toString(), "Guest's bedroom", false)
            )
        }
    }

}