package br.com.tecapp.theswitcher.vitorotero.shared.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.Transaction
import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms

@Database(entities = [Rooms::class], exportSchema = false, version = 1)
abstract class SwitcherDatabase : RoomDatabase() {

    abstract fun roomsDao(): RoomsDao

    companion object {
        private var INSTANCE: SwitcherDatabase? = null

        fun getInstance(context: Context): SwitcherDatabase {
            if (INSTANCE == null) {
                INSTANCE = databaseBuilder(
                    context.applicationContext,
                    SwitcherDatabase::class.java,
                    "switcher-db"
                ).allowMainThreadQueries()
                    .build()

                INSTANCE?.generateDefaultRoomsValues()
            }

            return INSTANCE as SwitcherDatabase
        }
    }

    @Transaction
    private fun generateDefaultRoomsValues() {
        INSTANCE?.runInTransaction {
            if (roomsDao().getAllRooms().isEmpty()) {
                val rooms = Rooms.generateBaseRooms()
                rooms.forEach {
                    roomsDao().insert(it)
                }
            }
        }
    }

}