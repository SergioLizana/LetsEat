package ikigaiworks.letseat.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import ikigaiworks.letseat.R;

/**
 * Created by sergiolizanamontero on 22/9/17.
 */

// the base class
public abstract class BaseActivity extends AppCompatActivity
{
    Toolbar toolbar;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        configureToolbar();

    }

    protected abstract int getLayoutResource();

    public Toolbar getToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        return toolbar;
    }

    private void configureToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void replaceFragment(Fragment fragment,int resId,String tag,boolean addToBackStack , boolean addAnimation){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(addToBackStack){
            ft.addToBackStack(tag);
        }
        ft.replace(resId, fragment);
        ft.commit();

    }

}
