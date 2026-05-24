package objetos;

import utilidades.Constantes;

import static org.lwjgl.opengl.GL11.*;

public class Cocina {

    private static final float Y = Constantes.ALTURA_PISO_1;

    private static float convertirXGeoAOpenGL(float xGeo) {
        float x = xGeo - Constantes.CENTRO_GEOGEBRA_X;
        x = -x;
        return x * Constantes.ESCALA_CASA;
    }

    private static float convertirZGeoAOpenGL(float zGeo) {
        float z = zGeo - Constantes.CENTRO_GEOGEBRA_Z;
        return z * Constantes.ESCALA_CASA;
    }

    private static float escalar(float valor) {
        return valor * Constantes.ESCALA_CASA;
    }

    public static void dibujar() {
        /*
         * =====================================================
         * COCINA - PISO 1
         * =====================================================
         *
         * Grupo derecho:
         * O3 = (3.2, 9.7)
         * P3 = (3.8, 9.7)
         * Q3 = (3.8, 8.6)
         * R3 = (2.4, 8.6)
         * S3 = (2.4, 9.1)
         * T3 = (3.2, 9.1)
         *
         * Grupo izquierdo:
         * Z3 = (0.2, 8.6)
         * A4 = (1.5, 8.6)
         * B4 = (1.5, 9.1)
         * W3 = (0.7, 9.1)
         * U3 = (0.2, 9.8)
         * V3 = (0.7, 9.8)
         *
         * Espacio del refrigerador:
         * U3 = (0.2, 9.8)
         * V3 = (0.7, 9.8)
         * H4 = (0.2, 10.3)
         * I4 = (0.7, 10.3)
         *
         * Isla / mesa:
         * F4 = (1.9, 10.4)
         * E4 = (1.9, 10.9)
         * G4 = (3.8, 10.9)
         * D4 = (3.8, 10.4)
         */

        dibujarGrupoDerechoInferior();
        dibujarGrupoDerechoSuperior();

        dibujarGrupoIzquierdoInferior();
        dibujarGrupoIzquierdoSuperior();

        dibujarLavaboCocina();
        dibujarEstufaConCampana();
        dibujarRefrigerador();
        dibujarIslaComedor();
        dibujarDecoracionTechoIsla();
        dibujarAlacenaAlta();

        // Alacenas adicionales sobre lavabo y refrigerador
        dibujarAlacenasExtra();
        dibujarMesaComedor6Sillas();
        dibujarVitrinaVinos();
        dibujarRepisasVinosLaterales();
        dibujarTVComedor();
    }

    // =====================================================
    // TELEVISIÓN EN COMEDOR
    // =====================================================
    private static void dibujarTVComedor() {
        // Pared D1 (0.1, 14.0) a E1 (3.9, 14.0)
        // Centro X = 2.0, Z = 14.0 (A la pared)
        float xCentroGeo = 2.0f;
        float zCentroGeo = 13.95f; // Ligeramente despegado de la pared para evitar z-fighting

        float x = convertirXGeoAOpenGL(xCentroGeo);
        float z = convertirZGeoAOpenGL(zCentroGeo);

        float anchoTV = escalar(2.2f); // Pantalla grande
        float altoTV = 1.2f;
        float grosorTV = escalar(0.08f);

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // Marco de la TV (Negro)
        Cubo.dibujar(
                0f,
                1.50f, // Altura en la pared
                0f,
                anchoTV,
                altoTV,
                grosorTV,
                0.05f, 0.05f, 0.05f);

        // Pantalla de la TV (Gris oscuro / efecto apagado)
        Cubo.dibujar(
                0f,
                1.50f,
                -grosorTV / 2f - 0.005f, // Al frente del marco
                anchoTV * 0.96f,
                altoTV * 0.92f,
                0.01f,
                0.12f, 0.12f, 0.15f); // Gris azulado oscuro

        glPopMatrix();
    }

    private static void dibujarRepisasVinosLaterales() {
        // Pegadas a la pared izquierda (X aprox 0.1). Las ponemos en xGeo = 0.16f.
        dibujarRepisaVinosPared(0.16f, 11.95f, 90f);
        dibujarRepisaVinosPared(0.16f, 13.55f, 90f);
    }

    private static void dibujarRepisaVinosPared(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        /*
         * Soporte vertical de madera estilo rústico (como la foto)
         * Pegado a la pared.
         */
        Cubo.dibujar(
                0f,
                1.30f,
                0f,
                escalar(0.25f), // Ancho de la tabla (Z visual)
                1.30f,          // Alto de la tabla
                escalar(0.05f), // Grosor (despegue de la pared)
                0.35f, 0.18f, 0.08f); // Madera oscura rústica

        // Botellas alternando direcciones (cuello izq, cuello der)
        float startY = 0.75f;
        float sepY = 0.22f; // Separación vertical entre botellas
        
        dibujarBotellaColgadaPared(0f, startY, 0.06f, true);
        dibujarBotellaColgadaPared(0f, startY + sepY * 1, 0.06f, false);
        dibujarBotellaColgadaPared(0f, startY + sepY * 2, 0.06f, true);
        dibujarBotellaColgadaPared(0f, startY + sepY * 3, 0.06f, false);
        dibujarBotellaColgadaPared(0f, startY + sepY * 4, 0.06f, true);
        dibujarBotellaColgadaPared(0f, startY + sepY * 5, 0.06f, false);

        glPopMatrix();
    }

    private static void dibujarBotellaColgadaPared(
            float zLocal,
            float yLocal,
            float xLocal,
            boolean apuntaIzquierda) {

        float direccion = apuntaIzquierda ? -1f : 1f;

        glPushMatrix();
        glTranslatef(xLocal, yLocal, zLocal);

        // Cuerpo oscuro de la botella
        Cubo.dibujar(
                0f,
                0f,
                0f,
                escalar(0.40f), // Largo
                0.08f,          // Alto (diámetro)
                escalar(0.08f), // Profundidad (diámetro)
                0.15f, 0.20f, 0.15f); // Verde oscuro botella

        // Etiqueta clara rústica (blanco amarillento)
        Cubo.dibujar(
                0f,
                0.005f,
                0f,
                escalar(0.20f),
                0.082f,
                escalar(0.082f),
                0.92f, 0.88f, 0.80f);

        // Cuello de la botella
        Cubo.dibujar(
                direccion * escalar(0.26f),
                0f,
                0f,
                escalar(0.12f),
                0.035f,
                escalar(0.035f),
                0.15f, 0.20f, 0.15f);

        // Tapa/cápsula naranja
        Cubo.dibujar(
                direccion * escalar(0.35f),
                0f,
                0f,
                escalar(0.06f),
                0.038f,
                escalar(0.038f),
                0.95f, 0.40f, 0.10f); // Naranja fuerte

        glPopMatrix();
    }

    private static void dibujarBotellaColgada(
            float xLocal,
            float yLocal,
            float zLocal,
            boolean apuntaIzquierda) {

        float direccion = apuntaIzquierda ? -1f : 1f;

        glPushMatrix();
        glTranslatef(xLocal, yLocal, zLocal);

        // Cuerpo oscuro de la botella acostada
        Cubo.dibujar(
                0f,
                0f,
                0f,
                escalar(0.55f),
                0.10f,
                escalar(0.10f),
                0.04f,
                0.04f,
                0.03f);

        // Etiqueta clara al centro
        Cubo.dibujar(
                0f,
                0.01f,
                0f,
                escalar(0.22f),
                0.11f,
                escalar(0.11f),
                0.88f,
                0.82f,
                0.65f);

        // Cuello de la botella
        Cubo.dibujar(
                direccion * escalar(0.34f),
                0f,
                0f,
                escalar(0.18f),
                0.07f,
                escalar(0.07f),
                0.03f,
                0.03f,
                0.025f);

        // Tapa naranja
        Cubo.dibujar(
                direccion * escalar(0.46f),
                0f,
                0f,
                escalar(0.08f),
                0.075f,
                escalar(0.075f),
                0.90f,
                0.35f,
                0.08f);

        // Soportes pequeños negros sobre la madera
        Cubo.dibujar(
                -escalar(0.17f),
                -0.08f,
                0f,
                escalar(0.05f),
                0.08f,
                escalar(0.12f),
                0.02f,
                0.02f,
                0.02f);

        Cubo.dibujar(
                escalar(0.17f),
                -0.08f,
                0f,
                escalar(0.05f),
                0.08f,
                escalar(0.12f),
                0.02f,
                0.02f,
                0.02f);

        glPopMatrix();
    }

    private static void dibujarMesaComedor6Sillas() {
        /*
         * Mesa comedor en el rectángulo:
         * L4 = (1.1, 13.3)
         * M4 = (1.1, 12.2)
         * N4 = (3.0, 12.2)
         * O4 = (3.0, 13.3)
         */

        float xCentroGeo = 2.05f;
        float zCentroGeo = 12.75f;

        float x = convertirXGeoAOpenGL(xCentroGeo);
        float z = convertirZGeoAOpenGL(zCentroGeo);

        float anchoMesa = escalar(1.10f);
        float fondoMesa = escalar(0.58f);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Cubierta de la mesa
        Cubo.dibujar(
                0f,
                0.78f,
                0f,
                anchoMesa,
                0.10f,
                fondoMesa,
                0.46f,
                0.28f,
                0.14f);

        // Base central de la mesa
        Cubo.dibujar(
                0f,
                0.40f,
                0f,
                escalar(0.16f),
                0.70f,
                escalar(0.16f),
                0.16f,
                0.16f,
                0.16f);

        // Base inferior
        Cubo.dibujar(
                0f,
                0.05f,
                0f,
                escalar(0.48f),
                0.10f,
                escalar(0.48f),
                0.10f,
                0.10f,
                0.10f);

        glPopMatrix();

        // =====================================================
        // 6 SILLAS ALREDEDOR
        // 2 arriba, 2 abajo, 1 izquierda, 1 derecha
        // =====================================================

        // Arriba
        dibujarSillaComedor(1.55f, 13.28f, 0f);
        dibujarSillaComedor(2.55f, 13.28f, 0f);

        // Abajo
        dibujarSillaComedor(1.55f, 12.22f, 180f);
        dibujarSillaComedor(2.55f, 12.22f, 180f);

        // Izquierda
        dibujarSillaComedor(1.02f, 12.75f, 90f);

        // Derecha
        dibujarSillaComedor(3.08f, 12.75f, 270f);
    }

    private static void dibujarSillaComedor(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Asiento
        Cubo.dibujar(
                0f,
                0.45f,
                0f,
                escalar(0.28f),
                0.08f,
                escalar(0.28f),
                0.30f,
                0.18f,
                0.10f);

        // Respaldo
        Cubo.dibujar(
                0f,
                0.80f,
                escalar(0.12f),
                escalar(0.28f),
                0.55f,
                escalar(0.08f),
                0.26f,
                0.16f,
                0.09f);

        // Patas
        Cubo.dibujar(-escalar(0.10f), 0.20f, -escalar(0.10f), escalar(0.05f), 0.40f, escalar(0.05f), 0.14f, 0.14f,
                0.14f);
        Cubo.dibujar(escalar(0.10f), 0.20f, -escalar(0.10f), escalar(0.05f), 0.40f, escalar(0.05f), 0.14f, 0.14f,
                0.14f);
        Cubo.dibujar(-escalar(0.10f), 0.20f, escalar(0.10f), escalar(0.05f), 0.40f, escalar(0.05f), 0.14f, 0.14f,
                0.14f);
        Cubo.dibujar(escalar(0.10f), 0.20f, escalar(0.10f), escalar(0.05f), 0.40f, escalar(0.05f), 0.14f, 0.14f, 0.14f);

        glPopMatrix();
    }

    private static void dibujarVitrinaVinos() {
        /*
         * Vitrina / mueble transparente para vinos:
         *
         * P4 = (0.2, 12.2)
         * Q4 = (0.6, 12.2)
         * R4 = (0.6, 13.3)
         * S4 = (0.2, 13.3)
         */

        float xCentroGeo = 0.40f;
        float zCentroGeo = 12.75f;

        float x = convertirXGeoAOpenGL(xCentroGeo);
        float z = convertirZGeoAOpenGL(zCentroGeo);

        float ancho = escalar(0.34f);
        float fondo = escalar(1.02f);
        float altura = 2.20f;

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Base inferior oscura
        Cubo.dibujar(
                0f,
                0.05f,
                0f,
                ancho,
                0.10f,
                fondo,
                0.10f,
                0.10f,
                0.12f);

        // Techo de la vitrina
        Cubo.dibujar(
                0f,
                altura,
                0f,
                ancho,
                0.10f,
                fondo,
                0.12f,
                0.12f,
                0.15f);

        // Laterales (simulando vidrio)
        Cubo.dibujar(
                -ancho / 2f + escalar(0.02f),
                altura / 2f,
                0f,
                escalar(0.04f),
                altura,
                fondo,
                0.78f,
                0.88f,
                0.92f);

        Cubo.dibujar(
                ancho / 2f - escalar(0.02f),
                altura / 2f,
                0f,
                escalar(0.04f),
                altura,
                fondo,
                0.78f,
                0.88f,
                0.92f);

        // Parte trasera
        Cubo.dibujar(
                0f,
                altura / 2f,
                fondo / 2f - escalar(0.02f),
                ancho,
                altura,
                escalar(0.04f),
                0.75f,
                0.84f,
                0.90f);

        // Puerta frontal de vidrio
        Cubo.dibujar(
                0f,
                altura / 2f,
                -fondo / 2f + escalar(0.02f),
                ancho,
                altura,
                escalar(0.04f),
                0.84f,
                0.92f,
                0.96f);

        // Manija
        Cubo.dibujar(
                ancho / 2f - escalar(0.05f),
                1.10f,
                -fondo / 2f + escalar(0.04f),
                escalar(0.03f),
                0.40f,
                escalar(0.03f),
                0.70f,
                0.70f,
                0.70f);

        // Repisas internas
        Cubo.dibujar(
                0f,
                0.62f,
                0f,
                ancho * 0.90f,
                0.04f,
                fondo * 0.90f,
                0.18f,
                0.18f,
                0.20f);

        Cubo.dibujar(
                0f,
                1.18f,
                0f,
                ancho * 0.90f,
                0.04f,
                fondo * 0.90f,
                0.18f,
                0.18f,
                0.20f);

        Cubo.dibujar(
                0f,
                1.74f,
                0f,
                ancho * 0.90f,
                0.04f,
                fondo * 0.90f,
                0.18f,
                0.18f,
                0.20f);

        // Botellas / vinos
        dibujarBotellaVinoLocal(0f, 0.34f, -fondo * 0.25f, 0.35f, 0.35f, 0.35f);
        dibujarBotellaVinoLocal(0f, 0.34f, 0f, 0.45f, 0.12f, 0.12f);
        dibujarBotellaVinoLocal(0f, 0.34f, fondo * 0.25f, 0.78f, 0.72f, 0.22f);

        dibujarBotellaVinoLocal(0f, 0.90f, -fondo * 0.25f, 0.20f, 0.45f, 0.18f);
        dibujarBotellaVinoLocal(0f, 0.90f, 0f, 0.52f, 0.18f, 0.18f);
        dibujarBotellaVinoLocal(0f, 0.90f, fondo * 0.25f, 0.72f, 0.65f, 0.22f);

        dibujarBotellaVinoLocal(0f, 1.46f, -fondo * 0.25f, 0.38f, 0.12f, 0.12f);
        dibujarBotellaVinoLocal(0f, 1.46f, 0f, 0.55f, 0.16f, 0.16f);
        dibujarBotellaVinoLocal(0f, 1.46f, fondo * 0.25f, 0.78f, 0.24f, 0.18f);

        glPopMatrix();
    }

    private static void dibujarBotellaVinoLocal(
            float xLocal,
            float yBase,
            float zLocal,
            float r,
            float g,
            float b) {

        // Cuerpo de la botella
        Cubo.dibujar(
                xLocal,
                yBase + 0.18f,
                zLocal,
                escalar(0.10f),
                0.36f,
                escalar(0.10f),
                r,
                g,
                b);

        // Cuello
        Cubo.dibujar(
                xLocal,
                yBase + 0.42f,
                zLocal,
                escalar(0.05f),
                0.14f,
                escalar(0.05f),
                r * 0.8f,
                g * 0.8f,
                b * 0.8f);

        // Tapa
        Cubo.dibujar(
                xLocal,
                yBase + 0.51f,
                zLocal,
                escalar(0.04f),
                0.04f,
                escalar(0.04f),
                0.82f,
                0.72f,
                0.25f);
    }

    private static void dibujarAlacenaAlta() {
        /*
         * Alacena / closet alto de cocina:
         *
         * H4 = (0.2, 10.3)
         * I4 = (0.7, 10.3)
         * J4 = (0.7, 10.9)
         * K4 = (0.2, 10.9)
         *
         * Es un mueble completo del suelo al techo.
         */

        float xCentroGeo = 0.45f;
        float zCentroGeo = 10.60f;

        float x = convertirXGeoAOpenGL(xCentroGeo);
        float z = convertirZGeoAOpenGL(zCentroGeo);

        float ancho = escalar(0.48f);
        float fondo = escalar(0.56f);
        float altura = 3.20f; // Llega hasta el techo

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Cuerpo principal azul oscuro
        Cubo.dibujar(
                0f,
                altura / 2f,
                0f,
                ancho,
                altura,
                fondo,
                0.04f,
                0.10f,
                0.25f);

        // Puerta superior
        Cubo.dibujar(
                0f,
                2.46f,
                -fondo / 2f + 0.02f,
                ancho * 0.92f,
                1.40f,
                0.035f,
                0.05f,
                0.12f,
                0.30f);

        // Puerta media
        Cubo.dibujar(
                0f,
                1.25f,
                -fondo / 2f + 0.02f,
                ancho * 0.92f,
                0.85f,
                0.035f,
                0.05f,
                0.12f,
                0.30f);

        // Puerta inferior
        Cubo.dibujar(
                0f,
                0.42f,
                -fondo / 2f + 0.02f,
                ancho * 0.92f,
                0.65f,
                0.035f,
                0.05f,
                0.12f,
                0.30f);

        // Separaciones entre puertas
        Cubo.dibujar(
                0f,
                1.75f,
                -fondo / 2f + 0.04f,
                ancho * 0.95f,
                0.025f,
                0.025f,
                0.02f,
                0.04f,
                0.08f);

        Cubo.dibujar(
                0f,
                0.82f,
                -fondo / 2f + 0.04f,
                ancho * 0.95f,
                0.025f,
                0.025f,
                0.02f,
                0.04f,
                0.08f);

        // Jaladeras verticales
        Cubo.dibujar(
                ancho * 0.28f,
                2.46f,
                -fondo / 2f + 0.06f,
                0.04f,
                0.35f,
                0.035f,
                0.75f,
                0.75f,
                0.75f);

        Cubo.dibujar(
                ancho * 0.28f,
                1.25f,
                -fondo / 2f + 0.06f,
                0.04f,
                0.35f,
                0.035f,
                0.75f,
                0.75f,
                0.75f);

        Cubo.dibujar(
                ancho * 0.28f,
                0.42f,
                -fondo / 2f + 0.06f,
                0.04f,
                0.28f,
                0.035f,
                0.75f,
                0.75f,
                0.75f);

        // Zócalo inferior
        Cubo.dibujar(
                0f,
                0.04f,
                0f,
                ancho,
                0.08f,
                fondo,
                0.03f,
                0.03f,
                0.04f);

        glPopMatrix();
    }

    // =====================================================
    // GRUPO DERECHO - MUEBLES INFERIORES
    // =====================================================
    private static void dibujarGrupoDerechoInferior() {
        // Bloque horizontal inferior
        dibujarModuloBajoPorGeo(
                2.43f, 8.63f,
                3.77f, 9.10f);

        // Bloque vertical derecho
        dibujarModuloBajoPorGeo(
                3.20f, 9.10f,
                3.77f, 9.67f);
    }

    // =====================================================
    // GRUPO DERECHO - GABINETES ALTOS
    // =====================================================
    private static void dibujarGrupoDerechoSuperior() {
        // Gabinete alto horizontal
        dibujarGabineteAltoPorGeo(
                2.43f, 8.66f,
                3.77f, 9.00f, 1.55f, 1.65f);

        // Gabinete alto vertical
        dibujarGabineteAltoPorGeo(
                3.30f, 9.10f,
                3.77f, 9.67f, 1.55f, 1.65f);
    }

    // =====================================================
    // GRUPO IZQUIERDO - MUEBLES INFERIORES
    // =====================================================
    private static void dibujarGrupoIzquierdoInferior() {
        // Bloque horizontal inferior
        dibujarModuloBajoPorGeo(
                0.23f, 8.63f,
                1.47f, 9.10f);

        // Bloque vertical izquierdo
        // OJO: ahora termina en 9.8 para dejar espacio al refrigerador
        dibujarModuloBajoPorGeo(
                0.23f, 9.10f,
                0.67f, 9.77f);
    }

    // =====================================================
    // GRUPO IZQUIERDO - GABINETES ALTOS
    // =====================================================
    private static void dibujarGrupoIzquierdoSuperior() {
        // Gabinete alto horizontal
        dibujarGabineteAltoPorGeo(
                0.23f, 8.66f,
                1.47f, 9.00f, 1.55f, 1.65f);

        // Gabinete alto vertical
        // También se recorta para no invadir el espacio del refrigerador
        dibujarGabineteAltoPorGeo(
                0.23f, 9.10f,
                0.60f, 9.77f, 1.55f, 1.65f);
    }

    // =====================================================
    // ALACENAS EXTRA (Lavabo y Refrigerador)
    // =====================================================
    private static void dibujarAlacenasExtra() {
        // Alacena sobre el lavabo (llena el hueco horizontal)
        dibujarGabineteAltoPorGeo(
                1.47f, 8.66f,
                2.43f, 9.00f,
                1.55f, 1.65f);

        // Alacena sobre el refrigerador (espacio U3 a I4)
        // El refrigerador mide aprox 2.14m de alto con su base y remate superior.
        // El techo está a 3.20m.
        float yBaseRefri = 2.14f;
        float alturaRefri = 3.20f - yBaseRefri;

        dibujarGabineteAltoPorGeo(
                0.20f, 9.80f,
                0.70f, 10.30f,
                yBaseRefri, alturaRefri);
    }

    // =====================================================
    // LAVABO DE COCINA
    // Rectángulo: B4, S3, R3, A4
    // =====================================================
    private static void dibujarLavaboCocina() {
        float xCentroGeo = 1.95f;
        float zCentroGeo = 8.85f;

        float x = convertirXGeoAOpenGL(xCentroGeo);
        float z = convertirZGeoAOpenGL(zCentroGeo);

        float ancho = escalar(0.85f);
        float fondo = escalar(0.45f);

        float alturaBase = 0.75f;
        float alturaCubierta = 0.10f;

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Mueble azul inferior
        Cubo.dibujar(
                0f,
                alturaBase / 2f,
                0f,
                ancho,
                alturaBase,
                fondo,
                0.05f,
                0.12f,
                0.30f);

        // Cubierta café
        Cubo.dibujar(
                0f,
                alturaBase + alturaCubierta / 2f,
                0f,
                ancho + 0.08f,
                alturaCubierta,
                fondo + 0.08f,
                0.45f,
                0.28f,
                0.12f);

        // Borde metálico exterior de la tarja
        Cubo.dibujar(
                0f,
                alturaBase + alturaCubierta + 0.03f,
                0f,
                escalar(0.58f),
                0.04f,
                escalar(0.34f),
                0.70f,
                0.70f,
                0.70f);

        // Hueco negro de la tarja
        Cubo.dibujar(
                0f,
                alturaBase + alturaCubierta + 0.05f,
                0f,
                escalar(0.45f),
                0.03f,
                escalar(0.23f),
                0.03f,
                0.03f,
                0.03f);

        // Llave vertical
        Cubo.dibujar(
                0f,
                alturaBase + alturaCubierta + 0.23f,
                -fondo / 4f,
                escalar(0.05f),
                0.30f,
                escalar(0.05f),
                0.65f,
                0.65f,
                0.65f);

        // Llave horizontal
        Cubo.dibujar(
                0f,
                alturaBase + alturaCubierta + 0.37f,
                -fondo / 8f,
                escalar(0.05f),
                0.05f,
                escalar(0.20f),
                0.65f,
                0.65f,
                0.65f);

        // Manijas del mueble
        Cubo.dibujar(
                -ancho * 0.18f,
                0.48f,
                -fondo / 2f + 0.03f,
                escalar(0.18f),
                0.035f,
                0.03f,
                0.82f,
                0.82f,
                0.82f);

        Cubo.dibujar(
                ancho * 0.18f,
                0.48f,
                -fondo / 2f + 0.03f,
                escalar(0.18f),
                0.035f,
                0.03f,
                0.82f,
                0.82f,
                0.82f);

        glPopMatrix();
    }

    // =====================================================
    // ESTUFA + CAMPANA
    // Rectángulo: O3, C4, D4, P3
    // =====================================================
    private static void dibujarEstufaConCampana() {
        float xCentroGeo = 3.5f;
        float zCentroGeo = 10.05f;

        float x = convertirXGeoAOpenGL(xCentroGeo);
        float z = convertirZGeoAOpenGL(zCentroGeo);

        float ancho = escalar(0.55f);
        float fondo = escalar(0.65f);

        float alturaBase = 0.75f;
        float alturaCubierta = 0.10f;

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Mueble inferior azul
        Cubo.dibujar(
                0f,
                alturaBase / 2f,
                0f,
                ancho,
                alturaBase,
                fondo,
                0.05f,
                0.12f,
                0.30f);

        // Cubierta café
        Cubo.dibujar(
                0f,
                alturaBase + alturaCubierta / 2f,
                0f,
                ancho + 0.08f,
                alturaCubierta,
                fondo + 0.08f,
                0.45f,
                0.28f,
                0.12f);

        // Parrilla negra
        Cubo.dibujar(
                0f,
                alturaBase + alturaCubierta + 0.035f,
                0f,
                ancho * 0.85f,
                0.05f,
                fondo * 0.75f,
                0.03f,
                0.03f,
                0.03f);

        // Quemadores
        dibujarQuemador(-ancho * 0.22f, alturaBase + alturaCubierta + 0.08f, -fondo * 0.18f);
        dibujarQuemador(ancho * 0.22f, alturaBase + alturaCubierta + 0.08f, -fondo * 0.18f);
        dibujarQuemador(-ancho * 0.22f, alturaBase + alturaCubierta + 0.08f, fondo * 0.18f);
        dibujarQuemador(ancho * 0.22f, alturaBase + alturaCubierta + 0.08f, fondo * 0.18f);

        // Frente del horno
        Cubo.dibujar(
                0f,
                0.42f,
                -fondo / 2f + 0.02f,
                ancho * 0.70f,
                0.35f,
                0.03f,
                0.85f,
                0.82f,
                0.78f);

        // Ventana negra del horno
        Cubo.dibujar(
                0f,
                0.43f,
                -fondo / 2f + 0.05f,
                ancho * 0.45f,
                0.20f,
                0.02f,
                0.03f,
                0.03f,
                0.03f);

        // Perillas
        Cubo.dibujar(-ancho * 0.30f, 0.68f, -fondo / 2f + 0.01f, 0.06f, 0.06f, 0.03f, 0.12f, 0.12f, 0.12f);
        Cubo.dibujar(-ancho * 0.10f, 0.68f, -fondo / 2f + 0.01f, 0.06f, 0.06f, 0.03f, 0.12f, 0.12f, 0.12f);
        Cubo.dibujar(ancho * 0.10f, 0.68f, -fondo / 2f + 0.01f, 0.06f, 0.06f, 0.03f, 0.12f, 0.12f, 0.12f);
        Cubo.dibujar(ancho * 0.30f, 0.68f, -fondo / 2f + 0.01f, 0.06f, 0.06f, 0.03f, 0.12f, 0.12f, 0.12f);

        // Campana extractora
        Cubo.dibujar(
                0f,
                1.55f,
                0f,
                ancho * 1.15f,
                0.16f,
                fondo * 0.80f,
                0.75f,
                0.75f,
                0.72f);

        Cubo.dibujar(
                0f,
                1.78f,
                0f,
                ancho * 0.90f,
                0.28f,
                fondo * 0.55f,
                0.82f,
                0.82f,
                0.78f);

        Cubo.dibujar(
                0f,
                2.56f, // Centro ajustado para llegar a 3.2
                0f,
                ancho * 0.40f,
                1.28f, // Altura extendida hasta el techo (3.2m)
                fondo * 0.30f,
                0.70f,
                0.70f,
                0.68f);

        glPopMatrix();
    }

    private static void dibujarQuemador(float x, float y, float z) {
        Cubo.dibujar(
                x,
                y,
                z,
                escalar(0.12f),
                0.035f,
                escalar(0.12f),
                0.08f,
                0.08f,
                0.08f);
    }

    // =====================================================
    // REFRIGERADOR
    // Rectángulo: U3, H4, I4, V3
    // U3 = (0.2, 9.8)
    // V3 = (0.7, 9.8)
    // H4 = (0.2, 10.3)
    // I4 = (0.7, 10.3)
    // =====================================================
    private static void dibujarRefrigerador() {
        float xCentroGeo = 0.45f;
        float zCentroGeo = 10.05f;

        float x = convertirXGeoAOpenGL(xCentroGeo);
        float z = convertirZGeoAOpenGL(zCentroGeo);

        float ancho = escalar(0.46f);
        float fondo = escalar(0.46f);
        float altura = 2.05f;

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Cuerpo principal blanco
        Cubo.dibujar(
                0f,
                altura / 2f,
                0f,
                ancho,
                altura,
                fondo,
                0.95f,
                0.95f,
                0.92f);

        // Puerta superior (congelador)
        Cubo.dibujar(
                0f,
                1.65f,
                -fondo / 2f + 0.015f,
                ancho * 0.92f,
                0.55f,
                0.03f,
                0.92f,
                0.92f,
                0.89f);

        // Puerta inferior (refrigerador)
        Cubo.dibujar(
                0f,
                0.72f,
                -fondo / 2f + 0.015f,
                ancho * 0.92f,
                1.20f,
                0.03f,
                0.93f,
                0.93f,
                0.90f);

        // Separación entre puertas
        Cubo.dibujar(
                0f,
                1.12f,
                -fondo / 2f + 0.02f,
                ancho * 0.95f,
                0.03f,
                0.02f,
                0.78f,
                0.78f,
                0.78f);

        // Jaladera superior
        Cubo.dibujar(
                ancho * 0.26f,
                1.65f,
                -fondo / 2f + 0.035f,
                0.04f,
                0.32f,
                0.03f,
                0.72f,
                0.72f,
                0.72f);

        // Jaladera inferior
        Cubo.dibujar(
                ancho * 0.26f,
                0.72f,
                -fondo / 2f + 0.035f,
                0.04f,
                0.60f,
                0.03f,
                0.72f,
                0.72f,
                0.72f);

        // Parte superior / remate
        Cubo.dibujar(
                0f,
                altura + 0.03f,
                0f,
                ancho * 1.02f,
                0.06f,
                fondo * 1.02f,
                0.88f,
                0.88f,
                0.85f);

        // Base inferior
        Cubo.dibujar(
                0f,
                0.03f,
                0f,
                ancho,
                0.06f,
                fondo,
                0.70f,
                0.70f,
                0.70f);

        glPopMatrix();
    }

    // =====================================================
    // ISLA / MESA COMEDOR
    // =====================================================
    private static void dibujarIslaComedor() {
        float xCentroGeo = 2.85f;
        float zCentroGeo = 10.65f;

        float x = convertirXGeoAOpenGL(xCentroGeo);
        float z = convertirZGeoAOpenGL(zCentroGeo);

        float ancho = escalar(1.9f);
        float fondo = escalar(0.5f);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Base azul
        Cubo.dibujar(
                0f,
                0.38f,
                0f,
                ancho,
                0.75f,
                fondo,
                0.05f,
                0.12f,
                0.30f);

        // Cubierta de madera
        Cubo.dibujar(
                0f,
                0.82f,
                0f,
                ancho + escalar(0.10f),
                0.10f,
                fondo + escalar(0.10f),
                0.45f,
                0.28f,
                0.12f);

        // Detalle frontal
        Cubo.dibujar(
                0f,
                0.50f,
                fondo / 2f - 0.02f,
                ancho * 0.70f,
                0.08f,
                0.03f,
                0.82f,
                0.82f,
                0.82f);

        glPopMatrix();

        // 3 sillas del lado E4 -> G4, por fuera
        dibujarSillaIsla(2.20f, 11.25f, 0f);
        dibujarSillaIsla(2.85f, 11.25f, 0f);
        dibujarSillaIsla(3.50f, 11.25f, 0f);
    }

    private static void dibujarSillaIsla(float xGeo, float zGeo, float rotacionY) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Asiento
        Cubo.dibujar(
                0f,
                0.42f,
                0f,
                escalar(0.28f),
                0.10f,
                escalar(0.28f),
                0.18f,
                0.18f,
                0.18f);

        // Respaldo
        Cubo.dibujar(
                0f,
                0.70f,
                escalar(0.14f),
                escalar(0.28f),
                0.45f,
                escalar(0.08f),
                0.12f,
                0.12f,
                0.12f);

        // Pata central
        Cubo.dibujar(
                0f,
                0.22f,
                0f,
                escalar(0.07f),
                0.40f,
                escalar(0.07f),
                0.10f,
                0.10f,
                0.10f);

        // Base
        Cubo.dibujar(
                0f,
                0.04f,
                0f,
                escalar(0.24f),
                0.08f,
                escalar(0.24f),
                0.08f,
                0.08f,
                0.08f);

        glPopMatrix();
    }

    // =====================================================
    // DECORACIÓN DE TECHO SOBRE LA ISLA
    // =====================================================
    private static void dibujarDecoracionTechoIsla() {
        float xCentroGeo = 2.85f;
        float zCentroGeo = 10.65f;

        float x = convertirXGeoAOpenGL(xCentroGeo);
        float z = convertirZGeoAOpenGL(zCentroGeo);

        float anchoMesa = escalar(1.9f);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // 1. Viga de madera en el techo (Plafón)
        // Se coloca justo debajo del techo (3.20m)
        Cubo.dibujar(
                0f,
                3.15f,
                0f,
                anchoMesa * 1.1f, // Ligeramente más ancha que la mesa
                0.10f,
                escalar(0.7f),
                0.40f, 0.22f, 0.10f); // Madera oscura

        // 2. Lámparas Colgantes (A los extremos)
        // Lámpara Izquierda
        dibujarLamparaColgante(-anchoMesa * 0.35f, 0f);
        // Lámpara Derecha
        dibujarLamparaColgante(anchoMesa * 0.35f, 0f);

        // 3. Rack colgante central para botellas y copas
        float altoRack = 2.45f;
        float anchoRack = escalar(0.6f);
        float fondoRack = escalar(0.35f);

        // Soportes/Cadenas negras desde el techo hasta el rack
        Cubo.dibujar(-anchoRack / 2f + 0.02f, 2.8f, -fondoRack / 2f + 0.02f, 0.02f, 0.6f, 0.02f, 0.15f, 0.15f, 0.15f);
        Cubo.dibujar(-anchoRack / 2f + 0.02f, 2.8f, fondoRack / 2f - 0.02f, 0.02f, 0.6f, 0.02f, 0.15f, 0.15f, 0.15f);
        Cubo.dibujar(anchoRack / 2f - 0.02f, 2.8f, -fondoRack / 2f + 0.02f, 0.02f, 0.6f, 0.02f, 0.15f, 0.15f, 0.15f);
        Cubo.dibujar(anchoRack / 2f - 0.02f, 2.8f, fondoRack / 2f - 0.02f, 0.02f, 0.6f, 0.02f, 0.15f, 0.15f, 0.15f);

        // Repisa de madera del rack
        Cubo.dibujar(0f, altoRack, 0f, anchoRack, 0.04f, fondoRack, 0.45f, 0.28f, 0.12f);
        // Borde frontal metálico del rack
        Cubo.dibujar(0f, altoRack + 0.04f, fondoRack / 2f, anchoRack, 0.05f, 0.02f, 0.2f, 0.2f, 0.2f);
        Cubo.dibujar(0f, altoRack + 0.04f, -fondoRack / 2f, anchoRack, 0.05f, 0.02f, 0.2f, 0.2f, 0.2f);

        // Botellas acostadas en la repisa
        Cubo.dibujar(-anchoRack * 0.25f, altoRack + 0.06f, 0f, 0.06f, 0.06f, fondoRack * 0.8f, 0.1f, 0.3f, 0.1f); // Verde
        Cubo.dibujar(0f, altoRack + 0.06f, 0f, 0.06f, 0.06f, fondoRack * 0.8f, 0.3f, 0.1f, 0.1f); // Roja
        Cubo.dibujar(anchoRack * 0.25f, altoRack + 0.06f, 0f, 0.06f, 0.06f, fondoRack * 0.8f, 0.1f, 0.1f, 0.3f); // Azul

        // Copas de vino colgando boca abajo debajo del rack
        float nivelCopas = altoRack - 0.12f; // Altura de las copas
        dibujarCopaInvertida(-anchoRack * 0.3f, nivelCopas, 0f);
        dibujarCopaInvertida(-anchoRack * 0.1f, nivelCopas, 0f);
        dibujarCopaInvertida(anchoRack * 0.1f, nivelCopas, 0f);
        dibujarCopaInvertida(anchoRack * 0.3f, nivelCopas, 0f);

        glPopMatrix();
    }

    private static void dibujarLamparaColgante(float xLocal, float zLocal) {
        // Cable negro
        // Desde el techo (3.10) hasta la lámpara (1.80). Altura = 1.3, Centro = 2.45
        Cubo.dibujar(xLocal, 2.45f, zLocal, 0.015f, 1.30f, 0.015f, 0.1f, 0.1f, 0.1f);

        // Pantalla de la lámpara (Cuerpo estilo diamante/conical en capas)
        Cubo.dibujar(xLocal, 1.85f, zLocal, 0.08f, 0.10f, 0.08f, 0.15f, 0.15f, 0.15f); // Base
        Cubo.dibujar(xLocal, 1.75f, zLocal, 0.20f, 0.10f, 0.20f, 0.2f, 0.2f, 0.2f); // Parte ancha
        Cubo.dibujar(xLocal, 1.65f, zLocal, 0.28f, 0.10f, 0.28f, 0.15f, 0.15f, 0.15f); // Borde inferior

        // Foco amarillo brillante
        Cubo.dibujar(xLocal, 1.55f, zLocal, 0.08f, 0.08f, 0.08f, 1.0f, 0.95f, 0.6f);
    }

    private static void dibujarCopaInvertida(float x, float y, float z) {
        // Cristal transparente / blancuzco
        float r = 0.85f, g = 0.9f, b = 0.95f;

        // Base de la copa (pegada al rack)
        Cubo.dibujar(x, y + 0.10f, z, 0.06f, 0.01f, 0.06f, r, g, b);

        // Tallo delgado
        Cubo.dibujar(x, y + 0.05f, z, 0.01f, 0.09f, 0.01f, r, g, b);

        // Cáliz ancho (cuerpo de la copa)
        Cubo.dibujar(x, y - 0.02f, z, 0.07f, 0.05f, 0.07f, r, g, b);

        // Borde superior de la copa (abajo en este caso porque está invertida)
        Cubo.dibujar(x, y - 0.06f, z, 0.05f, 0.04f, 0.05f, r, g, b);
    }

    // =====================================================
    // MÓDULO BAJO DE COCINA
    // =====================================================
    private static void dibujarModuloBajoPorGeo(
            float xMinGeo,
            float zMinGeo,
            float xMaxGeo,
            float zMaxGeo) {

        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo;
        float largoGeo = zMaxGeo - zMinGeo;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        float alturaBase = 0.75f;
        float alturaCubierta = 0.10f;

        // Mueble inferior azul
        Cubo.dibujar(
                x,
                Y + alturaBase / 2f,
                z,
                ancho,
                alturaBase,
                largo,
                0.05f,
                0.12f,
                0.30f);

        // Encimera café
        Cubo.dibujar(
                x,
                Y + alturaBase + alturaCubierta / 2f,
                z,
                ancho + 0.04f,
                alturaCubierta,
                largo + 0.04f,
                0.45f,
                0.28f,
                0.12f);

        // Zócalo inferior oscuro
        Cubo.dibujar(
                x,
                Y + 0.04f,
                z,
                ancho,
                0.08f,
                largo,
                0.04f,
                0.04f,
                0.04f);

        // División horizontal
        Cubo.dibujar(
                x,
                Y + 0.40f,
                z,
                ancho,
                0.03f,
                0.02f,
                0.75f,
                0.75f,
                0.75f);

        // Jaladera frontal
        Cubo.dibujar(
                x,
                Y + 0.55f,
                z - largo / 2f + 0.03f,
                ancho * 0.45f,
                0.03f,
                0.03f,
                0.82f,
                0.82f,
                0.82f);
    }

    // =====================================================
    // GABINETE ALTO
    // =====================================================
    private static void dibujarGabineteAltoPorGeo(
            float xMinGeo,
            float zMinGeo,
            float xMaxGeo,
            float zMaxGeo,
            float yBase,
            float altura) {

        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo;
        float largoGeo = zMaxGeo - zMinGeo;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        Cubo.dibujar(
                x,
                Y + yBase + altura / 2f,
                z,
                ancho,
                altura,
                largo,
                0.04f,
                0.10f,
                0.25f);

        // Jaladera frontal
        Cubo.dibujar(
                x,
                Y + yBase + altura / 2f,
                z - largo / 2f + 0.03f,
                ancho * 0.40f,
                0.03f,
                0.03f,
                0.82f,
                0.82f,
                0.82f);
    }
}