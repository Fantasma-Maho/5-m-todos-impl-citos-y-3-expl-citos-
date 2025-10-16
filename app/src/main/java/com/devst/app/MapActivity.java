package com.devst.app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    private TextView tvCoordenadas;
    private ImageView imgMarcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        tvCoordenadas = findViewById(R.id.tvCoordenadas);
        imgMarcador = findViewById(R.id.imgMarcador);

        // Recibir las coordenadas enviadas
        double lat = getIntent().getDoubleExtra("lat", 0.0);
        double lng = getIntent().getDoubleExtra("lng", 0.0);

        // Mostrar en el TextView
        tvCoordenadas.setText("Marcador en:\nLat: " + lat + "\nLng: " + lng);
    }
}
