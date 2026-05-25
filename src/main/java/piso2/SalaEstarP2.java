package piso2;

import objetos.Cubo;
import utilidades.Constantes;

import static org.lwjgl.opengl.GL11.*;

public class SalaEstarP2 {

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
         * SALA DE ESTAR - PISO 2
         *
         * Sillón:
         * B4 = (0.2, 13.8)
         * C4 = (0.2, 10.4)
         * D4 = (1.2, 10.4)
         * E4 = (1.2, 13.0)
         * F4 = (3.1, 13.0)
         * G4 = (3.1, 13.8)
         *
         * Mesa:
         * H4 = (1.6, 12.0)
         * I4 = (1.6, 11.2)
         * J4 = (3.1, 11.2)
         * K4 = (3.1, 12.0)
         *
         * Mueble con TV:
         * L4 = (0.2, 10.2)
         * M4 = (0.2, 9.6)
         * N4 = (2.8, 9.6)
         * O4 = (2.8, 10.2)
         */

        dibujarSillonSeccionalP2();

        dibujarMesaCentro(1.6f, 11.2f, 3.1f, 12.0f, 0f);

        dibujarMuebleTV(0.2f, 9.6f, 2.8f, 10.2f, 0f);

        dibujarTapeteSala(0.65f, 10.85f, 3.25f, 12.65f);
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
    // SILLÓN SECCIONAL DEL PISO 2
    // Del punto B4 al G4
    // ==========================================================
    private static void dibujarSillonSeccionalP2() {
        float altoAsiento = 0.35f;
        float altoRespaldo = 0.75f;
        float grosorRespaldoGeo = 0.12f;

        // Parte larga superior del sillón: B4 - G4
        // Ocupa de X 0.2 a 3.1 y de Z 13.0 a 13.8
        dibujarCuboPorGeo(
                0.2f, 13.0f,
                3.1f, 13.8f,
                Y,
                altoAsiento,
                0.70f, 0.68f, 0.63f);

        // Chaise / extensión izquierda: C4 - D4 - E4
        // Ocupa de X 0.2 a 1.2 y de Z 10.4 a 13.0
        dibujarCuboPorGeo(
                0.2f, 10.4f,
                1.2f, 13.0f,
                Y,
                altoAsiento,
                0.72f, 0.70f, 0.66f);

        // Respaldo superior, pegado al segmento B4 - G4
        dibujarCuboPorGeo(
                0.2f, 13.68f,
                3.1f, 13.8f,
                Y + altoAsiento,
                altoRespaldo,
                0.58f, 0.56f, 0.52f);

        // Respaldo izquierdo, pegado al segmento B4 - C4
        dibujarCuboPorGeo(
                0.2f, 10.4f,
                0.32f, 13.8f,
                Y + altoAsiento,
                altoRespaldo,
                0.58f, 0.56f, 0.52f);

        // Brazo derecho pequeño en G4 - F4
        dibujarCuboPorGeo(
                2.98f, 13.0f,
                3.1f, 13.8f,
                Y + altoAsiento,
                0.55f,
                0.60f, 0.58f, 0.54f);

        // Brazo inferior de la chaise, cerca de C4 - D4
        dibujarCuboPorGeo(
                0.2f, 10.4f,
                1.2f, 10.52f,
                Y + altoAsiento,
                0.48f,
                0.60f, 0.58f, 0.54f);

        // Cojines sobre la parte larga
        dibujarCojin(0.75f, 13.35f, 0f);
        dibujarCojin(1.45f, 13.35f, 0f);
        dibujarCojin(2.15f, 13.35f, 0f);
        dibujarCojin(2.75f, 13.35f, 0f);

        // Cojines en la parte larga de la chaise
        dibujarCojinLargo(0.72f, 12.35f, 0f);
        dibujarCojinLargo(0.72f, 11.35f, 0f);

        // Cojines decorativos
        dibujarCojinDecorativo(0.45f, 13.25f, 0f);
        dibujarCojinDecorativo(2.85f, 13.25f, 0f);
    }

    private static void dibujarCojin(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        Cubo.dibujar(
                0f, 0.50f, 0f,
                escalar(0.55f), 0.16f, escalar(0.42f),
                0.78f, 0.76f, 0.72f);

        glPopMatrix();
    }

    private static void dibujarCojinLargo(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        Cubo.dibujar(
                0f, 0.50f, 0f,
                escalar(0.55f), 0.16f, escalar(0.75f),
                0.76f, 0.74f, 0.70f);

        glPopMatrix();
    }

    private static void dibujarCojinDecorativo(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        Cubo.dibujar(
                0f, 0.82f, 0f,
                escalar(0.28f), 0.18f, escalar(0.22f),
                0.86f, 0.82f, 0.74f);

        glPopMatrix();
    }

    // ==========================================================
    // MESA CENTRAL
    // Entre H4, I4, J4, K4
    // ==========================================================
    private static void dibujarMesaCentro(
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

        // Superficie de la mesa
        Cubo.dibujar(
                0f, 0.42f, 0f,
                ancho, 0.08f, largo,
                0.45f, 0.30f, 0.18f);

        // Repisa inferior
        Cubo.dibujar(
                0f, 0.22f, 0f,
                ancho * 0.90f, 0.06f, largo * 0.85f,
                0.32f, 0.20f, 0.12f);

        // Patas
        Cubo.dibujar(-ancho / 2f + 0.06f, 0.20f, -largo / 2f + 0.06f, 0.06f, 0.40f, 0.06f,
                0.15f, 0.15f, 0.15f);
        Cubo.dibujar(ancho / 2f - 0.06f, 0.20f, -largo / 2f + 0.06f, 0.06f, 0.40f, 0.06f,
                0.15f, 0.15f, 0.15f);
        Cubo.dibujar(-ancho / 2f + 0.06f, 0.20f, largo / 2f - 0.06f, 0.06f, 0.40f, 0.06f,
                0.15f, 0.15f, 0.15f);
        Cubo.dibujar(ancho / 2f - 0.06f, 0.20f, largo / 2f - 0.06f, 0.06f, 0.40f, 0.06f,
                0.15f, 0.15f, 0.15f);

        // Decoración sobre la mesa
        Cubo.dibujar(
                0f, 0.51f, 0f,
                ancho * 0.35f, 0.04f, largo * 0.28f,
                0.85f, 0.85f, 0.80f);

        Cubo.dibujar(
                -ancho * 0.25f, 0.58f, largo * 0.20f,
                0.08f, 0.14f, 0.08f,
                0.10f, 0.55f, 0.18f);

        glPopMatrix();
    }

    // ==========================================================
    // MUEBLE CON TV
    // Entre L4, M4, N4, O4
    // ==========================================================
    private static void dibujarMuebleTV(
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

        // Mueble bajo pegado a la pared
        Cubo.dibujar(
                0f, 0.30f, 0f,
                ancho, 0.60f, largo,
                0.22f, 0.18f, 0.14f);

        // Cajones
        Cubo.dibujar(
                -ancho * 0.25f, 0.32f, largo / 2f + 0.02f,
                ancho * 0.35f, 0.18f, 0.03f,
                0.35f, 0.26f, 0.18f);

        Cubo.dibujar(
                ancho * 0.25f, 0.32f, largo / 2f + 0.02f,
                ancho * 0.35f, 0.18f, 0.03f,
                0.35f, 0.26f, 0.18f);

        // Base de la TV
        Cubo.dibujar(
                0f, 0.65f, 0f,
                ancho * 0.22f, 0.06f, largo * 0.35f,
                0.08f, 0.08f, 0.08f);

        // Cuello / soporte
        Cubo.dibujar(
                0f, 0.80f, 0f,
                ancho * 0.06f, 0.28f, largo * 0.08f,
                0.06f, 0.06f, 0.06f);

        // Pantalla de TV, vertical y pegada visualmente a la pared
        Cubo.dibujar(
                0f, 1.40f, -largo / 2f - 0.03f,
                ancho * 0.78f, 1.05f, 0.04f,
                0.03f, 0.03f, 0.035f);

        // Pantalla interior azul oscuro
        Cubo.dibujar(
                0f, 1.40f, -largo / 2f - 0.055f,
                ancho * 0.68f, 0.85f, 0.03f,
                0.07f, 0.10f, 0.16f);

        // Decoración lateral sobre el mueble
        Cubo.dibujar(
                -ancho * 0.42f, 0.76f, largo * 0.15f,
                0.10f, 0.22f, 0.10f,
                0.12f, 0.45f, 0.18f);

        Cubo.dibujar(
                ancho * 0.42f, 0.72f, largo * 0.10f,
                0.14f, 0.14f, 0.14f,
                0.75f, 0.70f, 0.60f);

        glPopMatrix();
    }

    // ==========================================================
    // TAPETE
    // Cubre parte del sillón y de la mesa
    // ==========================================================
    private static void dibujarTapeteSala(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo) {

        dibujarCuboPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y + 0.01f,
                0.035f,
                0.48f, 0.48f, 0.52f);

        // Líneas decorativas del tapete
        dibujarCuboPorGeo(
                xMinGeo + 0.12f, zMinGeo + 0.15f,
                xMaxGeo - 0.12f, zMinGeo + 0.22f,
                Y + 0.05f,
                0.02f,
                0.70f, 0.70f, 0.72f);

        dibujarCuboPorGeo(
                xMinGeo + 0.12f, zMaxGeo - 0.22f,
                xMaxGeo - 0.12f, zMaxGeo - 0.15f,
                Y + 0.05f,
                0.02f,
                0.70f, 0.70f, 0.72f);

        dibujarCuboPorGeo(
                xMinGeo + 0.20f, zMinGeo + 0.35f,
                xMaxGeo - 0.20f, zMaxGeo - 0.35f,
                Y + 0.055f,
                0.015f,
                0.56f, 0.56f, 0.60f);
    }
}