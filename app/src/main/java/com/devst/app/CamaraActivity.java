package com.devst.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CamaraActivity extends AppCompatActivity {

    private ImageView imgPreview;
    private Uri uriFoto;

    private final ActivityResultLauncher<String> permisoCamaraLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
                if (granted) {
                    Toast.makeText(this, "Permiso concedido. Ahora puedes usar la cámara.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permiso de cámara denegado.", Toast.LENGTH_SHORT).show();
                }
            });

    private final ActivityResultLauncher<Uri> tomarFotoLauncher =
            registerForActivityResult(new ActivityResultContracts.TakePicture(), ok -> {
                if (ok && uriFoto != null) {
                    imgPreview.setImageURI(uriFoto);
                    Toast.makeText(this, "Foto tomada correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No se tomó ninguna foto", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        Button btnCamaraTrasera = findViewById(R.id.btnCamaraTrasera);
        Button btnCamaraDelantera = findViewById(R.id.btnCamaraDelantera);
        imgPreview = new ImageView(this);
        imgPreview.setAdjustViewBounds(true);
        imgPreview.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Agregamos vista de imagen en el contenedor raíz
        addContentView(imgPreview, new android.widget.LinearLayout.LayoutParams(
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                android.widget.LinearLayout.LayoutParams.MATCH_PARENT
        ));

        // Solicitar permiso si no está concedido
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            permisoCamaraLauncher.launch(Manifest.permission.CAMERA);
        }

        btnCamaraTrasera.setOnClickListener(v -> abrirCamara(false));
        btnCamaraDelantera.setOnClickListener(v -> abrirCamara(true));
    }

    private void abrirCamara(boolean esDelantera) {
        try {
            File archivo = crearArchivoImagen();
            uriFoto = FileProvider.getUriForFile(
                    this,
                    getPackageName() + ".fileprovider",
                    archivo
            );

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto);

            // Intentos para cámara frontal (pueden ser ignorados por el fabricante)
            intent.putExtra("android.intent.extras.CAMERA_FACING", esDelantera ? 1 : 0);
            intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", esDelantera);
            intent.putExtra("android.intent.extras.LENS_FACING_FRONT", esDelantera ? 1 : 0);

            // 🚨 Verificar que exista una app que maneje el intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                tomarFotoLauncher.launch(uriFoto);
            } else {
                Toast.makeText(this, "No hay aplicación de cámara disponible", Toast.LENGTH_LONG).show();
            }

        } catch (IOException e) {
            Toast.makeText(this, "Error al crear archivo de imagen", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (Exception e) {
            Toast.makeText(this, "Error al intentar abrir la cámara", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private File crearArchivoImagen() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreArchivo = "IMG_" + timeStamp + "_";
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!dir.exists()) dir.mkdirs();
        return File.createTempFile(nombreArchivo, ".jpg", dir);
    }
}
