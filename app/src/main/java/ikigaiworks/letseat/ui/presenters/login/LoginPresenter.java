package ikigaiworks.letseat.ui.presenters.login;

/**
 * Created by sergiolizanamontero on 23/11/17.
 */

public interface LoginPresenter {
    interface SignIn {
        void signInOK();

        void signInKO(int code);
    }

    interface Login {
        void loginOK();

        void loginKO(int code);
    }

}
