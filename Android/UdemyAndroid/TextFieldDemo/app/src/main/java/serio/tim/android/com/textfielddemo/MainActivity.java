package serio.tim.android.com.textfielddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void signInClicked(View view) {
        EditText usernameEditText = (EditText)findViewById(R.id.usernameEditText);
        EditText passwordEditText = (EditText)findViewById(R.id.passwordEditText);

        Log.i("Username", usernameEditText.getText().toString());
        Log.i("Password", passwordEditText.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}