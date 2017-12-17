package ikigaiworks.letseat.ui.view.activities;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
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
    @ViewById(R.id.progressbar)
    protected ProgressBar loader;
    @ViewById(R.id.login_constraint_container)
    protected View container;
    @ViewById(R.id.login_image_background)
    protected View background;


    @AfterViews
    void init() {
        initComponents();
    }

    @CheckedChange(R.id.mySwitch)
    void checkedChange(CompoundButton button, boolean isChecked) {
        swapFragment(isChecked);
    }

    private void swapFragment(boolean isChecked) {
        if (!isChecked) {
            replaceFragment(FragmentLogin_.builder().build(), R.id.content_login, getString(R.string.tag_name_login)
                    , false, true);
        } else {
            replaceFragment(FragmentRegistro_.builder().build(), R.id.content_login, getString(R.string.tag_name_signup)
                    , false, true);
        }
    }

    public void swapToLogin() {
        if (switchButton.isChecked()) {
            switchButton.setChecked(false);
            swapFragment(false);
        }
    }

    public void swapToSignUp() {
        if (!switchButton.isChecked()) {
            switchButton.setChecked(true);
            swapFragment(true);
        }
    }

    private void initComponents() {
        switchButton.setTrackDrawable(new SwitchTrackTextDrawable(this, R.string.login, R.string.registro));
        replaceFragment(FragmentLogin_.builder().build(), R.id.content_login, getString(R.string.tag_name_login)
                , false, false);
    }

    public void showLoader() {
        background.setVisibility(View.INVISIBLE);
        container.setVisibility(View.INVISIBLE);
        loader.setVisibility(View.VISIBLE);
    }

    public void hideLoader() {
        background.setVisibility(View.VISIBLE);
        container.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
    }

}
