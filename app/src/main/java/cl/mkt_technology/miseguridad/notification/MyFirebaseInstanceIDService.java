package cl.mkt_technology.miseguridad.notification;



import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Aldo Gallardo on 17-04-2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Notifications");
        reference.child("token").setValue(refreshToken);
    }
}
    