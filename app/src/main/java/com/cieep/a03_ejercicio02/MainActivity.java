package com.cieep.a03_ejercicio02;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cieep.a03_ejercicio02.actividades.CrearBiciActivity;
import com.cieep.a03_ejercicio02.actividades.CrearCocheActivity;
import com.cieep.a03_ejercicio02.actividades.CrearMotoActivity;
import com.cieep.a03_ejercicio02.modelos.BiciModel;
import com.cieep.a03_ejercicio02.modelos.CocheModel;
import com.cieep.a03_ejercicio02.modelos.MotoModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Atributos para la vista
    private TextView lblCantidadCoches;
    private TextView lblCantidadMotos;
    private TextView lblCantidadBicis;
    private Button btnCrearCoche;
    private Button btnCrearMoto;
    private Button btnCrearBici;

    // Atributos para la lógica
    private ArrayList<CocheModel> listaCoches;
    private ArrayList<MotoModel> listaMotos;
    private ArrayList<BiciModel> listaBicis;

    // Atributos para los launchers
    private ActivityResultLauncher<Intent> launcherCoches;
    private ActivityResultLauncher<Intent> launcherMotos;
    private ActivityResultLauncher<Intent> launcherBicis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaVariables();

        btnCrearCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCoches.launch(new Intent(MainActivity.this, CrearCocheActivity.class));
            }
        });
        
        btnCrearMoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherMotos.launch(new Intent(MainActivity.this, CrearMotoActivity.class));
            }
        });

        btnCrearBici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherBicis.launch(new Intent(MainActivity.this, CrearBiciActivity.class));
            }
        });

        launcherCoches = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                CocheModel cocheModel = (CocheModel) result.getData().getExtras().getSerializable("COCHE");
                                if (cocheModel != null) {
                                    listaCoches.add(cocheModel);
                                    lblCantidadCoches.setText("Coche: "+listaCoches.size());
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "NO HAY COCHE", Toast.LENGTH_SHORT).show();
                                    Log.e("BOBO", "Revisa el identificador del Bundle");
                                }
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Activity Cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        launcherMotos = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                MotoModel motoModel = (MotoModel) result.getData().getExtras().getSerializable("MOTO");
                                if (motoModel != null) {
                                    listaMotos.add(motoModel);
                                    lblCantidadMotos.setText("Moto: "+listaMotos.size());
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "NO HAY MOTO", Toast.LENGTH_SHORT).show();
                                    Log.e("BOBO", "Revisa el identificador del Bundle");
                                }
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Activity Cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        launcherBicis = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                BiciModel biciModel = (BiciModel) result.getData().getExtras().getSerializable("BICI");
                                if (biciModel != null) {
                                    listaBicis.add(biciModel);
                                    lblCantidadBicis.setText("Bici: "+listaBicis.size());
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "NO HAY BICI", Toast.LENGTH_SHORT).show();
                                    Log.e("BOBO", "Revisa el identificador del Bundle");
                                }
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Activity Cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    private void inicializaVariables() {
        lblCantidadCoches = findViewById(R.id.lblCantidadCochesMain);
        lblCantidadMotos = findViewById(R.id.lblCantidadMotosMain);
        lblCantidadBicis = findViewById(R.id.lblCantidadBicisMain);
        btnCrearCoche = findViewById(R.id.btnCrearCocheMain);
        btnCrearMoto = findViewById(R.id.btnCrearMotoMain);
        btnCrearBici = findViewById(R.id.btnCrearBiciMain);

        listaCoches = new ArrayList<>();
        listaMotos = new ArrayList<>();
        listaBicis = new ArrayList<>();
    }
}