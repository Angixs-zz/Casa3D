package objetos;

import static org.lwjgl.opengl.GL11.*;

public class Puerta {
    private String nombre;
    private float x, y, z; // Posición de la bisagra (eje de rotación) en OpenGL
    private float ancho;   // Ancho de la puerta en OpenGL
    private float alto;    // Altura de la puerta en OpenGL
    private boolean esEjeX; // true si se extiende en X, false si se extiende en Z
    private boolean abierta;
    private float anguloActual; 
    private float anguloApertura; // 90.0f o -90.0f según hacia dónde abre
    private float rotacionBase; // Rotación inicial para alinear la puerta cerrada

    public Puerta(String nombre, float x, float y, float z, float ancho, float alto, boolean esEjeX, float anguloApertura, float rotacionBase) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.z = z;
        this.ancho = ancho;
        this.alto = alto;
        this.esEjeX = esEjeX;
        this.anguloApertura = anguloApertura;
        this.rotacionBase = rotacionBase;
        this.abierta = false;
        this.anguloActual = 0.0f;
    }

    public void interactuar() {
        this.abierta = !this.abierta;
    }

    public boolean isAbierta() {
        return abierta;
    }

    public void actualizar(float velocidadAnimacion) {
        float anguloObjetivo = abierta ? anguloApertura : 0.0f;
        
        if (anguloApertura > 0) {
            if (anguloActual < anguloObjetivo) {
                anguloActual += velocidadAnimacion;
                if (anguloActual > anguloObjetivo) anguloActual = anguloObjetivo;
            } else if (anguloActual > anguloObjetivo) {
                anguloActual -= velocidadAnimacion;
                if (anguloActual < anguloObjetivo) anguloActual = anguloObjetivo;
            }
        } else {
            if (anguloActual > anguloObjetivo) {
                anguloActual -= velocidadAnimacion;
                if (anguloActual < anguloObjetivo) anguloActual = anguloObjetivo;
            } else if (anguloActual < anguloObjetivo) {
                anguloActual += velocidadAnimacion;
                if (anguloActual > anguloObjetivo) anguloActual = anguloObjetivo;
            }
        }
    }

    public void dibujar() {
        glPushMatrix();
        glTranslatef(x, y + alto / 2.0f, z);
        
        glRotatef(rotacionBase + anguloActual, 0.0f, 1.0f, 0.0f);

        glBegin(GL_QUADS);
        // Color café para la puerta
        glColor3f(0.54f, 0.27f, 0.07f); 

        float grosor = 0.05f; 
        float xMin, xMax, zMin, zMax;

        if (esEjeX) {
            xMin = 0; xMax = ancho;
            zMin = -grosor; zMax = grosor;
        } else {
            xMin = -grosor; xMax = grosor;
            zMin = 0; zMax = ancho;
        }
        
        float yMin = -alto / 2.0f;
        float yMax = alto / 2.0f;

        // Cara Frontal
        glVertex3f(xMin, yMin, zMax); glVertex3f(xMax, yMin, zMax);
        glVertex3f(xMax, yMax, zMax); glVertex3f(xMin, yMax, zMax);
        // Cara Trasera
        glVertex3f(xMin, yMin, zMin); glVertex3f(xMin, yMax, zMin);
        glVertex3f(xMax, yMax, zMin); glVertex3f(xMax, yMin, zMin);
        // Cara Izquierda
        glVertex3f(xMin, yMin, zMin); glVertex3f(xMin, yMin, zMax);
        glVertex3f(xMin, yMax, zMax); glVertex3f(xMin, yMax, zMin);
        // Cara Derecha
        glVertex3f(xMax, yMin, zMin); glVertex3f(xMax, yMax, zMin);
        glVertex3f(xMax, yMax, zMax); glVertex3f(xMax, yMin, zMax);
        // Cara Superior
        glVertex3f(xMin, yMax, zMin); glVertex3f(xMin, yMax, zMax);
        glVertex3f(xMax, yMax, zMax); glVertex3f(xMax, yMax, zMin);
        // Cara Inferior
        glVertex3f(xMin, yMin, zMin); glVertex3f(xMax, yMin, zMin);
        glVertex3f(xMax, yMin, zMax); glVertex3f(xMin, yMin, zMax);
        
        glEnd();
        glPopMatrix();
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getZ() { return z; }
    public float getAncho() { return ancho; }
    public float getAlto() { return alto; }
    public boolean isEjeX() { return esEjeX; }
    public String getNombre() { return nombre; }
    public float getRotacionBase() { return rotacionBase; }
}
