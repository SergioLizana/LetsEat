package ikigaiworks.letseat.ui.view.fragments.login;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.Button;
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
import ikigaiworks.letseat.ui.presenters.login.SignInPresenterImpl;
import ikigaiworks.letseat.ui.view.activities.LoginActivity;
import ikigaiworks.letseat.ui.view.view.LoginView;
import ikigaiworks.letseat.utils.LoginUtils;

/**
 * A login screen that offers login via email/password.
 */

@EFragment(R.layout.fragment_registro)
public class FragmentRegistro extends Fragment implements LoginView{

    @ViewById(R.id.email_registro)
    protected EditText mEmailView;
    @ViewById(R.id.password_registro)
    protected EditText mPasswordView;
    @ViewById(R.id.repeat_password)
    protected EditText mPasswordViewRepeat;
    @ViewById(R.id.email_registr_button)
    protected Button email_register_button;

    private SignInPresenterImpl mPresenter;
    @AfterViews
    void init(){
        mPresenter = new SignInPresenterImpl(getActivity(),this);
    }

    @Click(R.id.email_registr_button)
    public void signIn(){
        if (isSignUpValid()) {
            ((LoginActivity) getActivity()).showLoader();
            mPresenter.signIn(mEmailView.getText().toString(), mPasswordView.getText().toString());
        }
    }

    @Override
    public void operationOK() {
        ((BaseActivity)getActivity()).showToast(getString(R.string.auth_complete),Toast.LENGTH_SHORT);
        ((BaseActivity)getActivity()).showToast(getString(R.string.welcome),Toast.LENGTH_LONG);
        getActivity().finish();
    }


    @Override
    public void operationFailure(int code) {
        ((BaseActivity)getActivity()).showToast(LoginUtils.parseCodeToStringError(code),Toast.LENGTH_LONG);
        ((LoginActivity) getActivity()).hideLoader();
    }

    @EditorAction(R.id.repeat_password)
    void onEditorAction(TextView hello, int actionId, KeyEvent keyEvent) {
        if (actionId == LetsEatConstants.CONTINUE_EDITOR_ACTION_ID){
            ((LoginActivity)getActivity()).hideKeyboard();
        }
    }


    public boolean isSignUpValid(){
        if (mEmailView == null
                || mPasswordView == null
                || mPasswordViewRepeat == null){
            ((BaseActivity)getActivity()).showToast(getString(R.string.login_error_unexpected),Toast.LENGTH_LONG);
            return false;
        }else{
            if (mEmailView.getText().toString().isEmpty()
                    || mPasswordView.getText().toString().isEmpty()
                    || mPasswordViewRepeat.getText().toString().isEmpty()){
                ((BaseActivity)getActivity()).showToast(getString(R.string.signup_fields_error),Toast.LENGTH_LONG);
                return false;
            }else{
                if (LoginUtils.isEmailValid(mEmailView.getText().toString())){
                    if (mPasswordView.getText().toString().equals(mPasswordViewRepeat.getText().toString())){
                        return true;
                    }else{
                        ((BaseActivity)getActivity()).showToast(getString(R.string.signup_diferent_password_error),Toast.LENGTH_LONG);
                        return false;
                    }
                }else{
                    ((BaseActivity)getActivity()).showToast(getString(R.string.login_error_format_mail),Toast.LENGTH_LONG);
                    return false;
                }

            }
        }
    }
}

