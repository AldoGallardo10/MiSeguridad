package cl.mkt_technology.miseguridad.alert;

import com.google.firebase.database.DatabaseReference;

import cl.mkt_technology.miseguridad.data.CurrentUser;
import cl.mkt_technology.miseguridad.data.Nodes;
import cl.mkt_technology.miseguridad.models.Alert;

/**
 * Created by Aldo Gallardo on 12-04-2017.
 */

public class SendAlert {

    private Validate callback;

    public SendAlert(Validate callback) {
        this.callback = callback;
    }

    public void enviarAlert(String adress, String latitud, String longitud)
    {
            CurrentUser currentUser = new CurrentUser();
            Alert ale = new Alert();
            ale.setName(currentUser.name());
            ale.setAdress(adress);
            ale.setLatitud(latitud);
            ale.setLongitud(longitud);

            new Nodes().allAlerts().push().setValue(ale);
            callback.success();

    }
}
