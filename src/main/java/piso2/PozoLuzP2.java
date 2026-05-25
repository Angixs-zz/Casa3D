package piso2;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class PozoLuzP2 {

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
         * POZO DE LUZ - PISO 2
         *
         * Área:
         * P4 = (0.2, 8.5)
         * Q4 = (0.2, 8.0)
         * R4 = (3.8, 8.5)
         * S4 = (3.8, 8.0)
         *
         * Aquí va una encimera larga con 3 plantas grandes encima.
         */

        dibujarEncimera(0.2f, 8.0f, 3.8f, 8.5f);

        // Tres plantas grandes sobre la encimera
        dibujarPlantaPalmeraGrande(0.75f, 8.25f, 0f);
        dibujarPlantaArbustoGrande(2.00f, 8.25f, 0f);
        dibujarPlantaHojasLargas(3.25f, 8.25f, 0f);
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
    // ENCIMERA LARGA
    // ==========================================================
    private static void dibujarEncimera(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroX = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZ = (zMinGeo + zMaxGeo) / 2.0f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float ancho = escalar(xMaxGeo - xMinGeo);
        float largo = escalar(zMaxGeo - zMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Base blanca tipo jardinera / encimera
        Cubo.dibujar(
                0f, 0.35f, 0f,
                ancho, 0.70f, largo,
                0.88f, 0.88f, 0.84f);

        // Cubierta superior más clara
        Cubo.dibujar(
                0f, 0.73f, 0f,
                ancho * 1.02f, 0.08f, largo * 1.05f,
                0.96f, 0.96f, 0.92f);

        // Franja inferior tipo madera / sombra
        Cubo.dibujar(
                0f, 0.08f, largo / 2f + 0.015f,
                ancho * 0.96f, 0.10f, 0.035f,
                0.42f, 0.28f, 0.16f);

        // Separaciones decorativas, como módulos de encimera
        Cubo.dibujar(
                -ancho * 0.18f, 0.38f, largo / 2f + 0.02f,
                0.035f, 0.48f, 0.035f,
                0.72f, 0.72f, 0.68f);

        Cubo.dibujar(
                ancho * 0.18f, 0.38f, largo / 2f + 0.02f,
                0.035f, 0.48f, 0.035f,
                0.72f, 0.72f, 0.68f);

        glPopMatrix();
    }

    // ==========================================================
    // PLANTA 1 - PALMERA GRANDE
    // ==========================================================
    private static void dibujarPlantaPalmeraGrande(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y + 0.75f, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Maceta blanca alta
        Cubo.dibujar(
                0f, 0.22f, 0f,
                escalar(0.38f), 0.44f, escalar(0.32f),
                0.92f, 0.92f, 0.88f);

        // Base inferior de la maceta
        Cubo.dibujar(
                0f, 0.04f, 0f,
                escalar(0.32f), 0.08f, escalar(0.26f),
                0.65f, 0.45f, 0.28f);

        // Tierra
        Cubo.dibujar(
                0f, 0.46f, 0f,
                escalar(0.34f), 0.05f, escalar(0.28f),
                0.12f, 0.08f, 0.04f);

        // Troncos tipo palmera
        Cubo.dibujar(
                -0.04f, 0.95f, 0f,
                0.06f, 0.95f, 0.06f,
                0.42f, 0.26f, 0.12f);

        Cubo.dibujar(
                0.05f, 0.90f, 0.02f,
                0.05f, 0.82f, 0.05f,
                0.48f, 0.30f, 0.14f);

        // Hojas grandes superiores
        dibujarHojaGrande(0f, 1.45f, 0f, 0f);
        dibujarHojaGrande(0f, 1.40f, 0f, 45f);
        dibujarHojaGrande(0f, 1.42f, 0f, 90f);
        dibujarHojaGrande(0f, 1.38f, 0f, 135f);
        dibujarHojaGrande(0f, 1.47f, 0f, 180f);
        dibujarHojaGrande(0f, 1.36f, 0f, 225f);
        dibujarHojaGrande(0f, 1.44f, 0f, 270f);
        dibujarHojaGrande(0f, 1.39f, 0f, 315f);

        glPopMatrix();
    }

    // ==========================================================
    // PLANTA 2 - ARBUSTO GRANDE REDONDO
    // ==========================================================
    private static void dibujarPlantaArbustoGrande(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y + 0.75f, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Maceta redonda simulada con bloques
        Cubo.dibujar(
                0f, 0.18f, 0f,
                escalar(0.36f), 0.36f, escalar(0.36f),
                0.96f, 0.96f, 0.92f);

        Cubo.dibujar(
                0f, 0.42f, 0f,
                escalar(0.42f), 0.08f, escalar(0.42f),
                0.88f, 0.88f, 0.84f);

        // Tierra
        Cubo.dibujar(
                0f, 0.48f, 0f,
                escalar(0.34f), 0.05f, escalar(0.34f),
                0.10f, 0.07f, 0.04f);

        // Tronco central
        Cubo.dibujar(
                0f, 0.75f, 0f,
                0.06f, 0.55f, 0.06f,
                0.34f, 0.20f, 0.10f);

        // Volumen de hojas con varios cubos verdes
        Cubo.dibujar(
                0f, 1.05f, 0f,
                escalar(0.48f), 0.42f, escalar(0.42f),
                0.08f, 0.35f, 0.12f);

        Cubo.dibujar(
                -0.12f, 1.18f, 0.03f,
                escalar(0.36f), 0.36f, escalar(0.32f),
                0.10f, 0.45f, 0.16f);

        Cubo.dibujar(
                0.14f, 1.16f, -0.05f,
                escalar(0.34f), 0.34f, escalar(0.34f),
                0.07f, 0.38f, 0.13f);

        Cubo.dibujar(
                0f, 1.35f, 0f,
                escalar(0.34f), 0.30f, escalar(0.30f),
                0.12f, 0.50f, 0.18f);

        // Hojitas salidas para que se vea más natural
        dibujarHojaPequena(-0.20f, 1.22f, 0.03f, 20f);
        dibujarHojaPequena(0.22f, 1.18f, -0.02f, -30f);
        dibujarHojaPequena(0.06f, 1.43f, 0.08f, 80f);
        dibujarHojaPequena(-0.05f, 1.38f, -0.12f, 140f);

        glPopMatrix();
    }

    // ==========================================================
    // PLANTA 3 - HOJAS LARGAS TIPO INTERIOR MODERNO
    // ==========================================================
    private static void dibujarPlantaHojasLargas(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y + 0.75f, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Maceta negra alta
        Cubo.dibujar(
                0f, 0.26f, 0f,
                escalar(0.38f), 0.52f, escalar(0.34f),
                0.05f, 0.05f, 0.05f);

        // Borde superior de maceta
        Cubo.dibujar(
                0f, 0.55f, 0f,
                escalar(0.44f), 0.08f, escalar(0.40f),
                0.10f, 0.10f, 0.10f);

        // Tierra
        Cubo.dibujar(
                0f, 0.61f, 0f,
                escalar(0.34f), 0.04f, escalar(0.30f),
                0.10f, 0.07f, 0.04f);

        // Tallos
        Cubo.dibujar(0f, 0.95f, 0f, 0.04f, 0.70f, 0.04f, 0.15f, 0.38f, 0.12f);
        Cubo.dibujar(-0.08f, 0.92f, 0.03f, 0.035f, 0.58f, 0.035f, 0.12f, 0.34f, 0.10f);
        Cubo.dibujar(0.08f, 0.90f, -0.03f, 0.035f, 0.55f, 0.035f, 0.12f, 0.34f, 0.10f);

        // Hojas largas
        dibujarHojaVertical(0f, 1.35f, 0f, 0f, 0.55f);
        dibujarHojaVertical(-0.12f, 1.22f, 0.05f, -25f, 0.48f);
        dibujarHojaVertical(0.12f, 1.22f, -0.04f, 25f, 0.48f);
        dibujarHojaVertical(-0.05f, 1.08f, -0.10f, -60f, 0.42f);
        dibujarHojaVertical(0.07f, 1.10f, 0.10f, 60f, 0.42f);
        dibujarHojaVertical(0f, 1.50f, 0.03f, 10f, 0.38f);

        glPopMatrix();
    }

    // ==========================================================
    // HOJAS AUXILIARES
    // ==========================================================
    private static void dibujarHojaGrande(float x, float y, float z, float rotacionY) {
        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);
        glRotatef(-18f, 0f, 0f, 1f);

        Cubo.dibujar(
                0f, 0f, 0.18f,
                0.13f, 0.035f, 0.48f,
                0.12f, 0.55f, 0.16f);

        Cubo.dibujar(
                0f, 0f, 0.36f,
                0.20f, 0.03f, 0.28f,
                0.18f, 0.70f, 0.22f);

        glPopMatrix();
    }

    private static void dibujarHojaPequena(float x, float y, float z, float rotacionY) {
        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        Cubo.dibujar(
                0f, 0f, 0.12f,
                0.10f, 0.035f, 0.28f,
                0.12f, 0.52f, 0.18f);

        glPopMatrix();
    }

    private static void dibujarHojaVertical(float x, float y, float z, float rotacionY, float alto) {
        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);
        glRotatef(-12f, 0f, 0f, 1f);

        Cubo.dibujar(
                0f, alto / 2f, 0f,
                0.12f, alto, 0.035f,
                0.15f, 0.58f, 0.18f);

        Cubo.dibujar(
                0f, alto * 0.75f, 0.015f,
                0.18f, alto * 0.35f, 0.03f,
                0.20f, 0.72f, 0.24f);

        glPopMatrix();
    }
}