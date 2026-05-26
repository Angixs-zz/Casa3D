package objetos;

/**
 * Representa una pared invisible utilizada únicamente para cálculos de
 * colisiones
 * (no se renderiza en la vista 3D).
 */
public class ParedInvisible extends Pared {

    /**
     * Crea una pared invisible con altura por defecto (3.0) y grosor delgado (0.1).
     *
     * @param nombre     Nombre identificador de la pared.
     * @param inicio     Punto 2D de inicio (x, y).
     * @param fin        Punto 2D de fin (x, y).
     * @param alturaBase Altura base (piso) donde se ubica la pared.
     */
    public ParedInvisible(String nombre, Punto2D inicio, Punto2D fin, double alturaBase) {
        // Altura por defecto = 3.0 (misma que una pared normal), Grosor = 0.1
        super(nombre, inicio, fin, 3.0, 0.1, alturaBase);
    }

    /**
     * Crea una pared invisible especificando todas sus dimensiones.
     *
     * @param nombre     Nombre identificador de la pared.
     * @param inicio     Punto 2D de inicio (x, y).
     * @param fin        Punto 2D de fin (x, y).
     * @param altura     Altura de la pared.
     * @param grosor     Grosor de la pared.
     * @param alturaBase Altura base (piso) donde se ubica la pared.
     */
    public ParedInvisible(String nombre, Punto2D inicio, Punto2D fin, double altura, double grosor, double alturaBase) {
        super(nombre, inicio, fin, altura, grosor, alturaBase);
    }
}
