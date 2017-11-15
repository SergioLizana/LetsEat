package ikigaiworks.letseat.ui.view.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.ui.view.fragments.main.FragmentMain;
import ikigaiworks.letseat.ui.view.fragments.main.FragmentMain_;
import ikigaiworks.letseat.utils.App;
import ikigaiworks.letseat.utils.CommonUtils;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    FragmentMain fragment;

    @AfterViews
    void init(){
        addNavigationDrawer();
        setToolbarTitle("Taste Bakery");
        if (findViewById(R.id.content_main) != null) {
            fragment = FragmentMain_.builder().build();
            replaceFragment(fragment,R.id.content_main,"main",false,false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (App.appInit) {
            CommonUtils.deleteCart();
        }
    }
}
