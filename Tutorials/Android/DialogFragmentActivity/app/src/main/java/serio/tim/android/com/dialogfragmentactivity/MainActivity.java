package serio.tim.android.com.dialogfragmentactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button mOpenDialog;
    private TextView mInputDisplay;

    private String mInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenDialog = findViewById(R.id.open_dialog);
        mInputDisplay = findViewById(R.id.input_display);
    }
}
