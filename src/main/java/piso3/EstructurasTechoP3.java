package piso3;

import objetos.Cubo;
import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class EstructurasTechoP3 {

    private static final float Y_PISO3 = Constantes.ALTURA_PISO_3; // 6.4f
    private static final float Y_TECHO = 9.6f; // Techo sobre comedor (C,F,G,E)

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
        // 1. Muros diagonales huecos (N, O, F, C)
        dibujarMurosDiagonales();

        // 2. Tinaco sobre techo (C, F, G, E)
        dibujarTinaco(6.4f, 10.7f); // Centro aproximado del techo
    }

    // =======================================================
    // MUROS DIAGONALES (Aletas/Pérgola inclinada)
    // =======================================================
    private static void dibujarMurosDiagonales() {
        /*
         * Aletas que van a lo largo del eje X (de 4.9 a 7.9).
         * Se distribuyen a lo largo del eje Z (de 12.0 a 13.9).
         * Empiezan altas en N-C (X=4.9) y terminan bajas en O-F (X=7.9).
         */

        float xIzquierdaGeo = 4.9f;
        float xDerechaGeo = 7.9f;
        
        float zMinGeo = 12.0f;
        float zMaxGeo = 13.9f;

        // 4 Muros espaciados
        float numMuros = 4;
        float pasoZ = (zMaxGeo - zMinGeo) / (numMuros - 1);

        for (int i = 0; i < numMuros; i++) {
            float zGeo = zMinGeo + i * pasoZ;
            // La última aleta (N->O, Z=13.9) será sólida para tapar el fondo
            boolean esHueca = (i != numMuros - 1); 
            dibujarAletaDiagonal(zGeo, xIzquierdaGeo, xDerechaGeo, esHueca);
        }
    }

    private static void dibujarAletaDiagonal(float zGeo, float xIzquierdaGeo, float xDerechaGeo, boolean esHueca) {
        float zCentro = convertirZGeoAOpenGL(zGeo);
        float xIzq = convertirXGeoAOpenGL(xIzquierdaGeo); // -1.8 aprox
        float xDer = convertirXGeoAOpenGL(xDerechaGeo);   // -7.8 aprox

        float grosorGeo = 0.15f; 
        float grosor = escalar(grosorGeo);

        float yBase = Y_PISO3;
        float altoIzq = 1.0f; // Parte chica en N-C (X=4.9)
        float altoDer = 3.2f; // Parte grande en O-F (X=7.9)

        float yTopIzq = yBase + altoIzq;
        float yTopDer = yBase + altoDer;

        // Márgenes del hueco
        float mX = escalar(0.4f); // Ancho de los pilares laterales
        float mYBottom = 0.6f; // Altura de la base bajo el hueco
        float mYTop = 0.4f;    // Grosor de la viga superior

        // Como xDer < xIzq en OpenGL (por el escalado negativo), xIzq es el valor mayor.
        // Pilar izquierdo (cerca de X=4.9, que es xIzq)
        float xPilarIzqStart = xIzq;
        float xPilarIzqEnd = xIzq - mX; // Restamos porque xDer es menor
        
        // Pilar derecho (cerca de X=7.9, que es xDer)
        float xPilarDerStart = xDer + mX;
        float xPilarDerEnd = xDer;

        // Función para calcular Y del techo en cualquier X
        // slope = (yTopDer - yTopIzq) / (xDer - xIzq)
        float slope = (yTopDer - yTopIzq) / (xDer - xIzq);

        glPushMatrix();
        glTranslatef(0, 0, zCentro);

        // Color blanco para el muro
        glColor3f(0.88f, 0.88f, 0.88f);

        if (!esHueca) {
            // Muro sólido sin hueco en el centro
            dibujarBloqueIrregular(xDer, xIzq, yBase, yBase, yTopDer, yTopIzq, grosor);
            glPopMatrix();
            return;
        }

        // Si es hueca, solo dibujamos la Viga Superior Inclinada (la "hipotenusa")
        // que va de extremo a extremo sin los catetos (pilares o base).
        float yVigaBotLeft = yTopIzq - mYTop; // En X = xIzq
        float yVigaBotRight = yTopDer - mYTop; // En X = xDer
        
        dibujarBloqueIrregular(xDer, xIzq, yVigaBotRight, yVigaBotLeft, yTopDer, yTopIzq, grosor);

        glPopMatrix();
    }

    /**
     * Dibuja un bloque con caras frontales, traseras y laterales.
     * x1 < x2.
     * yBot1 y yTop1 son las alturas en x1.
     * yBot2 y yTop2 son las alturas en x2.
     */
    private static void dibujarBloqueIrregular(float x1, float x2, float yBot1, float yBot2, float yTop1, float yTop2, float grosor) {
        float zFront = grosor / 2;
        float zBack = -grosor / 2;

        // Cara Frontal
        glBegin(GL_POLYGON);
        glVertex3f(x1, yBot1, zFront);
        glVertex3f(x2, yBot2, zFront);
        glVertex3f(x2, yTop2, zFront);
        glVertex3f(x1, yTop1, zFront);
        glEnd();

        // Cara Trasera
        glBegin(GL_POLYGON);
        glVertex3f(x1, yBot1, zBack);
        glVertex3f(x1, yTop1, zBack);
        glVertex3f(x2, yTop2, zBack);
        glVertex3f(x2, yBot2, zBack);
        glEnd();

        // Cara Inferior
        glBegin(GL_POLYGON);
        glVertex3f(x1, yBot1, zFront);
        glVertex3f(x1, yBot1, zBack);
        glVertex3f(x2, yBot2, zBack);
        glVertex3f(x2, yBot2, zFront);
        glEnd();

        // Cara Superior
        glBegin(GL_POLYGON);
        glVertex3f(x1, yTop1, zFront);
        glVertex3f(x2, yTop2, zFront);
        glVertex3f(x2, yTop2, zBack);
        glVertex3f(x1, yTop1, zBack);
        glEnd();

        // Cara Izquierda (en x1)
        glBegin(GL_POLYGON);
        glVertex3f(x1, yBot1, zFront);
        glVertex3f(x1, yTop1, zFront);
        glVertex3f(x1, yTop1, zBack);
        glVertex3f(x1, yBot1, zBack);
        glEnd();

        // Cara Derecha (en x2)
        glBegin(GL_POLYGON);
        glVertex3f(x2, yBot2, zFront);
        glVertex3f(x2, yBot2, zBack);
        glVertex3f(x2, yTop2, zBack);
        glVertex3f(x2, yTop2, zFront);
        glEnd();

        // Bordes (para resaltar la figura)
        glColor3f(0.1f, 0.1f, 0.1f);
        glBegin(GL_LINE_LOOP);
        glVertex3f(x1, yBot1, zFront);
        glVertex3f(x2, yBot2, zFront);
        glVertex3f(x2, yTop2, zFront);
        glVertex3f(x1, yTop1, zFront);
        glEnd();
        glBegin(GL_LINE_LOOP);
        glVertex3f(x1, yBot1, zBack);
        glVertex3f(x2, yBot2, zBack);
        glVertex3f(x2, yTop2, zBack);
        glVertex3f(x1, yTop1, zBack);
        glEnd();
        
        // Volver a color blanco
        glColor3f(0.88f, 0.88f, 0.88f);
    }

    // =======================================================
    // TINACO / TANQUE DE AGUA
    // =======================================================
    private static void dibujarTinaco(float xGeo, float zGeo) {
        float x = convertirXGeoAOpenGL(xGeo);
        float z = convertirZGeoAOpenGL(zGeo);

        glPushMatrix();
        glTranslatef(x, Y_TECHO, z);

        // Estructura metálica base (Torre)
        float baseAncho = escalar(1.2f);
        float baseAlto = 0.6f;
        float grosorPata = 0.05f;

        // 4 patas
        Cubo.dibujar(-baseAncho/2, baseAlto/2, -baseAncho/2, grosorPata, baseAlto, grosorPata, 0.2f, 0.2f, 0.2f);
        Cubo.dibujar(baseAncho/2, baseAlto/2, -baseAncho/2, grosorPata, baseAlto, grosorPata, 0.2f, 0.2f, 0.2f);
        Cubo.dibujar(-baseAncho/2, baseAlto/2, baseAncho/2, grosorPata, baseAlto, grosorPata, 0.2f, 0.2f, 0.2f);
        Cubo.dibujar(baseAncho/2, baseAlto/2, baseAncho/2, grosorPata, baseAlto, grosorPata, 0.2f, 0.2f, 0.2f);

        // Marco superior
        Cubo.dibujar(0f, baseAlto, -baseAncho/2, baseAncho, grosorPata, grosorPata, 0.2f, 0.2f, 0.2f);
        Cubo.dibujar(0f, baseAlto, baseAncho/2, baseAncho, grosorPata, grosorPata, 0.2f, 0.2f, 0.2f);
        Cubo.dibujar(-baseAncho/2, baseAlto, 0f, grosorPata, grosorPata, baseAncho, 0.2f, 0.2f, 0.2f);
        Cubo.dibujar(baseAncho/2, baseAlto, 0f, grosorPata, grosorPata, baseAncho, 0.2f, 0.2f, 0.2f);

        // Plataforma cruzada
        Cubo.dibujar(0f, baseAlto, 0f, baseAncho, 0.02f, baseAncho, 0.25f, 0.25f, 0.25f);

        // Tanque Cilindrico (Simulado con prisma poligonal)
        float radioCilindro = escalar(0.55f);
        float altoCilindro = 1.4f;
        int segmentos = 16;
        float yCilindro = baseAlto;

        glColor3f(0.12f, 0.12f, 0.12f); // Negro/Gris oscuro
        glBegin(GL_QUAD_STRIP);
        for (int i = 0; i <= segmentos; i++) {
            double angulo = 2 * Math.PI * i / segmentos;
            float cx = (float) (Math.cos(angulo) * radioCilindro);
            float cz = (float) (Math.sin(angulo) * radioCilindro);
            
            glVertex3f(cx, yCilindro, cz);
            glVertex3f(cx, yCilindro + altoCilindro, cz);
        }
        glEnd();

        // Tapa del tanque
        glColor3f(0.15f, 0.15f, 0.15f);
        glBegin(GL_POLYGON);
        for (int i = 0; i < segmentos; i++) {
            double angulo = 2 * Math.PI * i / segmentos;
            float cx = (float) (Math.cos(angulo) * radioCilindro);
            float cz = (float) (Math.sin(angulo) * radioCilindro);
            glVertex3f(cx, yCilindro + altoCilindro, cz);
        }
        glEnd();

        // Tapa inferior del tanque
        glBegin(GL_POLYGON);
        for (int i = 0; i < segmentos; i++) {
            double angulo = 2 * Math.PI * i / segmentos;
            float cx = (float) (Math.cos(angulo) * radioCilindro);
            float cz = (float) (Math.sin(angulo) * radioCilindro);
            glVertex3f(cx, yCilindro, cz);
        }
        glEnd();

        // Tubo de bajada
        Cubo.dibujar(baseAncho/2 + 0.1f, baseAlto/2, 0f, 0.08f, baseAlto + 0.2f, 0.08f, 0.8f, 0.8f, 0.8f);

        glPopMatrix();
    }
}
