package serio.tim.android.com.dictionary3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import stanford.androidlib.SimpleActivity;

public class MainActivity extends SimpleActivity {

    private static final int REQ_CODE_ADD_WORD = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playTheGameClick(View view) {
        Intent intent = new Intent(this, DictionaryActivity.class);
        startActivity(intent);
    }

    public void addANewWordClick(View view) {
        Intent intent = new Intent(this, AddWordActivity.class);
        intent.putExtra("initialtext", "FooBar");
        startActivityForResult(intent, REQ_CODE_ADD_WORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == REQ_CODE_ADD_WORD) {
            String newWord = intent.getStringExtra("newword");
            String newDefn = intent.getStringExtra("newdefn");

            Toast.makeText(this, "You added the word " + newWord, Toast.LENGTH_SHORT).show();
        }
    }
}
