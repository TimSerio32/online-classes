package serio.tim.android.com.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class Number {
        int number;

        public boolean isSquare() {
            double squareRoot = Math.sqrt(number);
            if(squareRoot == Math.floor(squareRoot)) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isTriangular() {
            int x = 1;
            int triangularNumber = 1;

            while(triangularNumber < number) {
                x++;
                triangularNumber = triangularNumber + x;
            }
            if(triangularNumber == number) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void testNumber(View view) {
        String message = "";
        EditText usersNumber = (EditText)findViewById(R.id.usersNumber);
        if(usersNumber.getText().toString().isEmpty()) {
            message = "Please enter a number.";
        }
        Number myNumber = new Number();
        myNumber.number = Integer.parseInt(usersNumber.getText().toString());
        if(myNumber.isSquare() && myNumber.isTriangular()) {
            message = myNumber.number + " is a triangular number and a square number.";
        } else if (myNumber.isSquare()) {
            message = myNumber.number + " is a square number.";
        } else if (myNumber.isTriangular()) {
            message = myNumber.number + " is a triangular number.";
        } else {
            message = myNumber.number + " is not a triangular number or a square number.";
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
