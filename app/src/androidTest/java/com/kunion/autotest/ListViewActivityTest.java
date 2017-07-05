package com.kunion.autotest;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

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
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by Administrator on 2017/7/5.
 */
@RunWith(AndroidJUnit4.class)
public class ListViewActivityTest {

    @Rule
    public ActivityTestRule<ListViewActivity> mActivityRule = new ActivityTestRule<>(
            ListViewActivity.class);

    @Test
    public void findItemAndClick(){

        onData(allOf(is(Matchers.instanceOf(Map.class)),
                hasEntry(equalTo("STR"), is("item 40")))).perform(click());

    }

    @Test
    public void findItemAndClickCustom() {
        onData(withItemContent("item 40")).perform(click());
    }


    @Test
    public void findItemAndClickChild() {
        onData(withItemContent("item 40")).onChildView(withId(R.id.simple_len)).perform(click());
    }

    public static Matcher<Object> withItemContent(final Matcher<String> itemTextMatcher) {
        return new BoundedMatcher<Object, Map>(Map.class) {
            @Override
            public boolean matchesSafely(Map map) {
                return hasEntry(equalTo("STR"), itemTextMatcher).matches(map);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with item content: ");
                itemTextMatcher.describeTo(description);
            }
        };
    }

    public static Matcher<Object> withItemContent(String expectedText) {
        return withItemContent(equalTo(expectedText));
    }
}
