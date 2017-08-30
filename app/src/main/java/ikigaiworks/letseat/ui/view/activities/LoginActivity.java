package ikigaiworks.letseat.ui.view.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Switch;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.ui.view.fragments.FragmentLogin;
import ikigaiworks.letseat.utils.SwitchTrackTextDrawable;

public class LoginActivity extends AppCompatActivity {

    Switch switchButton;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        switchButton = (Switch) findViewById(R.id.mySwitch);
        switchButton.setTrackDrawable(new SwitchTrackTextDrawable(this,R.string.login,R.string.registro));
        init();

    }

    public void init(){
        fragment = FragmentLogin.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_login, fragment).commit();
    }


}
