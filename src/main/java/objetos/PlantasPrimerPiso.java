package objetos;

import utilidades.Constantes;

import static org.lwjgl.opengl.GL11.*;

public class PlantasPrimerPiso {

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
         * PLANTAS - PRIMER PISO
         * =====================================================
         *
         * 1) Planta en maceta del área de servicio:
         * Z6, V6, A7, W6
         *
         * 2) Jardín en área de limpieza:
         * B7, D7, E7, C7
         *
         * 3) Plantas del patio con fuente:
         * F7, I7, G7, H7
         * J7, M7, L7, K7
         *
         * 4) Arbusto recto en área libre:
         * N7, Q7, O7, P7
         *
         * 5) Jardín de cochera:
         * R7, S7, U7, T7
         *
         * 6) Piso de pasto en área libre:
         * V7, A8, Z7, W7
         */

        dibujarPlantaMacetaAreaServicio();
        dibujarJardinAreaLimpieza();
        dibujarPlantasPatioFuente();
        dibujarArbustoAreaLibre();
        dibujarJardinCochera();
        dibujarPastoAreaLibre();
    }

    // =====================================================
    // 1. PLANTA EN MACETA - ÁREA DE SERVICIO
    // Z6 = (6.0, 20.2)
    // V6 = (6.6, 20.2)
    // A7 = (6.0, 19.6)
    // W6 = (6.6, 19.6)
    // =====================================================
    private static void dibujarPlantaMacetaAreaServicio() {
        float xCentroGeo = 6.30f;
        float zCentroGeo = 19.90f;

        dibujarMacetaConPlantaGrande(xCentroGeo, zCentroGeo);
    }

    // =====================================================
    // 2. JARDÍN EN ÁREA DE LIMPIEZA
    // B7 = (0.2, 20.2)
    // D7 = (1.6, 20.2)
    // E7 = (0.2, 18.7)
    // C7 = (1.6, 18.7)
    // =====================================================
    private static void dibujarJardinAreaLimpieza() {
        // Base de pasto
        dibujarPastoPorGeo(
                0.2f, 18.7f,
                1.6f, 20.2f);

        // Plantas tipo arbusto dentro del jardín
        dibujarArbustoBajo(0.45f, 19.00f);
        dibujarArbustoBajo(0.85f, 19.35f);
        dibujarArbustoBajo(1.25f, 19.75f);

        // Planta más alta al fondo
        dibujarArbustoRecto(0.45f, 19.85f, 0.75f);
        dibujarArbustoRecto(1.30f, 19.00f, 0.65f);
    }

    // =====================================================
    // 3. PLANTAS EN PATIO CON FUENTE
    //
    // Planta 1:
    // F7 = (7.3, 13.9)
    // G7 = (7.3, 13.4)
    // H7 = (7.8, 13.4)
    // I7 = (7.8, 13.9)
    //
    // Planta 2:
    // J7 = (7.3, 12.5)
    // K7 = (7.3, 12.0)
    // L7 = (7.8, 12.0)
    // M7 = (7.8, 12.5)
    // =====================================================
    private static void dibujarPlantasPatioFuente() {
        // Planta superior junto a la fuente
        dibujarPastoPorGeo(
                7.3f, 13.4f,
                7.8f, 13.9f);

        dibujarPlantaDesdeSuelo(7.55f, 13.65f);

        // Planta inferior junto a la fuente
        dibujarPastoPorGeo(
                7.3f, 12.0f,
                7.8f, 12.5f);

        dibujarPlantaDesdeSuelo(7.55f, 12.25f);
    }

    // =====================================================
    // 4. ARBUSTO RECTO EN ÁREA LIBRE
    // N7 = (5.6, 4.8)
    // O7 = (5.6, 4.5)
    // P7 = (5.9, 4.5)
    // Q7 = (5.9, 4.8)
    // =====================================================
    private static void dibujarArbustoAreaLibre() {
        /*
         * Arbusto vertical de entrada:
         * N7 = (5.6, 4.8)
         * O7 = (5.6, 4.5)
         * P7 = (5.9, 4.5)
         * Q7 = (5.9, 4.8)
         *
         * Va desde el piso hasta casi el techo.
         */

        dibujarPastoPorGeo(
                5.6f, 4.5f,
                5.9f, 4.8f);

        dibujarArbustoRectoAltoEntrada(5.75f, 4.65f);
    }

    private static void dibujarArbustoRectoAltoEntrada(float xGeo, float zGeo) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        float altura = 2.75f;

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Tronco interno
        Cubo.dibujar(
                0f,
                altura / 2f,
                0f,
                escalar(0.06f),
                altura,
                escalar(0.06f),
                0.18f,
                0.28f,
                0.10f);

        // Cuerpo principal del arbusto, alto y estrecho
        Cubo.dibujar(
                0f,
                altura / 2f,
                0f,
                escalar(0.26f),
                altura,
                escalar(0.26f),
                0.07f,
                0.42f,
                0.12f);

        // Volumen superior para que se vea más frondoso
        Cubo.dibujar(
                0f,
                altura * 0.78f,
                0f,
                escalar(0.32f),
                altura * 0.38f,
                escalar(0.32f),
                0.10f,
                0.55f,
                0.15f);

        // Volumen bajo
        Cubo.dibujar(
                0f,
                altura * 0.30f,
                0f,
                escalar(0.30f),
                altura * 0.35f,
                escalar(0.30f),
                0.08f,
                0.50f,
                0.13f);

        glPopMatrix();
    }

    // =====================================================
    // 5. JARDÍN EN ESPACIO DE COCHERA
    // R7 = (0.2, 5.3)
    // S7 = (0.2, 4.7)
    // T7 = (3.9, 4.7)
    // U7 = (3.9, 5.3)
    // =====================================================
    private static void dibujarJardinCochera() {
        // Pasto base del jardín de cochera
        dibujarPastoPorGeo(
                0.2f, 4.7f,
                3.9f, 5.3f);

        // Arbustos pequeños
        dibujarArbustoBajo(0.70f, 5.00f);
        dibujarArbustoBajo(1.35f, 5.02f);
        dibujarArbustoBajo(2.10f, 4.98f);
        dibujarArbustoBajo(2.85f, 5.03f);

        // Árboles pequeños decorativos
        dibujarArbolPequeno(0.45f, 4.90f);
        dibujarArbolPequeno(3.55f, 5.05f);

        // Flores o plantas bajas
        dibujarFlor(1.00f, 4.78f);
        dibujarFlor(1.75f, 4.80f);
        dibujarFlor(2.55f, 4.78f);
        dibujarFlor(3.25f, 4.82f);
    }

    // =====================================================
    // 6. PISO DE PASTO EN ÁREA LIBRE
    // V7 = (5.9, 5.3)
    // A8 = (7.8, 5.3)
    // Z7 = (7.8, 1.2)
    // W7 = (5.9, 1.2)
    // =====================================================
    private static void dibujarPastoAreaLibre() {
        /*
         * Piso de pasto en área libre:
         * V7 = (5.9, 5.3)
         * A8 = (7.8, 5.3)
         * Z7 = (7.8, 1.2)
         * W7 = (5.9, 1.2)
         *
         * Aquí solo va césped verde, sin plantas.
         */
        dibujarPastoPorGeo(
                5.9f, 1.2f,
                7.8f, 5.3f);
    }

    // =====================================================
    // HELPERS DE JARDÍN
    // =====================================================

    private static void dibujarPastoPorGeo(
            float xMinGeo,
            float zMinGeo,
            float xMaxGeo,
            float zMaxGeo) {

        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo;
        float fondoGeo = zMaxGeo - zMinGeo;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        Cubo.dibujar(
                x,
                Y + 0.025f,
                z,
                escalar(anchoGeo),
                0.05f,
                escalar(fondoGeo),
                0.12f,
                0.45f,
                0.12f);
    }

    private static void dibujarMacetaConPlantaGrande(float xGeo, float zGeo) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Maceta
        Cubo.dibujar(
                0f,
                0.22f,
                0f,
                escalar(0.36f),
                0.44f,
                escalar(0.36f),
                0.36f,
                0.20f,
                0.10f);

        // Tierra
        Cubo.dibujar(
                0f,
                0.46f,
                0f,
                escalar(0.32f),
                0.04f,
                escalar(0.32f),
                0.10f,
                0.06f,
                0.03f);

        // Tallo
        Cubo.dibujar(
                0f,
                0.78f,
                0f,
                escalar(0.06f),
                0.65f,
                escalar(0.06f),
                0.20f,
                0.42f,
                0.12f);

        // Hojas grandes
        dibujarHojasLocales(0f, 1.05f, 0f, 0.55f);

        glPopMatrix();
    }

    private static void dibujarPlantaDesdeSuelo(float xGeo, float zGeo) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Tallo base
        Cubo.dibujar(
                0f,
                0.35f,
                0f,
                escalar(0.05f),
                0.70f,
                escalar(0.05f),
                0.18f,
                0.38f,
                0.10f);

        // Hojas
        dibujarHojasLocales(0f, 0.78f, 0f, 0.45f);

        glPopMatrix();
    }

    private static void dibujarArbustoRecto(float xGeo, float zGeo, float altura) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Tronco/centro
        Cubo.dibujar(
                0f,
                altura / 2f,
                0f,
                escalar(0.08f),
                altura,
                escalar(0.08f),
                0.18f,
                0.30f,
                0.10f);

        // Volumen de hojas vertical
        Cubo.dibujar(
                0f,
                altura * 0.55f,
                0f,
                escalar(0.22f),
                altura * 0.85f,
                escalar(0.22f),
                0.08f,
                0.42f,
                0.12f);

        Cubo.dibujar(
                0f,
                altura * 0.95f,
                0f,
                escalar(0.28f),
                altura * 0.35f,
                escalar(0.28f),
                0.10f,
                0.55f,
                0.16f);

        glPopMatrix();
    }

    private static void dibujarArbustoBajo(float xGeo, float zGeo) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        Cubo.dibujar(
                0f,
                0.18f,
                0f,
                escalar(0.28f),
                0.35f,
                escalar(0.28f),
                0.08f,
                0.42f,
                0.10f);

        Cubo.dibujar(
                escalar(0.08f),
                0.32f,
                escalar(0.05f),
                escalar(0.25f),
                0.28f,
                escalar(0.25f),
                0.12f,
                0.55f,
                0.15f);

        glPopMatrix();
    }

    private static void dibujarArbolPequeno(float xGeo, float zGeo) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Tronco
        Cubo.dibujar(
                0f,
                0.45f,
                0f,
                escalar(0.10f),
                0.90f,
                escalar(0.10f),
                0.35f,
                0.18f,
                0.08f);

        // Copa
        Cubo.dibujar(
                0f,
                1.05f,
                0f,
                escalar(0.42f),
                0.55f,
                escalar(0.42f),
                0.08f,
                0.45f,
                0.10f);

        Cubo.dibujar(
                0f,
                1.35f,
                0f,
                escalar(0.30f),
                0.35f,
                escalar(0.30f),
                0.10f,
                0.55f,
                0.12f);

        glPopMatrix();
    }

    private static void dibujarFlor(float xGeo, float zGeo) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Tallo
        Cubo.dibujar(
                0f,
                0.18f,
                0f,
                escalar(0.03f),
                0.35f,
                escalar(0.03f),
                0.10f,
                0.45f,
                0.10f);

        // Flor
        Cubo.dibujar(
                0f,
                0.38f,
                0f,
                escalar(0.12f),
                0.08f,
                escalar(0.12f),
                0.90f,
                0.85f,
                0.20f);

        glPopMatrix();
    }

    private static void dibujarHojasLocales(
            float xLocal,
            float yLocal,
            float zLocal,
            float escala) {

        // Hoja izquierda
        Cubo.dibujar(
                xLocal - escalar(0.12f),
                yLocal,
                zLocal,
                escalar(escala),
                0.12f,
                escalar(0.10f),
                0.08f,
                0.48f,
                0.12f);

        // Hoja derecha
        Cubo.dibujar(
                xLocal + escalar(0.12f),
                yLocal + 0.08f,
                zLocal,
                escalar(escala),
                0.12f,
                escalar(0.10f),
                0.10f,
                0.58f,
                0.16f);

        // Hoja frontal
        Cubo.dibujar(
                xLocal,
                yLocal + 0.04f,
                zLocal + escalar(0.12f),
                escalar(0.10f),
                0.12f,
                escalar(escala),
                0.08f,
                0.50f,
                0.14f);

        // Hoja trasera
        Cubo.dibujar(
                xLocal,
                yLocal + 0.10f,
                zLocal - escalar(0.12f),
                escalar(0.10f),
                0.12f,
                escalar(escala),
                0.12f,
                0.62f,
                0.18f);
    }
}