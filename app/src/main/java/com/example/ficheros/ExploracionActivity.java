package com.example.ficheros;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.regex.Pattern;

public class ExploracionActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int ABRIRFICHERO_REQUEST_CODE = 1;
    private Button botonAbrir;
    private TextView txtInfo;

    //region Sin material file picker, con app externa
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploracion);
        botonAbrir = (Button) findViewById(R.id.btnAbrir);
        txtInfo = (TextView) findViewById(R.id.txvInfo);
        botonAbrir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, ABRIRFICHERO_REQUEST_CODE);
        else
            //informar que no hay ninguna aplicación para manejar ficheros
            Toast.makeText(this, "No hay aplicación para manejar ficheros", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ABRIRFICHERO_REQUEST_CODE)
            if (resultCode == RESULT_OK) {
                // Mostramos en la etiqueta la ruta del archivo seleccionado
                String ruta = data.getData().getPath();
                txtInfo.setText(ruta);
            } else
                Toast.makeText(this, "Error: " + resultCode, Toast.LENGTH_SHORT).show();
    } */
    //endregion


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploracion);
        botonAbrir = (Button) findViewById(R.id.btnAbrir);
        txtInfo = (TextView) findViewById(R.id.txvInfo);
        botonAbrir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == botonAbrir) {
            openFilePicker();
        }
    }

    public void openFilePicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(1)
                .withFilter(Pattern.compile(".*\\.txt$")) // Filtering files and directories by file name using regexp
                .withFilterDirectories(true) // Set directories filterable (false by default)
                .withHiddenFiles(true) // Show hidden files and folders
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Memoria miMemoria = new Memoria(getApplicationContext());
        if (requestCode == 1 && resultCode == RESULT_OK) {
            txtInfo.setText(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
        }
    }
}
