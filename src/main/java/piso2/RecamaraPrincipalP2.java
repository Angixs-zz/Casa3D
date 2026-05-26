package piso2;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class RecamaraPrincipalP2 {

    public static int texturaMueble = 0;
    public static int texturaColcha = 0;
    public static int texturaSillon = 0;

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
         * RECÁMARA PRINCIPAL - PISO 2
         *
         * Sillón:
         * G7 = (5.0, 3.0)
         * H7 = (6.4, 3.0)
         * I7 = (6.4, 1.7)
         * J7 = (3.7, 1.7)
         * K7 = (3.7, 2.4)
         * L7 = (5.0, 2.4)
         *
         * Mesa:
         * M7 = (3.4, 3.4)
         * N7 = (3.4, 2.8)
         * O7 = (4.4, 2.8)
         * P7 = (4.4, 3.4)
         *
         * Cama:
         * Q7 = (1.2, 4.2)
         * R7 = (1.2, 2.6)
         * S7 = (2.5, 2.6)
         * T7 = (2.5, 4.2)
         *
         * Buró 1:
         * U7 = (0.8, 4.2)
         * V7 = (0.8, 3.9)
         * W7 = (1.1, 3.9)
         * Z7 = (1.1, 4.2)
         *
         * Buró 2:
         * A8 = (2.6, 4.2)
         * B8 = (2.6, 3.9)
         * C8 = (2.9, 3.9)
         * D8 = (2.9, 4.2)
         *
         * Mesa:
         * E8 = (0.2, 1.7)
         * F8 = (0.2, 2.1)
         * G8 = (1.9, 2.1)
         * H8 = (1.9, 1.7)
         *
         * TV colgada:
         * I8 = (0.2, 2.4)
         * J8 = (0.9, 1.7)
         */

        // Cambia el último número para girar: 0f, 90f, -90f, 180f

        dibujarCamaPrincipal(1.2f, 2.6f, 2.5f, 4.2f, 0f);

        dibujarBuro(0.8f, 3.9f, 1.1f, 4.2f, 0f);
        dibujarBuro(2.6f, 3.9f, 2.9f, 4.2f, 0f);

        dibujarSillonPrincipal();
        dibujarMesaCentro(3.4f, 2.8f, 4.4f, 3.4f, 0f);

        dibujarMesaDecorativa(0.2f, 1.7f, 1.9f, 2.1f, 0f);

        dibujarTVColgada(1.2f, 1.6f, 0.1f, 1.6f); // Entre Q2 y J

        dibujarTapete(1.05f, 2.15f, 4.75f, 3.75f);

        dibujarDecoracionExtra();
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

    private static void dibujarCuboPorGeoTextura(
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
    // CAMA PRINCIPAL
    // Entre Q7, R7, S7, T7
    // ==========================================================
    private static void dibujarCamaPrincipal(
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
        if (texturaMueble > 0) {
            Cubo.dibujarConTextura(
                    0f, 0.18f, 0f,
                    ancho, 0.36f, largo,
                    texturaMueble);
        } else {
            Cubo.dibujar(
                    0f, 0.18f, 0f,
                    ancho, 0.36f, largo,
                    0.32f, 0.19f, 0.10f);
        }

        // Colchón grande
        Cubo.dibujar(
                0f, 0.48f, 0f,
                ancho * 0.92f, 0.22f, largo * 0.92f,
                0.92f, 0.92f, 0.88f);

        // Cobija elegante
        if (texturaColcha > 0) {
            Cubo.dibujarConTextura(
                    0f, 0.63f, -largo * 0.10f,
                    ancho * 0.86f, 0.08f, largo * 0.58f,
                    texturaColcha);
        } else {
            Cubo.dibujar(
                    0f, 0.63f, -largo * 0.10f,
                    ancho * 0.86f, 0.08f, largo * 0.58f,
                    0.24f, 0.34f, 0.58f);
        }

        // Franja decorativa de la cobija
        Cubo.dibujar(
                0f, 0.69f, -largo * 0.27f,
                ancho * 0.86f, 0.04f, largo * 0.10f,
                0.78f, 0.76f, 0.70f);

        // Almohadas
        Cubo.dibujar(
                -ancho * 0.23f, 0.72f, largo * 0.32f,
                ancho * 0.35f, 0.12f, largo * 0.18f,
                0.96f, 0.96f, 0.94f);

        Cubo.dibujar(
                ancho * 0.23f, 0.72f, largo * 0.32f,
                ancho * 0.35f, 0.12f, largo * 0.18f,
                0.96f, 0.96f, 0.94f);

        // Cabecera alta
        if (texturaMueble > 0) {
            Cubo.dibujarConTextura(
                    0f, 0.65f, largo / 2f + 0.06f,
                    ancho, 1.30f, 0.12f,
                    texturaMueble);
        } else {
            Cubo.dibujar(
                    0f, 0.65f, largo / 2f + 0.06f,
                    ancho, 1.30f, 0.12f,
                    0.28f, 0.16f, 0.09f);
        }

        // Paneles de cabecera
        if (texturaMueble > 0) {
            Cubo.dibujarConTextura(
                    -ancho * 0.25f, 0.82f, largo / 2f + 0.13f,
                    ancho * 0.36f, 0.70f, 0.035f,
                    texturaMueble);

            Cubo.dibujarConTextura(
                    ancho * 0.25f, 0.82f, largo / 2f + 0.13f,
                    ancho * 0.36f, 0.70f, 0.035f,
                    texturaMueble);
        } else {
            Cubo.dibujar(
                    -ancho * 0.25f, 0.82f, largo / 2f + 0.13f,
                    ancho * 0.36f, 0.70f, 0.035f,
                    0.42f, 0.27f, 0.16f);

            Cubo.dibujar(
                    ancho * 0.25f, 0.82f, largo / 2f + 0.13f,
                    ancho * 0.36f, 0.70f, 0.035f,
                    0.42f, 0.27f, 0.16f);
        }

        glPopMatrix();
    }

    // ==========================================================
    // BURÓS
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
        if (texturaMueble > 0) {
            Cubo.dibujarConTextura(
                    0f, 0.28f, 0f,
                    ancho, 0.56f, largo,
                    texturaMueble);
        } else {
            Cubo.dibujar(
                    0f, 0.28f, 0f,
                    ancho, 0.56f, largo,
                    0.35f, 0.20f, 0.10f);
        }

        // Cajón
        if (texturaMueble > 0) {
            Cubo.dibujarConTextura(
                    0f, 0.34f, largo / 2f + 0.018f,
                    ancho * 0.82f, 0.18f, 0.035f,
                    texturaMueble);
        } else {
            Cubo.dibujar(
                    0f, 0.34f, largo / 2f + 0.018f,
                    ancho * 0.82f, 0.18f, 0.035f,
                    0.48f, 0.30f, 0.18f);
        }

        // Manija
        Cubo.dibujar(
                0f, 0.34f, largo / 2f + 0.045f,
                ancho * 0.32f, 0.035f, 0.025f,
                0.78f, 0.78f, 0.75f);

        // Lámpara
        Cubo.dibujar(
                0f, 0.68f, 0f,
                ancho * 0.20f, 0.24f, largo * 0.20f,
                0.12f, 0.12f, 0.12f);

        Cubo.dibujar(
                0f, 0.88f, 0f,
                ancho * 0.48f, 0.18f, largo * 0.42f,
                0.95f, 0.84f, 0.55f);

        glPopMatrix();
    }

    // ==========================================================
    // SILLÓN PRINCIPAL
    // Entre G7, H7, I7, J7, K7, L7
    // ==========================================================
    private static void dibujarSillonPrincipal() {
        float altoAsiento = 0.35f;
        float altoRespaldo = 0.75f;

        // Partes del sillón
        if (texturaSillon > 0) {
            dibujarCuboPorGeoTextura(3.7f, 1.7f, 6.4f, 2.4f, Y, altoAsiento, texturaSillon);
            dibujarCuboPorGeoTextura(5.0f, 2.4f, 6.4f, 3.0f, Y, altoAsiento, texturaSillon);
            dibujarCuboPorGeoTextura(3.7f, 1.7f, 6.4f, 1.82f, Y + altoAsiento, altoRespaldo, texturaSillon);
            dibujarCuboPorGeoTextura(6.28f, 1.7f, 6.4f, 3.0f, Y + altoAsiento, altoRespaldo, texturaSillon);
            dibujarCuboPorGeoTextura(3.7f, 1.7f, 3.82f, 2.4f, Y + altoAsiento, 0.55f, texturaSillon);
        } else {
            // Parte larga horizontal inferior del sillón
            dibujarCuboPorGeo(
                    3.7f, 1.7f,
                    6.4f, 2.4f,
                    Y,
                    altoAsiento,
                    0.70f, 0.68f, 0.63f);

            // Parte superior derecha del sillón
            dibujarCuboPorGeo(
                    5.0f, 2.4f,
                    6.4f, 3.0f,
                    Y,
                    altoAsiento,
                    0.72f, 0.70f, 0.66f);

            // Respaldo largo trasero J7-I7
            dibujarCuboPorGeo(
                    3.7f, 1.7f,
                    6.4f, 1.82f,
                    Y + altoAsiento,
                    altoRespaldo,
                    0.58f, 0.56f, 0.52f);

            // Respaldo lateral derecho H7-I7
            dibujarCuboPorGeo(
                    6.28f, 1.7f,
                    6.4f, 3.0f,
                    Y + altoAsiento,
                    altoRespaldo,
                    0.58f, 0.56f, 0.52f);

            // Brazo izquierdo en J7-K7
            dibujarCuboPorGeo(
                    3.7f, 1.7f,
                    3.82f, 2.4f,
                    Y + altoAsiento,
                    0.55f,
                    0.60f, 0.58f, 0.54f);
        }

        // Cojines
        dibujarCojinSillon(4.20f, 2.05f, 0f);
        dibujarCojinSillon(4.90f, 2.05f, 0f);
        dibujarCojinSillon(5.60f, 2.05f, 0f);
        dibujarCojinSillon(5.70f, 2.70f, 0f);

        // Almohadas decorativas
        dibujarAlmohadaDecorativa(3.95f, 2.10f, 0f);
        dibujarAlmohadaDecorativa(6.10f, 2.70f, 0f);
    }

    private static void dibujarCojinSillon(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        if (texturaSillon > 0) {
            Cubo.dibujarConTextura(
                    0f, 0.50f, 0f,
                    escalar(0.55f), 0.16f, escalar(0.42f),
                    texturaSillon);
        } else {
            Cubo.dibujar(
                    0f, 0.50f, 0f,
                    escalar(0.55f), 0.16f, escalar(0.42f),
                    0.78f, 0.76f, 0.72f);
        }

        glPopMatrix();
    }

    private static void dibujarAlmohadaDecorativa(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        if (texturaSillon > 0) {
            Cubo.dibujarConTextura(
                    0f, 0.82f, 0f,
                    escalar(0.25f), 0.18f, escalar(0.20f),
                    texturaSillon);
        } else {
            Cubo.dibujar(
                    0f, 0.82f, 0f,
                    escalar(0.25f), 0.18f, escalar(0.20f),
                    0.86f, 0.80f, 0.70f);
        }

        glPopMatrix();
    }

    // ==========================================================
    // MESA DE CENTRO
    // Entre M7, N7, O7, P7
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

        // Tapa de mesa
        if (texturaMueble > 0) {
            Cubo.dibujarConTextura(
                    0f, 0.42f, 0f,
                    ancho, 0.08f, largo,
                    texturaMueble);
        } else {
            Cubo.dibujar(
                    0f, 0.42f, 0f,
                    ancho, 0.08f, largo,
                    0.45f, 0.30f, 0.18f);
        }

        // Repisa inferior
        if (texturaMueble > 0) {
            Cubo.dibujarConTextura(
                    0f, 0.22f, 0f,
                    ancho * 0.88f, 0.06f, largo * 0.84f,
                    texturaMueble);
        } else {
            Cubo.dibujar(
                    0f, 0.22f, 0f,
                    ancho * 0.88f, 0.06f, largo * 0.84f,
                    0.30f, 0.19f, 0.11f);
        }

        // Patas
        Cubo.dibujar(-ancho / 2f + 0.06f, 0.20f, -largo / 2f + 0.06f, 0.06f, 0.40f, 0.06f,
                0.12f, 0.12f, 0.12f);
        Cubo.dibujar(ancho / 2f - 0.06f, 0.20f, -largo / 2f + 0.06f, 0.06f, 0.40f, 0.06f,
                0.12f, 0.12f, 0.12f);
        Cubo.dibujar(-ancho / 2f + 0.06f, 0.20f, largo / 2f - 0.06f, 0.06f, 0.40f, 0.06f,
                0.12f, 0.12f, 0.12f);
        Cubo.dibujar(ancho / 2f - 0.06f, 0.20f, largo / 2f - 0.06f, 0.06f, 0.40f, 0.06f,
                0.12f, 0.12f, 0.12f);

        // Decoración sobre mesa
        Cubo.dibujar(
                -ancho * 0.20f, 0.51f, 0f,
                ancho * 0.24f, 0.04f, largo * 0.30f,
                0.90f, 0.86f, 0.76f);

        Cubo.dibujar(
                ancho * 0.22f, 0.58f, 0f,
                0.08f, 0.16f, 0.08f,
                0.12f, 0.48f, 0.16f);

        glPopMatrix();
    }

    // ==========================================================
    // MESA DECORATIVA
    // Entre E8, F8, G8, H8
    // ==========================================================
    private static void dibujarMesaDecorativa(
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

        // Mesa/mueble bajo
        if (texturaMueble > 0) {
            Cubo.dibujarConTextura(
                    0f, 0.32f, 0f,
                    ancho, 0.64f, largo,
                    texturaMueble);
        } else {
            Cubo.dibujar(
                    0f, 0.32f, 0f,
                    ancho, 0.64f, largo,
                    0.34f, 0.22f, 0.14f);
        }

        // Tapa superior
        if (texturaMueble > 0) {
            Cubo.dibujarConTextura(
                    0f, 0.68f, 0f,
                    ancho * 1.04f, 0.08f, largo * 1.06f,
                    texturaMueble);
        } else {
            Cubo.dibujar(
                    0f, 0.68f, 0f,
                    ancho * 1.04f, 0.08f, largo * 1.06f,
                    0.46f, 0.30f, 0.18f);
        }

        // Cajones
        if (texturaMueble > 0) {
            Cubo.dibujarConTextura(
                    -ancho * 0.25f, 0.36f, largo / 2f + 0.015f,
                    ancho * 0.36f, 0.18f, 0.035f,
                    texturaMueble);

            Cubo.dibujarConTextura(
                    ancho * 0.25f, 0.36f, largo / 2f + 0.015f,
                    ancho * 0.36f, 0.18f, 0.035f,
                    texturaMueble);
        } else {
            Cubo.dibujar(
                    -ancho * 0.25f, 0.36f, largo / 2f + 0.015f,
                    ancho * 0.36f, 0.18f, 0.035f,
                    0.48f, 0.30f, 0.18f);

            Cubo.dibujar(
                    ancho * 0.25f, 0.36f, largo / 2f + 0.015f,
                    ancho * 0.36f, 0.18f, 0.035f,
                    0.48f, 0.30f, 0.18f);
        }

        // Adornos
        Cubo.dibujar(
                -ancho * 0.35f, 0.82f, 0f,
                0.10f, 0.22f, 0.10f,
                0.12f, 0.45f, 0.18f);

        Cubo.dibujar(
                ancho * 0.10f, 0.78f, 0f,
                0.24f, 0.08f, 0.16f,
                0.80f, 0.76f, 0.68f);

        glPopMatrix();
    }

    // ==========================================================
    // TELEVISIÓN COLGADA EN LA PARED
    // Segmento I8 - J8
    // ==========================================================
    private static void dibujarTVColgada(float x1Geo, float z1Geo, float x2Geo, float z2Geo) {
        float centroXGeo = (x1Geo + x2Geo) / 2.0f;
        float centroZGeo = (z1Geo + z2Geo) / 2.0f;

        float dx = x2Geo - x1Geo;
        float dz = z2Geo - z1Geo;

        float largoGeo = (float) Math.sqrt(dx * dx + dz * dz);
        float angulo = (float) Math.toDegrees(Math.atan2(dz, dx));

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Orientación siguiendo el segmento I8 - J8
        glRotatef(-angulo + 180f, 0f, 1f, 0f);

        // Desplazar la TV hacia el cuarto (+Z local) asegurando que no se asome hacia el balcón
        glTranslatef(0f, 0f, 0.15f);

        // Panel principal de la TV
        Cubo.dibujar(
                0f, 1.65f, 0f,
                escalar(largoGeo), 0.75f, 0.045f,
                0.02f, 0.02f, 0.025f);

        // Pantalla visible hacia el cuarto
        // Antes estaba en -0.03f, por eso miraba hacia la pared.
        // Ahora está en +0.03f.
        Cubo.dibujar(
                0f, 1.65f, 0.03f,
                escalar(largoGeo) * 0.86f, 0.58f, 0.025f,
                0.05f, 0.10f, 0.18f);

        // Marco superior
        Cubo.dibujar(
                0f, 2.05f, 0.035f,
                escalar(largoGeo), 0.05f, 0.035f,
                0.01f, 0.01f, 0.01f);

        // Marco inferior
        Cubo.dibujar(
                0f, 1.25f, 0.035f,
                escalar(largoGeo), 0.05f, 0.035f,
                0.01f, 0.01f, 0.01f);

        // Marco izquierdo
        Cubo.dibujar(
                -escalar(largoGeo) / 2f + 0.03f, 1.65f, 0.035f,
                0.05f, 0.75f, 0.035f,
                0.01f, 0.01f, 0.01f);

        // Marco derecho
        Cubo.dibujar(
                escalar(largoGeo) / 2f - 0.03f, 1.65f, 0.035f,
                0.05f, 0.75f, 0.035f,
                0.01f, 0.01f, 0.01f);

        // Soporte hacia la pared, atrás de la TV
        Cubo.dibujar(
                0f, 1.18f, -0.04f,
                escalar(largoGeo) * 0.22f, 0.20f, 0.05f,
                0.08f, 0.08f, 0.08f);

        glPopMatrix();
    }

    // ==========================================================
    // TAPETE Y DECORACIÓN
    // ==========================================================
    private static void dibujarTapete(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        dibujarCuboPorGeo(
                xMinGeo, zMinGeo,
                xMaxGeo, zMaxGeo,
                Y + 0.01f,
                0.035f,
                0.48f, 0.48f, 0.54f);

        dibujarCuboPorGeo(
                xMinGeo + 0.12f, zMinGeo + 0.12f,
                xMaxGeo - 0.12f, zMaxGeo - 0.12f,
                Y + 0.045f,
                0.018f,
                0.66f, 0.66f, 0.70f);
    }

    private static void dibujarDecoracionExtra() {
        // Planta decorativa
        dibujarCuboPorGeo(
                6.05f, 3.20f,
                6.30f, 3.45f,
                Y,
                0.35f,
                0.52f, 0.32f, 0.18f);

        dibujarCuboPorGeo(
                5.95f, 3.10f,
                6.40f, 3.55f,
                Y + 0.35f,
                0.50f,
                0.10f, 0.48f, 0.16f);

        // Cuadro decorativo sobre la zona de mesa
        dibujarCuboPorGeo(
                0.55f, 1.55f,
                1.45f, 1.60f,
                Y + 1.45f,
                0.60f,
                0.16f, 0.16f, 0.18f);

        dibujarCuboPorGeo(
                0.67f, 1.52f,
                1.33f, 1.57f,
                Y + 1.52f,
                0.42f,
                0.58f, 0.68f, 0.78f);
    }
}