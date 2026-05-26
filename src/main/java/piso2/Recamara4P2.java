package piso2;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class Recamara4P2 {

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
         * RECÁMARA 4 - PISO 2
         *
         * Cama:
         * T4 = (0.2, 7.1)
         * U4 = (0.2, 5.8)
         * V4 = (2.0, 5.8)
         * W4 = (2.0, 7.1)
         *
         * Buró superior:
         * Z4 = (0.2, 7.5)
         * A5 = (0.5, 7.5)
         * B5 = (0.5, 7.2)
         * C5 = (0.2, 7.2)
         *
         * Buró inferior:
         * D5 = (0.2, 5.7)
         * E5 = (0.2, 5.4)
         * F5 = (0.5, 5.4)
         * G5 = (0.5, 5.7)
         *
         * Mesa con TV:
         * H5 = (3.4, 6.9)
         * I5 = (3.4, 5.6)
         * J5 = (3.8, 5.6)
         * K5 = (3.8, 6.9)
         *
         * Mesa gamer:
         * L5 = (0.6, 7.8)
         * M5 = (0.6, 7.5)
         * N5 = (1.7, 7.5)
         * O5 = (1.7, 7.8)
         *
         * Clóset / estantería / mueble:
         * P5 = (0.2, 5.0)
         * Q5 = (0.2, 4.5)
         * R5 = (3.4, 4.5)
         * S5 = (3.4, 5.0)
         */

        // Cambia el último número si quieres rotar algún mueble:
        // 0f, 90f, -90f o 180f

        dibujarCama(0.2f, 5.8f, 2.0f, 7.1f, 0f);

        dibujarBuro(0.2f, 7.2f, 0.5f, 7.5f, 0f);
        // El buro de D5 a G5 fue removido.

        dibujarMesaConTV(3.4f, 5.6f, 3.8f, 6.9f, 0f);

        dibujarMesaGamerConSilla(0.6f, 7.5f, 1.7f, 7.8f, 0f);

        // Silla gamer reubicada entre T5 y W5
        dibujarSillaGamer(1.15f, 7.3f, 0f);

        // Nueva zona dividida
        dibujarCloset(1.8f, 4.5f, 3.4f, 5.0f, 0f);
        dibujarMesaSillaEstudio(0.8f, 4.5f, 1.8f, 5.0f, 0f);
        dibujarEstanteria(0.2f, 4.5f, 0.8f, 5.0f, 0f);

        // dibujarTapete(0.65f, 5.85f, 2.65f, 7.25f);
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
    // CAMA
    // Entre T4, U4, V4, W4
    // ==========================================================
    private static void dibujarCama(
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
        dibujarCuboMadera(
                0f, 0.18f, 0f,
                ancho, 0.36f, largo,
                0.34f, 0.20f, 0.12f);

        // Colchón
        Cubo.dibujar(
                0f, 0.48f, 0f,
                ancho * 0.90f, 0.22f, largo * 0.90f,
                0.92f, 0.92f, 0.88f);

        // Cobija (desplazada hacia -X, es decir los pies de la cama)
        Cubo.dibujar(
                -ancho * 0.12f, 0.63f, 0f,
                ancho * 0.56f, 0.08f, largo * 0.84f,
                0.26f, 0.38f, 0.66f);

        // Almohadas (pegadas a +X que es la pared X=0.2 GeoGebra)
        Cubo.dibujar(
                ancho * 0.32f, 0.70f, -largo * 0.23f,
                ancho * 0.18f, 0.10f, largo * 0.35f,
                0.96f, 0.96f, 0.94f);

        Cubo.dibujar(
                ancho * 0.32f, 0.70f, largo * 0.23f,
                ancho * 0.18f, 0.10f, largo * 0.35f,
                0.96f, 0.96f, 0.94f);

        // Cabecera pegada a la pared izquierda (X=0.2, que en OpenGL es +ancho/2)
        dibujarCuboMadera(
                ancho / 2f - 0.05f, 0.55f, 0f,
                0.10f, 1.10f, largo,
                0.28f, 0.16f, 0.10f);

        // Detalle de cabecera
        dibujarCuboMadera(
                ancho / 2f - 0.105f, 0.92f, 0f,
                0.035f, 0.12f, largo * 0.82f,
                0.44f, 0.28f, 0.16f);

        glPopMatrix();
    }

    // ==========================================================
    // BURÓ
    // Z4-A5-B5-C5 y D5-E5-F5-G5
    // ==========================================================
    private static void dibujarBuro(
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

        // Cuerpo del buró
        dibujarCuboMadera(
                0f, 0.28f, 0f,
                ancho, 0.56f, largo,
                0.36f, 0.20f, 0.10f);

        // Cajón frontal
        dibujarCuboMadera(
                0f, 0.32f, largo / 2f + 0.015f,
                ancho * 0.82f, 0.18f, 0.03f,
                0.48f, 0.30f, 0.18f);

        // Manija
        Cubo.dibujar(
                0f, 0.32f, largo / 2f + 0.04f,
                ancho * 0.30f, 0.035f, 0.025f,
                0.75f, 0.75f, 0.72f);

        // Lámpara pequeña
        Cubo.dibujar(
                0f, 0.68f, 0f,
                ancho * 0.22f, 0.22f, largo * 0.22f,
                0.14f, 0.14f, 0.14f);

        Cubo.dibujar(
                0f, 0.86f, 0f,
                ancho * 0.48f, 0.18f, largo * 0.42f,
                0.95f, 0.85f, 0.55f);

        glPopMatrix();
    }

    // ==========================================================
    // MESA CON TV PEGADA A LA PARED
    // Entre H5, I5, J5, K5
    // ==========================================================
    private static void dibujarMesaConTV(
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

        // Mesa / mueble vertical
        dibujarCuboMadera(
                0f, 0.38f, 0f,
                ancho, 0.76f, largo,
                0.34f, 0.22f, 0.14f);

        // Cubierta superior
        dibujarCuboMadera(
                0f, 0.80f, 0f,
                ancho * 1.08f, 0.08f, largo * 1.02f,
                0.46f, 0.30f, 0.18f);

        // Cajones
        dibujarCuboMadera(
                0f, 0.30f, largo / 2f + 0.018f,
                ancho * 0.78f, 0.16f, 0.035f,
                0.46f, 0.29f, 0.17f);

        dibujarCuboMadera(
                0f, 0.52f, largo / 2f + 0.018f,
                ancho * 0.78f, 0.16f, 0.035f,
                0.46f, 0.29f, 0.17f);

        // Soporte de TV
        Cubo.dibujar(
                -ancho * 0.35f, 0.95f, 0f,
                0.04f, 0.30f, largo * 0.16f,
                0.05f, 0.05f, 0.05f);

        // TV pegada a la pared derecha (-X) (tamaño ajustado a los límites)
        Cubo.dibujar(
                -ancho / 2f - 0.04f, 1.42f, 0f,
                0.04f, 0.95f, largo * 0.95f,
                0.02f, 0.02f, 0.025f);

        // Pantalla interior
        Cubo.dibujar(
                -ancho / 2f - 0.07f, 1.42f, 0f,
                0.025f, 0.76f, largo * 0.90f,
                0.06f, 0.10f, 0.16f);

        glPopMatrix();
    }

    // ==========================================================
    // MESA GAMER + SILLA GAMER
    // Entre L5, M5, N5, O5
    // ==========================================================
    private static void dibujarMesaGamerConSilla(
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

        // Escritorio gamer
        dibujarCuboMadera(
                0f, 0.74f, 0f,
                ancho, 0.08f, largo,
                0.10f, 0.10f, 0.12f);

        // Franja RGB azul al frente (+Z)
        Cubo.dibujar(
                0f, 0.79f, largo / 2f + 0.015f,
                ancho * 0.90f, 0.035f, 0.025f,
                0.05f, 0.35f, 0.95f);

        // Patas
        dibujarCuboMadera(-ancho / 2f + 0.05f, 0.36f, -largo / 2f + 0.05f, 0.06f, 0.72f, 0.06f,
                0.05f, 0.05f, 0.05f);
        dibujarCuboMadera(ancho / 2f - 0.05f, 0.36f, -largo / 2f + 0.05f, 0.06f, 0.72f, 0.06f,
                0.05f, 0.05f, 0.05f);
        dibujarCuboMadera(-ancho / 2f + 0.05f, 0.36f, largo / 2f - 0.05f, 0.06f, 0.72f, 0.06f,
                0.05f, 0.05f, 0.05f);
        dibujarCuboMadera(ancho / 2f - 0.05f, 0.36f, largo / 2f - 0.05f, 0.06f, 0.72f, 0.06f,
                0.05f, 0.05f, 0.05f);

        // Monitor gamer (hacia la pared -Z)
        Cubo.dibujar(
                0f, 1.05f, -largo * 0.15f,
                ancho * 0.46f, 0.40f, 0.035f,
                0.02f, 0.02f, 0.025f);

        Cubo.dibujar(
                0f, 1.05f, -largo * 0.18f,
                ancho * 0.38f, 0.31f, 0.02f,
                0.04f, 0.10f, 0.20f);

        // Teclado
        Cubo.dibujar(
                0f, 0.82f, largo * 0.18f,
                ancho * 0.42f, 0.035f, largo * 0.20f,
                0.02f, 0.02f, 0.02f);

        // Mouse
        Cubo.dibujar(
                -ancho * 0.28f, 0.83f, largo * 0.15f,
                ancho * 0.12f, 0.035f, largo * 0.16f,
                0.06f, 0.06f, 0.07f);

        glPopMatrix();
    }

    private static void dibujarSillaGamer(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Asiento
        Cubo.dibujar(
                0f, 0.32f, 0f,
                escalar(0.55f), 0.22f, escalar(0.48f),
                0.05f, 0.05f, 0.06f);

        // Cojín azul
        Cubo.dibujar(
                0f, 0.45f, 0f,
                escalar(0.45f), 0.08f, escalar(0.38f),
                0.05f, 0.28f, 0.90f);

        // Respaldo alto
        Cubo.dibujar(
                0f, 0.92f, -escalar(0.20f),
                escalar(0.50f), 0.95f, 0.10f,
                0.04f, 0.04f, 0.05f);

        // Franja del respaldo
        Cubo.dibujar(
                0f, 1.02f, -escalar(0.26f),
                escalar(0.30f), 0.70f, 0.035f,
                0.05f, 0.30f, 0.95f);

        // Base/pata central
        Cubo.dibujar(
                0f, 0.15f, 0f,
                0.08f, 0.30f, 0.08f,
                0.08f, 0.08f, 0.08f);

        // Brazos
        Cubo.dibujar(
                -escalar(0.32f), 0.55f, 0f,
                0.06f, 0.32f, escalar(0.45f),
                0.04f, 0.04f, 0.05f);

        Cubo.dibujar(
                escalar(0.32f), 0.55f, 0f,
                0.06f, 0.32f, escalar(0.45f),
                0.04f, 0.04f, 0.05f);

        glPopMatrix();
    }

    // ==========================================================
    // CLÓSET
    // ==========================================================
    private static void dibujarCloset(
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

        // Clóset ocupando todo el ancho y largo disponible
        dibujarCuboMadera(
                0f, 1.40f, 0f,
                ancho, 2.80f, largo,
                0.38f, 0.23f, 0.13f);

        // Puertas del clóset (al frente en +Z)
        float puertaAncho = ancho / 5f;
        for (int i = 0; i < 5; i++) {
            float px = -ancho / 2f + puertaAncho / 2f + i * puertaAncho;

            dibujarCuboMadera(
                    px, 1.40f, largo / 2f + 0.015f,
                    puertaAncho * 0.90f, 2.60f, 0.025f,
                    0.46f, 0.29f, 0.18f);

            Cubo.dibujar(
                    px + puertaAncho * 0.25f, 1.40f, largo / 2f + 0.035f,
                    0.025f, 0.40f, 0.025f,
                    0.75f, 0.75f, 0.72f);
        }

        glPopMatrix();
    }

    // ==========================================================
    // MESA DE ESTUDIO + SILLA
    // ==========================================================
    private static void dibujarMesaSillaEstudio(
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

        // Mesa
        dibujarCuboMadera(
                0f, 0.42f, 0f,
                ancho, 0.08f, largo,
                0.45f, 0.28f, 0.16f);

        // Cajón / Base
        dibujarCuboMadera(
                0f, 0.22f, 0f,
                ancho * 0.90f, 0.06f, largo * 0.80f,
                0.30f, 0.18f, 0.10f);

        glPopMatrix();

        // Silla básica frente a la mesa
        dibujarSillaBasica(centroX, centroZ + 0.35f, rotacionY);
    }

    private static void dibujarSillaBasica(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Asiento
        dibujarCuboMadera(0f, 0.25f, 0f, 0.35f, 0.05f, 0.35f, 0.30f, 0.18f, 0.10f);

        // Patas
        dibujarCuboMadera(-0.15f, 0.125f, -0.15f, 0.04f, 0.25f, 0.04f, 0.10f, 0.10f, 0.10f);
        dibujarCuboMadera(0.15f, 0.125f, -0.15f, 0.04f, 0.25f, 0.04f, 0.10f, 0.10f, 0.10f);
        dibujarCuboMadera(-0.15f, 0.125f, 0.15f, 0.04f, 0.25f, 0.04f, 0.10f, 0.10f, 0.10f);
        dibujarCuboMadera(0.15f, 0.125f, 0.15f, 0.04f, 0.25f, 0.04f, 0.10f, 0.10f, 0.10f);

        // Respaldo
        dibujarCuboMadera(0f, 0.50f, -0.15f, 0.35f, 0.45f, 0.05f, 0.30f, 0.18f, 0.10f);

        glPopMatrix();
    }

    // ==========================================================
    // ESTANTERÍA DEL PISO AL TECHO
    // ==========================================================
    private static void dibujarEstanteria(
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

        // Cuerpo principal del piso al techo
        dibujarCuboMadera(
                0f, 1.45f, 0f,
                ancho, 2.90f, largo,
                0.30f, 0.18f, 0.10f);

        // Repisas de la estantería
        for (float yRepisa = 0.45f; yRepisa <= 2.60f; yRepisa += 0.45f) {
            dibujarCuboMadera(
                    0f, yRepisa, 0f,
                    ancho * 0.90f, 0.06f, largo * 0.90f,
                    0.46f, 0.30f, 0.18f);
        }

        // Libros / adornos
        Cubo.dibujar(0.05f, 0.90f, 0f, 0.06f, 0.25f, 0.08f, 0.8f, 0.1f, 0.1f);
        Cubo.dibujar(-0.05f, 0.92f, 0f, 0.06f, 0.30f, 0.08f, 0.1f, 0.2f, 0.8f);
        Cubo.dibujar(0f, 1.78f, 0f, 0.12f, 0.16f, 0.12f, 0.85f, 0.80f, 0.65f);

        glPopMatrix();
    }

    // ==========================================================
    // TAPETE
    // ==========================================================
    private static void dibujarTapete(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        dibujarCuboPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y + 0.01f,
                0.035f,
                0.50f, 0.50f, 0.56f);

        dibujarCuboPorGeo(
                xMinGeo + 0.12f, zMinGeo + 0.15f,
                xMaxGeo - 0.12f, zMaxGeo - 0.15f,
                Y + 0.05f,
                0.018f,
                0.65f, 0.65f, 0.70f);
    }
}