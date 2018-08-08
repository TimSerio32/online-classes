package serio.tim.android.com.teatimer.Home;

import java.util.List;

/**
 * Created by Tim on 11/9/17.
 */

public class TeaType {
    String name;
    //int color;

    TeaType(String name) {
        this.name = name;
        //this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
