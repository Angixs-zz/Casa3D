package personaje;

import objetos.Cubo;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Girasol {

    private float x;
    private float y;
    private float z;

    private float rotacionY;
    private float escala;

    public Girasol(float x, float y, float z) {
        this(x, y, z, 0.7f);
    }

    public Girasol(float x, float y, float z, float escala) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.escala = escala;
        this.rotacionY = 0f;
    }

    public void actualizar(long ventana) {
        float velocidadMovimiento = 0.12f;
        float velocidadRotacion = 2.2f;

        if (glfwGetKey(ventana, GLFW_KEY_A) == GLFW_PRESS) {
            rotacionY += velocidadRotacion;
        }

        if (glfwGetKey(ventana, GLFW_KEY_D) == GLFW_PRESS) {
            rotacionY -= velocidadRotacion;
        }

        float radianes = (float) Math.toRadians(rotacionY);

        float direccionX = (float) Math.sin(radianes);
        float direccionZ = (float) Math.cos(radianes);

        if (glfwGetKey(ventana, GLFW_KEY_W) == GLFW_PRESS) {
            x += direccionX * velocidadMovimiento;
            z += direccionZ * velocidadMovimiento;
        }

        if (glfwGetKey(ventana, GLFW_KEY_S) == GLFW_PRESS) {
            x -= direccionX * velocidadMovimiento;
            z -= direccionZ * velocidadMovimiento;
        }

        // Temporal: subir y bajar manualmente hasta que hagamos escaleras reales
        if (glfwGetKey(ventana, GLFW_KEY_E) == GLFW_PRESS) {
            y += 0.06f;
        }

        if (glfwGetKey(ventana, GLFW_KEY_Q) == GLFW_PRESS) {
            y -= 0.06f;
        }

        if (y < 0f) {
            y = 0f;
        }
    }

    public void dibujar() {
        glPushMatrix();

        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);
        glScalef(escala, escala, escala);

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

        Cubo.dibujar(0f, 2.1f, 0f, 0.35f, 0.35f, 0.12f, amarilloR, amarilloG, amarilloB);
        Cubo.dibujar(0f, 1.2f, 0f, 0.35f, 0.35f, 0.12f, amarilloR, amarilloG, amarilloB);
        Cubo.dibujar(-0.45f, 1.65f, 0f, 0.35f, 0.35f, 0.12f, amarilloR, amarilloG, amarilloB);
        Cubo.dibujar(0.45f, 1.65f, 0f, 0.35f, 0.35f, 0.12f, amarilloR, amarilloG, amarilloB);

        Cubo.dibujar(-0.32f, 1.97f, 0f, 0.3f, 0.3f, 0.12f, amarilloR, amarilloG, amarilloB);
        Cubo.dibujar(0.32f, 1.97f, 0f, 0.3f, 0.3f, 0.12f, amarilloR, amarilloG, amarilloB);
        Cubo.dibujar(-0.32f, 1.33f, 0f, 0.3f, 0.3f, 0.12f, amarilloR, amarilloG, amarilloB);
        Cubo.dibujar(0.32f, 1.33f, 0f, 0.3f, 0.3f, 0.12f, amarilloR, amarilloG, amarilloB);
    }

    private void dibujarHojas() {
        Cubo.dibujar(
                -0.35f, 0.85f, 0f,
                0.55f, 0.18f, 0.12f,
                0.05f, 0.45f, 0.05f
        );

        Cubo.dibujar(
                0.35f, 1.05f, 0f,
                0.55f, 0.18f, 0.12f,
                0.05f, 0.45f, 0.05f
        );
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getRotacionY() {
        return rotacionY;
    }

    public float getAlturaOjos() {
        return y + (1.55f * escala);
    }
}
