package serio.tim.android.com.instaslam.Model;

import android.net.Uri;

/**
 * Created by Tim on 10/14/17.
 */

public class InstaImage {
    public Uri getImgResourceUrl() {
        return imgResourceUrl;
    }

    private Uri imgResourceUrl;

    public InstaImage(Uri imgResourceUrl) {
        this.imgResourceUrl = imgResourceUrl;
    }
}
