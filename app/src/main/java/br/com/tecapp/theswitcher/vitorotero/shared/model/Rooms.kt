package br.com.tecapp.theswitcher.vitorotero.shared.model

import java.util.*

data class Rooms(
    val id: String,
    val name: String,
    val status: Boolean
) {

    fun generateBaseRooms(): List<Rooms> {
        return listOf(
            Rooms(UUID.randomUUID().toString(), "Kitchen", false),
            Rooms(UUID.randomUUID().toString(), "Living Room", false),
            Rooms(UUID.randomUUID().toString(), "Master Bedroom", false),
            Rooms(UUID.randomUUID().toString(), "Guest's bedroom", false)
        )
    }

}