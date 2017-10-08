package ikigaiworks.letseat.ui.view.activities;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentCategory;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentCategory_;


@EActivity(R.layout.activity_menu)
public class MenuActivity extends BaseActivity{

    FragmentCategory menu;

    @AfterViews
    void init(){
        addNavigationDrawer();
        setToolbarTitle("Menu");
        setToolbarBackgroundColor(R.color.colorPrimaryDark);
        menu = FragmentCategory_.builder().build();
        replaceFragment(menu,R.id.content_menu,"menu",false,false);
    }

}
