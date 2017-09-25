package ikigaiworks.letseat.ui.view.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import butterknife.Unbinder;
import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.ui.view.fragments.main.FragmentMain;
import ikigaiworks.letseat.ui.view.fragments.main.FragmentMain_;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Unbinder unbinder;
    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;
    @ViewById(R.id.nav_view)
    NavigationView navigationView;
    Toolbar toolbar;

    FragmentMain fragment;

    @AfterViews
    void init(){
        initNavDrawer();
        toolbar.setTitle("Taste Bakery");
        if (findViewById(R.id.content_main) != null) {
            fragment = FragmentMain_.builder().build();
            replaceFragment(fragment,R.id.content_main,"main",false,false);
        }
    }

    private void initNavDrawer(){
        toolbar = getToolbar();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.login) {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.carta) {
            Intent intent = MenuActivity_.intent(this).get();
            startActivity(intent);

        } else if (id == R.id.pedido) {

        } else if (id == R.id.mis_pedidos) {

        } else if (id == R.id.promos) {

        } else if (id == R.id.aboutme) {

        } else if (id == R.id.config) {

        } else if (id == R.id.exit) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }
}
