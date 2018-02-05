package ikigaiworks.letseat.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.app.App;

/**
 * Created by sergiolizanamontero on 31/1/18.
 */

public class InternetUtils {

    public static final int CONNECTION_CODE = 876;

    public static boolean isOnline(final Activity activity) {
        ConnectivityManager cm =
                (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        alertwifi(activity);
        return false;
    }


    private static void alertwifi(final Activity activity) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                activity);

        // set title
        alertDialogBuilder.setTitle(App.getInstance().getApplicationContext().getResources().getString(R.string.internet_dialog_title));

        // set dialog message
        alertDialogBuilder
                .setMessage(App.getInstance().getApplicationContext().getResources().getString(R.string.internet_dialog_msg))
                .setCancelable(false)
                .setPositiveButton(App.getInstance().getApplicationContext().getResources().getString(R.string.internet_dialog_wifi_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        activity.startActivityForResult(new Intent(Settings.ACTION_WIRELESS_SETTINGS),CONNECTION_CODE);
                    }
                })
                .setNeutralButton(App.getInstance().getApplicationContext().getResources().getString(R.string.internet_dialog_settings_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        activity.startActivityForResult(new Intent("android.settings.SETTINGS"),CONNECTION_CODE);
                    }
                })
                .setNegativeButton(App.getInstance().getApplicationContext().getResources().getString(R.string.internet_dialog_back_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        exit(activity);


                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private static void exit(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

}
