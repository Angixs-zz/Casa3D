package objetos;

import utilidades.Constantes;
import static org.lwjgl.opengl.GL11.*;

public class Recamara1 {

    private static final float Y = Constantes.ALTURA_PISO_1;

    private static float convertirXGeoAOpenGL(float xGeo) {
        float x = xGeo - Constantes.CENTRO_GEOGEBRA_X;
        x = -x; // Invertido porque en OpenGL +X es izquierda según la cámara
        return x * Constantes.ESCALA_CASA;
    }

    private static float convertirZGeoAOpenGL(float zGeo) {
        float z = zGeo - Constantes.CENTRO_GEOGEBRA_Z;
        return z * Constantes.ESCALA_CASA;
    }

    private static float escalar(float valor) {
        return valor * Constantes.ESCALA_CASA;
    }

    public static int texturaMuebles = 0;
    public static int texturaColcha = 0;

    private static void dibujarMadera(float x, float y, float z, float ancho, float alto, float profundo, float r, float g, float b) {
        if (texturaMuebles != 0) {
            Cubo.dibujarConTextura(x, y, z, ancho, alto, profundo, texturaMuebles);
        } else {
            Cubo.dibujar(x, y, z, ancho, alto, profundo, r, g, b);
        }
    }

    public static void dibujar() {
        // Pared principal continua (Z=15.5 a 16.0)
        dibujarEstante(0.2f, 15.5f, 0.8f, 16.0f);
        dibujarMesaYSilla(0.8f, 15.5f, 1.5f, 16.0f);
        dibujarCloset(1.5f, 15.5f, 3.7f, 16.0f);
        
        // Cama y Burós
        dibujarCama(0.2f, 16.8f, 2.0f, 18.0f);
        dibujarBuroLateral(0.2f, 16.4f, 0.5f, 16.7f);
        dibujarBuroLateral(0.2f, 18.2f, 0.5f, 18.5f);
        
        // Buró lateral con TV (X=3.3 a 3.8, Z=17.3 a 18.3)
        dibujarBuroConTV(3.3f, 17.3f, 3.8f, 18.3f);
    }

    private static void dibujarCloset(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo; // 2.2
        float largoGeo = zMaxGeo - zMinGeo; // 0.5

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);
        float alto = 3.2f; // De suelo a techo (aprox 3.2 según los cuartos)

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // Estructura principal del closet de madera
        dibujarMadera(0f, alto / 2f, 0f, ancho, alto, largo, 0.4f, 0.25f, 0.15f); // Madera oscura

        // Detalles: Puertas del closet (Ligeramente salidas hacia +Z para que se vean)
        // Dividiremos el ancho de 2.2 en 4 puertas de 0.55 cada una
        float anchoPuerta = ancho / 4f;
        for (int i = 0; i < 4; i++) {
            float offsetXPpuerta = -ancho/2f + anchoPuerta/2f + (i * anchoPuerta);
            
            // Puerta larga
            dibujarMadera(
                    offsetXPpuerta, alto / 2f, largo / 2f + 0.01f, 
                    anchoPuerta * 0.95f, alto * 0.95f, 0.02f, 
                    0.45f, 0.28f, 0.18f); // Madera un poco más clara para contraste
                    
            // Manijas cromadas largas
            Cubo.dibujar(
                    offsetXPpuerta + (i % 2 == 0 ? anchoPuerta * 0.3f : -anchoPuerta * 0.3f), // Manijas encontradas en el centro de cada par
                    alto * 0.45f, largo / 2f + 0.03f, 
                    0.02f, 0.4f, 0.03f, 
                    0.7f, 0.7f, 0.7f); // Metal
        }
        
        glPopMatrix();
    }

    private static void dibujarEstante(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo; // 0.6
        float largoGeo = zMaxGeo - zMinGeo; // 0.5

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);
        float alto = 3.2f;

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // Estructura lateral y trasera del estante
        dibujarMadera(0f, alto / 2f, 0f, ancho, alto, largo, 0.4f, 0.25f, 0.15f); // Misma madera oscura

        // Interior del estante (Haciendo un hueco visual con paneles de sombra o simplemente repisas sobresalientes)
        // Para simplificar y hacerlo realista, pondremos las repisas sobre un fondo oscuro
        Cubo.dibujar(0f, alto / 2f, largo / 2f + 0.005f, ancho * 0.9f, alto * 0.95f, 0.01f, 0.2f, 0.1f, 0.05f); // Fondo interior

        // Repisas horizontales
        int numRepisas = 6;
        float espacioRepisa = alto / numRepisas;
        for (int i = 1; i < numRepisas; i++) {
            dibujarMadera(
                    0f, i * espacioRepisa, largo / 2f + 0.01f, 
                    ancho * 0.9f, 0.04f, largo * 0.8f, 
                    0.45f, 0.28f, 0.18f); // Repisas de madera
        }

        glPopMatrix();
    }

    private static void dibujarMesaYSilla(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo; // 0.7
        float largoGeo = zMaxGeo - zMinGeo; // 0.5

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // MESA
        float altoMesa = 0.75f;
        // Tablero de la mesa
        dibujarMadera(0f, altoMesa, 0f, ancho, 0.05f, largo, 0.45f, 0.28f, 0.18f);
        
        // Cajones bajo el tablero
        dibujarMadera(0f, altoMesa - 0.1f, 0f, ancho * 0.95f, 0.15f, largo * 0.9f, 0.4f, 0.25f, 0.15f);
        // Manija del cajón central
        Cubo.dibujar(0f, altoMesa - 0.1f, largo / 2f + 0.01f, 0.15f, 0.02f, 0.02f, 0.7f, 0.7f, 0.7f);

        // Panel trasero (para que se integre con el mueble continuo)
        dibujarMadera(0f, altoMesa / 2f, -largo / 2f + 0.02f, ancho, altoMesa, 0.04f, 0.4f, 0.25f, 0.15f);

        // SILLA
        // La silla está enfrente (Z positivo respecto a la mesa)
        glPushMatrix();
        glTranslatef(0f, 0f, largo + 0.3f); // Un poco más separada para dar espacio al nuevo tamaño
        glScalef(1.4f, 1.4f, 1.4f); // Incrementamos el tamaño un 40%
        
        float altoAsiento = 0.45f;
        // Asiento
        Cubo.dibujar(0f, altoAsiento, 0f, 0.4f, 0.05f, 0.4f, 0.9f, 0.9f, 0.9f); // Blanco/Crema
        // Patas
        dibujarMadera(-0.15f, altoAsiento / 2f, -0.15f, 0.04f, altoAsiento, 0.04f, 0.6f, 0.4f, 0.2f); // Madera clara
        dibujarMadera(0.15f, altoAsiento / 2f, -0.15f, 0.04f, altoAsiento, 0.04f, 0.6f, 0.4f, 0.2f);
        dibujarMadera(-0.15f, altoAsiento / 2f, 0.15f, 0.04f, altoAsiento, 0.04f, 0.6f, 0.4f, 0.2f);
        dibujarMadera(0.15f, altoAsiento / 2f, 0.15f, 0.04f, altoAsiento, 0.04f, 0.6f, 0.4f, 0.2f);
        // Respaldo
        Cubo.dibujar(0f, altoAsiento + 0.25f, 0.15f, 0.4f, 0.4f, 0.04f, 0.9f, 0.9f, 0.9f); // Respaldo acolchado crema
        glPopMatrix();

        glPopMatrix();
    }

    private static void dibujarBuroConTV(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo; // 0.5 (X)
        float largoGeo = zMaxGeo - zMinGeo; // 1.0 (Z)

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // 1. Buró con cajones
        float altoBuro = 0.8f;
        dibujarMadera(0f, altoBuro / 2f, 0f, ancho, altoBuro, largo, 0.4f, 0.25f, 0.15f); // Madera oscura
        
        // Detalles de cajones en la cara lateral (Asumiendo que los cajones miran hacia el centro del cuarto, es decir hacia -X)
        // La pared está en +X (X=3.8 aprox). Así que el frente del buró es -X.
        for (int i = 0; i < 3; i++) {
            float yCajon = altoBuro * 0.8f - (i * 0.25f);
            // Frente del cajón
            dibujarMadera(-ancho / 2f - 0.01f, yCajon, 0f, 0.02f, 0.22f, largo * 0.9f, 0.45f, 0.28f, 0.18f);
            // Manija
            Cubo.dibujar(-ancho / 2f - 0.02f, yCajon, 0f, 0.02f, 0.02f, 0.15f, 0.7f, 0.7f, 0.7f);
        }

        // 2. Televisión de pared arriba del buró
        // La pared está en X positivo (derecha). La TV debe colgar mirando hacia la izquierda (-X).
        float altoTV = 0.8f;
        float anchoPantallaTV = largo * 0.8f; // A lo largo de Z
        float grosorTV = 0.05f;
        float yCentroTV = 1.6f; // A 1.6m de altura

        // Marco de la TV pegado a la pared (+X)
        Cubo.dibujar(ancho / 2f - grosorTV / 2f, yCentroTV, 0f, grosorTV, altoTV, anchoPantallaTV, 0.05f, 0.05f, 0.05f);
        
        // Pantalla (Mirando hacia -X)
        Cubo.dibujar(ancho / 2f - grosorTV - 0.005f, yCentroTV, 0f, 0.01f, altoTV * 0.9f, anchoPantallaTV * 0.95f, 0.1f, 0.1f, 0.15f); // Pantalla apagada / negra

        glPopMatrix();
    }

    private static void dibujarCama(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo; // 1.8 (Largo de la cama en X)
        float largoGeo = zMaxGeo - zMinGeo; // 1.2 (Ancho de la cama en Z)

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // Base de la cama (Madera)
        float altoBase = 0.35f;
        dibujarMadera(0f, altoBase / 2f, 0f, ancho, altoBase, largo, 0.4f, 0.25f, 0.15f); // Madera oscura
        
        // Colchón (Blanco / Crema)
        float altoColchon = 0.2f;
        Cubo.dibujar(0f, altoBase + altoColchon / 2f, 0f, ancho * 0.98f, altoColchon, largo * 0.98f, 0.95f, 0.95f, 0.9f); 

        // Edredón / Cubrecama
        // Cubre casi todo el colchón dejando la parte de la cabecera expuesta
        if (texturaColcha != 0) {
            Cubo.dibujarConTextura(
                    -ancho * 0.1f, // Desplazado hacia los pies (-X en OpenGL)
                    altoBase + altoColchon + 0.01f, 
                    0f, 
                    ancho * 0.8f, 0.02f, largo * 1.02f, 
                    texturaColcha);
        } else {
            Cubo.dibujar(
                    -ancho * 0.1f, // Desplazado hacia los pies (-X en OpenGL)
                    altoBase + altoColchon + 0.01f, 
                    0f, 
                    ancho * 0.8f, 0.02f, largo * 1.02f, 
                    0.1f, 0.2f, 0.4f); // Azul oscuro
        }

        // Cabecera (Madera con diseño geométrico como en la imagen de referencia)
        // Pegada a la pared (que es +X en OpenGL, es decir, el borde de ancho/2)
        dibujarMadera(ancho / 2f - 0.05f, 0.8f, 0f, 0.1f, 1.6f, largo * 1.1f, 0.35f, 0.2f, 0.1f); // Cabecera alta de madera
        // Detalles dorados en la cabecera
        Cubo.dibujar(ancho / 2f - 0.06f, 0.8f, 0f, 0.11f, 1.5f, 0.02f, 0.8f, 0.6f, 0.1f); // Línea vertical central dorada

        // Almohadas
        // Pegadas a la cabecera (+X)
        float xAlmohadas = ancho / 2f - 0.2f;
        float yAlmohadas = altoBase + altoColchon + 0.1f;
        
        // Almohadas traseras (Blancas)
        glPushMatrix();
        glTranslatef(xAlmohadas, yAlmohadas, -largo * 0.25f);
        glRotatef(-15f, 0f, 0f, 1f); // Inclinadas contra la cabecera
        Cubo.dibujar(0f, 0f, 0f, 0.2f, 0.3f, 0.4f, 0.95f, 0.95f, 0.95f);
        glPopMatrix();

        glPushMatrix();
        glTranslatef(xAlmohadas, yAlmohadas, largo * 0.25f);
        glRotatef(-15f, 0f, 0f, 1f);
        Cubo.dibujar(0f, 0f, 0f, 0.2f, 0.3f, 0.4f, 0.95f, 0.95f, 0.95f);
        glPopMatrix();
        
        // Almohadas decorativas delanteras (Azules con patrón)
        glPushMatrix();
        glTranslatef(xAlmohadas - 0.15f, yAlmohadas - 0.05f, -largo * 0.2f);
        glRotatef(-25f, 0f, 0f, 1f);
        Cubo.dibujar(0f, 0f, 0f, 0.15f, 0.25f, 0.35f, 0.2f, 0.3f, 0.5f);
        glPopMatrix();

        glPushMatrix();
        glTranslatef(xAlmohadas - 0.15f, yAlmohadas - 0.05f, largo * 0.2f);
        glRotatef(-25f, 0f, 0f, 1f);
        Cubo.dibujar(0f, 0f, 0f, 0.15f, 0.25f, 0.35f, 0.2f, 0.3f, 0.5f);
        glPopMatrix();

        glPopMatrix();
    }

    private static void dibujarBuroLateral(float xMinGeo, float zMinGeo, float xMaxGeo, float zMaxGeo) {
        float centroXGeo = (xMinGeo + xMaxGeo) / 2.0f;
        float centroZGeo = (zMinGeo + zMaxGeo) / 2.0f;

        float anchoGeo = xMaxGeo - xMinGeo; // 0.3
        float largoGeo = zMaxGeo - zMinGeo; // 0.3

        float x = convertirXGeoAOpenGL(centroXGeo);
        float z = convertirZGeoAOpenGL(centroZGeo);

        float ancho = escalar(anchoGeo);
        float largo = escalar(largoGeo);

        glPushMatrix();
        glTranslatef(x, Y, z);
        
        // Cuerpo del buró
        float altoBuro = 0.5f;
        dibujarMadera(0f, altoBuro / 2f, 0f, ancho, altoBuro, largo, 0.4f, 0.25f, 0.15f); // Madera oscura

        // 2 cajones (mirando hacia adelante, es decir hacia -X, asumiendo que es accesible desde la cama, o hacia el frente de la cama)
        // Como la cama está a lo largo del eje X (pies hacia -X), los cajones deberían mirar hacia -X para ser vistos de frente.
        for (int i = 0; i < 2; i++) {
            float yCajon = altoBuro * 0.7f - (i * 0.3f);
            dibujarMadera(-ancho / 2f - 0.01f, yCajon, 0f, 0.02f, 0.2f, largo * 0.9f, 0.45f, 0.28f, 0.18f); // Frente
            Cubo.dibujar(-ancho / 2f - 0.02f, yCajon, 0f, 0.02f, 0.02f, 0.1f, 0.7f, 0.7f, 0.7f); // Manija
        }
        
        // Lámpara de noche encima del buró
        Cubo.dibujar(0f, altoBuro + 0.02f, 0f, 0.15f, 0.04f, 0.15f, 0.1f, 0.1f, 0.1f); // Base
        Cubo.dibujar(0f, altoBuro + 0.15f, 0f, 0.02f, 0.3f, 0.02f, 0.7f, 0.7f, 0.7f); // Poste metálico
        Cubo.dibujar(0f, altoBuro + 0.3f, 0f, 0.18f, 0.15f, 0.18f, 0.9f, 0.9f, 0.8f); // Pantalla de la lámpara (crema)

        glPopMatrix();
    }
}
