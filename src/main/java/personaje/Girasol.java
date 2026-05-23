package personaje;

import objetos.Cubo;
import escena.Casa;
import objetos.Colisiones;
import objetos.Escalera;
import utilidades.Constantes;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Girasol {

    private float x;
    private float y;
    private float z;

    private float rotacionY;
    private float escala;

    public Girasol(float x, float y, float z) {
        this(x, y, z, 0.7f);
    }

    public Girasol(float x, float y, float z, float escala) {
        this(x, y, z, escala, 0f);
    }

    public Girasol(float x, float y, float z, float escala, float rotacionY) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.escala = escala;
        this.rotacionY = rotacionY;
    }

    public void actualizar(long ventana, Casa casa) {
        float velocidadMovimiento = 0.07f;
        float velocidadRotacion = 2.2f;
        float radioJugador = 0.12f;

        if (glfwGetKey(ventana, GLFW_KEY_A) == GLFW_PRESS) {
            rotacionY += velocidadRotacion;
        }

        if (glfwGetKey(ventana, GLFW_KEY_D) == GLFW_PRESS) {
            rotacionY -= velocidadRotacion;
        }

        float radianes = (float) Math.toRadians(rotacionY);

        float direccionX = (float) Math.sin(radianes);
        float direccionZ = (float) Math.cos(radianes);

        float nuevoX = x;
        float nuevoZ = z;

        // Corrección: W avanza hacia adelante
        if (glfwGetKey(ventana, GLFW_KEY_W) == GLFW_PRESS) {
            nuevoX -= direccionX * velocidadMovimiento;
            nuevoZ -= direccionZ * velocidadMovimiento;
        }

        // Corrección: S retrocede
        if (glfwGetKey(ventana, GLFW_KEY_S) == GLFW_PRESS) {
            nuevoX += direccionX * velocidadMovimiento;
            nuevoZ += direccionZ * velocidadMovimiento;
        }

        boolean colision = Colisiones.hayColisionConParedes(
                nuevoX,
                nuevoZ,
                y,
                casa,
                radioJugador);

        if (!colision) {
            x = nuevoX;
            z = nuevoZ;
        }

        aplicarEscaleras();

        if (y < 0f) {
            y = 0f;
        }
    }

    private void aplicarEscaleras() {
        /*
         * Escalera 1:
         * Primer piso al segundo piso.
         */
        Escalera escaleraPrimerASegundo = new Escalera(
                4.9f, 7.9f,
                9.5f, 12.0f,
                Constantes.ALTURA_PISO_1,
                Constantes.ALTURA_PISO_2,
                true);

        /*
         * Escalera 2:
         * Segundo piso al tercer piso.
         */
        Escalera escaleraSegundoATercero = new Escalera(
                4.9f, 7.9f,
                9.5f, 12.0f,
                Constantes.ALTURA_PISO_2,
                Constantes.ALTURA_PISO_3,
                true);

        // Convertir posición actual de OpenGL a GeoGebra para la lógica de tramos
        float xGeo = Constantes.CENTRO_GEOGEBRA_X - (x / Constantes.ESCALA_CASA);
        float zGeo = (z / Constantes.ESCALA_CASA) + Constantes.CENTRO_GEOGEBRA_Z;
        float midZ = 10.75f;

        boolean enEscalera1 = false;
        boolean enEscalera2 = false;

        if (escaleraPrimerASegundo.estaDentro(x, z)) {
            if (y < 1.6f) {
                // En el primer piso, solo puede usar la escalera 1 si NO está en el baño de abajo (zGeo < midZ cuando y es muy bajo)
                if (y <= 0.1f && zGeo < midZ) {
                    enEscalera1 = false;
                } else {
                    enEscalera1 = true;
                }
            } else if (y >= 1.6f && y < 3.0f) {
                // Subiendo la escalera 1
                enEscalera1 = true;
            } else if (y >= 3.0f && y <= 3.4f) {
                // Parado en el segundo piso:
                // Si está en el tramo de abajo (zGeo < midZ), va a bajar por la escalera 1
                // Si está en el tramo de arriba (zGeo >= midZ), va a subir por la escalera 2
                if (zGeo < midZ) {
                    enEscalera1 = true;
                } else {
                    enEscalera2 = true;
                }
            } else if (y > 3.4f && y < 6.2f) {
                // Subiendo la escalera 2
                enEscalera2 = true;
            } else if (y >= 6.2f) {
                // Parado en el tercer piso, para bajar entra por el tramo frontal (zGeo < midZ)
                if (zGeo < midZ) {
                    enEscalera2 = true;
                }
            }
        }

        if (enEscalera1) {
            y = escaleraPrimerASegundo.calcularAltura(x, z, y);
            return;
        }

        if (enEscalera2) {
            y = escaleraSegundoATercero.calcularAltura(x, z, y);
            return;
        }

        // Si no está en escalera, lo ajustamos al piso más cercano
        if (y < 1.6f) {
            y = Constantes.ALTURA_PISO_1;
        } else if (y < 4.8f) {
            y = Constantes.ALTURA_PISO_2;
        } else {
            y = Constantes.ALTURA_PISO_3;
        }
    }

    public void dibujar() {
        glPushMatrix();

        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);
        glScalef(escala, escala, escala);

        dibujarTallo();
        dibujarCabeza();
        dibujarPetalos();
        dibujarHojas();

        glPopMatrix();
    }

    private void dibujarTallo() {
        Cubo.dibujar(
                0f, 0.75f, 0f,
                0.18f, 1.5f, 0.18f,
                0.1f, 0.55f, 0.1f);
    }

    private void dibujarCabeza() {
        Cubo.dibujar(
                0f, 1.65f, 0f,
                0.65f, 0.65f, 0.18f,
                0.35f, 0.18f, 0.05f);
    }

    private void dibujarPetalos() {
        float amarilloR = 1.0f;
        float amarilloG = 0.85f;
        float amarilloB = 0.05f;

        Cubo.dibujar(0f, 2.1f, 0f, 0.35f, 0.35f, 0.12f, amarilloR, amarilloG, amarilloB);
        Cubo.dibujar(0f, 1.2f, 0f, 0.35f, 0.35f, 0.12f, amarilloR, amarilloG, amarilloB);
        Cubo.dibujar(-0.45f, 1.65f, 0f, 0.35f, 0.35f, 0.12f, amarilloR, amarilloG, amarilloB);
        Cubo.dibujar(0.45f, 1.65f, 0f, 0.35f, 0.35f, 0.12f, amarilloR, amarilloG, amarilloB);

        Cubo.dibujar(-0.32f, 1.97f, 0f, 0.3f, 0.3f, 0.12f, amarilloR, amarilloG, amarilloB);
        Cubo.dibujar(0.32f, 1.97f, 0f, 0.3f, 0.3f, 0.12f, amarilloR, amarilloG, amarilloB);
        Cubo.dibujar(-0.32f, 1.33f, 0f, 0.3f, 0.3f, 0.12f, amarilloR, amarilloG, amarilloB);
        Cubo.dibujar(0.32f, 1.33f, 0f, 0.3f, 0.3f, 0.12f, amarilloR, amarilloG, amarilloB);
    }

    private void dibujarHojas() {
        Cubo.dibujar(
                -0.35f, 0.85f, 0f,
                0.55f, 0.18f, 0.12f,
                0.05f, 0.45f, 0.05f);

        Cubo.dibujar(
                0.35f, 1.05f, 0f,
                0.55f, 0.18f, 0.12f,
                0.05f, 0.45f, 0.05f);
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

    public float getRotacionY() {
        return rotacionY;
    }

    public float getAlturaOjos() {
        return y + (1.55f * escala);
    }
}
