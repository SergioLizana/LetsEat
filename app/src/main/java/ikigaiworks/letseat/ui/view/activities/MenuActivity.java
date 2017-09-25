package ikigaiworks.letseat.ui.view.activities;
import android.os.Bundle;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.ui.view.fragments.main.FragmentMain_;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentMenu;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentMenu_;


@EActivity(R.layout.activity_menu)
public class MenuActivity extends BaseActivity {

    FragmentMenu menu;
    @AfterViews
    void init(){
        menu = FragmentMenu_.builder().build();
        replaceFragment(menu,R.id.content_menu,"menu",false,false);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_menu;
    }
}
