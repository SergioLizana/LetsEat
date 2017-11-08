package ikigaiworks.letseat.utils;

import android.app.Application;
import android.content.Context;


public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DiscreteScrollViewOptions.init(this);
        App.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return App.context;
    }

}
