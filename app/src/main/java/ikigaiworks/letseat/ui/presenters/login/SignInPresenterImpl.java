package ikigaiworks.letseat.ui.presenters.login;

import android.content.Context;

import ikigaiworks.letseat.manager.FirebaseManagerImpl;
import ikigaiworks.letseat.ui.view.view.LoginView;

/**
 * Created by sergiolizanamontero on 23/11/17.
 */

public class SignInPresenterImpl implements LoginPresenter.SignIn {

    Context c;
    LoginView view;
    private FirebaseManagerImpl firebaseManager;

    public SignInPresenterImpl(Context c, LoginView view) {
        this.view = view;
        this.c = c;
        firebaseManager = new FirebaseManagerImpl().getInstance();

    }

    public void signIn(String email, String password) {
        firebaseManager.signUp(email, password, this);
    }

    @Override
    public void signInOK() {
        view.operationOK();
    }

    @Override
    public void signInKO(int code) {
        view.operationFailure(code);
    }
}
