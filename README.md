# EDA II – Resumen del Bimestre

Programa de escritorio (JavaFX 21, Java 17, Maven, patrón MVC) – Escuela Politécnica Nacional.

## Cómo ejecutarlo
**NetBeans:** Archivo → Abrir proyecto → seleccionar esta carpeta → clic derecho → *Run* (usa el plugin `javafx-maven-plugin`).
**Terminal:** `mvn javafx:run`
**JAR ejecutable:** `mvn clean package` → `java -jar target/EDA-II-Resumen-Bimestre-1.0.jar`

## Personalización
- Logo: copiar el archivo a `src/main/resources/images/logo-epn.png` (si no existe se muestra «LOGO»).
- Facultad/Carrera: editar la etiqueta `FACULTAD / CARRERA` en `views/inicio.fxml`.

## MVC
- **Modelo:** paquetes `eficiencia`, `complejidad`, `clasificacion`, `grafos`, `util`.
- **Vista:** `resources/views/*.fxml`, `css/estilos.css` y `vista/GrafoCanvas.java`.
- **Controlador:** paquete `controller` (uno por ventana y uno por módulo).
