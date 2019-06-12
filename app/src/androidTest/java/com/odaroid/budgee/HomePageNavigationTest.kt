package com.odaroid.budgee


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.odaroid.budgee.ui.home.AccountsFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@RunWith(AndroidJUnit4::class)
class HomePageNavigationTest {

    @Test
    fun testNavigationToAddAccountScreen() {
        val navController = mock(NavController::class.java)
        val homeScenario = launchFragmentInContainer<AccountsFragment>()
        homeScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.view!!, navController)
        }
        onView(ViewMatchers.withId(R.id.add_account_floating_action_button)).perform(ViewActions.click())
        verify(navController).navigate(R.id.action_accountsFragment_to_addAccountFragment)
    }

}