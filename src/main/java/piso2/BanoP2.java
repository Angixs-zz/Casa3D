package piso2;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class BanoP2 {

        private static final float Y = Constantes.ALTURA_PISO_2;

        public static int texturaPiso = 0;
        public static int texturaMueble = 0;
        public static int texturaCeramica = 0;
        public static int texturaTapete = 0;
        public static int texturaDecoracion = 0;

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
                 * BAÑO - PISO 2
                 *
                 * Bañera:
                 * O6 = (7.1, 5.8)
                 * P6 = (7.1, 4.4)
                 * Q6 = (7.8, 4.4)
                 * R6 = (7.8, 5.8)
                 *
                 * Regadera:
                 * S6 = (5.0, 4.3)
                 * T6 = (5.0, 3.6)
                 * U6 = (6.5, 3.6)
                 * V6 = (6.5, 4.3)
                 *
                 * Retrete:
                 * W6 = (6.2, 5.8)
                 * Z6 = (6.2, 5.1)
                 * A7 = (6.6, 5.1)
                 * B7 = (6.6, 5.8)
                 *
                 * Lavabo:
                 * C7 = (5.0, 5.8)
                 * D7 = (5.0, 5.3)
                 * E7 = (6.1, 5.3)
                 * F7 = (6.1, 5.8)
                 */

                dibujarBañera(7.1f, 4.4f, 7.8f, 5.8f, 0f);

                dibujarRegaderaCristal(5.0f, 3.6f, 6.5f, 4.3f, 0f);

                dibujarRetrete(6.2f, 5.1f, 6.6f, 5.8f, 180f);

                dibujarLavabo(5.0f, 5.3f, 6.1f, 5.8f, 180f);

                // Detalles del baño
                dibujarTapete(5.15f, 4.65f, 6.85f, 5.05f);
                dibujarToallerosYDecoracion();
        }

        private static void dibujarCuboPorGeo(
                        float xMinGeo, float zMinGeo,
                        float xMaxGeo, float zMaxGeo,
                        float yBase, float altura,
                        float r, float g, float b, int texturaID) {

                float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
                float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

                float anchoGeo = xMaxGeo - xMinGeo;
                float largoGeo = zMaxGeo - zMinGeo;

                float x = convertirXGeoAOpenGL(centroXGeo);
                float z = convertirZGeoAOpenGL(centroZGeo);

                float ancho = escalar(anchoGeo);
                float largo = escalar(largoGeo);

                if (texturaID != 0) {
                        Cubo.dibujarConTextura(x, yBase + altura / 2.0f, z, ancho, altura, largo, texturaID);
                } else {
                        Cubo.dibujar(x, yBase + altura / 2.0f, z, ancho, altura, largo, r, g, b);
                }
        }

        // ==========================================================
        // BAÑERA DECORADA
        // Entre O6, P6, Q6, R6
        // ==========================================================
        private static void dibujarBañera(
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

                // Base exterior de la bañera
                if (texturaCeramica != 0) {
                        Cubo.dibujarConTextura(0f, 0.30f, 0f, ancho, 0.60f, largo, texturaCeramica);
                        Cubo.dibujarConTextura(0f, 0.66f, 0f, ancho * 1.05f, 0.12f, largo * 1.04f, texturaCeramica);
                        Cubo.dibujarConTextura(0f, 0.72f, 0f, ancho * 0.72f, 0.08f, largo * 0.74f, texturaCeramica);
                } else {
                        Cubo.dibujar(0f, 0.30f, 0f, ancho, 0.60f, largo, 0.93f, 0.93f, 0.90f);
                        Cubo.dibujar(0f, 0.66f, 0f, ancho * 1.05f, 0.12f, largo * 1.04f, 0.98f, 0.98f, 0.95f);
                        Cubo.dibujar(0f, 0.72f, 0f, ancho * 0.72f, 0.08f, largo * 0.74f, 0.70f, 0.74f, 0.76f);
                }

                // Agua
                Cubo.dibujar(
                                0f, 0.78f, 0f,
                                ancho * 0.62f, 0.035f, largo * 0.64f,
                                0.30f, 0.62f, 0.78f);

                // Respaldo/cabecera de la bañera
                Cubo.dibujar(
                                0f, 0.68f, largo / 2f - 0.06f,
                                ancho * 0.85f, 0.48f, 0.12f,
                                0.92f, 0.92f, 0.88f);

                // Grifo cromado
                Cubo.dibujar(
                                -ancho * 0.32f, 0.95f, -largo * 0.32f,
                                0.05f, 0.32f, 0.05f,
                                0.65f, 0.65f, 0.65f);

                Cubo.dibujar(
                                -ancho * 0.22f, 1.09f, -largo * 0.32f,
                                0.22f, 0.05f, 0.05f,
                                0.65f, 0.65f, 0.65f);

                // Llaves de agua
                Cubo.dibujar(
                                -ancho * 0.42f, 0.88f, -largo * 0.24f,
                                0.07f, 0.05f, 0.07f,
                                0.10f, 0.20f, 0.80f);

                Cubo.dibujar(
                                -ancho * 0.42f, 0.88f, -largo * 0.40f,
                                0.07f, 0.05f, 0.07f,
                                0.85f, 0.10f, 0.10f);

                // Jabón / esponja en el borde
                Cubo.dibujar(
                                ancho * 0.26f, 0.85f, largo * 0.36f,
                                0.18f, 0.06f, 0.10f,
                                0.95f, 0.85f, 0.45f);

                Cubo.dibujar(
                                ancho * 0.05f, 0.86f, largo * 0.38f,
                                0.14f, 0.08f, 0.12f,
                                0.75f, 0.75f, 0.70f);

                glPopMatrix();
        }

        // ==========================================================
        // REGADERA CON CRISTAL
        // Entre S6, T6, U6, V6
        // ==========================================================
        private static void dibujarRegaderaCristal(
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

                // Piso de la regadera
                if (texturaPiso != 0) {
                        Cubo.dibujarConTextura(0f, 0.04f, 0f, ancho, 0.08f, largo, texturaPiso);
                        Cubo.dibujarConTextura(0f, 0.10f, 0f, ancho * 0.82f, 0.04f, largo * 0.76f, texturaPiso);
                } else {
                        Cubo.dibujar(0f, 0.04f, 0f, ancho, 0.08f, largo, 0.55f, 0.55f, 0.52f);
                        Cubo.dibujar(0f, 0.10f, 0f, ancho * 0.82f, 0.04f, largo * 0.76f, 0.72f, 0.72f, 0.70f);
                }

                // Cristal frontal
                Cubo.dibujar(
                                0f, 1.10f, largo / 2f + 0.015f,
                                ancho, 2.10f, 0.035f,
                                0.55f, 0.78f, 0.88f);

                // Cristal lateral izquierdo
                Cubo.dibujar(
                                -ancho / 2f + 0.015f, 1.10f, 0f,
                                0.035f, 2.10f, largo,
                                0.55f, 0.78f, 0.88f);

                // Marco negro de cristal
                Cubo.dibujar(
                                0f, 2.15f, largo / 2f + 0.04f,
                                ancho, 0.05f, 0.05f,
                                0.05f, 0.05f, 0.05f);

                Cubo.dibujar(
                                -ancho / 2f + 0.03f, 1.10f, largo / 2f + 0.04f,
                                0.05f, 2.10f, 0.05f,
                                0.05f, 0.05f, 0.05f);

                Cubo.dibujar(
                                ancho / 2f - 0.03f, 1.10f, largo / 2f + 0.04f,
                                0.05f, 2.10f, 0.05f,
                                0.05f, 0.05f, 0.05f);

                // Regadera superior
                Cubo.dibujar(
                                0f, 2.15f, -largo / 2f + 0.10f,
                                0.07f, 0.60f, 0.07f,
                                0.62f, 0.62f, 0.62f);

                Cubo.dibujar(
                                0f, 2.45f, -largo / 2f + 0.28f,
                                0.40f, 0.07f, 0.18f,
                                0.65f, 0.65f, 0.65f);

                // Chorros de agua simulados
                Cubo.dibujar(
                                -0.10f, 1.75f, -largo / 2f + 0.32f,
                                0.025f, 0.95f, 0.025f,
                                0.35f, 0.70f, 0.95f);

                Cubo.dibujar(
                                0f, 1.70f, -largo / 2f + 0.32f,
                                0.025f, 1.05f, 0.025f,
                                0.35f, 0.70f, 0.95f);

                Cubo.dibujar(
                                0.10f, 1.75f, -largo / 2f + 0.32f,
                                0.025f, 0.95f, 0.025f,
                                0.35f, 0.70f, 0.95f);

                // Repisa pequeña para shampoo
                Cubo.dibujar(
                                ancho * 0.30f, 1.05f, -largo / 2f + 0.08f,
                                0.28f, 0.06f, 0.14f,
                                0.35f, 0.35f, 0.35f);

                // Botellas de shampoo
                Cubo.dibujar(
                                ancho * 0.24f, 1.22f, -largo / 2f + 0.08f,
                                0.07f, 0.28f, 0.07f,
                                0.10f, 0.45f, 0.90f);

                Cubo.dibujar(
                                ancho * 0.36f, 1.18f, -largo / 2f + 0.08f,
                                0.07f, 0.22f, 0.07f,
                                0.90f, 0.90f, 0.25f);

                glPopMatrix();
        }

        // ==========================================================
        // RETRETE
        // Entre W6, Z6, A7, B7
        // ==========================================================
        private static void dibujarRetrete(
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

                // Base inferior
                if (texturaCeramica != 0) {
                        Cubo.dibujarConTextura(0f, 0.18f, 0f, ancho * 0.78f, 0.36f, largo * 0.58f, texturaCeramica);
                        Cubo.dibujarConTextura(0f, 0.48f, 0.04f, ancho * 0.86f, 0.36f, largo * 0.68f, texturaCeramica);
                        Cubo.dibujarConTextura(0f, 0.60f, 0.06f, ancho * 0.48f, 0.07f, largo * 0.36f, texturaCeramica);
                        Cubo.dibujarConTextura(0f, 0.66f, 0.06f, ancho * 0.60f, 0.04f, largo * 0.46f, texturaCeramica);
                        Cubo.dibujarConTextura(0f, 0.95f, -largo / 2f + 0.08f, ancho * 0.86f, 0.65f, largo * 0.22f, texturaCeramica);
                        Cubo.dibujarConTextura(0f, 1.30f, -largo / 2f + 0.08f, ancho * 0.94f, 0.07f, largo * 0.26f, texturaCeramica);
                } else {
                        Cubo.dibujar(0f, 0.18f, 0f, ancho * 0.78f, 0.36f, largo * 0.58f, 0.92f, 0.92f, 0.90f);
                        Cubo.dibujar(0f, 0.48f, 0.04f, ancho * 0.86f, 0.36f, largo * 0.68f, 0.96f, 0.96f, 0.94f);
                        Cubo.dibujar(0f, 0.60f, 0.06f, ancho * 0.48f, 0.07f, largo * 0.36f, 0.35f, 0.46f, 0.52f);
                        Cubo.dibujar(0f, 0.66f, 0.06f, ancho * 0.60f, 0.04f, largo * 0.46f, 0.88f, 0.88f, 0.86f);
                        Cubo.dibujar(0f, 0.95f, -largo / 2f + 0.08f, ancho * 0.86f, 0.65f, largo * 0.22f, 0.95f, 0.95f, 0.92f);
                        Cubo.dibujar(0f, 1.30f, -largo / 2f + 0.08f, ancho * 0.94f, 0.07f, largo * 0.26f, 0.88f, 0.88f, 0.86f);
                }

                // Botón de descarga
                Cubo.dibujar(
                                ancho * 0.22f, 1.36f, -largo / 2f + 0.08f,
                                0.08f, 0.035f, 0.08f,
                                0.65f, 0.65f, 0.65f);

                // Papel higiénico lateral
                Cubo.dibujar(
                                ancho * 0.58f, 0.88f, 0f,
                                0.10f, 0.18f, 0.18f,
                                0.95f, 0.95f, 0.90f);

                Cubo.dibujar(
                                ancho * 0.58f, 0.88f, 0f,
                                0.035f, 0.20f, 0.035f,
                                0.50f, 0.50f, 0.50f);

                glPopMatrix();
        }

        // ==========================================================
        // LAVABO
        // Entre C7, D7, E7, F7
        // ==========================================================
        private static void dibujarLavabo(
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

                // Mueble base
                if (texturaMueble != 0) {
                        Cubo.dibujarConTextura(0f, 0.38f, 0f, ancho, 0.76f, largo, texturaMueble);
                        Cubo.dibujarConTextura(-ancho * 0.25f, 0.38f, largo / 2f + 0.015f, ancho * 0.38f, 0.46f, 0.035f, texturaMueble);
                        Cubo.dibujarConTextura(ancho * 0.25f, 0.38f, largo / 2f + 0.015f, ancho * 0.38f, 0.46f, 0.035f, texturaMueble);
                } else {
                        Cubo.dibujar(0f, 0.38f, 0f, ancho, 0.76f, largo, 0.42f, 0.30f, 0.22f);
                        Cubo.dibujar(-ancho * 0.25f, 0.38f, largo / 2f + 0.015f, ancho * 0.38f, 0.46f, 0.035f, 0.50f, 0.36f, 0.26f);
                        Cubo.dibujar(ancho * 0.25f, 0.38f, largo / 2f + 0.015f, ancho * 0.38f, 0.46f, 0.035f, 0.50f, 0.36f, 0.26f);
                }

                // Manijas
                Cubo.dibujar(-ancho * 0.25f, 0.45f, largo / 2f + 0.045f, 0.14f, 0.035f, 0.025f, 0.78f, 0.78f, 0.75f);
                Cubo.dibujar(ancho * 0.25f, 0.45f, largo / 2f + 0.045f, 0.14f, 0.035f, 0.025f, 0.78f, 0.78f, 0.75f);

                // Cubierta blanca y lavabo
                if (texturaCeramica != 0) {
                        Cubo.dibujarConTextura(0f, 0.80f, 0f, ancho * 1.04f, 0.08f, largo * 1.06f, texturaCeramica);
                        Cubo.dibujarConTextura(0f, 0.86f, 0f, ancho * 0.52f, 0.08f, largo * 0.50f, texturaCeramica);
                        Cubo.dibujarConTextura(0f, 0.91f, 0f, ancho * 0.34f, 0.05f, largo * 0.32f, texturaCeramica);
                } else {
                        Cubo.dibujar(0f, 0.80f, 0f, ancho * 1.04f, 0.08f, largo * 1.06f, 0.95f, 0.95f, 0.92f);
                        Cubo.dibujar(0f, 0.86f, 0f, ancho * 0.52f, 0.08f, largo * 0.50f, 0.78f, 0.80f, 0.80f);
                        Cubo.dibujar(0f, 0.91f, 0f, ancho * 0.34f, 0.05f, largo * 0.32f, 0.32f, 0.42f, 0.46f);
                }

                // Agua
                Cubo.dibujar(
                                0f, 0.95f, 0f,
                                ancho * 0.25f, 0.02f, largo * 0.24f,
                                0.25f, 0.55f, 0.75f);

                // Grifo
                Cubo.dibujar(
                                0f, 1.12f, -largo * 0.24f,
                                0.05f, 0.32f, 0.05f,
                                0.62f, 0.62f, 0.62f);

                Cubo.dibujar(
                                0f, 1.26f, -largo * 0.05f,
                                0.05f, 0.05f, largo * 0.30f,
                                0.62f, 0.62f, 0.62f);

                // Espejo sobre el lavabo
                Cubo.dibujar(
                                0f, 1.70f, -largo / 2f - 0.04f,
                                ancho * 0.76f, 0.85f, 0.04f,
                                0.55f, 0.72f, 0.78f);

                // Marco del espejo
                Cubo.dibujar(
                                0f, 2.15f, -largo / 2f - 0.06f,
                                ancho * 0.84f, 0.06f, 0.05f,
                                0.12f, 0.12f, 0.12f);

                Cubo.dibujar(
                                0f, 1.25f, -largo / 2f - 0.06f,
                                ancho * 0.84f, 0.06f, 0.05f,
                                0.12f, 0.12f, 0.12f);

                Cubo.dibujar(
                                -ancho * 0.42f, 1.70f, -largo / 2f - 0.06f,
                                0.05f, 0.90f, 0.05f,
                                0.12f, 0.12f, 0.12f);

                Cubo.dibujar(
                                ancho * 0.42f, 1.70f, -largo / 2f - 0.06f,
                                0.05f, 0.90f, 0.05f,
                                0.12f, 0.12f, 0.12f);

                // Jabón y cepillos
                Cubo.dibujar(
                                -ancho * 0.35f, 0.90f, largo * 0.22f,
                                0.12f, 0.05f, 0.08f,
                                0.95f, 0.85f, 0.45f);

                Cubo.dibujar(
                                ancho * 0.34f, 1.02f, largo * 0.22f,
                                0.10f, 0.24f, 0.10f,
                                0.15f, 0.45f, 0.85f);

                Cubo.dibujar(
                                ancho * 0.34f, 1.20f, largo * 0.22f,
                                0.025f, 0.20f, 0.025f,
                                0.95f, 0.95f, 0.95f);

                glPopMatrix();
        }

        // ==========================================================
        // TAPETE Y DECORACIÓN EXTRA
        // ==========================================================
        private static void dibujarTapete(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
                dibujarCuboPorGeo(
                                xMinGeo, zMinGeo,
                                xMaxGeo, zMaxGeo,
                                Y + 0.01f,
                                0.035f,
                                0.42f, 0.52f, 0.58f, texturaTapete);

                dibujarCuboPorGeo(
                                xMinGeo + 0.10f, zMinGeo + 0.06f,
                                xMaxGeo - 0.10f, zMaxGeo - 0.06f,
                                Y + 0.045f,
                                0.018f,
                                0.65f, 0.72f, 0.76f, texturaTapete);
        }

        private static void dibujarToallerosYDecoracion() {
                // Toallero decorativo cerca del lavabo
                dibujarCuboPorGeo(5.0f, 5.95f, 6.1f, 6.02f, Y + 1.30f, 0.06f, 0.65f, 0.65f, 0.65f, 0);

                // Toalla colgada
                dibujarCuboPorGeo(5.35f, 5.98f, 5.75f, 6.04f, Y + 0.92f, 0.70f, 0.82f, 0.82f, 0.78f, texturaTapete);

                // Planta pequeña decorativa
                dibujarCuboPorGeo(6.8f, 5.95f, 7.05f, 6.20f, Y, 0.30f, 0.48f, 0.30f, 0.18f, texturaDecoracion);
                dibujarCuboPorGeo(6.72f, 5.90f, 7.13f, 6.25f, Y + 0.30f, 0.42f, 0.12f, 0.45f, 0.16f, 0);

                // Repisa pequeña de decoración
                dibujarCuboPorGeo(6.85f, 4.15f, 7.65f, 4.22f, Y + 1.40f, 0.07f, 0.36f, 0.24f, 0.14f, texturaMueble);

                // Velas / frascos sobre repisa
                dibujarCuboPorGeo(6.95f, 4.12f, 7.08f, 4.25f, Y + 1.48f, 0.22f, 0.90f, 0.85f, 0.65f, texturaDecoracion);
                dibujarCuboPorGeo(7.18f, 4.12f, 7.31f, 4.25f, Y + 1.48f, 0.22f, 0.20f, 0.55f, 0.70f, texturaDecoracion);
                dibujarCuboPorGeo(7.42f, 4.12f, 7.55f, 4.25f, Y + 1.48f, 0.22f, 0.85f, 0.85f, 0.85f, texturaDecoracion);
        }
}