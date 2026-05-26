package objetos;

import static org.lwjgl.opengl.GL11.*;

public class Cubo {

    public static void dibujar(
            float x,
            float y,
            float z,
            float ancho,
            float alto,
            float profundo,
            float r,
            float g,
            float b) {

        float x1 = x - ancho / 2;
        float x2 = x + ancho / 2;

        float y1 = y - alto / 2;
        float y2 = y + alto / 2;

        float z1 = z - profundo / 2;
        float z2 = z + profundo / 2;

        glColor3f(r, g, b);

        glBegin(GL_QUADS);

        // Frente
        glVertex3f(x1, y1, z2);
        glVertex3f(x2, y1, z2);
        glVertex3f(x2, y2, z2);
        glVertex3f(x1, y2, z2);

        // Atrás
        glVertex3f(x2, y1, z1);
        glVertex3f(x1, y1, z1);
        glVertex3f(x1, y2, z1);
        glVertex3f(x2, y2, z1);

        // Izquierda
        glVertex3f(x1, y1, z1);
        glVertex3f(x1, y1, z2);
        glVertex3f(x1, y2, z2);
        glVertex3f(x1, y2, z1);

        // Derecha
        glVertex3f(x2, y1, z2);
        glVertex3f(x2, y1, z1);
        glVertex3f(x2, y2, z1);
        glVertex3f(x2, y2, z2);

        // Arriba
        glVertex3f(x1, y2, z2);
        glVertex3f(x2, y2, z2);
        glVertex3f(x2, y2, z1);
        glVertex3f(x1, y2, z1);

        // Abajo
        glVertex3f(x1, y1, z1);
        glVertex3f(x2, y1, z1);
        glVertex3f(x2, y1, z2);
        glVertex3f(x1, y1, z2);

        glEnd();
    }

    public static void dibujar(
            float x,
            float y,
            float z,
            float ancho,
            float alto,
            float profundo,
            float r,
            float g,
            float b,
            float a) {

        float x1 = x - ancho / 2;
        float x2 = x + ancho / 2;

        float y1 = y - alto / 2;
        float y2 = y + alto / 2;

        float z1 = z - profundo / 2;
        float z2 = z + profundo / 2;

        glColor4f(r, g, b, a);

        glBegin(GL_QUADS);

        // Frente
        glVertex3f(x1, y1, z2);
        glVertex3f(x2, y1, z2);
        glVertex3f(x2, y2, z2);
        glVertex3f(x1, y2, z2);

        // Atrás
        glVertex3f(x2, y1, z1);
        glVertex3f(x1, y1, z1);
        glVertex3f(x1, y2, z1);
        glVertex3f(x2, y2, z1);

        // Izquierda
        glVertex3f(x1, y1, z1);
        glVertex3f(x1, y1, z2);
        glVertex3f(x1, y2, z2);
        glVertex3f(x1, y2, z1);

        // Derecha
        glVertex3f(x2, y1, z2);
        glVertex3f(x2, y1, z1);
        glVertex3f(x2, y2, z1);
        glVertex3f(x2, y2, z2);

        // Arriba
        glVertex3f(x1, y2, z2);
        glVertex3f(x2, y2, z2);
        glVertex3f(x2, y2, z1);
        glVertex3f(x1, y2, z1);

        // Abajo
        glVertex3f(x1, y1, z1);
        glVertex3f(x2, y1, z1);
        glVertex3f(x2, y1, z2);
        glVertex3f(x1, y1, z2);

        glEnd();
    }

    /**
     * Dibuja un cuboide texturizado con un identificador de textura dado.
     * Mapea las coordenadas de textura (s, t) escaladas según el tamaño del cuboide
     * para mantener la proporción de la textura y evitar estiramientos.
     */
    public static void dibujarConTextura(
            float x,
            float y,
            float z,
            float ancho,
            float alto,
            float profundo,
            int textureID) {

        float x1 = x - ancho / 2;
        float x2 = x + ancho / 2;

        float y1 = y - alto / 2;
        float y2 = y + alto / 2;

        float z1 = z - profundo / 2;
        float z2 = z + profundo / 2;

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureID);
        
        // Color blanco para no teñir la textura
        glColor4f(1f, 1f, 1f, 1f);

        glBegin(GL_QUADS);

        // Ajustamos la escala de repetición de la textura.
        // Al usar 1.0f la textura se estira exactamente una vez por cara, eliminando la cuadrícula.
        float tcAncho = 1.0f;
        float tcAlto = 1.0f;
        float tcProfundo = 1.0f;

        // Frente (ancho x alto) - Mira hacia +Z
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z2);
        glTexCoord2f(tcAncho, 0.0f);    glVertex3f(x2, y1, z2);
        glTexCoord2f(tcAncho, tcAlto);   glVertex3f(x2, y2, z2);
        glTexCoord2f(0.0f, tcAlto);      glVertex3f(x1, y2, z2);

        // Atrás (ancho x alto) - Mira hacia -Z
        glTexCoord2f(tcAncho, 0.0f);    glVertex3f(x2, y1, z1);
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(0.0f, tcAlto);      glVertex3f(x1, y2, z1);
        glTexCoord2f(tcAncho, tcAlto);   glVertex3f(x2, y2, z1);

        // Izquierda (profundo x alto) - Mira hacia -X
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(tcProfundo, 0.0f); glVertex3f(x1, y1, z2);
        glTexCoord2f(tcProfundo, tcAlto);glVertex3f(x1, y2, z2);
        glTexCoord2f(0.0f, tcAlto);      glVertex3f(x1, y2, z1);

        // Derecha (profundo x alto) - Mira hacia +X
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x2, y1, z2);
        glTexCoord2f(tcProfundo, 0.0f); glVertex3f(x2, y1, z1);
        glTexCoord2f(tcProfundo, tcAlto);glVertex3f(x2, y2, z1);
        glTexCoord2f(0.0f, tcAlto);      glVertex3f(x2, y2, z2);

        // Arriba (ancho x profundo) - Mira hacia +Y
        glTexCoord2f(0.0f, tcProfundo);  glVertex3f(x1, y2, z2);
        glTexCoord2f(tcAncho, tcProfundo);glVertex3f(x2, y2, z2);
        glTexCoord2f(tcAncho, 0.0f);     glVertex3f(x2, y2, z1);
        glTexCoord2f(0.0f, 0.0f);        glVertex3f(x1, y2, z1);

        // Abajo (ancho x profundo) - Mira hacia -Y
        glTexCoord2f(0.0f, 0.0f);        glVertex3f(x1, y1, z1);
        glTexCoord2f(tcAncho, 0.0f);     glVertex3f(x2, y1, z1);
        glTexCoord2f(tcAncho, tcProfundo);glVertex3f(x2, y1, z2);
        glTexCoord2f(0.0f, tcProfundo);  glVertex3f(x1, y1, z2);

        glEnd();

        glDisable(GL_TEXTURE_2D);
    }

    /**
     * Dibuja un cuboide texturizado modulando sus colores con un color RGB dado.
     * Esto permite aplicar detalles de relieve/textura y variar el color de fondo.
     */
    public static void dibujarConTexturaYColor(
            float x,
            float y,
            float z,
            float ancho,
            float alto,
            float profundo,
            int textureID,
            float r,
            float g,
            float b) {

        float x1 = x - ancho / 2;
        float x2 = x + ancho / 2;

        float y1 = y - alto / 2;
        float y2 = y + alto / 2;

        float z1 = z - profundo / 2;
        float z2 = z + profundo / 2;

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureID);
        
        // Aplicamos el color RGB que modulará los tonos de la textura
        glColor4f(r, g, b, 1f);

        glBegin(GL_QUADS);

        // Estiramos la textura una vez por cara
        float tcAncho = 1.0f;
        float tcAlto = 1.0f;
        float tcProfundo = 1.0f;

        // Frente (ancho x alto) - Mira hacia +Z
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z2);
        glTexCoord2f(tcAncho, 0.0f);    glVertex3f(x2, y1, z2);
        glTexCoord2f(tcAncho, tcAlto);   glVertex3f(x2, y2, z2);
        glTexCoord2f(0.0f, tcAlto);      glVertex3f(x1, y2, z2);

        // Atrás (ancho x alto) - Mira hacia -Z
        glTexCoord2f(tcAncho, 0.0f);    glVertex3f(x2, y1, z1);
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(0.0f, tcAlto);      glVertex3f(x1, y2, z1);
        glTexCoord2f(tcAncho, tcAlto);   glVertex3f(x2, y2, z1);

        // Izquierda (profundo x alto) - Mira hacia -X
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(tcProfundo, 0.0f); glVertex3f(x1, y1, z2);
        glTexCoord2f(tcProfundo, tcAlto);glVertex3f(x1, y2, z2);
        glTexCoord2f(0.0f, tcAlto);      glVertex3f(x1, y2, z1);

        // Derecha (profundo x alto) - Mira hacia +X
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x2, y1, z2);
        glTexCoord2f(tcProfundo, 0.0f); glVertex3f(x2, y1, z1);
        glTexCoord2f(tcProfundo, tcAlto);glVertex3f(x2, y2, z1);
        glTexCoord2f(0.0f, tcAlto);      glVertex3f(x2, y2, z2);

        // Arriba (ancho x profundo) - Mira hacia +Y
        glTexCoord2f(0.0f, tcProfundo);  glVertex3f(x1, y2, z2);
        glTexCoord2f(tcAncho, tcProfundo);glVertex3f(x2, y2, z2);
        glTexCoord2f(tcAncho, 0.0f);     glVertex3f(x2, y2, z1);
        glTexCoord2f(0.0f, 0.0f);        glVertex3f(x1, y2, z1);

        // Abajo (ancho x profundo) - Mira hacia -Y
        glTexCoord2f(0.0f, 0.0f);        glVertex3f(x1, y1, z1);
        glTexCoord2f(tcAncho, 0.0f);     glVertex3f(x2, y1, z1);
        glTexCoord2f(tcAncho, tcProfundo);glVertex3f(x2, y1, z2);
        glTexCoord2f(0.0f, tcProfundo);  glVertex3f(x1, y1, z2);

        glEnd();

        glDisable(GL_TEXTURE_2D);
    }

    /**
     * Dibuja un cuboide con dos texturas diferentes:
     * Una textura para la cara posterior (Atrás / -Z), ideal para el interior de una habitación,
     * y otra textura para las demás 5 caras (Frente, Izquierda, Derecha, Arriba, Abajo), ideal para el exterior/fachada.
     */
    public static void dibujarConDosTexturas(
            float x,
            float y,
            float z,
            float ancho,
            float alto,
            float profundo,
            int texturaExterior,
            int texturaInterior) {

        float x1 = x - ancho / 2;
        float x2 = x + ancho / 2;

        float y1 = y - alto / 2;
        float y2 = y + alto / 2;

        float z1 = z - profundo / 2;
        float z2 = z + profundo / 2;

        glEnable(GL_TEXTURE_2D);
        glColor4f(1f, 1f, 1f, 1f); // Resetear color base a blanco

        // --- 1. CARA FRENTE (MIRA HACIA +Z): TEXTURA INTERIOR (LADRILLO) ---
        glBindTexture(GL_TEXTURE_2D, texturaInterior);
        glBegin(GL_QUADS);
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z2);
        glTexCoord2f(1.0f, 0.0f);       glVertex3f(x2, y1, z2);
        glTexCoord2f(1.0f, 1.0f);       glVertex3f(x2, y2, z2);
        glTexCoord2f(0.0f, 1.0f);       glVertex3f(x1, y2, z2);
        glEnd();

        // --- 2. OTRAS CARAS: TEXTURA EXTERIOR (PARED BLANCA) ---
        glBindTexture(GL_TEXTURE_2D, texturaExterior);
        glBegin(GL_QUADS);

        // Atrás (ancho x alto) - Mira hacia -Z
        glTexCoord2f(1.0f, 0.0f);       glVertex3f(x2, y1, z1);
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(0.0f, 1.0f);       glVertex3f(x1, y2, z1);
        glTexCoord2f(1.0f, 1.0f);       glVertex3f(x2, y2, z1);

        // Izquierda (profundo x alto) - Mira hacia -X
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(1.0f, 0.0f);       glVertex3f(x1, y1, z2);
        glTexCoord2f(1.0f, 1.0f);       glVertex3f(x1, y2, z2);
        glTexCoord2f(0.0f, 1.0f);       glVertex3f(x1, y2, z1);

        // Derecha (profundo x alto) - Mira hacia +X
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x2, y1, z2);
        glTexCoord2f(1.0f, 0.0f);       glVertex3f(x2, y1, z1);
        glTexCoord2f(1.0f, 1.0f);       glVertex3f(x2, y2, z1);
        glTexCoord2f(0.0f, 1.0f);       glVertex3f(x2, y2, z2);

        // Arriba (ancho x profundo) - Mira hacia +Y
        glTexCoord2f(0.0f, 1.0f);       glVertex3f(x1, y2, z2);
        glTexCoord2f(1.0f, 1.0f);       glVertex3f(x2, y2, z2);
        glTexCoord2f(1.0f, 0.0f);       glVertex3f(x2, y2, z1);
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y2, z1);

        // Abajo (ancho x profundo) - Mira hacia -Y
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(1.0f, 0.0f);       glVertex3f(x2, y1, z1);
        glTexCoord2f(1.0f, 1.0f);       glVertex3f(x2, y1, z2);
        glTexCoord2f(0.0f, 1.0f);       glVertex3f(x1, y1, z2);

        glEnd();

        glDisable(GL_TEXTURE_2D);
    }

    public static void dibujarConTexturaRepetida(
            float x, float y, float z,
            float ancho, float alto, float profundo,
            int textureID, float repeticionesPorMetro) {

        float x1 = x - ancho / 2;
        float x2 = x + ancho / 2;
        float y1 = y - alto / 2;
        float y2 = y + alto / 2;
        float z1 = z - profundo / 2;
        float z2 = z + profundo / 2;

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureID);
        glColor4f(1f, 1f, 1f, 1f);
        glBegin(GL_QUADS);

        float tcAncho = ancho * repeticionesPorMetro;
        float tcAlto = alto * repeticionesPorMetro;
        float tcProfundo = profundo * repeticionesPorMetro;

        // Frente
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z2);
        glTexCoord2f(tcAncho, 0.0f);    glVertex3f(x2, y1, z2);
        glTexCoord2f(tcAncho, tcAlto);  glVertex3f(x2, y2, z2);
        glTexCoord2f(0.0f, tcAlto);     glVertex3f(x1, y2, z2);

        // Atrás
        glTexCoord2f(tcAncho, 0.0f);    glVertex3f(x2, y1, z1);
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(0.0f, tcAlto);     glVertex3f(x1, y2, z1);
        glTexCoord2f(tcAncho, tcAlto);  glVertex3f(x2, y2, z1);

        // Izquierda
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(tcProfundo, 0.0f); glVertex3f(x1, y1, z2);
        glTexCoord2f(tcProfundo, tcAlto);glVertex3f(x1, y2, z2);
        glTexCoord2f(0.0f, tcAlto);     glVertex3f(x1, y2, z1);

        // Derecha
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x2, y1, z2);
        glTexCoord2f(tcProfundo, 0.0f); glVertex3f(x2, y1, z1);
        glTexCoord2f(tcProfundo, tcAlto);glVertex3f(x2, y2, z1);
        glTexCoord2f(0.0f, tcAlto);     glVertex3f(x2, y2, z2);

        // Arriba
        glTexCoord2f(0.0f, tcProfundo); glVertex3f(x1, y2, z2);
        glTexCoord2f(tcAncho, tcProfundo);glVertex3f(x2, y2, z2);
        glTexCoord2f(tcAncho, 0.0f);    glVertex3f(x2, y2, z1);
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y2, z1);

        // Abajo
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(tcAncho, 0.0f);    glVertex3f(x2, y1, z1);
        glTexCoord2f(tcAncho, tcProfundo);glVertex3f(x2, y1, z2);
        glTexCoord2f(0.0f, tcProfundo); glVertex3f(x1, y1, z2);

        glEnd();
        glDisable(GL_TEXTURE_2D);
    }
}