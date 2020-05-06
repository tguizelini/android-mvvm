package br.com.ht7.androidmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.ht7.androidmvvm.models.Country
import br.com.ht7.androidmvvm.models.CountryService
import br.com.ht7.androidmvvm.viewmodels.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var countryService: CountryService

    @InjectMocks
    var viewModel = ListViewModel()

    private var testList: Single<List<Country>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun setUpRxSchedulers() {
        val schedulerFake = object: Scheduler() {
            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { schedulerFake }
        RxJavaPlugins.setInitComputationSchedulerHandler { schedulerFake }
        RxJavaPlugins.setInitNewThreadSchedulerHandler{ schedulerFake }
        RxJavaPlugins.setInitSingleSchedulerHandler { schedulerFake }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedulerFake }
    }

    @Test
    fun getCountries() {
        val countryFake = Country("Brasil", "", "Brasília")
        val countryListFake: List<Country> = arrayListOf(countryFake)

        testList = Single.just(countryListFake)

        `when`(countryService.getCountries()).thenReturn(testList)

        viewModel.refresh()

        Assert.assertEquals(1, viewModel.countries.value?.size)
        Assert.assertEquals(false, viewModel.loadingError.value)
        Assert.assertEquals(false, viewModel.loading.value)
    }
}