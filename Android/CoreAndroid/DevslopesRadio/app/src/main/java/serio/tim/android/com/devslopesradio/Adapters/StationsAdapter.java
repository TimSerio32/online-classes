package serio.tim.android.com.devslopesradio.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import serio.tim.android.com.devslopesradio.Activities.MainActivity;
import serio.tim.android.com.devslopesradio.Holders.StationViewHolder;
import serio.tim.android.com.devslopesradio.Model.Station;
import serio.tim.android.com.devslopesradio.R;

/**
 * Created by Tim on 10/11/17.
 */

public class StationsAdapter extends RecyclerView.Adapter<StationViewHolder> {

    private ArrayList<Station> stations;


    public StationsAdapter(ArrayList<Station> stations) {
        this.stations = stations;
    }

    @Override
    public void onBindViewHolder(StationViewHolder holder, int position) {
        final Station station = stations.get(position);
        holder.updateUI(station);

        final int p = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getMainActivity().loadDetailsScreen(station);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View stationCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_station, parent, false);
        return new StationViewHolder(stationCard);
    }
}
