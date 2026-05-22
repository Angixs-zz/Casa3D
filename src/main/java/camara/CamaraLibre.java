package camara;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class CamaraLibre {

    private float distancia = 28f;
    private float rotacionY = 35f;
    private float rotacionX = 25f;
    private float moverX = 0f;
    private float moverY = -2f;
    private float moverZ = 0f;

    public void actualizar(long ventana) {
        float velocidadMovimiento = 0.25f;
        float velocidadRotacion = 1.5f;
        float velocidadZoom = 0.5f;

        if (glfwGetKey(ventana, GLFW_KEY_LEFT) == GLFW_PRESS) {
            rotacionY -= velocidadRotacion;
        }

        if (glfwGetKey(ventana, GLFW_KEY_RIGHT) == GLFW_PRESS) {
            rotacionY += velocidadRotacion;
        }

        if (glfwGetKey(ventana, GLFW_KEY_UP) == GLFW_PRESS) {
            rotacionX += velocidadRotacion;
        }

        if (glfwGetKey(ventana, GLFW_KEY_DOWN) == GLFW_PRESS) {
            rotacionX -= velocidadRotacion;
        }

        if (glfwGetKey(ventana, GLFW_KEY_W) == GLFW_PRESS) {
            moverZ += velocidadMovimiento;
        }

        if (glfwGetKey(ventana, GLFW_KEY_S) == GLFW_PRESS) {
            moverZ -= velocidadMovimiento;
        }

        if (glfwGetKey(ventana, GLFW_KEY_A) == GLFW_PRESS) {
            moverX += velocidadMovimiento;
        }

        if (glfwGetKey(ventana, GLFW_KEY_D) == GLFW_PRESS) {
            moverX -= velocidadMovimiento;
        }

        if (glfwGetKey(ventana, GLFW_KEY_Q) == GLFW_PRESS) {
            moverY -= velocidadMovimiento;
        }

        if (glfwGetKey(ventana, GLFW_KEY_E) == GLFW_PRESS) {
            moverY += velocidadMovimiento;
        }

        if (glfwGetKey(ventana, GLFW_KEY_Z) == GLFW_PRESS) {
            distancia -= velocidadZoom;
        }

        if (glfwGetKey(ventana, GLFW_KEY_X) == GLFW_PRESS) {
            distancia += velocidadZoom;
        }

        if (distancia < 5f) {
            distancia = 5f;
        }

        if (distancia > 80f) {
            distancia = 80f;
        }

        if (rotacionX > 85f) {
            rotacionX = 85f;
        }

        if (rotacionX < -10f) {
            rotacionX = -10f;
        }
    }

    public void aplicar() {
        glTranslatef(moverX, moverY, -distancia + moverZ);
        glRotatef(rotacionX, 1f, 0f, 0f);
        glRotatef(rotacionY, 0f, 1f, 0f);
    }
}
