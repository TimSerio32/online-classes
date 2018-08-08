package serio.tim.android.com.simpsongrades;

import java.io.Serializable;

public class Grade implements Serializable{
    int course_id;
    String course_name;
    String grade;
    int student_id;

    public Grade() {}

    public String toString() {
        return "Grade {course_id=" + course_id + ", course_name=" + course_name
                + ", student_id=" + student_id + ", grade=" + grade + "}";
    }
}
