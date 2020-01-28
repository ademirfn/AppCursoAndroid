package com.example.adiligencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void consultaCep(View view){

        Intent intent = new Intent(this, consultaCep.class);
        startActivity(intent);
    }

    public void proximaTela(View view){

        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}
