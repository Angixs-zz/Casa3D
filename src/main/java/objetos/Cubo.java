package objetos;

import static org.lwjgl.opengl.GL11.*;

public class Cubo {

    public static void dibujarTransparente(
            float x, float y, float z,
            float ancho, float alto, float profundo,
            float r, float g, float b, float alpha) {

        float x1 = x - ancho / 2; float x2 = x + ancho / 2;
        float y1 = y - alto / 2; float y2 = y + alto / 2;
        float z1 = z - profundo / 2; float z2 = z + profundo / 2;

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDepthMask(false); // Para que el cristal no tape la geometría de atrás

        glDisable(GL_TEXTURE_2D);
        glColor4f(r, g, b, alpha);

        glBegin(GL_QUADS);
        // Frente
        glVertex3f(x1, y1, z2); glVertex3f(x2, y1, z2); glVertex3f(x2, y2, z2); glVertex3f(x1, y2, z2);
        // Atrás
        glVertex3f(x2, y1, z1); glVertex3f(x1, y1, z1); glVertex3f(x1, y2, z1); glVertex3f(x2, y2, z1);
        // Arriba
        glVertex3f(x1, y2, z1); glVertex3f(x1, y2, z2); glVertex3f(x2, y2, z2); glVertex3f(x2, y2, z1);
        // Abajo
        glVertex3f(x1, y1, z1); glVertex3f(x2, y1, z1); glVertex3f(x2, y1, z2); glVertex3f(x1, y1, z2);
        // Izquierda
        glVertex3f(x1, y1, z1); glVertex3f(x1, y1, z2); glVertex3f(x1, y2, z2); glVertex3f(x1, y2, z1);
        // Derecha
        glVertex3f(x2, y1, z2); glVertex3f(x2, y1, z1); glVertex3f(x2, y2, z1); glVertex3f(x2, y2, z2);
        glEnd();

        glDepthMask(true);
        glDisable(GL_BLEND);
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

    public static void dibujarConTexturaTransparente(
            float x, float y, float z,
            float ancho, float alto, float profundo,
            int textureID, float alpha) {

        float x1 = x - ancho / 2;
        float x2 = x + ancho / 2;

        float y1 = y - alto / 2;
        float y2 = y + alto / 2;

        float z1 = z - profundo / 2;
        float z2 = z + profundo / 2;

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureID);
        
        glColor4f(1f, 1f, 1f, alpha);

        glBegin(GL_QUADS);

        float tcAncho = 1.0f;
        float tcAlto = 1.0f;
        float tcProfundo = 1.0f;

        // Frente
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z2);
        glTexCoord2f(tcAncho, 0.0f);    glVertex3f(x2, y1, z2);
        glTexCoord2f(tcAncho, tcAlto);   glVertex3f(x2, y2, z2);
        glTexCoord2f(0.0f, tcAlto);      glVertex3f(x1, y2, z2);

        // Atrás
        glTexCoord2f(tcAncho, 0.0f);    glVertex3f(x2, y1, z1);
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(0.0f, tcAlto);      glVertex3f(x1, y2, z1);
        glTexCoord2f(tcAncho, tcAlto);   glVertex3f(x2, y2, z1);

        // Izquierda
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(tcProfundo, 0.0f); glVertex3f(x1, y1, z2);
        glTexCoord2f(tcProfundo, tcAlto);glVertex3f(x1, y2, z2);
        glTexCoord2f(0.0f, tcAlto);      glVertex3f(x1, y2, z1);

        // Derecha
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x2, y1, z2);
        glTexCoord2f(tcProfundo, 0.0f); glVertex3f(x2, y1, z1);
        glTexCoord2f(tcProfundo, tcAlto);glVertex3f(x2, y2, z1);
        glTexCoord2f(0.0f, tcAlto);      glVertex3f(x2, y2, z2);

        // Arriba
        glTexCoord2f(0.0f, tcProfundo);  glVertex3f(x1, y2, z2);
        glTexCoord2f(tcAncho, tcProfundo);glVertex3f(x2, y2, z2);
        glTexCoord2f(tcAncho, 0.0f);     glVertex3f(x2, y2, z1);
        glTexCoord2f(0.0f, 0.0f);        glVertex3f(x1, y2, z1);

        // Abajo
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

    public static void dibujarConDosTexturasMapeadas(
            float x, float y, float z,
            float ancho, float alto, float profundo,
            int texturaExterior, int texturaInterior,
            float repetirX_Ext, float repetirY_Ext,
            float repetirX_Int, float repetirY_Int) {

        float x1 = x - ancho / 2;
        float x2 = x + ancho / 2;
        float y1 = y - alto / 2;
        float y2 = y + alto / 2;
        float z1 = z - profundo / 2;
        float z2 = z + profundo / 2;

        glEnable(GL_TEXTURE_2D);
        glColor4f(1f, 1f, 1f, 1f);

        // --- 1. CARA FRENTE (MIRA HACIA +Z): TEXTURA INTERIOR ---
        glBindTexture(GL_TEXTURE_2D, texturaInterior);
        glBegin(GL_QUADS);
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z2);
        glTexCoord2f(repetirX_Int, 0.0f); glVertex3f(x2, y1, z2);
        glTexCoord2f(repetirX_Int, repetirY_Int); glVertex3f(x2, y2, z2);
        glTexCoord2f(0.0f, repetirY_Int);  glVertex3f(x1, y2, z2);
        glEnd();

        // --- 2. OTRAS CARAS: TEXTURA EXTERIOR ---
        glBindTexture(GL_TEXTURE_2D, texturaExterior);
        glBegin(GL_QUADS);

        // Atrás (-Z)
        glTexCoord2f(repetirX_Ext, 0.0f); glVertex3f(x2, y1, z1);
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(0.0f, repetirY_Ext);  glVertex3f(x1, y2, z1);
        glTexCoord2f(repetirX_Ext, repetirY_Ext); glVertex3f(x2, y2, z1);

        // Izquierda (-X)
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y1, z1);
        glTexCoord2f(1.0f, 0.0f);       glVertex3f(x1, y1, z2);
        glTexCoord2f(1.0f, 1.0f);       glVertex3f(x1, y2, z2);
        glTexCoord2f(0.0f, 1.0f);       glVertex3f(x1, y2, z1);

        // Derecha (+X)
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x2, y1, z2);
        glTexCoord2f(1.0f, 0.0f);       glVertex3f(x2, y1, z1);
        glTexCoord2f(1.0f, 1.0f);       glVertex3f(x2, y2, z1);
        glTexCoord2f(0.0f, 1.0f);       glVertex3f(x2, y2, z2);

        // Arriba (+Y)
        glTexCoord2f(0.0f, 1.0f);       glVertex3f(x1, y2, z2);
        glTexCoord2f(1.0f, 1.0f);       glVertex3f(x2, y2, z2);
        glTexCoord2f(1.0f, 0.0f);       glVertex3f(x2, y2, z1);
        glTexCoord2f(0.0f, 0.0f);       glVertex3f(x1, y2, z1);

        // Abajo (-Y)
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

    public static void dibujarCilindroConTextura(
            float xCentro, float yBase, float zCentro,
            float radio, float altura, int lados, int textureID,
            float repeticionesY) {

        glPushMatrix();
        glTranslatef(xCentro, yBase, zCentro);

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureID);
        glColor4f(1f, 1f, 1f, 1f);

        // Cuerpo
        glBegin(GL_QUAD_STRIP);
        for (int i = 0; i <= lados; i++) {
            float angulo = (float) (i * 2.0 * Math.PI / lados);
            float cx = (float) (radio * Math.cos(angulo));
            float cz = (float) (radio * Math.sin(angulo));
            float u = (float) i / lados;
            
            glTexCoord2f(u * 2.0f, 0.0f); 
            glVertex3f(cx, 0, cz);
            
            glTexCoord2f(u * 2.0f, repeticionesY);
            glVertex3f(cx, altura, cz);
        }
        glEnd();

        // Tapa superior
        glBegin(GL_TRIANGLE_FAN);
        glTexCoord2f(0.5f, 0.5f);
        glVertex3f(0, altura, 0);
        for (int i = 0; i <= lados; i++) {
            float angulo = (float) (i * 2.0 * Math.PI / lados);
            float cx = (float) (radio * Math.cos(angulo));
            float cz = (float) (radio * Math.sin(angulo));
            glTexCoord2f(0.5f + 0.5f * (float)Math.cos(angulo), 0.5f + 0.5f * (float)Math.sin(angulo));
            glVertex3f(cx, altura, cz);
        }
        glEnd();
        
        // Tapa inferior
        glBegin(GL_TRIANGLE_FAN);
        glTexCoord2f(0.5f, 0.5f);
        glVertex3f(0, 0, 0);
        for (int i = lados; i >= 0; i--) {
            float angulo = (float) (i * 2.0 * Math.PI / lados);
            float cx = (float) (radio * Math.cos(angulo));
            float cz = (float) (radio * Math.sin(angulo));
            glTexCoord2f(0.5f + 0.5f * (float)Math.cos(angulo), 0.5f + 0.5f * (float)Math.sin(angulo));
            glVertex3f(cx, 0, cz);
        }
        glEnd();

        glDisable(GL_TEXTURE_2D);
        glPopMatrix();
    }

    public static void dibujarCilindro(
            float xCentro, float yBase, float zCentro,
            float radio, float altura, int lados,
            float r, float g, float b) {

        glPushMatrix();
        glTranslatef(xCentro, yBase, zCentro);
        glColor3f(r, g, b);

        // Cuerpo
        glBegin(GL_QUAD_STRIP);
        for (int i = 0; i <= lados; i++) {
            float angulo = (float) (i * 2.0 * Math.PI / lados);
            float cx = (float) (radio * Math.cos(angulo));
            float cz = (float) (radio * Math.sin(angulo));
            glVertex3f(cx, 0, cz);
            glVertex3f(cx, altura, cz);
        }
        glEnd();

        // Tapa superior
        glBegin(GL_TRIANGLE_FAN);
        glVertex3f(0, altura, 0);
        for (int i = 0; i <= lados; i++) {
            float angulo = (float) (i * 2.0 * Math.PI / lados);
            float cx = (float) (radio * Math.cos(angulo));
            float cz = (float) (radio * Math.sin(angulo));
            glVertex3f(cx, altura, cz);
        }
        glEnd();

        // Tapa inferior
        glBegin(GL_TRIANGLE_FAN);
        glVertex3f(0, 0, 0);
        for (int i = lados; i >= 0; i--) {
            float angulo = (float) (i * 2.0 * Math.PI / lados);
            float cx = (float) (radio * Math.cos(angulo));
            float cz = (float) (radio * Math.sin(angulo));
            glVertex3f(cx, 0, cz);
        }
        glEnd();

        glPopMatrix();
    }
}