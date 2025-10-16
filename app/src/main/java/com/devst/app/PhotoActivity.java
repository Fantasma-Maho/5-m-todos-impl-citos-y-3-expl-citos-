package com.devst.app;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ImageView imgPreview = findViewById(R.id.imgPreview);

        // Recibir la URI de la foto desde HomeActivity
        String uriString = getIntent().getStringExtra("photo_uri");

        if (uriString != null) {
            Uri uri = Uri.parse(uriString);
            imgPreview.setImageURI(uri);
        } else {
            Toast.makeText(this, "No se encontró la imagen", Toast.LENGTH_SHORT).show();
            finish(); // Cierra si no hay imagen
        }
    }
}
