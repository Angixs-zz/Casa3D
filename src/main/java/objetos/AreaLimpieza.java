package objetos;

import utilidades.Constantes;

import static org.lwjgl.opengl.GL11.*;

public class AreaLimpieza {

    private static final float Y = Constantes.ALTURA_PISO_1;

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
         * =====================================================
         * ÁREA DE LIMPIEZA - PISO 1
         * =====================================================
         *
         * 1) LAVADERO:
         * Q6 = (7.4, 18.9)
         * P6 = (7.4, 19.6)
         * S6 = (7.8, 19.6)
         * R6 = (7.8, 18.9)
         *
         * 2) ENCIMERA / ESTANTE:
         * P6 = (7.4, 19.6)
         * T6 = (7.4, 20.2)
         * U6 = (7.8, 20.2)
         * S6 = (7.8, 19.6)
         *
         * 3) LAVADORA:
         * W6 = (6.6, 19.6)
         * V6 = (6.6, 20.2)
         * T6 = (7.4, 20.2)
         * P6 = (7.4, 19.6)
         */

        dibujarLavadora();
        dibujarEncimeraEstante();
        dibujarLavadero();
    }

    // =====================================================
    // 1. LAVADERO PARA LAVAR ROPA
    // Ubicado en P6, S6, Q6, R6
    // =====================================================
    private static void dibujarLavadero() {
        float xCentroGeo = 7.60f;
        float zCentroGeo = 19.25f;

        float x = convertirXGeoAOpenGL(xCentroGeo);
        float z = convertirZGeoAOpenGL(zCentroGeo);

        float ancho = escalar(0.38f); // X: 7.4 a 7.8
        float fondo = escalar(0.66f); // Z: 18.9 a 19.6

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Base principal de concreto
        Cubo.dibujar(
                0f,
                0.78f,
                0f,
                ancho,
                0.22f,
                fondo,
                0.72f,
                0.72f,
                0.70f);

        // Borde superior del lavadero
        Cubo.dibujar(
                0f,
                0.94f,
                0f,
                ancho + escalar(0.04f),
                0.08f,
                fondo + escalar(0.04f),
                0.80f,
                0.80f,
                0.78f);

        // Hueco izquierdo oscuro
        Cubo.dibujar(
                -ancho * 0.23f,
                1.00f,
                0f,
                ancho * 0.34f,
                0.035f,
                fondo * 0.55f,
                0.20f,
                0.22f,
                0.24f);

        // Hueco derecho oscuro
        Cubo.dibujar(
                ancho * 0.23f,
                1.00f,
                0f,
                ancho * 0.34f,
                0.035f,
                fondo * 0.55f,
                0.20f,
                0.22f,
                0.24f);

        // División central
        Cubo.dibujar(
                0f,
                1.02f,
                0f,
                escalar(0.035f),
                0.08f,
                fondo * 0.70f,
                0.58f,
                0.58f,
                0.56f);

        // Pata izquierda
        Cubo.dibujar(
                -ancho * 0.28f,
                0.38f,
                0f,
                escalar(0.10f),
                0.75f,
                escalar(0.12f),
                0.68f,
                0.68f,
                0.66f);

        // Pata derecha
        Cubo.dibujar(
                ancho * 0.28f,
                0.38f,
                0f,
                escalar(0.10f),
                0.75f,
                escalar(0.12f),
                0.68f,
                0.68f,
                0.66f);

        // Llave vertical
        Cubo.dibujar(
                0f,
                1.18f,
                -fondo * 0.25f,
                escalar(0.035f),
                0.28f,
                escalar(0.035f),
                0.60f,
                0.60f,
                0.60f);

        // Llave horizontal
        Cubo.dibujar(
                0f,
                1.32f,
                -fondo * 0.12f,
                escalar(0.035f),
                0.04f,
                escalar(0.20f),
                0.60f,
                0.60f,
                0.60f);

        glPopMatrix();
    }

    // =====================================================
    // 2. ENCIMERA / ESTANTE
    // Ubicado en T6, U6, P6, S6
    // =====================================================
    private static void dibujarEncimeraEstante() {
        float xCentroGeo = 7.60f;
        float zCentroGeo = 19.90f;

        float x = convertirXGeoAOpenGL(xCentroGeo);
        float z = convertirZGeoAOpenGL(zCentroGeo);

        float ancho = escalar(0.38f); // X: 7.4 a 7.8
        float fondo = escalar(0.56f); // Z: 19.6 a 20.2

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Cubierta superior tipo encimera
        Cubo.dibujar(
                0f,
                0.88f,
                0f,
                ancho,
                0.10f,
                fondo,
                0.62f,
                0.62f,
                0.62f);

        // Cuerpo inferior tipo mueble sencillo
        Cubo.dibujar(
                0f,
                0.45f,
                0f,
                ancho * 0.90f,
                0.70f,
                fondo * 0.85f,
                0.55f,
                0.55f,
                0.55f);

        // Repisa interna
        Cubo.dibujar(
                0f,
                0.45f,
                0f,
                ancho * 0.92f,
                0.05f,
                fondo * 0.88f,
                0.78f,
                0.78f,
                0.76f);

        // Puerta / frente del mueble
        Cubo.dibujar(
                0f,
                0.48f,
                -fondo / 2f + 0.03f,
                ancho * 0.80f,
                0.50f,
                0.035f,
                0.50f,
                0.50f,
                0.50f);

        // Jaladera
        Cubo.dibujar(
                0f,
                0.55f,
                -fondo / 2f + 0.06f,
                ancho * 0.45f,
                0.035f,
                0.03f,
                0.82f,
                0.82f,
                0.82f);

        // Detergente decorativo sobre la encimera
        Cubo.dibujar(
                -ancho * 0.18f,
                1.08f,
                0f,
                escalar(0.10f),
                0.30f,
                escalar(0.10f),
                0.10f,
                0.35f,
                0.85f);

        // Tapa del detergente
        Cubo.dibujar(
                -ancho * 0.18f,
                1.25f,
                0f,
                escalar(0.07f),
                0.05f,
                escalar(0.07f),
                0.95f,
                0.95f,
                0.95f);

        glPopMatrix();
    }

    // =====================================================
    // 3. LAVADORA
    // Ubicada en V6, T6, W6, P6
    // =====================================================
    private static void dibujarLavadora() {
        float xCentroGeo = 7.00f;
        float zCentroGeo = 19.90f;

        float x = convertirXGeoAOpenGL(xCentroGeo);
        float z = convertirZGeoAOpenGL(zCentroGeo);

        float ancho = escalar(0.72f); // X: 6.6 a 7.4
        float fondo = escalar(0.56f); // Z: 19.6 a 20.2
        float altura = 1.00f;

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Cuerpo principal blanco
        Cubo.dibujar(
                0f,
                altura / 2f,
                0f,
                ancho,
                altura,
                fondo,
                0.92f,
                0.92f,
                0.90f);

        // Tapa superior gris
        Cubo.dibujar(
                0f,
                altura + 0.035f,
                0f,
                ancho * 1.02f,
                0.07f,
                fondo * 1.02f,
                0.78f,
                0.78f,
                0.78f);

        // Panel de control
        Cubo.dibujar(
                0f,
                0.84f,
                -fondo / 2f + 0.035f,
                ancho * 0.90f,
                0.16f,
                0.035f,
                0.75f,
                0.75f,
                0.78f);

        // Marco de la puerta circular simulado como cuadro grande
        Cubo.dibujar(
                0f,
                0.42f,
                -fondo / 2f + 0.04f,
                ancho * 0.58f,
                0.50f,
                0.035f,
                0.66f,
                0.66f,
                0.68f);

        // Cristal oscuro de la puerta
        Cubo.dibujar(
                0f,
                0.42f,
                -fondo / 2f + 0.065f,
                ancho * 0.42f,
                0.34f,
                0.025f,
                0.08f,
                0.10f,
                0.12f);

        // Cajetín de jabón
        Cubo.dibujar(
                -ancho * 0.26f,
                0.84f,
                -fondo / 2f + 0.065f,
                ancho * 0.22f,
                0.08f,
                0.025f,
                0.95f,
                0.95f,
                0.95f);

        // Botón 1
        Cubo.dibujar(
                ancho * 0.12f,
                0.85f,
                -fondo / 2f + 0.07f,
                escalar(0.045f),
                0.045f,
                0.02f,
                0.16f,
                0.16f,
                0.16f);

        // Botón 2
        Cubo.dibujar(
                ancho * 0.26f,
                0.85f,
                -fondo / 2f + 0.07f,
                escalar(0.045f),
                0.045f,
                0.02f,
                0.16f,
                0.16f,
                0.16f);

        // Base inferior
        Cubo.dibujar(
                0f,
                0.04f,
                0f,
                ancho,
                0.08f,
                fondo,
                0.65f,
                0.65f,
                0.65f);

        glPopMatrix();
    }
}