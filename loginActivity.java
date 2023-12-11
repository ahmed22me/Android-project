package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class loginActivity extends AppCompatActivity {

    Button signUp;
    Button login ;
    TextView skip;
    EditText username_login;
    EditText password_login;
    SqliteHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        skip =(TextView) findViewById(R.id.skip);

        signUp = (Button) findViewById(R.id.sign_up);
        login = (Button) findViewById(R.id.login);
        username_login = (EditText) findViewById(R.id.email);
        password_login = (EditText) findViewById(R.id.password_login);
        DB = new SqliteHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 = username_login.getText().toString();
                String password1 = password_login.getText().toString();

                if (username1.equals("") || password1.equals(""))
                    Toast.makeText(loginActivity.this, " please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuser_pass = DB.checkusernamepassword(username1, password1);
                    if (checkuser_pass == true){
                        Toast.makeText(loginActivity.this, " sign in successfull ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(loginActivity.this, " invalid username or password!! please tray to sign up ", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                //openMainActivity3();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signupActivity.class);
                startActivity(intent);

                //openMainActivity2();
            }
        });






    }
}