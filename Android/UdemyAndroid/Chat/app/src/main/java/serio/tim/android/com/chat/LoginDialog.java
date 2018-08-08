package serio.tim.android.com.chat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 11/8/17.
 */

public class LoginDialog {
    static List<OnSuccessListener<EmailPasswordResult>> callbacks = new ArrayList<>();
    public static void onCredentials(final OnSuccessListener<EmailPasswordResult> callback) {
        callbacks.add(callback);
    }

    public static class EmailPasswordResult {
        public String email;
        public String password;

        public EmailPasswordResult() {

        }

        public EmailPasswordResult(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public static void showLoginPrompt(final Activity activity, final FirebaseApp app) {
        showLoginPrompt(activity, app, null);
    }

    public static void showLoginPrompt(final Activity activity, final FirebaseApp app, final OnSuccessListener<EmailPasswordResult> callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("What's your username?");
        LinearLayout parent = new LinearLayout(activity);

        parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        parent.setOrientation(LinearLayout.VERTICAL);

        final EditText email = new EditText(activity);
        email.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        email.setHint("Username");
        parent.addView(email);

        final EditText password = new EditText(activity);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        password.setHint("Password");
        parent.addView(password);

        builder.setView(parent);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance(app).createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        EmailPasswordResult result = new EmailPasswordResult(email.getText().toString(), password.getText().toString());
                        if(callback != null) {
                            callback.onSuccess(result);
                        } else {
                            for(OnSuccessListener<EmailPasswordResult> callback: callbacks) {
                                callback.onSuccess(result);
                            }
                        }
                    }
                });
                dialogInterface.dismiss();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }
}
