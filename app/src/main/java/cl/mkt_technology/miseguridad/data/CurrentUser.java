package cl.mkt_technology.miseguridad.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class CurrentUser {

    //obtenemos el usuario
    public FirebaseUser get(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    //obtenemos el email a partir del usuario
    public String email(){
        return get().getEmail();
    }

    public String name(){
        return get().getDisplayName();
    }

    public String userId(){
        return get().getUid();
    }


}
