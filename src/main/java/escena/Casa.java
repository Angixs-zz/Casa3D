package escena;

import java.util.ArrayList;

import objetos.Pared;
import objetos.Punto2D;

public class Casa {

    private ArrayList<Punto2D> puntos;
    private ArrayList<Pared> paredes;

    private double alturaPared = 3.0;
    private double grosorPared = 0.15;

    public Casa() {
        puntos = new ArrayList<>();
        paredes = new ArrayList<>();

        crearPuntosPrimeraPlanta();
        crearParedesPrimeraPlanta();
    }

    private Punto2D buscarPunto(String nombre) {
        for (Punto2D punto : puntos) {
            if (punto.getNombre().equals(nombre)) {
                return punto;
            }
        }

        System.out.println("No se encontró el punto: " + nombre);
        return null;
    }

    private void agregarPunto(String nombre, double x, double y) {
        puntos.add(new Punto2D(nombre, x, y));
    }

    private void agregarPared(String nombre, String puntoInicio, String puntoFin) {
        Punto2D inicio = buscarPunto(puntoInicio);
        Punto2D fin = buscarPunto(puntoFin);

        if (inicio != null && fin != null) {
            paredes.add(new Pared(nombre, inicio, fin, alturaPared, grosorPared));
        }
    }

    private void crearPuntosPrimeraPlanta() {
        agregarPunto("C", 4.8, 1.6);
        agregarPunto("D", 4.8, 0.2);
        agregarPunto("E", 0.1, 0.2);
        agregarPunto("F", 5.9, 0.2);
        agregarPunto("G", 5.9, 1.6);
        agregarPunto("H", 7.9, 0.2);
        agregarPunto("I", 7.9, 5.4);
        agregarPunto("J", 7.9, 9.5);
        agregarPunto("K", 7.9, 10.7);
        agregarPunto("L", 7.9, 20.3);
        agregarPunto("M", 0.1, 20.3);
        agregarPunto("N", 0.1, 5.4);
        agregarPunto("O", 3.8, 5.4);
        agregarPunto("P", 1.3, 5.4);
        agregarPunto("Q", 2.7, 5.4);
        agregarPunto("R", 4.8, 5.4);
        agregarPunto("S", 5.8, 5.4);
        agregarPunto("T", 4.5, 6.5);
        agregarPunto("U", 3.9, 6.5);
        agregarPunto("V", 3.9, 8.4);
        agregarPunto("W", 0.1, 8.4);
        agregarPunto("Z", 4.9, 9.5);

        agregarPunto("A1", 4.9, 10.7);
        agregarPunto("B1", 4.9, 12.0);
        agregarPunto("C1", 3.9, 12.0);
        agregarPunto("D1", 0.1, 14.0);
        agregarPunto("E1", 3.9, 14.0);
        agregarPunto("F1", 2.5, 14.0);
        agregarPunto("G1", 2.5, 14.5);
        agregarPunto("H1", 3.9, 14.5);
        agregarPunto("I1", 4.9, 14.5);
        agregarPunto("J1", 4.9, 14.0);
        agregarPunto("K1", 5.7, 14.0);
        agregarPunto("L1", 7.3, 14.0);
        agregarPunto("M1", 7.9, 14.0);
        agregarPunto("N1", 7.9, 12.0);
        agregarPunto("O1", 4.9, 15.4);
        agregarPunto("P1", 4.9, 18.6);
        agregarPunto("Q1", 7.9, 18.6);
        agregarPunto("R1", 3.9, 16.1);
        agregarPunto("S1", 3.9, 15.4);
        agregarPunto("T1", 0.1, 15.4);
        agregarPunto("U1", 0.1, 18.6);
        agregarPunto("V1", 3.9, 18.6);
        agregarPunto("W1", 5.7, 18.6);
        agregarPunto("Z1", 7.2, 18.6);

        agregarPunto("A2", 1.6, 18.6);
        agregarPunto("B2", 3.1, 18.6);
        agregarPunto("C2", 3.9, 17.0);
        agregarPunto("D2", 6.8, 10.7);
    }

    private void crearParedesPrimeraPlanta() {
        agregarPared("f", "E", "D");
        agregarPared("g", "D", "C");
        agregarPared("h", "G", "F");
        agregarPared("i", "F", "H");
        agregarPared("j", "H", "I");
        agregarPared("k", "I", "S");

        agregarPared("l", "N", "P");
        agregarPared("m", "Q", "O");
        agregarPared("n", "R", "T");
        agregarPared("p", "T", "U");
        agregarPared("q", "U", "V");
        agregarPared("r", "N", "E");
        agregarPared("s", "N", "W");

        agregarPared("t", "J", "I");
        agregarPared("a", "Z", "J");
        agregarPared("b", "J", "K");
        agregarPared("c", "K", "N1");
        agregarPared("d", "N1", "B1");
        agregarPared("e", "A1", "D2");

        agregarPared("f1", "C1", "V");
        agregarPared("g1", "V", "W");
        agregarPared("h1", "D1", "W");
        agregarPared("i1", "J1", "K1");
        agregarPared("j1", "J1", "I1");

        agregarPared("k1", "O1", "P1");
        agregarPared("l1", "P1", "W1");
        agregarPared("m1", "W1", "Z1");
        agregarPared("n1", "Z1", "Q1");
        agregarPared("p1", "Q1", "M1");
        agregarPared("q1", "M1", "L1");
        agregarPared("r1", "L1", "K1");

        agregarPared("s1", "E1", "H1");
        agregarPared("t1", "G1", "F1");
        agregarPared("a1", "F1", "E1");
        agregarPared("b1", "F1", "D1");
        agregarPared("c1", "D1", "T1");
        agregarPared("d1", "T1", "S1");
        agregarPared("e1", "S1", "R1");

        agregarPared("f2", "U1", "T1");
        agregarPared("g2", "U1", "A2");
        agregarPared("h2", "A2", "B2");
        agregarPared("i2", "B2", "V1");
        agregarPared("j2", "V1", "C2");
        agregarPared("k2", "U1", "M");

        agregarPared("l2", "M", "L");
        agregarPared("m2", "L", "Q1");
        agregarPared("n2", "M1", "N1");
    }

    public void mostrarPuntos() {
        System.out.println("========== PUNTOS DE LA PRIMERA PLANTA ==========");

        for (Punto2D punto : puntos) {
            System.out.println(punto);
        }
    }

    public void mostrarParedes() {
        System.out.println("========== PAREDES DE LA PRIMERA PLANTA ==========");

        for (Pared pared : paredes) {
            pared.mostrarInformacion();
        }
    }

    public ArrayList<Pared> getParedes() {
    return paredes;
}
}