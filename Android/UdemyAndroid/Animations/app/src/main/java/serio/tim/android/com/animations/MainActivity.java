package serio.tim.android.com.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view) {
        ImageView fry = (ImageView)findViewById(R.id.fry);

        fry.animate()
                .translationXBy(1000f)
                .translationYBy(1000f)
                .rotationBy(3600)
                .setDuration(2000);
        //ImageView bender = (ImageView)findViewById(R.id.bender);
        //bender.animate().alpha(1f).setDuration(2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView fry = (ImageView)findViewById(R.id.fry);

        fry.setTranslationX(-1000f);
        fry.setTranslationY(1000f);
    }
}
