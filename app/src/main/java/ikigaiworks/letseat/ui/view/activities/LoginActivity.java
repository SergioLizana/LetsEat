package ikigaiworks.letseat.ui.view.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.ui.view.fragments.login.FragmentLogin;
import ikigaiworks.letseat.ui.view.fragments.login.FragmentRegistro;
import ikigaiworks.letseat.utils.SwitchTrackTextDrawable;

public class LoginActivity extends AppCompatActivity {

    Switch switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        switchButton = (Switch) findViewById(R.id.mySwitch);
        switchButton.setTrackDrawable(new SwitchTrackTextDrawable(this,R.string.login,R.string.registro));
        if (switchButton != null) {
            switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == false){
                        replaceFragment(FragmentLogin.newInstance());
                    }else{
                        replaceFragment(FragmentRegistro.newInstance());
                    }
                }
            });
        }
        init();

    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.slide_left_animation,R.animator.slide_right_animation);
        transaction.replace(R.id.content_login, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void init(){

        getFragmentManager().beginTransaction()
                .add(R.id.content_login, FragmentLogin.newInstance()).commit();
    }


}
