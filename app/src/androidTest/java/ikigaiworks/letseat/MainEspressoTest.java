package ikigaiworks.letseat;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ikigaiworks.letseat.ui.view.activities.MainActivity_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)

public class MainEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity_> mActivityRule =
            new ActivityTestRule(MainActivity_.class);

    @Test
    public void testaMain(){
          mainTest();
    }

    public void mainTest(){
        onView(withId(R.id.titleCarrusel)).check(matches(withText("Menu")));
        onView(withId(R.id.picker)).perform(swipeLeft());
        onView(withId(R.id.picker)).perform(swipeLeft());
        onView(withId(R.id.titleCarrusel)).check(matches(withText("Pedidos Favoritos")));
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open());
        onView(withId(R.id.drawer_layout))
                .check(matches(isOpen(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.close());
    }

}
