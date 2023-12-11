package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class signupActivity extends AppCompatActivity {

    TextView sign_in_reg;
    Button reg;
    EditText firstname_reg,lastname_reg,username_reg,phone_reg,password_reg,confirm_pass_reg;
    SqliteHelper Db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        sign_in_reg = (TextView) findViewById(R.id.sign_in_reg);
        reg = (Button) findViewById(R.id.register);
        firstname_reg =(EditText) findViewById(R.id.first_name);
        lastname_reg = (EditText) findViewById(R.id.last_name);
        username_reg = (EditText) findViewById(R.id.email_reg);
        phone_reg = (EditText) findViewById(R.id.phone_num);
        password_reg = (EditText) findViewById(R.id.password_reg);
        confirm_pass_reg = (EditText) findViewById(R.id.confirm_password_reg);
        Db = new SqliteHelper(this);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = firstname_reg.getText().toString();
                String last_name = lastname_reg.getText().toString();
                String username2 = username_reg.getText().toString();
                String phone = phone_reg.getText().toString();
                String password2 = password_reg.getText().toString();
                String confirm_pass = confirm_pass_reg.getText().toString();


                if(first_name.equals("") || last_name.equals("") ||  username2.equals("") || phone.equals("")  ||password2.equals("") || confirm_pass.equals("")  )
                    Toast.makeText(signupActivity.this, "please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (password2.equals(confirm_pass)){
                        Boolean checkuser = Db.checkusername(username2);
                        if (checkuser == false){
                            Boolean insert = Db.addUser(username2, first_name,last_name,phone,password2);
                            if (insert == true) {
                                Toast.makeText(signupActivity.this, "Registered Successfuly", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            }
                            else {
                                Toast.makeText(signupActivity.this, " Registration failed ", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(signupActivity.this, " user already exists! please sign in ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(signupActivity.this, " password not matching ", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        sign_in_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), loginActivity.class);
                startActivity(intent);
                //openMainActivity();
            }
        });







    }
}