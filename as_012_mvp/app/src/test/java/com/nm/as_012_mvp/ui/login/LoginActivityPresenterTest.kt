package com.nm.as_012_mvp.ui.login

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nm.as_012_mvp.R
import com.nm.as_012_mvp.dao.UserDao
import com.nm.as_012_mvp.model.User
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class LoginActivityPresenterTest {

    @Mock
    private lateinit var mView: LoginActivityContract.I_View

    @Mock
    private lateinit var userDao: UserDao

    private lateinit var presenter: LoginActivityPresenter

    @Before
    fun setUp() {
        initMocks(this)

        presenter = LoginActivityPresenter(userDao, mView)
    }

    @Test
    fun processLoginNoName() {
        presenter.processLogin("", "")

        verify(mView).execShowMessages(R.string.error_no_name)
    }

    @Test
    fun processLoginNoPassword() {
        presenter.processLogin("Hugo", "")

        verify(mView).execShowMessages(R.string.error_no_password)
    }

    @Test
    fun processLoginInvallidCredentions() {
        doReturn(
            null
        ).whenever(userDao).getUser(anyString(), anyString())

        presenter.processLogin("Hugo", "t123")

        verify(mView).execShowMessages(R.string.error_invalid_credentials)
    }

    @Test
    fun processLoginVallidCredentions() {
        doReturn(
            User(10L, "Hugo", "T123")
        ).whenever(userDao).getUser(anyString(), anyString())

        presenter.processLogin("Hugo", "T123")

        verify(mView).execNav()
    }

}