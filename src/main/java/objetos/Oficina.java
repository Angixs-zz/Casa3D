package objetos;

import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class Oficina {

    private static float convertirXGeoAOpenGL(float xGeo) {
        float x = xGeo - Constantes.CENTRO_GEOGEBRA_X;
        x = -x;
        return x * Constantes.ESCALA_CASA;
    }

    private static float convertirZGeoAOpenGL(float zGeo) {
        float z = zGeo - Constantes.CENTRO_GEOGEBRA_Z;
        return z * Constantes.ESCALA_CASA;
    }

    public static void dibujar(int texturaEscritorio, int texturaRepisa, int texturaLibro, int texturaSillon, int texturaSillonIndividual, int texturaSillaOficina, int texturaCuadro) {
        float y = Constantes.ALTURA_PISO_1;

        // =====================================================
        // ESCRITORIO EN "L" PRECIOSO Y EXACTO (SEGMENTOS Y PUNTOS DE GEOGEBRA)
        // =====================================================
        float pataR = 0.15f, pataG = 0.15f, pataB = 0.15f; // Metal oscuro

        // Cubiertas del escritorio
        // Sección 1: Superior horizontal (0.2 a 3.8 en X, 8.0 a 8.4 en Z)
        dibujarCuboPorGeoConTextura(0.2f, 8.0f, 3.8f, 8.4f, y + 0.7f, 0.08f, texturaEscritorio);
        // Sección 2: Derecha vertical (3.2 a 3.8 en X, 7.0 a 8.0 en Z)
        dibujarCuboPorGeoConTextura(3.2f, 7.0f, 3.8f, 8.0f, y + 0.7f, 0.08f, texturaEscritorio);
        // Sección 3: Inferior horizontal (1.9 a 3.8 en X, 6.5 a 7.0 en Z)
        dibujarCuboPorGeoConTextura(1.9f, 6.5f, 3.8f, 7.0f, y + 0.7f, 0.08f, texturaEscritorio);

        // Patas de soporte robustas y estilizadas
        // Pata 1: Esquina superior izquierda
        dibujarCuboPorGeo(0.35f, 8.15f, 0.45f, 8.25f, y, 0.7f, pataR, pataG, pataB);
        // Pata 2: Esquina inferior izquierda (brazo proyectado)
        dibujarCuboPorGeo(2.05f, 6.65f, 2.15f, 6.75f, y, 0.7f, pataR, pataG, pataB);
        // Pata 3: Esquina inferior derecha
        dibujarCuboPorGeo(3.55f, 6.65f, 3.65f, 6.75f, y, 0.7f, pataR, pataG, pataB);
        // Pata 4: Esquina superior derecha
        dibujarCuboPorGeo(3.55f, 8.15f, 3.65f, 8.25f, y, 0.7f, pataR, pataG, pataB);

        // =====================================================
        // SILLA Y COMPUTADORA (Distribución exacta)
        // =====================================================
        // Computadora sobre el escritorio (Z=7.5, X=3.55, viendo hacia el oeste/teclado
        // al oeste)
        dibujarComputadora(3.55f, y, 7.5f, 90.0f);

        // Silla de oficina (Z=7.5, X=2.5, dentro de la "L", viendo hacia el
        // este/computadora)
        dibujarSilla(2.5f, y, 7.5f, 90.0f, texturaSillaOficina);

        // =====================================================
        // MACETAS CON PLANTAS (En hilera sobre el escritorio contra la ventana
        // superior)
        // =====================================================
        dibujarMacetaConPlanta(0.5f, y + 0.78f, 8.2f);
        dibujarMacetaConPlanta(1.2f, y + 0.78f, 8.2f);
        dibujarMacetaConPlanta(1.9f, y + 0.78f, 8.2f);
        dibujarMacetaConPlanta(2.6f, y + 0.78f, 8.2f);
        dibujarMacetaConPlanta(3.3f, y + 0.78f, 8.2f);

        // =====================================================
        // ESTANTERÍA Y GABINETES
        // =====================================================
        // Estante flotante arriba del escritorio (Pared superior g1)
        dibujarEstanteAncho(2.0f, y, 8.35f, 180.0f, texturaRepisa, texturaLibro);

        // Estante flotante a la derecha arriba de la computadora (Pared derecha q)
        dibujarEstanteDerecha(3.70f, y, 7.5f, -87.0f, texturaRepisa, texturaLibro);

        // Gabinete lateral de madera al final del sofá (Esquina superior izquierda,
        // Z=8.2, X=0.5)
        // dibujarCabinetLateral(0.5f, y, 8.2f);

        dibujarSofaLargo(0.5f, y, 6.9f, -90.0f, texturaSillon);

        // =====================================================
        // CUADRO DECORATIVO (Sobre el sillón largo, pegado a la pared izquierda)
        // =====================================================
        // 1. Marco exterior de madera (texturaRepisa)
        dibujarCuboPorGeoConTextura(0.18f, 5.95f, 0.20f, 7.85f, y + 1.25f, 0.9f, texturaRepisa);
        // 2. Lienzo interior (texturaCuadro)
        dibujarCuboPorGeoConTextura(0.201f, 6.0f, 0.205f, 7.8f, y + 1.275f, 0.85f, texturaCuadro);

        // Planta decorativa en esquina al inicio del sofá (Esquina inferior izquierda,
        // Z=5.73, X=0.43)
        dibujarPlantaEsquina(0.43f, y, 5.73f);

        // =====================================================
        // SILLONES PARA VISITAS (Abajo, orientados al norte)
        // =====================================================
        dibujarSillonIndividual(1.7f, y, 5.85f, 0.0f, texturaSillonIndividual);
        dibujarSillonIndividual(2.5f, y, 5.85f, 0.0f, texturaSillonIndividual);
    }

    private static void dibujarCuboPorGeo(float xMin, float zMin, float xMax, float zMax, float yBase, float altura,
            float r, float g, float b) {
        float centroXGeo = (xMin + xMax) / 2.0f;
        float centroZGeo = (zMin + zMax) / 2.0f;

        float anchoGeo = xMax - xMin;
        float largoGeo = zMax - zMin;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float anchoOpenGL = anchoGeo * Constantes.ESCALA_CASA;
        float largoOpenGL = largoGeo * Constantes.ESCALA_CASA;

        Cubo.dibujar(x, yBase + altura / 2.0f, z, anchoOpenGL, altura, largoOpenGL, r, g, b);
    }

    private static void dibujarCuboPorGeoConTextura(float xMin, float zMin, float xMax, float zMax, float yBase, float altura,
            int texturaID) {
        float centroXGeo = (xMin + xMax) / 2.0f;
        float centroZGeo = (zMin + zMax) / 2.0f;

        float anchoGeo = xMax - xMin;
        float largoGeo = zMax - zMin;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float anchoOpenGL = anchoGeo * Constantes.ESCALA_CASA;
        float largoOpenGL = largoGeo * Constantes.ESCALA_CASA;

        if (texturaID != 0) {
            Cubo.dibujarConTextura(x, yBase + altura / 2.0f, z, anchoOpenGL, altura, largoOpenGL, texturaID);
        } else {
            // Caída de seguridad por si no carga la textura (madera elegante por defecto)
            Cubo.dibujar(x, yBase + altura / 2.0f, z, anchoOpenGL, altura, largoOpenGL, 0.45f, 0.30f, 0.15f);
        }
    }

    private static void dibujarMacetaConPlanta(float xGeo, float yBase, float zGeo) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, yBase, z);

        // Maceta (color negro elegante / gris carbón)
        Cubo.dibujar(0f, 0.04f, 0f, 0.2f, 0.08f, 0.2f, 0.15f, 0.15f, 0.15f);

        // Tierra (color marrón muy oscuro/negro)
        Cubo.dibujar(0f, 0.07f, 0f, 0.18f, 0.02f, 0.18f, 0.15f, 0.1f, 0.05f);

        // Hojas de la planta en tonos verdes hermosos (reducidas de tamaño)
        Cubo.dibujar(-0.04f, 0.13f, 0.01f, 0.1f, 0.1f, 0.1f, 0.1f, 0.6f, 0.2f);
        Cubo.dibujar(0.04f, 0.15f, -0.01f, 0.09f, 0.12f, 0.1f, 0.15f, 0.7f, 0.25f);
        Cubo.dibujar(0.01f, 0.12f, 0.03f, 0.11f, 0.08f, 0.1f, 0.08f, 0.55f, 0.18f);

        glPopMatrix();
    }

    private static void dibujarCabinetLateral(float xGeo, float yBase, float zGeo) {
        float r = 0.35f, g = 0.18f, b = 0.08f; // Madera oscura
        // Estructura principal
        dibujarCuboPorGeo(xGeo - 0.4f, zGeo - 0.2f, xGeo + 0.4f, zGeo + 0.2f, yBase, 0.78f, r, g, b);
        // Detalles/manijas plateadas o doradas en el frente (el frente mira al
        // este/derecha)
        float xManija = convertirXGeoAOpenGL(xGeo + 0.21f);
        float zManija1 = convertirZGeoAOpenGL(zGeo - 0.1f);
        float zManija2 = convertirZGeoAOpenGL(zGeo + 0.1f);
        Cubo.dibujar(xManija, yBase + 0.5f, zManija1, 0.04f, 0.04f, 0.08f, 0.8f, 0.6f, 0.2f);
        Cubo.dibujar(xManija, yBase + 0.5f, zManija2, 0.04f, 0.04f, 0.08f, 0.8f, 0.6f, 0.2f);
    }

    private static void dibujarPlantaEsquina(float xGeo, float yBase, float zGeo) {
        // Maceta negra grande y elegante
        dibujarCuboPorGeo(xGeo - 0.25f, zGeo - 0.25f, xGeo + 0.25f, zGeo + 0.25f, yBase, 0.5f, 0.15f, 0.15f, 0.15f);
        // Hojas verdes de planta exótica (más pequeñas, naciendo desde la parte superior de la maceta en yBase + 0.5f)
        dibujarCuboPorGeo(xGeo - 0.13f, zGeo - 0.13f, xGeo + 0.13f, zGeo + 0.13f, yBase + 0.5f, 0.15f, 0.1f, 0.5f,
                0.15f);
        dibujarCuboPorGeo(xGeo - 0.07f, zGeo - 0.07f, xGeo + 0.07f, zGeo + 0.07f, yBase + 0.65f, 0.12f, 0.15f, 0.6f,
                0.2f);
        dibujarCuboPorGeo(xGeo - 0.14f, zGeo - 0.03f, xGeo + 0.14f, zGeo + 0.03f, yBase + 0.53f, 0.06f, 0.08f, 0.45f, 0.1f);
        dibujarCuboPorGeo(xGeo - 0.03f, zGeo - 0.14f, xGeo + 0.03f, zGeo + 0.14f, yBase + 0.53f, 0.06f, 0.08f, 0.45f, 0.1f);
    }

    private static void dibujarEstanteAncho(float xGeo, float y, float zGeo, float rotacionY, int texturaRepisa, int texturaLibro) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // 1. MANTENEMOS TU LARGO NUEVO
        float largoEstante = 5.6f;

        // 2 repisas flotantes pegadas a g1
        if (texturaRepisa != 0) {
            Cubo.dibujarConTextura(0f, 1.6f, 0f, largoEstante, 0.1f, 0.3f, texturaRepisa);
            Cubo.dibujarConTextura(0f, 2.1f, 0f, largoEstante, 0.1f, 0.3f, texturaRepisa);
        } else {
            float r = 0.35f, g = 0.18f, b = 0.08f;
            Cubo.dibujar(0f, 1.6f, 0f, largoEstante, 0.1f, 0.3f, r, g, b);
            Cubo.dibujar(0f, 2.1f, 0f, largoEstante, 0.1f, 0.3f, r, g, b);
        }

        // Generar libros
        float[][] colores = {
                { 0.8f, 0.1f, 0.1f }, { 0.1f, 0.2f, 0.8f }, { 0.1f, 0.6f, 0.2f },
                { 0.9f, 0.9f, 0.2f }, { 0.7f, 0.2f, 0.7f }, { 0.2f, 0.8f, 0.8f }
        };

        // 2. AMPLIAMOS LOS LÍMITES DEL BUCLE PARA LLENAR EL NUEVO ESPACIO
        // Fuimos de -12 a 12, ahora vamos de -19 a 19 para abarcar todo el 5.6f
        for (int i = -19; i <= 19; i++) {
            float posX = i * 0.14f;

            float[] c1 = colores[Math.abs(i + 15) % colores.length];
            float[] c2 = colores[Math.abs(i + 18) % colores.length];

            float altoLibro = 0.3f + (Math.abs(i % 3) * 0.05f);
            float centroY = (altoLibro / 2f) + 0.05f;

            // Libros en repisa 1
            if (texturaLibro != 0) {
                Cubo.dibujarConTexturaYColor(posX, 1.6f + centroY, 0.02f, 0.1f, altoLibro, 0.22f, texturaLibro, c1[0], c1[1], c1[2]);
            } else {
                Cubo.dibujar(posX, 1.6f + centroY, 0.02f, 0.1f, altoLibro, 0.22f, c1[0], c1[1], c1[2]);
            }

            // Libros en repisa 2
            if (texturaLibro != 0) {
                Cubo.dibujarConTexturaYColor(posX, 2.1f + centroY, 0.02f, 0.1f, altoLibro, 0.22f, texturaLibro, c2[0], c2[1], c2[2]);
            } else {
                Cubo.dibujar(posX, 2.1f + centroY, 0.02f, 0.1f, altoLibro, 0.22f, c2[0], c2[1], c2[2]);
            }
        }

        glPopMatrix();
    }

    private static void dibujarEstanteDerecha(float xGeo, float y, float zGeo, float rotacionY, int texturaRepisa, int texturaLibro) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        float largoEstante = 3.0f;

        // 2 repisas flotantes
        if (texturaRepisa != 0) {
            Cubo.dibujarConTextura(0f, 1.6f, 0f, largoEstante, 0.1f, 0.3f, texturaRepisa);
            Cubo.dibujarConTextura(0f, 2.1f, 0f, largoEstante, 0.1f, 0.3f, texturaRepisa);
        } else {
            float r = 0.35f, g = 0.18f, b = 0.08f;
            Cubo.dibujar(0f, 1.6f, 0f, largoEstante, 0.1f, 0.3f, r, g, b);
            Cubo.dibujar(0f, 2.1f, 0f, largoEstante, 0.1f, 0.3f, r, g, b);
        }

        // Generar libros
        float[][] colores = {
                { 0.8f, 0.1f, 0.1f }, { 0.1f, 0.2f, 0.8f }, { 0.1f, 0.6f, 0.2f },
                { 0.9f, 0.9f, 0.2f }, { 0.7f, 0.2f, 0.7f }, { 0.2f, 0.8f, 0.8f }
        };

        for (int i = -10; i <= 10; i++) {
            float posX = i * 0.14f;

            float[] c1 = colores[Math.abs(i + 15) % colores.length];
            float[] c2 = colores[Math.abs(i + 18) % colores.length];

            float altoLibro = 0.3f + (Math.abs(i % 3) * 0.05f);
            float centroY = (altoLibro / 2f) + 0.05f;

            // Libros en repisa 1
            if (texturaLibro != 0) {
                Cubo.dibujarConTexturaYColor(posX, 1.6f + centroY, 0.02f, 0.1f, altoLibro, 0.22f, texturaLibro, c1[0], c1[1], c1[2]);
            } else {
                Cubo.dibujar(posX, 1.6f + centroY, 0.02f, 0.1f, altoLibro, 0.22f, c1[0], c1[1], c1[2]);
            }

            // Libros en repisa 2
            if (texturaLibro != 0) {
                Cubo.dibujarConTexturaYColor(posX, 2.1f + centroY, 0.02f, 0.1f, altoLibro, 0.22f, texturaLibro, c2[0], c2[1], c2[2]);
            } else {
                Cubo.dibujar(posX, 2.1f + centroY, 0.02f, 0.1f, altoLibro, 0.22f, c2[0], c2[1], c2[2]);
            }
        }

        glPopMatrix();
    }

    private static void dibujarSofaLargo(float xGeo, float y, float zGeo, float rotacionY, int texturaSillon) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        if (texturaSillon != 0) {
            // Asiento largo
            Cubo.dibujarConTextura(0f, 0.35f, 0f, 2.4f, 0.35f, 0.8f, texturaSillon);
            // Respaldo largo
            Cubo.dibujarConTextura(0f, 0.8f, -0.32f, 2.4f, 0.8f, 0.18f, texturaSillon);
            // Brazos
            Cubo.dibujarConTextura(-1.28f, 0.55f, 0f, 0.16f, 0.55f, 0.8f, texturaSillon);
            Cubo.dibujarConTextura(1.28f, 0.55f, 0f, 0.16f, 0.55f, 0.8f, texturaSillon);
        } else {
            // Asiento largo (beige/gris como en la imagen)
            Cubo.dibujar(0f, 0.35f, 0f, 2.4f, 0.35f, 0.8f, 0.7f, 0.68f, 0.63f);
            // Respaldo largo
            Cubo.dibujar(0f, 0.8f, -0.32f, 2.4f, 0.8f, 0.18f, 0.65f, 0.63f, 0.58f);
            // Brazos
            Cubo.dibujar(-1.28f, 0.55f, 0f, 0.16f, 0.55f, 0.8f, 0.65f, 0.63f, 0.58f);
            Cubo.dibujar(1.28f, 0.55f, 0f, 0.16f, 0.55f, 0.8f, 0.65f, 0.63f, 0.58f);
        }

        glPopMatrix();
    }

    private static void dibujarSilla(float xGeo, float y, float zGeo, float rotacionY, int texturaSilla) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        if (texturaSilla != 0) {
            Cubo.dibujarConTextura(0f, 0.45f, 0f, 0.7f, 0.18f, 0.7f, texturaSilla); // Asiento
            Cubo.dibujarConTextura(0f, 0.9f, 0.32f, 0.7f, 0.8f, 0.12f, texturaSilla); // Respaldo
        } else {
            Cubo.dibujar(0f, 0.45f, 0f, 0.7f, 0.18f, 0.7f, 0.15f, 0.15f, 0.15f); // Asiento
            Cubo.dibujar(0f, 0.9f, 0.32f, 0.7f, 0.8f, 0.12f, 0.12f, 0.12f, 0.12f); // Respaldo
        }
        Cubo.dibujar(0f, 0.2f, 0f, 0.15f, 0.4f, 0.15f, 0.05f, 0.05f, 0.05f); // Base

        glPopMatrix();
    }

    private static void dibujarComputadora(float xGeo, float y, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        Cubo.dibujar(0f, 1.15f, 0f, 0.75f, 0.45f, 0.08f, 0.02f, 0.02f, 0.02f); // Monitor
        Cubo.dibujar(0f, 0.9f, 0.05f, 0.18f, 0.25f, 0.08f, 0.08f, 0.08f, 0.08f); // Base
        Cubo.dibujar(0f, 0.86f, 0.32f, 0.65f, 0.05f, 0.18f, 0.03f, 0.03f, 0.03f); // Teclado

        glPopMatrix();
    }

    private static void dibujarSillonIndividual(float xGeo, float y, float zGeo, float rotacionY, int texturaSillon) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        if (texturaSillon != 0) {
            Cubo.dibujarConTextura(0f, 0.35f, 0f, 0.8f, 0.35f, 0.8f, texturaSillon);
            Cubo.dibujarConTextura(0f, 0.8f, -0.32f, 0.8f, 0.8f, 0.18f, texturaSillon);
            Cubo.dibujarConTextura(-0.48f, 0.55f, 0f, 0.16f, 0.55f, 0.8f, texturaSillon);
            Cubo.dibujarConTextura(0.48f, 0.55f, 0f, 0.16f, 0.55f, 0.8f, texturaSillon);
        } else {
            // Mismo tono beige que el sofá largo
            Cubo.dibujar(0f, 0.35f, 0f, 0.8f, 0.35f, 0.8f, 0.7f, 0.68f, 0.63f);
            Cubo.dibujar(0f, 0.8f, -0.32f, 0.8f, 0.8f, 0.18f, 0.65f, 0.63f, 0.58f);
            Cubo.dibujar(-0.48f, 0.55f, 0f, 0.16f, 0.55f, 0.8f, 0.65f, 0.63f, 0.58f);
            Cubo.dibujar(0.48f, 0.55f, 0f, 0.16f, 0.55f, 0.8f, 0.65f, 0.63f, 0.58f);
        }

        glPopMatrix();
    }

    private static void dibujarMesaCentro(float xGeo, float y, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        Cubo.dibujar(0f, 0.35f, 0f, 0.9f, 0.12f, 0.55f, 0.35f, 0.18f, 0.08f); // Tabla
        Cubo.dibujar(-0.35f, 0.18f, -0.2f, 0.08f, 0.35f, 0.08f, 0.1f, 0.1f, 0.1f); // Patas
        Cubo.dibujar(0.35f, 0.18f, -0.2f, 0.08f, 0.35f, 0.08f, 0.1f, 0.1f, 0.1f);
        Cubo.dibujar(-0.35f, 0.18f, 0.2f, 0.08f, 0.35f, 0.08f, 0.1f, 0.1f, 0.1f);
        Cubo.dibujar(0.35f, 0.18f, 0.2f, 0.08f, 0.35f, 0.08f, 0.1f, 0.1f, 0.1f);

        glPopMatrix();
    }
}