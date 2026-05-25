package escena;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import objetos.Pared;
import objetos.Punto2D;
import objetos.Cubo;
import objetos.Puerta;
import camara.CamaraLibre;
import personaje.Girasol;
import utilidades.Constantes;
import objetos.Escalera3D;
import objetos.Oficina;
import objetos.SalaEstar;
import objetos.BanoMedio;
import objetos.BanoPrincipal;
import objetos.Cocina;
import objetos.Gimnasio;
import objetos.Recamara1;
import objetos.AreaLimpieza;
import objetos.Auto;
import objetos.PlantasPrimerPiso;
import objetos.Auto;
import piso2.Recamara2p2;
import piso2.BanoPrincipalp2;
import piso2.LavanderiaP2;
import piso2.Recamara3P2;
import piso2.LavanderiaP2;
import piso2.SalaEstarP2;
import piso2.PozoLuzP2;
import piso2.Recamara4P2;

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
        private boolean ePressed = false;

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

                // ==========================================================
                // CONSTANTES GENERALES PARA LAS PUERTAS (Compartidas)
                // ==========================================================
                float ancho = 1.1f * Constantes.ESCALA_CASA; // Ancho estándar de 1.1 unidades GeoGebra
                float alto = 2.7f; // Altura estándar de 3.2f

                // ==========================================================
                // 1. PUERTA PRINCIPAL (Muro Horizontal)
                // =================================w=========================
                // Agregar la puerta principal en base a C(4.8, 1.6) y G(5.9, 1.6)
                float pX = convertirXGeoAOpenGL(4.8f); // Bisagra en C
                float pZ = convertirZGeoAOpenGL(1.6f); // C está en Z=1.6

                // true = eje X. rotacionBase = 180.0f para extenderse hacia G. anguloApertura =
                // 90.0f interior.
                casa.agregarPuerta(new Puerta("Puerta Principal", pX, 0.0f, pZ, ancho, alto, true, 90.0f, 180.0f));

                // ==========================================================
                // 1. PUERTA PRINCIPAL (Muro Horizontal)
                // ==========================================================
                /*
                 * // Agregar la puerta principal en base a C(4.8, 1.6) y G(5.9, 1.6)
                 * float pserviciox = convertirXGeoAOpenGL(4.9f); // Bisagra en C
                 * float pservicioy = convertirZGeoAOpenGL(18.6f); // C está en Z=1.6
                 * 
                 * // true = eje X. rotacionBase = 180.0f para extenderse hacia G.
                 * anguloApertura =
                 * // 90.0f interior.
                 * casa.agregarPuerta(
                 * new Puerta("Puerta Servicio", pserviciox, 0.0f, pservicioy, ancho, alto,
                 * true, -90.0f,
                 * 180.0f));
                 */

                // ==========================================================
                // 2. PUERTA CUARTO S / RECAMARA (Muro Horizontal)
                // ==========================================================
                // Hueco entre R(4.8, 5.4) y S(5.8, 5.4). Bisagra en R (lado izquierdo)
                float pXr = convertirXGeoAOpenGL(4.8f);
                float pZr = convertirZGeoAOpenGL(5.4f);

                // true = eje X. rotacionBase = 180.0f apunta hacia S. anguloApertura = 90.0f
                // abre al interior.
                casa.agregarPuerta(new Puerta("Puerta Cuarto S", pXr, 0.0f, pZr, ancho, alto, true, 90.0f, 180.0f));

                // ==========================================================
                // 3. PUERTA DESPACHO / ESTANCIA (Bisagra en O, abre a la izquierda)
                // ==========================================================
                // Coordenadas exactas del Punto O (abajo en el plano)
                float puertaDespachoX = convertirXGeoAOpenGL(3.8f);
                float puertaDespachoZ = convertirZGeoAOpenGL(5.4f);

                // false = eje Z (muro vertical entre U y O).
                // rotacionBase = 0.0f hace que la puerta cerrada suba desde O hacia U.
                // anguloApertura = -90.0f hace que la puerta gire hacia la izquierda (interior
                // del despacho).
                casa.agregarPuerta(
                                new Puerta("Puerta Despacho", puertaDespachoX, 0.0f, puertaDespachoZ, ancho, alto,
                                                false, 80f, 0.0f));

                // ==========================================================
                // 4. PUERTA Baño
                // ==========================================================
                // Coordenadas exactas del Punto O (abajo en el plano)
                float puertabanox = convertirXGeoAOpenGL(4.9f);
                float puertabanoy = convertirZGeoAOpenGL(9.6f);

                // false = eje Z (muro vertical entre U y O).
                // rotacionBase = 0.0f hace que la puerta cerrada suba desde O hacia U.
                // anguloApertura = -90.0f hace que la puerta gire hacia la izquierda (interior
                // del despacho).
                casa.agregarPuerta(new Puerta("Puerta Baño", puertabanox, 0.0f, puertabanoy, ancho, alto, false, -80.0f,
                                0.0f));

                // ==========================================================
                // 4. PUERTA Gimnasio
                // ==========================================================
                float puertagymx = convertirXGeoAOpenGL(4.9f);
                float puertagymy = convertirZGeoAOpenGL(14.5f);
                casa.agregarPuerta(
                                new Puerta("Puerta Gimnasio", puertagymx, 0.0f, puertagymy, ancho, alto, false, -80f,
                                                0.0f));

                // ==========================================================
                // PUERTA GYM TRASERA (P1 a W1)
                // ==========================================================
                float puertaGymTraseraX = convertirXGeoAOpenGL(4.9f);
                float puertaGymTraseraZ = convertirZGeoAOpenGL(18.6f);

                casa.agregarPuerta(
                                new Puerta("Puerta GYM Trasera", puertaGymTraseraX, 0.0f, puertaGymTraseraZ,
                                                ancho, alto, true, -90f,
                                                0.0f));

                // ==========================================================
                // 5. PUERTA Baño2
                // ==========================================================
                float puertabaño2x = convertirXGeoAOpenGL(2.5f);
                float puertabaño2y = convertirZGeoAOpenGL(15.4f);
                casa.agregarPuerta(
                                new Puerta("Puerta Baño 2", puertabaño2x, 0.0f, puertabaño2y, ancho, alto, false, -80f,
                                                180.0f));

                // ==========================================================
                // 6. PUERTA Recamara1
                // ==========================================================
                float puertaReca1x = convertirXGeoAOpenGL(3.9f);
                float puertaReca1y = convertirZGeoAOpenGL(16.1f);
                casa.agregarPuerta(
                                new Puerta("Puerta Recamara 1", puertaReca1x, 0.0f, puertaReca1y, ancho, alto, false,
                                                80f, 0.0f));

                // ==========================================================
                // 7. PUERTA Pozo
                // ==========================================================
                float puertaPozox = convertirXGeoAOpenGL(3.9f);
                float puertaPozoy = convertirZGeoAOpenGL(9.5f);
                casa.agregarPuerta(
                                new Puerta("Puerta Pozo", puertaPozox, 3.2f, puertaPozoy, ancho, alto, false, -80f,
                                                180.0f));

                // ==========================================================
                // 7. Recamara 4
                // ==========================================================
                float puertaRecamara4x = convertirXGeoAOpenGL(3.9f);
                float puertaRecamara4y = convertirZGeoAOpenGL(7.9f);
                casa.agregarPuerta(
                                new Puerta("Puerta Pozo", puertaRecamara4x, 3.2f, puertaRecamara4y, ancho, alto, false,
                                                -80f, 180.0f));

                // ==========================================================
                // 7. Recamara Principal
                // ==========================================================
                float puertaRecaPrincipalx = convertirXGeoAOpenGL(3.9f);
                float puertaRecaPrincipaly = convertirZGeoAOpenGL(7.0f);
                casa.agregarPuerta(
                                new Puerta("Puerta Pozo", puertaRecaPrincipalx, 3.2f, puertaRecaPrincipaly, ancho, alto,
                                                false, -80f,
                                                270.0f));

                // ==========================================================
                // 7. Closet
                // ==========================================================
                float puertaClosetx = convertirXGeoAOpenGL(4.9f);
                float puertaClosety = convertirZGeoAOpenGL(5.9f);
                casa.agregarPuerta(
                                new Puerta("Puerta Closet", puertaClosetx, 3.2f, puertaClosety, ancho, alto, false,
                                                -80f, 360.0f));

                // ==========================================================
                // 7. BañoPrincipal
                // ==========================================================
                float puertaBañoPrincipalx = convertirXGeoAOpenGL(4.9f);
                float puertaBañoPrincipaly = convertirZGeoAOpenGL(4.3f);
                casa.agregarPuerta(
                                new Puerta("Puerta BañoPrincipal", puertaBañoPrincipalx, 3.2f, puertaBañoPrincipaly,
                                                ancho, alto, false,
                                                -80f, 360.0f));

                // ==========================================================
                // 7. PUERTA Baño2da
                // ==========================================================
                float puertaBaño2dax = convertirXGeoAOpenGL(2.6f);
                float puertaBaño2day = convertirZGeoAOpenGL(15.4f);
                casa.agregarPuerta(
                                new Puerta("Puerta Baño2da", puertaBaño2dax, 3.2f, puertaBaño2day, ancho, alto, false,
                                                -80f, 180.0f));

                // ==========================================================
                // 7. PUERTA Recamara2
                // ==========================================================
                float puertaRecamara2x = convertirXGeoAOpenGL(3.9f);
                float puertaRecamara2y = convertirZGeoAOpenGL(16.1f);
                casa.agregarPuerta(
                                new Puerta("Puerta Recamara2", puertaRecamara2x, 3.2f, puertaRecamara2y, ancho, alto,
                                                false, 80f,
                                                0.0f));

                // ==========================================================
                // 7. PUERTA Lavanderia
                // ==========================================================
                float puertaLavanderiax = convertirXGeoAOpenGL(3.9f);
                float puertaLavanderiay = convertirZGeoAOpenGL(17.3f);
                casa.agregarPuerta(
                                new Puerta("Puerta Lavanderia", puertaLavanderiax, 3.2f, puertaLavanderiay, ancho, alto,
                                                false, 80f,
                                                270.0f));

                // ==========================================================
                // 7. PUERTA Area Libre
                // ==========================================================
                float puertaArealibrex = convertirXGeoAOpenGL(6.0f);
                float puertaArealibrey = convertirZGeoAOpenGL(17.3f);
                casa.agregarPuerta(
                                new Puerta("Puerta Area Libre", puertaArealibrex, 3.2f, puertaArealibrey, ancho, alto,
                                                false, 80f,
                                                360.0f));

                // ==========================================================
                // 7. PUERTA Recamara3
                // ==========================================================
                float puertaRecamara3x = convertirXGeoAOpenGL(4.9f);
                float puertaRecamara3y = convertirZGeoAOpenGL(17.0f);
                casa.agregarPuerta(
                                new Puerta("Puerta Recamara 3", puertaRecamara3x, 3.2f, puertaRecamara3y, ancho, alto,
                                                false, 80f,
                                                180.0f));

                // ==========================================================
                // 7. PUERTA Escalera
                // ==========================================================
                float puertaEscalera1x = convertirXGeoAOpenGL(4.9f);
                float puertaEscalera1y = convertirZGeoAOpenGL(9.5f);
                casa.agregarPuerta(
                                new Puerta("Puerta Escalera 1", puertaEscalera1x, 6.4f, puertaEscalera1y, ancho, alto,
                                                false, -80f,
                                                0.0f));

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

                        if (glfwGetKey(ventana, GLFW_KEY_E) == GLFW_PRESS) {
                                if (!ePressed) {
                                        ePressed = true;
                                        float gX = girasol.getX();
                                        float gY = girasol.getY();
                                        float gZ = girasol.getZ();

                                        Puerta puertaMasCercana = null;
                                        double distanciaMinima = 1.9; // Radio máximo de interacción

                                        for (Puerta puerta : casa.getPuertas()) {
                                                int nivelPuerta = 1;
                                                if (puerta.getY() > 3.0f)
                                                        nivelPuerta = 2;
                                                if (puerta.getY() > 6.0f)
                                                        nivelPuerta = 3;

                                                // Solo interactuar con puertas visibles
                                                if (nivelPuerta > nivelVisible)
                                                        continue;

                                                float dx = puerta.getX() - gX;
                                                float dy = puerta.getY() - gY;
                                                float dz = puerta.getZ() - gZ;
                                                double distancia = Math.sqrt(dx * dx + dy * dy + dz * dz);

                                                // Evalúa TODAS las puertas y se queda solo con la más cercana
                                                if (distancia < distanciaMinima) {
                                                        distanciaMinima = distancia;
                                                        puertaMasCercana = puerta;
                                                }
                                        }

                                        // Si encontró una puerta en el rango, interactúa con ella
                                        if (puertaMasCercana != null) {
                                                puertaMasCercana.interactuar();
                                                System.out.println(
                                                                "Interactuando con: " + puertaMasCercana.getNombre());
                                        }
                                }
                        } else {
                                ePressed = false;
                        }

                        for (Puerta puerta : casa.getPuertas()) {
                                puerta.actualizar(2.5f);
                        }

                        if (modoCamara == CAMARA_LIBRE) {
                                camaraLibre.actualizar(ventana);
                        } else {
                                girasol.actualizar(ventana, casa);
                        }

                        configurarCamara();

                        dibujarPiso();

                        dibujarCasaGeoGebra();

                        dibujarMuebles();

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
                float rotacionX = girasol.getRotacionX();
                float radianes = (float) Math.toRadians(rotacion);

                float direccionX = (float) Math.sin(radianes);
                float direccionZ = (float) Math.cos(radianes);

                // Pequeño retroceso de la cámara para que los ojos no entren visualmente en la
                // pared
                float distanciaOjosAtras = 0.35f;

                float camaraX = girasol.getX() + direccionX * distanciaOjosAtras;
                float camaraY = girasol.getAlturaOjos();
                float camaraZ = girasol.getZ() + direccionZ * distanciaOjosAtras;

                glRotatef(rotacionX, 1f, 0f, 0f);
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
                                // Hacer las paredes de la escalera invisibles en el renderizado para no
                                // obstruir
                                String nombrePared = pared.getNombre();
                                if (nombrePared.equals("d") || nombrePared.equals("P2_d1")
                                                || nombrePared.equals("P3_c1")) {
                                        continue;
                                }

                                Punto2D inicio = pared.getInicio();
                                Punto2D fin = pared.getFin();

                                if (pared.getTipo() == Pared.TIPO_VENTANA) {
                                        dibujarVentana(
                                                        (float) inicio.getX(),
                                                        (float) inicio.getY(),
                                                        (float) fin.getX(),
                                                        (float) fin.getY(),
                                                        (float) pared.getAltura(),
                                                        (float) pared.getGrosor(),
                                                        (float) pared.getAlturaBase());
                                } else if (pared.getTipo() == Pared.TIPO_VENTANAL) {
                                        dibujarVentanal(
                                                        (float) inicio.getX(),
                                                        (float) inicio.getY(),
                                                        (float) fin.getX(),
                                                        (float) fin.getY(),
                                                        (float) pared.getAltura(),
                                                        (float) pared.getGrosor(),
                                                        (float) pared.getAlturaBase());
                                } else {
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
                }

                dibujarLosas();
                dibujarEscaleras();

                // Barandales de pasillo y hueco de escalera
                if (nivelVisible >= 2) {
                        // Barandal del pasillo desde C(4.9, 12.0) a N1(4.9, 13.9) en Piso 2
                        dibujarBarandalRecto(4.9f, 12.0f, 4.9f, 13.9f, 3.2f);
                }

                // Dibujar las puertas
                for (Puerta puerta : casa.getPuertas()) {
                        int nivelPuerta = 1;
                        if (puerta.getY() > 3.0f)
                                nivelPuerta = 2;
                        if (puerta.getY() > 6.0f)
                                nivelPuerta = 3;

                        if (nivelPuerta <= nivelVisible) {
                                puerta.dibujar();
                        }
                }
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
                if (nivelVisible >= 2) {
                        dibujarLosaSegundoPiso();
                }

                if (nivelVisible >= 3) {
                        dibujarLosaTercerPiso();
                }
        }

        private void dibujarLosaSegundoPiso() {
                float altura = 3.2f;

                /*
                 * SEGUNDO PISO
                 *
                 * Importante:
                 * La zona B1, G1, V, U, S NO debe tener piso.
                 * Por eso no rellenamos completo ese bloque.
                 */

                // Habitación / zona inferior izquierda:
                // I, J, Q2, C1, H1, I1
                dibujarLosaSegundoPisoPorCoordenadas(0.1f, 0.2f, 4.8f, 1.6f, altura);
                dibujarLosaSegundoPisoPorCoordenadas(0.1f, 1.6f, 2.6f, 4.4f, altura);
                dibujarLosaSegundoPisoPorCoordenadas(2.6f, 1.6f, 4.8f, 4.4f, altura);

                // Recámara / zona principal:
                // J1, J, K, M, N, O, P, Q
                dibujarLosaSegundoPisoPorCoordenadas(0.1f, 4.4f, 3.5f, 7.9f, altura);
                dibujarLosaSegundoPisoPorCoordenadas(3.5f, 4.4f, 3.9f, 7.9f, altura);
                // dibujarLosaPorCoordenadas(3.9f, 5.5f, 4.9f, 7.0f, altura);
                dibujarLosaSegundoPisoPorCoordenadas(3.5f, 3.5f, 4.9f, 7.9f, altura);

                // Pasillo / conexión hacia zona media
                dibujarLosaSegundoPisoPorCoordenadas(0.1f, 7.9f, 4.9f, 9.5f, altura);
                dibujarLosaSegundoPisoPorCoordenadas(0.1f, 9.5f, 3.9f, 13.9f, altura);

                // Closet / habitación entre E, H, T, S, R, L1
                // Esta es la zona que dijiste que faltaba piso.
                dibujarLosaSegundoPisoPorCoordenadas(4.9f, 5.9f, 7.9f, 9.5f, altura);

                // Franja pequeña entre R, S y L1
                dibujarLosaSegundoPisoPorCoordenadas(4.9f, 5.3f, 7.9f, 5.9f, altura);

                /*
                 * Zona de escalera.
                 * Dejamos hueco al centro para que la escalera pueda llegar.
                 * No rellenamos completo de 4.9 a 7.9.
                 */

                // Lado izquierdo de llegada de escalera
                // dibujarLosaSegundoPisoPorCoordenadas(4.9f, 9.5f, 5.8f, 12.0f, altura);

                // Lado derecho de llegada de escalera
                // dibujarLosaSegundoPisoPorCoordenadas(7.2f, 9.5f, 7.9f, 12.0f, altura);
                // Losa del pasillo del blancon con eslaera
                dibujarLosaSegundoPisoPorCoordenadas(3.9f, 9.5f, 4.9f, 13.9f, altura);
                // Piso después de la escalera
                // dibujarLosaPorCoordenadas(4.9f, 12.0f, 7.9f, 13.9f, altura);

                // partes faltantes de la cmara principal espacialmente donde va el sillon
                dibujarLosaSegundoPisoPorCoordenadas(4.8f, 1.6f, 6.6f, 3.5f, altura);
                // parte faltante del baño principaemente donde va la tina
                dibujarLosaSegundoPisoPorCoordenadas(4.9f, 3.5f, 6.6f, 4.3f, altura);
                // parte faltante del baño principaemente el baño al lado de la tina
                dibujarLosaSegundoPisoPorCoordenadas(4.9f, 4.3f, 7.9f, 5.9f, altura);

                /*
                 * Parte superior izquierda:
                 * T1, U1, V1, W1, Z1, S1, R1, A2...
                 */
                dibujarLosaSegundoPisoPorCoordenadas(0.1f, 13.9f, 2.6f, 15.4f, altura);
                dibujarLosaSegundoPisoPorCoordenadas(2.6f, 13.9f, 3.9f, 16.1f, altura);
                dibujarLosaSegundoPisoPorCoordenadas(0.1f, 15.4f, 3.9f, 18.6f, altura);
                dibujarLosaSegundoPisoPorCoordenadas(0.1f, 18.6f, 3.9f, 20.3f, altura);

                /*
                 * Parte superior derecha:
                 * N1, O1, P1, Q1, C2, D2, L2...
                 */
                dibujarLosaSegundoPisoPorCoordenadas(3.9f, 13.9f, 7.9f, 17.3f, altura);
                dibujarLosaSegundoPisoPorCoordenadas(4.9f, 17.0f, 7.9f, 17.3f, altura);
                dibujarLosaSegundoPisoPorCoordenadas(6.0f, 17.3f, 7.9f, 20.3f, altura);
                dibujarLosaSegundoPisoPorCoordenadas(3.9f, 17.3f, 6.0f, 20.3f, altura);
        }

        private void dibujarLosaTercerPiso() {
                float altura = 6.4f;

                // Parte inferior izquierda
                dibujarLosaPorCoordenadas(0.1f, 1.5f, 6.6f, 2.6f, altura);
                dibujarLosaPorCoordenadas(0.1f, 2.6f, 6.6f, 5.0f, altura);

                // Parte central izquierda
                dibujarLosaPorCoordenadas(0.1f, 7.0f, 3.8f, 9.5f, altura);
                dibujarLosaPorCoordenadas(0.1f, 9.5f, 3.8f, 14.0f, altura);

                // Parte izquierda superior
                dibujarLosaPorCoordenadas(0.1f, 14.0f, 3.8f, 18.6f, altura);

                /*
                 * Hueco de escalera del segundo al tercer piso.
                 * No se rellena completo de 4.9 a 7.9 / 9.5 a 12.0.
                 */

                // Lado izquierdo del hueco
                // dibujarLosaPorCoordenadas(4.9f, 9.5f, 5.5f, 12.0f, altura);

                // Lado derecho del hueco
                // dibujarLosaPorCoordenadas(7.5f, 9.5f, 7.9f, 12.0f, altura);

                // Después de la llegada de la escalera
                // dibujarLosaPorCoordenadas(4.9f, 12.0f, 7.9f, 13.9f, altura);

                // Parte superior derecha
                dibujarLosaPorCoordenadas(3.8f, 13.9f, 7.9f, 17.4f, altura);
                dibujarLosaPorCoordenadas(5.9f, 17.4f, 7.9f, 20.3f, altura);
                dibujarLosaPorCoordenadas(3.8f, 17.4f, 5.9f, 20.3f, altura);

                // partes faltates comeor de arriba
                dibujarLosaPorCoordenadas(4.9f, 4.3f, 7.9f, 9.5f, altura);
                // sala de estar
                dibujarLosaPorCoordenadas(0.1f, 4.3f, 4.9f, 7.0f, altura);
                // pasilo de arriba
                dibujarLosaPorCoordenadas(3.8f, 7.0f, 4.9f, 13.9f, altura);
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

        private void dibujarLosaSegundoPisoPorCoordenadas(
                        float xMin,
                        float zMin,
                        float xMax,
                        float zMax,
                        float altura) {

                float centroX = (xMin + xMax) / 2.0f;
                float centroZ = (zMin + zMax) / 2.0f;

                float anchoLosa = xMax - xMin;
                float largoLosa = zMax - zMin;

                centroX = centroX - 4.0f;
                centroZ = centroZ - 10.0f;

                centroX = -centroX;

                centroX = centroX * Constantes.ESCALA_CASA;
                centroZ = centroZ * Constantes.ESCALA_CASA;
                anchoLosa = anchoLosa * Constantes.ESCALA_CASA;
                largoLosa = largoLosa * Constantes.ESCALA_CASA;

                // Color del piso 2
                float r = 0.30f;
                float g = 0.45f;
                float b = 0.65f;

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

        private void dibujarVentana(
                        float x1, float z1, float x2, float z2,
                        float altura, float grosor, float alturaBase) {
                x1 = x1 - 4.0f;
                x2 = x2 - 4.0f;
                z1 = z1 - 10.0f;
                z2 = z2 - 10.0f;
                x1 = -x1;
                x2 = -x2;
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
                glTranslatef(centroX, alturaBase, centroZ);
                glRotatef(-angulo, 0f, 1f, 0f);

                // Pared inferior (alféizar) - de 0 a 1m
                Cubo.dibujar(0f, 0.5f, 0f, longitud, 1.0f, grosor, 1f, 1f, 1f);
                // Pared superior (dintel) - de 2.5m a 3.2m
                Cubo.dibujar(0f, 2.85f, 0f, longitud, 0.7f, grosor, 1f, 1f, 1f);

                // Marcos laterales (aluminio negro)
                float altoVentana = 1.5f; // de 1.0m a 2.5m
                float yVentana = 1.75f;
                Cubo.dibujar(-longitud / 2f + 0.05f, yVentana, 0f, 0.1f, altoVentana, grosor * 1.05f, 0.1f, 0.1f, 0.1f);
                Cubo.dibujar(longitud / 2f - 0.05f, yVentana, 0f, 0.1f, altoVentana, grosor * 1.05f, 0.1f, 0.1f, 0.1f);
                // Marco central
                Cubo.dibujar(0f, yVentana, 0f, 0.1f, altoVentana, grosor * 1.05f, 0.1f, 0.1f, 0.1f);

                // Marcos horizontales (inferior y superior de la ventana)
                Cubo.dibujar(0f, 1.05f, 0f, longitud, 0.1f, grosor * 1.05f, 0.1f, 0.1f, 0.1f);
                Cubo.dibujar(0f, 2.45f, 0f, longitud, 0.1f, grosor * 1.05f, 0.1f, 0.1f, 0.1f);

                // Cristales (azul claro transparente)
                glEnable(GL_BLEND);
                glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                glDepthMask(false);
                Cubo.dibujar(-longitud / 4f, yVentana, 0f, longitud / 2f - 0.1f, altoVentana - 0.2f, 0.02f, 0.4f, 0.6f,
                                0.8f, 0.35f);
                Cubo.dibujar(longitud / 4f, yVentana, 0f, longitud / 2f - 0.1f, altoVentana - 0.2f, 0.02f, 0.4f, 0.6f,
                                0.8f, 0.35f);
                glDepthMask(true);
                glDisable(GL_BLEND);

                glPopMatrix();
        }

        private void dibujarVentanal(
                        float x1, float z1, float x2, float z2,
                        float altura, float grosor, float alturaBase) {
                x1 = x1 - 4.0f;
                x2 = x2 - 4.0f;
                z1 = z1 - 10.0f;
                z2 = z2 - 10.0f;
                x1 = -x1;
                x2 = -x2;
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
                glTranslatef(centroX, alturaBase, centroZ);
                glRotatef(-angulo, 0f, 1f, 0f);

                // Marcos horizontales (suelo y techo)
                Cubo.dibujar(0f, 0.05f, 0f, longitud, 0.1f, grosor * 1.05f, 0.1f, 0.1f, 0.1f);
                Cubo.dibujar(0f, altura - 0.05f, 0f, longitud, 0.1f, grosor * 1.05f, 0.1f, 0.1f, 0.1f);

                // Marcos laterales y centrales
                // Asumiendo paneles de aproximadamente 1m de ancho
                int numPaneles = Math.max(1, Math.round(longitud / 1.0f));
                float anchoPanel = longitud / numPaneles;
                float altoVentana = altura - 0.2f;
                float yVentana = altura / 2f;

                // Marco izquierdo
                Cubo.dibujar(-longitud / 2f + 0.05f, yVentana, 0f, 0.1f, altoVentana, grosor * 1.05f, 0.1f, 0.1f, 0.1f);

                // Cristales y marcos separadores
                for (int i = 0; i < numPaneles; i++) {
                        float xCentroPanel = -longitud / 2f + (i * anchoPanel) + (anchoPanel / 2f);

                        // Cristal transparente
                        glEnable(GL_BLEND);
                        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                        glDepthMask(false);
                        Cubo.dibujar(xCentroPanel, yVentana, 0f, anchoPanel - 0.1f, altoVentana, 0.02f, 0.4f, 0.6f,
                                        0.8f, 0.35f);
                        glDepthMask(true);
                        glDisable(GL_BLEND);

                        // Marco separador derecho del panel (excepto en el último que es el borde
                        // derecho)
                        if (i < numPaneles - 1) {
                                float xMarco = -longitud / 2f + ((i + 1) * anchoPanel);
                                Cubo.dibujar(xMarco, yVentana, 0f, 0.1f, altoVentana, grosor * 1.05f, 0.1f, 0.1f, 0.1f);
                        }
                }

                // Marco derecho
                Cubo.dibujar(longitud / 2f - 0.05f, yVentana, 0f, 0.1f, altoVentana, grosor * 1.05f, 0.1f, 0.1f, 0.1f);

                glPopMatrix();
        }

        private void dibujarBarandalRecto(float x1, float z1, float x2, float z2, float y) {
                float opX1 = convertirXGeoAOpenGL(x1);
                float opZ1 = convertirZGeoAOpenGL(z1);
                float opX2 = convertirXGeoAOpenGL(x2);
                float opZ2 = convertirZGeoAOpenGL(z2);

                float dx = opX2 - opX1;
                float dz = opZ2 - opZ1;
                float cx = (opX1 + opX2) / 2.0f;
                float cz = (opZ1 + opZ2) / 2.0f;
                float length = (float) Math.sqrt(dx * dx + dz * dz);
                float angle = (float) Math.toDegrees(Math.atan2(dz, dx));

                float altoBarandal = 0.9f;
                float[] colBarandal = { 0.1f, 0.1f, 0.1f };

                glPushMatrix();
                glTranslatef(cx, y, cz);
                glRotatef(-angle, 0f, 1f, 0f);

                Cubo.dibujar(0f, altoBarandal, 0f, length, 0.04f, 0.04f, colBarandal[0], colBarandal[1],
                                colBarandal[2]);
                Cubo.dibujar(0f, altoBarandal * 0.66f, 0f, length, 0.02f, 0.02f, colBarandal[0], colBarandal[1],
                                colBarandal[2]);
                Cubo.dibujar(0f, altoBarandal * 0.33f, 0f, length, 0.02f, 0.02f, colBarandal[0], colBarandal[1],
                                colBarandal[2]);
                glPopMatrix();

                int numPostes = Math.max(1, (int) (length / 0.5f));
                for (int i = 0; i <= numPostes; i++) {
                        float t = (float) i / numPostes;
                        float px = opX1 + t * dx;
                        float pz = opZ1 + t * dz;
                        Cubo.dibujar(px, y + altoBarandal / 2.0f, pz, 0.04f, altoBarandal, 0.04f, colBarandal[0],
                                        colBarandal[1], colBarandal[2]);
                }
        }

        private void dibujarMuebles() {
                if (nivelVisible >= 1) {
                        Oficina.dibujar();
                        SalaEstar.dibujar();
                        BanoMedio.dibujar();
                        Cocina.dibujar();
                        BanoPrincipal.dibujar();
                        Gimnasio.dibujar();
                        Recamara1.dibujar();
                        AreaLimpieza.dibujar();
                        PlantasPrimerPiso.dibujar();

                        Auto.dibujar();

                }
                if (nivelVisible >= 2) {
                        Recamara2p2.dibujar();
                        BanoPrincipalp2.dibujar();
                        Recamara3P2.dibujar();
                        LavanderiaP2.dibujar();
                        SalaEstarP2.dibujar();
                        PozoLuzP2.dibujar();
                        Recamara4P2.dibujar();
                }
        }

        private void cerrar() {

                glfwDestroyWindow(ventana);

                glfwTerminate();
        }
}