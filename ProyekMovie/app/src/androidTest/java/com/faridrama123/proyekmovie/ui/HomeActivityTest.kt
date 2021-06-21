package com.faridrama123.proyekmovie.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.faridrama123.proyekmovie.R
import com.faridrama123.proyekmovie.ui.home.HomeActivity
import com.faridrama123.proyekmovie.utils.DataDummy
import com.faridrama123.proyekmovie.utils.EspressoIdlingResource

import org.junit.After
import org.junit.Before
import org.junit.Test


class HomeActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovie()
    private val dummyTVShow = DataDummy.generateDummyTVShow()


    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText(dummyMovie[0].releaseDate)))
        onView(withId(R.id.text_bahasa)).check(matches(isDisplayed()))
        onView(withId(R.id.text_bahasa)).check(matches(withText("("+dummyMovie[0].originalLanguage+")")))
        onView(withId(R.id.text_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre)).check(matches(withText(dummyMovie[0].genreIds.toString())))
        onView(withId(R.id.text_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.text_rate)).check(matches(withText(dummyMovie[0].voteAverage.toString())))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(withText(dummyMovie[0].overview.toString())))

    }

    @Test
    fun loadTVShow() {
        onView(withText("TVSHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTVShow.size))
    }

    @Test
    fun loadDetailTVShow() {
        onView(withText("TVSHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyTVShow[0].originalName)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText(dummyTVShow[0].firstAirDate)))
        onView(withId(R.id.text_bahasa)).check(matches(isDisplayed()))
        onView(withId(R.id.text_bahasa)).check(matches(withText("("+dummyTVShow[0].originalLanguage+")")))
        onView(withId(R.id.text_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre)).check(matches(withText(dummyTVShow[0].genreIds.toString())))
        onView(withId(R.id.text_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.text_rate)).check(matches(withText(dummyTVShow[0].voteAverage.toString())))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(withText(dummyTVShow[0].overview.toString())))

    }


}