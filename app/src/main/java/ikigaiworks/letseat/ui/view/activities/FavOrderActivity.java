package ikigaiworks.letseat.ui.view.activities;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.ui.view.fragments.lastorder.FragmentFavOrderList;
import ikigaiworks.letseat.ui.view.fragments.lastorder.FragmentFavOrderList_;

/**
 * Created by sergiolizanamontero on 30/11/17.
 */

@EActivity(R.layout.activity_menu)
public class FavOrderActivity extends BaseActivity {

    FragmentFavOrderList lastOrderFragment;

    @AfterViews
    void init(){
        addNavigationDrawer();
        setToolbarTitle(getString(R.string.title_last_orders));
        setToolbarBackgroundColor(R.color.colorPrimaryDark);
        lastOrderFragment = FragmentFavOrderList_.builder().build();
        replaceFragment(lastOrderFragment,R.id.content_menu,getString(R.string.tag_name_menu),false,true);
    }

}
