package ikigaiworks.letseat.ui.view.activities;

import android.app.FragmentManager;
import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.ui.view.fragments.cart.FragmentCartList;
import ikigaiworks.letseat.ui.view.fragments.cart.FragmentCartList_;
import ikigaiworks.letseat.ui.view.fragments.main.FragmentMain;
import ikigaiworks.letseat.ui.view.fragments.main.FragmentMain_;

/**
 * Created by sergiolizanamontero on 12/11/17.
 */
@EActivity(R.layout.activity_cart)
public class CartActivity  extends BaseActivity {

    FragmentCartList fragment;

    @AfterViews
    void init(){
        addToolbar();
        setToolbarTitle(getString(R.string.title_cart));
        setToolbarBackgroundColor(R.color.colorPrimary);
        if (findViewById(R.id.content_activity_cart) != null) {
            fragment = FragmentCartList_.builder().build();
            replaceFragment(fragment,R.id.content_activity_cart,getString(R.string.tag_name_main),false,true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
