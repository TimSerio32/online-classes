package serio.tim.android.com.bootcamplocator.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import serio.tim.android.com.bootcamplocator.Holders.LocationViewHolder;
import serio.tim.android.com.bootcamplocator.Model.Devslopes;
import serio.tim.android.com.bootcamplocator.R;

/**
 * Created by Tim on 10/13/17.
 */

public class LocationsAdapter extends RecyclerView.Adapter<LocationViewHolder> {

    private ArrayList<Devslopes> locations;

    public LocationsAdapter(ArrayList<Devslopes> locations) {
        this.locations = locations;
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        final Devslopes location = locations.get(position);
        holder.updateUI(location);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load details page
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_location, parent, false);
        return new LocationViewHolder(card);
    }
}
