package piso3;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class TerrazaP3 {

    private static final float Y = Constantes.ALTURA_PISO_3;

    public static int texturaMadera = 0;
    public static int texturaSofa = 0;
    public static int texturaCojin = 0;
    public static int texturaAlmohada = 0;
    public static int texturaHojas = 0;
    public static int texturaTapete = 0;
    public static int texturaCesped = 0;

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
         * TERRAZA / SALA EXTERIOR - PISO 3
         *
         * Sofá exterior:
         * W1 = (0.2, 5.6)
         * Z1 = (1.2, 5.6)
         * A2 = (1.2, 6.1)
         * B2 = (3.0, 6.1)
         * C2 = (3.0, 6.9)
         * D2 = (0.2, 6.9)
         *
         * Mesa:
         * E2 = (1.7, 5.9)
         * F2 = (1.7, 5.5)
         * G2 = (2.5, 5.5)
         * H2 = (2.5, 5.9)
         *
         * Sombrilla:
         * Cubre el espacio de la terraza, tomando como referencia
         * D2-C2, D2-I1 y J1-C2.
         *
         * Puntos del plano de casa:
         * P3_I1 = (0.1, 5.0)
         * P3_J1 = (2.8, 5.0)
         * Estos ya existen en Casa.java para el tercer piso.
         */

        dibujarSofaExterior();
        dibujarMesaExterior(1.7f, 5.5f, 2.5f, 5.9f, 0f);

        // Sombrilla grande tipo terraza
        dibujarSombrillaGrande();

        // Detalles extra para que se vea más como terraza
        dibujarTapeteExterior(0.55f, 5.35f, 2.95f, 6.45f);
        dibujarPlantaDecorativa(0.45f, 5.35f, 0f);
        dibujarPlantaDecorativa(2.85f, 6.55f, 0f);
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

    private static void dibujarCuboConTexturaPorGeo(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float yBase, float altura,
            int texturaID) {

        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo;
        float largoGeo = zMaxGeo - zMinGeo;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        Cubo.dibujarConTextura(
                x,
                yBase + altura / 2.0f,
                z,
                ancho,
                altura,
                largo,
                texturaID);
    }

    // ==========================================================
    // SOFÁ EXTERIOR EN L
    // De W1 a D2
    // ==========================================================
    private static void dibujarSofaExterior() {
        float altoAsiento = 0.35f;
        float altoRespaldo = 0.80f;

        // Parte larga del sofá: D2-C2-B2-A2
        if (texturaSofa != 0) {
            dibujarCuboConTexturaPorGeo(0.2f, 6.1f, 3.0f, 6.9f, Y, altoAsiento, texturaSofa);
        } else {
            dibujarCuboPorGeo(
                    0.2f, 6.1f,
                    3.0f, 6.9f,
                    Y,
                    altoAsiento,
                    0.86f, 0.84f, 0.78f);
        }

        // Parte chaise izquierda: W1-Z1-A2-D2
        if (texturaSofa != 0) {
            dibujarCuboConTexturaPorGeo(0.2f, 5.6f, 1.2f, 6.1f, Y, altoAsiento, texturaSofa);
        } else {
            dibujarCuboPorGeo(
                    0.2f, 5.6f,
                    1.2f, 6.1f,
                    Y,
                    altoAsiento,
                    0.88f, 0.86f, 0.80f);
        }

        // Respaldo largo pegado al segmento D2-C2
        if (texturaSofa != 0) {
            dibujarCuboConTexturaPorGeo(0.2f, 6.78f, 3.0f, 6.9f, Y + altoAsiento, altoRespaldo, texturaSofa);
        } else {
            dibujarCuboPorGeo(
                    0.2f, 6.78f,
                    3.0f, 6.9f,
                    Y + altoAsiento,
                    altoRespaldo,
                    0.76f, 0.74f, 0.68f);
        }

        // Respaldo lateral izquierdo pegado al segmento D2-W1
        if (texturaSofa != 0) {
            dibujarCuboConTexturaPorGeo(0.2f, 5.6f, 0.32f, 6.9f, Y + altoAsiento, altoRespaldo, texturaSofa);
        } else {
            dibujarCuboPorGeo(
                    0.2f, 5.6f,
                    0.32f, 6.9f,
                    Y + altoAsiento,
                    altoRespaldo,
                    0.76f, 0.74f, 0.68f);
        }

        // Brazo derecho del sofá cerca de B2-C2
        if (texturaSofa != 0) {
            dibujarCuboConTexturaPorGeo(2.88f, 6.1f, 3.0f, 6.9f, Y + altoAsiento, 0.60f, texturaSofa);
        } else {
            dibujarCuboPorGeo(
                    2.88f, 6.1f,
                    3.0f, 6.9f,
                    Y + altoAsiento,
                    0.60f,
                    0.76f, 0.74f, 0.68f);
        }

        // Cojines de respaldo
        dibujarCojin(0.65f, 6.55f, 0f);
        dibujarCojin(1.25f, 6.55f, 0f);
        dibujarCojin(1.85f, 6.55f, 0f);
        dibujarCojin(2.45f, 6.55f, 0f);

        // Cojines de asiento para la chaise
        dibujarCojinLargo(0.70f, 5.85f, 0f);

        // Almohadas decorativas
        dibujarAlmohadaDecorativa(0.45f, 6.55f, 0f);
        dibujarAlmohadaDecorativa(2.70f, 6.55f, 0f);
        dibujarAlmohadaDecorativa(0.75f, 5.85f, 0f);
    }

    private static void dibujarCojin(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        if (texturaCojin != 0) {
            Cubo.dibujarConTextura(
                    0f, 0.52f, 0f,
                    escalar(0.50f), 0.16f, escalar(0.36f),
                    texturaCojin);
        } else {
            Cubo.dibujar(
                    0f, 0.52f, 0f,
                    escalar(0.50f), 0.16f, escalar(0.36f),
                    0.94f, 0.92f, 0.86f);
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
            Cubo.dibujarConTextura(
                    0f, 0.52f, 0f,
                    escalar(0.55f), 0.16f, escalar(0.35f),
                    texturaCojin);
        } else {
            Cubo.dibujar(
                    0f, 0.52f, 0f,
                    escalar(0.55f), 0.16f, escalar(0.35f),
                    0.92f, 0.90f, 0.84f);
        }

        glPopMatrix();
    }

    private static void dibujarAlmohadaDecorativa(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        if (texturaAlmohada != 0) {
            Cubo.dibujarConTextura(
                    0f, 0.84f, 0f,
                    escalar(0.25f), 0.20f, escalar(0.20f),
                    texturaAlmohada);
        } else {
            Cubo.dibujar(
                    0f, 0.84f, 0f,
                    escalar(0.25f), 0.20f, escalar(0.20f),
                    0.78f, 0.76f, 0.70f);
        }

        glPopMatrix();
    }

    // ==========================================================
    // MESA EXTERIOR
    // Entre E2, F2, G2, H2
    // ==========================================================
    private static void dibujarMesaExterior(
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

        // Base de madera
        if (texturaMadera != 0) {
            Cubo.dibujarConTextura(0f, 0.18f, 0f, ancho * 0.92f, 0.36f, largo * 0.92f, texturaMadera);
        } else {
            Cubo.dibujar(
                    0f, 0.18f, 0f,
                    ancho * 0.92f, 0.36f, largo * 0.92f,
                    0.45f, 0.30f, 0.18f);
        }

        // Cubierta tipo cristal verde/azul
        Cubo.dibujar(
                0f, 0.44f, 0f,
                ancho * 1.05f, 0.06f, largo * 1.05f,
                0.28f, 0.62f, 0.62f);

        // Borde de la mesa
        if (texturaMadera != 0) {
            Cubo.dibujarConTextura(0f, 0.50f, -largo / 2f, ancho, 0.04f, 0.04f, texturaMadera);
            Cubo.dibujarConTextura(0f, 0.50f, largo / 2f, ancho, 0.04f, 0.04f, texturaMadera);
            Cubo.dibujarConTextura(-ancho / 2f, 0.50f, 0f, 0.04f, 0.04f, largo, texturaMadera);
            Cubo.dibujarConTextura(ancho / 2f, 0.50f, 0f, 0.04f, 0.04f, largo, texturaMadera);
        } else {
            Cubo.dibujar(
                    0f, 0.50f, -largo / 2f,
                    ancho, 0.04f, 0.04f,
                    0.18f, 0.18f, 0.18f);

            Cubo.dibujar(
                    0f, 0.50f, largo / 2f,
                    ancho, 0.04f, 0.04f,
                    0.18f, 0.18f, 0.18f);

            Cubo.dibujar(
                    -ancho / 2f, 0.50f, 0f,
                    0.04f, 0.04f, largo,
                    0.18f, 0.18f, 0.18f);

            Cubo.dibujar(
                    ancho / 2f, 0.50f, 0f,
                    0.04f, 0.04f, largo,
                    0.18f, 0.18f, 0.18f);
        }

        // Decoración sobre la mesa
        Cubo.dibujar(
                -ancho * 0.22f, 0.60f, 0f,
                0.12f, 0.18f, 0.12f,
                0.60f, 0.30f, 0.12f);

        Cubo.dibujar(
                ancho * 0.15f, 0.56f, 0f,
                0.24f, 0.05f, 0.16f,
                0.90f, 0.86f, 0.72f);

        glPopMatrix();
    }

    // ==========================================================
    // SOMBRILLA GRANDE / CANOPY
    // Cubre D2-C2, D2-I1 y J1-C2
    // ==========================================================
    private static void dibujarSombrillaGrande() {
        /*
         * Puntos de cobertura aproximados:
         * D2 = (0.2, 6.9)
         * C2 = (3.0, 6.9)
         * I1 = (0.1, 5.0)
         * J1 = (2.8, 5.0)
         *
         * Se dibuja una sombrilla grande elevada como en la referencia:
         * - poste negro
         * - brazo inclinado
         * - lona clara cubriendo casi toda la sala exterior
         */

        float posteXGeo = 2.85f;
        float posteZGeo = 5.80f;

        float xPoste = convertirXGeoAOpenGL(posteXGeo);
        float zPoste = convertirZGeoAOpenGL(posteZGeo);

        // Poste vertical negro
        Cubo.dibujar(
                xPoste,
                Y + 1.55f,
                zPoste,
                0.08f,
                3.10f,
                0.08f,
                0.04f, 0.04f, 0.04f);

        // Brazo inclinado simulado con varios cubos
        dibujarBrazoSombrilla(posteXGeo, posteZGeo, 1.72f, 6.25f);

        // Lona de la sombrilla
        dibujarLonaSombrilla();

        // Centro de unión
        Cubo.dibujar(
                xPoste,
                Y + 2.85f,
                zPoste,
                0.16f,
                0.16f,
                0.16f,
                0.05f, 0.05f, 0.05f);
    }

    private static void dibujarBrazoSombrilla(
            float x1Geo, float z1Geo,
            float x2Geo, float z2Geo) {

        float centroXGeo = (x1Geo + x2Geo) / 2.0f;
        float centroZGeo = (z1Geo + z2Geo) / 2.0f;

        float dx = x2Geo - x1Geo;
        float dz = z2Geo - z1Geo;

        float largoGeo = (float) Math.sqrt(dx * dx + dz * dz);
        float angulo = (float) Math.toDegrees(Math.atan2(dz, dx));

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        glPushMatrix();
        glTranslatef(x, Y + 2.65f, z);
        glRotatef(-angulo, 0f, 1f, 0f);
        glRotatef(-12f, 0f, 0f, 1f);

        Cubo.dibujar(
                0f, 0f, 0f,
                escalar(largoGeo),
                0.06f,
                0.06f,
                0.04f, 0.04f, 0.04f);

        glPopMatrix();
    }

    private static void dibujarLonaSombrilla() {
        /*
         * Lona con polígonos planos.
         * Está en Y + 2.75f para que quede por encima del sofá.
         */

        float yLona = Y + 2.75f;

        float xD2 = convertirXGeoAOpenGL(0.2f);
        float zD2 = convertirZGeoAOpenGL(6.9f);

        float xC2 = convertirXGeoAOpenGL(3.0f);
        float zC2 = convertirZGeoAOpenGL(6.9f);

        float xI1 = convertirXGeoAOpenGL(0.1f);
        float zI1 = convertirZGeoAOpenGL(5.0f);

        float xJ1 = convertirXGeoAOpenGL(2.8f);
        float zJ1 = convertirZGeoAOpenGL(5.0f);

        float xCentro = convertirXGeoAOpenGL(1.65f);
        float zCentro = convertirZGeoAOpenGL(6.05f);

        glPushMatrix();

        // Lona principal beige
        glColor3f(0.88f, 0.82f, 0.66f);
        glBegin(GL_TRIANGLES);

        // Triángulo D2 - C2 - centro
        glVertex3f(xD2, yLona, zD2);
        glVertex3f(xC2, yLona, zC2);
        glVertex3f(xCentro, yLona + 0.18f, zCentro);

        // Triángulo C2 - J1 - centro
        glVertex3f(xC2, yLona, zC2);
        glVertex3f(xJ1, yLona, zJ1);
        glVertex3f(xCentro, yLona + 0.18f, zCentro);

        // Triángulo J1 - I1 - centro
        glVertex3f(xJ1, yLona, zJ1);
        glVertex3f(xI1, yLona, zI1);
        glVertex3f(xCentro, yLona + 0.18f, zCentro);

        // Triángulo I1 - D2 - centro
        glVertex3f(xI1, yLona, zI1);
        glVertex3f(xD2, yLona, zD2);
        glVertex3f(xCentro, yLona + 0.18f, zCentro);

        glEnd();

        // Bordes negros de la lona
        dibujarBordeLona(0.2f, 6.9f, 3.0f, 6.9f, yLona);
        dibujarBordeLona(3.0f, 6.9f, 2.8f, 5.0f, yLona);
        dibujarBordeLona(2.8f, 5.0f, 0.1f, 5.0f, yLona);
        dibujarBordeLona(0.1f, 5.0f, 0.2f, 6.9f, yLona);

        // Varillas desde el centro hacia las esquinas
        dibujarVarillaLona(1.65f, 6.05f, 0.2f, 6.9f, yLona);
        dibujarVarillaLona(1.65f, 6.05f, 3.0f, 6.9f, yLona);
        dibujarVarillaLona(1.65f, 6.05f, 0.1f, 5.0f, yLona);
        dibujarVarillaLona(1.65f, 6.05f, 2.8f, 5.0f, yLona);

        glPopMatrix();
    }

    private static void dibujarBordeLona(
            float x1Geo, float z1Geo,
            float x2Geo, float z2Geo,
            float y) {

        float centroXGeo = (x1Geo + x2Geo) / 2.0f;
        float centroZGeo = (z1Geo + z2Geo) / 2.0f;

        float dx = x2Geo - x1Geo;
        float dz = z2Geo - z1Geo;

        float largoGeo = (float) Math.sqrt(dx * dx + dz * dz);
        float angulo = (float) Math.toDegrees(Math.atan2(dz, dx));

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(-angulo, 0f, 1f, 0f);

        Cubo.dibujar(
                0f, 0f, 0f,
                escalar(largoGeo),
                0.04f,
                0.04f,
                0.06f, 0.06f, 0.06f);

        glPopMatrix();
    }

    private static void dibujarVarillaLona(
            float x1Geo, float z1Geo,
            float x2Geo, float z2Geo,
            float y) {

        float centroXGeo = (x1Geo + x2Geo) / 2.0f;
        float centroZGeo = (z1Geo + z2Geo) / 2.0f;

        float dx = x2Geo - x1Geo;
        float dz = z2Geo - z1Geo;

        float largoGeo = (float) Math.sqrt(dx * dx + dz * dz);
        float angulo = (float) Math.toDegrees(Math.atan2(dz, dx));

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        glPushMatrix();
        glTranslatef(x, y + 0.03f, z);
        glRotatef(-angulo, 0f, 1f, 0f);

        Cubo.dibujar(
                0f, 0f, 0f,
                escalar(largoGeo),
                0.025f,
                0.025f,
                0.05f, 0.05f, 0.05f);

        glPopMatrix();
    }

    // ==========================================================
    // DECORACIÓN
    // ==========================================================
    private static void dibujarTapeteExterior(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo) {

        if (texturaTapete != 0) {
            dibujarCuboConTexturaPorGeo(xMinGeo, zMinGeo, xMaxGeo, zMaxGeo, Y + 0.01f, 0.035f, texturaTapete);
            dibujarCuboConTexturaPorGeo(xMinGeo + 0.10f, zMinGeo + 0.08f, xMaxGeo - 0.10f, zMaxGeo - 0.08f, Y + 0.045f, 0.018f, texturaTapete);
        } else {
            dibujarCuboPorGeo(
                    xMinGeo, zMinGeo,
                    xMaxGeo, zMaxGeo,
                    Y + 0.01f,
                    0.035f,
                    0.48f, 0.48f, 0.52f);

            dibujarCuboPorGeo(
                    xMinGeo + 0.10f, zMinGeo + 0.08f,
                    xMaxGeo - 0.10f, zMaxGeo - 0.08f,
                    Y + 0.045f,
                    0.018f,
                    0.66f, 0.66f, 0.70f);
        }
    }

    private static void dibujarPlantaDecorativa(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Maceta
        Cubo.dibujar(
                0f, 0.22f, 0f,
                escalar(0.32f), 0.44f, escalar(0.32f),
                0.08f, 0.08f, 0.08f);

        // Tierra
        if (texturaCesped != 0) {
            Cubo.dibujarConTextura(
                    0f, 0.47f, 0f,
                    escalar(0.26f), 0.04f, escalar(0.26f),
                    texturaCesped);
        } else {
            Cubo.dibujar(
                    0f, 0.47f, 0f,
                    escalar(0.26f), 0.04f, escalar(0.26f),
                    0.10f, 0.06f, 0.03f);
        }

        // Hojas
        if (texturaHojas != 0) {
            Cubo.dibujarConTextura(0f, 0.80f, 0f, escalar(0.36f), 0.45f, escalar(0.20f), texturaHojas);
            Cubo.dibujarConTextura(0f, 0.82f, 0f, escalar(0.20f), 0.48f, escalar(0.36f), texturaHojas);
            Cubo.dibujarConTextura(-0.08f, 1.05f, 0.06f, escalar(0.24f), 0.34f, escalar(0.16f), texturaHojas);
            Cubo.dibujarConTextura(0.10f, 1.02f, -0.05f, escalar(0.22f), 0.32f, escalar(0.16f), texturaHojas);
        } else {
            Cubo.dibujar(
                    0f, 0.80f, 0f,
                    escalar(0.36f), 0.45f, escalar(0.20f),
                    0.12f, 0.48f, 0.16f);

            Cubo.dibujar(
                    0f, 0.82f, 0f,
                    escalar(0.20f), 0.48f, escalar(0.36f),
                    0.10f, 0.55f, 0.18f);

            Cubo.dibujar(
                    -0.08f, 1.05f, 0.06f,
                    escalar(0.24f), 0.34f, escalar(0.16f),
                    0.16f, 0.62f, 0.20f);

            Cubo.dibujar(
                    0.10f, 1.02f, -0.05f,
                    escalar(0.22f), 0.32f, escalar(0.16f),
                    0.16f, 0.62f, 0.20f);
        }

        glPopMatrix();
    }
}