package objetos;

import utilidades.Constantes;

import static org.lwjgl.opengl.GL11.*;

public class BanoMedio {

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
         * Baño del piso 1.
         * Cuarto entre los puntos A1, K, J, Y y Z.
         * Por ahora solo tiene:
         * - Retrete
         * - Lavabo
         */

        // Retrete pegado al lado derecho del baño (Muro K-J, X=7.9)
        // Lo ponemos en X=7.45, Z=10.1 y lo rotamos 90 grados para que el tanque quede
        // pegado a la pared derecha
        dibujarRetrete(7.45f, 10.1f, 90f);

        // Lavabo dentro del baño, pegado al muro inferior (Muro Z-J, Z=9.5)
        // Lo ponemos en X=6.0, Z=9.7, sin rotación para que la llave quede en la pared
        // de Z=9.5
        dibujarLavabo(6.5f, 9.7f, 0f);
    }

    private static void dibujarRetrete(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Base inferior
        Cubo.dibujar(
                0f,
                0.18f,
                0f,
                escalar(0.35f),
                0.35f,
                escalar(0.45f),
                0.90f,
                0.90f,
                0.86f);

        // Taza
        Cubo.dibujar(
                0f,
                0.48f,
                0.05f,
                escalar(0.45f),
                0.30f,
                escalar(0.50f),
                0.96f,
                0.96f,
                0.92f);

        // Hueco visual de la taza
        Cubo.dibujar(
                0f,
                0.66f,
                0.05f,
                escalar(0.25f),
                0.04f,
                escalar(0.28f),
                0.10f,
                0.10f,
                0.10f);

        // Tanque trasero
        Cubo.dibujar(
                0f,
                0.80f,
                -0.33f,
                escalar(0.50f),
                0.45f,
                escalar(0.18f),
                0.92f,
                0.92f,
                0.88f);

        // Tapa superior del tanque
        Cubo.dibujar(
                0f,
                1.05f,
                -0.33f,
                escalar(0.55f),
                0.06f,
                escalar(0.22f),
                0.82f,
                0.82f,
                0.78f);

        glPopMatrix();
    }

    private static void dibujarLavabo(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Mueble/base del lavabo
        Cubo.dibujar(
                0f,
                0.35f,
                0f,
                escalar(0.70f),
                0.70f,
                escalar(0.38f),
                0.55f,
                0.35f,
                0.20f);

        // Lavabo blanco superior
        Cubo.dibujar(
                0f,
                0.78f,
                0f,
                escalar(0.78f),
                0.16f,
                escalar(0.45f),
                0.95f,
                0.95f,
                0.90f);

        // Hueco del lavabo
        Cubo.dibujar(
                0f,
                0.89f,
                0.02f,
                escalar(0.38f),
                0.04f,
                escalar(0.22f),
                0.18f,
                0.25f,
                0.30f);

        // Llave
        Cubo.dibujar(
                0f,
                1.02f,
                -0.12f,
                escalar(0.08f),
                0.22f,
                escalar(0.08f),
                0.65f,
                0.65f,
                0.65f);

        Cubo.dibujar(
                0f,
                1.13f,
                -0.03f,
                escalar(0.08f),
                0.06f,
                escalar(0.25f),
                0.65f,
                0.65f,
                0.65f);

        glPopMatrix();
    }
}