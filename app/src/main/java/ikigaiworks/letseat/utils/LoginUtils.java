package ikigaiworks.letseat.utils;

/**
 * Created by sergiolizanamontero on 29/9/17.
 */

public class LoginUtils {

    private static final String EMAIL_PATTERN  = "[a-zA-Z0-9_-]+@[a-z]+\\.+[a-z]+";

    public static boolean validateEmail(String eMail){
        if (eMail.matches(EMAIL_PATTERN)){
            return true;
        }else{
            return false;
        }
    }





}
