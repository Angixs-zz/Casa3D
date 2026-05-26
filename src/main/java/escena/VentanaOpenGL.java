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
import utilidades.CargadorTexturas;
import objetos.Escalera3D;
import objetos.EstructuraCerrada;
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
import piso2.ClosetP2;
import piso2.LavanderiaP2;
import piso2.Recamara3P2;
import piso2.LavanderiaP2;
import piso2.SalaEstarP2;
import piso2.PozoLuzP2;
import piso2.Recamara4P2;
import piso2.ClosetP2;
import piso2.BanoP2;
import piso2.RecamaraPrincipalP2;
import piso2.BalconP2;
import piso2.Piso3;
import piso3.PergolaBancoP3;
import piso3.TerrazaP3;
import piso3.PergolaBancoP3;
import piso3.ComedorP3;
import piso3.EstructurasTechoP3;
import objetos.Fuente;
import objetos.EstructuraCerrada;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class VentanaOpenGL {

        private long ventana;

        private Casa casa;
        private CamaraLibre camaraLibre;
        private Girasol girasol;
        private int texturaParedBlanca;
        private int texturaClosetPiso2;
        private int texturaPiso3;
        private int texturaParedBano;
        private int texturaEscritorio;
        private int texturaLibro;
        private int texturaSillon;
        private int texturaParedLadrillo;
        private int texturaSillonIndividual;
        private int texturaSillaOficina;
        private int texturaCuadroOficina;
        private int texturaSillonGrisSala;
        private int texturaAlmohadaSala;
        private int texturaParedAzul;
        private int texturaCuadro2;
        private int texturaCuadro3;
        private int texturaCuadro4;
        private int texturaCuadro5;
        private int texturaParedMadera;
        private int texturaCuadro6;
        private int texturaCuadro7;
        private int texturaCuadro8;
        private int texturaCuadro9;
        private int texturaCuadro10;
        private int texturaCuadro11;
        private int texturaAdornoSala;
        private int texturaAdorno2;
        private int texturaChimenea;
        private int texturaRepisa;
        private int texturaBano;
        private int texturaPisoDentro;
        private int texturaCochera;
        private int texturaPiedraFuera;
        private int texturaMaderaPrincipal;
        private int texturaParedCochera;

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
                // VENTANAL CORREDIZO (K1 a L1)
                // ==========================================================
                float ventanalX = convertirXGeoAOpenGL(5.7f); // K1
                float ventanalZ = convertirZGeoAOpenGL(14.0f); // Mismo eje Z
                float ventanalAncho = (7.3f - 5.7f) * Constantes.ESCALA_CASA; // de K1 a L1
                // El ventanal se abre hasta la mitad (un panel se desliza sobre el otro)
                Puerta ventanalCorredizo = new Puerta("Ventanal Corredizo K1-L1", ventanalX, 0.0f, ventanalZ, ventanalAncho, alto, true, ventanalAncho * 0.5f, 180.0f);
                ventanalCorredizo.setEsCorrediza(true);
                casa.agregarPuerta(ventanalCorredizo);


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

                // ==========================================================
                // PUERTA Balcon Recamara Principal
                // ==========================================================
                /*
                 * float puertaBalconX = convertirXGeoAOpenGL(1.2f);
                 * float puertaBalconZ = convertirZGeoAOpenGL(1.6f);
                 * Puerta puertaBalcon = new Puerta("Puerta Balcon", puertaBalconX, 3.2f,
                 * puertaBalconZ, 1.1f * Constantes.ESCALA_CASA, alto,
                 * true, -90f, 180.0f);
                 * puertaBalcon.interactuar(); // Abrirla por defecto
                 */
                // casa.agregarPuerta(puertaBalcon);

                camaraLibre = new CamaraLibre();
                girasol = new Girasol(-2.7f, 0.0f, -22.0f, 0.5f, 180f);

                // Cargar la textura de las paredes
                texturaParedBlanca = CargadorTexturas.cargarTextura("/texturas/PAREDBLANCA.jpg");
                texturaEscritorio = CargadorTexturas.cargarTextura("/texturas/escritoriooficina.jpg");
                objetos.BanoPrincipal.texturaMuebleBano = texturaEscritorio;
                piso2.BanoPrincipalp2.texturaMuebleBano = texturaEscritorio;
                texturaRepisa = CargadorTexturas.cargarTextura("/texturas/repisaoficina.jpg");
                texturaLibro = CargadorTexturas.cargarTextura("/texturas/libro1.jpg");
                texturaSillon = CargadorTexturas.cargarTextura("/texturas/sillonoficina.jpg");
                texturaParedLadrillo = CargadorTexturas.cargarTextura("/texturas/ladrillooficina.jpg");
                texturaSillonIndividual = CargadorTexturas.cargarTextura("/texturas/SILLONBLANOFI.jpg");
                texturaSillaOficina = CargadorTexturas.cargarTextura("/texturas/SILLAOFICINA.jpg");
                texturaCuadroOficina = CargadorTexturas.cargarTextura("/texturas/CUADROOFI.jpg");
                texturaSillonGrisSala = CargadorTexturas.cargarTextura("/texturas/SILLONGRISSALA.jpg");
                texturaAlmohadaSala = CargadorTexturas.cargarTextura("/texturas/ALMOHADASALA.jpg");
                texturaParedAzul = CargadorTexturas.cargarTextura("/texturas/paredazul.jpg");
                texturaCuadro2 = CargadorTexturas.cargarTextura("/texturas/cuadro2.jpg");
                texturaCuadro3 = CargadorTexturas.cargarTextura("/texturas/cuadro3.jpg");
                texturaCuadro4 = CargadorTexturas.cargarTextura("/texturas/cuadro4.jpg");
                texturaCuadro5 = CargadorTexturas.cargarTextura("/texturas/cuadro5.jpg");
                texturaParedMadera = CargadorTexturas.cargarTextura("/texturas/paredmadera.jpg");
                texturaCuadro6 = CargadorTexturas.cargarTextura("/texturas/cuadro6.jpg");
                texturaCuadro7 = CargadorTexturas.cargarTextura("/texturas/cuadro7.jpg");
                texturaCuadro8 = CargadorTexturas.cargarTextura("/texturas/cuadro8.jpg");
                texturaCuadro9 = CargadorTexturas.cargarTextura("/texturas/cuadro9.jpg");
                texturaCuadro10 = CargadorTexturas.cargarTextura("/texturas/cuadro10.jpg");
                texturaCuadro11 = CargadorTexturas.cargarTextura("/texturas/cuadro11.jpg");
                objetos.Gimnasio.texturaSacoBox = CargadorTexturas.cargarTextura("/texturas/sacodebox.jpg");
                objetos.Gimnasio.texturaRutina = CargadorTexturas.cargarTextura("/texturas/rutina.jpg");
                texturaAdornoSala = CargadorTexturas.cargarTextura("/texturas/adornosala.jpg");
                texturaAdorno2 = CargadorTexturas.cargarTextura("/texturas/adorno2.jpg");
                texturaChimenea = CargadorTexturas.cargarTextura("/texturas/chimenea.jpg");
                texturaRepisa = CargadorTexturas.cargarTextura("/texturas/repisaoficina.jpg");
                texturaBano = CargadorTexturas.cargarTextura("/texturas/baño.jpg");
                texturaParedBano = CargadorTexturas.cargarTextura("/texturas/bañopared.png");
                
                objetos.BanoPrincipal.texturaLavaboBano = texturaBano;
                piso2.BanoPrincipalp2.texturaLavaboBano = texturaBano;
                
                int texEspejo = CargadorTexturas.cargarTextura("/texturas/espejo.jpg");
                objetos.BanoPrincipal.texturaEspejo = texEspejo;
                piso2.BanoPrincipalp2.texturaEspejo = texEspejo;
                texturaPisoDentro = CargadorTexturas.cargarTextura("/texturas/PISODENTRO.jpg");
                texturaCochera = CargadorTexturas.cargarTextura("/texturas/cochera.jpg");
                texturaPiedraFuera = CargadorTexturas.cargarTextura("/texturas/piedrafuera.jpg");
                texturaMaderaPrincipal = CargadorTexturas.cargarTextura("/texturas/maderaPrincipal.jpg");
                texturaParedCochera = CargadorTexturas.cargarTextura("/texturas/paredCochera.jpg");
                
                // Cargar textura de césped para las áreas verdes
                int texturaCespedVal = CargadorTexturas.cargarTextura("/texturas/cesped.jpg");
                objetos.PlantasPrimerPiso.texturaCesped = texturaCespedVal;
                objetos.Cocina.texturaMaderaParaCocina = CargadorTexturas
                                .cargarTextura("/texturas/MADERAPARACOCINA.jpg");
                objetos.Fuente.texturaCascada = CargadorTexturas.cargarTextura("/texturas/cascada.jpg");
                piso2.SalaEstarP2.texturaSofa = texturaSillonGrisSala;
                piso2.SalaEstarP2.texturaCojin = texturaAlmohadaSala;
                piso2.SalaEstarP2.texturaMadera = texturaMaderaPrincipal;
                piso3.PergolaBancoP3.texturaMadera = texturaMaderaPrincipal;
                piso3.ComedorP3.texturaMadera = texturaMaderaPrincipal;
                piso2.SalaEstarP2.texturaDecoracion = texturaAdornoSala;
                
                piso2.BanoP2.texturaPiso = texturaPisoDentro;
                piso2.BanoP2.texturaMueble = objetos.Cocina.texturaMaderaParaCocina;
                piso2.BanoP2.texturaCeramica = texturaBano;
                piso2.BanoP2.texturaTapete = texturaAlmohadaSala;
                piso2.BanoP2.texturaDecoracion = texturaAdornoSala;
                
                texturaClosetPiso2 = CargadorTexturas.cargarTextura("/texturas/closetPiso2.jpg");
                texturaPiso3 = CargadorTexturas.cargarTextura("/texturas/piso3.jpg");
                piso2.ClosetP2.texturaMaderaCloset = texturaClosetPiso2;
                piso2.ClosetP2.texturaTapete = texturaAlmohadaSala;
                piso2.ClosetP2.texturaCojin = texturaSillonGrisSala;
                
                objetos.Cocina.texturaCocinaAzul = CargadorTexturas.cargarTextura("/texturas/cocinaazul.jpg");
                objetos.Cocina.texturaCocinaMadera = CargadorTexturas.cargarTextura("/texturas/COCINAMADERA.jpg");
                objetos.Cocina.texturaRefri = CargadorTexturas.cargarTextura("/texturas/refri.jpg");
                objetos.Cocina.texturaSillaBlanca = CargadorTexturas.cargarTextura("/texturas/sillablanca.jpg");
                objetos.Cocina.texturaSillaGris = CargadorTexturas.cargarTextura("/texturas/sillagris.jpg");

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
                        if (pared instanceof objetos.ParedInvisible) {
                                continue;
                        }
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
                                                        pared.getNombre(),
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
                
                // Dibujar decoración de pérgola/alero blanco en la fachada (Piso 2)
                if (nivelVisible >= 2) {
                        dibujarDecoracionFachada();
                }
        }

        private void dibujarDecoracionFachada() {
                if (texturaParedBlanca == 0) return;
                
                // Usamos la textura blanca para esta estructura
                glEnable(GL_TEXTURE_2D);
                glBindTexture(GL_TEXTURE_2D, texturaParedBlanca);
                
                // 1. Viga izquierda (al lado de la pared azul)
                // Centros OpenGL corregidos: X=-1.7f, Y=3.05f, Z=-18.2f
                // Dimensiones escaladas (ESCALA_CASA = 2.0f): ancho=0.2f, alto=0.3f, profundo=2.8f
                Cubo.dibujarConTextura(-1.7f, 3.05f, -18.2f, 0.2f, 0.3f, 2.8f, texturaParedBlanca);
                
                // 2. Viga derecha (conectada al bloque de piedra)
                // Centros OpenGL corregidos: X=-3.7f, Y=3.05f, Z=-18.2f
                // Dimensiones escaladas: ancho=0.2f, alto=0.3f, profundo=2.8f
                Cubo.dibujarConTextura(-3.7f, 3.05f, -18.2f, 0.2f, 0.3f, 2.8f, texturaParedBlanca);
                
                // 3. Viga frontal (conecta ambas vigas al frente)
                // Centros OpenGL corregidos: X=-2.7f, Y=3.05f, Z=-19.5f
                // Dimensiones escaladas: ancho=2.2f, alto=0.3f, profundo=0.2f
                Cubo.dibujarConTextura(-2.7f, 3.05f, -19.5f, 2.2f, 0.3f, 0.2f, texturaParedBlanca);
                
                // 4. Vigas intermedias tipo pérgola (más delgadas y elevadas)
                // Centros OpenGL corregidos: X=-2.36f y X=-3.03f, Y=3.125f, Z=-18.2f
                // Dimensiones escaladas: ancho=0.1f, alto=0.15f, profundo=2.8f
                Cubo.dibujarConTextura(-2.36f, 3.125f, -18.2f, 0.1f, 0.15f, 2.8f, texturaParedBlanca);
                Cubo.dibujarConTextura(-3.03f, 3.125f, -18.2f, 0.1f, 0.15f, 2.8f, texturaParedBlanca);
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

                if (texturaPisoDentro != 0) {
                Cubo.dibujarConTexturaRepetida(
                                centroX,
                                altura,
                                centroZ,
                                anchoPiso,
                                0.06f,
                                largoPiso,
                                texturaPisoDentro,
                                4.0f);
                } else {
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
                        dibujarLosaCuartoPiso(); // Techo del piso 3
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
                dibujarLosaPorCoordenadas(0.1f, 1.5f, 6.6f, 2.6f, altura, texturaPiso3);
                dibujarLosaPorCoordenadas(0.1f, 2.6f, 6.6f, 5.0f, altura, texturaPiso3);

                // Parte central izquierda
                dibujarLosaPorCoordenadas(0.1f, 7.0f, 3.8f, 7.9f, altura, texturaPiso3); // Se eliminó de Z=7.9 a 9.5 (Z, D1, E1, A1)
                dibujarLosaPorCoordenadas(0.1f, 9.5f, 3.8f, 14.0f, altura, texturaPiso3);

                // Parte izquierda superior
                dibujarLosaPorCoordenadas(0.1f, 14.0f, 3.8f, 18.6f, altura, texturaPiso3);

                /*
                 * Hueco de escalera del segundo al tercer piso.
                 * No se rellena completo de 4.9 a 7.9 / 9.5 a 12.0.
                 */

                // Lado izquierdo del hueco
                // dibujarLosaPorCoordenadas(4.9f, 9.5f, 5.5f, 12.0f, altura, texturaPiso3);

                // Lado derecho del hueco
                // dibujarLosaPorCoordenadas(7.5f, 9.5f, 7.9f, 12.0f, altura, texturaPiso3);

                // Después de la llegada de la escalera
                // dibujarLosaPorCoordenadas(4.9f, 12.0f, 7.9f, 13.9f, altura, texturaPiso3);

                // Parte superior derecha
                dibujarLosaPorCoordenadas(3.8f, 13.9f, 7.9f, 17.4f, altura, texturaPiso3);
                dibujarLosaPorCoordenadas(5.9f, 17.4f, 7.9f, 20.3f, altura, texturaPiso3);
                dibujarLosaPorCoordenadas(3.8f, 17.4f, 5.9f, 20.3f, altura, texturaPiso3);

                // partes faltates comeor de arriba
                dibujarLosaPorCoordenadas(4.9f, 4.3f, 7.9f, 9.5f, altura, texturaPiso3);
                // sala de estar
                dibujarLosaPorCoordenadas(0.1f, 4.3f, 4.9f, 7.0f, altura, texturaPiso3);
                // pasilo de arriba
                dibujarLosaPorCoordenadas(3.8f, 7.0f, 4.9f, 13.9f, altura, texturaPiso3);
        }

        private void dibujarLosaCuartoPiso() {
                float altura = 9.6f; // 3.2 * 3

                /*
                 * CUARTO PISO (Techo del Piso 3)
                 * Zona cubierta de la pérgola y las recámaras
                 */

                dibujarLosaPorCoordenadas(4.9f, 9.5f, 7.9f, 12.0f, altura, 0);
        }

        private void dibujarLosaPorCoordenadas(
                        float xMin,
                        float zMin,
                        float xMax,
                        float zMax,
                        float altura,
                        int texturaID) {
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

                if (texturaID != 0) {
                        Cubo.dibujarConTexturaRepetida(
                                        centroX,
                                        altura,
                                        centroZ,
                                        anchoLosa,
                                        0.12f,
                                        largoLosa,
                                        texturaID,
                                        4);
                } else {
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
                        String nombre,
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

                if (nombre != null && nombre.equals("t") && texturaParedMadera != 0 && texturaParedBlanca != 0) {
                        // La cara interior (+Z) apuntando hacia la sala tendrá madera (texturaInterior
                        // en el método)
                        // La cara exterior (-Z) apuntando hacia afuera tendrá blanco (texturaExterior
                        // en el método)
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor, texturaParedBlanca,
                                        texturaParedMadera);

                        if (texturaCuadro6 != 0 || texturaCuadro7 != 0) {
                                float anchoCuadro = 2.8f; // Tamaño gigante restaurado (para cuadro 6)
                                float altoCuadro = 1.6f;
                                float grosorCuadro = 0.04f;
                                float zCuadro = (grosor / 2f) + (grosorCuadro / 2f);

                                if (texturaCuadro6 != 0) {
                                        glPushMatrix();
                                        glTranslatef(0f, 0.6f, zCuadro); // cuadro6 perfectamente centrado
                                        glRotatef(180f, 0f, 0f, 1f);
                                        Cubo.dibujarConTextura(0f, 0f, 0f, anchoCuadro, altoCuadro, grosorCuadro,
                                                        texturaCuadro6);
                                        glPopMatrix();
                                }

                                if (texturaCuadro7 != 0) {
                                        float anchoCuadro7 = 1.0f; // Cuadro más angosto (vertical)
                                        float altoCuadro7 = 1.8f; // Más alto para respetar su proporción

                                        glPushMatrix();
                                        // cuadro7 a la izquierda de cuadro6 (coordenadas positivas en este eje local)
                                        glTranslatef(2.1f, 0.6f, zCuadro);
                                        glRotatef(180f, 0f, 0f, 1f);
                                        Cubo.dibujarConTextura(0f, 0f, 0f, anchoCuadro7, altoCuadro7, grosorCuadro,
                                                        texturaCuadro7);
                                        glPopMatrix();
                                }
                        }
                } else if (nombre != null && nombre.equals("n") && texturaParedAzul != 0 && texturaParedLadrillo != 0) {
                        // Textura azul de un lado (interior) y ladrillo del otro (exterior)
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor, texturaParedLadrillo,
                                        texturaParedAzul);

                        // Dibujar los cuadros en la cara interior (+Z)
                        float anchoCuadro = 0.4f;
                        float altoCuadro = 0.5f;
                        float grosorCuadro = 0.04f;
                        float zCuadro = (grosor / 2f) + (grosorCuadro / 2f);

                        if (texturaCuadro2 != 0) {
                                // Cuadro superior izquierdo
                                Cubo.dibujarConTextura(-0.25f, 0.35f, zCuadro, anchoCuadro, altoCuadro, grosorCuadro,
                                                texturaCuadro2);
                        }
                        if (texturaCuadro3 != 0) {
                                // Cuadro superior derecho
                                Cubo.dibujarConTextura(0.25f, 0.35f, zCuadro, anchoCuadro, altoCuadro, grosorCuadro,
                                                texturaCuadro3);
                        }
                        if (texturaCuadro4 != 0) {
                                // Cuadro inferior izquierdo
                                Cubo.dibujarConTextura(-0.25f, -0.25f, zCuadro, anchoCuadro, altoCuadro, grosorCuadro,
                                                texturaCuadro4);
                        }
                        if (texturaCuadro5 != 0) {
                                // Cuadro inferior derecho
                                Cubo.dibujarConTextura(0.25f, -0.25f, zCuadro, anchoCuadro, altoCuadro, grosorCuadro,
                                                texturaCuadro5);
                        }
                } else if (nombre != null && nombre.equals("s") && texturaParedLadrillo != 0
                                && texturaParedBlanca != 0) {
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor, texturaParedBlanca,
                                        texturaParedLadrillo);
                } else if (nombre != null && nombre.equals("a") && texturaParedBlanca != 0 && texturaParedAzul != 0) {
                        // Textura azul del lado de la sala (interior), blanca del otro lado (baño)
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor, texturaParedBlanca,
                                        texturaParedAzul);

                        // Cuadro grande en el lado de la sala (+Z local)
                        if (texturaCuadro8 != 0) {
                                float anchoCuadro = 2.0f; // Tamaño adecuado grande
                                float altoCuadro = 1.2f; // Proporción adecuada
                                float grosorCuadro = 0.04f;
                                float zCuadro = (grosor / 2f) + (grosorCuadro / 2f);
                                Cubo.dibujarConTextura(0f, 0.4f, zCuadro, anchoCuadro, altoCuadro, grosorCuadro,
                                                texturaCuadro8);
                        }
                } else if (nombre != null && nombre.equals("a1") && objetos.Cocina.texturaCocinaMadera != 0) {
                        int texturaOtroLado = texturaParedBano != 0 ? texturaParedBano
                                        : objetos.Cocina.texturaCocinaMadera;
                        // a1 está rotada 180 grados, por lo que el lado de la cocina es +Z (Interior) y
                        // el otro lado es -Z (Exterior)
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor, texturaOtroLado,
                                        objetos.Cocina.texturaCocinaMadera);
                } else if (nombre != null && nombre.equals("b1") && objetos.Cocina.texturaCocinaMadera != 0) {
                        int texturaOtroLado = texturaParedBano != 0 ? texturaParedBano
                                        : objetos.Cocina.texturaCocinaMadera;
                        // b1 tiene el lado de la cocina hacia -Z (Exterior) y el otro lado hacia +Z
                        // (Interior)
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor,
                                        objetos.Cocina.texturaCocinaMadera, texturaOtroLado);
                } else if (nombre != null && nombre.equals("h1") && objetos.Cocina.texturaMaderaParaCocina != 0) {
                        int texturaOtroLado = texturaParedBlanca != 0 ? texturaParedBlanca
                                        : objetos.Cocina.texturaMaderaParaCocina;
                        // h1 tiene el lado de la cocina hacia -Z (Exterior) y el otro lado hacia +Z
                        // (Interior)
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor,
                                        objetos.Cocina.texturaMaderaParaCocina, texturaOtroLado);
                } else if (nombre != null && (nombre.equals("k1") || nombre.equals("p1")) && texturaParedAzul != 0) {
                        int texturaOtroLado = texturaParedBlanca != 0 ? texturaParedBlanca
                                        : texturaParedAzul;
                        // k1 y p1 tienen el lado del gimnasio hacia +Z (Interior) y el otro lado hacia -Z (Exterior)
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor, texturaOtroLado,
                                        texturaParedAzul);

                        if (nombre.equals("p1")) {
                                float anchoCuadro = 1.2f;
                                float altoCuadro = 1.6f;
                                float grosorCuadro = 0.04f;
                                float zCuadro = (grosor / 2f) + (grosorCuadro / 2f);

                                // Distribuimos 3 cuadros a lo largo de la pared (eje X local)
                                if (texturaCuadro9 != 0) {
                                        glPushMatrix();
                                        glTranslatef(-2.5f, 0.4f, zCuadro);
                                        glRotatef(180f, 0f, 0f, 1f);
                                        Cubo.dibujarConTextura(0f, 0f, 0f, anchoCuadro, altoCuadro, grosorCuadro, texturaCuadro9);
                                        glPopMatrix();
                                }
                                if (texturaCuadro10 != 0) {
                                        glPushMatrix();
                                        glTranslatef(0f, 0.4f, zCuadro);
                                        glRotatef(180f, 0f, 0f, 1f);
                                        Cubo.dibujarConTextura(0f, 0f, 0f, anchoCuadro, altoCuadro, grosorCuadro, texturaCuadro10);
                                        glPopMatrix();
                                }
                                if (texturaCuadro11 != 0) {
                                        glPushMatrix();
                                        glTranslatef(2.5f, 0.4f, zCuadro);
                                        glRotatef(180f, 0f, 0f, 1f);
                                        Cubo.dibujarConTextura(0f, 0f, 0f, anchoCuadro, altoCuadro, grosorCuadro, texturaCuadro11);
                                        glPopMatrix();
                                }
                        }
                } else if (nombre != null && (nombre.equals("c1") || nombre.equals("d1")) && texturaParedBano != 0 && texturaParedBlanca != 0) {
                        // Paredes donde el lado 2 (Atrás/-Z local) mira hacia el interior del baño
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor, texturaParedBlanca, texturaParedBano);
                } else if (nombre != null && nombre.equals("s1") && texturaParedBano != 0 && texturaParedBlanca != 0) {
                        // Paredes donde el lado 1 (Frente/+Z local) mira hacia el interior del baño
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor, texturaParedBano, texturaParedBlanca);
                } else if (nombre != null && (nombre.equals("t1") || nombre.equals("Dintel baño2")) && texturaParedBano != 0 && texturaParedBlanca != 0) {
                        // Pared t1 y el marco interior (Dintel baño2) llevan textura de baño en ambas caras
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor, texturaParedBano, texturaParedBano);
                } else if (nombre != null && (nombre.equals("h") || nombre.equals("i") || nombre.equals("j")
                                || nombre.equals("P2_m") || nombre.equals("P2_n") || nombre.equals("P2_p")
                                || nombre.equals("P2_q") || nombre.equals("P2_r") || nombre.equals("P3_r_resto"))
                                && texturaPiedraFuera != 0) {
                        Cubo.dibujarConTextura(0f, 0f, 0f, longitud, altura, grosor, texturaPiedraFuera);
                } else if (nombre != null && (nombre.equals("Dintel Principal") || nombre.equals("P2_l") || nombre.equals("P3_r_madera")) 
                                && texturaMaderaPrincipal != 0) {
                        Cubo.dibujarConTextura(0f, 0f, 0f, longitud, altura, grosor, texturaMaderaPrincipal);
                } else if (nombre != null && (nombre.equals("P2_g") || nombre.equals("P2_i") || nombre.equals("P3_r_azul"))
                                && texturaParedAzul != 0) {
                        int texturaInterior = texturaParedBlanca != 0 ? texturaParedBlanca : 0;
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor, texturaInterior, texturaParedAzul);
                } else if (nombre != null && (nombre.equals("g") || nombre.equals("r_cochera")) 
                                && texturaParedCochera != 0) {
                        Cubo.dibujarConTextura(0f, 0f, 0f, longitud, altura, grosor, texturaParedCochera);
                } else if (nombre != null && nombre.equals("f") && texturaCochera != 0) {
                        int texturaOtroLado = texturaParedBlanca != 0 ? texturaParedBlanca : 0;
                        Cubo.dibujarConDosTexturas(0f, 0f, 0f, longitud, altura, grosor, texturaOtroLado,
                                        texturaCochera);
                } else if (texturaParedBlanca != 0) {
                        Cubo.dibujarConTextura(0f, 0f, 0f, longitud, altura, grosor, texturaParedBlanca);
                } else {
                        Cubo.dibujar(0f, 0f, 0f, longitud, altura, grosor, 1f, 1f, 1f);
                }

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

                // Pared inferior (alféizar) y superior (dintel)
                if (texturaParedBlanca != 0) {
                        Cubo.dibujarConTextura(0f, 0.5f, 0f, longitud, 1.0f, grosor, texturaParedBlanca);
                        Cubo.dibujarConTextura(0f, 2.85f, 0f, longitud, 0.7f, grosor, texturaParedBlanca);
                } else {
                        Cubo.dibujar(0f, 0.5f, 0f, longitud, 1.0f, grosor, 1f, 1f, 1f);
                        Cubo.dibujar(0f, 2.85f, 0f, longitud, 0.7f, grosor, 1f, 1f, 1f);
                }

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
                        Oficina.dibujar(texturaEscritorio, texturaRepisa, texturaLibro, texturaSillon,
                                        texturaSillonIndividual, texturaSillaOficina, texturaCuadroOficina);
                        SalaEstar.dibujar(texturaSillonGrisSala, texturaAlmohadaSala, texturaSillon, texturaEscritorio,
                                        texturaAdornoSala, texturaAdorno2, texturaChimenea, texturaRepisa);
                        BanoMedio.dibujar(texturaEscritorio, texturaBano);
                        Cocina.dibujar();
                        BanoPrincipal.dibujar();
                        Gimnasio.dibujar();
                        Recamara1.dibujar();
                        AreaLimpieza.dibujar();
                        PlantasPrimerPiso.dibujar();
                        Fuente.dibujarFuenteDoble();

                        Auto.dibujar();
                        Fuente.dibujar();
                        EstructuraCerrada.dibujar();

                }
                if (nivelVisible >= 2) {
                        Recamara2p2.dibujar();
                        BanoPrincipalp2.dibujar();
                        Recamara3P2.dibujar();
                        LavanderiaP2.dibujar();
                        SalaEstarP2.dibujar();
                        PozoLuzP2.dibujar();
                        Recamara4P2.dibujar();
                        ClosetP2.dibujar();
                        BanoP2.dibujar();
                        RecamaraPrincipalP2.dibujar();
                        BalconP2.dibujar();
                }
                if (nivelVisible >= 3) {
                        Piso3.dibujar();
                        TerrazaP3.dibujar();
                        PergolaBancoP3.dibujar();
                        ComedorP3.dibujar();
                        EstructurasTechoP3.dibujar();
                }
        }

        private void cerrar() {

                glfwDestroyWindow(ventana);

                glfwTerminate();
        }
}