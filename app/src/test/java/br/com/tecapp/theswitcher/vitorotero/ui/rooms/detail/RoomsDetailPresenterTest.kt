package br.com.tecapp.theswitcher.vitorotero.ui.rooms.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.tecapp.theswitcher.vitorotero.shared.model.Rooms
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
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

class RoomsDetailPresenterTest {

    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()
    private val testScheduler = TestScheduler()

    @Mock
    lateinit var view: RoomsDetailContract.View
    private lateinit var presenter: RoomsDetailContract.Presenter
    private var roomSelected = Rooms(UUID.randomUUID().toString(), "Kitchen", true)

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        view = mock()
        presenter = RoomsDetailPresenter(view)
    }

    @After
    fun after() {
        presenter.detachView()
    }

    @Test
    fun test_room_selected() {
        presenter.setRoomSelected(roomSelected)
        testScheduler.triggerActions()

        verify(view).showRoomSelected(roomSelected)
        verifyNoMoreInteractions(view)
    }
}