package objetos;

import static org.lwjgl.opengl.GL11.*;

public class Escalera3D {

    public static void dibujar(
            float x,
            float y,
            float z,
            float anchoStairwell,
            float largoStairwell,
            float alturaTotal,
            int escalonesTotales,
            float rotacionY) {
        
        glPushMatrix();
        glTranslatef(x, y, z);
        glRotatef(rotacionY, 0f, 1f, 0f);

        // Escalera en U (ida y vuelta con descanso)
        // Ancho del hueco GeoGebra: 4.9 a 7.9 -> 3.0 unidades GeoGebra = 6.0f en OpenGL
        // Largo del hueco GeoGebra: 9.5 a 12.0 -> 2.5 unidades GeoGebra = 5.0f en OpenGL
        
        float halfW = 3.0f; // local x desde -3.0f (pared derecha GeoGebra 7.9) hasta +3.0f (pared izquierda GeoGebra 4.9)
        float halfL = 2.5f; // local z desde -2.5f (pared frontal GeoGebra 9.5) hasta +2.5f (pared trasera GeoGebra 12.0)
        float midH = alturaTotal / 2.0f; // Altura del descanso (1.6f)
        
        int esc1 = escalonesTotales / 2;
        int esc2 = escalonesTotales - esc1;
        
        // Colores premium estilo casa moderna
        float[] colMadera = {0.36f, 0.20f, 0.09f}; // Café madera elegante
        float[] colEstructura = {0.95f, 0.95f, 0.95f}; // Blanco concreto
        float[] colBarandal = {0.1f, 0.1f, 0.1f}; // Negro acero
        
        // ==========================================
        // TRAMO 1: Sube de izquierda a derecha (Z > 0)
        // ==========================================
        float xInicio1 = 3.0f;  // pared izquierda (GeoGebra 4.9)
        float xFin1 = -1.2f;    // inicio del descanso (GeoGebra 7.3)
        float zMin1 = 0.05f;
        float zMax1 = 2.45f;
        float wTramo1 = zMax1 - zMin1;
        
        float dx1 = xFin1 - xInicio1; // -4.2f
        float stepLength1 = Math.abs(dx1) / esc1;
        float stepHeight1 = midH / esc1;
        
        for (int i = 0; i < esc1; i++) {
            float stepX = xInicio1 - (i * stepLength1) - (stepLength1 / 2.0f);
            float stepY = (i * stepHeight1) + (stepHeight1 / 2.0f);
            float stepZ = (zMin1 + zMax1) / 2.0f;
            
            // Escalón (madera)
            Cubo.dibujar(stepX, stepY, stepZ, stepLength1, 0.08f, wTramo1, colMadera[0], colMadera[1], colMadera[2]);
            
            // Soporte del escalón (concreto blanco)
            Cubo.dibujar(stepX, stepY - 0.06f, stepZ, stepLength1 * 0.9f, stepHeight1 - 0.08f, wTramo1 * 0.95f, colEstructura[0], colEstructura[1], colEstructura[2]);
        }
        
        // ==========================================
        // DESCANSO (MESETA): X entre -1.2f y -3.0f, Z entre -2.5f y +2.5f
        // ==========================================
        float descansoX = -2.1f;
        float descansoY = midH - 0.04f;
        float descansoZ = 0.0f;
        float descansoW = 1.8f; // Ancho en X
        float descansoL = 4.9f; // Largo en Z
        
        // Base de concreto blanca
        Cubo.dibujar(descansoX, descansoY, descansoZ, descansoW, 0.08f, descansoL, colEstructura[0], colEstructura[1], colEstructura[2]);
        // Cubierta de madera
        Cubo.dibujar(descansoX, descansoY + 0.06f, descansoZ, descansoW, 0.02f, descansoL * 0.98f, colMadera[0], colMadera[1], colMadera[2]);
        
        // ==========================================
        // TRAMO 2: Sube de derecha a izquierda (Z < 0)
        // ==========================================
        float xInicio2 = -1.2f;  // sale del descanso
        float xFin2 = 3.0f;     // llega a planta alta
        float zMin2 = -2.45f;
        float zMax2 = -0.05f;
        float wTramo2 = zMax2 - zMin2;
        
        float dx2 = xFin2 - xInicio2; // 4.2f
        float stepLength2 = Math.abs(dx2) / esc2;
        float stepHeight2 = (alturaTotal - midH) / esc2;
        
        for (int i = 0; i < esc2; i++) {
            float stepX = xInicio2 + (i * stepLength2) + (stepLength2 / 2.0f);
            float stepY = midH + (i * stepHeight2) + (stepHeight2 / 2.0f);
            float stepZ = (zMin2 + zMax2) / 2.0f;
            
            // Escalón (madera)
            Cubo.dibujar(stepX, stepY, stepZ, stepLength2, 0.08f, wTramo2, colMadera[0], colMadera[1], colMadera[2]);
            
            // Soporte del escalón (concreto blanco)
            Cubo.dibujar(stepX, stepY - 0.06f, stepZ, stepLength2 * 0.9f, stepHeight2 - 0.08f, wTramo2 * 0.95f, colEstructura[0], colEstructura[1], colEstructura[2]);
        }
        
        // ==========================================
        // BARANDALES NEGROS MODERNOS
        // ==========================================
        // Barandal Tramo 1 (lado interior Z = 0)
        float railY1 = midH / 2.0f + 0.5f;
        Cubo.dibujar(0.9f, railY1, 0.02f, 4.2f, 0.03f, 0.03f, colBarandal[0], colBarandal[1], colBarandal[2]); // pasamanos inclinado
        Cubo.dibujar(2.8f, midH / 4.0f + 0.5f, 0.02f, 0.04f, 1.0f, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]); // postes verticales
        Cubo.dibujar(0.9f, midH / 2.0f + 0.5f, 0.02f, 0.04f, 1.0f, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]);
        Cubo.dibujar(-1.0f, 3.0f * midH / 4.0f + 0.5f, 0.02f, 0.04f, 1.0f, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]);

        // Barandal Tramo 2 (lado interior Z = 0)
        float railY2 = midH + midH / 2.0f + 0.5f;
        Cubo.dibujar(0.9f, railY2, -0.02f, 4.2f, 0.03f, 0.03f, colBarandal[0], colBarandal[1], colBarandal[2]);
        Cubo.dibujar(-1.0f, midH + midH / 4.0f + 0.5f, -0.02f, 0.04f, 1.0f, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]);
        Cubo.dibujar(0.9f, midH + midH / 2.0f + 0.5f, -0.02f, 0.04f, 1.0f, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]);
        Cubo.dibujar(2.8f, midH + 3.0f * midH / 4.0f + 0.5f, -0.02f, 0.04f, 1.0f, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]);
        
        // Barandal de protección del descanso (X = -3.0f en la meseta)
        Cubo.dibujar(-2.95f, midH + 0.5f, 0.0f, 0.03f, 0.03f, 4.8f, colBarandal[0], colBarandal[1], colBarandal[2]); // pasamanos
        Cubo.dibujar(-2.95f, midH + 0.5f, -2.3f, 0.04f, 1.0f, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]); // postes descanso
        Cubo.dibujar(-2.95f, midH + 0.5f, 0.0f, 0.04f, 1.0f, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]);
        Cubo.dibujar(-2.95f, midH + 0.5f, 2.3f, 0.04f, 1.0f, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]);

        glPopMatrix();
    }
}