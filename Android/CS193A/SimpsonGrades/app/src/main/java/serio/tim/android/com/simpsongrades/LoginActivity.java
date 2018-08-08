package serio.tim.android.com.simpsongrades;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import stanford.androidlib.SimpleActivity;

public class LoginActivity extends SimpleActivity {

    public static final String FIREBASE_USERNAME = "mstepp@stanford.edu";
    public static final String FIREBASE_PASSWORD = "1234567";

    //public static final String FIREBASE_USERNAME = "serio.tim@gmail.com";
    //public static final String FIREBASE_PASSWORD = "Rewq32uiop";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(FIREBASE_USERNAME, FIREBASE_PASSWORD);
        mAuth.signInWithEmailAndPassword(FIREBASE_USERNAME, FIREBASE_PASSWORD);
    }

    public void loginClick(View view) {
        String name = $ET(R.id.name).getText().toString();
        final String password = $ET(R.id.password).getText().toString();

        DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
        DatabaseReference students = fb.child("simpsons/students");
        Query bart = students.orderByChild("name").equalTo(name);
        //DatabaseReference bart = fb.child("simpsons/students/123/");
        bart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {
                Student stu = data.getChildren().iterator().next().getValue(Student.class);
                //Student stu = data.getValue(Student.class);
                String correntPassword = stu.password;
                if(password.equals(correntPassword)) {
                    log("Log in successful");
                    startActivity(GradesActivity.class, "name", stu.name, "id", stu.id);
                } else {
                    log("Log in failed");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                log("onCancelled: " + databaseError);
            }
        });
    }
}
