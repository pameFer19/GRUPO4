# Documentación de Funcionalidad de Cámara - App Productos

## 1. Cambios en Configuración
- **AndroidManifest.xml**: Se añadieron permisos de `CAMERA`, `READ_EXTERNAL_STORAGE` y `WRITE_EXTERNAL_STORAGE`. Se configuró el `FileProvider` para evitar errores de exposición de URI (`FileUriExposedException`).
- **file_paths.xml**: Se habilitó el acceso a `external-files-path` para persistir las imágenes en la carpeta física del dispositivo de forma segura.

## 2. Componentes y Lógica de Imagen
- **CameraUtils.kt**: Centraliza la creación de archivos temporales y la simulación de subida a la nube.
- **Visualización Local**: Se ajustó el `ProductViewModel` para que, tras la captura, se utilice la ruta del archivo. Esto garantiza que la imagen sea visible en la App inmediatamente mediante Coil.

## 3. Integración MVVM
- **ProductViewModel**: 
    - `imagenUriState`: Almacena el path o URL actual de la imagen capturada.
    - `uploadImage()`: Ejecuta la lógica de subida y actualiza el estado para que la View (`InsertProductScreen`) refresque la interfaz.
- **Room Persistence**: El campo `imagenUri` en la entidad `Producto` guarda el String resultante, permitiendo que la imagen persista localmente.

---

## CUMPLIMIENTO DE REQUERIMIENTOS

### 1. Crear permisos a cámara y almacenamiento interno del dispositivo
Se configuraron los permisos necesarios en el `AndroidManifest.xml` y se implementó la solicitud de permisos en tiempo de ejecución en la vista usando `ActivityResultContracts.RequestPermission()`.

### 2. Crear funcionalidad de acceso a cámara
Se utilizó `rememberLauncherForActivityResult` con el contrato `TakePicture()` en `InsertProductScreen.kt` para disparar el intent de la cámara del sistema.

### 3. Crear función de toma y guardado de fotos
Implementado en `CameraUtils.createTempImageFile()`, que genera un archivo físico en el almacenamiento privado de la app, asegurando que cada foto tenga un nombre único basado en un timestamp.

### 4. Crear una función para cargar la imagen automáticamente a cloud
Implementado en `CameraUtils.uploadImageSimulated()`. El flujo es automático: al cerrar la cámara con éxito, el ViewModel dispara la subida y obtiene la URL para la base de datos.

---

## 🛠 MANUAL DE INTEGRACIÓN (Para DynamoDB / Cloud)
Hola compañero, para integrar tu lógica de DynamoDB y la subida real a la nube, sigue estos pasos:

1. **Punto de Inyección (Subida de Archivos)**:
   Toda tu lógica de subida debe ir en el archivo `utils/CameraUtils.kt`, específicamente en la función:
   ```kotlin
   suspend fun uploadImageSimulated(file: File): String {
       // AQUÍ: Reemplaza mi simulación por tu llamada a S3, Firebase o tu API.
       // Debes retornar la URL pública que te devuelva tu servicio.
   }
   ```

2. **Guardado en DynamoDB**:
   Cuando el usuario presiona "Guardar", el `ProductViewModel` ya tiene la URL en la variable `imagenUriState`.
   Si vas a usar un Repositorio Remoto, en el método `insert` del `ProductViewModel`, añade tu llamada a DynamoDB. El objeto `Producto` ya contiene el campo `.imagenUri` listo.

3. **Sincronización**:
   Recuerda que el campo `imagenUri` es un `String`. Si la subida falla, el código actual está preparado para guardar la ruta local como "backup".
