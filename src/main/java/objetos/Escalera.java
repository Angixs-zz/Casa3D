package objetos;

import utilidades.Constantes;

public class Escalera {

    private float xMinGeo;
    private float xMaxGeo;
    private float zMinGeo;
    private float zMaxGeo;

    private float alturaInicial;
    private float alturaFinal;

    private boolean subeConZ;

    public Escalera(
            float xMinGeo,
            float xMaxGeo,
            float zMinGeo,
            float zMaxGeo,
            float alturaInicial,
            float alturaFinal,
            boolean subeConZ
    ) {
        this.xMinGeo = xMinGeo;
        this.xMaxGeo = xMaxGeo;
        this.zMinGeo = zMinGeo;
        this.zMaxGeo = zMaxGeo;
        this.alturaInicial = alturaInicial;
        this.alturaFinal = alturaFinal;
        this.subeConZ = subeConZ;
    }

    public boolean estaDentro(float xOpenGL, float zOpenGL) {
        float xGeo = convertirXGeo(xOpenGL);
        float zGeo = convertirZGeo(zOpenGL);

        return xGeo >= xMinGeo &&
               xGeo <= xMaxGeo &&
               zGeo >= zMinGeo &&
               zGeo <= zMaxGeo;
    }

    public float calcularAltura(float xOpenGL, float zOpenGL, float alturaActual) {
        if (!estaDentro(xOpenGL, zOpenGL)) {
            return alturaActual;
        }

        float xGeo = convertirXGeo(xOpenGL);
        float zGeo = convertirZGeo(zOpenGL);

        float midZ = (zMinGeo + zMaxGeo) / 2.0f; // 10.75f
        float midH = (alturaInicial + alturaFinal) / 2.0f; // 1.6f o 4.8f

        // Límite del descanso en X (GeoGebra 7.3 a 7.9)
        float xDescansoGeo = 7.3f;

        // --- Caso 1: Tramo 1 (Z > midZ, sube de izquierda a derecha) ---
        if (zGeo > midZ) {
            if (xGeo >= xMinGeo && xGeo < xDescansoGeo) {
                // Interpolar de xMinGeo (alturaInicial) a xDescansoGeo (midH)
                float t = (xGeo - xMinGeo) / (xDescansoGeo - xMinGeo);
                if (t < 0f) t = 0f;
                if (t > 1f) t = 1f;
                return alturaInicial + t * (midH - alturaInicial);
            } else {
                // Ya está en el descanso
                return midH;
            }
        }
        
        // --- Caso 2: Descanso (X >= xDescansoGeo en cualquier Z) ---
        if (xGeo >= xDescansoGeo) {
            return midH;
        }

        // --- Caso 3: Tramo 2 (Z <= midZ y X < xDescansoGeo, sube de derecha a izquierda) ---
        // Sube desde el descanso (xDescansoGeo -> midH) hasta el final (xMinGeo -> alturaFinal)
        float t = (xDescansoGeo - xGeo) / (xDescansoGeo - xMinGeo);
        if (t < 0f) t = 0f;
        if (t > 1f) t = 1f;
        return midH + t * (alturaFinal - midH);
    }

    private float convertirXGeo(float xOpenGL) {
        return Constantes.CENTRO_GEOGEBRA_X - (xOpenGL / Constantes.ESCALA_CASA);
    }

    private float convertirZGeo(float zOpenGL) {
        return (zOpenGL / Constantes.ESCALA_CASA) + Constantes.CENTRO_GEOGEBRA_Z;
    }
}