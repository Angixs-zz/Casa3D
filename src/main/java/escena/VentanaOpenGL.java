package escena;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import objetos.Pared;
import objetos.Punto2D;
import objetos.Cubo;
import camara.CamaraLibre;
import personaje.Girasol;
import utilidades.Constantes;
import objetos.Escalera3D;

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
        girasol = new Girasol(-2.7f, 0.0f, -22.0f, 0.5f, 180f);

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
                girasol.actualizar(ventana, casa);
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
                -aspecto * 0.1,
                aspecto * 0.1,
                -0.1,
                0.1,
                0.1,
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
        float rotacion = girasol.getRotacionY();
        float radianes = (float) Math.toRadians(rotacion);

        float direccionX = (float) Math.sin(radianes);
        float direccionZ = (float) Math.cos(radianes);

        // Pequeño retroceso de la cámara para que los ojos no entren visualmente en la
        // pared
        float distanciaOjosAtras = 0.35f;

        float camaraX = girasol.getX() + direccionX * distanciaOjosAtras;
        float camaraY = girasol.getAlturaOjos();
        float camaraZ = girasol.getZ() + direccionZ * distanciaOjosAtras;

        glRotatef(-rotacion, 0f, 1f, 0f);

        glTranslatef(
                -camaraX,
                -camaraY,
                -camaraZ);
    }

    private void aplicarCamaraTerceraPersona() {
        glTranslatef(0f, -2.2f, -8.0f);

        glRotatef(18f, 1f, 0f, 0f);

        glRotatef(-girasol.getRotacionY(), 0f, 1f, 0f);

        glTranslatef(
                -girasol.getX(),
                -(girasol.getY() + 1.0f),
                -girasol.getZ());
    }

    private void dibujarPiso() {

        glColor3f(0.1f, 0.5f, 0.1f);

        glBegin(GL_QUADS);

        glVertex3f(-80f, 0f, -80f);
        glVertex3f(80f, 0f, -80f);
        glVertex3f(80f, 0f, 80f);
        glVertex3f(-80f, 0f, 80f);

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
                // Hacer las paredes de la escalera invisibles en el renderizado para no obstruir
                String nombrePared = pared.getNombre();
                if (nombrePared.equals("d") || nombrePared.equals("P2_d1") || nombrePared.equals("P3_c1")) {
                    continue;
                }

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
        dibujarEscaleras();
    }

    private void dibujarEscaleras() {
        /*
         * Escalera 1:
         * Primer piso -> segundo piso.
         * Visible desde el nivel 1.
         */
        if (nivelVisible >= 1) {
            Escalera3D.dibujar(
                    convertirXGeoAOpenGL(6.4f),
                    0.05f,
                    convertirZGeoAOpenGL(10.75f),
                    3.0f,
                    2.5f,
                    3.2f,
                    12,
                    0f);
        }

        /*
         * Escalera 2:
         * Segundo piso -> tercer piso.
         * Visible desde el nivel 2.
         */
        if (nivelVisible >= 2) {
            Escalera3D.dibujar(
                    convertirXGeoAOpenGL(6.4f),
                    3.25f,
                    convertirZGeoAOpenGL(10.75f),
                    3.0f,
                    2.5f,
                    3.2f,
                    12,
                    0f);
        }
    }

    private float convertirXGeoAOpenGL(float xGeo) {
        float x = xGeo - Constantes.CENTRO_GEOGEBRA_X;
        x = -x;
        return x * Constantes.ESCALA_CASA;
    }

    private float convertirZGeoAOpenGL(float zGeo) {
        float z = zGeo - Constantes.CENTRO_GEOGEBRA_Z;
        return z * Constantes.ESCALA_CASA;
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

        centroX = centroX * Constantes.ESCALA_CASA;
        centroZ = centroZ * Constantes.ESCALA_CASA;
        anchoPiso = anchoPiso * Constantes.ESCALA_CASA;
        largoPiso = largoPiso * Constantes.ESCALA_CASA;

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
        /*
         * Piso / losa del segundo nivel.
         * Está dividido en piezas más pequeñas para que no se salga tanto
         * del contorno real de las paredes del segundo piso.
         */
        if (nivelVisible >= 2) {
            dibujarLosaSegundoPiso();
        }

        /*
         * Piso / losa del tercer nivel.
         * También se divide por zonas para respetar mejor el contorno.
         */
        if (nivelVisible >= 3) {
            dibujarLosaTercerPiso();
        }
    }

    private void dibujarLosaSegundoPiso() {
        float altura = 3.2f;

        /*
         * Piso del segundo nivel.
         * Se divide en varias piezas para rellenar la casa sin salirse demasiado.
         * Dejamos libre la zona de la escalera para que sí se pueda subir.
         */

        // Parte baja izquierda
        dibujarLosaPorCoordenadas(0.1f, 0.2f, 4.8f, 1.6f, altura);

        // Parte baja derecha
        dibujarLosaPorCoordenadas(5.9f, 0.2f, 7.9f, 1.6f, altura);

        // Zona derecha inferior
        dibujarLosaPorCoordenadas(4.8f, 1.6f, 7.9f, 4.3f, altura);

        // Zona media derecha
        dibujarLosaPorCoordenadas(4.9f, 4.3f, 7.9f, 5.9f, altura);

        // Zona media izquierda grande
        dibujarLosaPorCoordenadas(0.1f, 4.4f, 3.9f, 7.9f, altura);

        // Conector central
        dibujarLosaPorCoordenadas(3.5f, 5.3f, 4.9f, 9.5f, altura);

        // Zona izquierda media-superior
        dibujarLosaPorCoordenadas(0.1f, 7.9f, 4.9f, 9.5f, altura);

        /*
         * Zona de escalera.
         * No cubrimos toda el área 4.9 - 7.9 / 9.5 - 12.0,
         * porque ahí está la llegada de la escalera.
         */

        // Franja izquierda de la zona de escalera
        dibujarLosaPorCoordenadas(4.9f, 9.5f, 5.8f, 12.0f, altura);

        // Franja derecha de la zona de escalera
        dibujarLosaPorCoordenadas(7.2f, 9.5f, 7.9f, 12.0f, altura);

        // Zona arriba de la escalera
        dibujarLosaPorCoordenadas(4.9f, 12.0f, 7.9f, 13.9f, altura);

        // Bloque superior izquierdo
        dibujarLosaPorCoordenadas(0.1f, 13.9f, 3.9f, 15.4f, altura);

        // Bloque superior izquierdo medio
        dibujarLosaPorCoordenadas(0.1f, 15.4f, 3.9f, 18.6f, altura);

        // Bloque superior central
        dibujarLosaPorCoordenadas(3.9f, 13.9f, 7.9f, 17.3f, altura);

        // Bloque superior derecho final
        dibujarLosaPorCoordenadas(6.0f, 17.3f, 7.9f, 20.3f, altura);

        // Bloque superior central final
        dibujarLosaPorCoordenadas(3.9f, 17.3f, 6.0f, 20.3f, altura);

        // Bloque superior izquierdo final
        dibujarLosaPorCoordenadas(0.1f, 18.6f, 3.9f, 20.3f, altura);
    }

    private void dibujarLosaTercerPiso() {
        float altura = 6.4f;

        /*
         * Piso del tercer nivel.
         * Se rellena por zonas, pero se deja libre el hueco donde llega
         * la escalera del segundo al tercer piso.
         */

        // Zona inferior izquierda
        dibujarLosaPorCoordenadas(0.1f, 1.5f, 6.6f, 2.6f, altura);

        // Zona inferior media
        dibujarLosaPorCoordenadas(0.1f, 2.6f, 6.6f, 5.0f, altura);

        // Zona izquierda central baja
        dibujarLosaPorCoordenadas(0.1f, 7.0f, 3.8f, 9.5f, altura);

        // Zona izquierda central
        dibujarLosaPorCoordenadas(0.1f, 9.5f, 3.8f, 14.0f, altura);

        // Zona izquierda alta
        dibujarLosaPorCoordenadas(0.1f, 14.0f, 3.8f, 18.6f, altura);

        /*
         * Zona de llegada de escalera al tercer piso.
         * No ponemos piso completo entre 4.9 y 7.9, 9.5 y 12.0,
         * porque ahí debe quedar el hueco de acceso.
         */

        // Franja izquierda junto a la escalera
        dibujarLosaPorCoordenadas(4.9f, 9.5f, 5.8f, 12.0f, altura);

        // Franja derecha junto a la escalera
        dibujarLosaPorCoordenadas(7.2f, 9.5f, 7.9f, 12.0f, altura);

        // Piso después de la llegada de la escalera
        dibujarLosaPorCoordenadas(4.9f, 12.0f, 7.9f, 13.9f, altura);

        // Zona superior derecha
        dibujarLosaPorCoordenadas(3.8f, 13.9f, 7.9f, 17.4f, altura);

        // Zona superior derecha final
        dibujarLosaPorCoordenadas(5.9f, 17.4f, 7.9f, 20.3f, altura);

        // Zona superior central final
        dibujarLosaPorCoordenadas(3.8f, 18.6f, 5.9f, 20.3f, altura);
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

        centroX = centroX * Constantes.ESCALA_CASA;
        centroZ = centroZ * Constantes.ESCALA_CASA;
        anchoLosa = anchoLosa * Constantes.ESCALA_CASA;
        largoLosa = largoLosa * Constantes.ESCALA_CASA;

        float r = 0.58f;
        float g = 0.48f;
        float b = 0.35f;

        Cubo.dibujar(
                centroX,
                altura,
                centroZ,
                anchoLosa,
                0.12f,
                largoLosa,
                r,
                g,
                b);
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
        x1 = x1 * Constantes.ESCALA_CASA;
        x2 = x2 * Constantes.ESCALA_CASA;
        z1 = z1 * Constantes.ESCALA_CASA;
        z2 = z2 * Constantes.ESCALA_CASA;

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