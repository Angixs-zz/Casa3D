package piso3;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class PergolaBancoP3 {

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
         * BANCO CON PÉRGOLA - PISO 3
         *
         * Banco:
         * I2 = (0.2, 4.3)
         * J2 = (0.2, 3.9)
         * K2 = (2.8, 3.9)
         * L2 = (2.8, 4.3)
         *
         * Estructura / pérgola:
         * M2 = (0.2, 4.4)
         * N2 = (0.2, 3.0)
         * O2 = (2.9, 3.0)
         * P2 = (2.9, 4.4)
         */

        dibujarBaseJardinera(0.2f, 3.9f, 2.2f, 4.3f);
        dibujarBanco(0.2f, 3.9f, 2.2f, 4.3f);

        dibujarPergola(0.2f, 3.0f, 2.3f, 4.4f);

        dibujarPanelListonesFondo(0.25f, 4.25f, 2.25f, 4.35f);
        dibujarPanelListonesLateral(2.15f, 3.1f, 2.25f, 4.25f);

        dibujarJardinerasLaterales();
        dibujarPlantasDecorativas();
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
    // BASE / JARDINERA BAJA
    // ==========================================================
    private static void dibujarBaseJardinera(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        // Base blanca donde va montado el banco
        dibujarCuboPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y,
                0.45f,
                0.88f, 0.88f, 0.84f);

        // Tapa superior clara
        dibujarCuboPorGeo(
                xMinGeo - 0.03f, zMinGeo - 0.03f,
                xMaxGeo + 0.03f, zMaxGeo + 0.03f,
                Y + 0.45f,
                0.06f,
                0.96f, 0.96f, 0.92f);

        // Frente decorativo de la base
        dibujarCuboPorGeo(
                xMinGeo + 0.10f, zMinGeo - 0.04f,
                xMaxGeo - 0.10f, zMinGeo + 0.02f,
                Y + 0.12f,
                0.12f,
                0.70f, 0.70f, 0.66f);
    }

    // ==========================================================
    // BANCO DE MADERA
    // Entre I2, J2, K2, L2
    // ==========================================================
    private static void dibujarBanco(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroX = (xMinGeo + xMaxGeo) / 2f;
        float centroZ = (zMinGeo + zMaxGeo) / 2f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float ancho = escalar(xMaxGeo - xMinGeo);
        float fondo = escalar(zMaxGeo - zMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Asiento de madera
        if (texturaMadera != 0) {
            Cubo.dibujarConTextura(0f, 0.62f, 0f, ancho * 0.92f, 0.12f, fondo * 0.95f, texturaMadera);
        } else {
            Cubo.dibujar(0f, 0.62f, 0f, ancho * 0.92f, 0.12f, fondo * 0.95f, 0.46f, 0.28f, 0.13f);
        }

        // Listones del asiento
        for (int i = -3; i <= 3; i++) {
            if (texturaMadera != 0) {
                Cubo.dibujarConTextura(i * (ancho * 0.12f), 0.70f, 0f, ancho * 0.08f, 0.04f, fondo * 0.96f, texturaMadera);
            } else {
                Cubo.dibujar(i * (ancho * 0.12f), 0.70f, 0f, ancho * 0.08f, 0.04f, fondo * 0.96f, 0.58f, 0.34f, 0.16f);
            }
        }

        // Respaldo de madera
        if (texturaMadera != 0) {
            Cubo.dibujarConTextura(0f, 1.03f, fondo / 2f + 0.05f, ancho * 0.92f, 0.75f, 0.10f, texturaMadera);
        } else {
            Cubo.dibujar(0f, 1.03f, fondo / 2f + 0.05f, ancho * 0.92f, 0.75f, 0.10f, 0.42f, 0.25f, 0.12f);
        }

        // Listones horizontales del respaldo
        for (int i = 0; i < 5; i++) {
            if (texturaMadera != 0) {
                Cubo.dibujarConTextura(0f, 0.78f + i * 0.15f, fondo / 2f + 0.12f, ancho * 0.88f, 0.055f, 0.05f, texturaMadera);
            } else {
                Cubo.dibujar(0f, 0.78f + i * 0.15f, fondo / 2f + 0.12f, ancho * 0.88f, 0.055f, 0.05f, 0.56f, 0.33f, 0.15f);
            }
        }

        // Cojín largo del asiento
        Cubo.dibujar(
                0f, 0.78f, -fondo * 0.08f,
                ancho * 0.82f, 0.12f, fondo * 0.65f,
                0.86f, 0.84f, 0.78f);

        // Almohadas
        dibujarAlmohadaLocal(-ancho * 0.28f, 1.04f, fondo * 0.18f);
        dibujarAlmohadaLocal(0f, 1.04f, fondo * 0.18f);
        dibujarAlmohadaLocal(ancho * 0.28f, 1.04f, fondo * 0.18f);

        glPopMatrix();
    }

    private static void dibujarAlmohadaLocal(float x, float y, float z) {
        Cubo.dibujar(
                x, y, z,
                0.45f, 0.24f, 0.16f,
                0.92f, 0.90f, 0.84f);
    }

    // ==========================================================
    // PÉRGOLA / ESTRUCTURA DE SOMBRA
    // Entre M2, N2, O2, P2
    // ==========================================================
    private static void dibujarPergola(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float x1 = convertirXGeoAOpenGL(xMinGeo);
        float x2 = convertirXGeoAOpenGL(xMaxGeo);
        float z1 = convertirZGeoAOpenGL(zMinGeo);
        float z2 = convertirZGeoAOpenGL(zMaxGeo);

        float altoPoste = 2.75f;
        float grosorPoste = 0.11f;

        // Postes principales en las 4 esquinas
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

        // Vigas perimetrales superiores
        dibujarVigaEntrePuntos(xMinGeo, zMinGeo, xMaxGeo, zMinGeo, 2.70f, 0.12f, 0.12f);
        dibujarVigaEntrePuntos(xMinGeo, zMaxGeo, xMaxGeo, zMaxGeo, 2.70f, 0.12f, 0.12f);
        dibujarVigaEntrePuntos(xMinGeo, zMinGeo, xMinGeo, zMaxGeo, 2.70f, 0.12f, 0.12f);
        dibujarVigaEntrePuntos(xMaxGeo, zMinGeo, xMaxGeo, zMaxGeo, 2.70f, 0.12f, 0.12f);

        // Vigas superiores repetidas tipo pérgola
        int numVigas = 7;
        for (int i = 0; i < numVigas; i++) {
            float t = (float) i / (numVigas - 1);
            float xGeo = xMinGeo + t * (xMaxGeo - xMinGeo);

            dibujarVigaEntrePuntos(
                    xGeo, zMinGeo,
                    xGeo, zMaxGeo,
                    2.92f,
                    0.08f,
                    0.10f);
        }

        // Algunas vigas transversales delgadas para dar más detalle
        dibujarVigaEntrePuntos(xMinGeo + 0.15f, zMinGeo + 0.35f, xMaxGeo - 0.15f, zMinGeo + 0.35f, 2.55f, 0.06f, 0.08f);
        dibujarVigaEntrePuntos(xMinGeo + 0.15f, zMaxGeo - 0.35f, xMaxGeo - 0.15f, zMaxGeo - 0.35f, 2.55f, 0.06f, 0.08f);
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
            Cubo.dibujar(0f, 0f, 0f, escalar(largoGeo), grosorY, grosorZ, 0.46f, 0.27f, 0.12f);
        }

        glPopMatrix();
    }

    // ==========================================================
    // PANEL DE LISTONES DEL FONDO
    // ==========================================================
    private static void dibujarPanelListonesFondo(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroX = (xMinGeo + xMaxGeo) / 2f;
        float centroZ = (zMinGeo + zMaxGeo) / 2f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float ancho = escalar(xMaxGeo - xMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Postes verticales del panel
        Cubo.dibujar(-ancho / 2f, 1.35f, 0f, 0.08f, 2.35f, 0.08f,
                0.36f, 0.20f, 0.09f);
        Cubo.dibujar(0f, 1.35f, 0f, 0.08f, 2.35f, 0.08f,
                0.36f, 0.20f, 0.09f);
        Cubo.dibujar(ancho / 2f, 1.35f, 0f, 0.08f, 2.35f, 0.08f,
                0.36f, 0.20f, 0.09f);

        // Listones horizontales
        for (int i = 0; i < 13; i++) {
            float y = 0.55f + i * 0.14f;

            Cubo.dibujar(
                    0f, y, 0f,
                    ancho, 0.045f, 0.055f,
                    0.50f, 0.30f, 0.14f);
        }

        glPopMatrix();
    }

    // ==========================================================
    // PANEL DE LISTONES LATERAL DERECHO
    // ==========================================================
    private static void dibujarPanelListonesLateral(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroX = (xMinGeo + xMaxGeo) / 2f;
        float centroZ = (zMinGeo + zMaxGeo) / 2f;

        float x = convertirXGeoAOpenGL(centroX);
        float z = convertirZGeoAOpenGL(centroZ);

        float largo = escalar(zMaxGeo - zMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Postes
        Cubo.dibujar(0f, 1.35f, -largo / 2f, 0.08f, 2.30f, 0.08f,
                0.36f, 0.20f, 0.09f);
        Cubo.dibujar(0f, 1.35f, 0f, 0.08f, 2.30f, 0.08f,
                0.36f, 0.20f, 0.09f);
        Cubo.dibujar(0f, 1.35f, largo / 2f, 0.08f, 2.30f, 0.08f,
                0.36f, 0.20f, 0.09f);

        // Listones horizontales laterales
        for (int i = 0; i < 12; i++) {
            float y = 0.60f + i * 0.14f;

            Cubo.dibujar(
                    0f, y, 0f,
                    0.055f, 0.045f, largo,
                    0.50f, 0.30f, 0.14f);
        }

        glPopMatrix();
    }

    // ==========================================================
    // JARDINERAS Y PLANTAS
    // ==========================================================
    private static void dibujarJardinerasLaterales() {
        // Jardinera izquierda
        dibujarCuboPorGeo(
                0.25f, 4.02f,
                0.55f, 4.28f,
                Y + 0.46f,
                0.28f,
                0.88f, 0.88f, 0.84f);

        // Jardinera derecha
        dibujarCuboPorGeo(
                1.85f, 4.02f,
                2.15f, 4.28f,
                Y + 0.46f,
                0.28f,
                0.88f, 0.88f, 0.84f);
    }

    private static void dibujarPlantasDecorativas() {
        dibujarPlantaPequena(0.40f, 4.15f);
        dibujarPlantaPequena(2.00f, 4.15f);
    }

    private static void dibujarPlantaPequena(float xGeo, float zGeo) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y + 0.75f, z);

        // Tierra
        Cubo.dibujar(
                0f, 0.04f, 0f,
                escalar(0.20f), 0.05f, escalar(0.16f),
                0.12f, 0.08f, 0.04f);

        // Hojas
        Cubo.dibujar(
                0f, 0.22f, 0f,
                escalar(0.22f), 0.28f, escalar(0.12f),
                0.12f, 0.50f, 0.16f);

        Cubo.dibujar(
                0f, 0.24f, 0f,
                escalar(0.12f), 0.30f, escalar(0.22f),
                0.10f, 0.58f, 0.18f);

        Cubo.dibujar(
                0.05f, 0.36f, 0.04f,
                escalar(0.14f), 0.22f, escalar(0.10f),
                0.18f, 0.66f, 0.22f);

        glPopMatrix();
    }
}

 


