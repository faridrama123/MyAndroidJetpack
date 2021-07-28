package com.faridrama123.app.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.faridrama123.app.R
import com.faridrama123.app.utils.DataDummy
import com.faridrama123.app.utils.EspressoIdlingResource
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
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
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
    fun loadMovieFavorites() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText("Movie Fav")).perform(click())
        onView(withId(R.id.rv_favmovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favmovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
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
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }


    @Test
    fun loadTVShow() {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTVShow.size))
    }

    @Test
    fun loadDetailTVShow() {
        onView(withText("Tv Show")).perform(click())
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

    @Test
    fun loadTVShowFavorites() {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText("Tv Show Fav")).perform(click())
        onView(withId(R.id.rv_favtvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favtvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
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
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }
}