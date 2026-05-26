package objetos;

import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class EstructuraCerrada {

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
         * ESTRUCTURA CERRADA DE MINIMUROS
         *
         * Coordenadas GeoGebra:
         * A9 = (7.0, 1.6)
         * Z7 = (7.8, 1.6)
         * B9 = (7.8, 0.2)
         * Z8 = (7.0, 0.2)
         *
         * Centro: x = 7.4, z = 0.9
         * Ancho X = 0.8
         * Largo Z = 1.4
         */

        float xGeoCentro = 7.4f;
        float zGeoCentro = 0.9f;

        float x = convertirXGeoAOpenGL(xGeoCentro);
        float z = convertirZGeoAOpenGL(zGeoCentro);

        float anchoX = escalar(0.8f);
        float largoZ = escalar(1.4f);
        float grosorMuro = escalar(0.1f);
        float altoMuro = 0.8f; // Altura del minimuro

        glPushMatrix();
        glTranslatef(x, Y, z);

        // Color blanco/gris claro para los minimuros (como las jardineras)
        float r = 0.88f, g = 0.88f, b = 0.84f;

        // Muro Trasero (Z negativo local, cerca de 0.2)
        Cubo.dibujar(
                0f, 
                altoMuro / 2f, 
                -largoZ / 2f + grosorMuro / 2f, 
                anchoX, altoMuro, grosorMuro, 
                r, g, b
        );

        // Muro Frontal (Z positivo local, cerca de 1.6)
        Cubo.dibujar(
                0f, 
                altoMuro / 2f, 
                largoZ / 2f - grosorMuro / 2f, 
                anchoX, altoMuro, grosorMuro, 
                r, g, b
        );

        // Muro Izquierdo (X negativo local, cerca de 7.8 -> recuerden que OpenGL X es negativo para GeoGebra X positivo)
        // Entonces X negativo local corresponde a GeoGebra 7.8 (B9-Z7)
        Cubo.dibujar(
                -anchoX / 2f + grosorMuro / 2f, 
                altoMuro / 2f, 
                0f, 
                grosorMuro, altoMuro, largoZ - grosorMuro * 2f, 
                r, g, b
        );

        // Muro Derecho (X positivo local, corresponde a GeoGebra 7.0 A9-Z8)
        Cubo.dibujar(
                anchoX / 2f - grosorMuro / 2f, 
                altoMuro / 2f, 
                0f, 
                grosorMuro, altoMuro, largoZ - grosorMuro * 2f, 
                r, g, b
        );

        // Decoración superior de los muros (una tapa ligeramente más oscura/diferente)
        float altoTapa = 0.05f;
        float rT = 0.8f, gT = 0.8f, bT = 0.75f;
        
        // Tapa Trasera
        Cubo.dibujar(0f, altoMuro + altoTapa / 2f, -largoZ / 2f + grosorMuro / 2f, anchoX + 0.05f, altoTapa, grosorMuro + 0.05f, rT, gT, bT);
        // Tapa Frontal
        Cubo.dibujar(0f, altoMuro + altoTapa / 2f, largoZ / 2f - grosorMuro / 2f, anchoX + 0.05f, altoTapa, grosorMuro + 0.05f, rT, gT, bT);
        // Tapa Izquierda
        Cubo.dibujar(-anchoX / 2f + grosorMuro / 2f, altoMuro + altoTapa / 2f, 0f, grosorMuro + 0.05f, altoTapa, largoZ - grosorMuro * 2f, rT, gT, bT);
        // Tapa Derecha
        Cubo.dibujar(anchoX / 2f - grosorMuro / 2f, altoMuro + altoTapa / 2f, 0f, grosorMuro + 0.05f, altoTapa, largoZ - grosorMuro * 2f, rT, gT, bT);

        // Relleno / Techo interior para que no se vea hueco (color marrón/tierra como en la imagen)
        float rRelleno = 0.45f, gRelleno = 0.30f, bRelleno = 0.20f;
        Cubo.dibujar(
                0f, 
                altoMuro - 0.05f, // Justo debajo de la tapa
                0f, 
                anchoX - grosorMuro * 2f, 
                0.1f, 
                largoZ - grosorMuro * 2f, 
                rRelleno, gRelleno, bRelleno
        );

        glPopMatrix();
    }
}
