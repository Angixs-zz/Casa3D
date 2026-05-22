package personaje;

import objetos.Cubo;

import static org.lwjgl.opengl.GL11.*;

public class Girasol {

    private float x;
    private float y;
    private float z;

    public Girasol(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void dibujar() {
        glPushMatrix();

        glTranslatef(x, y, z);

        dibujarTallo();
        dibujarCabeza();
        dibujarPetalos();
        dibujarHojas();

        glPopMatrix();
    }

    private void dibujarTallo() {
        Cubo.dibujar(
                0f, 0.75f, 0f,
                0.18f, 1.5f, 0.18f,
                0.1f, 0.55f, 0.1f
        );
    }

    private void dibujarCabeza() {
        Cubo.dibujar(
                0f, 1.65f, 0f,
                0.65f, 0.65f, 0.18f,
                0.35f, 0.18f, 0.05f
        );
    }

    private void dibujarPetalos() {
        float amarilloR = 1.0f;
        float amarilloG = 0.85f;
        float amarilloB = 0.05f;

        // Arriba
        Cubo.dibujar(
                0f, 2.1f, 0f,
                0.35f, 0.35f, 0.12f,
                amarilloR, amarilloG, amarilloB
        );

        // Abajo
        Cubo.dibujar(
                0f, 1.2f, 0f,
                0.35f, 0.35f, 0.12f,
                amarilloR, amarilloG, amarilloB
        );

        // Izquierda
        Cubo.dibujar(
                -0.45f, 1.65f, 0f,
                0.35f, 0.35f, 0.12f,
                amarilloR, amarilloG, amarilloB
        );

        // Derecha
        Cubo.dibujar(
                0.45f, 1.65f, 0f,
                0.35f, 0.35f, 0.12f,
                amarilloR, amarilloG, amarilloB
        );

        // Esquina superior izquierda
        Cubo.dibujar(
                -0.32f, 1.97f, 0f,
                0.3f, 0.3f, 0.12f,
                amarilloR, amarilloG, amarilloB
        );

        // Esquina superior derecha
        Cubo.dibujar(
                0.32f, 1.97f, 0f,
                0.3f, 0.3f, 0.12f,
                amarilloR, amarilloG, amarilloB
        );

        // Esquina inferior izquierda
        Cubo.dibujar(
                -0.32f, 1.33f, 0f,
                0.3f, 0.3f, 0.12f,
                amarilloR, amarilloG, amarilloB
        );

        // Esquina inferior derecha
        Cubo.dibujar(
                0.32f, 1.33f, 0f,
                0.3f, 0.3f, 0.12f,
                amarilloR, amarilloG, amarilloB
        );
    }

    private void dibujarHojas() {
        // Hoja izquierda
        Cubo.dibujar(
                -0.35f, 0.85f, 0f,
                0.55f, 0.18f, 0.12f,
                0.05f, 0.45f, 0.05f
        );

        // Hoja derecha
        Cubo.dibujar(
                0.35f, 1.05f, 0f,
                0.55f, 0.18f, 0.12f,
                0.05f, 0.45f, 0.05f
        );
    }
}
