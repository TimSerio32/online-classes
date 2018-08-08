package serio.tim.android.com.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNum;

    public void makeToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    public void guessPressed(View view) {
        EditText guessEditText = (EditText)findViewById(R.id.guessEditText);

        int intGuess = Integer.parseInt(guessEditText.getText().toString());
        if(randomNum == intGuess) {
            makeToast("You got it! Try again.");
            Random rand = new Random();
            randomNum = rand.nextInt(20) + 1;
        } else if(randomNum > intGuess) {
            makeToast("Guess Higher");
        } else if(randomNum < intGuess) {
            makeToast("Guess Lower");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();
        randomNum = rand.nextInt(20) + 1;
    }
}
