package objetos;

import static org.lwjgl.opengl.GL11.*;

public class Escalera3D {

    public static void dibujar(
            float x,
            float y,
            float z,
            float ancho,
            float largo,
            float alturaTotal,
            int escalones,
            float rotacionY) {
        glPushMatrix();

        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        dibujarEscalones(ancho, largo, alturaTotal, escalones);
        dibujarBase(ancho, largo, alturaTotal);
        dibujarBarandales(ancho, largo, alturaTotal);

        glPopMatrix();
    }

    private static void dibujarEscalones(
            float ancho,
            float largo,
            float alturaTotal,
            int escalones) {
        float profundidadEscalon = largo / escalones;
        float alturaEscalon = alturaTotal / escalones;

        for (int i = 0; i < escalones; i++) {
            float z = -largo / 2f + profundidadEscalon * i + profundidadEscalon / 2f;
            float y = alturaEscalon * i + alturaEscalon / 2f;

            Cubo.dibujar(
                    0f,
                    y,
                    z,
                    ancho,
                    alturaEscalon,
                    profundidadEscalon,
                    0.82f,
                    0.82f,
                    0.82f);
        }
    }

    private static void dibujarBase(
            float ancho,
            float largo,
            float alturaTotal) {
        glPushMatrix();

        glColor3f(0.75f, 0.75f, 0.75f);

        glBegin(GL_TRIANGLES);

        // Lado izquierdo
        glVertex3f(-ancho / 2f, 0f, -largo / 2f);
        glVertex3f(-ancho / 2f, 0f, largo / 2f);
        glVertex3f(-ancho / 2f, alturaTotal, largo / 2f);

        // Lado derecho
        glVertex3f(ancho / 2f, 0f, -largo / 2f);
        glVertex3f(ancho / 2f, alturaTotal, largo / 2f);
        glVertex3f(ancho / 2f, 0f, largo / 2f);

        glEnd();

        glPopMatrix();
    }

    private static void dibujarBarandales(
            float ancho,
            float largo,
            float alturaTotal) {
        float alturaBarandal = alturaTotal + 0.8f;

        // Postes izquierdos
        Cubo.dibujar(-ancho / 2f - 0.08f, 0.6f, -largo / 2f, 0.08f, 1.2f, 0.08f, 0f, 0f, 0f);
        Cubo.dibujar(-ancho / 2f - 0.08f, alturaTotal / 2f + 0.6f, 0f, 0.08f, 1.2f, 0.08f, 0f, 0f, 0f);
        Cubo.dibujar(-ancho / 2f - 0.08f, alturaTotal + 0.6f, largo / 2f, 0.08f, 1.2f, 0.08f, 0f, 0f, 0f);

        // Postes derechos
        Cubo.dibujar(ancho / 2f + 0.08f, 0.6f, -largo / 2f, 0.08f, 1.2f, 0.08f, 0f, 0f, 0f);
        Cubo.dibujar(ancho / 2f + 0.08f, alturaTotal / 2f + 0.6f, 0f, 0.08f, 1.2f, 0.08f, 0f, 0f, 0f);
        Cubo.dibujar(ancho / 2f + 0.08f, alturaTotal + 0.6f, largo / 2f, 0.08f, 1.2f, 0.08f, 0f, 0f, 0f);

        // Barandal superior izquierdo
        glPushMatrix();
        glTranslatef(-ancho / 2f - 0.08f, alturaBarandal / 2f, 0f);
        glRotatef(-32f, 1f, 0f, 0f);
        Cubo.dibujar(0f, 0f, 0f, 0.08f, 0.08f, largo + 0.4f, 0f, 0f, 0f);
        glPopMatrix();

        // Barandal superior derecho
        glPushMatrix();
        glTranslatef(ancho / 2f + 0.08f, alturaBarandal / 2f, 0f);
        glRotatef(-32f, 1f, 0f, 0f);
        Cubo.dibujar(0f, 0f, 0f, 0.08f, 0.08f, largo + 0.4f, 0f, 0f, 0f);
        glPopMatrix();
    }
}