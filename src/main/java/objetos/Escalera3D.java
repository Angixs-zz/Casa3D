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
        // BARANDALES NEGROS MODERNOS (Estilo Imagen)
        // ==========================================
        float altoBarandal = 0.9f;
        int numPostes1 = 4;
        
        // Barandal Tramo 1 (lado interior Z = 0.02f)
        dibujarBarandalTramo(xInicio1, 0f, xFin1, midH, 0.02f, altoBarandal, colBarandal, numPostes1);

        // Barandal Tramo 1 (lado exterior Z = 2.45f)
        dibujarBarandalTramo(xInicio1, 0f, xFin1, midH, 2.45f, altoBarandal, colBarandal, numPostes1);

        // Barandal de protección del descanso (X = -2.95f en la meseta)
        float zInicioDescanso = -2.45f;
        float zFinDescanso = 2.45f;
        int numPostesDescanso = 5;
        float dxDescanso = 0f;
        float dzDescanso = zFinDescanso - zInicioDescanso;
        float cxDescanso = -2.95f;
        float czDescanso = (zInicioDescanso + zFinDescanso) / 2.0f;
        float lengthDescanso = Math.abs(dzDescanso);
        
        glPushMatrix();
        glTranslatef(cxDescanso, midH, czDescanso);
        glRotatef(90f, 0f, 1f, 0f); // Rotar 90 grados en Y para que corra a lo largo de Z
        Cubo.dibujar(0f, altoBarandal, 0f, lengthDescanso, 0.04f, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]);
        Cubo.dibujar(0f, altoBarandal * 0.66f, 0f, lengthDescanso, 0.02f, 0.02f, colBarandal[0], colBarandal[1], colBarandal[2]);
        Cubo.dibujar(0f, altoBarandal * 0.33f, 0f, lengthDescanso, 0.02f, 0.02f, colBarandal[0], colBarandal[1], colBarandal[2]);
        glPopMatrix();
        
        for(int i=0; i<=numPostesDescanso; i++) {
            float t = (float)i / numPostesDescanso;
            float pz = zInicioDescanso + t * dzDescanso;
            Cubo.dibujar(cxDescanso, midH + altoBarandal/2.0f, pz, 0.04f, altoBarandal, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]);
        }

        // Barandal Tramo 2 (lado interior Z = -0.02f)
        dibujarBarandalTramo(xInicio2, midH, xFin2, alturaTotal, -0.02f, altoBarandal, colBarandal, numPostes1);

        // Barandal Tramo 2 (lado EXTERIOR Z = -2.45f, protegiendo el hueco de la pared invisible)
        dibujarBarandalTramo(xInicio2, midH, xFin2, alturaTotal, -2.45f, altoBarandal, colBarandal, numPostes1);
        
        // Barandal del descanso (lado EXTERIOR Z = -2.45f)
        dibujarBarandalTramo(-1.2f, midH, -3.0f, midH, -2.45f, altoBarandal, colBarandal, 2);

        // Barandal del descanso (lado EXTERIOR Z = 2.45f)
        dibujarBarandalTramo(-1.2f, midH, -3.0f, midH, 2.45f, altoBarandal, colBarandal, 2);

        glPopMatrix();
    }

    private static void dibujarBarandalTramo(float xInicio, float yInicio, float xFin, float yFin, float z, float altoBarandal, float[] colBarandal, int numPostes) {
        float dx = xFin - xInicio;
        float dy = yFin - yInicio;
        float cx = (xInicio + xFin) / 2.0f;
        float cy = (yInicio + yFin) / 2.0f;
        
        float length = (float) Math.sqrt(dx*dx + dy*dy);
        float angle = (float) Math.toDegrees(Math.atan2(dy, dx));
        
        // Barras inclinadas (superior)
        glPushMatrix();
        glTranslatef(cx, cy + altoBarandal, z);
        glRotatef(angle, 0f, 0f, 1f);
        Cubo.dibujar(0f, 0f, 0f, length, 0.04f, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]);
        glPopMatrix();
        
        // Barras inclinadas (media)
        glPushMatrix();
        glTranslatef(cx, cy + altoBarandal * 0.66f, z);
        glRotatef(angle, 0f, 0f, 1f);
        Cubo.dibujar(0f, 0f, 0f, length, 0.02f, 0.02f, colBarandal[0], colBarandal[1], colBarandal[2]);
        glPopMatrix();
        
        // Barras inclinadas (inferior)
        glPushMatrix();
        glTranslatef(cx, cy + altoBarandal * 0.33f, z);
        glRotatef(angle, 0f, 0f, 1f);
        Cubo.dibujar(0f, 0f, 0f, length, 0.02f, 0.02f, colBarandal[0], colBarandal[1], colBarandal[2]);
        glPopMatrix();
        
        // Postes verticales
        for(int i=0; i<=numPostes; i++) {
            float t = (float)i / numPostes;
            float px = xInicio + t * dx;
            float py = yInicio + t * dy;
            Cubo.dibujar(px, py + altoBarandal/2.0f, z, 0.04f, altoBarandal, 0.04f, colBarandal[0], colBarandal[1], colBarandal[2]);
        }
    }
}