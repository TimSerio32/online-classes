package serio.tim.android.com.flagsoftheworld;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleDialog;

public class FlagsActivity extends SimpleActivity {

    private static final String[] COUNTRIES = {
            "Australia",
            "Brazil",
            "China",
            "Egypt",
            "France",
            "Germany",
            "Ghana",
            "Italy",
            "Japan",
            "Mexico",
            "Nepal",
            "Nigeria",
            "Spain",
            "United Kingdom",
            "United States"

    };

    // Instance initializer; runs before any other code (on construction)
    {
        setTraceLifecycle(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flags);


//        Button butt = new Button(this);
//        Button butt2 = new Button(this);
//        Button butt3 = new Button(this);
        GridLayout layout = (GridLayout) findViewById(R.id.activity_flags);
//        butt.setText("My button");
//        butt2.setText("Hello");
//        butt3.setText("Goodbye");

//        butt.setPadding(40, 0, 30, 20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        butt.setLayoutParams(params);
//        butt2.setLayoutParams(params);
//        butt3.setLayoutParams(params);
//        layout.addView(butt, 1);
//        layout.addView(butt2);
//        layout.addView(butt3);

        for(String name : COUNTRIES) {
            addFlag(name, layout);
        }
    }

    private void addFlag(final String countryName, GridLayout layout) {

        View flag = getLayoutInflater().inflate(R.layout.flag, null);
        TextView tv = (TextView) flag.findViewById(R.id.flag_text);
        tv.setText(countryName);

        String countryName2 = countryName.replace(" ", "").toLowerCase();

        int flagImageId = getResources().getIdentifier(countryName2, "drawable", getPackageName());

        ImageButton img = (ImageButton) flag.findViewById(R.id.flag_image);
        img.setImageResource(flagImageId);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(FlagsActivity.this, "You clicked " + countryName, Toast.LENGTH_SHORT).show();
                doTheDialog(countryName);
            }
        });

        layout.addView(flag);
    }

    private void doTheDialog(String countryName) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(FlagsActivity.this);
//        builder.setTitle("My Dialog");
//        builder.setMessage("You clicked " + countryName);
//
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(FlagsActivity.this, "You clicked OK", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();

        // Following code is the same as the above code (Stanford Library):

        //SimpleDialog.with(this).showAlertDialog("You clicked " + countryName);

        SimpleDialog.with(this).showInputDialog("Type your name:", "Submit");
    }

    @Override
    public void onInputDialogClose(AlertDialog dialog, String input) {
        toast("You typed: " + input);
    }
}
