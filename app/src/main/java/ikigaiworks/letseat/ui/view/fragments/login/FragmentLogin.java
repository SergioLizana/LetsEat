package ikigaiworks.letseat.ui.view.fragments.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.ViewById;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.FirebaseCommon;
import ikigaiworks.letseat.app.LetsEatConstants;
import ikigaiworks.letseat.ui.presenters.login.LoginPresenterImpl;
import ikigaiworks.letseat.ui.view.activities.LoginActivity;
import ikigaiworks.letseat.ui.view.view.LoginView;
import ikigaiworks.letseat.utils.LoginUtils;

/**
 * A login screen that offers login via email/password.
 */
@EFragment(R.layout.fragment_login)
public class FragmentLogin extends Fragment implements LoginView{


    @ViewById(R.id.email)
    protected EditText mEmailView;
    @ViewById(R.id.password)
    protected EditText mPasswordView;
    @ViewById(R.id.login_container)
    protected ConstraintLayout loginContainer;


    private LoginPresenterImpl loginPresenter;


    @AfterViews
    void init(){
        loginPresenter = new LoginPresenterImpl(getActivity(),this);
    }



   @Click(R.id.email_sign_in_button)
    void clickLogin(){
        doLogin();
   }

    @EditorAction(R.id.password)
    void onEditorAction(TextView hello, int actionId, KeyEvent keyEvent) {
       if (actionId == LetsEatConstants.LOGIN_EDITOR_ACTION_ID){
           ((LoginActivity)getActivity()).hideKeyboard();
           doLogin();
       }
    }

    private void doLogin(){
        if (LoginUtils.isEmailValid(mEmailView.getText().toString())) {
            ((LoginActivity) getActivity()).showLoader();
            loginPresenter.doLogin(mEmailView.getText().toString(), mPasswordView.getText().toString());
        }else{
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.login_error_format_mail),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void operationOK() {
        Toast.makeText(getActivity().getApplicationContext(),
                "Bienvenido a Lets Eats "+ FirebaseCommon.getFirebaseAuth().getCurrentUser().getEmail(),
                Toast.LENGTH_LONG).show();
        getActivity().finish();
    }

    @Override
    public void operationFailure(int code) {
        ((LoginActivity)getActivity()).hideLoader();
        Toast.makeText(getActivity().getApplicationContext(), LoginUtils.parseCodeToStringError(code),
                Toast.LENGTH_LONG).show();
    }
}

