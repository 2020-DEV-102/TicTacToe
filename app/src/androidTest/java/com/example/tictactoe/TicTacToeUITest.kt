package com.example.tictactoe

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TicTacToeUITest {

    private var valuePlayer1: String = "X"

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun x_always_goes_first() {
        onView(withId(R.id.button_00)).perform(ViewActions.click())
        onView(withId(R.id.button_00))
            .check(ViewAssertions.matches(ViewMatchers.withText(valuePlayer1)))
    }
}