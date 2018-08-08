package serio.tim.android.com.teatimer.Home;

import java.util.ArrayList;

/**
 * Created by Tim on 11/14/17.
 */

public class TeaTypeList {
    public static ArrayList<TeaType> getList() {
        ArrayList teaTypes = new ArrayList<TeaType>();
        teaTypes.add(new TeaType("Green Tea"));
        teaTypes.add(new TeaType("Black Tea"));
        teaTypes.add(new TeaType("Oolong Tea"));
        teaTypes.add(new TeaType("White Tea"));
        teaTypes.add(new TeaType("Rooibos Tea"));
        teaTypes.add(new TeaType("Gyokuro"));
        teaTypes.add(new TeaType("Yerba Mate"));
        teaTypes.add(new TeaType("Pu Erh"));
        return teaTypes;
    }

}
