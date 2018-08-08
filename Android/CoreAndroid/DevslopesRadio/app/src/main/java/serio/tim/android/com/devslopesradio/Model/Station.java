package serio.tim.android.com.devslopesradio.Model;

/**
 * Created by Tim on 10/11/17.
 */

public class Station {
    final String DRAWABLE = "drawable/";

    private String stationTitle;
    private String imgUri;

    public Station(String stationTitle, String imgUri) {
        this.stationTitle = stationTitle;
        this.imgUri = imgUri;
    }

    public String getStationTitle() {
        return stationTitle;
    }

    public String getImgUri() {
        return DRAWABLE + imgUri;
    }
}
