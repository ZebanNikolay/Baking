package com.udacity.zeban.baking.presentation.recipes_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.udacity.zeban.baking.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class RecipesListActivityTest {

    @Rule
    public ActivityTestRule<RecipesListActivity> mActivityRule = new ActivityTestRule<>(
            RecipesListActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void checkRecyclerView() {

        onView(withId(R.id.recycler_view))
                .check(matches(isDisplayed()));
    }


    @Test
    public void clickRecipeListItem_openStepDetailFragment() throws Exception {

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.steps_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.container))
                .check(matches(isDisplayed()));
    }


}