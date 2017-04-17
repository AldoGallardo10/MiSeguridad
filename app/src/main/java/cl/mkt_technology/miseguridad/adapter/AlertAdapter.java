package cl.mkt_technology.miseguridad.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import cl.mkt_technology.miseguridad.R;
import cl.mkt_technology.miseguridad.data.Nodes;
import cl.mkt_technology.miseguridad.models.Alert;
import cl.mkt_technology.miseguridad.view.detail.MapsActivity;

/**
 * Created by Aldo Gallardo on 12-04-2017.
 */

public class AlertAdapter extends FirebaseRecyclerAdapter<Alert,AlertAdapter.AlertHolder>{


    public AlertAdapter() {
        super(Alert.class, R.layout.custom_alert,AlertHolder.class, new Nodes().allAlerts());
    }

    @Override
    protected void populateViewHolder(AlertHolder viewHolder, Alert model, int position) {
        TextView name, adress;
        name = (TextView) viewHolder.itemView.findViewById(R.id.nameCustomTv);
        adress = (TextView) viewHolder.itemView.findViewById(R.id.adressCustomTv);
        final String longitud = model.getLongitud();
        final String latitud = model.getLatitud();

        name.setText(model.getName());
        adress.setText(model.getAdress());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent intent = new Intent(v.getContext(),MapsActivity.class);
                intent.putExtra("longitud",longitud);
                intent.putExtra("latitud",latitud);
                v.getContext().startActivity(intent);
            }
        });

    }




    public static class AlertHolder extends RecyclerView.ViewHolder {
        public AlertHolder(View itemView) {
            super(itemView);
        }


    }
}
