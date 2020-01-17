package com.example.adiligencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

    public void proximaTela(View view){

        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
    public void cadastrar(View view){

        Intent intent = new Intent(this, cadastrar.class);
        startActivity(intent);
    }
}
