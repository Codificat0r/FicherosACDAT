package com.example.ficheros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnEscribirInterna;
    private Button btnEscribirExterna;
    private Button btnLeer;
    private Button btnCodificacion;
    private Button btnExplorar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEscribirInterna = (Button)findViewById(R.id.btnEscribirInterna);
        btnEscribirInterna.setOnClickListener(this);
        btnEscribirExterna = (Button)findViewById(R.id.btnEscribirExterna);
        btnEscribirExterna.setOnClickListener(this);
        btnLeer = (Button)findViewById(R.id.btnLeer);
        btnLeer.setOnClickListener(this);
        btnCodificacion = (Button)findViewById(R.id.btnCodificacion);
        btnCodificacion.setOnClickListener(this);
        btnExplorar = (Button)findViewById(R.id.btnExplorar);
        btnExplorar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.btnEscribirInterna:
                i = new Intent(MainActivity.this, EscribirInternaActivity.class);
                startActivity(i);
                break;
            case R.id.btnEscribirExterna:
                i = new Intent(MainActivity.this, EscribirExternaActivity.class);
                startActivity(i);
                break;
            case R.id.btnLeer:
                i = new Intent(MainActivity.this, LeerFicherosActivity.class);
                startActivity(i);
                break;
            case R.id.btnCodificacion:
                i = new Intent(MainActivity.this, CodificacionActivity.class);
                startActivity(i);
                break;
            case R.id.btnExplorar:
                i = new Intent(MainActivity.this, ExploracionActivity.class);
                startActivity(i);
                break;
        }
    }
}
