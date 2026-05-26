package piso2;

import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;
import objetos.Cubo;

public class BanoPrincipalp2 {
    public static int texturaMuebleBano = 0;
    public static int texturaLavaboBano = 0;
    public static int texturaEspejo = 0;

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
         * Baño Principal del primer piso.
         * Dividido en dos áreas:
         * 1. Área de Tina y Retrete: Rectángulo D1(0.1, 14.0) a T1(0.1, 15.4) y F1(2.5, 14.0)
         * 2. Área de Lavabo: Rectángulo F1(2.5, 14.0) a H1(3.9, 14.5)
         */

        // Regadera pegada a la pared izquierda (X = 0.1 a 1.1)
        // Centro en X = 0.6, Z = 14.7
        dibujarRegadera(0.6f, 14.7f, 0f);

        // Retrete al lado de la regadera, recargado en la pared inferior (Z = 14.0)
        // Centro en X = 1.7, Z = 14.3
        dibujarRetrete(1.7f, 14.3f, 0f);

        // Lavabo en su cuarto correspondiente, pegado a la pared inferior (Z = 14.0)
        // Centro en X = 3.2, Z = 14.25
        dibujarLavabo(3.2f, 14.25f, 0f);
    }

    private static void dibujarRegadera(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Piso de la regadera (Plato de ducha) - Le aplicamos textura si existe
        if (texturaLavaboBano != 0) {
                Cubo.dibujarConTextura(
                        0f, 0.04f, 0f, 
                        escalar(1.0f), 0.08f, escalar(1.4f), 
                        texturaLavaboBano);
        } else {
                Cubo.dibujar(
                        0f, 0.04f, 0f, 
                        escalar(1.0f), 0.08f, escalar(1.4f), 
                        0.9f, 0.9f, 0.9f);
        }

        // Charola ligeramente hundida
        Cubo.dibujar(
                0f, 0.10f, 0f, 
                escalar(0.85f), 0.04f, escalar(1.25f), 
                0.85f, 0.85f, 0.85f);

        // Rejilla de desagüe metálica (Centro del piso)
        Cubo.dibujar(
                0f, 0.125f, 0f, 
                0.15f, 0.01f, 0.15f, 
                0.3f, 0.3f, 0.3f);
        Cubo.dibujar(
                0f, 0.13f, 0f, 
                0.12f, 0.01f, 0.12f, 
                0.1f, 0.1f, 0.1f); // Hueco de la rejilla

        // ==========================================
        // CANCEL DE CRISTAL (Lado Derecho)
        // ==========================================
        // Cristal lateral derecho fijo (Mitad trasera)
        Cubo.dibujarTransparente(
                escalar(0.5f), 1.20f, -escalar(0.35f), 
                0.02f, 2.30f, escalar(0.7f), 
                0.55f, 0.82f, 0.88f, 0.35f); // 0.35 de opacidad

        // Puerta de Cristal (Mitad delantera) - Ligeramente salida para dar efecto de puerta
        Cubo.dibujarTransparente(
                escalar(0.5f) + 0.02f, 1.20f, escalar(0.35f), 
                0.02f, 2.30f, escalar(0.7f), 
                0.55f, 0.82f, 0.88f, 0.35f); // 0.35 de opacidad

        // Marco inferior del cancel (Aluminio negro)
        Cubo.dibujar(
                escalar(0.5f), 0.10f, 0f, 
                0.06f, 0.06f, escalar(1.4f), 
                0.1f, 0.1f, 0.1f);

        // Marco superior del cancel (Aluminio negro)
        Cubo.dibujar(
                escalar(0.5f), 2.35f, 0f, 
                0.06f, 0.06f, escalar(1.4f), 
                0.1f, 0.1f, 0.1f);

        // Jaladera/Manija de la puerta de cristal (Cromada)
        Cubo.dibujar(
                escalar(0.5f) + 0.04f, 1.20f, escalar(0.6f), 
                0.02f, 0.40f, 0.03f, 
                0.8f, 0.8f, 0.8f);

        // ==========================================
        // SISTEMA DE DUCHA MODERNO (Pegado a la pared izquierda)
        // ==========================================
        // Tubo vertical principal en la pared izquierda
        Cubo.dibujar(
                -escalar(0.48f), 1.20f, 0f, 
                0.04f, 1.80f, 0.04f, 
                0.15f, 0.15f, 0.15f);

        // Brazo horizontal (Mucho más corto, pegado a la pared)
        Cubo.dibujar(
                -escalar(0.45f), 2.10f, 0f, 
                escalar(0.10f), 0.04f, 0.04f, 
                0.15f, 0.15f, 0.15f);

        // Cabezal de ducha tipo "Lluvia" (Pegado a la pared izquierda)
        Cubo.dibujar(
                -escalar(0.40f), 2.05f, 0f, 
                0.35f, 0.05f, 0.35f, 
                0.1f, 0.1f, 0.1f);

        // Llave mezcladora cuadrada (En la misma pared)
        Cubo.dibujar(
                -escalar(0.49f), 1.0f, 0f, 
                0.04f, 0.25f, 0.25f, 
                0.1f, 0.1f, 0.1f); // Placa base
        Cubo.dibujar(
                -escalar(0.45f), 1.0f, 0f, 
                0.08f, 0.04f, 0.15f, 
                0.8f, 0.8f, 0.8f); // Palanca cromada

        // Múltiples chorros de agua (Desplazados hacia la pared izquierda)
        for (float zGota = -0.1f; zGota <= 0.1f; zGota += 0.1f) {
                for (float xGota = -escalar(0.45f); xGota <= -escalar(0.35f); xGota += escalar(0.05f)) {
                        Cubo.dibujar(
                                xGota, 1.50f, zGota, 
                                0.03f, 1.0f, 0.03f, 
                                0.4f, 0.75f, 0.95f);
                }
        }

        // ==========================================
        // REPISA Y ACCESORIOS (Shampoos, jabón)
        // ==========================================
        // Repisa empotrada en la pared del fondo (Z = 14.0, es decir -Z local)
        Cubo.dibujar(
                -escalar(0.1f), 1.10f, -escalar(0.68f), 
                escalar(0.4f), 0.04f, 0.15f, 
                0.2f, 0.2f, 0.2f); // Repisa oscura

        // Bote de Shampoo 1 (Naranja)
        Cubo.dibujar(
                -escalar(0.2f), 1.25f, -escalar(0.68f), 
                0.08f, 0.26f, 0.08f, 
                0.85f, 0.4f, 0.1f);
        // Tapa Shampoo 1
        Cubo.dibujar(
                -escalar(0.2f), 1.39f, -escalar(0.68f), 
                0.05f, 0.03f, 0.05f, 
                0.1f, 0.1f, 0.1f);

        // Bote de Shampoo 2 (Azul)
        Cubo.dibujar(
                0f, 1.20f, -escalar(0.68f), 
                0.06f, 0.18f, 0.06f, 
                0.1f, 0.4f, 0.85f);
        // Tapa Shampoo 2
        Cubo.dibujar(
                0f, 1.30f, -escalar(0.68f), 
                0.04f, 0.03f, 0.04f, 
                0.9f, 0.9f, 0.9f);

        // Jabón en barra (Blanco amarillento)
        Cubo.dibujar(
                escalar(0.15f), 1.14f, -escalar(0.68f), 
                0.10f, 0.03f, 0.08f, 
                0.95f, 0.92f, 0.8f);

        glPopMatrix();
    }

    private static void dibujarRetrete(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Base inferior
        Cubo.dibujar(
                0f, 0.18f, 0f,
                escalar(0.35f), 0.35f, escalar(0.45f),
                0.90f, 0.90f, 0.86f);

        // Taza
        Cubo.dibujar(
                0f, 0.48f, 0.05f,
                escalar(0.45f), 0.30f, escalar(0.50f),
                0.96f, 0.96f, 0.92f);

        // Hueco visual de la taza
        Cubo.dibujar(
                0f, 0.66f, 0.05f,
                escalar(0.25f), 0.04f, escalar(0.28f),
                0.10f, 0.10f, 0.10f);

        // Tanque trasero (pegado a Z = -0.33)
        Cubo.dibujar(
                0f, 0.80f, -0.33f,
                escalar(0.50f), 0.45f, escalar(0.18f),
                0.92f, 0.92f, 0.88f);

        // Tapa superior del tanque
        Cubo.dibujar(
                0f, 1.05f, -0.33f,
                escalar(0.55f), 0.06f, escalar(0.22f),
                0.82f, 0.82f, 0.78f);

        glPopMatrix();
    }

    private static void dibujarLavabo(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Mueble/base del lavabo (ocupa casi todo el espacio del cuartito)
        if (texturaMuebleBano != 0) {
                Cubo.dibujarConTextura(
                        0f, 0.35f, 0f,
                        escalar(1.30f), 0.70f, escalar(0.45f),
                        texturaMuebleBano);
        } else {
                Cubo.dibujar(
                        0f, 0.35f, 0f,
                        escalar(1.30f), 0.70f, escalar(0.45f),
                        0.35f, 0.20f, 0.10f); // Madera oscura
        }

        // Encimera del lavabo
        if (texturaLavaboBano != 0) {
            Cubo.dibujarConTextura(
                    0f, 0.72f, 0f,
                    escalar(1.35f), 0.06f, escalar(0.50f),
                    texturaLavaboBano);
        } else {
            Cubo.dibujar(
                    0f, 0.72f, 0f,
                    escalar(1.35f), 0.06f, escalar(0.50f),
                    0.95f, 0.95f, 0.95f);
        }

        // Lavabo blanco incrustado
        if (texturaLavaboBano != 0) {
            Cubo.dibujarConTextura(
                    0f, 0.76f, 0f,
                    escalar(0.60f), 0.10f, escalar(0.35f),
                    texturaLavaboBano);
        } else {
            Cubo.dibujar(
                    0f, 0.76f, 0f,
                    escalar(0.60f), 0.10f, escalar(0.35f),
                    0.90f, 0.90f, 0.90f);
        }

        // Hueco del lavabo
        Cubo.dibujar(
                0f, 0.82f, 0.02f,
                escalar(0.50f), 0.04f, escalar(0.25f),
                0.15f, 0.20f, 0.25f);

        // Llave del lavabo
        Cubo.dibujar(0f, 0.90f, -0.15f, escalar(0.08f), 0.15f, escalar(0.08f), 0.65f, 0.65f, 0.65f);
        Cubo.dibujar(0f, 0.98f, -0.05f, escalar(0.08f), 0.06f, escalar(0.20f), 0.65f, 0.65f, 0.65f);

        // Espejo en la pared (Ajustado a -0.30f para que no quede enterrado dentro del grosor de la pared)
        float espejoZ = -0.30f;
        float espejoAncho = escalar(0.85f); // Hecho un poco más pequeño (antes era 1.1)
        float espejoAlto = 0.70f; // Hecho un poco más pequeño (antes era 0.9)

        // Marco del espejo (Madera oscura para combinar con el lavabo)
        Cubo.dibujar(
                0f, 1.55f, espejoZ,
                espejoAncho, espejoAlto, 0.04f,
                0.35f, 0.20f, 0.10f); // Mismo color del mueble de madera
                
        // Vidrio del espejo
        if (texturaEspejo != 0) {
                glMatrixMode(GL_TEXTURE);
                glPushMatrix();
                glScalef(1f, -1f, 1f); // Voltear la imagen porque Java la carga de cabeza
                glMatrixMode(GL_MODELVIEW);

                Cubo.dibujarConTextura(
                        0f, 1.55f, espejoZ + 0.021f, // Un poquito hacia adelante del marco
                        espejoAncho * 0.92f, espejoAlto * 0.92f, 0.01f,
                        texturaEspejo);

                glMatrixMode(GL_TEXTURE);
                glPopMatrix();
                glMatrixMode(GL_MODELVIEW);
        } else {
                Cubo.dibujar(
                        0f, 1.55f, espejoZ + 0.021f,
                        espejoAncho * 0.92f, espejoAlto * 0.92f, 0.01f,
                        0.75f, 0.90f, 0.95f); // Color reflejante
        }

        glPopMatrix();
    }
}
