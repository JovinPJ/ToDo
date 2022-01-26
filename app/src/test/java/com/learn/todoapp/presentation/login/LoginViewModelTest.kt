package com.learn.todoapp.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.learn.todoapp.CoroutineTestRule
import com.learn.todoapp.domain.models.LoginResponse
import com.learn.todoapp.domain.usecases.LoginUsecase
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class LoginViewModelTest {


    companion object {
        private const val EMAIL = "abc@example.com"
        private const val PASSWORD = "123456"

        @BeforeClass
        @JvmStatic
        fun setup() {
            println("Before Class")
        }

        @AfterClass
        @JvmStatic
        fun teardown() {
            println("After Class")
        }
    }


    @Mock
    private lateinit var loginUsecase: LoginUsecase


    private val mockObserver = mock<Observer<Boolean>>()

    private inline fun <reified T> mock(): T = Mockito.mock(T::class.java)


    private lateinit var loginViewModel: LoginViewModel


    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coRoutineTestRule = CoroutineTestRule()


    @Before
    fun before() {
        loginViewModel = LoginViewModel(loginUsecase, TestCoroutineDispatcher())
        loginViewModel.getLoggedInLiveData().observeForever(mockObserver)
    }

    @Test
    fun `check whether login works with correct input data format`() {

        //given
        runBlockingTest {

            //given
            Mockito.`when`(loginUsecase.login(EMAIL, PASSWORD)).thenAnswer { LoginResponse(true) }

            //when
            loginViewModel.login(EMAIL, PASSWORD)

            //then
            verify(mockObserver).onChanged(true)
            verify(loginUsecase, times(1)).login(EMAIL, PASSWORD)
            Mockito.verifyNoMoreInteractions(mockObserver)
        }
    }

    @Test
    fun `check whether login fails with invalid email format`() {

        //given
        runBlockingTest {

            //given
            val inValidEmail = "abc@example"
            val password = "123456"

            //when
            loginViewModel.login(inValidEmail, password)

            //then
            Mockito.verifyNoInteractions(mockObserver)
            verify(loginUsecase, times(0)).login(inValidEmail, password)

        }
    }

    @Test
    fun `check whether login fails with invalid password format`() {

        //given
        runBlockingTest {

            //given
            val inValidEmail = "abc@example.com"
            val password = ""

            //when
            loginViewModel.login(inValidEmail, password)

            //then
            Mockito.verifyNoInteractions(mockObserver)
            verify(loginUsecase, times(0)).login(inValidEmail, password)

        }
    }

    @After
    @Throws(Exception::class)
    fun tearDownClass() {
        Mockito.framework().clearInlineMocks()
        loginViewModel.getLoggedInLiveData().removeObserver(mockObserver)
    }

}