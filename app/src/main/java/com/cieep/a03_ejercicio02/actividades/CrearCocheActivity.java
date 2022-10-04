package com.cieep.a03_ejercicio02.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cieep.a03_ejercicio02.R;
import com.cieep.a03_ejercicio02.modelos.CocheModel;

public class CrearCocheActivity extends AppCompatActivity {

    // Variables para la Vista
    private EditText txtMarca;
    private EditText txtModelo;
    private EditText txtColor;
    private Button btnCancelar;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_coche);

        inicializaVistas();

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marca = txtMarca.getText().toString().trim();
                String modelo = txtModelo.getText().toString().trim();
                String color = txtColor.getText().toString().trim();

                if (!marca.isEmpty() && !modelo.isEmpty() && !color.isEmpty()){
                    // CREO EL COCHE
                    CocheModel cocheModel = new CocheModel(marca, modelo, color);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("COCHE", cocheModel);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    Toast.makeText(CrearCocheActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializaVistas() {
        txtMarca = findViewById(R.id.txtMarcaCrearCoche);
        txtModelo = findViewById(R.id.txtModeloCrearCoche);
        txtColor = findViewById(R.id.txtColorCrearCoche);

        btnCancelar = findViewById(R.id.btnCancelarCrearCoche);
        btnCrear = findViewById(R.id.btnCrearCrearCoche);
    }
}