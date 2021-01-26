package com.faridrama123.moviecatalogue

import com.faridrama123.moviecatalogue.utils.DataDummy.generateDummyMovie
import com.faridrama123.moviecatalogue.utils.DataDummy.generateDummyTv

import org.junit.Before
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import com.faridrama123.moviecatalogue.R
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.action.ViewActions
import com.faridrama123.moviecatalogue.ui.home.HomeActivity
import org.junit.Test

class HomeActivityTest {
    private val dummyMovie = generateDummyMovie()
    private val dummytv = generateDummyTv()
    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun loadmovie() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_academy)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_academy)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailmovie() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.text_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_title)).check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].title)))
        Espresso.onView(ViewMatchers.withId(R.id.text_date)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_date)).check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].rilis)))
        Espresso.onView(ViewMatchers.withId(R.id.text_genre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_genre)).check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].genre)))
        Espresso.onView(ViewMatchers.withId(R.id.text_rate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_rate)).check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].rating)))
        Espresso.onView(ViewMatchers.withId(R.id.text_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_description)).check(ViewAssertions.matches(ViewMatchers.withText(dummyMovie[0].desc)))
    }

    @Test
    fun loadtvshow() {
        Espresso.onView(ViewMatchers.withText("TVSHOW")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummytv.size))
    }

    @Test
    fun loadDetailtvshow() {
        Espresso.onView(ViewMatchers.withText("TVSHOW")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.text_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_title)).check(ViewAssertions.matches(ViewMatchers.withText(dummytv[0].title)))
        Espresso.onView(ViewMatchers.withId(R.id.text_date)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_date)).check(ViewAssertions.matches(ViewMatchers.withText(dummytv[0].rilis)))
        Espresso.onView(ViewMatchers.withId(R.id.text_genre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_genre)).check(ViewAssertions.matches(ViewMatchers.withText(dummytv[0].genre)))
        Espresso.onView(ViewMatchers.withId(R.id.text_rate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_rate)).check(ViewAssertions.matches(ViewMatchers.withText(dummytv[0].rating)))
        Espresso.onView(ViewMatchers.withId(R.id.text_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_description)).check(ViewAssertions.matches(ViewMatchers.withText(dummytv[0].desc)))
    }
}