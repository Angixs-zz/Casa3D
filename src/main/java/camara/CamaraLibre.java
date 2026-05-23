package camara;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class CamaraLibre {

    private float distancia = 45f;
    private float rotacionY = 35f;
    private float rotacionX = 25f;

    private float moverX = 0f;
    private float moverY = -2f;
    private float moverZ = 0f;

    private double mouseAnteriorX = 0;
    private double mouseAnteriorY = 0;
    private boolean primerMovimientoMouse = true;

    public void actualizar(long ventana) {
        actualizarTeclado(ventana);
        actualizarMouse(ventana);
    }

    private void actualizarTeclado(long ventana) {
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

        limitarValores();
    }

    private void actualizarMouse(long ventana) {
        double[] posicionX = new double[1];
        double[] posicionY = new double[1];

        glfwGetCursorPos(ventana, posicionX, posicionY);

        double mouseX = posicionX[0];
        double mouseY = posicionY[0];

        if (primerMovimientoMouse) {
            mouseAnteriorX = mouseX;
            mouseAnteriorY = mouseY;
            primerMovimientoMouse = false;
        }

        double diferenciaX = mouseX - mouseAnteriorX;
        double diferenciaY = mouseY - mouseAnteriorY;

        mouseAnteriorX = mouseX;
        mouseAnteriorY = mouseY;

        float sensibilidad = 0.15f;

        // Mantener clic izquierdo para rotar con mouse/mousepad
        if (glfwGetMouseButton(ventana, GLFW_MOUSE_BUTTON_LEFT) == GLFW_PRESS) {
            rotacionY += diferenciaX * sensibilidad;
            rotacionX += diferenciaY * sensibilidad;
        }

        // Mantener clic derecho para mover/panear la cámara
        if (glfwGetMouseButton(ventana, GLFW_MOUSE_BUTTON_RIGHT) == GLFW_PRESS) {
            moverX += diferenciaX * 0.01f;
            moverY -= diferenciaY * 0.01f;
        }

        limitarValores();
    }

    private void limitarValores() {
        if (distancia < 5f) {
            distancia = 5f;
        }

        if (distancia > 140f) {
            distancia = 140f;
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
