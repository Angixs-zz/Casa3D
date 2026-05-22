package escena;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class VentanaOpenGL {

    private long ventana;

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
                NULL
        );

        if (ventana == NULL) {
            throw new RuntimeException("No se pudo crear la ventana");
        }

        glfwMakeContextCurrent(ventana);

        glfwSwapInterval(1);

        glfwShowWindow(ventana);

        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);

        glClearColor(
                0.53f,
                0.81f,
                0.98f,
                1.0f
        );
    }

    private void bucle() {

        while (!glfwWindowShouldClose(ventana)) {

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

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
                100
        );

        glMatrixMode(GL_MODELVIEW);

        glLoadIdentity();

        glTranslatef(0f, -2f, -25f);

        glRotatef(25f, 1f, 0f, 0f);

        glRotatef(35f, 0f, 1f, 0f);
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

    private void dibujarCasaTemporal() {

        // Frente
        dibujarCubo(
                0f,
                1.5f,
                -5f,
                8f,
                3f,
                0.2f
        );

        // Atrás
        dibujarCubo(
                0f,
                1.5f,
                5f,
                8f,
                3f,
                0.2f
        );

        // Izquierda
        dibujarCubo(
                -4f,
                1.5f,
                0f,
                0.2f,
                3f,
                10f
        );

        // Derecha
        dibujarCubo(
                4f,
                1.5f,
                0f,
                0.2f,
                3f,
                10f
        );
    }

    private void dibujarCasaGeoGebra() {
    float altura = 3.0f;
    float grosor = 0.15f;

    // Paredes primera planta
    dibujarPared(0.1f, 0.2f, 4.8f, 0.2f, altura, grosor);   // E-D
    dibujarPared(4.8f, 0.2f, 4.8f, 1.6f, altura, grosor);   // D-C
    dibujarPared(5.9f, 1.6f, 5.9f, 0.2f, altura, grosor);   // G-F
    dibujarPared(5.9f, 0.2f, 7.9f, 0.2f, altura, grosor);   // F-H
    dibujarPared(7.9f, 0.2f, 7.9f, 5.4f, altura, grosor);   // H-I
    dibujarPared(7.9f, 5.4f, 5.8f, 5.4f, altura, grosor);   // I-S

    dibujarPared(0.1f, 5.4f, 1.3f, 5.4f, altura, grosor);   // N-P
    dibujarPared(2.7f, 5.4f, 3.8f, 5.4f, altura, grosor);   // Q-O
    dibujarPared(4.8f, 5.4f, 4.5f, 6.5f, altura, grosor);   // R-T
    dibujarPared(4.5f, 6.5f, 3.9f, 6.5f, altura, grosor);   // T-U
    dibujarPared(3.9f, 6.5f, 3.9f, 8.4f, altura, grosor);   // U-V
    dibujarPared(0.1f, 5.4f, 0.1f, 0.2f, altura, grosor);   // N-E
    dibujarPared(0.1f, 5.4f, 0.1f, 8.4f, altura, grosor);   // N-W

    dibujarPared(7.9f, 9.5f, 7.9f, 5.4f, altura, grosor);   // J-I
    dibujarPared(4.9f, 9.5f, 7.9f, 9.5f, altura, grosor);   // Z-J
    dibujarPared(7.9f, 9.5f, 7.9f, 10.7f, altura, grosor);  // J-K
    dibujarPared(7.9f, 10.7f, 7.9f, 12.0f, altura, grosor); // K-N1
    dibujarPared(7.9f, 12.0f, 4.9f, 12.0f, altura, grosor); // N1-B1
    dibujarPared(4.9f, 10.7f, 6.8f, 10.7f, altura, grosor); // A1-D2

    dibujarPared(3.9f, 12.0f, 3.9f, 8.4f, altura, grosor);  // C1-V
    dibujarPared(3.9f, 8.4f, 0.1f, 8.4f, altura, grosor);   // V-W
    dibujarPared(0.1f, 14.0f, 0.1f, 8.4f, altura, grosor);  // D1-W

    dibujarPared(4.9f, 14.0f, 5.7f, 14.0f, altura, grosor); // J1-K1
    dibujarPared(4.9f, 14.0f, 4.9f, 14.5f, altura, grosor); // J1-I1

    dibujarPared(4.9f, 15.4f, 4.9f, 18.6f, altura, grosor); // O1-P1
    dibujarPared(4.9f, 18.6f, 5.7f, 18.6f, altura, grosor); // P1-W1
    dibujarPared(5.7f, 18.6f, 7.2f, 18.6f, altura, grosor); // W1-Z1
    dibujarPared(7.2f, 18.6f, 7.9f, 18.6f, altura, grosor); // Z1-Q1
    dibujarPared(7.9f, 18.6f, 7.9f, 14.0f, altura, grosor); // Q1-M1
    dibujarPared(7.9f, 14.0f, 7.3f, 14.0f, altura, grosor); // M1-L1
    dibujarPared(7.3f, 14.0f, 5.7f, 14.0f, altura, grosor); // L1-K1

    dibujarPared(3.9f, 14.0f, 3.9f, 14.5f, altura, grosor); // E1-H1
    dibujarPared(2.5f, 14.5f, 2.5f, 14.0f, altura, grosor); // G1-F1
    dibujarPared(2.5f, 14.0f, 3.9f, 14.0f, altura, grosor); // F1-E1
    dibujarPared(2.5f, 14.0f, 0.1f, 14.0f, altura, grosor); // F1-D1
    dibujarPared(0.1f, 14.0f, 0.1f, 15.4f, altura, grosor); // D1-T1
    dibujarPared(0.1f, 15.4f, 3.9f, 15.4f, altura, grosor); // T1-S1
    dibujarPared(3.9f, 15.4f, 3.9f, 16.1f, altura, grosor); // S1-R1

    dibujarPared(0.1f, 18.6f, 0.1f, 15.4f, altura, grosor); // U1-T1
    dibujarPared(0.1f, 18.6f, 1.6f, 18.6f, altura, grosor); // U1-A2
    dibujarPared(1.6f, 18.6f, 3.1f, 18.6f, altura, grosor); // A2-B2
    dibujarPared(3.1f, 18.6f, 3.9f, 18.6f, altura, grosor); // B2-V1
    dibujarPared(3.9f, 18.6f, 3.9f, 17.0f, altura, grosor); // V1-C2
    dibujarPared(0.1f, 18.6f, 0.1f, 20.3f, altura, grosor); // U1-M

    dibujarPared(0.1f, 20.3f, 7.9f, 20.3f, altura, grosor); // M-L
    dibujarPared(7.9f, 20.3f, 7.9f, 18.6f, altura, grosor); // L-Q1
    dibujarPared(7.9f, 14.0f, 7.9f, 12.0f, altura, grosor); // M1-N1
}

private void dibujarPared(
        float x1,
        float z1,
        float x2,
        float z2,
        float altura,
        float grosor
) {
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

    dibujarCubo(0f, 0f, 0f, longitud, altura, grosor);

    glPopMatrix();
}

    private void dibujarCubo(
            float x,
            float y,
            float z,
            float ancho,
            float alto,
            float profundo
    ) {

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