package piso2;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class ClosetP2 {

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
         * CLOSET - PISO 2
         *
         * Forma principal en U:
         * D6 = (5.0, 6.8)
         * E6 = (5.6, 6.8)
         * F6 = (5.6, 8.7)
         * G6 = (7.2, 8.7)
         * H6 = (7.2, 6.8)
         * M6 = (7.8, 6.8)
         * J6 = (7.8, 9.4)
         * K6 = (5.0, 9.4)
         *
         * Espejo:
         * M6 = (7.8, 6.8)
         * I6 = (7.8, 6.0)
         *
         * Estanterías:
         * L6 = (7.2, 6.0)
         * N6 = (5.9, 6.0)
         */

        dibujarClosetEnU();

        // Espejo del segmento M6 a I6
        dibujarEspejo(7.8f, 6.0f, 7.8f, 6.8f);

        // Estanterías del segmento L6 a N6
        dibujarEstanteriasInferiores(5.9f, 6.0f, 7.2f, 6.0f);

        // Tapete central del clóset
        dibujarTapeteCentral(5.85f, 7.05f, 7.10f, 8.35f);

        // Banco central decorativo
        dibujarBancoCentral(6.25f, 7.55f, 6.85f, 8.05f);
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
    // CLOSET EN FORMA DE U
    // ==========================================================
    private static void dibujarClosetEnU() {
        /*
         * Se arma como clóset en U:
         * - Lado izquierdo: entre D6, E6, F6 y K6
         * - Fondo superior: entre F6, G6, J6 y K6
         * - Lado derecho: entre G6, H6, M6 y J6
         */

        // Módulo izquierdo vertical
        dibujarModuloClosetIzquierdo(5.0f, 6.8f, 5.6f, 9.4f);

        // Módulo superior/fondo
        dibujarModuloClosetFondo(5.6f, 8.7f, 7.2f, 9.4f);

        // Módulo derecho vertical
        dibujarModuloClosetDerecho(7.2f, 6.8f, 7.8f, 9.4f);

        // Parte interior abierta entre E6-F6-G6-H6
        dibujarRopaColgadaInterior();

        // Cajonera baja dentro del clóset
        dibujarCajoneraInterior(5.85f, 6.90f, 6.65f, 7.25f);

        // Decoración: cajas superiores
        dibujarCajasSuperiores();
    }

    private static void dibujarModuloClosetIzquierdo(float xMin, float zMin, float xMax, float zMax) {
        // Cuerpo del mueble
        dibujarCuboPorGeo(xMin, zMin, xMax, zMax, Y, 2.45f,
                0.34f, 0.22f, 0.13f);

        // Frente un poco más claro
        dibujarCuboPorGeo(xMin + 0.03f, zMin + 0.05f, xMax - 0.03f, zMax - 0.05f, Y + 0.05f, 2.30f,
                0.43f, 0.28f, 0.17f);

        // Puertas largas
        dibujarPuertasVerticales(xMin, zMin, xMax, zMax, 4);

        // Repisas visibles en la parte baja
        dibujarRepisaHorizontal(xMin + 0.06f, zMin + 0.20f, xMax - 0.06f, zMin + 0.55f, 0.55f);
        dibujarRepisaHorizontal(xMin + 0.06f, zMin + 0.65f, xMax - 0.06f, zMin + 1.00f, 0.95f);
    }

    private static void dibujarModuloClosetFondo(float xMin, float zMin, float xMax, float zMax) {
        // Cuerpo del módulo de fondo
        dibujarCuboPorGeo(xMin, zMin, xMax, zMax, Y, 2.45f,
                0.34f, 0.22f, 0.13f);

        // Fondo interior
        dibujarCuboPorGeo(xMin + 0.05f, zMin + 0.05f, xMax - 0.05f, zMax - 0.05f, Y + 0.05f, 2.30f,
                0.41f, 0.27f, 0.16f);

        // Barra para ropa colgada
        dibujarBarraColgar(xMin + 0.20f, zMin + 0.20f, xMax - 0.20f, zMin + 0.20f, 1.75f);

        // Ropa colgada en el fondo
        dibujarPrendaColgada(5.85f, 8.95f, 0.80f, 0.20f, 0.75f, 0.10f, 0.25f, 0.80f);
        dibujarPrendaColgada(6.15f, 8.95f, 0.90f, 0.22f, 0.85f, 0.80f, 0.20f, 0.20f);
        dibujarPrendaColgada(6.45f, 8.95f, 0.82f, 0.20f, 0.78f, 0.15f, 0.55f, 0.25f);
        dibujarPrendaColgada(6.75f, 8.95f, 0.95f, 0.22f, 0.90f, 0.85f, 0.85f, 0.85f);

        // Maletas/cajas superiores
        dibujarCaja(5.90f, 9.05f, 0.32f, 0.22f, 2.10f, 0.50f, 0.35f, 0.20f);
        dibujarCaja(6.35f, 9.05f, 0.38f, 0.24f, 2.12f, 0.15f, 0.15f, 0.15f);
        dibujarCaja(6.85f, 9.05f, 0.32f, 0.22f, 2.10f, 0.70f, 0.50f, 0.28f);
    }

    private static void dibujarModuloClosetDerecho(float xMin, float zMin, float xMax, float zMax) {
        // Cuerpo del mueble
        dibujarCuboPorGeo(xMin, zMin, xMax, zMax, Y, 2.45f,
                0.34f, 0.22f, 0.13f);

        // Frente
        dibujarCuboPorGeo(xMin + 0.03f, zMin + 0.05f, xMax - 0.03f, zMax - 0.05f, Y + 0.05f, 2.30f,
                0.43f, 0.28f, 0.17f);

        // Zona abierta de ropa
        dibujarBarraColgar(xMin + 0.12f, zMin + 0.35f, xMax - 0.12f, zMin + 0.35f, 1.75f);

        dibujarPrendaColgada(7.50f, 7.20f, 0.75f, 0.18f, 0.80f, 0.05f, 0.20f, 0.70f);
        dibujarPrendaColgada(7.50f, 7.55f, 0.80f, 0.18f, 0.85f, 0.90f, 0.90f, 0.25f);
        dibujarPrendaColgada(7.50f, 7.90f, 0.70f, 0.18f, 0.75f, 0.60f, 0.20f, 0.65f);

        // Cajones bajos
        dibujarCajonesVerticales(xMin + 0.08f, zMin + 1.65f, xMax - 0.08f, zMin + 2.45f);

        // Estantes altos
        dibujarRepisaHorizontal(xMin + 0.06f, zMax - 0.95f, xMax - 0.06f, zMax - 0.60f, 1.45f);
        dibujarRepisaHorizontal(xMin + 0.06f, zMax - 0.50f, xMax - 0.06f, zMax - 0.15f, 1.90f);
    }

    // ==========================================================
    // ROPA, BARRAS Y DETALLES
    // ==========================================================
    private static void dibujarRopaColgadaInterior() {
        // Barra interior siguiendo el frente F6-G6
        dibujarBarraColgar(5.75f, 8.55f, 7.05f, 8.55f, 1.62f);

        dibujarPrendaColgada(5.85f, 8.55f, 0.78f, 0.20f, 0.75f, 0.70f, 0.12f, 0.12f);
        dibujarPrendaColgada(6.10f, 8.55f, 0.84f, 0.22f, 0.82f, 0.12f, 0.42f, 0.80f);
        dibujarPrendaColgada(6.35f, 8.55f, 0.72f, 0.20f, 0.72f, 0.85f, 0.85f, 0.80f);
        dibujarPrendaColgada(6.60f, 8.55f, 0.88f, 0.24f, 0.85f, 0.12f, 0.55f, 0.20f);
        dibujarPrendaColgada(6.88f, 8.55f, 0.78f, 0.20f, 0.78f, 0.45f, 0.20f, 0.65f);

        // Zapatos abajo
        dibujarZapatos(5.85f, 6.95f);
        dibujarZapatos(6.25f, 6.95f);
        dibujarZapatos(6.65f, 6.95f);
    }

    private static void dibujarBarraColgar(float x1Geo, float z1Geo, float x2Geo, float z2Geo, float altura) {
        float centroXGeo = (x1Geo + x2Geo) / 2.0f;
        float centroZGeo = (z1Geo + z2Geo) / 2.0f;

        float dx = x2Geo - x1Geo;
        float dz = z2Geo - z1Geo;

        float largoGeo = (float) Math.sqrt(dx * dx + dz * dz);
        float angulo = (float) Math.toDegrees(Math.atan2(dz, dx));

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        glPushMatrix();
        glTranslatef(x, Y + altura, z);
        glRotatef(-angulo, 0f, 1f, 0f);

        Cubo.dibujar(
                0f, 0f, 0f,
                escalar(largoGeo), 0.045f, 0.045f,
                0.70f, 0.70f, 0.68f);

        glPopMatrix();
    }

    private static void dibujarPrendaColgada(
            float xGeo, float zGeo,
            float alto, float anchoGeo, float largoVisual,
            float r, float g, float b) {

        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Gancho
        Cubo.dibujar(
                0f, 1.60f, 0f,
                escalar(0.12f), 0.035f, 0.025f,
                0.65f, 0.65f, 0.65f);

        // Prenda
        Cubo.dibujar(
                0f, 1.22f, 0.03f,
                escalar(anchoGeo), alto, escalar(0.10f),
                r, g, b);

        // Parte inferior para que parezca camisa/vestido
        Cubo.dibujar(
                0f, 0.90f, 0.03f,
                escalar(anchoGeo * 0.85f), alto * 0.35f, escalar(0.09f),
                r * 0.85f, g * 0.85f, b * 0.85f);

        glPopMatrix();
    }

    private static void dibujarZapatos(float xGeo, float zGeo) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        Cubo.dibujar(
                -0.08f, 0.08f, 0f,
                escalar(0.18f), 0.08f, escalar(0.34f),
                0.05f, 0.05f, 0.05f);

        Cubo.dibujar(
                0.12f, 0.08f, 0f,
                escalar(0.18f), 0.08f, escalar(0.34f),
                0.05f, 0.05f, 0.05f);

        glPopMatrix();
    }

    private static void dibujarCaja(
            float xGeo, float zGeo,
            float anchoGeo, float largoGeo,
            float yCentro,
            float r, float g, float b) {

        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        Cubo.dibujar(
                x,
                Y + yCentro,
                z,
                escalar(anchoGeo),
                0.28f,
                escalar(largoGeo),
                r, g, b);
    }

    // ==========================================================
    // PUERTAS, CAJONES, REPISAS
    // ==========================================================
    private static void dibujarPuertasVerticales(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            int cantidad) {

        float anchoTotalGeo = xMaxGeo - xMinGeo;
        float largoTotalGeo = zMaxGeo - zMinGeo;

        float paso = largoTotalGeo / cantidad;

        for (int i = 0; i < cantidad; i++) {
            float z1 = zMinGeo + paso * i + 0.04f;
            float z2 = zMinGeo + paso * (i + 1) - 0.04f;

            // Puerta
            dibujarCuboPorGeo(
                    xMaxGeo - 0.04f, z1,
                    xMaxGeo + 0.01f, z2,
                    Y + 0.08f,
                    2.18f,
                    0.46f, 0.30f, 0.18f);

            // Manija
            dibujarCuboPorGeo(
                    xMaxGeo + 0.015f, (z1 + z2) / 2f - 0.05f,
                    xMaxGeo + 0.04f, (z1 + z2) / 2f + 0.05f,
                    Y + 1.05f,
                    0.38f,
                    0.75f, 0.75f, 0.72f);
        }
    }

    private static void dibujarCajonesVerticales(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo) {

        float altoCajon = 0.22f;

        dibujarCuboPorGeo(xMinGeo, zMinGeo, xMaxGeo, zMaxGeo, Y, 0.78f,
                0.36f, 0.22f, 0.13f);

        for (int i = 0; i < 3; i++) {
            float yBase = Y + 0.10f + i * 0.22f;

            dibujarCuboPorGeo(
                    xMinGeo + 0.02f, zMinGeo + 0.05f,
                    xMaxGeo - 0.02f, zMaxGeo - 0.05f,
                    yBase,
                    0.16f,
                    0.48f, 0.30f, 0.18f);

            // Manija
            dibujarCuboPorGeo(
                    xMinGeo + 0.16f, (zMinGeo + zMaxGeo) / 2f - 0.04f,
                    xMaxGeo - 0.16f, (zMinGeo + zMaxGeo) / 2f + 0.04f,
                    yBase + 0.08f,
                    0.035f,
                    0.72f, 0.72f, 0.70f);
        }
    }

    private static void dibujarRepisaHorizontal(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float altura) {

        dibujarCuboPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y + altura,
                0.06f,
                0.50f, 0.33f, 0.20f);
    }

    private static void dibujarCajoneraInterior(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo) {

        dibujarCuboPorGeo(xMinGeo, zMinGeo, xMaxGeo, zMaxGeo, Y, 0.85f,
                0.34f, 0.22f, 0.13f);

        // Tres cajones
        for (int i = 0; i < 3; i++) {
            float y = Y + 0.12f + i * 0.23f;

            dibujarCuboPorGeo(
                    xMinGeo + 0.05f, zMaxGeo - 0.04f,
                    xMaxGeo - 0.05f, zMaxGeo + 0.01f,
                    y,
                    0.16f,
                    0.45f, 0.29f, 0.18f);

            dibujarCuboPorGeo(
                    xMinGeo + 0.25f, zMaxGeo + 0.015f,
                    xMaxGeo - 0.25f, zMaxGeo + 0.035f,
                    y + 0.07f,
                    0.035f,
                    0.75f, 0.75f, 0.72f);
        }

        // Doblados arriba
        dibujarRopaDoblada(5.95f, 7.08f, 0.92f, 0.18f, 0.12f, 0.85f, 0.85f, 0.80f);
        dibujarRopaDoblada(6.25f, 7.08f, 0.98f, 0.18f, 0.12f, 0.20f, 0.45f, 0.85f);
        dibujarRopaDoblada(6.50f, 7.08f, 1.04f, 0.18f, 0.12f, 0.70f, 0.20f, 0.20f);
    }

    private static void dibujarRopaDoblada(
            float xGeo, float zGeo,
            float alturaBase,
            float anchoGeo, float largoGeo,
            float r, float g, float b) {

        dibujarCuboPorGeo(
                xGeo - anchoGeo / 2f, zGeo - largoGeo / 2f,
                xGeo + anchoGeo / 2f, zGeo + largoGeo / 2f,
                Y + alturaBase,
                0.07f,
                r, g, b);
    }

    private static void dibujarCajasSuperiores() {
        dibujarCaja(5.22f, 7.20f, 0.32f, 0.30f, 2.25f, 0.70f, 0.50f, 0.30f);
        dibujarCaja(5.22f, 8.05f, 0.32f, 0.30f, 2.25f, 0.18f, 0.18f, 0.18f);
        dibujarCaja(7.52f, 8.65f, 0.32f, 0.30f, 2.25f, 0.60f, 0.42f, 0.25f);
    }

    // ==========================================================
    // ESPEJO M6 - I6
    // ==========================================================
    private static void dibujarEspejo(float x1Geo, float z1Geo, float x2Geo, float z2Geo) {
        float centroXGeo = (x1Geo + x2Geo) / 2.0f;
        float centroZGeo = (z1Geo + z2Geo) / 2.0f;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float largoGeo = Math.abs(z2Geo - z1Geo);
        float largo = escalar(largoGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Marco de madera
        Cubo.dibujar(
                0f, 1.10f, 0f,
                0.08f, 2.05f, largo,
                0.28f, 0.17f, 0.10f);

        // Cristal del espejo
        Cubo.dibujar(
                -0.025f, 1.10f, 0f,
                0.035f, 1.78f, largo * 0.82f,
                0.55f, 0.72f, 0.78f);

        // Brillo diagonal simulado
        Cubo.dibujar(
                -0.05f, 1.45f, -largo * 0.15f,
                0.02f, 0.55f, 0.035f,
                0.85f, 0.95f, 1.00f);

        Cubo.dibujar(
                -0.05f, 0.95f, largo * 0.18f,
                0.02f, 0.40f, 0.035f,
                0.85f, 0.95f, 1.00f);

        glPopMatrix();
    }

    // ==========================================================
    // ESTANTERÍAS L6 - N6
    // ==========================================================
    private static void dibujarEstanteriasInferiores(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo) {

        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(xMaxGeo - xMinGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Mueble base de estanterías pegado al segmento L6-N6
        Cubo.dibujar(
                0f, 0.95f, 0f,
                ancho, 1.90f, escalar(0.28f),
                0.34f, 0.22f, 0.13f);

        // Repisas
        Cubo.dibujar(
                0f, 0.45f, 0.02f,
                ancho * 0.92f, 0.06f, escalar(0.32f),
                0.50f, 0.33f, 0.20f);

        Cubo.dibujar(
                0f, 0.90f, 0.02f,
                ancho * 0.92f, 0.06f, escalar(0.32f),
                0.50f, 0.33f, 0.20f);

        Cubo.dibujar(
                0f, 1.35f, 0.02f,
                ancho * 0.92f, 0.06f, escalar(0.32f),
                0.50f, 0.33f, 0.20f);

        Cubo.dibujar(
                0f, 1.80f, 0.02f,
                ancho * 0.92f, 0.06f, escalar(0.32f),
                0.50f, 0.33f, 0.20f);

        // Divisiones verticales
        Cubo.dibujar(
                -ancho * 0.28f, 0.95f, 0.03f,
                0.05f, 1.80f, escalar(0.30f),
                0.45f, 0.29f, 0.18f);

        Cubo.dibujar(
                ancho * 0.28f, 0.95f, 0.03f,
                0.05f, 1.80f, escalar(0.30f),
                0.45f, 0.29f, 0.18f);

        // Libros y cajas decorativas
        dibujarLibroLocal(-ancho * 0.38f, 0.58f, 0.18f, 0.10f, 0.28f, 0.08f, 0.80f, 0.10f, 0.10f);
        dibujarLibroLocal(-ancho * 0.30f, 0.60f, 0.18f, 0.10f, 0.32f, 0.08f, 0.10f, 0.20f, 0.80f);
        dibujarLibroLocal(-ancho * 0.22f, 0.56f, 0.18f, 0.10f, 0.24f, 0.08f, 0.10f, 0.60f, 0.20f);

        dibujarLibroLocal(ancho * 0.08f, 1.03f, 0.18f, 0.16f, 0.22f, 0.10f, 0.90f, 0.70f, 0.25f);
        dibujarLibroLocal(ancho * 0.25f, 1.48f, 0.18f, 0.22f, 0.18f, 0.12f, 0.75f, 0.75f, 0.70f);

        // Cajas de zapatos
        Cubo.dibujar(
                ancho * 0.33f, 0.60f, 0.16f,
                ancho * 0.18f, 0.18f, 0.16f,
                0.18f, 0.18f, 0.18f);

        Cubo.dibujar(
                -ancho * 0.10f, 1.48f, 0.16f,
                ancho * 0.22f, 0.18f, 0.16f,
                0.72f, 0.56f, 0.38f);

        glPopMatrix();
    }

    private static void dibujarLibroLocal(
            float x, float y, float z,
            float ancho, float alto, float fondo,
            float r, float g, float b) {

        Cubo.dibujar(
                x, y, z,
                ancho, alto, fondo,
                r, g, b);
    }

    // ==========================================================
    // DECORACIÓN CENTRAL
    // ==========================================================
    private static void dibujarTapeteCentral(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        dibujarCuboPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y + 0.01f,
                0.035f,
                0.52f, 0.52f, 0.56f);

        dibujarCuboPorGeo(
                xMinGeo + 0.10f, zMinGeo + 0.10f,
                xMaxGeo - 0.10f, zMaxGeo - 0.10f,
                Y + 0.045f,
                0.018f,
                0.68f, 0.68f, 0.72f);
    }

    private static void dibujarBancoCentral(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        // Base
        dibujarCuboPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y,
                0.35f,
                0.30f, 0.20f, 0.13f);

        // Cojín superior
        dibujarCuboPorGeo(
                xMinGeo + 0.04f, zMinGeo + 0.04f,
                xMaxGeo - 0.04f, zMaxGeo - 0.04f,
                Y + 0.35f,
                0.13f,
                0.78f, 0.76f, 0.70f);

        // Patas
        dibujarCuboPorGeo(xMinGeo + 0.05f, zMinGeo + 0.05f, xMinGeo + 0.12f, zMinGeo + 0.12f, Y, 0.35f,
                0.12f, 0.12f, 0.12f);
        dibujarCuboPorGeo(xMaxGeo - 0.12f, zMinGeo + 0.05f, xMaxGeo - 0.05f, zMinGeo + 0.12f, Y, 0.35f,
                0.12f, 0.12f, 0.12f);
        dibujarCuboPorGeo(xMinGeo + 0.05f, zMaxGeo - 0.12f, xMinGeo + 0.12f, zMaxGeo - 0.05f, Y, 0.35f,
                0.12f, 0.12f, 0.12f);
        dibujarCuboPorGeo(xMaxGeo - 0.12f, zMaxGeo - 0.12f, xMaxGeo - 0.05f, zMaxGeo - 0.05f, Y, 0.35f,
                0.12f, 0.12f, 0.12f);
    }
}