package com.example.ficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class CodificacionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txvFicheroLeer;
    private TextView txvFicheroGuardar;
    private EditText edtFicheroLeer;
    private EditText edtFicheroGuardar;
    private EditText edtTexto;
    private Button btnFicheroLeer;
    private Button btnFicheroGuardar;
    private RadioButton rbtUtf8;
    private RadioButton rbtUtf16;
    private RadioButton rbtIso;
    private Memoria memoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codificacion);

        iniciar();
    }

    private void iniciar() {
        txvFicheroLeer = (TextView) findViewById(R.id.txvFicheroLeer);
        txvFicheroGuardar = (TextView) findViewById(R.id.txvFicheroGuardar);
        edtFicheroLeer = (EditText) findViewById(R.id.edtFicheroLeer);
        edtFicheroGuardar = (EditText) findViewById(R.id.edtFicheroGuardar);
        edtTexto = (EditText) findViewById(R.id.edtTexto);
        btnFicheroLeer = (Button) findViewById(R.id.btnFicheroLeer);
        btnFicheroGuardar = (Button) findViewById(R.id.btnFicheroGuardar);

        btnFicheroLeer.setOnClickListener(this);
        btnFicheroGuardar.setOnClickListener(this);

        rbtUtf8 = (RadioButton) findViewById(R.id.rbtUtf8);
        rbtUtf16 = (RadioButton) findViewById(R.id.rbtUtf16);
        rbtIso = (RadioButton) findViewById(R.id.rbtIso);

        memoria = new Memoria(getApplicationContext());
    }


    @Override
    public void onClick(View v) {
        String rutaLectura = edtFicheroLeer.getText().toString();
        String rutaEscritura = edtFicheroGuardar.getText().toString();
        String texto = edtTexto.getText().toString();
        String codificacion;
        Resultado resultado;

        if (rbtUtf8.isChecked()) {
            codificacion = "UTF-8";
        } else if (rbtUtf16.isChecked()) {
            codificacion = "UTF-16";
        } else {
            codificacion = "ISO-8859-15";
        }

        if (v == btnFicheroLeer) {
            resultado = memoria.leerExterna(rutaLectura, codificacion);
            if (resultado.getCodigo()) {
                edtTexto.setText(resultado.getContenido());
            } else {
                Toast.makeText(this, "Error al leer el fichero " + rutaLectura, Toast.LENGTH_LONG).show();
            }
        }

        if (v == btnFicheroGuardar) {
            if (rutaEscritura.isEmpty()) {
                Toast.makeText(this, "Debe introducir el nombre del fichero", Toast.LENGTH_LONG).show();
            } else {
                if (memoria.disponibleEscritura()) {
                    if (memoria.escribirExterna(rutaEscritura, texto, false, codificacion)) {
                        Toast.makeText(this, "Fichero escrito correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Error al escribir en el fichero " + rutaEscritura, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "La memoria externa no está disponible", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
