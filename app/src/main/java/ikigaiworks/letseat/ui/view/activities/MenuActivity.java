package ikigaiworks.letseat.ui.view.activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.ui.view.fragments.main.FragmentMain_;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentMenu;
import ikigaiworks.letseat.ui.view.fragments.menu.FragmentMenu_;


@EActivity(R.layout.activity_menu)
public class MenuActivity extends BaseActivity{

    FragmentMenu menu;

    @AfterViews
    void init(){
        addNavigationDrawer();
        setToolbarTitle("Menu");
        setToolbarBackgroundColor(R.color.colorPrimaryDark);
        menu = FragmentMenu_.builder().build();
        replaceFragment(menu,R.id.content_menu,"menu",false,false);
    }

}
