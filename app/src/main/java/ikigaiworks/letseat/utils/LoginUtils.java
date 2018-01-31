package ikigaiworks.letseat.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.App;
import ikigaiworks.letseat.app.LetsEatConstants;

/**
 * Created by sergiolizanamontero on 29/9/17.
 */

public class LoginUtils {

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public static String parseCodeToStringError(int code) {
        switch (code) {
            case LetsEatConstants.LOGIN_ERROR_INVALID_CREDENTIALS_CODE:
            case LetsEatConstants.LOGIN_ERROR_PASSWORD_CODE:
            case LetsEatConstants.LOGIN_ERROR_USER_CODE:
                return App.getAppContext().getString(R.string.login_error_invalid_credentials_invalid);
            case LetsEatConstants.SIGNIN_ERROR_INVALID_EMAIL_CODE:
                return App.getAppContext().getString(R.string.signup_invalid_email_error);
            case LetsEatConstants.SIGNIN_ERROR_USER_EXIST_CODE:
                return App.getAppContext().getString(R.string.signup_user_exist_error);
            case LetsEatConstants.SIGNIN_ERROR_WEAK_PASSWORD_CODE:
                return App.getAppContext().getString(R.string.signup_weak_password_error);
            default:
                return App.getAppContext().getString(R.string.login_error_unexpected);
        }
    }


}
