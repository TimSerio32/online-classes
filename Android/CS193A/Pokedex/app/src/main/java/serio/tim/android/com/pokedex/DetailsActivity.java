package serio.tim.android.com.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import stanford.androidlib.SimpleActivity;

public class DetailsActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}
