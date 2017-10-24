package com.example.ficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EscribirExternaActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtOperando1, edtOperando2;
    TextView txvResultado, txvInfoFichero;
    Button btnCalcular;
    public final static String NOMBREFICHERO = "resultado.txt";
    Memoria miMemoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escribir_externa);

        edtOperando1 = (EditText)findViewById(R.id.edtOperando1);
        edtOperando2 = (EditText)findViewById(R.id.edtOperando2);
        txvResultado = (TextView)findViewById(R.id.txvResultado);
        txvInfoFichero = (TextView)findViewById(R.id.txvInfoFichero);
        btnCalcular = (Button)findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);

        miMemoria = new Memoria(getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        int r;
        String op1 = edtOperando1.getText().toString();
        String op2 = edtOperando2.getText().toString();
        String texto;

        if (view == btnCalcular) {
            try {
                r = Integer.parseInt(op1) + Integer.parseInt(op2);
                texto = Integer.toString(r);
                txvResultado.setText(texto);
                if (miMemoria.escribirExterna(NOMBREFICHERO, texto, false, "UTF-8")) {
                    txvInfoFichero.setText(miMemoria.mostrarPropiedadesExterna(NOMBREFICHERO));
                } else {
                    txvInfoFichero.setText("Error al escribir en el fichero " + NOMBREFICHERO);
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error en el formato de los números", Toast.LENGTH_LONG).show();
            }

        }
    }
}
