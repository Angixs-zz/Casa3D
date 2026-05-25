package escena;

import java.util.ArrayList;

import objetos.Pared;
import objetos.Puerta;
import objetos.Punto2D;

public class Casa {

    private ArrayList<Punto2D> puntos;
    private ArrayList<Pared> paredes;
    private ArrayList<Puerta> puertas;

    private double alturaPared = 3.2;
    private double grosorPared = 0.15;

    public Casa() {
        puntos = new ArrayList<>();
        paredes = new ArrayList<>();
        puertas = new ArrayList<>();

        crearPuntosPrimeraPlanta();
        crearParedesPrimeraPlanta();

        crearPuntosSegundaPlanta();
        crearParedesSegundaPlanta();

        crearPuntosTerceraPlanta();
        crearParedesTerceraPlanta();
    }

    public void agregarPuerta(Puerta puerta) {
        puertas.add(puerta);
    }

    public ArrayList<Puerta> getPuertas() {
        return puertas;
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

    private void agregarPared(String nombre, String puntoInicio, String puntoFin, double alturaBase) {
        Punto2D inicio = buscarPunto(puntoInicio);
        Punto2D fin = buscarPunto(puntoFin);

        if (inicio != null && fin != null) {
            paredes.add(new Pared(nombre, inicio, fin, alturaPared, grosorPared, alturaBase));
        }
    }

    private void agregarPared(String nombre, String puntoInicio, String puntoFin, double alturaBase,
            double alturaCustom) {
        Punto2D inicio = buscarPunto(puntoInicio);
        Punto2D fin = buscarPunto(puntoFin);

        if (inicio != null && fin != null) {
            paredes.add(new Pared(nombre, inicio, fin, alturaCustom, grosorPared, alturaBase));
        }
    }

    private void agregarVentana(String nombre, String puntoInicio, String puntoFin) {
        Punto2D inicio = buscarPunto(puntoInicio);
        Punto2D fin = buscarPunto(puntoFin);
        if (inicio != null && fin != null) {
            Pared ventana = new Pared(nombre, inicio, fin, alturaPared, grosorPared, 0.0);
            ventana.setTipo(Pared.TIPO_VENTANA);
            paredes.add(ventana);
        }
    }

    private void agregarVentanal(String nombre, String puntoInicio, String puntoFin) {
        Punto2D inicio = buscarPunto(puntoInicio);
        Punto2D fin = buscarPunto(puntoFin);
        if (inicio != null && fin != null) {
            Pared ventanal = new Pared(nombre, inicio, fin, alturaPared, grosorPared, 0.0);
            ventanal.setTipo(Pared.TIPO_VENTANAL);
            paredes.add(ventanal);
        }
    }

    private void agregarVentanaPiso2(String nombre, String puntoInicio, String puntoFin) {
        Punto2D inicio = buscarPunto(puntoInicio);
        Punto2D fin = buscarPunto(puntoFin);
        if (inicio != null && fin != null) {
            Pared ventana = new Pared(nombre, inicio, fin, alturaPared, grosorPared, 3.2);
            ventana.setTipo(Pared.TIPO_VENTANA);
            paredes.add(ventana);
        }
    }

    private void agregarVentanalPiso2(String nombre, String puntoInicio, String puntoFin) {
        Punto2D inicio = buscarPunto(puntoInicio);
        Punto2D fin = buscarPunto(puntoFin);
        if (inicio != null && fin != null) {
            Pared ventanal = new Pared(nombre, inicio, fin, alturaPared, grosorPared, 3.2);
            ventanal.setTipo(Pared.TIPO_VENTANAL);
            paredes.add(ventanal);
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
        agregarPunto("U", 3.8, 6.5);
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
        agregarPunto("D3", 6.8, 9.5); // Punto de fondo del baño bajo la escalera
        agregarPunto("E2", 2.5, 15.4);
    }

    private void crearParedesPrimeraPlanta() {
        agregarPared("f", "E", "D", 0.0);
        agregarPared("g", "D", "C", 0.0);
        agregarPared("h", "G", "F", 0.0);
        agregarPared("i", "F", "H", 0.0);
        agregarPared("j", "H", "I", 0.0);
        agregarVentanal("k", "I", "S");

        agregarPared("l", "N", "P", 0.0);
        agregarVentana("Ventana_PQ", "P", "Q");
        agregarPared("m", "Q", "O", 0.0);
        agregarPared("n", "R", "T", 0.0);
        agregarPared("p", "T", "U", 0.0);
        agregarPared("q", "U", "V", 0.0);
        agregarPared("r", "N", "E", 0.0);
        agregarPared("s", "N", "W", 0.0);

        agregarPared("t", "J", "I", 0.0);
        agregarPared("a", "Z", "J", 0.0);
        agregarVentana("b", "J", "K");
        agregarPared("c", "K", "N1", 0.0);
        agregarPared("d", "N1", "B1", 0.0); // Restaurada para colisión invisible
        agregarPared("e", "A1", "D2", 0.0);
        agregarPared("pared_bano_fondo", "D2", "K", 0.0, 1.6); // Pared física para cerrar el baño (altura 1.6 para no
                                                               // obstruir escaleras)

        agregarPared("f1", "C1", "V", 0.0);
        agregarPared("g1", "V", "W", 0.0);
        agregarPared("h1", "D1", "W", 0.0);
        agregarPared("i1", "J1", "K1", 0.0);
        agregarPared("j1", "J1", "I1", 0.0);

        agregarPared("k1", "O1", "P1", 0.0);
        // La pared l1 se reemplaza por la puerta con bisagra en P1 (hacia W1)
        agregarPared("Dintel Puerta P1", "P1", "W1", 2.7, 0.5);

        agregarVentana("m1", "W1", "Z1");
        agregarPared("n1", "Z1", "Q1", 0.0);
        agregarPared("p1", "Q1", "M1", 0.0);
        agregarPared("q1", "M1", "L1", 0.0);
        agregarVentanal("r1", "L1", "K1");

        agregarPared("s1", "E1", "H1", 0.0);
        agregarPared("t1", "G1", "F1", 0.0);
        agregarPared("a1", "F1", "E1", 0.0);
        agregarPared("b1", "F1", "D1", 0.0);
        agregarVentana("c1", "D1", "T1");
        agregarPared("d1", "T1", "S1", 0.0);
        agregarPared("e1", "S1", "R1", 0.0);

        agregarPared("f2", "U1", "T1", 0.0);
        agregarPared("g2", "U1", "A2", 0.0);
        agregarVentana("h2", "A2", "B2");
        agregarPared("i2", "B2", "V1", 0.0);
        agregarPared("j2", "V1", "C2", 0.0);
        agregarPared("k2", "U1", "M", 0.0);

        agregarPared("l2", "M", "L", 0.0);
        agregarPared("m2", "L", "Q1", 0.0);
        agregarPared("n2", "M1", "N1", 0.0);

        // Dintel Puerta Principal (Hueco entre C y G)
        agregarPared("Dintel Principal", "C", "G", 2.7, 0.5);

        // Dintel Cuarto S (Hueco entre R y S)
        agregarPared("Dintel Cuarto S", "R", "S", 2.7, 0.5);

        // Dintel Despacho (Hueco entre O y U)
        agregarPared("Dintel Despacho", "O", "U", 2.7, 0.5);

        // Dintel GYM (Hueco entre I1 y O1)
        agregarPared("Dintel GYM", "I1", "O1", 2.7, 0.5);

        // Dintel Baño2
        agregarPared("Dintel baño2", "G1", "E2", 2.7, 0.5);

        // Dintel Recamara 1
        agregarPared("Dintel Recamara 1", "R1", "C2", 2.7, 0.5);

        agregarPared("Dintel GYM Trasera", "V1", "P1", 2.7, 0.5);
    }

    private void crearPuntosSegundaPlanta() {
        agregarPunto("P2_C", 4.9, 12.0);
        agregarPunto("P2_D", 4.9, 10.7);
        agregarPunto("P2_E", 4.9, 9.5);
        agregarPunto("P2_F", 6.8, 10.7);
        agregarPunto("P2_G", 7.9, 12.0);
        agregarPunto("P2_H", 7.9, 9.5);
        agregarPunto("P2_I", 0.1, 0.2);
        agregarPunto("P2_J", 0.1, 1.6);
        agregarPunto("P2_K", 0.1, 7.9);
        agregarPunto("P2_L", 0.1, 9.5);
        agregarPunto("P2_M", 3.9, 7.9);
        agregarPunto("P2_N", 3.5, 4.4);
        agregarPunto("P2_O", 3.5, 5.5);
        agregarPunto("P2_P", 3.9, 5.5);
        agregarPunto("P2_Q", 3.9, 7.0);
        agregarPunto("P2_R", 4.9, 5.3);
        agregarPunto("P2_S", 4.9, 5.9);
        agregarPunto("P2_T", 7.9, 5.9);
        agregarPunto("P2_U", 7.9, 4.3);
        agregarPunto("P2_U_aux", 7.1, 4.3);
        agregarPunto("P2_V", 6.6, 4.3);
        agregarPunto("P2_W", 6.6, 3.5);
        agregarPunto("P2_Z", 4.9, 3.5);

        agregarPunto("P2_A1", 4.9, 4.3);
        agregarPunto("P2_B1", 6.6, 1.6);
        agregarPunto("P2_C1", 2.6, 1.6);
        agregarPunto("P2_Q2_aux", 2.3, 1.6);
        agregarPunto("P2_D1", 5.9, 1.6);
        agregarPunto("P2_E1", 5.9, 0.2);
        agregarPunto("P2_F1", 7.9, 0.2);
        agregarPunto("P2_G1", 7.9, 1.6);
        agregarPunto("P2_H1", 4.8, 1.6);
        agregarPunto("P2_I1", 4.8, 0.2);
        agregarPunto("P2_J1", 0.1, 4.4);
        agregarPunto("P2_K1", 1.8, 7.9);
        agregarPunto("P2_L1", 4.9, 7.0);
        agregarPunto("P2_M1", 3.9, 9.5);
        agregarPunto("P2_N1", 4.9, 13.9);
        agregarPunto("P2_N4", 2.8, 9.5);
        agregarPunto("P2_O1", 7.9, 13.9);
        agregarPunto("P2_P1", 5.8, 13.9);
        agregarPunto("P2_Q1", 7.3, 13.9);
        agregarPunto("P2_R1", 3.9, 13.9);
        agregarPunto("P2_S1", 2.6, 13.9);
        agregarPunto("P2_T1", 0.1, 13.9);
        agregarPunto("P2_U1", 0.1, 15.4);
        agregarPunto("P2_V1", 3.9, 15.4);
        agregarPunto("P2_W1", 3.9, 16.1);
        agregarPunto("P2_Z1", 2.6, 14.5);

        agregarPunto("P2_A2", 3.9, 14.5);
        agregarPunto("P2_B2", 4.9, 16.1);
        agregarPunto("P2_C2", 7.9, 17.3);
        agregarPunto("P2_D2", 4.9, 17.3);
        agregarPunto("P2_E2", 4.9, 17.0);
        agregarPunto("P2_F2", 3.9, 17.0);
        agregarPunto("P2_G2", 3.9, 17.3);
        agregarPunto("P2_H2", 3.9, 18.6);
        agregarPunto("P2_I2", 3.1, 18.6);
        agregarPunto("P2_J2", 1.6, 18.6);
        agregarPunto("P2_K2", 0.1, 18.6);
        agregarPunto("P2_L2", 6.0, 17.3);
        agregarPunto("P2_M2", 6.0, 18.3);
        agregarPunto("P2_N2", 3.9, 20.3);
        agregarPunto("P2_O2", 6.0, 20.3);
        agregarPunto("P2_P2", 7.9, 20.3);
        agregarPunto("P2_Q2", 1.2, 1.6);
        agregarPunto("P2_R2", 6.6, 1.9);
        agregarPunto("P2_S2", 6.6, 3.7);
        agregarPunto("P2_T2", 6.6, 4.0);
        agregarPunto("P2_U2", 2.6, 15.4);
        agregarPunto("P2_V2", 3.9, 8.6);
    }

    private void crearParedesSegundaPlanta() {
        double alturaSegundoPiso = 3.2;

        // agregarPared("P2_f", "P2_I", "P2_J", alturaSegundoPiso);
        agregarPared("P2_g", "P2_J", "P2_Q2", alturaSegundoPiso);
        agregarVentanalPiso2("Ventanal Q2_aux-C1", "P2_Q2_aux", "P2_C1"); // Ventanal con puerta
        agregarPared("P2_i", "P2_C1", "P2_H1", alturaSegundoPiso);
        // agregarPared("P2_j", "P2_H1", "P2_I1", alturaSegundoPiso);
        // agregarPared("P2_k", "P2_I1", "P2_I", alturaSegundoPiso);

        agregarPared("P2_l", "P2_H1", "P2_D1", alturaSegundoPiso);
        agregarPared("P2_m", "P2_D1", "P2_E1", alturaSegundoPiso);
        agregarPared("P2_n", "P2_E1", "P2_F1", alturaSegundoPiso);
        agregarPared("P2_p", "P2_F1", "P2_G1", alturaSegundoPiso);
        agregarPared("P2_q", "P2_G1", "P2_B1", alturaSegundoPiso);
        agregarPared("P2_r", "P2_B1", "P2_D1", alturaSegundoPiso);

        agregarPared("P2_s", "P2_B1", "P2_R2", alturaSegundoPiso);
        agregarVentanaPiso2("Ventana R2-W", "P2_R2", "P2_W"); // Ventana W a R2
        agregarPared("P2_a", "P2_W", "P2_Z", alturaSegundoPiso);
        agregarPared("P2_b", "P2_Z", "P2_A1", alturaSegundoPiso);

        agregarPared("P2_c", "P2_W", "P2_S2", alturaSegundoPiso);
        agregarPared("P2_d", "P2_V", "P2_T2", alturaSegundoPiso);
        agregarPared("P2_e", "P2_V", "P2_U_aux", alturaSegundoPiso);
        agregarVentanaPiso2("Ventana U_aux-U", "P2_U_aux", "P2_U"); // Ventana de U_aux a U
        agregarPared("P2_f1", "P2_T", "P2_U", alturaSegundoPiso);
        agregarPared("P2_g1", "P2_T", "P2_S", alturaSegundoPiso);
        agregarPared("P2_h1", "P2_S", "P2_R", alturaSegundoPiso);

        agregarPared("P2_i1", "P2_J", "P2_J1", alturaSegundoPiso);
        agregarPared("P2_j1", "P2_J1", "P2_N", alturaSegundoPiso);
        agregarPared("P2_k1", "P2_N", "P2_O", alturaSegundoPiso);
        agregarPared("P2_l1", "P2_O", "P2_P", alturaSegundoPiso);
        agregarVentanaPiso2("Ventana P-Q", "P2_P", "P2_Q"); // Ventana P a Q (p6 a q6)
        agregarPared("P2_n1", "P2_J1", "P2_K", alturaSegundoPiso);
        agregarPared("P2_p1", "P2_K1", "P2_M", alturaSegundoPiso);

        // Pared correcta del closet / zona de escalera
        agregarPared("P2_q1", "P2_H", "P2_E", alturaSegundoPiso);

        // Esta es la correcta: E con L1
        agregarPared("P2_r1", "P2_E", "P2_L1", alturaSegundoPiso);

        agregarPared("P2_s1", "P2_T", "P2_H", alturaSegundoPiso);
        agregarPared("P2_t1", "P2_H", "P2_G", alturaSegundoPiso);
        agregarPared("P2_a1", "P2_G", "P2_O1", alturaSegundoPiso);
        agregarPared("P2_b1", "P2_O1", "P2_Q1", alturaSegundoPiso);
        agregarPared("P2_c1", "P2_P1", "P2_N1", alturaSegundoPiso);

        // Esta es L con N4
        agregarPared("P2_e1", "P2_L", "P2_N4", alturaSegundoPiso);
        agregarVentanalPiso2("Ventanal M1-N4", "P2_M1", "P2_N4"); // Ventanal M1 a N4

        agregarPared("P2_f2", "P2_T1", "P2_S1", alturaSegundoPiso);
        agregarPared("P2_g2", "P2_S1", "P2_Z1", alturaSegundoPiso);
        agregarPared("P2_h2", "P2_S1", "P2_R1", alturaSegundoPiso);
        agregarPared("P2_i2", "P2_R1", "P2_A2", alturaSegundoPiso);
        agregarPared("P2_j2", "P2_N1", "P2_B2", alturaSegundoPiso);

        agregarPared("P2_k2", "P2_E2", "P2_D2", alturaSegundoPiso);
        agregarPared("P2_l2", "P2_D2", "P2_L2", alturaSegundoPiso);
        agregarPared("P2_m2", "P2_L2", "P2_C2", alturaSegundoPiso);
        agregarPared("P2_n2", "P2_C2", "P2_O1", alturaSegundoPiso);

        agregarPared("P2_p2", "P2_W1", "P2_V1", alturaSegundoPiso);
        agregarPared("P2_q2", "P2_L", "P2_T1", alturaSegundoPiso);
        agregarPared("P2_r2", "P2_T1", "P2_U1", alturaSegundoPiso);
        agregarPared("P2_s2", "P2_U1", "P2_U2", alturaSegundoPiso);
        agregarPared("P2_t2", "P2_U2", "P2_V1", alturaSegundoPiso);
        agregarPared("P2_a2", "P2_U1", "P2_K2", alturaSegundoPiso);
        agregarPared("P2_b2", "P2_K2", "P2_J2", alturaSegundoPiso);

        agregarPared("P2_c2", "P2_I2", "P2_H2", alturaSegundoPiso);
        agregarPared("P2_d2", "P2_H2", "P2_G2", alturaSegundoPiso);
        agregarPared("P2_e2", "P2_G2", "P2_F2", alturaSegundoPiso);
        agregarPared("P2_f3", "P2_H2", "P2_N2", alturaSegundoPiso);
        agregarPared("P2_g3", "P2_N2", "P2_O2", alturaSegundoPiso);
        agregarPared("Semimuro O2-P2", "P2_O2", "P2_P2", alturaSegundoPiso, 1.0); // Semimuro
        agregarPared("Semimuro P2-C2", "P2_P2", "P2_C2", alturaSegundoPiso, 1.0); // Semimuro
        agregarVentanaPiso2("Ventana O2-M2", "P2_O2", "P2_M2"); // Ventana O2 a M2 (q3 a m2)
        agregarVentanaPiso2("Ventana J2-I2", "P2_J2", "P2_I2"); // Ventana J2 a I2

        agregarVentanaPiso2("Ventana P1-Q1", "P2_P1", "P2_Q1"); // Ventana P1 a Q1
        agregarPared("P2_d1", "P2_C", "P2_G", alturaSegundoPiso);
        // agregarPared("P2_m3", "P2_F", "P2_D", alturaSegundoPiso);
        agregarVentanaPiso2("Ventana K-K1", "P2_K", "P2_K1"); // Ventana de K a K1
        agregarPared("P2_p3", "P2_S2", "P2_T2", alturaSegundoPiso);
        agregarPared("Semimuro K-L", "P2_K", "P2_L", alturaSegundoPiso, 1.0); // Semimuro pozo de luz
        agregarPared("P2_r3", "P2_M", "P2_V2", alturaSegundoPiso);

        // Dintel Puerta Balcon
        agregarPared("Dintel Puerta Balcon", "P2_Q2", "P2_Q2_aux", 5.9, 0.5);

        // Dintel Pozo Luz
        agregarPared("Dintel Pozo LUz", "P2_V2", "P2_M1", 5.9, 0.5);

        // Dintel Recamara 4
        agregarPared("Dintel Recamara 4", "P2_Q", "P2_M", 5.9, 0.5);

        // Dintel Recamara principal
        agregarPared("Dintel Recamara Principal", "P2_Q", "P2_L1", 5.9, 0.5);

        // Dintel CLOSET
        agregarPared("Dintel Closet", "P2_S", "P2_L1", 5.9, 0.5);
        // Dintel BAÑOPRINCIPAL
        agregarPared("Dintel BAÑO PRINCIPAL", "P2_A1", "P2_R", 5.9, 0.5);
        // Dintel Lavanderia
        agregarPared("Dintel Lavanderia", "P2_G2", "P2_D2", 5.9, 0.5);
        // Dintel Recamara2
        agregarPared("Dintel Recamara2", "P2_F2", "P2_W1", 5.9, 0.5);
        // Dintel Recamara3
        agregarPared("Dintel Recamara2", "P2_B2", "P2_E2", 5.9, 0.5);
        // Dintel AireLibre
        agregarPared("Dintel AireLibre", "P2_M2", "P2_L2", 5.9, 0.5);
        // Dintel Baño2
        agregarPared("Dintel Baño2", "P2_Z1", "P2_U2", 5.9, 0.5);
    }

    private void crearPuntosTerceraPlanta() {
        agregarPunto("P3_C", 4.9, 12.0);
        agregarPunto("P3_D", 4.9, 10.7);
        agregarPunto("P3_E", 4.9, 9.5);
        agregarPunto("P3_F", 7.9, 12.0);
        agregarPunto("P3_G", 7.9, 9.5);
        agregarPunto("P3_H", 6.8, 10.7);
        agregarPunto("P3_I", 7.9, 4.2);
        agregarPunto("P3_J", 6.6, 4.2);
        agregarPunto("P3_K", 6.6, 1.5);
        agregarPunto("P3_L", 0.1, 1.5);
        agregarPunto("P3_M", 0.1, 2.6);
        agregarPunto("P3_N", 4.9, 13.9);
        agregarPunto("P3_O", 7.9, 13.9);
        agregarPunto("P3_P", 7.9, 17.4);
        agregarPunto("P3_Q", 5.9, 17.4);
        agregarPunto("P3_R", 5.9, 20.3);
        agregarPunto("P3_S", 3.8, 20.3);
        agregarPunto("P3_T", 3.8, 18.6);
        agregarPunto("P3_U", 0.1, 18.6);
        agregarPunto("P3_V", 0.1, 15.4);
        agregarPunto("P3_W", 0.1, 14.0);
        agregarPunto("P3_Z", 0.1, 9.5);

        agregarPunto("P3_A1", 3.8, 9.5);
        agregarPunto("P3_B1", 3.8, 7.0);
        agregarPunto("P3_C1", 0.1, 7.0);
        agregarPunto("P3_D1", 0.1, 7.9);
        agregarPunto("P3_E1", 3.8, 7.9);
        agregarPunto("P3_F1", 3.8, 10.3);
        agregarPunto("P3_G1", 0.8, 10.3);
        agregarPunto("P3_H1", 0.8, 14.0);
        agregarPunto("P3_I1", 0.1, 5.0);
        agregarPunto("P3_J1", 2.8, 5.0);
        agregarPunto("P3_K1", 2.8, 4.5);
        agregarPunto("P3_L1", 6.6, 2.6);
        agregarPunto("P3_M1", 0.1, 4.5);
        agregarPunto("P3_N1", 0.8, 15.4);
        agregarPunto("P3_O1", 2.0, 14.0);
        agregarPunto("P3_P1", 2.0, 15.4);
        agregarPunto("P3_Q1", 3.0, 15.4);
        agregarPunto("P3_R1", 3.0, 14.0);
    }

    private void crearParedesTerceraPlanta() {
        double alturaTercerPiso = 6.4;

        agregarPared("P3_f", "P3_J", "P3_I", alturaTercerPiso);
        agregarPared("P3_g", "P3_I", "P3_G", alturaTercerPiso);
        agregarPared("P3_h", "P3_G", "P3_E", alturaTercerPiso);
        agregarPared("P3_i", "P3_J", "P3_L1", alturaTercerPiso);
        agregarPared("P3_j", "P3_L1", "P3_K", alturaTercerPiso);

        agregarPared("P3_k", "P3_L", "P3_M", alturaTercerPiso);
        agregarPared("P3_l", "P3_M", "P3_M1", alturaTercerPiso);
        agregarPared("P3_m", "P3_M1", "P3_K1", alturaTercerPiso);
        agregarPared("P3_n", "P3_K1", "P3_J1", alturaTercerPiso);
        agregarPared("P3_p", "P3_J1", "P3_I1", alturaTercerPiso);
        agregarPared("P3_q", "P3_I1", "P3_M1", alturaTercerPiso);

        agregarPared("P3_r", "P3_L", "P3_K", alturaTercerPiso);
        agregarPared("P3_s", "P3_L1", "P3_M", alturaTercerPiso);
        agregarPared("P3_t", "P3_I1", "P3_C1", alturaTercerPiso);

        agregarPared("P3_a", "P3_C1", "P3_B1", alturaTercerPiso);
        agregarPared("P3_b", "P3_B1", "P3_E1", alturaTercerPiso);
        agregarPared("P3_c", "P3_E1", "P3_D1", alturaTercerPiso);
        agregarPared("P3_d", "P3_D1", "P3_C1", alturaTercerPiso);
        agregarPared("P3_e", "P3_D1", "P3_Z", alturaTercerPiso);

        agregarPared("P3_f1", "P3_Z", "P3_A1", alturaTercerPiso);
        agregarPared("P3_g1", "P3_A1", "P3_E1", alturaTercerPiso);
        agregarPared("P3_h1", "P3_Z", "P3_W", alturaTercerPiso);
        agregarPared("P3_i1", "P3_W", "P3_H1", alturaTercerPiso);
        agregarPared("P3_j1", "P3_H1", "P3_N1", alturaTercerPiso);
        agregarPared("P3_k1", "P3_N1", "P3_V", alturaTercerPiso);
        agregarPared("P3_l1", "P3_V", "P3_W", alturaTercerPiso);

        agregarPared("P3_m1", "P3_G1", "P3_H1", alturaTercerPiso);
        agregarPared("P3_n1", "P3_G1", "P3_F1", alturaTercerPiso);
        agregarPared("P3_p1", "P3_F1", "P3_A1", alturaTercerPiso);

        agregarPared("P3_q1", "P3_R1", "P3_O1", alturaTercerPiso);
        agregarPared("P3_r1", "P3_O1", "P3_P1", alturaTercerPiso);
        agregarPared("P3_s1", "P3_P1", "P3_Q1", alturaTercerPiso);
        agregarPared("P3_t1", "P3_Q1", "P3_R1", alturaTercerPiso);

        agregarPared("P3_a1", "P3_N", "P3_O", alturaTercerPiso);
        agregarPared("P3_b1", "P3_O", "P3_F", alturaTercerPiso);
        agregarPared("P3_c1", "P3_F", "P3_C", alturaTercerPiso); // Restaurada para colisión invisible
        // agregarPared("P3_d1", "P3_D", "P3_H", alturaTercerPiso);
        agregarPared("P3_e1", "P3_G", "P3_F", alturaTercerPiso);

        agregarPared("P3_f2", "P3_O", "P3_P", alturaTercerPiso);
        agregarPared("P3_g2", "P3_P", "P3_Q", alturaTercerPiso);
        agregarPared("P3_h2", "P3_Q", "P3_R", alturaTercerPiso);
        agregarPared("P3_i2", "P3_R", "P3_S", alturaTercerPiso);
        agregarPared("P3_j2", "P3_S", "P3_T", alturaTercerPiso);
        agregarPared("P3_k2", "P3_T", "P3_U", alturaTercerPiso);
        agregarPared("P3_l2", "P3_U", "P3_V", alturaTercerPiso);

        // ==========================================================
        // DINTEL Cuarto (Tercer Piso)
        // ==========================================================
        // 9.1 es la altura base (6.4 del piso + 2.7 de la puerta) y 0.5 es el alto del
        // dintel
        agregarPared("Dintel 3er Piso", "P3_E", "P3_D", 9.1, 0.5);
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