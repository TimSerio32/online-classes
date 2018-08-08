package serio.tim.android.com.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase eventsDB = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            eventsDB.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)");
            //eventsDB.execSQL("CREATE TABLE IF NOT EXISTS events (event VARCHAR, year INT(4))");

            eventsDB.execSQL("INSERT INTO newUsers (name, age) VALUES ('John', 24)");
            eventsDB.execSQL("INSERT INTO newUsers (name, age) VALUES ('Jim', 30)");
            //eventsDB.execSQL("INSERT INTO users (name, age) VALUES ('John', 24)");
            //eventsDB.execSQL("INSERT INTO users (name, age) VALUES ('Jim', 30)");

            //eventsDB.execSQL("UPDATE users SET age = 25 WHERE name = 'John'");
            //eventsDB.execSQL("DELETE FROM users WHERE name = 'John'");

            eventsDB.execSQL("DELETE FROM newUsers WHERE id = 1");

            Cursor c = eventsDB.rawQuery("SELECT * FROM newUsers", null);
            //Cursor c = eventsDB.rawQuery("SELECT * FROM users WHERE name LIKE '%i%'", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();

            while(c != null) {
                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));

                c.moveToNext();
            }
            /*
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Tim', 22)");
            myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Marc', 22)");
            Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            c.moveToFirst();
            while(c != null) {
                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));
                c.moveToNext();
            }
            */
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
