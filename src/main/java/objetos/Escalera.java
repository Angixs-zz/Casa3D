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

        float zGeo = convertirZGeo(zOpenGL);

        float t = (zGeo - zMinGeo) / (zMaxGeo - zMinGeo);

        if (t < 0) {
            t = 0;
        }

        if (t > 1) {
            t = 1;
        }

        if (!subeConZ) {
            t = 1 - t;
        }

        return alturaInicial + t * (alturaFinal - alturaInicial);
    }

    private float convertirXGeo(float xOpenGL) {
        return Constantes.CENTRO_GEOGEBRA_X - (xOpenGL / Constantes.ESCALA_CASA);
    }

    private float convertirZGeo(float zOpenGL) {
        return (zOpenGL / Constantes.ESCALA_CASA) + Constantes.CENTRO_GEOGEBRA_Z;
    }
}