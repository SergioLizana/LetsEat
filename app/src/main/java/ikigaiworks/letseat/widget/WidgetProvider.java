package ikigaiworks.letseat.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.ui.view.activities.FavOrderActivity_;

public class WidgetProvider extends AppWidgetProvider {
    public static String EXTRA_WORD =
            "ikigaiworks.letseat.widget.WORD";
    public static final String ACTION_TOAST = "ikigaiworks.letseat.widget.ACTION_TOAST";
    public static final String WIDGET_ROW_ONCLICK = "ikigaiworks.letseat.widget.ACTION_CLICK";


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


            Intent clickIntentTemplate = new Intent(ctxt, FavOrderActivity_.class);
            PendingIntent clickPendingIntentTemplate = TaskStackBuilder.create(ctxt)
                    .addNextIntentWithParentStack(clickIntentTemplate)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            widget.setPendingIntentTemplate(R.id.fav_list_widget, clickPendingIntentTemplate);

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds[i], R.id.fav_list_widget);

            appWidgetManager.updateAppWidget(appWidgetIds[i], widget);
        }


        super.onUpdate(ctxt, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

}
