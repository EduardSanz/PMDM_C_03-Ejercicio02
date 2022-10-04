package com.cieep.a03_ejercicio02.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cieep.a03_ejercicio02.R;
import com.cieep.a03_ejercicio02.modelos.BiciModel;

public class CrearBiciActivity extends AppCompatActivity {

    private EditText txtMarca;
    private EditText txtPulgadas;
    private Button btnCancelar;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_bici);

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
                if ( txtMarca.getText().toString().trim().isEmpty() ||
                                txtPulgadas.getText().toString().trim().isEmpty() ){
                    Toast.makeText(CrearBiciActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }
                else{
                    BiciModel biciModel = new BiciModel(
                                                            txtMarca.getText().toString().trim(),
                                                            Integer.parseInt(txtPulgadas.getText().toString().trim())
                                                        );
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("BICI", biciModel);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void inicializaVistas() {
        txtMarca = findViewById(R.id.txtMarcaCrearBici);
        txtPulgadas = findViewById(R.id.txtPulgadasCrearBici);
        btnCancelar = findViewById(R.id.btnCancelarCrearBici);
        btnCrear = findViewById(R.id.btnCrearCrearBici);
    }
}