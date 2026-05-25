package piso2;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class BalconP2 {

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
         * BALCÓN - PISO 2
         *
         * Silla izquierda:
         * K8 = (0.4, 1.0)
         * L8 = (0.7, 0.7)
         * M8 = (1.0, 1.0)
         * N8 = (0.7, 1.3)
         *
         * Mesa:
         * O8 = (1.3, 1.1)
         * P8 = (1.3, 0.4)
         * Q8 = (2.0, 0.4)
         * R8 = (2.0, 1.1)
         *
         * Silla derecha:
         * S8 = (2.7, 1.0)
         * T8 = (3.0, 1.3)
         * U8 = (3.3, 1.0)
         * V8 = (3.0, 0.7)
         *
         * Planta:
         * W8 = (3.8, 1.3)
         * Z8 = (3.8, 0.7)
         * A9 = (4.3, 0.7)
         * B9 = (4.3, 1.3)
         */

        dibujarSillaBalcon(0.4f, 0.7f, 1.0f, 1.3f, 225f);

        dibujarMesaBalcon(1.3f, 0.4f, 2.0f, 1.1f, 0f);

        dibujarSillaBalcon(2.7f, 0.7f, 3.3f, 1.3f, -225f);

        dibujarPlantaBalcon(3.8f, 0.7f, 4.3f, 1.3f, 0f);

        dibujarTapeteBalcon(1.05f, 0.25f, 3.45f, 1.35f);

        dibujarBarandalBalcon();
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
    // SILLA DEL BALCÓN
    // ==========================================================
    private static void dibujarSillaBalcon(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float rotacionY) {

        float centroX = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZ = (zMinGeo + zMaxGeo) / 2.0f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float ancho = escalar(xMaxGeo - xMinGeo);
        float fondo = escalar(zMaxGeo - zMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Asiento
        Cubo.dibujar(
                0f, 0.34f, 0f,
                ancho * 0.78f, 0.18f, fondo * 0.78f,
                0.72f, 0.68f, 0.58f);

        // Cojín
        Cubo.dibujar(
                0f, 0.46f, 0f,
                ancho * 0.68f, 0.10f, fondo * 0.68f,
                0.86f, 0.84f, 0.78f);

        // Respaldo inclinado visual
        Cubo.dibujar(
                0f, 0.78f, -fondo * 0.38f,
                ancho * 0.76f, 0.72f, 0.10f,
                0.42f, 0.30f, 0.18f);

        // Brazos
        Cubo.dibujar(
                -ancho * 0.42f, 0.50f, 0f,
                0.07f, 0.45f, fondo * 0.72f,
                0.36f, 0.24f, 0.15f);

        Cubo.dibujar(
                ancho * 0.42f, 0.50f, 0f,
                0.07f, 0.45f, fondo * 0.72f,
                0.36f, 0.24f, 0.15f);

        // Patas
        Cubo.dibujar(-ancho * 0.30f, 0.18f, -fondo * 0.30f, 0.05f, 0.36f, 0.05f,
                0.16f, 0.16f, 0.16f);
        Cubo.dibujar(ancho * 0.30f, 0.18f, -fondo * 0.30f, 0.05f, 0.36f, 0.05f,
                0.16f, 0.16f, 0.16f);
        Cubo.dibujar(-ancho * 0.30f, 0.18f, fondo * 0.30f, 0.05f, 0.36f, 0.05f,
                0.16f, 0.16f, 0.16f);
        Cubo.dibujar(ancho * 0.30f, 0.18f, fondo * 0.30f, 0.05f, 0.36f, 0.05f,
                0.16f, 0.16f, 0.16f);

        glPopMatrix();
    }

    // ==========================================================
    // MESA DEL BALCÓN
    // Entre O8, P8, Q8, R8
    // ==========================================================
    private static void dibujarMesaBalcon(
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

        // Tapa de mesa cuadrada
        Cubo.dibujar(
                0f, 0.58f, 0f,
                ancho, 0.08f, largo,
                0.48f, 0.32f, 0.18f);

        // Base central
        Cubo.dibujar(
                0f, 0.30f, 0f,
                0.10f, 0.58f, 0.10f,
                0.18f, 0.18f, 0.18f);

        // Base inferior
        Cubo.dibujar(
                0f, 0.06f, 0f,
                ancho * 0.55f, 0.08f, largo * 0.55f,
                0.12f, 0.12f, 0.12f);

        // Detalle decorativo sobre mesa: vaso
        Cubo.dibujar(
                -ancho * 0.18f, 0.70f, -largo * 0.10f,
                0.08f, 0.18f, 0.08f,
                0.55f, 0.78f, 0.90f);

        // Detalle decorativo: plato/libro
        Cubo.dibujar(
                ancho * 0.16f, 0.65f, largo * 0.10f,
                0.20f, 0.035f, 0.14f,
                0.90f, 0.86f, 0.72f);

        // Centro tipo florero pequeño
        Cubo.dibujar(
                0f, 0.72f, 0f,
                0.08f, 0.20f, 0.08f,
                0.18f, 0.38f, 0.16f);

        Cubo.dibujar(
                0f, 0.88f, 0f,
                0.22f, 0.16f, 0.22f,
                0.12f, 0.55f, 0.18f);

        glPopMatrix();
    }

    // ==========================================================
    // PLANTA GRANDE DEL BALCÓN
    // Entre W8, Z8, A9, B9
    // ==========================================================
    private static void dibujarPlantaBalcon(
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

        // Maceta grande
        Cubo.dibujar(
                0f, 0.32f, 0f,
                ancho * 0.85f, 0.64f, largo * 0.85f,
                0.06f, 0.06f, 0.06f);

        // Borde superior
        Cubo.dibujar(
                0f, 0.68f, 0f,
                ancho, 0.09f, largo,
                0.12f, 0.12f, 0.12f);

        // Tierra
        Cubo.dibujar(
                0f, 0.75f, 0f,
                ancho * 0.72f, 0.05f, largo * 0.72f,
                0.12f, 0.08f, 0.04f);

        // Tallos
        Cubo.dibujar(
                0f, 1.10f, 0f,
                0.05f, 0.70f, 0.05f,
                0.18f, 0.38f, 0.14f);

        Cubo.dibujar(
                -0.10f, 1.05f, 0.04f,
                0.04f, 0.58f, 0.04f,
                0.16f, 0.34f, 0.12f);

        Cubo.dibujar(
                0.11f, 1.02f, -0.04f,
                0.04f, 0.54f, 0.04f,
                0.16f, 0.34f, 0.12f);

        // Hojas grandes
        dibujarHojaLocal(0f, 1.48f, 0f, 0f);
        dibujarHojaLocal(0f, 1.42f, 0f, 45f);
        dibujarHojaLocal(0f, 1.45f, 0f, 90f);
        dibujarHojaLocal(0f, 1.39f, 0f, 135f);
        dibujarHojaLocal(0f, 1.46f, 0f, 180f);
        dibujarHojaLocal(0f, 1.38f, 0f, 225f);
        dibujarHojaLocal(0f, 1.43f, 0f, 270f);
        dibujarHojaLocal(0f, 1.40f, 0f, 315f);

        glPopMatrix();
    }

    private static void dibujarHojaLocal(float x, float y, float z, float rotacionY) {
        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);
        glRotatef(-18f, 0f, 0f, 1f);

        Cubo.dibujar(
                0f, 0f, 0.18f,
                0.12f, 0.035f, 0.42f,
                0.12f, 0.55f, 0.18f);

        Cubo.dibujar(
                0f, 0f, 0.34f,
                0.18f, 0.03f, 0.24f,
                0.18f, 0.70f, 0.24f);

        glPopMatrix();
    }

    // ==========================================================
    // TAPETE DECORATIVO DEL BALCÓN
    // ==========================================================
    private static void dibujarTapeteBalcon(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo) {

        dibujarCuboPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y + 0.01f,
                0.03f,
                0.42f, 0.42f, 0.46f);

        dibujarCuboPorGeo(
                xMinGeo + 0.12f, zMinGeo + 0.10f,
                xMaxGeo - 0.12f, zMaxGeo - 0.10f,
                Y + 0.045f,
                0.015f,
                0.62f, 0.62f, 0.66f);
    }

    // ==========================================================
    // BARANDAL DEL BALCÓN
    // J -> I -> I1 -> H1
    // ==========================================================
    private static void dibujarBarandalBalcon() {
        // Segmento J -> I: (0.1, 1.6) a (0.1, 0.2)
        dibujarTramoBarandal(0.1f, 1.6f, 0.1f, 0.2f);

        // Segmento I -> I1: (0.1, 0.2) a (4.8, 0.2)
        dibujarTramoBarandal(0.1f, 0.2f, 4.8f, 0.2f);

        // Segmento I1 -> H1: (4.8, 0.2) a (4.8, 1.6)
        dibujarTramoBarandal(4.8f, 0.2f, 4.8f, 1.6f);
    }

    private static void dibujarTramoBarandal(float x1Geo, float z1Geo, float x2Geo, float z2Geo) {
        float minX = Math.min(x1Geo, x2Geo);
        float maxX = Math.max(x1Geo, x2Geo);
        float minZ = Math.min(z1Geo, z2Geo);
        float maxZ = Math.max(z1Geo, z2Geo);

        boolean esVertical = (Math.abs(maxX - minX) < 0.01f);

        float grosorMuro = 0.08f;
        float grosorCristal = 0.02f;
        float grosorPasamanos = 0.10f;

        // Muro Inferior
        float muroMinX = esVertical ? minX - grosorMuro / 2f : minX;
        float muroMaxX = esVertical ? maxX + grosorMuro / 2f : maxX;
        float muroMinZ = !esVertical ? minZ - grosorMuro / 2f : minZ;
        float muroMaxZ = !esVertical ? maxZ + grosorMuro / 2f : maxZ;

        dibujarCuboPorGeo(muroMinX, muroMinZ, muroMaxX, muroMaxZ, Y, 0.50f, 0.90f, 0.90f, 0.92f); // Pared abajo

        // Cristal / Hueco del barandal
        float cristalMinX = esVertical ? minX - grosorCristal / 2f : minX;
        float cristalMaxX = esVertical ? maxX + grosorCristal / 2f : maxX;
        float cristalMinZ = !esVertical ? minZ - grosorCristal / 2f : minZ;
        float cristalMaxZ = !esVertical ? maxZ + grosorCristal / 2f : maxZ;

        dibujarCuboPorGeo(cristalMinX, cristalMinZ, cristalMaxX, cristalMaxZ, Y + 0.50f, 0.60f, 0.55f, 0.75f, 0.85f); // Cristal
                                                                                                                      // translúcido

        // Pasamanos Superior
        float pasaMinX = esVertical ? minX - grosorPasamanos / 2f : minX;
        float pasaMaxX = esVertical ? maxX + grosorPasamanos / 2f : maxX;
        float pasaMinZ = !esVertical ? minZ - grosorPasamanos / 2f : minZ;
        float pasaMaxZ = !esVertical ? maxZ + grosorPasamanos / 2f : maxZ;

        dibujarCuboPorGeo(pasaMinX, pasaMinZ, pasaMaxX, pasaMaxZ, Y + 1.10f, 0.06f, 0.12f, 0.12f, 0.12f); // Barandal
                                                                                                          // negro
                                                                                                          // metálico

        // Postes metálicos de soporte cada ~0.8m
        float largoGeo = (float) Math.sqrt(Math.pow(maxX - minX, 2) + Math.pow(maxZ - minZ, 2));
        int numPostes = (int) (largoGeo / 0.8f);
        if (numPostes < 1)
            numPostes = 1;

        for (int i = 0; i <= numPostes; i++) {
            float frac = (float) i / numPostes;
            float px = minX + (maxX - minX) * frac;
            float pz = minZ + (maxZ - minZ) * frac;

            dibujarCuboPorGeo(px - 0.02f, pz - 0.02f, px + 0.02f, pz + 0.02f, Y + 0.50f, 0.60f, 0.10f, 0.10f, 0.10f);
        }
    }
}