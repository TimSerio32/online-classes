package serio.tim.android.com.devslopesradio.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import serio.tim.android.com.devslopesradio.Fragments.DetailsFragment;
import serio.tim.android.com.devslopesradio.Fragments.MainFragment;
import serio.tim.android.com.devslopesradio.Model.Station;
import serio.tim.android.com.devslopesradio.R;

public class MainActivity extends AppCompatActivity {

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    private static void setMainActivity(MainActivity mainActivity) {
        MainActivity.mainActivity = mainActivity;
    }

    private static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity.setMainActivity(this);

        FragmentManager fm = getSupportFragmentManager();
        MainFragment mainFragment = (MainFragment)fm.findFragmentById(R.id.container_main);

        if(mainFragment == null) {
            mainFragment = MainFragment.newInstance("blah", "kah");
            fm.beginTransaction().add(R.id.container_main, mainFragment).commit();
        }
    }

    public void loadDetailsScreen(Station selectedStation) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new DetailsFragment()).addToBackStack(null).commit();
    }
}
