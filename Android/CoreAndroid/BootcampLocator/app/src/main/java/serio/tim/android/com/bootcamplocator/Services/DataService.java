package serio.tim.android.com.bootcamplocator.Services;

import java.util.ArrayList;

import serio.tim.android.com.bootcamplocator.Model.Devslopes;

/**
 * Created by Tim on 10/13/17.
 */

public class DataService {
    private static final DataService instance = new DataService();

    public static DataService getInstance() {
        return instance;
    }

    private DataService() {

    }

    public ArrayList<Devslopes>getBootcampLocationsWithin10MilesOfZip(int zipcode) {

        ArrayList<Devslopes> list = new ArrayList<>();
        list.add(new Devslopes(41.878876f, -87.635915f, "Sears Tower", "233 S Wacker Dr, Chicago, IL 60606", "sears"));
        //Add more locations
        return list;

    }
}
