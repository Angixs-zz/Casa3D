package objetos;

import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class Fuente {

    private static final float Y = Constantes.ALTURA_PISO_1;
    public static int texturaCascada = 0;

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
         * FUENTE EN CASCADA - PRIMER PISO
         * =====================================================
         *
         * Base de la fuente:
         * L8 = (7.3, 4.7), M8 = (7.3, 1.8), N8 = (7.8, 4.7), O8 = (7.8, 1.8)
         * Centro Base: x = 7.55, z = 3.25
         * 
         * Estructura de la fuente:
         * P8 = (7.5, 4.3), Q8 = (7.8, 4.3), R8 = (7.8, 2.7), S8 = (7.5, 2.7)
         * Centro Estructura: x = 7.65, z = 3.50
         */

        float xGeoBase = 7.55f;
        float zGeoBase = 3.25f;

        float x = convertirXGeoAOpenGL(xGeoBase);
        float z = convertirZGeoAOpenGL(zGeoBase);

        glPushMatrix();
        glTranslatef(x, Y, z);

        dibujarBasePileta();
        dibujarEstructuraCascada();
        dibujarAguaCascada();
        dibujarPlantasLaterales();

        glPopMatrix();
    }

    public static void dibujarFuenteDoble() {
        /*
         * =====================================================
         * FUENTE EN CASCADA DOBLE - ALTURA DOS PISOS
         * =====================================================
         *
         * Base Exterior: G7(7.3, 13.4), H7(7.8, 13.4), J7(7.3, 12.5), M7(7.8, 12.5)
         * Centro Base: x = 7.55, z = 12.95
         */

        float xGeoBase = 7.55f;
        float zGeoBase = 12.95f;

        float x = convertirXGeoAOpenGL(xGeoBase);
        float z = convertirZGeoAOpenGL(zGeoBase);

        glPushMatrix();
        glTranslatef(x, Y, z);

        dibujarBasePiletaDoble();
        dibujarEstructuraCascadaDoble();
        dibujarAguaCascadaDoble();

        glPopMatrix();
    }

    private static void dibujarBasePileta() {
        // La pileta donde cae el agua (Base L8, M8, N8, O8)
        Cubo.dibujar(
                0f,
                0.15f, // Altura Y (0 a 0.3)
                0f,
                escalar(0.5f), // Ancho X = 0.5 GeoGebra
                0.3f, // Alto Y
                escalar(2.9f), // Largo Z = 2.9 GeoGebra
                0.6f, 0.6f, 0.6f // Gris concreto
        );

        // El agua de la pileta (azul semi transparente)
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDepthMask(false);
        Cubo.dibujar(
                0f,
                0.28f, // Nivel del agua
                0f,
                escalar(0.45f),
                0.02f,
                escalar(2.8f),
                0.2f, 0.5f, 0.8f, 0.8f // Alpha
        );
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

    private static void dibujarEstructuraCascada() {
        // Centro de la estructura relativo a la base
        float cx = escalar(-0.10f); // Pegado a la pared del fondo
        float cz = 0f; // Centrado en la base a lo largo del eje Z

        float widthX = escalar(0.3f); // Ancho P-Q = 0.3
        float lengthZ = escalar(1.6f); // Largo S-R = 1.6
        float heightY = 3.2f; // Altura total de la pared

        float colorMarcoR = 0.25f, colorMarcoG = 0.23f, colorMarcoB = 0.21f;
        float pilarThickness = escalar(0.2f); // Grosor de los marcos laterales

        // Muro verde de fondo (detrás de la estructura)
        Cubo.dibujar(
                cx - widthX / 2f - escalar(0.05f),
                heightY / 2f,
                cz,
                escalar(0.1f),
                heightY,
                lengthZ * 1.2f,
                0.15f, 0.35f, 0.15f);

        // Pilar izquierdo (Z negativo local)
        Cubo.dibujar(
                cx,
                heightY / 2f,
                cz - lengthZ / 2f + pilarThickness / 2f,
                widthX, heightY, pilarThickness,
                colorMarcoR, colorMarcoG, colorMarcoB);

        // Pilar derecho (Z positivo local)
        Cubo.dibujar(
                cx,
                heightY / 2f,
                cz + lengthZ / 2f - pilarThickness / 2f,
                widthX, heightY, pilarThickness,
                colorMarcoR, colorMarcoG, colorMarcoB);

        // Viga superior
        float vigaThickness = 0.4f; // 40 cm
        Cubo.dibujar(
                cx,
                heightY - vigaThickness / 2f,
                cz,
                widthX, vigaThickness, lengthZ,
                colorMarcoR, colorMarcoG, colorMarcoB);

        // Muro de piedra por donde cae el agua
        Cubo.dibujar(
                cx - escalar(0.05f),
                heightY / 2f,
                cz,
                widthX * 0.5f, heightY, lengthZ - pilarThickness * 2f,
                0.3f, 0.3f, 0.3f);
    }

    private static void dibujarAguaCascada() {
        float cx = escalar(-0.10f);
        float cz = 0f;
        float lengthZ = escalar(1.6f);
        float pilarThickness = escalar(0.2f);
        float heightY = 3.2f;

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDepthMask(false);

        if (texturaCascada != 0) {
            Cubo.dibujarConTextura(
                    cx + escalar(0.05f),
                    heightY / 2f,
                    cz,
                    escalar(0.02f),
                    heightY,
                    lengthZ - pilarThickness * 2f,
                    texturaCascada);
        } else {
            Cubo.dibujar(
                    cx + escalar(0.05f), // Por delante de la piedra
                    heightY / 2f,
                    cz,
                    escalar(0.02f),
                    heightY,
                    lengthZ - pilarThickness * 2f,
                    0.8f, 0.9f, 1.0f, 0.6f);
        }

        glDepthMask(true);
        glDisable(GL_BLEND);
    }

    private static void dibujarPlantasLaterales() {
        // Macetas con plantas a un lado de la fuente
        // Se colocarán cerca del extremo derecho (Z positivo) y un poco hacia adelante
        // (X positivo)

        // Maceta 1 (Baja)
        float xMaceta = escalar(0.3f);
        float zMaceta = escalar(1.3f);

        // Base maceta (Blanco)
        Cubo.dibujar(xMaceta, escalar(0.2f), zMaceta, escalar(0.15f), escalar(0.2f), escalar(0.15f), 0.9f, 0.9f, 0.9f);
        // Planta (Verde amarillento)
        Cubo.dibujar(xMaceta, escalar(0.45f), zMaceta, escalar(0.2f), escalar(0.3f), escalar(0.2f), 0.5f, 0.7f, 0.2f);

        // Maceta 2 (Media)
        float zMaceta2 = escalar(1.0f);
        // Base maceta (Blanco)
        Cubo.dibujar(xMaceta, escalar(0.4f), zMaceta2, escalar(0.15f), escalar(0.2f), escalar(0.15f), 0.9f, 0.9f, 0.9f);
        // Soporte (Negro)
        Cubo.dibujar(xMaceta, escalar(0.15f), zMaceta2, escalar(0.05f), escalar(0.3f), escalar(0.05f), 0.1f, 0.1f,
                0.1f);
        // Planta
        Cubo.dibujar(xMaceta, escalar(0.65f), zMaceta2, escalar(0.25f), escalar(0.35f), escalar(0.25f), 0.5f, 0.7f,
                0.2f);
    }

    private static void dibujarBasePiletaDoble() {
        // Base exterior de la fuente doble (0.5 x 0.9)
        Cubo.dibujar(
                0f,
                0.15f, // Altura Y
                0f,
                escalar(0.5f), // Ancho X = 0.5 GeoGebra
                0.3f, // Alto Y
                escalar(0.9f), // Largo Z = 0.9 GeoGebra
                0.6f, 0.6f, 0.6f // Gris concreto
        );

        // Agua de la pileta doble
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDepthMask(false);
        Cubo.dibujar(
                0f,
                0.28f, // Nivel del agua
                0f,
                escalar(0.45f),
                0.02f,
                escalar(0.8f),
                0.2f, 0.5f, 0.8f, 0.8f // Alpha
        );
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

    private static void dibujarEstructuraCascadaDoble() {
        // Centro Estructura: x = 7.60, z = 12.95
        float cx = escalar(-0.05f);
        float cz = 0f;

        float widthX = escalar(0.4f); // Ancho T-W = 0.4
        float lengthZ = escalar(0.85f); // Más ancha a lo largo de la pared (casi cubre los 0.9 de la base)
        float heightY = 6.4f; // Dos pisos de altura

        float colorMarcoR = 0.25f, colorMarcoG = 0.23f, colorMarcoB = 0.21f;
        float pilarThickness = escalar(0.1f);

        // Pilar izquierdo (Z negativo)
        Cubo.dibujar(
                cx,
                heightY / 2f,
                cz - lengthZ / 2f + pilarThickness / 2f,
                widthX, heightY, pilarThickness,
                colorMarcoR, colorMarcoG, colorMarcoB);

        // Pilar derecho (Z positivo)
        Cubo.dibujar(
                cx,
                heightY / 2f,
                cz + lengthZ / 2f - pilarThickness / 2f,
                widthX, heightY, pilarThickness,
                colorMarcoR, colorMarcoG, colorMarcoB);

        // Viga superior
        float vigaThickness = 0.4f;
        Cubo.dibujar(
                cx,
                heightY - vigaThickness / 2f,
                cz,
                widthX, vigaThickness, lengthZ,
                colorMarcoR, colorMarcoG, colorMarcoB);

        // Muro de piedra por donde cae el agua
        Cubo.dibujar(
                cx - escalar(0.05f),
                heightY / 2f,
                cz,
                widthX * 0.5f, heightY, lengthZ - pilarThickness * 2f,
                0.3f, 0.3f, 0.3f);
    }

    private static void dibujarAguaCascadaDoble() {
        float cx = escalar(-0.05f);
        float cz = 0f;
        float lengthZ = escalar(0.85f);
        float pilarThickness = escalar(0.1f);
        float heightY = 6.4f;

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDepthMask(false);

        if (texturaCascada != 0) {
            Cubo.dibujarConTextura(
                    cx + escalar(0.05f),
                    heightY / 2f,
                    cz,
                    escalar(0.02f),
                    heightY,
                    lengthZ - pilarThickness * 2f,
                    texturaCascada);
        } else {
            Cubo.dibujar(
                    cx + escalar(0.05f), // Por delante de la piedra
                    heightY / 2f,
                    cz,
                    escalar(0.02f),
                    heightY,
                    lengthZ - pilarThickness * 2f,
                    0.8f, 0.9f, 1.0f, 0.6f);
        }

        glDepthMask(true);
        glDisable(GL_BLEND);
    }
}
