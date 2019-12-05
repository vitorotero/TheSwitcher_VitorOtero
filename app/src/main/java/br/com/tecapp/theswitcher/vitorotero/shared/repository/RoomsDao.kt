package br.com.tecapp.theswitcher.vitorotero.shared.repository

import androidx.room.*
import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import io.reactivex.Completable

@Dao
interface RoomsDao {

    @Insert
    fun insert(rooms: Rooms)

    @Update
    fun update(rooms: Rooms): Completable

    @Delete
    fun delete(rooms: Rooms): Completable

    @Query("DELETE FROM rooms_table")
    fun deleteAllRooms(): Completable

    @Query("SELECT * FROM rooms_table")
    fun getAllRooms(): List<Rooms>

}