package serio.tim.android.com.simpsongrades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleList;

public class GradesActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        // extract parameters from intent
        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", /* default */ 0);
        final String name = intent.getStringExtra("name");
        $TV(R.id.heading).setText(name + "'s Grades");

        // TODO: look up grades for this student
        // SQL:  SELECT * FROM grades WHERE student_id = id;
        final DatabaseReference fb = FirebaseDatabase.getInstance().getReference();
        Query query = fb.child("simpsons/grades")
                .orderByChild("student_id").equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {
                // each child: key   = -KBNIF3XuUYw81zdzQmB,
                //             value = {course_id: 10001, course_name: "Computer Science 142", grade: "B-",  student_id: 123}
                ArrayList<String> items = new ArrayList<>();
                for (DataSnapshot child : data.getChildren()) {
                    Grade grade = child.getValue(Grade.class);
                    items.add(grade.grade + " in " + grade.course_name);
                }
                SimpleList.with(GradesActivity.this)
                        .setItems(R.id.gradelist, items);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                log("onCancelled: " + databaseError);
            }
        });
    }
}
