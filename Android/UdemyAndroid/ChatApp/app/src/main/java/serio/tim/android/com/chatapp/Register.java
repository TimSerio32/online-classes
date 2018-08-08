package serio.tim.android.com.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    EditText username, password;
    Button registerButton;
    String user, pass;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        registerButton = (Button)findViewById(R.id.registerButton);
        login = (TextView)findViewById(R.id.login);

        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                pass = password.getText().toString();

                if(user.equals("")) {
                    username.setError("Username can't be blak");
                } else if(pass.equals("")) {
                    password.setError("Password can't be blank");
                } else if(!user.matches("[A-Za-z0-9]+")) {
                    username.setError("Only letters and numbers allowed in username");
                } else if(user.length() < 5) {
                    username.setError("Username must be more than 4 characters");
                } else if(pass.length() < 5) {
                    password.setError("Password must be more than 4 character");
                } else {
                    final ProgressDialog pd = new ProgressDialog(Register.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://chatapp-fd6b5.firebaseio.com/users.json";
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Firebase reference = new Firebase("https://chatapp-fd6b5.firebaseio.com/users");
                            if (response.equals("null")) {
                                reference.child(user).child("password").setValue(pass);
                                Toast.makeText(Register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    if (!obj.has(user)) {
                                        reference.child(user).child("password").setValue(pass);
                                        Toast.makeText(Register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Register.this, "This username already exists.", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            pd.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("" + error);
                            pd.dismiss();
                        }
                    });
                    RequestQueue rQueue = Volley.newRequestQueue(Register.this);
                    rQueue.add(request);
                }
            }
        });
    }
}
