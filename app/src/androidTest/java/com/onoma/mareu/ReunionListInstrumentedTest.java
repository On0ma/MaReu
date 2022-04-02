package com.onoma.mareu;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import android.widget.TimePicker;

import com.onoma.mareu.ui.ListReunionActivity;
import com.onoma.mareu.utils.DeleteViewAction;
import com.onoma.mareu.utils.RecyclerViewItemCountAssertion;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class ReunionListInstrumentedTest {

    public static final int ITEM_COUNT = 10;
    public static final int ITEM_COUNT_PEACH = 3;
    public static final int DELETE_POS = 0;
    public static final String SEARCH_STRING = "Peach";
    public static final String NEW_REUNION_ROOM = "Luigi";
    public static final String NEW_REUNION_SUBJECT = "Reunion Z";
    public static final int NEW_REUNION_TIME_HOURS = 12;
    public static final int NEW_REUNION_TIME_MINUTES = 30;
    public static final String NEW_REUNION_ATTENDEES = "viviane@lamzone.com, alex@lamzone.com, marc@lamzone.com";

    @Rule
    public ActivityScenarioRule<ListReunionActivity> rule = new ActivityScenarioRule<>(ListReunionActivity.class);

    @Test
    public void A_reunionListNotEmpty() {
        onView(withId(R.id.reunion_fragment_list)).check(new RecyclerViewItemCountAssertion(ITEM_COUNT));
    }

    @Test
    public void B_reunionDeleteButtonShouldRemoveItem() {
        onView(withId(R.id.reunion_fragment_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(DELETE_POS, new DeleteViewAction()));
        onView(withId(R.id.reunion_fragment_list))
                .check(new RecyclerViewItemCountAssertion(ITEM_COUNT - 1));
    }

    @Test
    public void C_reunionFilterBySearch() {
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText(SEARCH_STRING));
        onView(withId(R.id.reunion_fragment_list)).check(new RecyclerViewItemCountAssertion(ITEM_COUNT_PEACH));
    }

    @Test
    public void D_addingNewReunion() {
        onView(withId(R.id.add_reunion)).perform(click());
        onView(withId(R.id.add_reunion_room)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(NEW_REUNION_ROOM))).perform(click());
        onView(withId(R.id.add_reunion_subject)).perform(typeText(NEW_REUNION_SUBJECT));
        onView(withId(R.id.add_reunion_time)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(
                PickerActions.setTime(NEW_REUNION_TIME_HOURS, NEW_REUNION_TIME_MINUTES)
        );
        onView(withText("OK")).perform(click());
        onView(withId(R.id.add_reunion_attendees)).perform(typeText(NEW_REUNION_ATTENDEES));
        onView(withId(R.id.add_reunion_button)).perform(click());
        onView(withId(R.id.reunion_fragment_list)).check(new RecyclerViewItemCountAssertion(ITEM_COUNT));
    }
}