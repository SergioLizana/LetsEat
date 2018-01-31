package ikigaiworks.letseat.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.widget.Toast;

import ikigaiworks.letseat.utils.DiscreteScrollViewOptions;
import ikigaiworks.letseat.widget.WidgetProvider;


public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    private static Context context;

    public static boolean appInit = false;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DiscreteScrollViewOptions.init(this);
        App.context = getApplicationContext();
        appInit = true;
        updateMyWidgets(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static void updateMyWidgets(Context context) {
        int[] ids = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, WidgetProvider.class));
        WidgetProvider myWidget = new WidgetProvider();
        myWidget.onUpdate(context, AppWidgetManager.getInstance(context), ids);
    }

}
