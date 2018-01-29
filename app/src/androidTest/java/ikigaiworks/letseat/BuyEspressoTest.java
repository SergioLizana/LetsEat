package ikigaiworks.letseat;

import android.support.annotation.NonNull;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ikigaiworks.letseat.ui.view.activities.MainActivity_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.internal.util.Checks.checkNotNull;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Created by sergiolizanamontero on 24/1/18.
 */

@RunWith(AndroidJUnit4.class)
public class BuyEspressoTest {

    private static final String SPINNER_TEXT = "Jamon y Queso";
    private static final String QUANTITY = "3";
    private static final String DUMMY_TEXT = "hello udacity";

    @Rule
    public ActivityTestRule<MainActivity_> mActivityRule =
            new ActivityTestRule(MainActivity_.class);

    @Test
    public void testFullBuy(){
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open());
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.carta));

        try{
            Thread.sleep(6000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        onView( allOf(withId(R.id.recyler_menu_categorias), isDisplayed())).perform(actionOnItemAtPosition(2, click()));

        try{
            Thread.sleep(6000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        onView( allOf(withId(R.id.recycler_list_products), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition(6)).perform(click());

        try{
            Thread.sleep(1500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.spinner_extras)).check(matches(isDisplayed()));
        onView(withId(R.id.spinner_extras)).check(matches(withSpinnerText(containsString(SPINNER_TEXT))));
        onView(withId(R.id.addToCart)).perform(click());


        onView( allOf(withId(R.id.recycler_list_products), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition(1)).perform(click());

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();

        }
        onView( allOf(withId(R.id.addToCart), isDisplayed()));
        onView(withId(R.id.addToCart)).perform(click());

        onView(withId(R.id.cart_button)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.cart_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.delete_layout)));

        onView(withId(R.id.cart_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.AddProductAmount)));
        onView(withId(R.id.cart_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.AddProductAmount)));
        onView(withRecyclerView(R.id.cart_list)
                .atPositionOnView(0, R.id.quantity))
                .check(matches(withText(QUANTITY)));
        onView(withId(R.id.amount_products)).check(matches(withText(QUANTITY)));
        onView(withId(R.id.pay)).perform(click());
        onView(withId(R.id.pay)).perform(click());


        onView(withRecyclerView(R.id.ticket_cart)
                .atPositionOnView(0, R.id.item_quantity))
                .check(matches(withText(QUANTITY)));

        onView(withId(R.id.ocultar)).perform(click());
        onView(withId(R.id.ticket_cart)).check(matches(not(isDisplayed())));
        onView(withId(R.id.ocultar)).perform(click());
        onView(withId(R.id.ticket_cart)).check(matches(isDisplayed()));

        onView(withId(R.id.fav_button)).perform(click());
        onView(withId(R.id.fav_name)).perform(typeText(DUMMY_TEXT), closeSoftKeyboard());
        onView(withId(R.id.fav_name)).check(matches(withText(DUMMY_TEXT)));
        onView(withId(android.R.id.button2)).perform(click());
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
