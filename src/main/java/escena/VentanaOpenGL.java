package escena;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import objetos.Pared;
import objetos.Punto2D;
import objetos.Cubo;
import camara.CamaraLibre;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class VentanaOpenGL {

    private long ventana;

    private Casa casa;
    private CamaraLibre camaraLibre;

    private final int ANCHO = 1280;
    private final int ALTO = 720;

    public void ejecutar() {
        iniciar();
        bucle();
        cerrar();
    }

    private void iniciar() {

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("No se pudo iniciar GLFW");
        }

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);

        ventana = glfwCreateWindow(
                ANCHO,
                ALTO,
                "Casa 3D OpenGL",
                NULL,
                NULL);

        if (ventana == NULL) {
            throw new RuntimeException("No se pudo crear la ventana");
        }

        glfwMakeContextCurrent(ventana);

        glfwSwapInterval(1);

        glfwShowWindow(ventana);

        GL.createCapabilities();

        casa = new Casa();
        camaraLibre = new CamaraLibre();

        glEnable(GL_DEPTH_TEST);

        glClearColor(
                0.53f,
                0.81f,
                0.98f,
                1.0f);
    }

    private void bucle() {

        while (!glfwWindowShouldClose(ventana)) {

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            camaraLibre.actualizar(ventana);

            configurarCamara();

            dibujarPiso();

            dibujarCasaGeoGebra();

            glfwSwapBuffers(ventana);

            glfwPollEvents();
        }
    }

    private void configurarCamara() {

        glMatrixMode(GL_PROJECTION);

        glLoadIdentity();

        float aspecto = (float) ANCHO / ALTO;

        glFrustum(
                -aspecto,
                aspecto,
                -1,
                1,
                1.5,
                100);

        glMatrixMode(GL_MODELVIEW);

        glLoadIdentity();

        camaraLibre.aplicar();
    }

    private void dibujarPiso() {

        glColor3f(0.1f, 0.5f, 0.1f);

        glBegin(GL_QUADS);

        glVertex3f(-20f, 0f, -20f);
        glVertex3f(20f, 0f, -20f);
        glVertex3f(20f, 0f, 20f);
        glVertex3f(-20f, 0f, 20f);

        glEnd();
    }



    private void dibujarCasaGeoGebra() {
        for (Pared pared : casa.getParedes()) {
            Punto2D inicio = pared.getInicio();
            Punto2D fin = pared.getFin();

            dibujarPared(
                    (float) inicio.getX(),
                    (float) inicio.getY(),
                    (float) fin.getX(),
                    (float) fin.getY(),
                    (float) pared.getAltura(),
                    (float) pared.getGrosor());
        }
    }

    private void dibujarPared(
            float x1,
            float z1,
            float x2,
            float z2,
            float altura,
            float grosor) {
        // Centrar el plano de GeoGebra en la escena OpenGL
        x1 = x1 - 4.0f;
        x2 = x2 - 4.0f;

        z1 = z1 - 10.0f;
        z2 = z2 - 10.0f;

        // Si la casa sale en espejo, activa estas líneas:
        // x1 = -x1;
        // x2 = -x2;

        float dx = x2 - x1;
        float dz = z2 - z1;

        float longitud = (float) Math.sqrt(dx * dx + dz * dz);

        float centroX = (x1 + x2) / 2.0f;
        float centroZ = (z1 + z2) / 2.0f;

        float angulo = (float) Math.toDegrees(Math.atan2(dz, dx));

        glPushMatrix();

        glTranslatef(centroX, altura / 2.0f, centroZ);
        glRotatef(-angulo, 0f, 1f, 0f);

        Cubo.dibujar(0f, 0f, 0f, longitud, altura, grosor, 1f, 1f, 1f);

        glPopMatrix();
    }

    private void dibujarCubo(
            float x,
            float y,
            float z,
            float ancho,
            float alto,
            float profundo) {

        float x1 = x - ancho / 2;
        float x2 = x + ancho / 2;

        float y1 = y - alto / 2;
        float y2 = y + alto / 2;

        float z1 = z - profundo / 2;
        float z2 = z + profundo / 2;

        glColor3f(1f, 1f, 1f);

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

    private void cerrar() {

        glfwDestroyWindow(ventana);

        glfwTerminate();
    }
}