package serio.tim.android.com.simpsongrades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class Student implements Serializable {
    int id;
    String name;
    String email;
    String password;

    public Student() {}

    public String toString() {
        return "Student {id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "}";
    }
}
