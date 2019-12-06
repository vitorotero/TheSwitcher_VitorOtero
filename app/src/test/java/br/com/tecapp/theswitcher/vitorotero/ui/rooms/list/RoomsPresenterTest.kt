package br.com.tecapp.theswitcher.vitorotero.ui.rooms.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.tecapp.theswitcher.vitorotero.shared.interactor.RoomsInteractor
import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import java.util.*

class RoomsPresenterTest {

    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()
    private val testScheduler = TestScheduler()

    @Mock
    lateinit var roomsInteractor: RoomsInteractor

    @Mock
    lateinit var view: RoomsContract.View

    private lateinit var presenter: RoomsContract.Presenter

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        roomsInteractor = mock()
        view = mock()
        presenter = RoomsPresenter(roomsInteractor, view)
    }

    @After
    fun after() {
        presenter.detachView()
    }

    @Test
    fun test_noData_returnsEmptyList() {
        val empty: List<Rooms> = emptyList()

        whenever(roomsInteractor.getAllRooms())
            .thenReturn(Single.just(empty))

        presenter.getAllRooms()
        testScheduler.triggerActions()

        verify(view).setRooms(empty)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun test_DataOnDb_returnsValues() {
        val rooms = Rooms.generateBaseRooms()
        val result = Single.just(rooms)

        whenever(roomsInteractor.getAllRooms())
            .thenReturn(result)

        presenter.getAllRooms()
        testScheduler.triggerActions()

        verify(view).setRooms(rooms)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun test_DataOnDb_returnsError() {
        val result = Throwable("Error")

        whenever(roomsInteractor.getAllRooms())
            .thenReturn(Single.error(result))

        presenter.getAllRooms()
        testScheduler.triggerActions()

        verify(view).showEmptyView()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun test_UpdateRoom_returnsSuccess() {
        val roomSelected = Rooms(UUID.randomUUID().toString(), "Kitchen", true)
        val result = Completable.complete()

        whenever(roomsInteractor.updateRoom(roomSelected))
            .thenReturn(result)

        presenter.updateRoom(roomSelected)
        testScheduler.triggerActions()

        verify(view).showMessageStatus(roomSelected.status)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun test_UpdateRoom_returnsError() {
        val roomSelected = Rooms(UUID.randomUUID().toString(), "Kitchen", true)
        val error = Throwable("Error")

        whenever(roomsInteractor.updateRoom(roomSelected))
            .thenReturn(Completable.error(error))

        presenter.updateRoom(roomSelected)
        testScheduler.triggerActions()

        verifyNoMoreInteractions(view)
    }

}