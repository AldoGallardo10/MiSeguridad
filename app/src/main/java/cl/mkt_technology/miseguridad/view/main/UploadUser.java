package cl.mkt_technology.miseguridad.view.main;

import com.google.firebase.database.DatabaseReference;

import cl.mkt_technology.miseguridad.data.CurrentUser;
import cl.mkt_technology.miseguridad.data.Nodes;
import cl.mkt_technology.miseguridad.models.User;

/**
 * Created by Aldo Gallardo on 26-03-2017.
 */

public class UploadUser {

    public  void toFirebase(){

        final CurrentUser currentUser = new CurrentUser();
        User user = new User();
        user.setName(currentUser.name());
        user.setEmail(currentUser.email());
        user.setUid(currentUser.userId());

        DatabaseReference userReference = new Nodes().userById(currentUser.userId());
        userReference.setValue(user);
    }
}
