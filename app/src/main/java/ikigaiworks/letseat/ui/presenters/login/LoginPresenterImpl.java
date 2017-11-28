package ikigaiworks.letseat.ui.presenters.login;

import android.content.Context;

import ikigaiworks.letseat.manager.FirebaseManager;
import ikigaiworks.letseat.manager.FirebaseManagerImpl;
import ikigaiworks.letseat.ui.view.view.LoginView;

/**
 * Created by sergiolizanamontero on 23/11/17.
 */

public class LoginPresenterImpl implements LoginPresenter.Login {

    Context c;
    LoginView view;
    private FirebaseManager firebaseManager;

    public LoginPresenterImpl(Context c, LoginView view){
        this.view = view;
        this.c = c;
        firebaseManager = FirebaseManagerImpl.getInstance();

    }

    public void doLogin(String email,String password){
        firebaseManager.login(email,password,this);
    }

    @Override
    public void loginOK() {
        view.operationOK();
    }

    @Override
    public void loginKO(int code) {
        view.operationFailure(code);
    }
}
