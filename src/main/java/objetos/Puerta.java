package objetos;

import static org.lwjgl.opengl.GL11.*;

public class Puerta {
    private String nombre;
    private float x, y, z; // Posición de la bisagra (eje de rotación) en OpenGL
    private float ancho; // Ancho de la puerta en OpenGL
    private float alto; // Altura de la puerta en OpenGL
    private boolean esEjeX; // true si se extiende en X, false si se extiende en Z
    private boolean abierta;
    private float anguloActual;
    private float anguloApertura; // 90.0f o -90.0f según hacia dónde abre (o distancia si es corrediza)
    private float rotacionBase; // Rotación inicial para alinear la puerta cerrada
    private boolean visible = true;
    private boolean esCorrediza = false;

    public static int texturaPuerta = 0;
    public static int texturaPuertaNegra = 0;

    public Puerta(String nombre, float x, float y, float z, float ancho, float alto, boolean esEjeX,
            float anguloApertura, float rotacionBase) {
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void actualizar(float velocidadAnimacion) {
        float anguloObjetivo = abierta ? anguloApertura : 0.0f;

        if (anguloApertura > 0) {
            if (anguloActual < anguloObjetivo) {
                anguloActual += velocidadAnimacion;
                if (anguloActual > anguloObjetivo)
                    anguloActual = anguloObjetivo;
            } else if (anguloActual > anguloObjetivo) {
                anguloActual -= velocidadAnimacion;
                if (anguloActual < anguloObjetivo)
                    anguloActual = anguloObjetivo;
            }
        } else {
            if (anguloActual > anguloObjetivo) {
                anguloActual -= velocidadAnimacion;
                if (anguloActual < anguloObjetivo)
                    anguloActual = anguloObjetivo;
            } else if (anguloActual < anguloObjetivo) {
                anguloActual += velocidadAnimacion;
                if (anguloActual > anguloObjetivo)
                    anguloActual = anguloObjetivo;
            }
        }
    }

    public boolean isEsCorrediza() {
        return esCorrediza;
    }

    public void setEsCorrediza(boolean esCorrediza) {
        this.esCorrediza = esCorrediza;
    }

    public void dibujar() {
        glPushMatrix();

        if (esCorrediza) {
            glTranslatef(x, y, z);
            glRotatef(rotacionBase, 0.0f, 1.0f, 0.0f);
            
            float grosorPanel = 0.05f;
            float mitad = ancho / 2.0f;
            float yCentro = alto / 2.0f;
            
            if (esEjeX) {
                // PANEL FIJO (de la mitad al ancho total)
                dibujarPanelVentanalX(mitad + mitad/2.0f, yCentro, -0.02f, mitad, alto, grosorPanel);
                
                // PANEL MÓVIL (de 0 a la mitad, se desliza)
                glPushMatrix();
                glTranslatef(anguloActual, 0.0f, 0.0f);
                dibujarPanelVentanalX(mitad/2.0f, yCentro, 0.02f, mitad, alto, grosorPanel);
                glPopMatrix();
                
                // Marcos horizontales (suelo y techo) que cubren todo el ancho
                Cubo.dibujar(mitad, 0.05f, 0f, ancho, 0.1f, grosorPanel * 2.0f, 0.1f, 0.1f, 0.1f);
                Cubo.dibujar(mitad, alto - 0.05f, 0f, ancho, 0.1f, grosorPanel * 2.0f, 0.1f, 0.1f, 0.1f);
            } else {
                dibujarPanelVentanalZ(-0.02f, yCentro, mitad + mitad/2.0f, mitad, alto, grosorPanel);
                
                glPushMatrix();
                glTranslatef(0.0f, 0.0f, anguloActual);
                dibujarPanelVentanalZ(0.02f, yCentro, mitad/2.0f, mitad, alto, grosorPanel);
                glPopMatrix();
                
                Cubo.dibujar(0f, 0.05f, mitad, grosorPanel * 2.0f, 0.1f, ancho, 0.1f, 0.1f, 0.1f);
                Cubo.dibujar(0f, alto - 0.05f, mitad, grosorPanel * 2.0f, 0.1f, ancho, 0.1f, 0.1f, 0.1f);
            }
            glPopMatrix();
            return;
        }

        glTranslatef(x, y + alto / 2.0f, z);
        glRotatef(rotacionBase + anguloActual, 0.0f, 1.0f, 0.0f);

        int texturaAplicar = texturaPuerta;
        if (nombre != null && nombre.equals("Puerta Principal") && texturaPuertaNegra != 0) {
            texturaAplicar = texturaPuertaNegra;
        }

        if (texturaAplicar != 0) {
            glEnable(GL_TEXTURE_2D);
            glBindTexture(GL_TEXTURE_2D, texturaAplicar);
            glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        } else {
            glColor3f(0.54f, 0.27f, 0.07f);
        }

        glBegin(GL_QUADS);
        float grosor = 0.05f;
        float xMin, xMax, zMin, zMax;

        if (esEjeX) {
            xMin = 0;
            xMax = ancho;
            zMin = -grosor;
            zMax = grosor;
        } else {
            xMin = -grosor;
            xMax = grosor;
            zMin = 0;
            zMax = ancho;
        }

        float yMin = -alto / 2.0f;
        float yMax = alto / 2.0f;

        // Cara Frontal
        if (texturaAplicar != 0) glTexCoord2f(0f, 1f);
        glVertex3f(xMin, yMin, zMax);
        if (texturaAplicar != 0) glTexCoord2f(1f, 1f);
        glVertex3f(xMax, yMin, zMax);
        if (texturaAplicar != 0) glTexCoord2f(1f, 0f);
        glVertex3f(xMax, yMax, zMax);
        if (texturaAplicar != 0) glTexCoord2f(0f, 0f);
        glVertex3f(xMin, yMax, zMax);

        // Cara Trasera
        if (texturaAplicar != 0) glTexCoord2f(1f, 1f);
        glVertex3f(xMin, yMin, zMin);
        if (texturaAplicar != 0) glTexCoord2f(1f, 0f);
        glVertex3f(xMin, yMax, zMin);
        if (texturaAplicar != 0) glTexCoord2f(0f, 0f);
        glVertex3f(xMax, yMax, zMin);
        if (texturaAplicar != 0) glTexCoord2f(0f, 1f);
        glVertex3f(xMax, yMin, zMin);

        // Cara Izquierda
        if (texturaAplicar != 0) glTexCoord2f(0f, 1f);
        glVertex3f(xMin, yMin, zMin);
        if (texturaAplicar != 0) glTexCoord2f(1f, 1f);
        glVertex3f(xMin, yMin, zMax);
        if (texturaAplicar != 0) glTexCoord2f(1f, 0f);
        glVertex3f(xMin, yMax, zMax);
        if (texturaAplicar != 0) glTexCoord2f(0f, 0f);
        glVertex3f(xMin, yMax, zMin);

        // Cara Derecha
        if (texturaAplicar != 0) glTexCoord2f(0f, 1f);
        glVertex3f(xMax, yMin, zMin);
        if (texturaAplicar != 0) glTexCoord2f(0f, 0f);
        glVertex3f(xMax, yMax, zMin);
        if (texturaAplicar != 0) glTexCoord2f(1f, 0f);
        glVertex3f(xMax, yMax, zMax);
        if (texturaAplicar != 0) glTexCoord2f(1f, 1f);
        glVertex3f(xMax, yMin, zMax);

        // Cara Superior
        if (texturaAplicar != 0) glTexCoord2f(0f, 0f);
        glVertex3f(xMin, yMax, zMin);
        if (texturaAplicar != 0) glTexCoord2f(0f, 1f);
        glVertex3f(xMin, yMax, zMax);
        if (texturaAplicar != 0) glTexCoord2f(1f, 1f);
        glVertex3f(xMax, yMax, zMax);
        if (texturaAplicar != 0) glTexCoord2f(1f, 0f);
        glVertex3f(xMax, yMax, zMin);

        // Cara Inferior
        if (texturaAplicar != 0) glTexCoord2f(0f, 0f);
        glVertex3f(xMin, yMin, zMin);
        if (texturaAplicar != 0) glTexCoord2f(1f, 0f);
        glVertex3f(xMax, yMin, zMin);
        if (texturaAplicar != 0) glTexCoord2f(1f, 1f);
        glVertex3f(xMax, yMin, zMax);
        if (texturaAplicar != 0) glTexCoord2f(0f, 1f);
        glVertex3f(xMin, yMin, zMax);

        glEnd();
        
        if (texturaAplicar != 0) {
            glDisable(GL_TEXTURE_2D);
        }

        // ======================
        // DIBUJAR MANIJAS
        // ======================
        if (nombre == null || !nombre.equals("Puerta Principal")) {
            float mX = 0f, mZ = 0f;
            float offsetManija = 0.15f; // Distancia desde el borde opuesto a la bisagra
            
            if (esEjeX) {
                mX = ancho - offsetManija; // Lejos de la bisagra (que está en X=0)
                mZ = 0f;
            } else {
                mX = 0f;
                mZ = ancho - offsetManija; // Lejos de la bisagra (que está en Z=0)
            }
            
            // Base de la manija
            Cubo.dibujar(mX, 0f, mZ, 0.08f, 0.12f, grosor * 2.5f + 0.02f, 0.7f, 0.7f, 0.7f);
            
            // Pomo frontal
            if (esEjeX) {
                Cubo.dibujar(mX + 0.02f, 0f, mZ + grosor + 0.04f, 0.1f, 0.02f, 0.02f, 0.8f, 0.8f, 0.8f);
                Cubo.dibujar(mX + 0.02f, 0f, mZ - grosor - 0.04f, 0.1f, 0.02f, 0.02f, 0.8f, 0.8f, 0.8f);
            } else {
                Cubo.dibujar(mX + grosor + 0.04f, 0f, mZ + 0.02f, 0.02f, 0.02f, 0.1f, 0.8f, 0.8f, 0.8f);
                Cubo.dibujar(mX - grosor - 0.04f, 0f, mZ + 0.02f, 0.02f, 0.02f, 0.1f, 0.8f, 0.8f, 0.8f);
            }
        }

        glPopMatrix();
    }

    private void dibujarPanelVentanalX(float cx, float cy, float cz, float panelAncho, float panelAlto, float grosor) {
        Cubo.dibujar(cx - panelAncho/2.0f + 0.05f, cy, cz, 0.1f, panelAlto, grosor, 0.1f, 0.1f, 0.1f);
        Cubo.dibujar(cx + panelAncho/2.0f - 0.05f, cy, cz, 0.1f, panelAlto, grosor, 0.1f, 0.1f, 0.1f);
        
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDepthMask(false);
        Cubo.dibujar(cx, cy, cz, panelAncho - 0.1f, panelAlto - 0.2f, 0.02f, 0.4f, 0.6f, 0.8f, 0.35f);
        glDepthMask(true);
        glDisable(GL_BLEND);
    }
    
    private void dibujarPanelVentanalZ(float cx, float cy, float cz, float panelAncho, float panelAlto, float grosor) {
        Cubo.dibujar(cx, cy, cz - panelAncho/2.0f + 0.05f, grosor, panelAlto, 0.1f, 0.1f, 0.1f, 0.1f);
        Cubo.dibujar(cx, cy, cz + panelAncho/2.0f - 0.05f, grosor, panelAlto, 0.1f, 0.1f, 0.1f, 0.1f);
        
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDepthMask(false);
        Cubo.dibujar(cx, cy, cz, 0.02f, panelAlto - 0.2f, panelAncho - 0.1f, 0.4f, 0.6f, 0.8f, 0.35f);
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getAncho() {
        return ancho;
    }

    public float getAlto() {
        return alto;
    }

    public boolean isEjeX() {
        return esEjeX;
    }

    public String getNombre() {
        return nombre;
    }

    public float getRotacionBase() {
        return rotacionBase;
    }

    public float getAnguloActual() {
        return anguloActual;
    }
}
