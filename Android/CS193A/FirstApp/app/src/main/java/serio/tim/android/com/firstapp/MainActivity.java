package serio.tim.android.com.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int num1;
    private int num2;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pickNumbers();
    }

    public void pickNumbers() {
        TextView pointsView = (TextView) findViewById(R.id.pointsView);
        pointsView.setText("Points: " + points);
        Random randy = new Random();
        while(true) {
            num1 = randy.nextInt(10);
            num2 = randy.nextInt(10);
            if(num1 != num2) {
                break;
            }
        }

        Button left = (Button) findViewById(R.id.left_button);
        Button right = (Button) findViewById(R.id.right_button);

        left.setText(String.valueOf(num1));
        right.setText(String.valueOf(num2));
    }

    public void leftButtonClick(View view) {
        if(num1 > num2) {
            points++;
        } else {
            points--;
        }
        pickNumbers();
    }

    public void rightButtonClick(View view) {
        if(num2 > num1) {
            points++;
        } else {
            points--;
        }
        pickNumbers();
    }
}
