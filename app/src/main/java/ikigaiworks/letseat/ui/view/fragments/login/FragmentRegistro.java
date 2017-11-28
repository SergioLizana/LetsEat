package ikigaiworks.letseat.ui.view.fragments.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.ViewById;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.FirebaseCommon;
import ikigaiworks.letseat.app.LetsEatConstants;
import ikigaiworks.letseat.ui.presenters.login.LoginPresenter;
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
        Toast.makeText(getActivity(),"Se ha registrado un usuario correctamente", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity().getApplicationContext(),
                "Bienvenido a Lets Eats "+ FirebaseCommon.getFirebaseAuth().getCurrentUser().getEmail(),
                Toast.LENGTH_LONG).show();
        getActivity().finish();
    }


    @Override
    public void operationFailure(int code) {
        ((LoginActivity) getActivity()).hideLoader();
        Toast.makeText(getActivity(),LoginUtils.parseCodeToStringError(code), Toast.LENGTH_LONG).show();
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
            Toast.makeText(getActivity(),getString(R.string.login_error_unexpected),Toast.LENGTH_LONG).show();
            return false;
        }else{
            if (mEmailView.getText().toString().isEmpty()
                    || mPasswordView.getText().toString().isEmpty()
                    || mPasswordViewRepeat.getText().toString().isEmpty()){
                Toast.makeText(getActivity(),getString(R.string.signup_fields_error),Toast.LENGTH_LONG).show();
                return false;
            }else{
                if (LoginUtils.isEmailValid(mEmailView.getText().toString())){
                    if (mPasswordView.getText().toString().equals(mPasswordViewRepeat.getText().toString())){
                        return true;
                    }else{
                        Toast.makeText(getActivity(),getString(R.string.signup_diferent_password_error),Toast.LENGTH_LONG).show();
                        return false;
                    }
                }else{
                    Toast.makeText(getActivity(),getString(R.string.login_error_format_mail),Toast.LENGTH_LONG).show();
                    return false;
                }

            }
        }
    }
}

