package com.nm.as_013_mvvm.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nm.as_013_mvvm.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations.initMocks

class LoginActivityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginActivityViewModel

    @Before
    fun setUp() {
        initMocks(this)

        viewModel = LoginActivityViewModel()
    }

    @Test
    fun processLoginNoName() {
        viewModel.processLogin("", "")

        val resposta = viewModel.resposta.value

        assertEquals(R.string.error_no_name, resposta)
    }

    @Test
    fun processLoginNoPassword() {
        viewModel.processLogin("Hugo", "")

        val resposta = viewModel.resposta.value

        assertEquals(R.string.error_no_password, resposta)
    }

    @Test
    fun processLoginInvalidCredentials() {
        viewModel.processLogin("Hugo", "t")

        val resposta = viewModel.resposta.value

        assertEquals(R.string.error_invalid_credentials, resposta)
    }

    @Test
    fun processLoginValidCredentials() {
        viewModel.processLogin("Hugo", "T123")

        val resposta = viewModel.navegacao.value

        assertTrue(resposta!!)
    }


}