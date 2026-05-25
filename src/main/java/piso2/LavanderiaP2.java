package piso2;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class LavanderiaP2 {

    private static final float Y = Constantes.ALTURA_PISO_2;

    private static float convertirXGeoAOpenGL(float xGeo) {
        float x = xGeo - Constantes.CENTRO_GEOGEBRA_X;
        x = -x;
        return x * Constantes.ESCALA_CASA;
    }

    private static float convertirZGeoAOpenGL(float zGeo) {
        float z = zGeo - Constantes.CENTRO_GEOGEBRA_Z;
        return z * Constantes.ESCALA_CASA;
    }

    private static float escalar(float valor) {
        return valor * Constantes.ESCALA_CASA;
    }

    public static void dibujar() {
        /*
         * CAMBIA SOLO EL ÚLTIMO NÚMERO PARA GIRAR:
         * 0f = normal
         * 90f = gira a un lado
         * -90f = gira al otro lado
         * 180f = volteado
         */

        dibujarBurroPlanchar(5.4f, 19.2f, 5.8f, 20.2f, 0f);

        dibujarLavadoraAbierta(3.9f, 19.7f, 4.5f, 20.2f, 270f);

        dibujarLavaboRopa(3.9f, 18.8f, 4.5f, 19.7f, 270f);

        dibujarSecadora(3.9f, 18.2f, 4.5f, 18.8f, 270f);
    }

    private static void dibujarCuboPorGeo(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float yBase, float altura,
            float r, float g, float b) {

        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo;
        float largoGeo = zMaxGeo - zMinGeo;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        Cubo.dibujar(
                x,
                yBase + altura / 2.0f,
                z,
                ancho,
                altura,
                largo,
                r, g, b);
    }

    // ==========================================================
    // 1. BURRO / MESA DE PLANCHAR
    // ==========================================================
    private static void dibujarBurroPlanchar(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float rotacionY) {

        float centroX = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZ = (zMinGeo + zMaxGeo) / 2.0f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float ancho = escalar(xMaxGeo - xMinGeo);
        float largo = escalar(zMaxGeo - zMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Tabla superior
        Cubo.dibujar(
                0f, 0.82f, 0f,
                ancho, 0.07f, largo,
                0.82f, 0.84f, 0.86f);

        // Funda azul
        Cubo.dibujar(
                0f, 0.87f, 0f,
                ancho * 0.92f, 0.035f, largo * 0.92f,
                0.55f, 0.75f, 0.90f);

        // Patas
        Cubo.dibujar(
                -ancho * 0.18f, 0.40f, -largo * 0.18f,
                0.05f, 0.78f, 0.05f,
                0.25f, 0.25f, 0.25f);

        Cubo.dibujar(
                ancho * 0.18f, 0.40f, largo * 0.18f,
                0.05f, 0.78f, 0.05f,
                0.25f, 0.25f, 0.25f);

        Cubo.dibujar(
                -ancho * 0.18f, 0.40f, largo * 0.18f,
                0.05f, 0.78f, 0.05f,
                0.25f, 0.25f, 0.25f);

        Cubo.dibujar(
                ancho * 0.18f, 0.40f, -largo * 0.18f,
                0.05f, 0.78f, 0.05f,
                0.25f, 0.25f, 0.25f);

        // Ropa doblada
        Cubo.dibujar(
                0f, 0.94f, -largo * 0.22f,
                ancho * 0.55f, 0.08f, largo * 0.18f,
                0.95f, 0.95f, 0.90f);

        Cubo.dibujar(
                0.02f, 1.03f, -largo * 0.22f,
                ancho * 0.45f, 0.07f, largo * 0.15f,
                0.75f, 0.82f, 0.95f);

        // Plancha
        Cubo.dibujar(
                0f, 0.96f, largo * 0.22f,
                ancho * 0.48f, 0.08f, largo * 0.16f,
                0.15f, 0.15f, 0.17f);

        Cubo.dibujar(
                0f, 1.05f, largo * 0.22f,
                ancho * 0.25f, 0.08f, largo * 0.09f,
                0.05f, 0.05f, 0.05f);

        glPopMatrix();
    }

    // ==========================================================
    // 2. LAVADORA ABIERTA
    // ==========================================================
    private static void dibujarLavadoraAbierta(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float rotacionY) {

        float centroX = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZ = (zMinGeo + zMaxGeo) / 2.0f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float ancho = escalar(xMaxGeo - xMinGeo);
        float largo = escalar(zMaxGeo - zMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Cuerpo principal
        Cubo.dibujar(
                0f, 0.48f, 0f,
                ancho, 0.96f, largo,
                0.88f, 0.88f, 0.86f);

        // Panel superior
        Cubo.dibujar(
                0f, 1.01f, -largo * 0.36f,
                ancho * 0.95f, 0.12f, largo * 0.24f,
                0.78f, 0.78f, 0.76f);

        // Botones
        Cubo.dibujar(
                -ancho * 0.28f, 1.09f, -largo * 0.40f,
                0.06f, 0.04f, 0.04f,
                0.1f, 0.1f, 0.1f);

        Cubo.dibujar(
                -ancho * 0.12f, 1.09f, -largo * 0.40f,
                0.06f, 0.04f, 0.04f,
                0.1f, 0.3f, 0.9f);

        Cubo.dibujar(
                ancho * 0.06f, 1.09f, -largo * 0.40f,
                0.06f, 0.04f, 0.04f,
                0.9f, 0.1f, 0.1f);

        Cubo.dibujar(
                ancho * 0.25f, 1.09f, -largo * 0.40f,
                0.10f, 0.04f, 0.04f,
                0.15f, 0.15f, 0.15f);

        // Interior abierto
        Cubo.dibujar(
                0f, 0.47f, largo / 2f + 0.01f,
                ancho * 0.62f, 0.55f, 0.035f,
                0.04f, 0.04f, 0.045f);

        // Tambor / motor visible
        Cubo.dibujar(
                0f, 0.47f, largo / 2f + 0.04f,
                ancho * 0.38f, 0.32f, 0.035f,
                0.45f, 0.45f, 0.48f);

        Cubo.dibujar(
                0f, 0.47f, largo / 2f + 0.07f,
                ancho * 0.25f, 0.20f, 0.035f,
                0.18f, 0.18f, 0.20f);

        // Detalles internos
        Cubo.dibujar(
                -ancho * 0.10f, 0.47f, largo / 2f + 0.10f,
                0.04f, 0.22f, 0.03f,
                0.65f, 0.65f, 0.68f);

        Cubo.dibujar(
                ancho * 0.10f, 0.47f, largo / 2f + 0.10f,
                0.04f, 0.22f, 0.03f,
                0.65f, 0.65f, 0.68f);

        Cubo.dibujar(
                0f, 0.47f, largo / 2f + 0.11f,
                0.26f, 0.04f, 0.03f,
                0.65f, 0.65f, 0.68f);

        // Puerta abierta
        Cubo.dibujar(
                ancho * 0.50f, 0.47f, largo / 2f + 0.10f,
                ancho * 0.36f, 0.08f, 0.035f,
                0.80f, 0.84f, 0.86f);

        Cubo.dibujar(
                ancho * 0.50f, 0.47f, largo / 2f + 0.13f,
                ancho * 0.22f, 0.04f, 0.035f,
                0.25f, 0.55f, 0.70f);

        glPopMatrix();
    }

    // ==========================================================
    // 3. LAVABO PARA ROPA
    // ==========================================================
    private static void dibujarLavaboRopa(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float rotacionY) {

        float centroX = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZ = (zMinGeo + zMaxGeo) / 2.0f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float ancho = escalar(xMaxGeo - xMinGeo);
        float largo = escalar(zMaxGeo - zMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Mueble base
        Cubo.dibujar(
                0f, 0.42f, 0f,
                ancho, 0.84f, largo,
                0.72f, 0.72f, 0.68f);

        // Cubierta
        Cubo.dibujar(
                0f, 0.88f, 0f,
                ancho * 1.02f, 0.08f, largo * 1.02f,
                0.55f, 0.55f, 0.52f);

        // Lavabo
        Cubo.dibujar(
                0f, 0.92f, 0f,
                ancho * 0.65f, 0.09f, largo * 0.55f,
                0.82f, 0.82f, 0.80f);

        Cubo.dibujar(
                0f, 0.96f, 0f,
                ancho * 0.45f, 0.08f, largo * 0.36f,
                0.35f, 0.40f, 0.42f);

        // Agua
        Cubo.dibujar(
                0f, 1.01f, 0f,
                ancho * 0.36f, 0.025f, largo * 0.28f,
                0.25f, 0.55f, 0.75f);

        // Grifo
        Cubo.dibujar(
                0f, 1.15f, -largo * 0.28f,
                0.05f, 0.35f, 0.05f,
                0.65f, 0.65f, 0.65f);

        Cubo.dibujar(
                0f, 1.30f, -largo * 0.12f,
                0.05f, 0.05f, largo * 0.30f,
                0.65f, 0.65f, 0.65f);

        // Repisa
        Cubo.dibujar(
                0f, 1.65f, -largo * 0.42f,
                ancho * 0.95f, 0.07f, 0.16f,
                0.42f, 0.28f, 0.16f);

        // Productos
        Cubo.dibujar(
                -ancho * 0.30f, 1.78f, -largo * 0.42f,
                0.09f, 0.22f, 0.09f,
                0.15f, 0.50f, 0.90f);

        Cubo.dibujar(
                -ancho * 0.10f, 1.77f, -largo * 0.42f,
                0.09f, 0.20f, 0.09f,
                0.90f, 0.90f, 0.20f);

        Cubo.dibujar(
                ancho * 0.12f, 1.80f, -largo * 0.42f,
                0.10f, 0.26f, 0.10f,
                0.10f, 0.65f, 0.25f);

        Cubo.dibujar(
                ancho * 0.32f, 1.76f, -largo * 0.42f,
                0.08f, 0.18f, 0.08f,
                0.05f, 0.05f, 0.05f);

        // Trapo
        Cubo.dibujar(
                -ancho * 0.32f, 0.65f, largo / 2f + 0.02f,
                0.18f, 0.38f, 0.03f,
                0.85f, 0.85f, 0.80f);

        glPopMatrix();
    }

    // ==========================================================
    // 4. SECADORA
    // ==========================================================
    private static void dibujarSecadora(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float rotacionY) {

        float centroX = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZ = (zMinGeo + zMaxGeo) / 2.0f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float ancho = escalar(xMaxGeo - xMinGeo);
        float largo = escalar(zMaxGeo - zMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Cuerpo principal
        Cubo.dibujar(
                0f, 0.48f, 0f,
                ancho, 0.96f, largo,
                0.86f, 0.86f, 0.84f);

        // Panel superior
        Cubo.dibujar(
                0f, 1.02f, -largo * 0.36f,
                ancho * 0.95f, 0.12f, largo * 0.22f,
                0.76f, 0.76f, 0.74f);

        // Botones
        Cubo.dibujar(
                -ancho * 0.25f, 1.10f, -largo * 0.40f,
                0.07f, 0.04f, 0.04f,
                0.10f, 0.10f, 0.10f);

        Cubo.dibujar(
                -ancho * 0.08f, 1.10f, -largo * 0.40f,
                0.07f, 0.04f, 0.04f,
                0.10f, 0.35f, 0.90f);

        Cubo.dibujar(
                ancho * 0.12f, 1.10f, -largo * 0.40f,
                0.07f, 0.04f, 0.04f,
                0.90f, 0.15f, 0.10f);

        // Puerta frontal
        Cubo.dibujar(
                0f, 0.47f, largo / 2f + 0.015f,
                ancho * 0.65f, 0.55f, 0.035f,
                0.18f, 0.18f, 0.18f);

        // Cristal
        Cubo.dibujar(
                0f, 0.47f, largo / 2f + 0.045f,
                ancho * 0.42f, 0.35f, 0.035f,
                0.28f, 0.55f, 0.68f);

        // Ropa dentro
        Cubo.dibujar(
                -ancho * 0.08f, 0.43f, largo / 2f + 0.075f,
                0.12f, 0.10f, 0.025f,
                0.95f, 0.95f, 0.90f);

        Cubo.dibujar(
                ancho * 0.08f, 0.50f, largo / 2f + 0.075f,
                0.12f, 0.10f, 0.025f,
                0.40f, 0.55f, 0.90f);

        glPopMatrix();
    }
}