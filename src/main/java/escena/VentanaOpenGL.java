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
    private final float ESCALA_CASA = 2.0f;

    private int modoCamara = 1;

    private static final int CAMARA_LIBRE = 1;
    private static final int CAMARA_PRIMERA_PERSONA = 2;
    private static final int CAMARA_TERCERA_PERSONA = 3;

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
                "Casa 3D | 1-2-3 Pisos | F1 Libre | F2 Primera Persona | F3 Tercera Persona | WASD mover",
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
        girasol = new Girasol(0f, 0f, -6f, 0.7f);

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

            if (glfwGetKey(ventana, GLFW_KEY_1) == GLFW_PRESS)
                nivelVisible = 1;
            if (glfwGetKey(ventana, GLFW_KEY_2) == GLFW_PRESS)
                nivelVisible = 2;
            if (glfwGetKey(ventana, GLFW_KEY_3) == GLFW_PRESS)
                nivelVisible = 3;

            if (glfwGetKey(ventana, GLFW_KEY_F1) == GLFW_PRESS)
                modoCamara = CAMARA_LIBRE;
            if (glfwGetKey(ventana, GLFW_KEY_F2) == GLFW_PRESS)
                modoCamara = CAMARA_PRIMERA_PERSONA;
            if (glfwGetKey(ventana, GLFW_KEY_F3) == GLFW_PRESS)
                modoCamara = CAMARA_TERCERA_PERSONA;

            if (modoCamara == CAMARA_LIBRE) {
                camaraLibre.actualizar(ventana);
            } else {
                girasol.actualizar(ventana);
            }

            configurarCamara();

            dibujarPiso();

            dibujarCasaGeoGebra();

            if (modoCamara != CAMARA_PRIMERA_PERSONA) {
                girasol.dibujar();
            }

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
                150);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        if (modoCamara == CAMARA_LIBRE) {
            camaraLibre.aplicar();
        }

        if (modoCamara == CAMARA_PRIMERA_PERSONA) {
            aplicarCamaraPrimeraPersona();
        }

        if (modoCamara == CAMARA_TERCERA_PERSONA) {
            aplicarCamaraTerceraPersona();
        }
    }

    private void aplicarCamaraPrimeraPersona() {
        glRotatef(-girasol.getRotacionY(), 0f, 1f, 0f);

        glTranslatef(
                -girasol.getX(),
                -girasol.getAlturaOjos(),
                -girasol.getZ()
        );
    }

    private void aplicarCamaraTerceraPersona() {
        glTranslatef(0f, -2.2f, -8.0f);

        glRotatef(18f, 1f, 0f, 0f);

        glRotatef(-girasol.getRotacionY(), 0f, 1f, 0f);

        glTranslatef(
                -girasol.getX(),
                -(girasol.getY() + 1.0f),
                -girasol.getZ()
        );
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
        dibujarPisoPrimeraPlanta();

        for (Pared pared : casa.getParedes()) {
            int nivelPared = 1;
            if (pared.getAlturaBase() > 3.0)
                nivelPared = 2;
            if (pared.getAlturaBase() > 6.0)
                nivelPared = 3;

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

    private void dibujarPisoPrimeraPlanta() {
        float alturaPiso = 0.03f;

        // Zona inferior
        dibujarPisoPorCoordenadas(0.1f, 0.2f, 7.9f, 5.4f, alturaPiso);

        // Zona media baja
        dibujarPisoPorCoordenadas(0.1f, 5.4f, 7.9f, 9.5f, alturaPiso);

        // Zona media
        dibujarPisoPorCoordenadas(0.1f, 9.5f, 7.9f, 14.0f, alturaPiso);

        // Zona superior izquierda
        dibujarPisoPorCoordenadas(0.1f, 14.0f, 4.9f, 18.6f, alturaPiso);

        // Zona superior derecha
        dibujarPisoPorCoordenadas(4.9f, 14.0f, 7.9f, 20.3f, alturaPiso);

        // Zona superior final izquierda
        dibujarPisoPorCoordenadas(0.1f, 18.6f, 7.9f, 20.3f, alturaPiso);

        // Dibujar líneas tipo duela para mayor realismo
        // dibujarLineasPisoPrimeraPlanta();
    }

    private void dibujarPisoPorCoordenadas(
            float xMin,
            float zMin,
            float xMax,
            float zMax,
            float altura) {
        float centroX = (xMin + xMax) / 2.0f;
        float centroZ = (zMin + zMax) / 2.0f;

        float anchoPiso = xMax - xMin;
        float largoPiso = zMax - zMin;

        // Convertir coordenadas de GeoGebra a OpenGL
        centroX = centroX - 4.0f;
        centroZ = centroZ - 10.0f;

        // Voltear igual que las paredes
        centroX = -centroX;

        centroX = centroX * ESCALA_CASA;
        centroZ = centroZ * ESCALA_CASA;
        anchoPiso = anchoPiso * ESCALA_CASA;
        largoPiso = largoPiso * ESCALA_CASA;

        Cubo.dibujar(
                centroX,
                altura,
                centroZ,
                anchoPiso,
                0.06f,
                largoPiso,
                0.55f,
                0.38f,
                0.22f);
    }

    private void dibujarLineasPisoPrimeraPlanta() {
        glColor3f(0.25f, 0.16f, 0.08f);
        glBegin(GL_LINES);

        float zInicio = 0.2f;
        float zFin = 20.3f;

        for (float x = 0.5f; x <= 7.5f; x += 0.5f) {
            float xConvertido = x - 4.0f;
            xConvertido = -xConvertido;

            float z1 = zInicio - 10.0f;
            float z2 = zFin - 10.0f;

            glVertex3f(xConvertido, 0.08f, z1);
            glVertex3f(xConvertido, 0.08f, z2);
        }

        for (float z = 0.7f; z <= 19.8f; z += 0.5f) {
            float zConvertido = z - 10.0f;

            float x1 = 0.1f - 4.0f;
            float x2 = 7.9f - 4.0f;

            x1 = -x1;
            x2 = -x2;

            glVertex3f(x1, 0.081f, zConvertido);
            glVertex3f(x2, 0.081f, zConvertido);
        }

        glEnd();
    }

    private void dibujarLosas() {
        // Losa del segundo piso, usando varias piezas para respetar la forma irregular
        if (nivelVisible >= 2) {
            // Zona inferior
            dibujarLosaPorCoordenadas(0.1f, 0.2f, 7.9f, 5.4f, 3.2f);

            // Zona media baja
            dibujarLosaPorCoordenadas(0.1f, 5.4f, 7.9f, 9.5f, 3.2f);

            // Zona media
            dibujarLosaPorCoordenadas(0.1f, 9.5f, 7.9f, 14.0f, 3.2f);

            // Zona superior izquierda
            dibujarLosaPorCoordenadas(0.1f, 14.0f, 4.9f, 18.6f, 3.2f);

            // Zona superior derecha
            dibujarLosaPorCoordenadas(4.9f, 14.0f, 7.9f, 20.3f, 3.2f);

            // Zona superior final izquierda
            dibujarLosaPorCoordenadas(0.1f, 18.6f, 7.9f, 20.3f, 3.2f);
        }

        // Losa del tercer piso
        if (nivelVisible >= 3) {
            // Zona inferior izquierda
            dibujarLosaPorCoordenadas(0.1f, 1.5f, 6.6f, 5.0f, 6.4f);

            // Zona central izquierda
            dibujarLosaPorCoordenadas(0.1f, 7.0f, 3.8f, 10.3f, 6.4f);

            // Zona central derecha
            dibujarLosaPorCoordenadas(4.9f, 9.5f, 7.9f, 13.9f, 6.4f);

            // Zona izquierda superior
            dibujarLosaPorCoordenadas(0.1f, 14.0f, 3.8f, 18.6f, 6.4f);

            // Zona superior derecha
            dibujarLosaPorCoordenadas(3.8f, 13.9f, 7.9f, 20.3f, 6.4f);
        }
    }

    private void dibujarLosaPorCoordenadas(
            float xMin,
            float zMin,
            float xMax,
            float zMax,
            float altura) {
        float centroX = (xMin + xMax) / 2.0f;
        float centroZ = (zMin + zMax) / 2.0f;

        float anchoLosa = xMax - xMin;
        float largoLosa = zMax - zMin;

        // Convertir coordenadas de GeoGebra a coordenadas OpenGL
        centroX = centroX - 4.0f;
        centroZ = centroZ - 10.0f;

        // Voltear igual que las paredes
        centroX = -centroX;

        centroX = centroX * ESCALA_CASA;
        centroZ = centroZ * ESCALA_CASA;
        anchoLosa = anchoLosa * ESCALA_CASA;
        largoLosa = largoLosa * ESCALA_CASA;

        Cubo.dibujar(
                centroX,
                altura,
                centroZ,
                anchoLosa,
                0.15f,
                largoLosa,
                0.65f,
                0.65f,
                0.65f);
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

        // Escalar casa para que el personaje y muebles quepan mejor
        x1 = x1 * ESCALA_CASA;
        x2 = x2 * ESCALA_CASA;
        z1 = z1 * ESCALA_CASA;
        z2 = z2 * ESCALA_CASA;

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