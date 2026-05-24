package objetos;

import escena.Casa;
import objetos.Puerta;
import utilidades.Constantes;

public class Colisiones {

    public static boolean hayColisionConParedes(
            float xJugador,
            float zJugador,
            float yJugador,
            Casa casa,
            float radioJugador) {

        // Determinar en qué piso está el jugador según su coordenada Y
        int pisoJugador = 1;
        if (yJugador >= 3.0f)
            pisoJugador = 2;
        if (yJugador >= 6.2f)
            pisoJugador = 3;

        for (Pared pared : casa.getParedes()) {
            float alturaBase = (float) pared.getAlturaBase();

            // Determinar a qué piso pertenece la pared según su alturaBase
            int pisoPared = 1;
            if (alturaBase > 3.0f)
                pisoPared = 2;
            if (alturaBase > 6.0f)
                pisoPared = 3;

            // Solo revisa paredes del piso donde está el jugador
            if (pisoPared != pisoJugador) {
                continue;
            }

            // CORRECCIÓN VERTICAL (3D): Omitir colisión si el personaje pasa completamente
            // por debajo de una pared elevada (dintel) o por encima de una baja.
            float alturaPersonaje = 1.6f; // Altura estimada de la caja de impacto del personaje
            if ((yJugador + alturaPersonaje) < alturaBase || yJugador > (alturaBase + pared.getAltura())) {
                continue;
            }

            // Si es la pared de fondo del baño bajo la escalera, no colisiona si el jugador
            // está arriba en la escalera (yJugador >= 1.0f)
            if (pared.getNombre().equals("pared_bano_fondo") && yJugador >= 1.0f) {
                continue;
            }

            Punto2D inicio = pared.getInicio();
            Punto2D fin = pared.getFin();

            float x1 = convertirX((float) inicio.getX());
            float z1 = convertirZ((float) inicio.getY());

            float x2 = convertirX((float) fin.getX());
            float z2 = convertirZ((float) fin.getY());

            float distancia = distanciaPuntoSegmento(
                    xJugador,
                    zJugador,
                    x1,
                    z1,
                    x2,
                    z2);

            float grosorPared = (float) pared.getGrosor() * Constantes.ESCALA_CASA;

            if (distancia < radioJugador + grosorPared) {
                return true;
            }
        }

        // Revisar colisión con puertas (abiertas o cerradas)
        for (Puerta puerta : casa.getPuertas()) {
            // Determinar piso de la puerta según su Y base
            int pisoPuerta = 1;
            if (puerta.getY() > 3.0f)
                pisoPuerta = 2;
            if (puerta.getY() > 6.0f)
                pisoPuerta = 3;

            if (pisoPuerta != pisoJugador)
                continue;

            float px1 = puerta.getX();
            float pz1 = puerta.getZ();
            float thetaRad = (float) Math.toRadians(puerta.getRotacionBase() + puerta.getAnguloActual());
            float px2, pz2;

            if (puerta.isEjeX()) {
                px2 = px1 + puerta.getAncho() * (float) Math.cos(thetaRad);
                pz2 = pz1 - puerta.getAncho() * (float) Math.sin(thetaRad);
            } else {
                px2 = px1 + puerta.getAncho() * (float) Math.sin(thetaRad);
                pz2 = pz1 + puerta.getAncho() * (float) Math.cos(thetaRad);
            }

            float distancia = distanciaPuntoSegmento(xJugador, zJugador, px1, pz1, px2, pz2);
            if (distancia < radioJugador + 0.1f) {
                return true;
            }
        }

        return false;
    }

    private static float convertirX(float xGeoGebra) {
        float x = xGeoGebra - Constantes.CENTRO_GEOGEBRA_X;
        x = -x;
        return x * Constantes.ESCALA_CASA;
    }

    private static float convertirZ(float zGeoGebra) {
        float z = zGeoGebra - Constantes.CENTRO_GEOGEBRA_Z;
        return z * Constantes.ESCALA_CASA;
    }

    private static float distanciaPuntoSegmento(
            float px,
            float pz,
            float x1,
            float z1,
            float x2,
            float z2) {
        float dx = x2 - x1;
        float dz = z2 - z1;

        if (dx == 0 && dz == 0) {
            return distancia(px, pz, x1, z1);
        }

        float t = ((px - x1) * dx + (pz - z1) * dz) / (dx * dx + dz * dz);

        if (t < 0) {
            t = 0;
        }

        if (t > 1) {
            t = 1;
        }

        float puntoCercanoX = x1 + t * dx;
        float puntoCercanoZ = z1 + t * dz;

        return distancia(px, pz, puntoCercanoX, puntoCercanoZ);
    }

    private static float distancia(float x1, float z1, float x2, float z2) {
        float dx = x2 - x1;
        float dz = z2 - z1;

        return (float) Math.sqrt(dx * dx + dz * dz);
    }

    // ==========================================================
    // SISTEMA DE COLISIONES PARA MUEBLES (ESCALABLE)
    // ==========================================================
    public static boolean hayColisionConMuebles(float xJugador, float zJugador, float yJugador, float radioJugador) {
        // Por ahora, todos los muebles están en la planta baja (y < 3.0f)
        if (yJugador > 3.0f) {
            return false;
        }

        float radioGeo = radioJugador / Constantes.ESCALA_CASA;
        float xGeo = (-xJugador / Constantes.ESCALA_CASA) + Constantes.CENTRO_GEOGEBRA_X;
        float zGeo = (zJugador / Constantes.ESCALA_CASA) + Constantes.CENTRO_GEOGEBRA_Z;

        // Lista de Cajas Delimitadoras (AABB) de los muebles en coordenadas de GeoGebra
        // Formato: { xMin, zMin, xMax, zMax }
        float[][] boundingBoxesMuebles = {
            // === MUEBLES DE LA OFICINA ===
            // Escritorio en L (sus 3 secciones)
            {0.2f, 8.0f, 3.8f, 8.4f},
            {3.2f, 7.0f, 3.8f, 8.0f},
            {1.9f, 6.5f, 3.8f, 7.0f},
            // Silla de oficina
            {2.15f, 7.15f, 2.85f, 7.85f},
            // Sofá Largo
            {0.1f, 5.7f, 0.9f, 8.1f},
            // Sillones individuales de visita
            {1.3f, 5.45f, 2.1f, 6.25f}, // Sillon 1
            {2.1f, 5.45f, 2.9f, 6.25f}, // Sillon 2
            // Gabinete lateral (junto al sofá)
            {0.1f, 8.0f, 0.9f, 8.4f},
            // Planta de esquina (junto al sofá)
            {0.3f, 5.35f, 0.7f, 5.75f}
            
            // Aquí puedes agregar fácilmente las colisiones para la sala, comedor, cocina, etc.
        };

        for (float[] mueble : boundingBoxesMuebles) {
            float xMin = mueble[0];
            float zMin = mueble[1];
            float xMax = mueble[2];
            float zMax = mueble[3];

            if (colisionCirculoRectangulo(xGeo, zGeo, radioGeo, xMin, zMin, xMax, zMax)) {
                return true;
            }
        }

        return false;
    }

    private static boolean colisionCirculoRectangulo(float cX, float cZ, float radio, float rXMin, float rZMin, float rXMax, float rZMax) {
        // Encuentra el punto más cercano en el rectángulo al centro del círculo
        float closestX = Math.max(rXMin, Math.min(cX, rXMax));
        float closestZ = Math.max(rZMin, Math.min(cZ, rZMax));

        // Calcula la distancia entre el centro del círculo y este punto más cercano
        float distanciaX = cX - closestX;
        float distanciaZ = cZ - closestZ;

        // Si la distancia es menor al radio, hay colisión
        return (distanciaX * distanciaX + distanciaZ * distanciaZ) < (radio * radio);
    }
}