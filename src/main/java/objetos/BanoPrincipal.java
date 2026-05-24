package objetos;

import utilidades.Constantes;

import static org.lwjgl.opengl.GL11.*;

public class BanoPrincipal {

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
         * Baño Principal del primer piso.
         * Dividido en dos áreas:
         * 1. Área de Tina y Retrete: Rectángulo D1(0.1, 14.0) a T1(0.1, 15.4) y F1(2.5, 14.0)
         * 2. Área de Lavabo: Rectángulo F1(2.5, 14.0) a H1(3.9, 14.5)
         */

        // Tina pegada a la pared izquierda (X = 0.1 a 1.1)
        // Centro en X = 0.6, Z = 14.7
        dibujarTina(0.6f, 14.7f, 0f);

        // Retrete al lado de la tina, recargado en la pared inferior (Z = 14.0)
        // Centro en X = 1.7, Z = 14.3
        dibujarRetrete(1.7f, 14.3f, 0f);

        // Lavabo en su cuarto correspondiente, pegado a la pared inferior (Z = 14.0)
        // Centro en X = 3.2, Z = 14.25
        dibujarLavabo(3.2f, 14.25f, 0f);
    }

    private static void dibujarTina(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Base/Estructura exterior de la tina (Blanco)
        Cubo.dibujar(
                0f, 0.25f, 0f, 
                escalar(1.0f), 0.50f, escalar(1.4f), 
                0.95f, 0.95f, 0.95f);

        // Borde superior de la tina
        Cubo.dibujar(
                0f, 0.52f, 0f, 
                escalar(1.0f), 0.04f, escalar(1.4f), 
                0.98f, 0.98f, 0.98f);

        // Hueco interior de la tina (Ligeramente más azulado para dar profundidad)
        Cubo.dibujar(
                0f, 0.40f, 0f, 
                escalar(0.80f), 0.20f, escalar(1.20f), 
                0.85f, 0.90f, 0.92f);

        // Grifo de la tina (Pegado al lado inferior en Z)
        Cubo.dibujar(0f, 0.60f, -escalar(0.60f), escalar(0.15f), 0.15f, escalar(0.10f), 0.7f, 0.7f, 0.7f); // Base grifo
        Cubo.dibujar(0f, 0.65f, -escalar(0.50f), escalar(0.05f), 0.05f, escalar(0.15f), 0.7f, 0.7f, 0.7f); // Tubo grifo

        glPopMatrix();
    }

    private static void dibujarRetrete(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Base inferior
        Cubo.dibujar(
                0f, 0.18f, 0f,
                escalar(0.35f), 0.35f, escalar(0.45f),
                0.90f, 0.90f, 0.86f);

        // Taza
        Cubo.dibujar(
                0f, 0.48f, 0.05f,
                escalar(0.45f), 0.30f, escalar(0.50f),
                0.96f, 0.96f, 0.92f);

        // Hueco visual de la taza
        Cubo.dibujar(
                0f, 0.66f, 0.05f,
                escalar(0.25f), 0.04f, escalar(0.28f),
                0.10f, 0.10f, 0.10f);

        // Tanque trasero (pegado a Z = -0.33)
        Cubo.dibujar(
                0f, 0.80f, -0.33f,
                escalar(0.50f), 0.45f, escalar(0.18f),
                0.92f, 0.92f, 0.88f);

        // Tapa superior del tanque
        Cubo.dibujar(
                0f, 1.05f, -0.33f,
                escalar(0.55f), 0.06f, escalar(0.22f),
                0.82f, 0.82f, 0.78f);

        glPopMatrix();
    }

    private static void dibujarLavabo(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Mueble/base del lavabo (ocupa casi todo el espacio del cuartito)
        Cubo.dibujar(
                0f, 0.35f, 0f,
                escalar(1.30f), 0.70f, escalar(0.45f),
                0.35f, 0.20f, 0.10f); // Madera oscura

        // Encimera del lavabo (Blanco marmol)
        Cubo.dibujar(
                0f, 0.72f, 0f,
                escalar(1.35f), 0.06f, escalar(0.50f),
                0.95f, 0.95f, 0.95f);

        // Lavabo blanco incrustado
        Cubo.dibujar(
                0f, 0.76f, 0f,
                escalar(0.60f), 0.10f, escalar(0.35f),
                0.90f, 0.90f, 0.90f);

        // Hueco del lavabo
        Cubo.dibujar(
                0f, 0.82f, 0.02f,
                escalar(0.50f), 0.04f, escalar(0.25f),
                0.15f, 0.20f, 0.25f);

        // Llave del lavabo
        Cubo.dibujar(0f, 0.90f, -0.15f, escalar(0.08f), 0.15f, escalar(0.08f), 0.65f, 0.65f, 0.65f);
        Cubo.dibujar(0f, 0.98f, -0.05f, escalar(0.08f), 0.06f, escalar(0.20f), 0.65f, 0.65f, 0.65f);

        glPopMatrix();
    }
}
