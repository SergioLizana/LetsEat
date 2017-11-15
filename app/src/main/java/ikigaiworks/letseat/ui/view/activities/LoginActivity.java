package ikigaiworks.letseat.ui.view.activities;

import android.widget.CompoundButton;
import android.widget.Switch;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.ui.view.fragments.login.FragmentLogin_;
import ikigaiworks.letseat.ui.view.fragments.login.FragmentRegistro_;
import ikigaiworks.letseat.utils.SwitchTrackTextDrawable;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewById(R.id.mySwitch)
    Switch switchButton;


    @AfterViews
    void init(){
        initComponents();
    }

    @CheckedChange(R.id.mySwitch)
    void checkedChange(CompoundButton button, boolean isChecked) {
        if (!isChecked){
            replaceFragment(FragmentLogin_.builder().build(),R.id.content_login,"LOGINFRAGMENT",false,true);
        }else{
            replaceFragment(FragmentRegistro_.builder().build(),R.id.content_login,"REGISTROFRAGMENT",false,true);
        }
    }

    private void initComponents(){
        switchButton.setTrackDrawable(new SwitchTrackTextDrawable(this,R.string.login,R.string.registro));
        replaceFragment(FragmentLogin_.builder().build(),R.id.content_login,"LOGINFRAGMENT",false,false);
    }

}
