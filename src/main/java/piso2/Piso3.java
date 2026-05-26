package piso2;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

/**
 * PISO 3 - Terraza / Rooftop
 *
 * Puntos de referencia (coordenadas GeoGebra):
 *   W  = (0.1, 14.0)  V  = (0.1, 15.4)
 *   H1 = (0.8, 14.0)  N1 = (0.8, 15.4)
 *   O1 = (2.0, 14.0)  P1 = (2.0, 15.4)
 *   R1 = (3.0, 14.0)  Q1 = (3.0, 15.4)
 *
 * Jardines:
 *   Jardín 1: Z(0.1,9.5) - A1(3.8,9.5) - F1(3.8,10.3) - G1(0.8,10.3) - H1(0.8,14.0) - W(0.1,14.0)
 *   Jardín 2: M(0.1,2.6) - L1(6.6,2.6) - K(6.6,1.5) - L(0.1,1.5)
 *   Jardín 3: D1(0.1,7.9) - E1(3.8,7.9) - C1(0.1,7.0) - B1(3.8,7.0) ... [área del cuarto]
 *
 * Mesa de regalos (puntos imagen): S1(6.7,16.7) - T1(6.7,14.6) - U1(7.8,14.6) - V1(7.8,16.7)
 */
public class Piso3 {

    private static final float Y = Constantes.ALTURA_PISO_3;
    
    public static int texturaCesped = 0;
    public static int texturaHojas = 0;

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
        // Repisas tipo banco/ledge
        dibujarRepisa(0.1f, 14.0f, 0.8f, 15.4f);  // V - N1 - H1 - W
        dibujarRepisa(2.0f, 14.0f, 3.0f, 15.4f);  // O1 - P1 - Q1 - R1

        // Mesa de regalos
        dibujarMesaRegalos(6.7f, 14.6f, 7.8f, 16.7f);

        // Jardines
        dibujarJardin1();
        dibujarJardin2();
        dibujarJardin3();
    }

    // ==========================================================
    // REPISA / BANCO (como en la imagen de referencia)
    // Panel de vidrio + tapa de concreto blanco
    // ==========================================================
    private static void dibujarRepisa(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroX = (xMinGeo + xMaxGeo) / 2f;
        float centroZ = (zMinGeo + zMaxGeo) / 2f;
        float ancho   = xMaxGeo - xMinGeo;
        float largo   = zMaxGeo - zMinGeo;

        float cx = convertirXGeoAOpenGL(centroX);
        float cz = convertirZGeoAOpenGL(centroZ);
        float ax = escalar(ancho);
        float az = escalar(largo);

        glPushMatrix();
        glTranslatef(cx, Y, cz);

        // Zoclo / base de concreto (parte inferior sólida)
        Cubo.dibujar(0f, 0.22f, 0f, ax, 0.44f, az,
                0.91f, 0.91f, 0.93f);

        // Panel de vidrio interior (frente y atrás)
        float panelH = 0.34f;
        float panelY = 0.44f + panelH / 2f;
        // Panel largo (eje Z)
        Cubo.dibujar(0f, panelY, -az * 0.35f, ax * 0.85f, panelH, 0.04f,
                0.65f, 0.75f, 0.82f);
        Cubo.dibujar(0f, panelY,  az * 0.35f, ax * 0.85f, panelH, 0.04f,
                0.65f, 0.75f, 0.82f);

        // Marcos verticales del panel (postes de madera/metal oscuro)
        float marcoW = 0.06f;
        float marcoH = panelH + 0.06f;
        float marcoY = 0.44f + marcoH / 2f;
        Cubo.dibujar(-ax * 0.42f, marcoY, 0f, marcoW, marcoH, az * 0.85f,
                0.35f, 0.22f, 0.12f);
        Cubo.dibujar( ax * 0.42f, marcoY, 0f, marcoW, marcoH, az * 0.85f,
                0.35f, 0.22f, 0.12f);

        // Tapa superior de concreto blanco
        float tapaY = 0.44f + panelH + 0.04f;
        Cubo.dibujar(0f, tapaY + 0.05f, 0f, ax + 0.08f, 0.10f, az + 0.08f,
                0.95f, 0.95f, 0.96f);

        glPopMatrix();
    }

    // ==========================================================
    // MESA DE REGALOS
    // Posición: S1(6.7,16.7) - T1(6.7,14.6) - U1(7.8,14.6) - V1(7.8,16.7)
    // ==========================================================
    private static void dibujarMesaRegalos(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroX = (xMinGeo + xMaxGeo) / 2f;
        float centroZ = (zMinGeo + zMaxGeo) / 2f;
        float ancho   = xMaxGeo - xMinGeo;
        float largo   = zMaxGeo - zMinGeo;

        float cx = convertirXGeoAOpenGL(centroX);
        float cz = convertirZGeoAOpenGL(centroZ);
        float ax = escalar(ancho);
        float az = escalar(largo);

        glPushMatrix();
        glTranslatef(cx, Y, cz);

        // Tablero de la mesa
        Cubo.dibujar(0f, 0.84f, 0f, ax, 0.08f, az,
                0.88f, 0.88f, 0.90f);

        // Patas (4 esquinas)
        float px = ax * 0.42f;
        float pz = az * 0.42f;
        dibujarPatasMesa(px, pz, 0.08f, 0.84f);

        // === REGALOS ENCIMA DE LA MESA ===
        // Regalo 1 - grande, rojo
        dibujarRegalo( ax * 0.25f, 0.90f, -az * 0.22f, 0.32f, 0.26f, 0.28f,
                0.85f, 0.12f, 0.12f, 0.92f, 0.90f, 0.25f);
        // Regalo 2 - mediano, azul
        dibujarRegalo(-ax * 0.20f, 0.90f,  az * 0.18f, 0.24f, 0.22f, 0.24f,
                0.20f, 0.35f, 0.78f, 0.92f, 0.90f, 0.25f);
        // Regalo 3 - pequeño, verde
        dibujarRegalo( ax * 0.10f, 0.90f,  az * 0.28f, 0.18f, 0.18f, 0.18f,
                0.15f, 0.65f, 0.20f, 0.92f, 0.90f, 0.25f);
        // Regalo 4 - alargado, morado
        dibujarRegalo(-ax * 0.28f, 0.90f, -az * 0.28f, 0.38f, 0.20f, 0.22f,
                0.60f, 0.15f, 0.75f, 0.95f, 0.92f, 0.28f);
        // Regalo 5 - pequeño encima del grande
        dibujarRegalo( ax * 0.25f, 1.17f, -az * 0.22f, 0.18f, 0.16f, 0.16f,
                0.90f, 0.55f, 0.10f, 0.92f, 0.90f, 0.25f);

        glPopMatrix();
    }

    private static void dibujarPatasMesa(float px, float pz, float grosor, float alto) {
        float[] xs = {-px, px, -px, px};
        float[] zs = {-pz, -pz, pz, pz};
        for (int i = 0; i < 4; i++) {
            Cubo.dibujar(xs[i], alto / 2f, zs[i], grosor, alto, grosor,
                    0.40f, 0.40f, 0.42f);
        }
    }

    private static void dibujarRegalo(
            float localX, float localY, float localZ,
            float ancho, float alto, float fondo,
            float rC, float gC, float bC,
            float rM, float gM, float bM) {
        // Cuerpo del regalo
        Cubo.dibujar(localX, localY + alto / 2f, localZ, ancho, alto, fondo,
                rC, gC, bC);
        // Moño / cinta horizontal
        Cubo.dibujar(localX, localY + alto + 0.015f, localZ,
                ancho + 0.01f, 0.04f, fondo * 0.18f,
                rM, gM, bM);
        // Cinta vertical
        Cubo.dibujar(localX, localY + alto + 0.015f, localZ,
                ancho * 0.18f, 0.04f, fondo + 0.01f,
                rM, gM, bM);
        // Lazo (3 cubitos arriba)
        Cubo.dibujar(localX - ancho * 0.08f, localY + alto + 0.06f, localZ,
                0.08f, 0.08f, 0.05f, rM, gM, bM);
        Cubo.dibujar(localX + ancho * 0.08f, localY + alto + 0.06f, localZ,
                0.08f, 0.08f, 0.05f, rM, gM, bM);
        Cubo.dibujar(localX, localY + alto + 0.075f, localZ,
                0.06f, 0.06f, 0.06f, rM, gM, bM);
    }

    // ==========================================================
    // JARDÍN 1
    // Área: Z(0.1,9.5) - A1(3.8,9.5) - F1(3.8,10.3) - G1(0.8,10.3) - H1(0.8,14.0) - W(0.1,14.0)
    // Forma de L invertida
    // ==========================================================
    private static void dibujarJardin1() {
        // Sección baja: Z-A1-F1-G1  (rectángulo estrecho)
        dibujarPastoPorGeo(0.1f, 9.5f, 3.8f, 10.3f);
        dibujarArbustoDecorativo(0.6f, 9.8f);
        dibujarArbustoDecorativo(1.5f, 9.9f);
        dibujarArbustoDecorativo(2.4f, 9.8f);
        dibujarArbustoDecorativo(3.3f, 9.75f);

        // Sección alta: G1-H1-W-Z (rectángulo vertical)
        dibujarPastoPorGeo(0.1f, 10.3f, 0.8f, 14.0f);
        dibujarArbustoDecorativo(0.45f, 11.0f);
        dibujarArbustoDecorativo(0.45f, 12.2f);
        dibujarArbustoDecorativo(0.45f, 13.4f);
    }

    // ==========================================================
    // JARDÍN 2
    // Área: M(0.1,2.6) - L1(6.6,2.6) - K(6.6,1.5) - L(0.1,1.5)
    // Franja horizontal larga
    // ==========================================================
    private static void dibujarJardin2() {
        dibujarPastoPorGeo(0.1f, 1.5f, 6.6f, 2.6f);
        // Plantas decorativas a lo largo
        dibujarArbustoDecorativo(0.7f, 2.05f);
        dibujarArbustoDecorativo(1.8f, 2.05f);
        dibujarArbustoDecorativo(3.0f, 2.0f);
        dibujarArbustoDecorativo(4.2f, 2.05f);
        dibujarArbustoDecorativo(5.4f, 2.0f);
        dibujarArbustoDecorativo(6.3f, 2.05f);
    }

    // ==========================================================
    // JARDÍN 3
    // Área: D1(0.1,7.9) - E1(3.8,7.9) - B1(3.8,7.0) - C1(0.1,7.0)
    // Franja horizontal
    // ==========================================================
    private static void dibujarJardin3() {
        dibujarPastoPorGeo(0.1f, 7.0f, 3.8f, 7.9f);
        dibujarArbustoDecorativo(0.6f, 7.45f);
        dibujarArbustoDecorativo(1.5f, 7.42f);
        dibujarArbustoDecorativo(2.4f, 7.45f);
        dibujarArbustoDecorativo(3.3f, 7.42f);
    }

    // ==========================================================
    // HELPERS DE JARDÍN
    // ==========================================================
    private static void dibujarPastoPorGeo(float xMin, float zMin, float xMax, float zMax) {
        float cx = convertirXGeoAOpenGL((xMin + xMax) / 2f);
        float cz = convertirZGeoAOpenGL((zMin + zMax) / 2f);
        float ax = escalar(xMax - xMin);
        float az = escalar(zMax - zMin);

        // Capa de tierra
        Cubo.dibujar(cx, Y + 0.04f, cz, ax, 0.08f, az, 0.25f, 0.14f, 0.05f);
        // Capa de pasto
        if (texturaCesped != 0) {
            Cubo.dibujarConTextura(cx, Y + 0.10f, cz, ax, 0.06f, az, texturaCesped);
        } else {
            Cubo.dibujar(cx, Y + 0.10f, cz, ax, 0.06f, az, 0.14f, 0.52f, 0.14f);
        }
    }

    private static void dibujarArbustoDecorativo(float xGeo, float zGeo) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y + 0.16f, z);

        // Tallo
        Cubo.dibujar(0f, 0.15f, 0f, escalar(0.05f), 0.30f, escalar(0.05f),
                0.20f, 0.35f, 0.12f);
        // Copa baja y alta
        if (texturaHojas != 0) {
            Cubo.dibujarConTextura(0f, 0.36f, 0f, escalar(0.28f), 0.25f, escalar(0.28f), texturaHojas);
            Cubo.dibujarConTextura(0f, 0.54f, 0f, escalar(0.20f), 0.20f, escalar(0.20f), texturaHojas);
        } else {
            Cubo.dibujar(0f, 0.36f, 0f, escalar(0.28f), 0.25f, escalar(0.28f),
                    0.10f, 0.48f, 0.13f);
            Cubo.dibujar(0f, 0.54f, 0f, escalar(0.20f), 0.20f, escalar(0.20f),
                    0.12f, 0.58f, 0.16f);
        }

        glPopMatrix();
    }
}
