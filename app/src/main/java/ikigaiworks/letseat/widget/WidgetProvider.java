package ikigaiworks.letseat.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.ComponentName;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.ui.view.activities.MainActivity;
import ikigaiworks.letseat.ui.view.activities.MainActivity_;

public class WidgetProvider extends AppWidgetProvider {
    public static String EXTRA_WORD =
            "ikigaiworks.letseat.widget.WORD";
    public static final String ACTION_TOAST = "ikigaiworks.letseat.widget.ACTION_TOAST";

    @Override
    public void onUpdate(Context ctxt, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        for (int i = 0; i < appWidgetIds.length; i++) {
            Intent svcIntent = new Intent(ctxt, WidgetService.class);

            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews widget = new RemoteViews(ctxt.getPackageName(),
                    R.layout.new_app_widget);

            widget.setRemoteAdapter(appWidgetIds[i], R.id.fav_list_widget,
                    svcIntent);


            final Intent onItemClick = new Intent(ctxt, WidgetProvider.class);
            onItemClick.setAction(ACTION_TOAST);
            onItemClick.setData(Uri.parse(onItemClick
                    .toUri(Intent.URI_INTENT_SCHEME)));
            final PendingIntent onClickPendingIntent = PendingIntent
                    .getBroadcast(ctxt, 0, onItemClick,
                            PendingIntent.FLAG_UPDATE_CURRENT);
            widget.setPendingIntentTemplate(R.id.fav_list_widget,
                    onClickPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetIds[i], widget);
        }


        super.onUpdate(ctxt, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_TOAST)) {
            String item = intent.getExtras().getString(EXTRA_WORD);
            Toast.makeText(context, item, Toast.LENGTH_LONG).show();
        }
        super.onReceive(context, intent);
    }

}
