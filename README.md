#  Proyecto Android — Aplicación

## Resumen del proyecto
Este proyecto es una aplicación Android desarrollada en **Java**, cuyo objetivo principal es **demostrar el uso de Intents (implícitos y explícitos)** en distintas pantallas y funcionalidades del sistema.  
La app incluye pantallas de inicio, login, cámara, perfil, mapa, galería de fotos y navegación por pestañas, junto con *fragments* que representan diferentes secciones de contenido.

### Versión del entorno
- Lenguaje: Java  
- Versión mínima de Android: API 31
- Versión de compilación (compileSdk): 34.
- Versión del Android Gradle Plugin (AGP): 

---
	

### implícitos
Estos Intents permiten que la aplicación interactúe con otras apps o servicios del sistema operativo Android.

| Descripción | Acción (Intent) | Pasos de prueba |
| Abrir ubicación en Google Maps | `Intent(Intent.ACTION_VIEW, Uri.parse("geo:lat,lng?q=texto"))` | Pulsar “Ver ubicación” -> Se abre Google Maps mostrando la dirección o lugar indicado. |
| Ver una página web específica | `Intent(Intent.ACTION_VIEW, Uri.parse("https://..."))` | Pulsar “Abrir sitio web” -> Se abre el navegador en la página indicada. |
| Tomar fotografía con la cámara | `Intent(MediaStore.ACTION_IMAGE_CAPTURE)` | Pulsar “Tomar foto” -> Se abre la cámara -> Capturar imagen -> Se guarda en galería. |
| Abrir configuración del dispositivo (Wi-Fi o Bluetooth) | `Intent(Settings.ACTION_WIFI_SETTINGS)` | Pulsar “Abrir configutacion” -> Se abre la configuración del sistema. |
| Agregar evento al calendario | `Intent(Intent.ACTION_INSERT).setData(Events.CONTENT_URI)` | Pulsar “Agregar evento” -> Se abre la app de calendario con datos precargados. |
| Abrir cámara frontal directamente *(si es posible)* | `Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA_FRONT)` | Pulsar “Cámara frontal” -> Se abre la cámara frontal (si el dispositivo lo soporta). |

---

### Explícitos
Estos Intents se utilizan para abrir *Activities* específicas dentro de la aplicación, pasando información entre ellas cuando es necesario.

| Desde | Hacia | Código ejemplo | Descripción / Pasos de prueba |
| `MainActivity` | `MapActivity` | ```java startActivity(new Intent(MainActivity.this, MapActivity.class) .putExtra("lat", lat).putExtra("lng", lng));``` | Muestra un marcador en el mapa con coordenadas enviadas desde `MainActivity`. |
| `MainActivity` | `PhotoActivity` | ```java startActivity(new Intent(MainActivity.this, PhotoActivity.class) .putExtra("photoUri", photoUri.toString()));``` | Muestra la imagen capturada anteriormente. Requiere permisos de almacenamiento. |
| `Activity` | `FragmentActivity` (con ViewPager) | ```java startActivity(new Intent(Activity.this, FragmentActivity.class));``` | Permite cambiar entre vistas mediante pestañas o *swipe*. Ideal para apps con secciones o categorías. |

---

## Mapa de navegación
```
LoginActivity -> HomeActivity
HomeActivity -> (PerfilActivity / MapActivity / CamaraActivity / PhotoActivity)
HomeActivity -> TabsActivity
TabsActivity -> (CamaraFragment / InfoFragment / PerfilFragment)
```

---

## Instrucciones para compilar o ejecutar
Opción 1 — Usar el APK incluido
1. Dirígete a la carpeta:
   ```
   app/build/outputs/apk/debug/
   ```
2. Instala el archivo `app-debug.apk` en tu dispositivo Android.

Opción 2 — Compilar manualmente
1. Abre el proyecto en Android Studio.  
2. Sincroniza dependencias (`Gradle Sync`).  
3. Ejecuta el proyecto en un emulador o dispositivo físico.  

---

## Capturas de pantalla



## Lecciones y mejoras futuras
- Se reforzó la comprensión del uso de Intents implícitos y explícitos.  
 

  


Versión del documento: 1.1  
Autor: Matias Castro y Cristobal Silva.  
Ubicación del APK: `app/build/outputs/apk/debug/app-debug.apk`
