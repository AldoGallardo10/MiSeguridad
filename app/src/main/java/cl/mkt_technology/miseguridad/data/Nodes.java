package cl.mkt_technology.miseguridad.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Aldo Gallardo on 24-03-2017.
 */

public class Nodes {

    private DatabaseReference root(){
        return FirebaseDatabase.getInstance().getReference();
    }
    public DatabaseReference users(){return root().child("users");}
    public DatabaseReference userById(String uid){
        return users().child(uid);
    }
    public DatabaseReference allAlerts(){ return root().child("alerts");}



}
