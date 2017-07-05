package com.kunion.autotest;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.kunion.autotest.ListViewActivityTest.withItemContent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by Administrator on 2017/7/5.
 */
@RunWith(AndroidJUnit4.class)
public class ListViewActivity2Test {

    @Rule
    public ActivityTestRule<ListViewActivity2> mActivityRule = new ActivityTestRule<>(
            ListViewActivity2.class);

    @Test
    public void findItemAndClick(){

        onData(allOf(is(instanceOf(SearchItem.class)),
                teacherSearchItemWithName("item 40"))).perform(click());

        onData(allOf(is(instanceOf(SearchItem.class)), teacherSearchItemWithName("item 40")))
                .inAdapterView(withId(R.id.list)).perform(click());
    }


    public static Matcher<Object> teacherSearchItemWithName(final String name) {
        return new BoundedMatcher<Object, SearchItem>(SearchItem.class) {
            @Override
            protected boolean matchesSafely(SearchItem item) {
                return item != null
                        && !TextUtils.isEmpty(item.getKeyword())
                        && item.getKeyword().equals(name);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("SearchItem has Name: " + name);
            }
        };
    }
}
