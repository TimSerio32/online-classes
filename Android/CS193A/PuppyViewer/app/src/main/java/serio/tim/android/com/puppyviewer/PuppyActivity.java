package serio.tim.android.com.puppyviewer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleList;

public class PuppyActivity extends SimpleActivity {

    private static final String WEBSITE_DIRECTORY = "http://www.martystepp.com/dogs/";

    private static final String[] ALL_IMAGES = {
            "barney-and-clyde-12.jpg",
            "daisy-14.jpg",
            "barney-and-clyde-02-large.jpg"
    };

    @BindView(R.id.puppyphoto) ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puppy);
        ButterKnife.bind(this);
        downloadImageNames();
        //SimpleList.with(this).setItems(R.id.puppy_spinner, ALL_IMAGES);
    }

    @OnItemSelected(R.id.puppy_spinner)
    public void itemGotSelected(AdapterView<?> parent, View view, int position, long id) {
        String filename = ALL_IMAGES[position];
        log("You clicked: " + filename);
    }

    private void downloadImageNames() {
        Ion.with(this)
                .load(WEBSITE_DIRECTORY + "files.txt")
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> result) {
                        String[] lines = result.getResult().split("\n");
                        SimpleList.with(PuppyActivity.this).setItems(R.id.puppy_spinner, lines);
                    }
                });
    }

    public void clickMeClick(View view) {
        YoYo.with(Techniques.Wobble).duration(2000).withListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                displayImage();
            }
        }).playOn(view);
    }

    private void displayImage() {
        ImageView img = $IV(R.id.puppyphoto);
        Picasso.get().load("http://www.martystepp.com/dogs/barney-and-clyde-12.jpg").placeholder(R.drawable.loading).into(img);
    }
}
