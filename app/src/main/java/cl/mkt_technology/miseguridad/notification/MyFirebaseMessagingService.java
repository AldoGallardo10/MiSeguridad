package cl.mkt_technology.miseguridad.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Contacts;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.BufferedInputStream;
import java.util.Map;

import cl.mkt_technology.miseguridad.R;
import cl.mkt_technology.miseguridad.view.main.MainActivity;

import static android.R.attr.data;
import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Aldo Gallardo on 17-04-2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    private static  final  String USERNAME = "username";
    private static  final  String IMAGEURL = "imageUrl";
    private static  final  String EMAIL = "email";
    private static  final  String UID = "uid";
    private static  final  String TEXT = "text";
    String username, text;




    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0 ){
            Map<String,String > data = remoteMessage.getData();

            username = data.get(USERNAME);
            String imageUrl = data.get(IMAGEURL);
            String email = data.get(EMAIL);
            String uid = data.get(UID);
            text = data.get(TEXT);
            showNotification(data);
            
        }
    }

    private void showNotification(Map<String, String> payload) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(payload.get(username));
        builder.setContentText(payload.get(text));

        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
