package com.example.app_amst_apisuperheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.toolbox.Volley;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String nombreHeroe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void buscarHeroe(View v){
        EditText edHeroe = (EditText) findViewById(R.id.edHeroe);
        nombreHeroe = edHeroe.getText().toString();
        if(nombreHeroe.length()<3){
            Toast.makeText(this, "No ofrece informacion suficiente", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent lista_heroe = new Intent(getBaseContext(), lista_heroe.class);
            lista_heroe.putExtra("heroe", nombreHeroe);
            startActivity(lista_heroe);
        }
    }
    ArrayList<String> nombreH = new ArrayList<String>();

}