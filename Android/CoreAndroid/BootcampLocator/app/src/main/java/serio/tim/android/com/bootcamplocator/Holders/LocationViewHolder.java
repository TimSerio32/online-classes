package serio.tim.android.com.bootcamplocator.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import serio.tim.android.com.bootcamplocator.Model.Devslopes;
import serio.tim.android.com.bootcamplocator.R;

/**
 * Created by Tim on 10/13/17.
 */

public class LocationViewHolder extends RecyclerView.ViewHolder {

    private ImageView locationImage;
    private TextView locationTitle;
    private TextView locationAddress;

    public LocationViewHolder(View itemView) {
        super(itemView);

        locationImage = (ImageView)itemView.findViewById(R.id.location_img);
        locationTitle = (TextView)itemView.findViewById(R.id.location_title);
        locationAddress = (TextView)itemView.findViewById(R.id.location_address);
    }

    public void updateUI(Devslopes location) {
        String uri = location.getImgUrl();
        int resource = locationImage.getResources().getIdentifier(uri, null, locationImage.getContext().getPackageName());
        locationImage.setImageResource(resource);
        locationTitle.setText(location.getLocationTitle());
        locationAddress.setText(location.getLocationAddress());
    }
}
