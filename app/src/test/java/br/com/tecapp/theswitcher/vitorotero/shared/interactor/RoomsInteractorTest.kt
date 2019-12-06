package br.com.tecapp.theswitcher.vitorotero.shared.interactor

import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import br.com.tecapp.theswitcher.vitorotero.shared.repository.RoomsDao
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class RoomsInteractorTest {

    private lateinit var roomsInteractor: RoomsInteractor
    private lateinit var roomsDao: RoomsDao

    @Before
    fun setup() {
        roomsDao = mock()
        roomsInteractor = RoomsInteractorImp(roomsDao)
    }

    @Test
    fun test_getAllRooms_returnsListRooms() {
        val roomsResponse = Rooms.generateBaseRooms()

        whenever(roomsDao.getAllRooms())
            .thenReturn(roomsResponse)

        val requestResult = roomsInteractor.getAllRooms()

        val testObserver = TestObserver<List<Rooms>>()
        requestResult.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        val listResult = testObserver.values()[0]

        assertEquals(listResult.size, 4)
        assertEquals(listResult[0].name, "Kitchen")
        assertEquals(listResult[0].status, false)

        assertEquals(listResult[1].name, "Living Room")
        assertEquals(listResult[1].status, false)

        assertEquals(listResult[2].name, "Master Bedroom")
        assertEquals(listResult[2].status, false)

        assertEquals(listResult[3].name, "Guest's bedroom")
        assertEquals(listResult[3].status, false)
    }

    @Test
    fun test_updateRoom_returnsCompletable() {
        val updateResponse = Completable.complete()
        val roomSelected = Rooms(UUID.randomUUID().toString(), "Kitchen", true)

        whenever(roomsDao.update(roomSelected))
            .thenReturn(updateResponse)

        val requestResult = roomsInteractor.updateRoom(roomSelected)

        val testObserver = TestObserver<Completable>()
        requestResult.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

}