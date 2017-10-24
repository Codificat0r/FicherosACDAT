package com.example.ficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LeerFicherosActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtLeerRaw;
    private EditText edtLeerAsset;
    private EditText edtLeerInterna;
    private EditText edtLeerExterna;
    private TextView txvCalculo;
    private Button btnCalcular;
    //El openRawResource no tiene que llevar la extensión.
    public static final String NUMERO = "numero";
    public static final String VALOR = "valor.txt";
    public static final String DATO = "datos.txt";
    public static final String DATO_SD = "dato_sd.txt";
    public static final String OPERACIONES = "operaciones.txt";
    public static final String CODIFICACION = "UTF-8";
    Memoria memoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_ficheros);

        iniciar();
    }

    private void iniciar() {
        Resultado resultado;

        edtLeerRaw = (EditText) findViewById(R.id.edtLeerRaw);
        edtLeerAsset = (EditText) findViewById(R.id.edtLeerAsset);
        edtLeerInterna = (EditText) findViewById(R.id.edtLeerInterna);
        edtLeerExterna = (EditText) findViewById(R.id.edtLeerExterna);
        txvCalculo = (TextView) findViewById(R.id.txvCalculo);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);
        memoria = new Memoria(getApplicationContext());

        resultado = memoria.leerRaw(NUMERO);
        if (resultado.getCodigo()) {
            edtLeerRaw.setText(resultado.getContenido());
        } else {
            edtLeerRaw.setText("0");
            Toast.makeText(this, "Error al leer " + NUMERO + " " + resultado.getMensaje(), Toast.LENGTH_SHORT).show();
        }

        resultado = memoria.leerAsset(VALOR);
        if (resultado.getCodigo()) {
            edtLeerAsset.setText(resultado.getContenido());
        } else {
            edtLeerAsset.setText("0");
            Toast.makeText(this, "Error al leer " + VALOR + " " + resultado.getMensaje(), Toast.LENGTH_SHORT).show();
        }

        if (memoria.escribirInterna(DATO, "7", false, CODIFICACION)) {
            resultado = memoria.leerInterna(DATO, CODIFICACION);
            if (resultado.getCodigo()) {
                edtLeerInterna.setText(resultado.getContenido());
            } else {
                edtLeerInterna.setText("0");
                Toast.makeText(this, "Error al leer " + DATO + " " + resultado.getMensaje(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error al escribir " + DATO, Toast.LENGTH_SHORT).show();
        }

        if (memoria.disponibleEscritura()) {
            if (memoria.escribirExterna(DATO_SD, "5", false, CODIFICACION)) {
                resultado = memoria.leerExterna(DATO_SD, CODIFICACION);
                if (resultado.getCodigo()) {
                    edtLeerExterna.setText(resultado.getContenido());
                } else {
                    edtLeerExterna.setText("0");
                    Toast.makeText(this, "Error al leer " + DATO_SD + " " + resultado.getMensaje(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Error al escribir " + DATO_SD, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "La memoria externa no está disponible", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btnCalcular) {
            String operando1 = edtLeerRaw.getText().toString();
            String operando2 = edtLeerAsset.getText().toString();
            String operando3 = edtLeerInterna.getText().toString();
            String operando4 = edtLeerExterna.getText().toString();

            String operacion;
            try {
                int resultado = Integer.parseInt(operando1) +
                        Integer.parseInt(operando2) +
                        Integer.parseInt(operando3) +
                        Integer.parseInt(operando4);

                operacion = Integer.parseInt(edtLeerRaw.getText().toString()) + "+" +
                        Integer.parseInt(edtLeerAsset.getText().toString()) + "+" +
                        Integer.parseInt(edtLeerInterna.getText().toString()) + "+" +
                        Integer.parseInt(edtLeerExterna.getText().toString()) + "=" + resultado;

                txvCalculo.setText(operacion);

                if (memoria.disponibleEscritura()) {
                    if (memoria.escribirExterna(OPERACIONES, operacion + "\n", true, CODIFICACION)) {
                        Toast.makeText(this, "Operación escrita correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Error al escribir en la memoria externa", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error al convertir las cifras", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
