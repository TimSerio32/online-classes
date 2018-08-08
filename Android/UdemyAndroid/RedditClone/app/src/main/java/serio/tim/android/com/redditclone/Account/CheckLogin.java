package serio.tim.android.com.redditclone.Account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tim on 11/5/17.
 */

public class CheckLogin {

    @SerializedName("json")
    @Expose
    private Json json;

    public Json getJson() {
        return json;
    }

    public void setJson(Json json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "CheckLogin{" +
                "json=" + json +
                '}';
    }
}
