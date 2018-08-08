package serio.tim.android.com.tmnt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class TMNTActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmnt);
    }

    public void turtleClick(View view) {
        ImageView img = (ImageView) findViewById(R.id.turtle_picture);

        if(view.getId() == R.id.leobutton) {
            img.setImageResource(R.drawable.leo);
        } else if(view.getId() == R.id.mikebutton) {
            img.setImageResource(R.drawable.michelangelo);
        } else if(view.getId() == R.id.donbutton) {
            img.setImageResource(R.drawable.donatello);
        } else {
            img.setImageResource(R.drawable.raphael);
        }
    }

    public void okClick(View view) {
        EditText edit = (EditText) findViewById(R.id.input_box);
        String text = edit.getText().toString();
        //Toast.makeText(this, "You typed: " + text, Toast.LENGTH_SHORT).show();

        Log.d("marty", "The user typed: " + text);
    }
}
