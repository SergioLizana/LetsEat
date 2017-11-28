package ikigaiworks.letseat.app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.ui.view.activities.LoginActivity_;
import ikigaiworks.letseat.ui.view.activities.MenuActivity_;

/**
 * Created by sergiolizanamontero on 22/9/17.
 */

@EActivity
public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;
    @ViewById(R.id.drawer_layout)
    protected DrawerLayout drawer;
    @ViewById(R.id.nav_view)
    protected NavigationView navigationView;

    public Toolbar addToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return toolbar;
    }

    public DrawerLayout addNavigationDrawer(){
        addToolbar();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
                manageMenuOptions();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawer.addDrawerListener(toggle);
        manageMenuOptions();
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        return drawer;
    }



    public void manageMenuOptions(){
        if (FirebaseCommon.getFirebaseAuth().getCurrentUser() != null) {
            disableLogin();
            enableLogout();
        }else{
            enableLogin();
            disableLogout();
        }
    }


    public void enableLogin(){
        navigationView.getMenu().getItem(0).setCheckable(false).setVisible(true);
    }

    public void disableLogin(){
        navigationView.getMenu().getItem(0).setCheckable(false).setVisible(false);
    }

    public void enableLogout(){
        navigationView.getMenu().getItem(navigationView.getMenu().size()-1).setCheckable(false).setVisible(true);
    }

    public void disableLogout(){
        navigationView.getMenu().getItem(navigationView.getMenu().size()-1).setCheckable(false).setVisible(false);
    }

    public void replaceToArrow(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setToolbarTitle(String title){
        toolbar.setTitle(title);
    }

    public void setToolbarBackgroundColor(int color){
        toolbar.setBackgroundColor(ContextCompat.getColor(this, color));
    }

    public void setToolbarTextColor(int color) {
        toolbar.setTitleTextColor(ContextCompat.getColor(this, color));
    }


    public void replaceFragment(Fragment fragment,int resId,String tag,boolean addToBackStack , boolean addAnimation){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(addToBackStack){
            ft.addToBackStack(tag);
        }
        if (addAnimation){
            ft.setCustomAnimations(R.animator.slide_left_animation,R.animator.slide_right_animation);
        }
        ft.replace(resId, fragment);
        ft.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.login) {
            if(FirebaseCommon.getFirebaseAuth().getCurrentUser() != null){
                Toast.makeText(this,"Estás autenticado ya en Lets Eat !",Toast.LENGTH_LONG).show();
            }else{
                Intent intent = LoginActivity_.intent(this).get();
                startActivity(intent);
            }
        } else if (id == R.id.carta) {
            Intent intent = MenuActivity_.intent(this).get();
            startActivity(intent);

        } else if (id == R.id.pedido) {

        } else if (id == R.id.mis_pedidos) {

        } else if (id == R.id.promos) {

        } else if (id == R.id.aboutme) {

        } else if (id == R.id.config) {

        } else if (id == R.id.exit) {
            if(FirebaseCommon.getFirebaseAuth().getCurrentUser() != null) {
                FirebaseCommon.getFirebaseAuth().signOut();
            }else{
                Toast.makeText(this,"No estás autenticado en Lets Eat",Toast.LENGTH_LONG).show();
            }
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;

    }



    @Override
    public void onBackPressed() {
        if(drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
            manageMenuOptions();
        }else{
            super.onBackPressed();
        }
    }

    public void hideKeyboard(){
        InputMethodManager inputManager =
                (InputMethodManager) this.
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                this.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }



}
