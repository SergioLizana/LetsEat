package ikigaiworks.letseat;

import android.support.annotation.NonNull;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ikigaiworks.letseat.ui.view.activities.FavOrderActivity;
import ikigaiworks.letseat.ui.view.activities.FavOrderActivity_;
import ikigaiworks.letseat.utils.FavoriteUtils;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.internal.util.Checks.checkNotNull;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by sergiolizanamontero on 24/1/18.
 */


@RunWith(AndroidJUnit4.class)
public class FavEspressoTest {
    @Rule
    public ActivityTestRule<FavOrderActivity> mActivityRule =
            new ActivityTestRule(FavOrderActivity_.class);

    @Test
    public void testFav(){
        if (FavoriteUtils.getFavList()!= null && FavoriteUtils.getFavList().size()>0){
            onView(withId(R.id.recycler_fav_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
            continueWithTest();
        }


    }

    public void continueWithTest(){
        onView(withId(R.id.pay)).perform(click());
        onView(withId(R.id.pay)).perform(click());

        onView(withId(R.id.ocultar)).perform(click());
        onView(withId(R.id.ticket_cart)).check(matches(not(isDisplayed())));
        onView(withId(R.id.ocultar)).perform(click());
        onView(withId(R.id.ticket_cart)).check(matches(isDisplayed()));

        onView(withId(R.id.fav_button)).perform(click());
        onView(withText(R.string.error_validation_is_fav)).inRoot(withDecorView(is(not(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        onView(withId(R.id.scrollCompletePayment)).perform(swipeUp());
        onView(withId(R.id.goToHome)).check(matches(isDisplayed())).perform(click());
    }


    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {

        return new RecyclerViewMatcher(recyclerViewId);
    }

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    // has no item on such position
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
}
