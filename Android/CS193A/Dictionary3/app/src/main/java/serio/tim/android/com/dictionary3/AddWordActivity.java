package serio.tim.android.com.dictionary3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.PrintStream;

import stanford.androidlib.SimpleActivity;

public class AddWordActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        Intent intent = getIntent();
        String extra = intent.getStringExtra("initialtext");

        EditText editText = (EditText) findViewById(R.id.new_word);
        editText.setText(extra);
    }

    public void addThisWordClick(View view) {
        EditText word = (EditText) findViewById(R.id.new_word);
        String newWord = word.getText().toString();

        EditText defn = (EditText) findViewById(R.id.new_defn);
        String newDefn = defn.getText().toString();

        PrintStream output = new PrintStream(openFileOutput("added_words.txt", MODE_PRIVATE | MODE_APPEND));
        output.println(newWord + "\t" + newDefn);
        output.close();

        Intent goBack = new Intent();
        goBack.putExtra("newword", newWord);
        goBack.putExtra("newdefn", newDefn);
        setResult(RESULT_OK, goBack);
        finish();
    }
}
