package ikigaiworks.letseat.ui.view.activities;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.ui.view.fragments.lastorder.FragmentLastOrder;
import ikigaiworks.letseat.ui.view.fragments.lastorder.FragmentLastOrder_;

/**
 * Created by sergiolizanamontero on 30/11/17.
 */

@EActivity(R.layout.activity_menu)
public class LastOrderActivity extends BaseActivity {

    FragmentLastOrder lastOrderFragment;

    @AfterViews
    void init(){
        addNavigationDrawer();
        setToolbarTitle("Últimos Pedidos");
        setToolbarBackgroundColor(R.color.colorPrimaryDark);
        lastOrderFragment = FragmentLastOrder_.builder().build();
        replaceFragment(lastOrderFragment,R.id.content_menu,"menu",false,true);
    }

}
