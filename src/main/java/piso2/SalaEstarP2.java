package piso2;

import objetos.Cubo;
import utilidades.Constantes;

import static org.lwjgl.opengl.GL11.*;

public class SalaEstarP2 {

    private static final float Y = Constantes.ALTURA_PISO_2;
    
    public static int texturaSofa = 0;
    public static int texturaCojin = 0;
    public static int texturaMadera = 0;
    public static int texturaDecoracion = 0;

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
        dibujarSillonSeccionalP2();
        dibujarMesaCentro(1.6f, 11.2f, 3.1f, 12.0f, 0f);
        dibujarMuebleTV(0.2f, 9.6f, 2.8f, 10.2f, 0f);
        dibujarTapeteSala(0.65f, 10.85f, 3.25f, 12.65f);
    }

    private static void dibujarCuboPorGeo(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float yBase, float altura,
            float r, float g, float b, int texturaID) {

        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo;
        float largoGeo = zMaxGeo - zMinGeo;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        if (texturaID != 0) {
            Cubo.dibujarConTextura(
                    x,
                    yBase + altura / 2.0f,
                    z,
                    ancho,
                    altura,
                    largo,
                    texturaID);
        } else {
            Cubo.dibujar(
                    x,
                    yBase + altura / 2.0f,
                    z,
                    ancho,
                    altura,
                    largo,
                    r, g, b);
        }
    }

    // ==========================================================
    // SILLÓN SECCIONAL DEL PISO 2
    // ==========================================================
    private static void dibujarSillonSeccionalP2() {
        float altoAsiento = 0.35f;
        float altoRespaldo = 0.75f;

        // Parte larga superior
        dibujarCuboPorGeo(
                0.2f, 13.0f,
                3.1f, 13.8f,
                Y,
                altoAsiento,
                0.70f, 0.68f, 0.63f, texturaSofa);

        // Chaise / extensión izquierda
        dibujarCuboPorGeo(
                0.2f, 10.4f,
                1.2f, 13.0f,
                Y,
                altoAsiento,
                0.72f, 0.70f, 0.66f, texturaSofa);

        // Respaldo superior
        dibujarCuboPorGeo(
                0.2f, 13.68f,
                3.1f, 13.8f,
                Y + altoAsiento,
                altoRespaldo,
                0.58f, 0.56f, 0.52f, texturaSofa);

        // Respaldo izquierdo
        dibujarCuboPorGeo(
                0.2f, 10.4f,
                0.32f, 13.8f,
                Y + altoAsiento,
                altoRespaldo,
                0.58f, 0.56f, 0.52f, texturaSofa);

        // Brazo derecho
        dibujarCuboPorGeo(
                2.98f, 13.0f,
                3.1f, 13.8f,
                Y + altoAsiento,
                0.55f,
                0.60f, 0.58f, 0.54f, texturaSofa);

        // Brazo inferior chaise
        dibujarCuboPorGeo(
                0.2f, 10.4f,
                1.2f, 10.52f,
                Y + altoAsiento,
                0.48f,
                0.60f, 0.58f, 0.54f, texturaSofa);

        // Cojines
        dibujarCojin(0.75f, 13.35f, 0f);
        dibujarCojin(1.45f, 13.35f, 0f);
        dibujarCojin(2.15f, 13.35f, 0f);
        dibujarCojin(2.75f, 13.35f, 0f);

        dibujarCojinLargo(0.72f, 12.35f, 0f);
        dibujarCojinLargo(0.72f, 11.35f, 0f);

        dibujarCojinDecorativo(0.45f, 13.25f, 0f);
        dibujarCojinDecorativo(2.85f, 13.25f, 0f);
    }

    private static void dibujarCojin(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        if (texturaCojin != 0) {
            Cubo.dibujarConTextura(0f, 0.50f, 0f, escalar(0.55f), 0.16f, escalar(0.42f), texturaCojin);
        } else {
            Cubo.dibujar(0f, 0.50f, 0f, escalar(0.55f), 0.16f, escalar(0.42f), 0.78f, 0.76f, 0.72f);
        }

        glPopMatrix();
    }

    private static void dibujarCojinLargo(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        if (texturaCojin != 0) {
            Cubo.dibujarConTextura(0f, 0.50f, 0f, escalar(0.55f), 0.16f, escalar(0.75f), texturaCojin);
        } else {
            Cubo.dibujar(0f, 0.50f, 0f, escalar(0.55f), 0.16f, escalar(0.75f), 0.76f, 0.74f, 0.70f);
        }

        glPopMatrix();
    }

    private static void dibujarCojinDecorativo(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        if (texturaDecoracion != 0) {
            Cubo.dibujarConTextura(0f, 0.82f, 0f, escalar(0.28f), 0.18f, escalar(0.22f), texturaDecoracion);
        } else {
            Cubo.dibujar(0f, 0.82f, 0f, escalar(0.28f), 0.18f, escalar(0.22f), 0.86f, 0.82f, 0.74f);
        }

        glPopMatrix();
    }

    // ==========================================================
    // MESA CENTRAL
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
        if (texturaMadera != 0) {
            Cubo.dibujarConTextura(0f, 0.42f, 0f, ancho, 0.08f, largo, texturaMadera);
            Cubo.dibujarConTextura(0f, 0.22f, 0f, ancho * 0.90f, 0.06f, largo * 0.85f, texturaMadera);
            Cubo.dibujarConTextura(-ancho / 2f + 0.06f, 0.20f, -largo / 2f + 0.06f, 0.06f, 0.40f, 0.06f, texturaMadera);
            Cubo.dibujarConTextura(ancho / 2f - 0.06f, 0.20f, -largo / 2f + 0.06f, 0.06f, 0.40f, 0.06f, texturaMadera);
            Cubo.dibujarConTextura(-ancho / 2f + 0.06f, 0.20f, largo / 2f - 0.06f, 0.06f, 0.40f, 0.06f, texturaMadera);
            Cubo.dibujarConTextura(ancho / 2f - 0.06f, 0.20f, largo / 2f - 0.06f, 0.06f, 0.40f, 0.06f, texturaMadera);
        } else {
            Cubo.dibujar(0f, 0.42f, 0f, ancho, 0.08f, largo, 0.45f, 0.30f, 0.18f);
            Cubo.dibujar(0f, 0.22f, 0f, ancho * 0.90f, 0.06f, largo * 0.85f, 0.32f, 0.20f, 0.12f);
            Cubo.dibujar(-ancho / 2f + 0.06f, 0.20f, -largo / 2f + 0.06f, 0.06f, 0.40f, 0.06f, 0.15f, 0.15f, 0.15f);
            Cubo.dibujar(ancho / 2f - 0.06f, 0.20f, -largo / 2f + 0.06f, 0.06f, 0.40f, 0.06f, 0.15f, 0.15f, 0.15f);
            Cubo.dibujar(-ancho / 2f + 0.06f, 0.20f, largo / 2f - 0.06f, 0.06f, 0.40f, 0.06f, 0.15f, 0.15f, 0.15f);
            Cubo.dibujar(ancho / 2f - 0.06f, 0.20f, largo / 2f - 0.06f, 0.06f, 0.40f, 0.06f, 0.15f, 0.15f, 0.15f);
        }

        // Decoración sobre la mesa
        if (texturaDecoracion != 0) {
            Cubo.dibujarConTextura(0f, 0.51f, 0f, ancho * 0.35f, 0.04f, largo * 0.28f, texturaDecoracion);
            Cubo.dibujarConTextura(-ancho * 0.25f, 0.58f, largo * 0.20f, 0.08f, 0.14f, 0.08f, texturaDecoracion);
        } else {
            Cubo.dibujar(0f, 0.51f, 0f, ancho * 0.35f, 0.04f, largo * 0.28f, 0.85f, 0.85f, 0.80f);
            Cubo.dibujar(-ancho * 0.25f, 0.58f, largo * 0.20f, 0.08f, 0.14f, 0.08f, 0.10f, 0.55f, 0.18f);
        }

        glPopMatrix();
    }

    // ==========================================================
    // MUEBLE CON TV
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
        if (texturaMadera != 0) {
            Cubo.dibujarConTextura(0f, 0.30f, 0f, ancho, 0.60f, largo, texturaMadera);
            Cubo.dibujarConTextura(-ancho * 0.25f, 0.32f, largo / 2f + 0.02f, ancho * 0.35f, 0.18f, 0.03f, texturaMadera);
            Cubo.dibujarConTextura(ancho * 0.25f, 0.32f, largo / 2f + 0.02f, ancho * 0.35f, 0.18f, 0.03f, texturaMadera);
        } else {
            Cubo.dibujar(0f, 0.30f, 0f, ancho, 0.60f, largo, 0.22f, 0.18f, 0.14f);
            Cubo.dibujar(-ancho * 0.25f, 0.32f, largo / 2f + 0.02f, ancho * 0.35f, 0.18f, 0.03f, 0.35f, 0.26f, 0.18f);
            Cubo.dibujar(ancho * 0.25f, 0.32f, largo / 2f + 0.02f, ancho * 0.35f, 0.18f, 0.03f, 0.35f, 0.26f, 0.18f);
        }

        // Base de la TV y cuello (sin textura, material oscuro)
        Cubo.dibujar(0f, 0.65f, 0f, ancho * 0.22f, 0.06f, largo * 0.35f, 0.08f, 0.08f, 0.08f);
        Cubo.dibujar(0f, 0.80f, 0f, ancho * 0.06f, 0.28f, largo * 0.08f, 0.06f, 0.06f, 0.06f);

        // Pantalla de TV
        Cubo.dibujar(0f, 1.40f, -largo / 2f - 0.03f, ancho * 0.78f, 1.05f, 0.04f, 0.03f, 0.03f, 0.035f);
        Cubo.dibujar(0f, 1.40f, -largo / 2f - 0.055f, ancho * 0.68f, 0.85f, 0.03f, 0.07f, 0.10f, 0.16f);

        // Decoración lateral sobre el mueble
        if (texturaDecoracion != 0) {
            Cubo.dibujarConTextura(-ancho * 0.42f, 0.76f, largo * 0.15f, 0.10f, 0.22f, 0.10f, texturaDecoracion);
            Cubo.dibujarConTextura(ancho * 0.42f, 0.72f, largo * 0.10f, 0.14f, 0.14f, 0.14f, texturaDecoracion);
        } else {
            Cubo.dibujar(-ancho * 0.42f, 0.76f, largo * 0.15f, 0.10f, 0.22f, 0.10f, 0.12f, 0.45f, 0.18f);
            Cubo.dibujar(ancho * 0.42f, 0.72f, largo * 0.10f, 0.14f, 0.14f, 0.14f, 0.75f, 0.70f, 0.60f);
        }

        glPopMatrix();
    }

    // ==========================================================
    // TAPETE
    // ==========================================================
    private static void dibujarTapeteSala(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo) {

        dibujarCuboPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y + 0.01f,
                0.035f,
                0.48f, 0.48f, 0.52f, texturaCojin); // Usamos texturaCojin o texturaDecoracion para tapete, es tela

        // Líneas decorativas del tapete (sin textura, solo lineas grises)
        dibujarCuboPorGeo(
                xMinGeo + 0.12f, zMinGeo + 0.15f,
                xMaxGeo - 0.12f, zMinGeo + 0.22f,
                Y + 0.05f,
                0.02f,
                0.70f, 0.70f, 0.72f, 0);

        dibujarCuboPorGeo(
                xMinGeo + 0.12f, zMaxGeo - 0.22f,
                xMaxGeo - 0.12f, zMaxGeo - 0.15f,
                Y + 0.05f,
                0.02f,
                0.70f, 0.70f, 0.72f, 0);

        dibujarCuboPorGeo(
                xMinGeo + 0.20f, zMinGeo + 0.35f,
                xMaxGeo - 0.20f, zMaxGeo - 0.35f,
                Y + 0.055f,
                0.015f,
                0.56f, 0.56f, 0.60f, 0);
    }
}