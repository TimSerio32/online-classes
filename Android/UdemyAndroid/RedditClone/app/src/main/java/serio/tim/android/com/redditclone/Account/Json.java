package serio.tim.android.com.redditclone.Account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tim on 11/5/17.
 */

public class Json {

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Json{" +
                "data=" + data +
                '}';
    }
}
