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

    public static int texturaCarro = 0;

    private static void dibujarParteCarroceria(float x, float y, float z, float w, float h, float d, float r, float g, float b) {
        if (texturaCarro != 0) {
            Cubo.dibujarConTextura(x, y, z, w, h, d, texturaCarro);
        } else {
            Cubo.dibujar(x, y, z, w, h, d, r, g, b);
        }
    }

    public static void dibujar() {
        float xGeo = 1.3f;
        float zGeo = 2.5f;

        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        dibujarCarroceriaAerodinamica();
        dibujarLlantas();

        glPopMatrix();
    }

    private static void dibujarCarroceriaAerodinamica() {
        float[] z = {-1.7f, -1.7f, -0.6f, -0.3f, 0.5f, 1.0f, 1.7f, 1.7f};
        float[] y = {0.2f,  0.45f, 0.60f, 1.05f, 1.05f, 0.65f, 0.55f, 0.2f};
        
        for (int i = 0; i < z.length; i++) {
            z[i] = escalar(z[i]);
            y[i] = escalar(y[i]);
        }
        
        float x1 = escalar(-0.65f);
        float x2 = escalar(0.65f);
        
        if (texturaCarro != 0) {
            glEnable(GL_TEXTURE_2D);
            glBindTexture(GL_TEXTURE_2D, texturaCarro);
            glColor4f(1f, 1f, 1f, 1f);
        } else {
            glDisable(GL_TEXTURE_2D);
            glColor3f(0.78f, 0.05f, 0.05f); // Rojo por defecto
        }
        
        // Lado izquierdo (X = x1)
        glBegin(GL_POLYGON);
        for (int i = 0; i < z.length; i++) {
            if (texturaCarro != 0) glTexCoord2f((z[i]/escalar(3.4f)) + 0.5f, y[i]/escalar(1.2f));
            glVertex3f(x1, y[i], z[i]);
        }
        glEnd();
        
        // Lado derecho (X = x2)
        glBegin(GL_POLYGON);
        for (int i = z.length - 1; i >= 0; i--) {
            if (texturaCarro != 0) glTexCoord2f((z[i]/escalar(3.4f)) + 0.5f, y[i]/escalar(1.2f));
            glVertex3f(x2, y[i], z[i]);
        }
        glEnd();
        
        // Techo, cofre, baúl, frentes y piso (La banda extrudida)
        glBegin(GL_QUAD_STRIP);
        float texU = 0f;
        for (int i = 0; i < z.length; i++) {
            if (i > 0) {
                float dz = z[i] - z[i-1];
                float dy = y[i] - y[i-1];
                texU += (float)Math.sqrt(dz*dz + dy*dy) / escalar(1.0f);
            }
            if (texturaCarro != 0) glTexCoord2f(texU, 0f);
            glVertex3f(x2, y[i], z[i]);
            if (texturaCarro != 0) glTexCoord2f(texU, 1f);
            glVertex3f(x1, y[i], z[i]);
        }
        // Cerrar la banda inferior
        float dzC = z[0] - z[z.length-1];
        float dyC = y[0] - y[z.length-1];
        texU += (float)Math.sqrt(dzC*dzC + dyC*dyC) / escalar(1.0f);
        if (texturaCarro != 0) glTexCoord2f(texU, 0f);
        glVertex3f(x2, y[0], z[0]);
        if (texturaCarro != 0) glTexCoord2f(texU, 1f);
        glVertex3f(x1, y[0], z[0]);
        glEnd();
        
        if (texturaCarro != 0) {
            glDisable(GL_TEXTURE_2D);
        }

        // =======================
        // CRISTALES (Ventanas oscuras)
        // =======================
        glColor4f(0.1f, 0.1f, 0.1f, 0.9f); 
        float offset = escalar(0.01f);
        
        // Parabrisas Frontal
        glBegin(GL_QUADS);
        glVertex3f(x2 - offset, y[2] + offset, z[2] - offset);
        glVertex3f(x1 + offset, y[2] + offset, z[2] - offset);
        glVertex3f(x1 + offset, y[3] + offset, z[3] - offset);
        glVertex3f(x2 - offset, y[3] + offset, z[3] - offset);
        glEnd();
        
        // Parabrisas Trasero
        glBegin(GL_QUADS);
        glVertex3f(x2 - offset, y[5] + offset, z[5] + offset);
        glVertex3f(x1 + offset, y[5] + offset, z[5] + offset);
        glVertex3f(x1 + offset, y[4] + offset, z[4] + offset);
        glVertex3f(x2 - offset, y[4] + offset, z[4] + offset);
        glEnd();
        
        // Ventanas Laterales Izquierdas
        glBegin(GL_POLYGON);
        glVertex3f(x1 - offset, y[2] + offset*2, z[2] + offset);
        glVertex3f(x1 - offset, y[3] - offset, z[3] + offset);
        glVertex3f(x1 - offset, y[4] - offset, z[4] - offset);
        glVertex3f(x1 - offset, y[5] + offset*2, z[5] - offset);
        glEnd();
        
        // Ventanas Laterales Derechas
        glBegin(GL_POLYGON);
        glVertex3f(x2 + offset, y[5] + offset*2, z[5] - offset);
        glVertex3f(x2 + offset, y[4] - offset, z[4] - offset);
        glVertex3f(x2 + offset, y[3] - offset, z[3] + offset);
        glVertex3f(x2 + offset, y[2] + offset*2, z[2] + offset);
        glEnd();
        
        // Faros Delanteros (Blancos/Amarillos)
        glColor3f(0.9f, 0.9f, 0.6f);
        glBegin(GL_QUADS);
        glVertex3f(x2 - escalar(0.1f), y[1] - escalar(0.05f), z[1] - offset);
        glVertex3f(x2 - escalar(0.3f), y[1] - escalar(0.05f), z[1] - offset);
        glVertex3f(x2 - escalar(0.3f), y[1] - escalar(0.15f), z[1] - offset);
        glVertex3f(x2 - escalar(0.1f), y[1] - escalar(0.15f), z[1] - offset);
        
        glVertex3f(x1 + escalar(0.3f), y[1] - escalar(0.05f), z[1] - offset);
        glVertex3f(x1 + escalar(0.1f), y[1] - escalar(0.05f), z[1] - offset);
        glVertex3f(x1 + escalar(0.1f), y[1] - escalar(0.15f), z[1] - offset);
        glVertex3f(x1 + escalar(0.3f), y[1] - escalar(0.15f), z[1] - offset);
        glEnd();

        // Faros Traseros (Rojos)
        glColor3f(0.8f, 0.1f, 0.1f);
        glBegin(GL_QUADS);
        glVertex3f(x2 - escalar(0.1f), y[6] - escalar(0.05f), z[6] + offset);
        glVertex3f(x1 + escalar(0.1f), y[6] - escalar(0.05f), z[6] + offset);
        glVertex3f(x1 + escalar(0.1f), y[6] - escalar(0.15f), z[6] + offset);
        glVertex3f(x2 - escalar(0.1f), y[6] - escalar(0.15f), z[6] + offset);
        glEnd();
    }

    private static void dibujarLlantas() {
        dibujarLlanta(-0.65f, -1.05f); // Delantera izq
        dibujarLlanta(0.65f, -1.05f);  // Delantera der
        dibujarLlanta(-0.65f, 1.05f);  // Trasera izq
        dibujarLlanta(0.65f, 1.05f);   // Trasera der
    }

    private static void dibujarLlanta(float xLocalGeo, float zLocalGeo) {
        float xLocal = escalar(xLocalGeo);
        float zLocal = escalar(zLocalGeo);
        
        glPushMatrix();
        glTranslatef(xLocal, escalar(0.22f), zLocal);
        
        // Rotar para acostar el cilindro sobre el eje X
        glRotatef(90f, 0f, 0f, 1f);
        
        float radioLlanta = escalar(0.22f);
        float anchoLlanta = escalar(0.16f);
        
        // Centrar la rueda en el eje X
        glTranslatef(0f, -anchoLlanta / 2f, 0f);
        
        // Neumático
        Cubo.dibujarCilindro(0f, 0f, 0f, radioLlanta, anchoLlanta, 20, 0.04f, 0.04f, 0.04f);
        // Rin (Ligeramente más largo para sobresalir un poco y radio más corto)
        Cubo.dibujarCilindro(0f, -escalar(0.01f), 0f, radioLlanta * 0.6f, anchoLlanta + escalar(0.02f), 16, 0.65f, 0.65f, 0.68f);
        
        glPopMatrix();
    }
}