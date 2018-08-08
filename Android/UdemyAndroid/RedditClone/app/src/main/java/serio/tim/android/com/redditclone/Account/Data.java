package serio.tim.android.com.redditclone.Account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tim on 11/5/17.
 */

public class Data {

    @SerializedName("modhash")
    @Expose
    private String modhash;

    @SerializedName("cookie")
    @Expose
    private String cookie;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @Override
    public String toString() {
        return "Data{" +
                "modhash='" + modhash + '\'' +
                ", cookie='" + cookie + '\'' +
                '}';
    }
}
