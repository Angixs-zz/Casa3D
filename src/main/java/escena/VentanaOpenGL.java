package escena;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import objetos.Pared;
import objetos.Punto2D;
import objetos.Cubo;
import camara.CamaraLibre;
import personaje.Girasol;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class VentanaOpenGL {

    private long ventana;

    private Casa casa;
    private CamaraLibre camaraLibre;
    private Girasol girasol;

    private int ancho = 1280;
    private int alto = 720;
    
    private int nivelVisible = 3;

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
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        ventana = glfwCreateWindow(
                ancho,
                alto,
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
        girasol = new Girasol(0f, 0f, 0f);

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

            if (glfwGetKey(ventana, GLFW_KEY_1) == GLFW_PRESS) nivelVisible = 1;
            if (glfwGetKey(ventana, GLFW_KEY_2) == GLFW_PRESS) nivelVisible = 2;
            if (glfwGetKey(ventana, GLFW_KEY_3) == GLFW_PRESS) nivelVisible = 3;

            camaraLibre.actualizar(ventana);

            configurarCamara();

            dibujarPiso();

            dibujarCasaGeoGebra();

            girasol.dibujar();

            glfwSwapBuffers(ventana);

            glfwPollEvents();
        }
    }

    private void configurarCamara() {

        glMatrixMode(GL_PROJECTION);

        glLoadIdentity();

        float aspecto = (float) ancho / alto;

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
            int nivelPared = 1;
            if (pared.getAlturaBase() > 3.0) nivelPared = 2;
            if (pared.getAlturaBase() > 6.0) nivelPared = 3;

            if (nivelPared <= nivelVisible) {
                Punto2D inicio = pared.getInicio();
                Punto2D fin = pared.getFin();

                dibujarPared(
                        (float) inicio.getX(),
                        (float) inicio.getY(),
                        (float) fin.getX(),
                        (float) fin.getY(),
                        (float) pared.getAltura(),
                        (float) pared.getGrosor(),
                        (float) pared.getAlturaBase());
            }
        }

        dibujarLosas();
    }

    private void dibujarLosas() {
        // Losa del segundo piso (techo del primero)
        if (nivelVisible >= 2) {
            Cubo.dibujar(0f, 3.2f, 0.25f, 8.0f, 0.2f, 20.5f, 0.7f, 0.7f, 0.7f);
        }

        // Losa del tercer piso (azotea)
        if (nivelVisible >= 3) {
            Cubo.dibujar(0f, 6.4f, 0.25f, 8.0f, 0.2f, 20.5f, 0.7f, 0.7f, 0.7f);
        }
    }

    private void dibujarPared(
            float x1,
            float z1,
            float x2,
            float z2,
            float altura,
            float grosor,
            float alturaBase) {
        // Centrar el plano de GeoGebra en la escena OpenGL
        x1 = x1 - 4.0f;
        x2 = x2 - 4.0f;

        z1 = z1 - 10.0f;
        z2 = z2 - 10.0f;

        // Voltear la casa en espejo horizontal
        x1 = -x1;
        x2 = -x2;

        float dx = x2 - x1;
        float dz = z2 - z1;

        float longitud = (float) Math.sqrt(dx * dx + dz * dz);

        float centroX = (x1 + x2) / 2.0f;
        float centroZ = (z1 + z2) / 2.0f;

        float angulo = (float) Math.toDegrees(Math.atan2(dz, dx));

        glPushMatrix();

        glTranslatef(centroX, alturaBase + altura / 2.0f, centroZ);
        glRotatef(-angulo, 0f, 1f, 0f);

        Cubo.dibujar(0f, 0f, 0f, longitud, altura, grosor, 1f, 1f, 1f);

        glPopMatrix();
    }

    private void cerrar() {

        glfwDestroyWindow(ventana);

        glfwTerminate();
    }
}