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
}