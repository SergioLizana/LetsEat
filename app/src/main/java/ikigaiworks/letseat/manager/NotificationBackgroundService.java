package ikigaiworks.letseat.manager;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.ui.view.activities.MainActivity_;

/**
 * Created by sergiolizanamontero on 27/1/18.
 */

public class NotificationBackgroundService extends IntentService {

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        pushNotification();
    }

    public NotificationBackgroundService() {
        super(NotificationBackgroundService.class.getName().toString());
    }


    private void pushNotification() {
        Context ctx = getApplicationContext();
        Intent intent = new Intent(ctx, MainActivity_.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, intent, 0);
        String title = "Taste Bakery";
        String text = "Su pedido con numero 99898 está listo para recoger";
        Notification noti = new Notification.Builder(ctx)
                .setContentTitle(title)
                .setContentText(text).setSmallIcon(R.drawable.ic_receipt_black_24dp)
                .setContentIntent(pIntent)
                .build();
        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
    }



}
