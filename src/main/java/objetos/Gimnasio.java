package objetos;

import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class Gimnasio {
    public static int texturaSacoBox = 0;
    public static int texturaRutina = 0;

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
         * Gimnasio:
         * 1. Caminadora (T4, W4, U4, V4) -> X: 7.0 a 7.8, Z: 14.4 a 16.4
         * 2. Bicicleta Estática (Z4, C5, B5, A5) -> X: 5.0 a 6.3, Z: 14.0 a 14.6
         * 3. Puesto de Pesas (D5, E5, F5, G5) -> X: 5.0 a 6.2, Z: 17.8 a 18.5
         * 4. Estante Mancuernas (H5, I5, J5, K5) -> X: 6.6 a 7.7, Z: 18.2 a 18.5
         * 5. Saco de Boxeo (L5, M5, N5, O5) -> X: 6.5 a 6.8, Z: 17.1 a 17.4
         */

        dibujarCaminadora(7.0f, 14.4f, 7.8f, 16.4f);
        dibujarBicicletaEstatica(5.0f, 14.0f, 6.3f, 14.6f);
        
        dibujarPuestoPesas(5.0f, 17.8f, 6.2f, 18.5f);
        dibujarEstanteMancuernas(6.6f, 18.2f, 7.7f, 18.5f);
        dibujarSacoBoxeo(6.5f, 17.1f, 6.8f, 17.4f);
        
        // TV en la pared K1 (entre O1(4.9, 15.4) y P1(4.9, 18.6))
        dibujarTVPared(4.9f, 15.4f, 4.9f, 18.6f);
    }

    private static void dibujarCaminadora(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;
        
        float anchoGeo = xMaxGeo - xMinGeo; // 0.8
        float largoGeo = zMaxGeo - zMinGeo; // 2.0

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // Asumimos que la caminadora mira hacia -Z (la consola está en la parte de menor Z)

        // 1. Base principal (Donde va la banda transportadora)
        // Está un poco elevada del piso
        Cubo.dibujar(
                0f, 0.15f, 0f, 
                ancho * 0.9f, 0.12f, largo, 
                0.15f, 0.15f, 0.15f); // Gris muy oscuro

        // 2. Banda transportadora (La cinta negra)
        Cubo.dibujar(
                0f, 0.22f, 0f, 
                ancho * 0.75f, 0.02f, largo * 0.95f, 
                0.05f, 0.05f, 0.05f); // Negro

        // 3. Pilares frontales (Soporte de la consola)
        // Ubicados en la parte frontal (Z negativo relativo al centro)
        float offsetZPilares = -largo / 2f + escalar(0.2f);
        float offsetXPilares = ancho / 2f - escalar(0.1f);
        
        // Pilar izquierdo
        Cubo.dibujar(
                -offsetXPilares, 0.7f, offsetZPilares, 
                escalar(0.1f), 1.2f, escalar(0.15f), 
                0.6f, 0.6f, 0.6f); // Metálico
                
        // Pilar derecho
        Cubo.dibujar(
                offsetXPilares, 0.7f, offsetZPilares, 
                escalar(0.1f), 1.2f, escalar(0.15f), 
                0.6f, 0.6f, 0.6f); // Metálico

        // 4. Panel de control (Consola y Pantalla)
        glPushMatrix();
        glTranslatef(0f, 1.35f, offsetZPilares - escalar(0.05f));
        glRotatef(30f, 1f, 0f, 0f); // Inclinado hacia el usuario
        
        // Base del panel
        Cubo.dibujar(
                0f, 0f, 0f, 
                ancho, escalar(0.4f), escalar(0.5f), 
                0.1f, 0.1f, 0.1f); // Negro plástico
                
        // Pantalla LED/LCD
        Cubo.dibujar(
                0f, 0.02f, escalar(0.05f), 
                ancho * 0.6f, escalar(0.02f), escalar(0.25f), 
                0.2f, 0.6f, 0.8f); // Azul brillante (pantalla encendida)
        glPopMatrix();

        // 5. Pasamanos laterales
        // Izquierdo
        Cubo.dibujar(
                -offsetXPilares, 1.15f, offsetZPilares + escalar(0.4f), 
                escalar(0.08f), escalar(0.08f), escalar(0.8f), 
                0.1f, 0.1f, 0.1f);
        // Derecho
        Cubo.dibujar(
                offsetXPilares, 1.15f, offsetZPilares + escalar(0.4f), 
                escalar(0.08f), escalar(0.08f), escalar(0.8f), 
                0.1f, 0.1f, 0.1f);

        // 6. Motor frontal (Carcasa protectora debajo del panel)
        Cubo.dibujar(
                0f, 0.25f, offsetZPilares - escalar(0.1f), 
                ancho * 0.9f, 0.25f, escalar(0.4f), 
                0.2f, 0.2f, 0.2f); // Gris oscuro

        glPopMatrix();
    }

    private static void dibujarBicicletaEstatica(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo; // 1.3
        float largoGeo = zMaxGeo - zMinGeo; // 0.6

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // Asumimos que la bicicleta mira hacia -X (la consola está en X negativo relativo)
        // El ancho es el eje X, largo es Z. Pero como mira hacia -X, el volante está a la izquierda.

        // 1. Soportes/Patas horizontales en el piso
        // Pata trasera (X positivo)
        Cubo.dibujar(
                ancho / 2f - escalar(0.2f), 0.05f, 0f, 
                escalar(0.1f), 0.1f, largo * 0.9f, 
                0.2f, 0.2f, 0.2f);
        // Pata delantera (X negativo)
        Cubo.dibujar(
                -ancho / 2f + escalar(0.2f), 0.05f, 0f, 
                escalar(0.1f), 0.1f, largo * 0.9f, 
                0.2f, 0.2f, 0.2f);

        // 2. Chasis central / Rueda de inercia
        // Está en la parte delantera (-X)
        Cubo.dibujar(
                -escalar(0.1f), 0.4f, 0f, 
                escalar(0.6f), 0.6f, escalar(0.2f), 
                0.1f, 0.1f, 0.1f); // Cuerpo negro
                
        // Detalle circular (Rueda de inercia)
        Cubo.dibujar(
                -escalar(0.25f), 0.4f, escalar(0.11f), 
                escalar(0.45f), 0.45f, escalar(0.02f), 
                0.7f, 0.1f, 0.1f); // Detalle rojo
        Cubo.dibujar(
                -escalar(0.25f), 0.4f, -escalar(0.11f), 
                escalar(0.45f), 0.45f, escalar(0.02f), 
                0.7f, 0.1f, 0.1f);

        // 3. Eje de los pedales
        Cubo.dibujar(
                escalar(0.1f), 0.4f, 0f, 
                escalar(0.15f), 0.15f, escalar(0.4f), 
                0.3f, 0.3f, 0.3f);
        // Pedal izquierdo (+Z)
        Cubo.dibujar(
                escalar(0.1f), 0.5f, escalar(0.25f), 
                escalar(0.15f), 0.05f, escalar(0.1f), 
                0.1f, 0.1f, 0.1f);
        // Pedal derecho (-Z)
        Cubo.dibujar(
                escalar(0.1f), 0.3f, -escalar(0.25f), 
                escalar(0.15f), 0.05f, escalar(0.1f), 
                0.1f, 0.1f, 0.1f);

        // 4. Poste del asiento y Sillín
        // Poste diagonal
        glPushMatrix();
        glTranslatef(escalar(0.3f), 0.7f, 0f);
        glRotatef(15f, 0f, 0f, 1f); // Inclinado ligeramente hacia atrás
        Cubo.dibujar(0f, 0f, 0f, escalar(0.1f), 0.6f, escalar(0.1f), 0.6f, 0.6f, 0.6f);
        
        // Sillín
        Cubo.dibujar(0f, 0.3f, 0f, escalar(0.35f), 0.1f, escalar(0.25f), 0.15f, 0.15f, 0.15f);
        glPopMatrix();

        // 5. Poste del manubrio y Consola
        // Poste diagonal delantero
        glPushMatrix();
        glTranslatef(-escalar(0.35f), 0.8f, 0f);
        glRotatef(-15f, 0f, 0f, 1f); // Inclinado hacia adelante
        Cubo.dibujar(0f, 0f, 0f, escalar(0.1f), 0.8f, escalar(0.1f), 0.6f, 0.6f, 0.6f);
        
        // Manubrio / Cuernos
        Cubo.dibujar(-escalar(0.1f), 0.45f, 0f, escalar(0.2f), escalar(0.08f), escalar(0.6f), 0.15f, 0.15f, 0.15f);
        Cubo.dibujar(-escalar(0.2f), 0.55f, escalar(0.3f), escalar(0.08f), 0.3f, escalar(0.08f), 0.15f, 0.15f, 0.15f); // Agarre izquierdo
        Cubo.dibujar(-escalar(0.2f), 0.55f, -escalar(0.3f), escalar(0.08f), 0.3f, escalar(0.08f), 0.15f, 0.15f, 0.15f); // Agarre derecho
        
        // Pantalla
        glRotatef(30f, 0f, 0f, 1f);
        Cubo.dibujar(0f, 0.45f, 0f, escalar(0.05f), 0.2f, escalar(0.25f), 0.1f, 0.1f, 0.1f); // Carcasa
        Cubo.dibujar(-escalar(0.03f), 0.45f, 0f, escalar(0.01f), 0.18f, escalar(0.22f), 0.5f, 0.8f, 0.9f); // LCD
        glPopMatrix();

        glPopMatrix();
    }

    private static void dibujarPuestoPesas(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo; // 1.2
        float largoGeo = zMaxGeo - zMinGeo; // 0.7

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // Puesto de Pesas (Bench Press)
        // La banca estará a lo largo del eje Z, y el soporte de la barra en el lado de mayor Z (pegado a la pared)

        // 1. Banca acolchada
        Cubo.dibujar(
                0f, 0.45f, -escalar(0.1f), 
                escalar(0.35f), 0.1f, largo * 0.8f, 
                0.15f, 0.15f, 0.15f); // Cuero negro

        // 2. Soporte central de la banca
        Cubo.dibujar(
                0f, 0.22f, -escalar(0.1f), 
                escalar(0.1f), 0.45f, escalar(0.1f), 
                0.6f, 0.6f, 0.6f); // Metálico
        Cubo.dibujar(
                0f, 0.05f, -escalar(0.1f), 
                escalar(0.4f), 0.1f, escalar(0.4f), 
                0.6f, 0.6f, 0.6f); // Base en el piso

        // 3. Estructura del Rack (Postes laterales)
        // Poste izquierdo
        Cubo.dibujar(
                -ancho / 2f + escalar(0.1f), 0.6f, largo / 2f - escalar(0.15f), 
                escalar(0.1f), 1.2f, escalar(0.1f), 
                0.3f, 0.3f, 0.3f); // Gris oscuro
        // Poste derecho
        Cubo.dibujar(
                ancho / 2f - escalar(0.1f), 0.6f, largo / 2f - escalar(0.15f), 
                escalar(0.1f), 1.2f, escalar(0.1f), 
                0.3f, 0.3f, 0.3f); // Gris oscuro
                
        // Barra horizontal uniendo los postes a nivel del piso
        Cubo.dibujar(
                0f, 0.05f, largo / 2f - escalar(0.15f), 
                ancho * 0.9f, 0.1f, escalar(0.1f), 
                0.3f, 0.3f, 0.3f);

        // 4. Barra olímpica descansando en el rack
        float alturaBarra = 1.15f;
        Cubo.dibujar(
                0f, alturaBarra, largo / 2f - escalar(0.15f), 
                ancho * 1.1f, escalar(0.04f), escalar(0.04f), 
                0.8f, 0.8f, 0.8f); // Barra de metal brillante

        // 5. Discos de pesas en la barra
        // Discos lado izquierdo
        Cubo.dibujar(-ancho / 2f - escalar(0.02f), alturaBarra, largo / 2f - escalar(0.15f), escalar(0.05f), 0.35f, escalar(0.35f), 0.1f, 0.1f, 0.1f);
        Cubo.dibujar(-ancho / 2f + escalar(0.05f), alturaBarra, largo / 2f - escalar(0.15f), escalar(0.05f), 0.25f, escalar(0.25f), 0.1f, 0.1f, 0.1f);
        
        // Discos lado derecho
        Cubo.dibujar(ancho / 2f + escalar(0.02f), alturaBarra, largo / 2f - escalar(0.15f), escalar(0.05f), 0.35f, escalar(0.35f), 0.1f, 0.1f, 0.1f);
        Cubo.dibujar(ancho / 2f - escalar(0.05f), alturaBarra, largo / 2f - escalar(0.15f), escalar(0.05f), 0.25f, escalar(0.25f), 0.1f, 0.1f, 0.1f);

        glPopMatrix();
    }

    private static void dibujarEstanteMancuernas(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo; // 1.1
        float largoGeo = zMaxGeo - zMinGeo; // 0.3

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // Es un mueble horizontal de 2 repisas inclinadas
        
        // Soportes laterales (Patas)
        Cubo.dibujar(
                -ancho / 2f + escalar(0.05f), 0.4f, 0f, 
                escalar(0.08f), 0.8f, largo * 0.9f, 
                0.2f, 0.2f, 0.2f);
        Cubo.dibujar(
                ancho / 2f - escalar(0.05f), 0.4f, 0f, 
                escalar(0.08f), 0.8f, largo * 0.9f, 
                0.2f, 0.2f, 0.2f);

        // Repisa Inferior (Inclinada)
        glPushMatrix();
        glTranslatef(0f, 0.35f, 0f);
        glRotatef(20f, 1f, 0f, 0f);
        Cubo.dibujar(0f, 0f, 0f, ancho * 0.95f, 0.05f, largo * 0.8f, 0.6f, 0.6f, 0.6f);
        // Mancuernas repisa inferior
        dibujarMancuerna(-ancho * 0.3f, 0.08f, 0f);
        dibujarMancuerna(0f, 0.08f, 0f);
        dibujarMancuerna(ancho * 0.3f, 0.08f, 0f);
        glPopMatrix();

        // Repisa Superior (Inclinada)
        glPushMatrix();
        glTranslatef(0f, 0.7f, -escalar(0.05f)); // Un poco más atrás
        glRotatef(20f, 1f, 0f, 0f);
        Cubo.dibujar(0f, 0f, 0f, ancho * 0.95f, 0.05f, largo * 0.8f, 0.6f, 0.6f, 0.6f);
        // Mancuernas repisa superior (un poco más pequeñas)
        glScalef(0.8f, 0.8f, 0.8f);
        dibujarMancuerna(-ancho * 0.35f, 0.08f, 0f);
        dibujarMancuerna(-ancho * 0.12f, 0.08f, 0f);
        dibujarMancuerna(ancho * 0.12f, 0.08f, 0f);
        dibujarMancuerna(ancho * 0.35f, 0.08f, 0f);
        glPopMatrix();

        glPopMatrix();
    }

    private static void dibujarMancuerna(float xLocal, float yLocal, float zLocal) {
        glPushMatrix();
        glTranslatef(xLocal, yLocal, zLocal);
        // Agarre central
        Cubo.dibujar(0f, 0f, 0f, escalar(0.12f), escalar(0.03f), escalar(0.03f), 0.7f, 0.7f, 0.7f); // Cromo
        // Peso Izquierdo
        Cubo.dibujar(-escalar(0.08f), 0f, 0f, escalar(0.06f), 0.15f, escalar(0.15f), 0.1f, 0.1f, 0.1f); // Hexágono negro
        // Peso Derecho
        Cubo.dibujar(escalar(0.08f), 0f, 0f, escalar(0.06f), 0.15f, escalar(0.15f), 0.1f, 0.1f, 0.1f);
        glPopMatrix();
    }

    private static void dibujarSacoBoxeo(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // Techo de la casa está en Y = 3.2 aprox.
        float alturaTecho = 3.1f;
        float largoCadena = 0.6f;
        float alturaSaco = 1.3f;
        float baseSacoY = alturaTecho - largoCadena - alturaSaco;

        // 1. Cadena/Soporte colgado del techo
        Cubo.dibujar(
                0f, alturaTecho - largoCadena / 2f, 0f, 
                escalar(0.02f), largoCadena, escalar(0.02f), 
                0.7f, 0.7f, 0.7f); // Cromo/Plata

        // 2. Saco de Boxeo
        if (texturaSacoBox != 0) {
            Cubo.dibujarConTextura(
                    0f, baseSacoY + alturaSaco / 2f, 0f, 
                    escalar(0.35f), alturaSaco, escalar(0.35f), 
                    texturaSacoBox);
        } else {
            Cubo.dibujar(
                    0f, baseSacoY + alturaSaco / 2f, 0f, 
                    escalar(0.35f), alturaSaco, escalar(0.35f), 
                    0.8f, 0.1f, 0.1f); // Rojo oscuro o negro.
        }
                
        // 3. Banda negra en el medio del saco (detalle)
        Cubo.dibujar(
                0f, baseSacoY + alturaSaco / 2f, 0f, 
                escalar(0.36f), 0.1f, escalar(0.36f), 
                0.1f, 0.1f, 0.1f); // Cinta negra

        glPopMatrix();
    }

    private static void dibujarTVPared(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        // Centro entre O1 y P1
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f; // 4.9
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f; // 17.0

        // El gimnasio está en el lado X > 4.9, así que la TV se despega hacia +X
        float xCentroGeoTV = centroXGeo + 0.05f; 
        
        float x = convertirXGeoAOpenGL(xCentroGeoTV);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float anchoTV = escalar(1.2f); // Ancho escalado
        float altoTV = escalar(0.7f); // Alto escalado para mantener proporción (evita distorsión)
        float grosorTV = escalar(0.08f); 

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // Marco de la TV
        Cubo.dibujar(
                0f, 1.60f, 0f, 
                grosorTV, altoTV, anchoTV, 
                0.05f, 0.05f, 0.05f);

        // Pantalla de la TV
        if (texturaRutina != 0) {
                glPushMatrix();
                glTranslatef(-grosorTV / 2f - 0.005f, 1.60f, 0f); // Centro de la pantalla
                
                // Voltear la textura verticalmente sin rotar el modelo en 3D
                glMatrixMode(GL_TEXTURE);
                glPushMatrix();
                glScalef(1f, -1f, 1f); // Flip vertical (V)
                glMatrixMode(GL_MODELVIEW);

                Cubo.dibujarConTextura(
                        0f, 0f, 0f, // Dibujamos desde el centro relativo
                        0.01f, // Grosor X
                        altoTV * 0.92f, // Alto Y
                        anchoTV * 0.96f, // Ancho Z
                        texturaRutina);
                
                // Restaurar matriz de textura
                glMatrixMode(GL_TEXTURE);
                glPopMatrix();
                glMatrixMode(GL_MODELVIEW);

                glPopMatrix();
        } else {
                Cubo.dibujar(
                        -grosorTV / 2f - 0.005f, 
                        1.60f, 
                        0f, 
                        0.01f, 
                        altoTV * 0.92f, 
                        anchoTV * 0.96f, 
                        0.15f, 0.25f, 0.35f);
        }

        glPopMatrix();
    }
}
