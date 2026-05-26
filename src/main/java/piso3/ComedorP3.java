package piso3;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class ComedorP3 {

    private static final float Y = Constantes.ALTURA_PISO_3;

    public static int texturaMadera = 0;

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
         * COMEDOR / ÁREA SOCIAL - PISO 3
         *
         * Mesa/jardinera con macetas:
         * Q2 = (4.9, 8.6)
         * R2 = (7.8, 8.6)
         * S2 = (7.8, 9.4)
         * T2 = (4.9, 9.4)
         *
         * Mesa de comedor:
         * U2 = (4.8, 7.6)
         * V2 = (4.8, 6.4)
         * W2 = (6.9, 6.4)
         * Z2 = (6.9, 7.6)
         *
         * Chimenea / horno:
         * A3 = (7.2, 7.7)
         * B3 = (7.8, 7.7)
         * C3 = (7.2, 7.1)
         * D3 = (7.8, 7.1)
         *
         * Barra / mesa conectada:
         * C3 = (7.2, 7.1)
         * D3 = (7.8, 7.1)
         * E3 = (7.2, 6.4)
         * F3 = (7.8, 6.4)
         *
         * Pérgola superior:
         * Se toma como área aproximada:
         * G3 = (3.8, 4.2)
         * I = (7.9, 4.2)
         * A1 = (3.8, 9.5)
         * G = (7.9, 9.5)
         */

        dibujarPergolaComedor(3.8f, 4.2f, 7.9f, 9.5f);

        dibujarMesaJardineraConMacetas(4.9f, 8.6f, 7.8f, 9.4f, 0f);

        dibujarMesaComedor(4.8f, 6.4f, 6.9f, 7.6f, 0f);
        dibujarSillasComedor();

        dibujarChimeneaHorno(7.2f, 7.1f, 7.8f, 7.7f, 0f);

        dibujarBarraAuxiliar(7.2f, 6.4f, 7.8f, 7.1f, 0f);

        dibujarTapeteComedor(4.45f, 6.15f, 7.15f, 7.85f);
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
    // MESA / JARDINERA CON 3 MACETAS
    // Entre Q2, R2, S2, T2
    // ==========================================================
    private static void dibujarMesaJardineraConMacetas(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float rotacionY) {

        float centroX = (xMinGeo + xMaxGeo) / 2f;
        float centroZ = (zMinGeo + zMaxGeo) / 2f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float ancho = escalar(xMaxGeo - xMinGeo);
        float largo = escalar(zMaxGeo - zMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Barra larga blanca / base de jardinera
        Cubo.dibujar(
                0f, 0.38f, 0f,
                ancho, 0.76f, largo,
                0.88f, 0.88f, 0.84f);

        // Cubierta superior tipo piedra
        Cubo.dibujar(
                0f, 0.80f, 0f,
                ancho * 1.03f, 0.08f, largo * 1.04f,
                0.62f, 0.56f, 0.50f);

        // Frente decorativo
        Cubo.dibujar(
                0f, 0.28f, largo / 2f + 0.02f,
                ancho * 0.94f, 0.12f, 0.04f,
                0.48f, 0.34f, 0.22f);

        glPopMatrix();

        // Tres macetas encima
        dibujarMacetaAlta(5.45f, 9.0f, 0f);
        dibujarMacetaAlta(6.35f, 9.0f, 0f);
        dibujarMacetaAlta(7.25f, 9.0f, 0f);
    }

    private static void dibujarMacetaAlta(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y + 0.82f, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Maceta negra moderna
        Cubo.dibujar(
                0f, 0.28f, 0f,
                escalar(0.34f), 0.56f, escalar(0.34f),
                0.05f, 0.05f, 0.05f);

        // Borde superior
        Cubo.dibujar(
                0f, 0.58f, 0f,
                escalar(0.40f), 0.08f, escalar(0.40f),
                0.12f, 0.12f, 0.12f);

        // Tierra
        Cubo.dibujar(
                0f, 0.64f, 0f,
                escalar(0.30f), 0.04f, escalar(0.30f),
                0.10f, 0.07f, 0.04f);

        // Tallos
        Cubo.dibujar(0f, 0.98f, 0f, 0.04f, 0.70f, 0.04f,
                0.15f, 0.35f, 0.12f);
        Cubo.dibujar(-0.08f, 0.94f, 0.04f, 0.035f, 0.58f, 0.035f,
                0.15f, 0.35f, 0.12f);
        Cubo.dibujar(0.08f, 0.94f, -0.04f, 0.035f, 0.58f, 0.035f,
                0.15f, 0.35f, 0.12f);

        // Hojas grandes
        dibujarHojaLocal(0f, 1.38f, 0f, 0f);
        dibujarHojaLocal(0f, 1.32f, 0f, 45f);
        dibujarHojaLocal(0f, 1.36f, 0f, 90f);
        dibujarHojaLocal(0f, 1.30f, 0f, 135f);
        dibujarHojaLocal(0f, 1.38f, 0f, 180f);
        dibujarHojaLocal(0f, 1.32f, 0f, 225f);
        dibujarHojaLocal(0f, 1.36f, 0f, 270f);
        dibujarHojaLocal(0f, 1.30f, 0f, 315f);

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
    // MESA PRINCIPAL DE COMEDOR
    // Entre U2, V2, W2, Z2
    // ==========================================================
    private static void dibujarMesaComedor(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float rotacionY) {

        float centroX = (xMinGeo + xMaxGeo) / 2f;
        float centroZ = (zMinGeo + zMaxGeo) / 2f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float ancho = escalar(xMaxGeo - xMinGeo);
        float largo = escalar(zMaxGeo - zMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Cubierta grande tipo piedra
        Cubo.dibujar(
                0f, 0.78f, 0f,
                ancho, 0.10f, largo,
                0.72f, 0.68f, 0.62f);

        // Base central negra
        Cubo.dibujar(
                0f, 0.38f, 0f,
                ancho * 0.16f, 0.74f, largo * 0.56f,
                0.05f, 0.05f, 0.05f);

        // Patas inclinadas simuladas
        Cubo.dibujar(
                -ancho * 0.25f, 0.36f, 0f,
                0.08f, 0.70f, 0.08f,
                0.05f, 0.05f, 0.05f);

        Cubo.dibujar(
                ancho * 0.25f, 0.36f, 0f,
                0.08f, 0.70f, 0.08f,
                0.05f, 0.05f, 0.05f);

        // Decoración: charola/frutero
        Cubo.dibujar(
                0f, 0.90f, 0f,
                ancho * 0.34f, 0.06f, largo * 0.22f,
                0.28f, 0.22f, 0.12f);

        // Frutas sobre la mesa
        Cubo.dibujar(-ancho * 0.08f, 0.98f, 0f, 0.10f, 0.10f, 0.10f,
                0.80f, 0.80f, 0.12f);
        Cubo.dibujar(0.00f, 1.00f, 0.04f, 0.10f, 0.10f, 0.10f,
                0.50f, 0.80f, 0.20f);
        Cubo.dibujar(ancho * 0.08f, 0.98f, -0.02f, 0.10f, 0.10f, 0.10f,
                0.85f, 0.55f, 0.12f);

        glPopMatrix();
    }

    // ==========================================================
    // SILLAS ALREDEDOR DE LA MESA
    // ==========================================================
    private static void dibujarSillasComedor() {
        // Lado inferior
        dibujarSillaComedor(5.15f, 6.05f, 0f);
        dibujarSillaComedor(5.85f, 6.05f, 0f);
        dibujarSillaComedor(6.55f, 6.05f, 0f);

        // Lado superior
        dibujarSillaComedor(5.15f, 7.95f, 180f);
        dibujarSillaComedor(5.85f, 7.95f, 180f);
        dibujarSillaComedor(6.55f, 7.95f, 180f);

        // Laterales
        // dibujarSillaComedor(4.40f, 7.00f, -90f);
        // dibujarSillaComedor(7.25f, 7.00f, 90f);
    }

    private static void dibujarSillaComedor(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        float ancho = escalar(0.42f);
        float fondo = escalar(0.42f);

        // Asiento madera
        Cubo.dibujar(
                0f, 0.40f, 0f,
                ancho, 0.12f, fondo,
                0.42f, 0.25f, 0.12f);

        // Cojín
        Cubo.dibujar(
                0f, 0.50f, 0f,
                ancho * 0.86f, 0.08f, fondo * 0.86f,
                0.62f, 0.68f, 0.72f);

        // Respaldo
        Cubo.dibujar(
                0f, 0.88f, -fondo / 2f + 0.05f,
                ancho, 0.75f, 0.08f,
                0.42f, 0.25f, 0.12f);

        // Brazos
        Cubo.dibujar(-ancho / 2f, 0.60f, 0f, 0.06f, 0.35f, fondo,
                0.42f, 0.25f, 0.12f);
        Cubo.dibujar(ancho / 2f, 0.60f, 0f, 0.06f, 0.35f, fondo,
                0.42f, 0.25f, 0.12f);

        // Patas
        Cubo.dibujar(-ancho * 0.35f, 0.18f, -fondo * 0.35f, 0.045f, 0.36f, 0.045f,
                0.18f, 0.12f, 0.07f);
        Cubo.dibujar(ancho * 0.35f, 0.18f, -fondo * 0.35f, 0.045f, 0.36f, 0.045f,
                0.18f, 0.12f, 0.07f);
        Cubo.dibujar(-ancho * 0.35f, 0.18f, fondo * 0.35f, 0.045f, 0.36f, 0.045f,
                0.18f, 0.12f, 0.07f);
        Cubo.dibujar(ancho * 0.35f, 0.18f, fondo * 0.35f, 0.045f, 0.36f, 0.045f,
                0.18f, 0.12f, 0.07f);

        glPopMatrix();
    }

    // ==========================================================
    // CHIMENEA / HORNO DE PIZZA
    // Entre A3, B3, C3, D3
    // ==========================================================
    private static void dibujarChimeneaHorno(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float rotacionY) {

        float centroX = (xMinGeo + xMaxGeo) / 2f;
        float centroZ = (zMinGeo + zMaxGeo) / 2f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float ancho = escalar(xMaxGeo - xMinGeo);
        float largo = escalar(zMaxGeo - zMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Base blanca
        Cubo.dibujar(
                0f, 0.65f, 0f,
                ancho, 1.30f, largo,
                0.88f, 0.88f, 0.84f);

        // Cámara del horno
        Cubo.dibujar(
                0f, 0.90f, largo / 2f + 0.025f,
                ancho * 0.76f, 0.58f, 0.05f,
                0.18f, 0.12f, 0.08f);

        // Interior negro del horno
        Cubo.dibujar(
                0f, 0.88f, largo / 2f + 0.055f,
                ancho * 0.58f, 0.42f, 0.04f,
                0.04f, 0.03f, 0.025f);

        // Ladrillos visibles
        for (int i = 0; i < 4; i++) {
            Cubo.dibujar(
                    -ancho * 0.30f + i * ancho * 0.20f,
                    1.18f,
                    largo / 2f + 0.08f,
                    ancho * 0.16f, 0.06f, 0.035f,
                    0.50f, 0.24f, 0.12f);
        }

        for (int i = 0; i < 3; i++) {
            Cubo.dibujar(
                    -ancho * 0.22f + i * ancho * 0.22f,
                    0.62f,
                    largo / 2f + 0.08f,
                    ancho * 0.18f, 0.06f, 0.035f,
                    0.50f, 0.24f, 0.12f);
        }

        // Campana superior blanca
        Cubo.dibujar(
                0f, 1.65f, 0f,
                ancho * 0.78f, 0.75f, largo * 0.78f,
                0.92f, 0.92f, 0.88f);

        // Chimenea vertical
        Cubo.dibujar(
                0f, 2.35f, 0f,
                ancho * 0.45f, 1.00f, largo * 0.45f,
                0.86f, 0.86f, 0.82f);

        // Fuego simulado
        Cubo.dibujar(
                -ancho * 0.12f, 0.72f, largo / 2f + 0.08f,
                0.10f, 0.22f, 0.035f,
                0.90f, 0.25f, 0.05f);

        Cubo.dibujar(
                ancho * 0.05f, 0.75f, largo / 2f + 0.08f,
                0.10f, 0.28f, 0.035f,
                0.95f, 0.65f, 0.05f);

        glPopMatrix();
    }

    // ==========================================================
    // BARRA / MESA AUXILIAR
    // Entre C3, D3, E3, F3
    // ==========================================================
    private static void dibujarBarraAuxiliar(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float rotacionY) {

        float centroX = (xMinGeo + xMaxGeo) / 2f;
        float centroZ = (zMinGeo + zMaxGeo) / 2f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float ancho = escalar(xMaxGeo - xMinGeo);
        float largo = escalar(zMaxGeo - zMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Mueble/barra
        Cubo.dibujar(
                0f, 0.42f, 0f,
                ancho, 0.84f, largo,
                0.56f, 0.44f, 0.34f);

        // Cubierta tipo piedra
        Cubo.dibujar(
                0f, 0.88f, 0f,
                ancho * 1.08f, 0.08f, largo * 1.04f,
                0.62f, 0.56f, 0.50f);

        // Tarja pequeña / fregadero
        Cubo.dibujar(
                0f, 0.94f, -largo * 0.20f,
                ancho * 0.55f, 0.06f, largo * 0.22f,
                0.35f, 0.42f, 0.44f);

        // Grifo
        Cubo.dibujar(
                0f, 1.12f, -largo * 0.32f,
                0.04f, 0.28f, 0.04f,
                0.65f, 0.65f, 0.65f);

        Cubo.dibujar(
                0f, 1.24f, -largo * 0.15f,
                0.04f, 0.04f, largo * 0.28f,
                0.65f, 0.65f, 0.65f);

        // Detalles: platos / botellas
        Cubo.dibujar(
                -ancho * 0.25f, 0.98f, largo * 0.20f,
                0.12f, 0.08f, 0.12f,
                0.90f, 0.90f, 0.84f);

        Cubo.dibujar(
                ancho * 0.25f, 1.05f, largo * 0.18f,
                0.08f, 0.26f, 0.08f,
                0.12f, 0.38f, 0.18f);

        glPopMatrix();
    }

    // ==========================================================
    // PÉRGOLA SUPERIOR
    // Área aproximada: G3 - I - A1 - G
    // ==========================================================
    private static void dibujarPergolaComedor(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float x1 = convertirXGeoAOpenGL(xMinGeo);
        float x2 = convertirXGeoAOpenGL(xMaxGeo);
        float z1 = convertirZGeoAOpenGL(zMinGeo);
        float z2 = convertirZGeoAOpenGL(zMaxGeo);

        float altoPoste = 2.95f;
        float grosorPoste = 0.11f;

        // Postes principales
        if (texturaMadera != 0) {
            Cubo.dibujarConTextura(x1, Y + altoPoste / 2f, z1, grosorPoste, altoPoste, grosorPoste, texturaMadera);
            Cubo.dibujarConTextura(x2, Y + altoPoste / 2f, z1, grosorPoste, altoPoste, grosorPoste, texturaMadera);
            Cubo.dibujarConTextura(x1, Y + altoPoste / 2f, z2, grosorPoste, altoPoste, grosorPoste, texturaMadera);
            Cubo.dibujarConTextura(x2, Y + altoPoste / 2f, z2, grosorPoste, altoPoste, grosorPoste, texturaMadera);
        } else {
            Cubo.dibujar(x1, Y + altoPoste / 2f, z1, grosorPoste, altoPoste, grosorPoste, 0.42f, 0.24f, 0.10f);
            Cubo.dibujar(x2, Y + altoPoste / 2f, z1, grosorPoste, altoPoste, grosorPoste, 0.42f, 0.24f, 0.10f);
            Cubo.dibujar(x1, Y + altoPoste / 2f, z2, grosorPoste, altoPoste, grosorPoste, 0.42f, 0.24f, 0.10f);
            Cubo.dibujar(x2, Y + altoPoste / 2f, z2, grosorPoste, altoPoste, grosorPoste, 0.42f, 0.24f, 0.10f);
        }

        // Vigas perimetrales
        dibujarVigaEntrePuntos(xMinGeo, zMinGeo, xMaxGeo, zMinGeo, 2.85f, 0.12f, 0.12f);
        dibujarVigaEntrePuntos(xMinGeo, zMaxGeo, xMaxGeo, zMaxGeo, 2.85f, 0.12f, 0.12f);
        dibujarVigaEntrePuntos(xMinGeo, zMinGeo, xMinGeo, zMaxGeo, 2.85f, 0.12f, 0.12f);
        dibujarVigaEntrePuntos(xMaxGeo, zMinGeo, xMaxGeo, zMaxGeo, 2.85f, 0.12f, 0.12f);

        // Palos superiores repetidos como techo
        int numVigas = 11;
        for (int i = 0; i < numVigas; i++) {
            float t = (float) i / (numVigas - 1);
            float xGeo = xMinGeo + t * (xMaxGeo - xMinGeo);

            dibujarVigaEntrePuntos(
                    xGeo, zMinGeo,
                    xGeo, zMaxGeo,
                    3.05f,
                    0.08f,
                    0.10f);
        }

        // Vigas cruzadas para más detalle
        dibujarVigaEntrePuntos(xMinGeo + 0.15f, zMinGeo + 0.80f, xMaxGeo - 0.15f, zMinGeo + 0.80f, 2.68f, 0.06f, 0.08f);
        dibujarVigaEntrePuntos(xMinGeo + 0.15f, zMinGeo + 1.80f, xMaxGeo - 0.15f, zMinGeo + 1.80f, 2.68f, 0.06f, 0.08f);
        dibujarVigaEntrePuntos(xMinGeo + 0.15f, zMinGeo + 2.80f, xMaxGeo - 0.15f, zMinGeo + 2.80f, 2.68f, 0.06f, 0.08f);
        dibujarVigaEntrePuntos(xMinGeo + 0.15f, zMinGeo + 3.80f, xMaxGeo - 0.15f, zMinGeo + 3.80f, 2.68f, 0.06f, 0.08f);
    }

    private static void dibujarVigaEntrePuntos(
            float x1Geo, float z1Geo,
            float x2Geo, float z2Geo,
            float altura,
            float grosorY,
            float grosorZ) {

        float centroXGeo = (x1Geo + x2Geo) / 2f;
        float centroZGeo = (z1Geo + z2Geo) / 2f;

        float dx = x2Geo - x1Geo;
        float dz = z2Geo - z1Geo;

        float largoGeo = (float) Math.sqrt(dx * dx + dz * dz);
        float angulo = (float) Math.toDegrees(Math.atan2(dz, dx));

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        glPushMatrix();
        glTranslatef(x, Y + altura, z);
        glRotatef(-angulo, 0f, 1f, 0f);

        if (texturaMadera != 0) {
            Cubo.dibujarConTextura(0f, 0f, 0f, escalar(largoGeo), grosorY, grosorZ, texturaMadera);
        } else {
            Cubo.dibujar(
                    0f, 0f, 0f,
                    escalar(largoGeo),
                    grosorY,
                    grosorZ,
                    0.46f, 0.27f, 0.12f);
        }

        glPopMatrix();
    }

    // ==========================================================
    // TAPETE
    // ==========================================================
    private static void dibujarTapeteComedor(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo) {

        dibujarCuboPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y + 0.01f,
                0.035f,
                0.50f, 0.50f, 0.54f);

        dibujarCuboPorGeo(
                xMinGeo + 0.15f, zMinGeo + 0.12f,
                xMaxGeo - 0.15f, zMaxGeo - 0.12f,
                Y + 0.045f,
                0.018f,
                0.68f, 0.68f, 0.72f);
    }
} 


