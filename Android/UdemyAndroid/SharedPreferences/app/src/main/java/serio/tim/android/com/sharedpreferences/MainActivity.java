package serio.tim.android.com.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("serio.tim.android.com.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> friends = new ArrayList<>();

        friends.add("John");
        friends.add("Paul");
        friends.add("George");
        friends.add("Ringo");

        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> newFriends = new ArrayList<>();
        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //sharedPreferences.edit().putString("username", "tim").apply();

        //String username = sharedPreferences.getString("username", "");
    }
}
