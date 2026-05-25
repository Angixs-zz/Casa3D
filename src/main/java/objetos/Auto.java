package objetos;

import utilidades.Constantes;

import static org.lwjgl.opengl.GL11.*;

public class Auto {

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
         * AUTO - COCHERA
         * =====================================================
         *
         * Rectángulo de la cochera:
         * B8 = (0.4, 4.6)
         * C8 = (2.2, 4.6)
         * D8 = (0.4, 0.4)
         * E8 = (2.2, 0.4)
         *
         * Centro:
         * x = 1.3
         * z = 2.5
         */

        float xGeo = 1.3f;
        float zGeo = 2.5f;

        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Si luego quieres girarlo:
        // glRotatef(0f, 0f, 1f, 0f);

        dibujarCarroceria();
        dibujarCabina();
        dibujarCofreYCajuela();
        dibujarVentanas();
        dibujarParabrisas();
        dibujarLlantas();
        dibujarFarosYDetalles();

        glPopMatrix();
    }

    // =====================================================
    // CUERPO PRINCIPAL
    // =====================================================
    private static void dibujarCarroceria() {
        // Base principal del auto
        Cubo.dibujar(
                0f,
                escalar(0.30f),
                0f,
                escalar(1.38f),
                escalar(0.35f),
                escalar(3.55f),
                0.78f,
                0.05f,
                0.05f);

        // Franja lateral izquierda
        Cubo.dibujar(
                -escalar(0.60f),
                escalar(0.42f),
                0f,
                escalar(0.12f),
                escalar(0.20f),
                escalar(3.20f),
                0.72f,
                0.04f,
                0.04f);

        // Franja lateral derecha
        Cubo.dibujar(
                escalar(0.60f),
                escalar(0.42f),
                0f,
                escalar(0.12f),
                escalar(0.20f),
                escalar(3.20f),
                0.72f,
                0.04f,
                0.04f);
    }

    // =====================================================
    // CABINA / TECHO
    // =====================================================
    private static void dibujarCabina() {
        // Cabina principal
        Cubo.dibujar(
                0f,
                escalar(0.78f),
                0.15f,
                escalar(1.10f),
                escalar(0.45f),
                escalar(1.55f),
                0.82f,
                0.08f,
                0.08f);

        // Techo
        Cubo.dibujar(
                0f,
                escalar(1.08f),
                0.15f,
                escalar(0.92f),
                escalar(0.14f),
                escalar(1.05f),
                0.70f,
                0.05f,
                0.05f);
    }

    // =====================================================
    // COFRE Y CAJUELA
    // =====================================================
    private static void dibujarCofreYCajuela() {
        // Cofre / frente del auto
        Cubo.dibujar(
                0f,
                escalar(0.52f),
                -escalar(1.10f),
                escalar(1.24f),
                escalar(0.16f),
                escalar(1.05f),
                0.82f,
                0.08f,
                0.08f);

        // Cajuela / parte trasera
        Cubo.dibujar(
                0f,
                escalar(0.56f),
                escalar(1.20f),
                escalar(1.20f),
                escalar(0.18f),
                escalar(0.88f),
                0.80f,
                0.06f,
                0.06f);

        // Defensa delantera
        Cubo.dibujar(
                0f,
                escalar(0.18f),
                -escalar(1.72f),
                escalar(1.22f),
                escalar(0.14f),
                escalar(0.18f),
                0.10f,
                0.10f,
                0.10f);

        // Defensa trasera
        Cubo.dibujar(
                0f,
                escalar(0.18f),
                escalar(1.72f),
                escalar(1.18f),
                escalar(0.14f),
                escalar(0.18f),
                0.12f,
                0.12f,
                0.12f);
    }

    // =====================================================
    // VENTANAS LATERALES Y POSTERIOR
    // =====================================================
    private static void dibujarVentanas() {
        // Ventana lateral izquierda
        Cubo.dibujar(
                -escalar(0.38f),
                escalar(0.92f),
                0.18f,
                escalar(0.20f),
                escalar(0.20f),
                escalar(1.05f),
                0.18f,
                0.30f,
                0.38f);

        // Ventana lateral derecha
        Cubo.dibujar(
                escalar(0.38f),
                escalar(0.92f),
                0.18f,
                escalar(0.20f),
                escalar(0.20f),
                escalar(1.05f),
                0.18f,
                0.30f,
                0.38f);

        // Ventana trasera
        Cubo.dibujar(
                0f,
                escalar(0.90f),
                escalar(0.88f),
                escalar(0.76f),
                escalar(0.18f),
                escalar(0.28f),
                0.18f,
                0.30f,
                0.38f);
    }

    // =====================================================
    // PARABRISAS
    // =====================================================
    private static void dibujarParabrisas() {
        // Parabrisas frontal
        Cubo.dibujar(
                0f,
                escalar(0.88f),
                -escalar(0.58f),
                escalar(0.78f),
                escalar(0.20f),
                escalar(0.30f),
                0.18f,
                0.30f,
                0.38f);

        // Marco del parabrisas
        Cubo.dibujar(
                0f,
                escalar(0.90f),
                -escalar(0.58f),
                escalar(0.88f),
                escalar(0.05f),
                escalar(0.36f),
                0.10f,
                0.10f,
                0.10f);
    }

    // =====================================================
    // LLANTAS
    // =====================================================
    private static void dibujarLlantas() {
        // Delantera izquierda
        dibujarLlanta(-0.78f, -1.05f);

        // Delantera derecha
        dibujarLlanta(0.78f, -1.05f);

        // Trasera izquierda
        dibujarLlanta(-0.78f, 1.05f);

        // Trasera derecha
        dibujarLlanta(0.78f, 1.05f);
    }

    private static void dibujarLlanta(float xLocalGeo, float zLocalGeo) {
        float xLocal = escalar(xLocalGeo);
        float zLocal = escalar(zLocalGeo);

        // Llanta exterior
        Cubo.dibujar(
                xLocal,
                escalar(0.20f),
                zLocal,
                escalar(0.22f),
                escalar(0.40f),
                escalar(0.52f),
                0.04f,
                0.04f,
                0.04f);

        // Rin
        Cubo.dibujar(
                xLocal,
                escalar(0.20f),
                zLocal,
                escalar(0.12f),
                escalar(0.18f),
                escalar(0.28f),
                0.65f,
                0.65f,
                0.68f);
    }

    // =====================================================
    // DETALLES
    // =====================================================
    private static void dibujarFarosYDetalles() {
        // Faros delanteros
        Cubo.dibujar(
                -escalar(0.36f),
                escalar(0.40f),
                -escalar(1.55f),
                escalar(0.20f),
                escalar(0.10f),
                escalar(0.08f),
                0.92f,
                0.92f,
                0.72f);

        Cubo.dibujar(
                escalar(0.36f),
                escalar(0.40f),
                -escalar(1.55f),
                escalar(0.20f),
                escalar(0.10f),
                escalar(0.08f),
                0.92f,
                0.92f,
                0.72f);

        // Parrilla frontal
        Cubo.dibujar(
                0f,
                escalar(0.34f),
                -escalar(1.50f),
                escalar(0.40f),
                escalar(0.10f),
                escalar(0.06f),
                0.10f,
                0.10f,
                0.10f);

        // Faros traseros
        Cubo.dibujar(
                -escalar(0.34f),
                escalar(0.42f),
                escalar(1.57f),
                escalar(0.18f),
                escalar(0.08f),
                escalar(0.08f),
                0.85f,
                0.12f,
                0.12f);

        Cubo.dibujar(
                escalar(0.34f),
                escalar(0.42f),
                escalar(1.57f),
                escalar(0.18f),
                escalar(0.08f),
                escalar(0.08f),
                0.85f,
                0.12f,
                0.12f);

        // Espejo izquierdo
        Cubo.dibujar(
                -escalar(0.73f),
                escalar(0.72f),
                -escalar(0.15f),
                escalar(0.08f),
                escalar(0.10f),
                escalar(0.16f),
                0.10f,
                0.10f,
                0.10f);

        // Espejo derecho
        Cubo.dibujar(
                escalar(0.73f),
                escalar(0.72f),
                -escalar(0.15f),
                escalar(0.08f),
                escalar(0.10f),
                escalar(0.16f),
                0.10f,
                0.10f,
                0.10f);

        // Placa delantera
        Cubo.dibujar(
                0f,
                escalar(0.22f),
                -escalar(1.61f),
                escalar(0.24f),
                escalar(0.08f),
                escalar(0.03f),
                0.96f,
                0.96f,
                0.96f);

        // Placa trasera
        Cubo.dibujar(
                0f,
                escalar(0.22f),
                escalar(1.61f),
                escalar(0.24f),
                escalar(0.08f),
                escalar(0.03f),
                0.96f,
                0.96f,
                0.96f);
    }
}