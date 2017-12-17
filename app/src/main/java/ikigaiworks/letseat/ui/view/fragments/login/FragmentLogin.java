package ikigaiworks.letseat.ui.view.fragments.login;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.ViewById;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.BaseActivity;
import ikigaiworks.letseat.app.LetsEatConstants;
import ikigaiworks.letseat.ui.presenters.login.LoginPresenterImpl;
import ikigaiworks.letseat.ui.view.activities.LoginActivity;
import ikigaiworks.letseat.ui.view.view.LoginView;
import ikigaiworks.letseat.utils.LoginUtils;

/**
 * A login screen that offers login via email/password.
 */
@EFragment(R.layout.fragment_login)
public class FragmentLogin extends Fragment implements LoginView {


    @ViewById(R.id.email)
    protected EditText mEmailView;
    @ViewById(R.id.password)
    protected EditText mPasswordView;
    @ViewById(R.id.login_container)
    protected ConstraintLayout loginContainer;


    private LoginPresenterImpl loginPresenter;


    @AfterViews
    void init() {
        loginPresenter = new LoginPresenterImpl(getActivity(), this);
    }


    @Click(R.id.email_sign_in_button)
    void clickLogin() {
        doLogin();
    }

    @EditorAction(R.id.password)
    void onEditorAction(TextView hello, int actionId, KeyEvent keyEvent) {
        if (actionId == LetsEatConstants.LOGIN_EDITOR_ACTION_ID) {
            ((LoginActivity) getActivity()).hideKeyboard();
            doLogin();
        }
    }

    private void doLogin() {
        if (LoginUtils.isEmailValid(mEmailView.getText().toString())) {
            ((LoginActivity) getActivity()).showLoader();
            loginPresenter.doLogin(mEmailView.getText().toString(), mPasswordView.getText().toString());
        } else {
            ((BaseActivity) getActivity()).showToast(getString(R.string.login_error_format_mail), Toast.LENGTH_LONG);
        }
    }

    @Override
    public void operationOK() {
        ((BaseActivity) getActivity()).showToast(getString(R.string.welcome), Toast.LENGTH_LONG);
        getActivity().finish();
    }

    @Override
    public void operationFailure(int code) {
        ((LoginActivity) getActivity()).hideLoader();
        ((BaseActivity) getActivity()).showToast(LoginUtils.parseCodeToStringError(code), Toast.LENGTH_LONG);
    }
}

