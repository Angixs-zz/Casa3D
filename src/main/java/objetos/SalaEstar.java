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

    public static void dibujar(int texturaSillonGrisSala, int texturaAlmohadaSala, int texturaSillonIndividual, int texturaMuebleTV, int texturaAdornoSala, int texturaAdorno2, int texturaChimenea, int texturaRepisa) {
        
        // =====================================================
        // SALA DE ESTAR - PISO 1
        // =====================================================
        
        // Grupo grande: M2, N2, O2, P2, Q2, R2
        // Este sillón sigue la forma real de los segmentos.
        dibujarSillonSeccionalGrande(texturaSillonGrisSala, texturaAlmohadaSala);

        // Grupo rectangular: S2, T2, U2, V2
        dibujarSillonCortoHorizontal(
                5.55f,
                8.95f,
                0.75f,
                0.45f,
                180f,
                texturaSillonGrisSala);

        // Grupo rombo: W2, Z2, A3, B3
        dibujarSillonIndividual(
                7.2f,
                6.0f,
                45f,
                texturaSillonIndividual);

        dibujarMuebleYTV(texturaMuebleTV, texturaAdornoSala, texturaAdorno2, texturaChimenea);

        // ==== NUEVAS MESAS / SILLONES ====
        dibujarMesaRectangularCentro(texturaRepisa);
        dibujarSillonCircular(texturaSillonGrisSala);
    }

    private static void dibujarMuebleYTV(int texturaMuebleTV, int texturaAdornoSala, int texturaAdorno2, int texturaChimenea) {
        // Coordenadas calculadas a partir de C3, D3, E3, F3
        float centroXGeo = 4.1f;
        float centroZGeo = 7.75f;

        float anchoGeo = 0.4f; // Eje X (4.3 - 3.9)
        float largoGeo = 2.3f; // Eje Z (8.9 - 6.6)

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);

        // 1. BASE DEL MUEBLE (Contiene la chimenea)
        float altoBase = 0.60f;
        // Mueble principal (madera oscura)
        Cubo.dibujarConTextura(0f, altoBase / 2f, 0f, ancho, altoBase, largo, texturaMuebleTV);
        
        // Hueco de la chimenea
        float largoChimenea = escalar(1.0f);
        float altoChimenea = 0.40f;
        float profChimenea = ancho + 0.02f; // Sobresale un poco para evitar z-fighting
        
        // Marco de la chimenea (interior negro)
        Cubo.dibujar(0f, altoChimenea / 2f + 0.05f, 0f, profChimenea, altoChimenea, largoChimenea, 0.1f, 0.1f, 0.1f);
        
        // Imagen de la chimenea (textura)
        Cubo.dibujarConTextura(0f, altoChimenea / 2f + 0.05f, 0f, profChimenea + 0.01f, altoChimenea * 0.9f, largoChimenea * 0.9f, texturaChimenea);

        // 2. SECCIÓN MEDIA (TV y estantes laterales)
        float altoPilares = 1.20f;
        float yMedio = altoBase + altoPilares / 2f;
        float anchoPilar = escalar(0.45f); // Un poco más de espacio para los adornos
        float grosorTablas = 0.04f;
        
        // Estantes laterales (Izquierdo)
        Cubo.dibujarConTextura(0f, yMedio, -largo / 2f + grosorTablas / 2f, ancho, altoPilares, grosorTablas, texturaMuebleTV); // Pared exterior izq
        Cubo.dibujarConTextura(0f, yMedio, -largo / 2f + anchoPilar - grosorTablas / 2f, ancho, altoPilares, grosorTablas, texturaMuebleTV); // Pared interior izq
        
        float yRepisa1 = altoBase + altoPilares * 0.33f;
        float yRepisa2 = altoBase + altoPilares * 0.66f;
        Cubo.dibujarConTextura(0f, yRepisa1, -largo / 2f + anchoPilar / 2f, ancho, grosorTablas, anchoPilar - grosorTablas * 2f, texturaMuebleTV);
        Cubo.dibujarConTextura(0f, yRepisa2, -largo / 2f + anchoPilar / 2f, ancho, grosorTablas, anchoPilar - grosorTablas * 2f, texturaMuebleTV);

        // Estantes laterales (Derecho)
        Cubo.dibujarConTextura(0f, yMedio, largo / 2f - grosorTablas / 2f, ancho, altoPilares, grosorTablas, texturaMuebleTV); // Pared exterior der
        Cubo.dibujarConTextura(0f, yMedio, largo / 2f - anchoPilar + grosorTablas / 2f, ancho, altoPilares, grosorTablas, texturaMuebleTV); // Pared interior der
        
        Cubo.dibujarConTextura(0f, yRepisa1, largo / 2f - anchoPilar / 2f, ancho, grosorTablas, anchoPilar - grosorTablas * 2f, texturaMuebleTV);
        Cubo.dibujarConTextura(0f, yRepisa2, largo / 2f - anchoPilar / 2f, ancho, grosorTablas, anchoPilar - grosorTablas * 2f, texturaMuebleTV);

        // ADORNOS Y FLOREROS EN LOS ESTANTES
        float zCentroIzq = -largo / 2f + anchoPilar / 2f;
        float zCentroDer = largo / 2f - anchoPilar / 2f;
        
        // Adorno en repisa izq (nivel bajo) - Florero texturizado 2
        Cubo.dibujarConTextura(0f, altoBase + 0.10f, zCentroIzq, ancho * 0.4f, 0.20f, ancho * 0.4f, texturaAdorno2);
        // Adorno en repisa izq (nivel medio) - Escultura texturizada 2
        Cubo.dibujarConTextura(0f, yRepisa1 + 0.08f, zCentroIzq, ancho * 0.3f, 0.16f, ancho * 0.3f, texturaAdorno2);
        // Adorno en repisa izq (nivel alto) - Caja decorativa azul/textura
        Cubo.dibujarConTextura(0f, yRepisa2 + 0.06f, zCentroIzq, ancho * 0.6f, 0.12f, anchoPilar * 0.5f, texturaAdornoSala);

        // Adorno en repisa der (nivel bajo) - Maceta con planta
        Cubo.dibujarConTextura(0f, altoBase + 0.08f, zCentroDer, ancho * 0.5f, 0.16f, ancho * 0.5f, texturaAdornoSala); // Maceta
        Cubo.dibujar(0f, altoBase + 0.20f, zCentroDer, ancho * 0.4f, 0.10f, ancho * 0.4f, 0.1f, 0.7f, 0.2f); // Planta
        // Adorno en repisa der (nivel medio) - Libros apilados horizontales
        Cubo.dibujar(0f, yRepisa1 + 0.03f, zCentroDer, ancho * 0.6f, 0.06f, anchoPilar * 0.6f, 0.8f, 0.3f, 0.3f);
        Cubo.dibujar(0f, yRepisa1 + 0.08f, zCentroDer, ancho * 0.5f, 0.05f, anchoPilar * 0.5f, 0.4f, 0.4f, 0.4f);
        // Adorno en repisa der (nivel alto) - Florero texturizado
        Cubo.dibujarConTextura(0f, yRepisa2 + 0.12f, zCentroDer, ancho * 0.3f, 0.24f, ancho * 0.3f, texturaAdornoSala);
        
        // Panel trasero para la TV (Centrado)
        float largoPanel = largo - (anchoPilar * 2f);
        float grosorPanel = escalar(0.05f);
        Cubo.dibujarConTextura(0f, yMedio, 0f, grosorPanel, altoPilares, largoPanel, texturaMuebleTV);

        // Pantalla de TV
        float altoTV = 0.90f;
        float largoTV = largoPanel * 0.9f;
        float grosorTV = escalar(0.08f); // Un poco más grueso que el panel
        Cubo.dibujar(0f, altoBase + (altoTV / 2f) + 0.15f, 0f, grosorTV, altoTV, largoTV, 0.05f, 0.05f, 0.05f);

        // 3. SECCIÓN SUPERIOR (Librería)
        float altoLibreria = 0.60f;
        float yLibreria = altoBase + altoPilares + altoLibreria / 2f;
        
        // Repisa superior e inferior de la librería
        Cubo.dibujarConTextura(0f, altoBase + altoPilares + 0.025f, 0f, ancho, 0.05f, largo, texturaMuebleTV);
        Cubo.dibujarConTextura(0f, altoBase + altoPilares + altoLibreria - 0.025f, 0f, ancho, 0.05f, largo, texturaMuebleTV);
        
        // Divisores de la librería
        Cubo.dibujarConTextura(0f, yLibreria, -largo / 2f + 0.025f, ancho, altoLibreria, 0.05f, texturaMuebleTV); // Borde izq
        Cubo.dibujarConTextura(0f, yLibreria, largo / 2f - 0.025f, ancho, altoLibreria, 0.05f, texturaMuebleTV);  // Borde der
        Cubo.dibujarConTextura(0f, yLibreria, -largo / 6f, ancho, altoLibreria, 0.05f, texturaMuebleTV); // Divisor 1
        Cubo.dibujarConTextura(0f, yLibreria, largo / 6f, ancho, altoLibreria, 0.05f, texturaMuebleTV);  // Divisor 2

        // Libros (simulados con cubos de colores en la librería)
        float altoLibro = 0.25f;
        float grosorLibro = escalar(0.15f);
        float profLibro = ancho * 0.8f;
        
        // Grupo de libros 1
        Cubo.dibujar(0f, altoBase + altoPilares + 0.05f + altoLibro / 2f, -largo / 3f, profLibro, altoLibro, grosorLibro, 0.8f, 0.2f, 0.2f);
        Cubo.dibujar(0f, altoBase + altoPilares + 0.05f + altoLibro * 0.8f / 2f, -largo / 3f + grosorLibro, profLibro, altoLibro * 0.8f, grosorLibro * 0.8f, 0.2f, 0.3f, 0.8f);
        
        // Grupo de libros 2
        Cubo.dibujar(0f, altoBase + altoPilares + 0.05f + altoLibro / 2f, 0f, profLibro, altoLibro, grosorLibro * 1.5f, 0.2f, 0.7f, 0.3f);
        
        // Grupo de libros 3
        Cubo.dibujar(0f, altoBase + altoPilares + 0.05f + altoLibro * 0.9f / 2f, largo / 3f, profLibro, altoLibro * 0.9f, grosorLibro * 0.5f, 0.7f, 0.7f, 0.2f);

        glPopMatrix();
    }

    private static void dibujarSillonSeccionalGrande(int texturaID, int texturaAlmohadaSala) {
        float altoAsiento = 0.35f;
        float altoRespaldo = 0.65f; // Respaldo más alto, a petición del usuario
        float profRespaldo = 0.15f;

        glPushMatrix();

        // --- 1. BASE DEL ASIENTO ---
        // Tumbona (Izquierda): X=[6.4, 7.0], Z=[8.4, 9.3]
        dibujarBloqueSillonPorGeoConTextura(
                6.4f, 8.4f,
                7.0f, 9.3f,
                altoAsiento,
                texturaID);

        // Esquina y Brazo Largo (Derecha): X=[7.0, 7.8], Z=[6.8, 9.3]
        dibujarBloqueSillonPorGeoConTextura(
                7.0f, 6.8f,
                7.8f, 9.3f,
                altoAsiento,
                texturaID);

        // --- 2. RESPALDOS (Alineados al ras, sin sobresalir de la base) ---
        // Respaldo Tumbona (Superior): Centro Z = 9.3 - 0.075 = 9.225
        dibujarRespaldoEntrePuntosConTextura(
                6.4f, 9.225f,
                7.8f - profRespaldo, 9.225f, // Cortar antes de la esquina para no cruzarse
                altoAsiento,
                profRespaldo, altoRespaldo,
                texturaID);

        // Respaldo Brazo Largo (Derecho): Centro X = 7.8 - 0.075 = 7.725
        dibujarRespaldoEntrePuntosConTextura(
                7.725f, 9.3f,
                7.725f, 6.8f,
                altoAsiento,
                profRespaldo, altoRespaldo,
                texturaID);

        // --- 3. COJINES DE RESPALDO (Inclinados y realistas) ---
        // Brazo largo (X=7.58, recargados contra el respaldo en X=7.725)
        dibujarCojinInclinado(7.58f, 7.1f, 90f, -12f, texturaID);
        dibujarCojinInclinado(7.58f, 7.6f, 90f, -12f, texturaID);
        dibujarCojinInclinado(7.58f, 8.1f, 90f, -12f, texturaID);

        // Tumbona (Z=9.08, recargado contra el respaldo en Z=9.225)
        dibujarCojinInclinado(6.7f, 9.08f, 0f, 12f, texturaID);

        // --- 4. ALMOHADAS DECORATIVAS (Pequeñas, con Textura ALMOHADASALA) ---
        // Esquina interior
        dibujarCojin(7.4f, 8.8f, -45f, texturaAlmohadaSala);
        // Extremo inferior
        dibujarCojin(7.45f, 6.9f, -20f, texturaAlmohadaSala);

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
                x, altoAsiento / 2f, z, // Centro Y es la mitad de la altura para que apoye en el piso
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

    private static void dibujarCojin(float xGeo, float zGeo, float rotacionY, int texturaID) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();

        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        Cubo.dibujarConTextura(
                0f,
                0.35f + 0.08f, // Apoyado perfectamente sobre el asiento de 0.35 (0.35 + 0.16/2)
                0f,
                escalar(0.24f), // Más cuadrado
                0.16f,
                escalar(0.24f), // Más cuadrado
                texturaID);

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

    private static void dibujarBloqueSillonPorGeoConTextura(
            float xMinGeo, float zMinGeo,
            float xMaxGeo, float zMaxGeo,
            float altoAsiento,
            int texturaID) {

        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo;
        float profGeo = zMaxGeo - zMinGeo;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        Cubo.dibujarConTextura(
                x, altoAsiento / 2f, z, // Centro Y es la mitad de la altura
                escalar(anchoGeo), altoAsiento, escalar(profGeo),
                texturaID);
    }

    private static void dibujarRespaldoEntrePuntosConTextura(
            float x1Geo, float z1Geo,
            float x2Geo, float z2Geo,
            float altoAsiento,
            float profRespaldo, float altoRespaldo,
            int texturaID) {

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
        glTranslatef(x, Y + altoAsiento + altoRespaldo / 2f, z);
        glRotatef(-angulo, 0f, 1f, 0f);

        Cubo.dibujarConTextura(
                0f, 0f, 0f,
                largo, altoRespaldo, profRespaldo,
                texturaID);
        glPopMatrix();
    }

    private static void dibujarCojinInclinado(float xGeo, float zGeo, float rotacionY, float inclinacionX, int texturaID) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y + 0.55f, z); // Centro Y descansa perfectamente sobre el asiento (0.35 + 0.40/2)
        
        glRotatef(rotacionY, 0f, 1f, 0f);
        // Inclinación hacia atrás para apoyarse de forma realista en el respaldo
        glRotatef(inclinacionX, 1f, 0f, 0f); 

        Cubo.dibujarConTextura(
                0f, 0f, 0f,
                escalar(0.50f), // Ancho de cojín realista
                0.40f,          // Alto
                escalar(0.12f), // Grosor delgado y elegante
                texturaID);
        glPopMatrix();
    }

    private static void dibujarSillonCortoHorizontal(
            float xGeo,
            float zGeo,
            float largoGeo,
            float profundidadGeo,
            float rotacionY,
            int texturaID) {

        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        float largo = escalar(largoGeo);
        float profundidad = escalar(profundidadGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Asiento (anclado al piso)
        Cubo.dibujarConTextura(
                0f,
                0.35f / 2f, // Mitad de la altura
                0f,
                largo,
                0.35f,
                profundidad,
                texturaID);

        // Respaldo (Desde el piso hasta 0.85)
        Cubo.dibujarConTextura(
                0f,
                0.85f / 2f, 
                -profundidad / 2f + 0.08f,
                largo,
                0.85f, // Más alto
                0.16f,
                texturaID);

        // Brazo izquierdo (Desde el piso hasta 0.55)
        Cubo.dibujarConTextura(
                -largo / 2f,
                0.55f / 2f,
                0f,
                0.14f,
                0.55f,
                profundidad,
                texturaID);

        // Brazo derecho (Desde el piso hasta 0.55)
        Cubo.dibujarConTextura(
                largo / 2f,
                0.55f / 2f,
                0f,
                0.14f,
                0.55f,
                profundidad,
                texturaID);

        glPopMatrix();
    }

    private static void dibujarSillonIndividual(
            float xGeo,
            float zGeo,
            float rotacionY,
            int texturaID) {

        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        float ancho = escalar(0.60f);
        float fondo = escalar(0.60f);

        glPushMatrix();
        glTranslatef(x, Y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Asiento
        Cubo.dibujarConTextura(
                0f,
                0.35f / 2f,
                0f,
                ancho,
                0.35f,
                fondo,
                texturaID);

        // Respaldo (Desde el piso hasta 0.85)
        Cubo.dibujarConTextura(
                0f,
                0.85f / 2f,
                -fondo / 2f + 0.08f,
                ancho,
                0.85f,
                0.14f,
                texturaID);

        // Brazo izquierdo
        Cubo.dibujarConTextura(
                -ancho / 2f,
                0.55f / 2f,
                0f,
                0.12f,
                0.55f,
                fondo,
                texturaID);

        // Brazo derecho
        Cubo.dibujarConTextura(
                ancho / 2f,
                0.55f / 2f,
                0f,
                0.12f,
                0.55f,
                fondo,
                texturaID);

        glPopMatrix();
    }

    private static void dibujarMesaRectangularCentro(int texturaRepisa) {
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

        // 1. Superficie de la mesa (Textura de repisa de oficina)
        Cubo.dibujarConTextura(
                0f,
                altoMesa - (grosorTabla / 2f),
                0f,
                ancho,
                grosorTabla,
                largo,
                texturaRepisa);

        // 2. Patas de la mesa (Estilo bloque en los extremos o 4 patitas finas)
        // Pata delantera izquierda
        Cubo.dibujarConTextura(-ancho / 2f + 0.04f, altoMesa / 2f, -largo / 2f + 0.04f, 0.05f, altoMesa, 0.05f, texturaRepisa);
        // Pata delantera derecha
        Cubo.dibujarConTextura(ancho / 2f - 0.04f, altoMesa / 2f, -largo / 2f + 0.04f, 0.05f, altoMesa, 0.05f, texturaRepisa);
        // Pata trasera izquierda
        Cubo.dibujarConTextura(-ancho / 2f + 0.04f, altoMesa / 2f, largo / 2f - 0.04f, 0.05f, altoMesa, 0.05f, texturaRepisa);
        // Pata trasera derecha
        Cubo.dibujarConTextura(ancho / 2f - 0.04f, altoMesa / 2f, largo / 2f - 0.04f, 0.05f, altoMesa, 0.05f, texturaRepisa);

        glPopMatrix();
    }

    private static void dibujarCilindro(float radioBase, float radioTope, float altura, int lados) {
        // Dibujar el cuerpo del cilindro sin textura
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

    private static void dibujarCilindroConTextura(float radioBase, float radioTope, float altura, int lados, int texturaID) {
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, texturaID);
        glBegin(GL_QUAD_STRIP);
        for (int i = 0; i <= lados; i++) {
            float angulo = (float) (2.0 * Math.PI * i / lados);
            float x = (float) Math.cos(angulo);
            float y = (float) Math.sin(angulo);
            float u = (float) i / lados;
            
            glTexCoord2f(u, 0f);
            glVertex3f(x * radioBase, y * radioBase, 0f);
            
            glTexCoord2f(u, 1f);
            glVertex3f(x * radioTope, y * radioTope, altura);
        }
        glEnd();
        glBindTexture(GL_TEXTURE_2D, 0);
        glDisable(GL_TEXTURE_2D);
    }

    private static void dibujarDiscoConTextura(float radio, int lados, int texturaID) {
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, texturaID);
        glBegin(GL_TRIANGLE_FAN);
        glTexCoord2f(0.5f, 0.5f);
        glVertex3f(0f, 0f, 0f); // Centro
        for (int i = 0; i <= lados; i++) {
            float angulo = (float) (2.0 * Math.PI * i / lados);
            float x = (float) Math.cos(angulo);
            float y = (float) Math.sin(angulo);
            
            glTexCoord2f(0.5f + x * 0.5f, 0.5f + y * 0.5f);
            glVertex3f(x * radio, y * radio, 0f);
        }
        glEnd();
        glBindTexture(GL_TEXTURE_2D, 0);
        glDisable(GL_TEXTURE_2D);
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

    private static void dibujarSillonCircular(int texturaSillon) {
        // Se mueve ligeramente hacia el mueble de la chimenea (X:4.1, Z:7.75)
        float centroXGeo = 4.8f; 
        float centroZGeo = 7.0f;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        // Hacemos el sillón más pequeño para que no choque con la puerta
        float radio = escalar(0.40f) / 2f;
        float altoAsiento = 0.40f; 
        float altoBase = 0.05f;

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Rotamos en X para que los discos y cilindros miren hacia arriba (eje Y)
        glPushMatrix();
        glRotatef(-90f, 1f, 0f, 0f);

        // --- 1. Base (Color oscuro) ---
        glColor3f(0.1f, 0.1f, 0.1f);
        dibujarCilindro(radio * 0.95f, radio * 0.95f, altoBase, 32);
        dibujarDisco(0f, radio * 0.95f, 32);

        // --- 2. Cojín del sillón ---
        glTranslatef(0f, 0f, altoBase);
        
        // Restaurar color a blanco puro antes de aplicar textura
        glColor3f(1.0f, 1.0f, 1.0f);
        
        // Borde del sillón
        dibujarCilindroConTextura(radio, radio, altoAsiento - altoBase, 32, texturaSillon);
        
        // Tapa superior del asiento
        glTranslatef(0f, 0f, altoAsiento - altoBase);
        dibujarDiscoConTextura(radio, 32, texturaSillon);

        glPopMatrix(); // Salir de la rotación
        glPopMatrix(); // Salir de la posición general
    }
}