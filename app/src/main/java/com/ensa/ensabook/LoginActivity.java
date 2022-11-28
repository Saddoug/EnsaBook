package com.ensa.ensabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView error;
    Button login;
    ImageView leftIcon ;
    private Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar=findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.editTextTextPersonName);
        password =findViewById(R.id.editTextTextPassword);
        error=findViewById(R.id.textView2);
        leftIcon= findViewById(R.id.left_icon);
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }

    public void affichProfile(View v){
        String u,p;
        u=username.getText().toString();
        p=password.getText().toString();
        if(u.isEmpty()| p.isEmpty()){
            Toast.makeText(this,"you have to fill blanks",Toast.LENGTH_LONG).show();
        }else{
            if (u.equals("admin")){
                if(p.equals("admin")){
                    Intent intent=new Intent(this,ProfilActivity.class);

                    startActivity(intent);

                }
            }else error.setText("username or password incorrect");
        }


    }
}