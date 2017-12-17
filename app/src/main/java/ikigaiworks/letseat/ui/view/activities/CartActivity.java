package ikigaiworks.letseat.ui.view.activities;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.ui.view.fragments.cart.FragmentCartList;
import ikigaiworks.letseat.ui.view.fragments.cart.FragmentCartList_;

/**
 * Created by sergiolizanamontero on 12/11/17.
 */
@EActivity(R.layout.activity_cart)
public class CartActivity extends BaseActivity {

    FragmentCartList fragment;
    @Extra
    boolean isFav;

    @AfterViews
    void init() {
        addToolbar();
        setToolbarTitle(getString(R.string.title_cart));
        setToolbarBackgroundColor(R.color.colorPrimary);
        if (findViewById(R.id.content_activity_cart) != null) {
            fragment = FragmentCartList_.builder().isFav(isFav).build();
            replaceFragment(fragment, R.id.content_activity_cart, getString(R.string.tag_name_main), false, true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
