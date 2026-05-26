package piso2;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class Recamara3P2 {

    private static final float Y = Constantes.ALTURA_PISO_2;
    public static int texturaMueble = 0;

    private static void dibujarCuboMadera(
            float x, float y, float z,
            float ancho, float alto, float profundo,
            float r, float g, float b) {
        if (texturaMueble > 0) {
            Cubo.dibujarConTextura(x, y, z, ancho, alto, profundo, texturaMueble);
        } else {
            Cubo.dibujar(x, y, z, ancho, alto, profundo, r, g, b);
        }
    }

    private static void dibujarCuboMaderaPorGeo(
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

        if (texturaMueble > 0) {
            Cubo.dibujarConTextura(x, yBase + altura / 2.0f, z, ancho, altura, largo, texturaMueble);
        } else {
            Cubo.dibujar(x, yBase + altura / 2.0f, z, ancho, altura, largo, r, g, b);
        }
    }

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
         * RECÁMARA 3 - PISO 2
         *
         * Cama:
         * V2 = (6.0, 16.3)
         * W2 = (6.0, 15.1)
         * Z2 = (7.7, 15.1)
         * A3 = (7.7, 16.3)
         *
         * Closet:
         * C3 = (5.9, 17.2)
         * B3 = (5.9, 16.8)
         * E3 = (7.8, 17.2)
         * D3 = (7.8, 16.8)
         *
         * Mesa:
         * F3 = (5.0, 15.1)
         * G3 = (5.4, 15.1)
         * H3 = (5.4, 14.0)
         * I3 = (5.0, 14.0)
         *
         * Buró:
         * J3 = (7.0, 14.7)
         * M3 = (7.8, 14.7)
         * K3 = (7.0, 14.3)
         * L3 = (7.8, 14.3)
         */

        // Cama entre V2, A3, W2 y Z2
        dibujarCama(6.0f, 15.1f, 7.7f, 16.3f);

        // Closet entre C3, B3, E3 y D3
        dibujarCloset(5.9f, 16.8f, 7.8f, 17.2f);

        // Mesa entre F3, G3, I3 y H3
        dibujarMesaYSilla(5.0f, 14.0f, 5.4f, 15.1f);

        // Buró entre J3, M3, K3 y L3

        // Detalles decorativos
        dibujarTapete(5.7f, 14.6f, 6.8f, 15.6f);

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

    private static void dibujarCama(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        // Base de madera
        dibujarCuboMaderaPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y,
                0.35f,
                0.35f, 0.22f, 0.14f);

        // Colchón
        dibujarCuboPorGeo(
                xMinGeo + 0.08f, zMinGeo + 0.08f,
                xMaxGeo - 0.08f, zMaxGeo - 0.08f,
                Y + 0.35f,
                0.20f,
                0.92f, 0.92f, 0.88f);

        // Cobija
        dibujarCuboPorGeo(
                xMinGeo + 0.12f, zMinGeo + 0.12f,
                xMaxGeo - 0.12f, zMaxGeo - 0.45f,
                Y + 0.56f,
                0.08f,
                0.25f, 0.38f, 0.65f);

        // Almohadas pegadas hacia la cabecera
        dibujarCuboPorGeo(
                xMaxGeo - 0.42f, zMinGeo + 0.15f,
                xMaxGeo - 0.12f, zMaxGeo - 0.15f,
                Y + 0.58f,
                0.10f,
                0.95f, 0.95f, 0.95f);

        // Cabecera pegada a la pared del lado C2 - O1
        // Esa pared queda hacia X mayor, por eso se coloca en el lado de A3 - Z2.
        dibujarCuboMaderaPorGeo(
                xMaxGeo - 0.05f, zMinGeo,
                xMaxGeo + 0.08f, zMaxGeo,
                Y,
                0.95f,
                0.28f, 0.16f, 0.10f);
    }

    private static void dibujarCloset(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo;
        float largoGeo = zMaxGeo - zMinGeo;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);
        float alto = 2.4f;

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Cuerpo principal del closet
        dibujarCuboMadera(
                0f, alto / 2f, 0f,
                ancho, alto, largo,
                0.38f, 0.23f, 0.13f);

        // Puertas del closet
        float anchoPuerta = ancho / 4f;

        for (int i = 0; i < 4; i++) {
            float offsetX = -ancho / 2f + anchoPuerta / 2f + i * anchoPuerta;

            dibujarCuboMadera(
                    offsetX, alto / 2f, -largo / 2f - 0.015f,
                    anchoPuerta * 0.92f, alto * 0.92f, 0.025f,
                    0.46f, 0.29f, 0.18f);

            // Manijas
            Cubo.dibujar(
                    offsetX + (i % 2 == 0 ? anchoPuerta * 0.25f : -anchoPuerta * 0.25f),
                    alto * 0.48f,
                    -largo / 2f - 0.04f,
                    0.025f, 0.35f, 0.025f,
                    0.75f, 0.75f, 0.75f);
        }

        glPopMatrix();
    }

    private static void dibujarMesaYSilla(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        // Cubierta de la mesa
        dibujarCuboMaderaPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y + 0.70f,
                0.08f,
                0.45f, 0.28f, 0.14f);

        // Patas de la mesa
        dibujarCuboPorGeo(xMinGeo + 0.03f, zMinGeo + 0.03f, xMinGeo + 0.08f, zMinGeo + 0.08f, Y, 0.70f,
                0.12f, 0.12f, 0.12f);
        dibujarCuboPorGeo(xMaxGeo - 0.08f, zMinGeo + 0.03f, xMaxGeo - 0.03f, zMinGeo + 0.08f, Y, 0.70f,
                0.12f, 0.12f, 0.12f);
        dibujarCuboPorGeo(xMinGeo + 0.03f, zMaxGeo - 0.08f, xMinGeo + 0.08f, zMaxGeo - 0.03f, Y, 0.70f,
                0.12f, 0.12f, 0.12f);
        dibujarCuboPorGeo(xMaxGeo - 0.08f, zMaxGeo - 0.08f, xMaxGeo - 0.03f, zMaxGeo - 0.03f, Y, 0.70f,
                0.12f, 0.12f, 0.12f);

        // Silla frente a la mesa
        dibujarCuboPorGeo(
                xMaxGeo + 0.18f, zMinGeo + 0.35f,
                xMaxGeo + 0.55f, zMinGeo + 0.75f,
                Y,
                0.30f,
                0.18f, 0.18f, 0.20f);

        // Respaldo de la silla
        dibujarCuboPorGeo(
                xMaxGeo + 0.48f, zMinGeo + 0.35f,
                xMaxGeo + 0.58f, zMinGeo + 0.75f,
                Y + 0.30f,
                0.60f,
                0.14f, 0.14f, 0.16f);

        // Computadora sobre la mesa
        float centroX = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZ = (zMinGeo + zMaxGeo) / 2.0f;

        // Monitor
        dibujarCuboPorGeo(
                centroX - 0.04f, centroZ - 0.22f,
                centroX + 0.04f, centroZ + 0.22f,
                Y + 0.82f,
                0.28f,
                0.05f, 0.05f, 0.05f);

        // Teclado
        dibujarCuboPorGeo(
                centroX + 0.10f, centroZ - 0.20f,
                centroX + 0.20f, centroZ + 0.20f,
                Y + 0.76f,
                0.04f,
                0.02f, 0.02f, 0.02f);
    }

    private static void dibujarTapete(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        dibujarCuboPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y + 0.01f,
                0.03f,
                0.55f, 0.55f, 0.60f);
    }

}