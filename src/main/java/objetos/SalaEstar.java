package objetos;

import utilidades.Constantes;

import static org.lwjgl.opengl.GL11.*;


public class SalaEstar {

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

        // =====================================================
        // SALA DE ESTAR - PISO 1
        // Solo sillones por ahora.
        // =====================================================

        // Grupo grande: M2, N2, O2, P2, Q2, R2
        // Este sillón sigue la forma real de los segmentos.
        dibujarSillonSeccionalGrande();

        // Grupo rectangular: S2, T2, U2, V2
        dibujarSillonCortoHorizontal(
                5.55f,
                8.95f,
                0.75f,
                0.45f,
                180f);

        // Grupo rombo: W2, Z2, A3, B3
        dibujarSillonIndividual(
                7.2f,
                6.0f,
                45f);

        dibujarMuebleYTV();

        // ==== NUEVAS MESAS ====
        dibujarMesaRectangularCentro();
        dibujarMesaCircular();
    }

    private static void dibujarMuebleYTV() {
        // Coordenadas calculadas a partir de C3, D3, E3, F3
        float centroXGeo = 4.1f;
        float centroZGeo = 7.75f;

        float anchoGeo = 0.4f; // Eje X (4.3 - 3.9)
        float largoGeo = 2.3f; // Eje Z (8.9 - 6.6)

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        // Altura del mueble de la TV
        float altoMueble = 0.45f;

        glPushMatrix();
        glTranslatef(x, Y, z);

        // 1. Mueble de TV (Mesa) - Color gris oscuro / madera oscura
        Cubo.dibujar(
                0f,
                altoMueble / 2f, // Y centrado en su propia altura
                0f,
                ancho,
                altoMueble,
                largo,
                0.20f, 0.20f, 0.20f); // Gris oscuro

        // 2. Base de la Televisión (el "pie" o soporte central)
        float altoBase = 0.05f;
        float yBase = altoMueble + (altoBase / 2f);
        Cubo.dibujar(
                0f,
                yBase,
                0f,
                escalar(0.15f),
                altoBase,
                escalar(0.50f),
                0.10f, 0.10f, 0.10f); // Casi negro

        float altoCuello = 0.08f;
        float yCuello = altoMueble + altoBase + (altoCuello / 2f);
        Cubo.dibujar(
                0f,
                yCuello,
                0f,
                escalar(0.05f),
                altoCuello,
                escalar(0.15f),
                0.10f, 0.10f, 0.10f); // Casi negro

        // 3. Pantalla Plana
        float altoTV = 1.9f;
        float grosorTV = escalar(0.04f); // Muy delgada
        float largoTV = escalar(1.8f); // Un poco más corta que el mueble (2.3)
        float yPantalla = altoMueble + altoBase + altoCuello + (altoTV / 2f);

        Cubo.dibujar(
                0f,
                yPantalla,
                0f,
                grosorTV,
                altoTV,
                largoTV,
                0.05f, 0.05f, 0.05f); // Pantalla negra

        glPopMatrix();
    }

    private static void dibujarSillonSeccionalGrande() {
        /*
         * Forma basada en los puntos de Geogebra para un contorno L:
         *
         * M2 = (6.4, 9.3) -> Extremo superior de la tumbona
         * R2 = (7.8, 9.3) -> Esquina superior derecha (respaldo exterior)
         * Q2 = (7.8, 6.8) -> Extremo inferior del brazo largo (respaldo exterior)
         * P2 = (7.0, 6.8) -> Extremo inferior del asiento brazo largo
         * O2 = (7.0, 8.4) -> Esquina interior de la L
         * N2 = (6.4, 8.4) -> Borde interior del asiento tumbona
         *
         * Se dibuja con bloques más grandes para un aspecto más limpio y uniforme.
         */

        float altoAsiento = 0.35f;
        float altoRespaldo = 0.70f;
        float profRespaldo = 0.14f;

        glPushMatrix();

        // --- BASE DEL ASIENTO (Gris Medio) ---
        // Dibujar un bloque L grande para el asiento para un aspecto limpio
        // Módulo Brazo Corto (Tumbona): Desde X=6.4, Z=9.3 hasta X=7.8, Z=8.4
        // Módulo Brazo Largo: Desde X=7.0, Z=8.4 hasta X=7.8, Z=6.8

        // Tumbona (Asiento Grande Uniforme)
        dibujarBloqueSillonPorGeo(
                6.4f, 8.4f,
                7.8f, 9.3f,
                altoAsiento,
                0.70f, 0.68f, 0.63f); // Gris Medio

        // Brazo Largo (Módulos de Asiento Uniformes)
        // Usaré dos módulos para definir asientos
        // Módulo 1: (7.0, 8.4) a (7.8, 7.6)
        // Módulo 2: (7.0, 7.6) a (7.8, 6.8)
        dibujarBloqueSillonPorGeo(
                7.0f, 7.6f,
                7.8f, 8.4f,
                altoAsiento,
                0.72f, 0.70f, 0.66f); // Un poco más claro para diferenciar
        dibujarBloqueSillonPorGeo(
                7.0f, 6.8f,
                7.8f, 7.6f,
                altoAsiento,
                0.70f, 0.68f, 0.63f); // Gris Medio

        // --- RESPALDO L-CONTINUO (Gris Oscuro) ---
        // Dibujar un respaldo L continuo alrededor del exterior
        // Respaldo Brazo Corto (Superior): M2 -> R2
        dibujarRespaldoEntrePuntos(
                6.4f, 9.3f,
                7.8f, 9.3f,
                altoAsiento, // <-- Se pasa el parámetro aquí
                profRespaldo, altoRespaldo,
                0.60f, 0.58f, 0.54f); // Gris Oscuro

        // Respaldo Brazo Largo (Derecho): R2 -> Q2
        dibujarRespaldoEntrePuntos(
                7.8f, 9.3f,
                7.8f, 6.8f,
                altoAsiento, // <-- Se pasa el parámetro aquí
                profRespaldo, altoRespaldo,
                0.60f, 0.58f, 0.54f); // Gris Oscuro

        // --- ALMOHADAS (Gris y Beige Decorativas) ---

        // Almohadas Decorativas (Pequeñas, Beige Claro)
        // Una en la esquina interior
        dibujarCojin(7.8f, 8.4f, 0f);
        // Una en el extremo inferior
        dibujarCojin(7.8f, 6.8f, 0f);

        // Almohadas de Respaldo Principales (Gris Medio)
        // Para el brazo largo (X=7.8), almohadas modulares
        dibujarCojinRespaldo(7.8f, 8.0f);
        dibujarCojinRespaldo(7.8f, 7.2f);

        // Para la tumbona (Z=9.3), una gran almohada de respaldo
        dibujarCojinRespaldoGrande(7.1f, 9.3f);

        glPopMatrix();
    }

    private static void dibujarModuloSillonEntrePuntos(
            float x1Geo,
            float z1Geo,
            float x2Geo,
            float z2Geo,
            float anchoGeo) {

        float centroXGeo = (x1Geo + x2Geo) / 2.0f;
        float centroZGeo = (z1Geo + z2Geo) / 2.0f;

        float dxGeo = x2Geo - x1Geo;
        float dzGeo = z2Geo - z1Geo;

        float largoGeo = (float) Math.sqrt(dxGeo * dxGeo + dzGeo * dzGeo);

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float largo = escalar(largoGeo);
        float ancho = escalar(anchoGeo);

        float angulo = (float) Math.toDegrees(Math.atan2(dzGeo, dxGeo));

        glPushMatrix();

        glTranslatef(x, Y, z);

        /*
         * La casa está volteada en espejo en X.
         * Por eso el ángulo se invierte.
         */
        glRotatef(-angulo, 0f, 1f, 0f);

        // Asiento del módulo
        Cubo.dibujar(
                0f,
                0.35f,
                0f,
                largo,
                0.35f,
                ancho,
                0.70f,
                0.68f,
                0.63f);

        // Respaldo del módulo
        Cubo.dibujar(
                0f,
                0.78f,
                -ancho / 2f + 0.08f,
                largo,
                0.70f,
                0.14f,
                0.60f,
                0.58f,
                0.54f);

        glPopMatrix();
    }

    // Métodos auxiliares específicos para la estructura simplificada
    private static void dibujarBloqueSillonPorGeo(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float altoAsiento,
            float r, float g, float b) {

        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo;
        float profGeo = zMaxGeo - zMinGeo;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        Cubo.dibujar(
                x, altoAsiento, z,
                escalar(anchoGeo), altoAsiento, escalar(profGeo),
                r, g, b);
    }

    private static void dibujarRespaldoEntrePuntos(
            float x1Geo, float z1Geo,
            float x2Geo, float z2Geo,
            float altoAsiento, // <-- Se añade el parámetro aquí
            float profRespaldo, float altoRespaldo,
            float r, float g, float b) {

        float centroXGeo = (x1Geo + x2Geo) / 2.0f;
        float centroZGeo = (z1Geo + z2Geo) / 2.0f;

        float dxGeo = x2Geo - x1Geo;
        float dzGeo = z2Geo - z1Geo;

        float largoGeo = (float) Math.sqrt(dxGeo * dxGeo + dzGeo * dzGeo);

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float largo = escalar(largoGeo);
        float angulo = (float) Math.toDegrees(Math.atan2(dzGeo, dxGeo));

        glPushMatrix();
        glTranslatef(x, Y + altoAsiento + altoRespaldo / 2f, z); // Ahora reconoce altoAsiento
        glRotatef(-angulo, 0f, 1f, 0f);

        Cubo.dibujar(
                0f, 0f, 0f,
                largo, altoRespaldo, profRespaldo,
                r, g, b);
        glPopMatrix();
    }

    private static void dibujarCojin(float xGeo, float zGeo, float rotacionY) {
        // Almohadas decorativas beige claras
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();

        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        Cubo.dibujar(
                0f,
                0.82f,
                0f,
                escalar(0.28f),
                0.16f,
                escalar(0.18f),
                0.85f, 0.85f, 0.80f); // Beige claro

        glPopMatrix();
    }

    private static void dibujarCojinRespaldo(float xGeo, float zGeo) {
        // Almohadas de respaldo modulares grises
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        Cubo.dibujar(
                0f,
                0.85f, // Ligeramente más alto
                0f,
                escalar(0.25f), // Más ancho en geogebra
                0.20f, // Más grueso
                escalar(0.25f), // Más alto en geogebra
                0.70f, 0.68f, 0.63f); // Gris Medio
        glPopMatrix();
    }

    private static void dibujarCojinRespaldoGrande(float xGeo, float zGeo) {
        // Almohada de respaldo grande para tumbona
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        Cubo.dibujar(
                0f,
                0.90f, // Mucho más alto
                0f,
                escalar(0.40f), // Mucho más ancho en geogebra
                0.30f, // Mucho más grueso
                escalar(0.40f), // Mucho más alto en geogebra
                0.70f, 0.68f, 0.63f); // Gris Medio
        glPopMatrix();
    }

    private static void dibujarSillonCortoHorizontal(
            float xGeo,
            float zGeo,
            float largoGeo,
            float profundidadGeo,
            float rotacionY) {

        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        float largo = escalar(largoGeo);
        float profundidad = escalar(profundidadGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Asiento
        Cubo.dibujar(
                0f,
                0.35f,
                0f,
                largo,
                0.35f,
                profundidad,
                0.72f,
                0.70f,
                0.66f);

        // Respaldo
        Cubo.dibujar(
                0f,
                0.80f,
                -profundidad / 2f + 0.08f,
                largo,
                0.80f,
                0.16f,
                0.62f,
                0.60f,
                0.56f);

        // Brazo izquierdo
        Cubo.dibujar(
                -largo / 2f,
                0.55f,
                0f,
                0.14f,
                0.55f,
                profundidad,
                0.62f,
                0.60f,
                0.56f);

        // Brazo derecho
        Cubo.dibujar(
                largo / 2f,
                0.55f,
                0f,
                0.14f,
                0.55f,
                profundidad,
                0.62f,
                0.60f,
                0.56f);

        glPopMatrix();
    }

    private static void dibujarSillonIndividual(
            float xGeo,
            float zGeo,
            float rotacionY) {

        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        float ancho = escalar(0.60f);
        float fondo = escalar(0.60f);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Asiento
        Cubo.dibujar(
                0f,
                0.35f,
                0f,
                ancho,
                0.35f,
                fondo,
                0.68f,
                0.66f,
                0.62f);

        // Respaldo
        Cubo.dibujar(
                0f,
                0.78f,
                -fondo / 2f + 0.08f,
                ancho,
                0.70f,
                0.14f,
                0.58f,
                0.56f,
                0.52f);

        // Brazo izquierdo
        Cubo.dibujar(
                -ancho / 2f,
                0.52f,
                0f,
                0.12f,
                0.50f,
                fondo,
                0.58f,
                0.56f,
                0.52f);

        // Brazo derecho
        Cubo.dibujar(
                ancho / 2f,
                0.52f,
                0f,
                0.12f,
                0.50f,
                fondo,
                0.58f,
                0.56f,
                0.52f);

        glPopMatrix();
    }

    private static void dibujarMesaRectangularCentro() {
        // Puntos G3=(5.9, 7.7) , H3=(5.9, 6.8) , I3=(6.6, 6.8) , J3=(6.6, 7.7)
        float centroXGeo = 6.25f; // (5.9 + 6.6) / 2
        float centroZGeo = 7.25f; // (6.8 + 7.7) / 2

        float anchoGeo = 0.7f; // Eje X (6.6 - 5.9)
        float largoGeo = 0.9f; // Eje Z (7.7 - 6.8)

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        float altoMesa = 0.40f;
        float grosorTabla = 0.05f;

        glPushMatrix();
        glTranslatef(x, Y, z);

        // 1. Superficie de la mesa (Color Madera Clara / Beige)
        Cubo.dibujar(
                0f,
                altoMesa - (grosorTabla / 2f),
                0f,
                ancho,
                grosorTabla,
                largo,
                0.80f, 0.72f, 0.62f);

        // 2. Patas de la mesa (Estilo bloque en los extremos o 4 patitas finas)
        // Pata delantera izquierda
        Cubo.dibujar(-ancho / 2f + 0.04f, altoMesa / 2f, -largo / 2f + 0.04f, 0.05f, altoMesa, 0.05f, 0.3f, 0.3f, 0.3f);
        // Pata delantera derecha
        Cubo.dibujar(ancho / 2f - 0.04f, altoMesa / 2f, -largo / 2f + 0.04f, 0.05f, altoMesa, 0.05f, 0.3f, 0.3f, 0.3f);
        // Pata trasera izquierda
        Cubo.dibujar(-ancho / 2f + 0.04f, altoMesa / 2f, largo / 2f - 0.04f, 0.05f, altoMesa, 0.05f, 0.3f, 0.3f, 0.3f);
        // Pata trasera derecha
        Cubo.dibujar(ancho / 2f - 0.04f, altoMesa / 2f, largo / 2f - 0.04f, 0.05f, altoMesa, 0.05f, 0.3f, 0.3f, 0.3f);

        glPopMatrix();
    }

    private static void dibujarCilindro(float radioBase, float radioTope, float altura, int lados) {
        // Dibujar el cuerpo del cilindro
        glBegin(GL_QUAD_STRIP);
        for (int i = 0; i <= lados; i++) {
            float angulo = (float) (2.0 * Math.PI * i / lados);
            float x = (float) Math.cos(angulo);
            float y = (float) Math.sin(angulo);
            
            // Vértice base
            glVertex3f(x * radioBase, y * radioBase, 0f);
            // Vértice tope
            glVertex3f(x * radioTope, y * radioTope, altura);
        }
        glEnd();
    }

    private static void dibujarDisco(float radioInterior, float radioExterior, int lados) {
        if (radioInterior == 0f) {
            glBegin(GL_TRIANGLE_FAN);
            glVertex3f(0f, 0f, 0f); // Centro
            for (int i = 0; i <= lados; i++) {
                float angulo = (float) (2.0 * Math.PI * i / lados);
                float x = (float) Math.cos(angulo);
                float y = (float) Math.sin(angulo);
                glVertex3f(x * radioExterior, y * radioExterior, 0f);
            }
            glEnd();
        } else {
            glBegin(GL_QUAD_STRIP);
            for (int i = 0; i <= lados; i++) {
                float angulo = (float) (2.0 * Math.PI * i / lados);
                float x = (float) Math.cos(angulo);
                float y = (float) Math.sin(angulo);
                
                glVertex3f(x * radioInterior, y * radioInterior, 0f);
                glVertex3f(x * radioExterior, y * radioExterior, 0f);
            }
            glEnd();
        }
    }

    private static void dibujarMesaCircular() {
        // Puntos K3=(4.8, 6.3) a N3=(5.4, 6.9). Caja de 0.6 x 0.6
        float centroXGeo = 5.1f; // (4.8 + 5.4) / 2
        float centroZGeo = 6.6f; // (6.3 + 6.9) / 2

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        // Diámetro es 0.6 en geogebra, el radio para OpenGL es la mitad escalado
        float radio = escalar(0.6f) / 2f;
        float altoMesa = 0.55f; // Un poco más alta por ser lateral/esquina
        float grosorTabla = 0.04f;

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Rotamos en X para que los discos y cilindros miren hacia arriba (eje Y)
        glPushMatrix();
        glRotatef(-90f, 1f, 0f, 0f);

        // --- 1. Base Central (Pata única cilíndrica de metal) ---
        glColor3f(0.2f, 0.2f, 0.2f); // Gris oscuro/metalizado
        dibujarCilindro(radio * 0.15f, radio * 0.15f, altoMesa - grosorTabla, 16);

        // Soporte del suelo (un pequeño disco en la base)
        dibujarDisco(0f, radio * 0.6f, 16);

        // --- 2. Tapa de la Mesa Redonda ---
        // Nos movemos hacia arriba en la altura local para poner la tapa
        glTranslatef(0f, 0f, altoMesa - grosorTabla);

        // Color de la tapa (Madera oscura o cristal)
        glColor3f(0.4f, 0.25f, 0.15f);

        // Borde de la mesa (cilindro muy corto para darle grosor 3D)
        dibujarCilindro(radio, radio, grosorTabla, 32);

        // Tapa superior del círculo
        glTranslatef(0f, 0f, grosorTabla);
        dibujarDisco(0f, radio, 32);

        glPopMatrix(); // Salir de la rotación
        glPopMatrix(); // Salir de la posición general
    }
}