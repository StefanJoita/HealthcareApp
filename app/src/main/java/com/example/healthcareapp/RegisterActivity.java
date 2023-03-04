package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername,edEmail,edConfirmPassword,edPassword;
    TextView tvLogin;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername=findViewById(R.id.editTextUsername);
        edEmail=findViewById(R.id.editTextEmail);
        edConfirmPassword=findViewById(R.id.editTextConfirmPassword);
        edPassword=findViewById(R.id.editTextPassword);
        tvLogin=findViewById(R.id.textViewExistingUser);
        btnRegister=findViewById(R.id.buttonRegister);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=edEmail.getText().toString();
                String username=edUsername.getText().toString();
                String password=edPassword.getText().toString();
                String confirmPassword=edConfirmPassword.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0 || password.length()==0 || confirmPassword.length()==0 || email.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Fill the fields!",Toast.LENGTH_SHORT).show();
                    Log.e(null,"Register Fileds Empty!");

                }
                else {
                    if(password.compareTo(confirmPassword)==0)
                    {   if(isEmail(email)) {
                            if (!isPasswordStrong(password)) {
                                Toast.makeText(getApplicationContext(), "You need at leat 8 char, including a lower, an upper and a digit!", Toast.LENGTH_SHORT).show();
                            } else {
                                db.register(username,email,password);
                                Toast.makeText(getApplicationContext(), "Account created succesful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Enter a valid email", Toast.LENGTH_SHORT);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Passwords dont match!",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }
    public static boolean isPasswordStrong(String password) {
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;

        // Check if password is at least 8 characters long
        if (password.length() < 8) {
            return false;
        }

        // Check if password contains at least one uppercase letter, one lowercase letter, and one digit
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        // Return true if password meets all criteria, false otherwise
        return (hasUppercase && hasLowercase && hasDigit);
     }
    public static boolean isEmail(String email) {
        // Email format regex pattern
        String pattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

        // Check if email matches pattern
        return email.matches(pattern);
    }
}

