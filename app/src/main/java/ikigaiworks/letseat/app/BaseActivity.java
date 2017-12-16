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
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.ui.view.activities.FavOrderActivity_;
import ikigaiworks.letseat.ui.view.activities.LoginActivity_;
import ikigaiworks.letseat.ui.view.activities.MainActivity;
import ikigaiworks.letseat.ui.view.activities.MainActivity_;
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
    ActionBarDrawerToggle toggle;

    public Toolbar addToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = MainActivity_.intent(getApplicationContext()).get();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        return toolbar;
    }

    public void changeToArrow(){
        if (toggle!=null) {
            toggle.setDrawerIndicatorEnabled(false);
            toggle.setToolbarNavigationClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }


    public DrawerLayout addNavigationDrawer(){
        addToolbar();
        toggle = new ActionBarDrawerToggle(
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

    public void manageIntents(int id){

        if (id == R.id.login) {
            if (FirebaseCommon.getFirebaseAuth().getCurrentUser() != null) {
                Toast.makeText(this, getString(R.string.authenticated), Toast.LENGTH_LONG).show();
            } else {
                Intent intent = LoginActivity_.intent(this).get();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        } else if (id == R.id.init){
            if(this instanceof MainActivity_){
                //DO NOTHING
            }else{
                Intent intent = MainActivity_.intent(this).get();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

        } else if (id == R.id.carta) {
            Intent intent = MenuActivity_.intent(this).get();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.pedido) {
            Intent intent = MenuActivity_.intent(this).get();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.mis_pedidos) {
            if(FirebaseCommon.getFirebaseAuth().getCurrentUser() != null){
                Intent intent = FavOrderActivity_.intent(this).get();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }else{
                Toast.makeText(this,getString(R.string.my_product_error),Toast.LENGTH_LONG).show();
            }

        } else if (id == R.id.promos) {
            Toast.makeText(this,getString(R.string.news),Toast.LENGTH_LONG).show();

        } else if (id == R.id.aboutme) {
            Toast.makeText(this,getString(R.string.news),Toast.LENGTH_LONG).show();

        } else if (id == R.id.config) {
            Toast.makeText(this,getString(R.string.news),Toast.LENGTH_LONG).show();

        } else if (id == R.id.exit) {
            if(FirebaseCommon.getFirebaseAuth().getCurrentUser() != null) {
                FirebaseCommon.getFirebaseAuth().signOut();
                Toast.makeText(this,getString(R.string.goodbye),Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,getString(R.string.news),Toast.LENGTH_LONG).show();
            }
        }
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
        manageIntents(id);
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

    public void hideKeyboard() {
        InputMethodManager inputManager =
                (InputMethodManager) this.
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                this.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void showToast(String msg,int duration){
        Toast.makeText(getApplicationContext(),msg,duration).show();
    }


    public void goToMainScreen(){
        Intent intent = MainActivity_.intent(this).get();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
