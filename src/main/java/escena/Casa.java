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

    private void agregarParedInvisible(String nombre, String puntoInicio, String puntoFin, double alturaBase) {
        Punto2D inicio = buscarPunto(puntoInicio);
        Punto2D fin = buscarPunto(puntoFin);

        if (inicio != null && fin != null) {
            paredes.add(new objetos.ParedInvisible(nombre, inicio, fin, alturaBase));
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
        agregarPunto("C9", 0.1, 1.6);
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

        // Puntos nuevos
        agregarPunto("B4", 1.5, 9.1);
        agregarPunto("S3", 2.4, 9.1);
        agregarPunto("O3", 3.2, 9.7);
        agregarPunto("C4", 3.2, 10.4);

        // Puntos para la colisión invisible
        agregarPunto("B8", 0.4, 4.4);
        agregarPunto("C8", 2.0, 4.4);
        agregarPunto("D8", 0.4, 0.6);
        agregarPunto("E8", 2.0, 0.6);

        // jardin
        agregarPunto("S7M", 0.2, 4.9);
        agregarPunto("T7M", 3.6, 4.9);
        agregarPunto("U7M", 3.6, 5.3);

        // postejardin
        agregarPunto("O7M", 5.65, 4.55);
        agregarPunto("N7M", 5.65, 4.75);
        agregarPunto("Q7M", 5.85, 4.75);
        agregarPunto("P7M", 5.85, 4.55);

        // maquinacaminadora
        agregarPunto("T4M", 7.2, 16.4);
        agregarPunto("W4M", 7.8, 16.4);
        agregarPunto("U4M", 7, 14);

        // colision bicicleta
        agregarPunto("Z4M", 5, 14.6);
        agregarPunto("C5M", 6.1, 14.6);
        agregarPunto("B5M", 6.1, 14);

        // mancuernas
        agregarPunto("H5M", 6.6, 18.5);
        agregarPunto("I5M", 6.6, 18.2);
        agregarPunto("JEM", 7.7, 18.5);

        // pesas
        agregarPunto("E5M", 5, 17.8);
        agregarPunto("F5M", 6.2, 17.8);
        agregarPunto("G5M", 6.2, 18.5);

        // BOXEO
        agregarPunto("L5M", 6.5, 17.4);
        agregarPunto("M5M", 6.5, 17.1);
        agregarPunto("O5M", 6.8, 17.1);
        agregarPunto("N5M", 6.8, 17.4);

        // LavavoBañoPricipal
        agregarPunto("H1", 3.9, 14.3);
        agregarPunto("G1", 2.5, 14.3);

        // LavaboPuertaBaño
        agregarPunto("F8M", 1.9, 14);
        agregarPunto("G8M", 1.9, 14.7);
        agregarPunto("H8M", 1.5, 14.7);
        agregarPunto("I8M", 1.5, 14);

        // BañeraPrincipal
        agregarPunto("J8M", 1, 14);
        agregarPunto("K8M", 1, 15.3);

        // CLOSETrecamara1
        agregarPunto("P5M", 3.7, 16);
        agregarPunto("B6M", 0.2, 16);

        // camaprincipal
        agregarPunto("F6M", 2, 16.8);
        agregarPunto("G6M", 2, 18);
        agregarPunto("D6M", 0.2, 18);
        agregarPunto("E6M", 0.2, 16.8);

        // mesarecamaraprincipal
        agregarPunto("T5M", 3.3, 17.3);
        agregarPunto("U5M", 3.3, 18.3);
        agregarPunto("V5M", 3.8, 18.3);
        agregarPunto("W5M", 3.8, 17.3);

        // BURORECAMARAPRINCIAL1
        agregarPunto("L6M", 0.5, 18.2);
        agregarPunto("M6M", 0.2, 18.2);
        agregarPunto("O6M", 0.5, 18.5);

        // BURORECAMARAPRINCIAL2
        agregarPunto("H6M", 0.2, 16.7);
        agregarPunto("I6M", 0.2, 16.4);
        agregarPunto("J6M", 1.2, 16.4);
        agregarPunto("K6M", 1.2, 16.7);

        // PLANTAS PATIO CON FUENTE
        agregarPunto("L1M", 7.3, 14);
        agregarPunto("K7M", 7.3, 12);

        // ============================================
        // PUNTOS PARA COLISIONES COCINA Y COMEDOR PISO 1
        // ============================================

        // Muebles Cocina Derecha 1
        agregarPunto("MCD1_A", 2.4, 8.6);
        agregarPunto("MCD1_B", 3.8, 8.6);
        agregarPunto("MCD1_C", 3.8, 9.1);
        agregarPunto("MCD1_D", 2.4, 9.1);

        // Muebles Cocina Derecha 2
        agregarPunto("MCD2_A", 3.2, 9.1);
        agregarPunto("MCD2_B", 3.8, 9.1);
        agregarPunto("MCD2_C", 3.8, 9.7);
        agregarPunto("MCD2_D", 3.2, 9.7);

        // Muebles Cocina Izquierda 1
        agregarPunto("MCI1_A", 0.2, 8.6);
        agregarPunto("MCI1_B", 1.5, 8.6);
        agregarPunto("MCI1_C", 1.5, 9.1);
        agregarPunto("MCI1_D", 0.2, 9.1);

        // Muebles Cocina Izquierda 2
        agregarPunto("MCI2_A", 0.2, 9.1);
        agregarPunto("MCI2_B", 0.7, 9.1);
        agregarPunto("MCI2_C", 0.7, 9.8);
        agregarPunto("MCI2_D", 0.2, 9.8);

        // Refrigerador
        agregarPunto("REF_A", 0.2, 9.8);
        agregarPunto("REF_B", 0.7, 9.8);
        agregarPunto("REF_C", 0.7, 10.3);
        agregarPunto("REF_D", 0.2, 10.3);

        // Alacena Alta
        agregarPunto("ALA_A", 0.2, 10.3);
        agregarPunto("ALA_B", 0.7, 10.3);
        agregarPunto("ALA_C", 0.7, 10.9);
        agregarPunto("ALA_D", 0.2, 10.9);

        // Isla
        agregarPunto("ISLA_A", 1.9, 10.4);
        agregarPunto("ISLA_B", 3.8, 10.4);
        agregarPunto("ISLA_C", 3.8, 10.9);
        agregarPunto("ISLA_D", 1.9, 10.9);

        // Mesa Comedor Centro
        agregarPunto("MESA_A", 1.50, 12.46);
        agregarPunto("MESA_B", 2.60, 12.46);
        agregarPunto("MESA_C", 2.60, 13.04);
        agregarPunto("MESA_D", 1.50, 13.04);

        // Silla Comedor Arriba Izquierda
        agregarPunto("SCAI_A", 1.41, 13.14);
        agregarPunto("SCAI_B", 1.69, 13.14);
        agregarPunto("SCAI_C", 1.69, 13.42);
        agregarPunto("SCAI_D", 1.41, 13.42);

        // Silla Comedor Arriba Derecha
        agregarPunto("SCAD_A", 2.41, 13.14);
        agregarPunto("SCAD_B", 2.69, 13.14);
        agregarPunto("SCAD_C", 2.69, 13.42);
        agregarPunto("SCAD_D", 2.41, 13.42);

        // Silla Comedor Abajo Izquierda
        agregarPunto("SCABI_A", 1.41, 12.08);
        agregarPunto("SCABI_B", 1.69, 12.08);
        agregarPunto("SCABI_C", 1.69, 12.36);
        agregarPunto("SCABI_D", 1.41, 12.36);

        // Silla Comedor Abajo Derecha
        agregarPunto("SCABD_A", 2.41, 12.08);
        agregarPunto("SCABD_B", 2.69, 12.08);
        agregarPunto("SCABD_C", 2.69, 12.36);
        agregarPunto("SCABD_D", 2.41, 12.36);

        // Silla Comedor Izquierda
        agregarPunto("SCI_A", 0.88, 12.61);
        agregarPunto("SCI_B", 1.16, 12.61);
        agregarPunto("SCI_C", 1.16, 12.89);
        agregarPunto("SCI_D", 0.88, 12.89);

        // Silla Comedor Derecha
        agregarPunto("SCD_A", 2.94, 12.61);
        agregarPunto("SCD_B", 3.22, 12.61);
        agregarPunto("SCD_C", 3.22, 12.89);
        agregarPunto("SCD_D", 2.94, 12.89);

        // Silla Isla 1
        agregarPunto("SI1_A", 2.06, 11.11);
        agregarPunto("SI1_B", 2.34, 11.11);
        agregarPunto("SI1_C", 2.34, 11.39);
        agregarPunto("SI1_D", 2.06, 11.39);

        // Silla Isla 2
        agregarPunto("SI2_A", 2.71, 11.11);
        agregarPunto("SI2_B", 2.99, 11.11);
        agregarPunto("SI2_C", 2.99, 11.39);
        agregarPunto("SI2_D", 2.71, 11.39);

        // Silla Isla 3
        agregarPunto("SI3_A", 3.36, 11.11);
        agregarPunto("SI3_B", 3.64, 11.11);
        agregarPunto("SI3_C", 3.64, 11.39);
        agregarPunto("SI3_D", 3.36, 11.39);

        // Vitrina Vinos
        agregarPunto("VIT_A", 0.2, 12.2);
        agregarPunto("VIT_B", 0.6, 12.2);
        agregarPunto("VIT_C", 0.6, 13.3);
        agregarPunto("VIT_D", 0.2, 13.3);

        // FUENTE Base
        agregarPunto("FUENTE_L8", 7.3, 4.7);
        agregarPunto("FUENTE_M8", 7.3, 2);
        agregarPunto("FUENTE_N8", 7.8, 4.7);
        agregarPunto("FUENTE_O8", 7.8, 2);

        // FUENTE Estructura
        agregarPunto("FUENTE_P8", 7.5, 4.3);
        agregarPunto("FUENTE_Q8", 7.8, 4.3);
        agregarPunto("FUENTE_R8", 7.8, 2.5);
        agregarPunto("FUENTE_S8", 7.5, 2.5);

        // Puntos W7 y Z7 (muro extra o límite)
        agregarPunto("W7", 5.9, 1.6);
        agregarPunto("Z7", 7.8, 1.6);
        agregarPunto("A9", 7.0, 1.6);
        agregarPunto("Z8", 7.0, 0.2);
        agregarPunto("B9", 7.8, 0.2);

        // FUENTE DOBLE (Altura 2 pisos)
        agregarPunto("FUENTE_G7", 7.3, 13.4);
        agregarPunto("FUENTE_H7", 7.8, 13.4);
        agregarPunto("FUENTE_J7", 7.3, 12.5);
        agregarPunto("FUENTE_M7", 7.8, 12.5);

        agregarPunto("FUENTE_T8", 7.4, 13.3);
        agregarPunto("FUENTE_W8", 7.8, 13.3);
        agregarPunto("FUENTE_U8", 7.4, 12.6);
        agregarPunto("FUENTE_V8", 7.8, 12.6);

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
        agregarPared("r", "N", "C9", 0.0);
        agregarPared("r_cochera", "E", "C9", 0.0);
        agregarPared("s", "N", "W", 0.0);

        agregarPared("t", "J", "I", 0.0);
        agregarPared("a", "Z", "J", 0.0);
        agregarPared("b", "J", "K", 0.0); // Pared completa (ya no ventana)
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
        // agregarVentanal("r1", "L1", "K1"); // Se añade como Puerta Corrediza
        // interactiva en VentanaOpenGL
        agregarPared("Dintel Ventanal Corredizo", "L1", "K1", 2.7, 0.5); // Tapar parte superior del ventanal

        agregarPared("s1", "E1", "H1", 0.0);
        agregarPared("t1", "G1", "F1", 0.0);
        agregarPared("a1", "F1", "E1", 0.0);
        agregarPared("b1", "F1", "D1", 0.0);
        agregarPared("c1", "D1", "T1", 0.0); // Convertida de ventana a pared sólida
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
        agregarPared("algo chueco", "P1", "W1", 0.0);

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

        // Paredes faltantes:
        agregarParedInvisible("ColisionLavavo", "S3", "B4", 0.0);
        agregarParedInvisible("ColisioneEstufa", "O3", "C4", 0.0);

        // Paredes Invisibles (Colisiones Primitivas)
        agregarParedInvisible("Colision_D8_E8", "D8", "E8", 0.0);
        agregarParedInvisible("Colision_E8_C8", "E8", "C8", 0.0);
        agregarParedInvisible("Colision_C8_B8", "C8", "B8", 0.0);
        agregarParedInvisible("Colision_B8_D8", "B8", "D8", 0.0);
        agregarParedInvisible("Colision Jardin", "S7M", "T7M", 0.0);
        agregarParedInvisible("Colision Jardin2", "T7M", "U7M", 0.0);
        // colison poste
        agregarParedInvisible("Colision_poste", "N7M", "Q7M", 0.0);
        agregarParedInvisible("Colision_poste", "N7M", "O7M", 0.0);
        agregarParedInvisible("Colision_poste", "O7M", "P7M", 0.0);
        agregarParedInvisible("Colision_poste", "Q7M", "P7M", 0.0);
        // colision caminadora
        agregarParedInvisible("Colision_caminadora", "T4M", "W4M", 0.0);
        agregarParedInvisible("Colision_caminadora", "T4M", "U4M", 0.0);
        // colision bicicleta
        agregarParedInvisible("Colision_bicicleta", "Z4M", "C5M", 0.0);
        agregarParedInvisible("Colision_bicicleta2", "B5M", "C5M", 0.0);
        // colision
        agregarParedInvisible("Mancuernas", "H5M", "I5M", 0.0);
        agregarParedInvisible("Mancuernas2", "I5M", "JEM", 0.0);

        // COLISION PESAS
        agregarParedInvisible("Pesas", "E5M", "F5M", 0.0);
        agregarParedInvisible("Mancuernas2", "F5M", "G5M", 0.0);

        // COLISION BOXEO
        agregarParedInvisible("BOXEO", "L5M", "M5M", 0.0);
        agregarParedInvisible("BOXEO2", "M5M", "N5M", 0.0);
        agregarParedInvisible("BOXEO3", "O5M", "N5M", 0.0);
        agregarParedInvisible("BOXEO4", "O5M", "L5M", 0.0);

        // colison lavabo
        agregarParedInvisible("colision lavabo", "H1", "G1", 0.0);

        // colision puerta baño
        agregarParedInvisible("colision puerta baño", "F8M", "G8M", 0.0);
        agregarParedInvisible("colision puerta baño", "F8M", "I8M", 0.0);
        agregarParedInvisible("colision puerta baño", "G8M", "H8M", 0.0);
        agregarParedInvisible("colision puerta baño", "I8M", "H8M", 0.0);

        // colision bañera
        agregarParedInvisible("colision bañera", "J8M", "K8M", 0.0);

        // colision closet
        agregarParedInvisible("colision closet", "P5M", "B6M", 0.0);

        // camarecamara1
        agregarParedInvisible("camarecamara1", "F6M", "G6M", 0.0);
        agregarParedInvisible("camarecamara1", "F6M", "E6M", 0.0);
        agregarParedInvisible("camarecamara1", "G6M", "D6M", 0.0);

        // mesarecamaraprincipal
        agregarParedInvisible("mesarecamaraprincipal", "T5M", "U5M", 0.0);
        agregarParedInvisible("mesarecamaraprincipal", "U5M", "V5M", 0.0);
        agregarParedInvisible("mesarecamaraprincipal", "V5M", "W5M", 0.0);
        agregarParedInvisible("mesarecamaraprincipal", "W5M", "T5M", 0.0);

        // BURORECAMARAPRINCIAL1
        agregarParedInvisible("burorecamaraprincipal", "L6M", "M6M", 0.0);
        agregarParedInvisible("burorecamaraprincipal", "M6M", "O6M", 0.0);
        agregarParedInvisible("burorecamaraprincipal", "O6M", "L6M", 0.0);

        // BURORECAMARAPRINCIAL2
        agregarParedInvisible("burorecamaraprincipal2", "H6M", "I6M", 0.0);
        agregarParedInvisible("burorecamaraprincipal2", "I6M", "J6M", 0.0);
        agregarParedInvisible("burorecamaraprincipal2", "J6M", "K6M", 0.0);
        agregarParedInvisible("burorecamaraprincipal2", "K6M", "H6M", 0.0);

        // PLANTAS PATIO CON FUENTE
        agregarParedInvisible("plantas patio con fuente", "L1M", "K7M", 0.0);

        // ============================================
        // PAREDES INVISIBLES COCINA Y COMEDOR PISO 1
        // ============================================

        // Colisiones Cocina - Grupo Derecho 1
        agregarParedInvisible("Colision_MCD1_AB", "MCD1_A", "MCD1_B", 0.0);
        agregarParedInvisible("Colision_MCD1_BC", "MCD1_B", "MCD1_C", 0.0);
        agregarParedInvisible("Colision_MCD1_CD", "MCD1_C", "MCD1_D", 0.0);
        agregarParedInvisible("Colision_MCD1_DA", "MCD1_D", "MCD1_A", 0.0);

        // Colisiones Cocina - Grupo Derecho 2
        agregarParedInvisible("Colision_MCD2_AB", "MCD2_A", "MCD2_B", 0.0);
        agregarParedInvisible("Colision_MCD2_BC", "MCD2_B", "MCD2_C", 0.0);
        agregarParedInvisible("Colision_MCD2_CD", "MCD2_C", "MCD2_D", 0.0);
        agregarParedInvisible("Colision_MCD2_DA", "MCD2_D", "MCD2_A", 0.0);

        // Colisiones Cocina - Grupo Izquierdo 1
        agregarParedInvisible("Colision_MCI1_AB", "MCI1_A", "MCI1_B", 0.0);
        agregarParedInvisible("Colision_MCI1_BC", "MCI1_B", "MCI1_C", 0.0);
        agregarParedInvisible("Colision_MCI1_CD", "MCI1_C", "MCI1_D", 0.0);
        agregarParedInvisible("Colision_MCI1_DA", "MCI1_D", "MCI1_A", 0.0);

        // Colisiones Cocina - Grupo Izquierdo 2
        agregarParedInvisible("Colision_MCI2_AB", "MCI2_A", "MCI2_B", 0.0);
        agregarParedInvisible("Colision_MCI2_BC", "MCI2_B", "MCI2_C", 0.0);
        agregarParedInvisible("Colision_MCI2_CD", "MCI2_C", "MCI2_D", 0.0);
        agregarParedInvisible("Colision_MCI2_DA", "MCI2_D", "MCI2_A", 0.0);

        // Colisiones Cocina - Refrigerador
        agregarParedInvisible("Colision_REF_AB", "REF_A", "REF_B", 0.0);
        agregarParedInvisible("Colision_REF_BC", "REF_B", "REF_C", 0.0);
        agregarParedInvisible("Colision_REF_CD", "REF_C", "REF_D", 0.0);
        agregarParedInvisible("Colision_REF_DA", "REF_D", "REF_A", 0.0);

        // Colisiones Cocina - Alacena Alta
        agregarParedInvisible("Colision_ALA_AB", "ALA_A", "ALA_B", 0.0);
        agregarParedInvisible("Colision_ALA_BC", "ALA_B", "ALA_C", 0.0);
        agregarParedInvisible("Colision_ALA_CD", "ALA_C", "ALA_D", 0.0);
        agregarParedInvisible("Colision_ALA_DA", "ALA_D", "ALA_A", 0.0);

        // Colisiones Cocina - Isla
        agregarParedInvisible("Colision_ISLA_AB", "ISLA_A", "ISLA_B", 0.0);
        agregarParedInvisible("Colision_ISLA_BC", "ISLA_B", "ISLA_C", 0.0);
        agregarParedInvisible("Colision_ISLA_CD", "ISLA_C", "ISLA_D", 0.0);
        agregarParedInvisible("Colision_ISLA_DA", "ISLA_D", "ISLA_A", 0.0);

        // Colisiones Comedor - Mesa Centro
        agregarParedInvisible("Colision_MESA_AB", "MESA_A", "MESA_B", 0.0);
        agregarParedInvisible("Colision_MESA_BC", "MESA_B", "MESA_C", 0.0);
        agregarParedInvisible("Colision_MESA_CD", "MESA_C", "MESA_D", 0.0);
        agregarParedInvisible("Colision_MESA_DA", "MESA_D", "MESA_A", 0.0);

        // Colisiones Sillas Comedor
        agregarParedInvisible("Colision_SCAI_AB", "SCAI_A", "SCAI_B", 0.0);
        agregarParedInvisible("Colision_SCAI_BC", "SCAI_B", "SCAI_C", 0.0);
        agregarParedInvisible("Colision_SCAI_CD", "SCAI_C", "SCAI_D", 0.0);
        agregarParedInvisible("Colision_SCAI_DA", "SCAI_D", "SCAI_A", 0.0);

        agregarParedInvisible("Colision_SCAD_AB", "SCAD_A", "SCAD_B", 0.0);
        agregarParedInvisible("Colision_SCAD_BC", "SCAD_B", "SCAD_C", 0.0);
        agregarParedInvisible("Colision_SCAD_CD", "SCAD_C", "SCAD_D", 0.0);
        agregarParedInvisible("Colision_SCAD_DA", "SCAD_D", "SCAD_A", 0.0);

        agregarParedInvisible("Colision_SCABI_AB", "SCABI_A", "SCABI_B", 0.0);
        agregarParedInvisible("Colision_SCABI_BC", "SCABI_B", "SCABI_C", 0.0);
        agregarParedInvisible("Colision_SCABI_CD", "SCABI_C", "SCABI_D", 0.0);
        agregarParedInvisible("Colision_SCABI_DA", "SCABI_D", "SCABI_A", 0.0);

        agregarParedInvisible("Colision_SCABD_AB", "SCABD_A", "SCABD_B", 0.0);
        agregarParedInvisible("Colision_SCABD_BC", "SCABD_B", "SCABD_C", 0.0);
        agregarParedInvisible("Colision_SCABD_CD", "SCABD_C", "SCABD_D", 0.0);
        agregarParedInvisible("Colision_SCABD_DA", "SCABD_D", "SCABD_A", 0.0);

        agregarParedInvisible("Colision_SCI_AB", "SCI_A", "SCI_B", 0.0);
        agregarParedInvisible("Colision_SCI_BC", "SCI_B", "SCI_C", 0.0);
        agregarParedInvisible("Colision_SCI_CD", "SCI_C", "SCI_D", 0.0);
        agregarParedInvisible("Colision_SCI_DA", "SCI_D", "SCI_A", 0.0);

        agregarParedInvisible("Colision_SCD_AB", "SCD_A", "SCD_B", 0.0);
        agregarParedInvisible("Colision_SCD_BC", "SCD_B", "SCD_C", 0.0);
        agregarParedInvisible("Colision_SCD_CD", "SCD_C", "SCD_D", 0.0);
        agregarParedInvisible("Colision_SCD_DA", "SCD_D", "SCD_A", 0.0);

        // Colisiones Sillas Isla
        agregarParedInvisible("Colision_SI1_AB", "SI1_A", "SI1_B", 0.0);
        agregarParedInvisible("Colision_SI1_BC", "SI1_B", "SI1_C", 0.0);
        agregarParedInvisible("Colision_SI1_CD", "SI1_C", "SI1_D", 0.0);
        agregarParedInvisible("Colision_SI1_DA", "SI1_D", "SI1_A", 0.0);

        agregarParedInvisible("Colision_SI2_AB", "SI2_A", "SI2_B", 0.0);
        agregarParedInvisible("Colision_SI2_BC", "SI2_B", "SI2_C", 0.0);
        agregarParedInvisible("Colision_SI2_CD", "SI2_C", "SI2_D", 0.0);
        agregarParedInvisible("Colision_SI2_DA", "SI2_D", "SI2_A", 0.0);

        agregarParedInvisible("Colision_SI3_AB", "SI3_A", "SI3_B", 0.0);
        agregarParedInvisible("Colision_SI3_BC", "SI3_B", "SI3_C", 0.0);
        agregarParedInvisible("Colision_SI3_CD", "SI3_C", "SI3_D", 0.0);
        agregarParedInvisible("Colision_SI3_DA", "SI3_D", "SI3_A", 0.0);

        // Colisiones Comedor - Vitrina
        agregarParedInvisible("Colision_VIT_AB", "VIT_A", "VIT_B", 0.0);
        agregarParedInvisible("Colision_VIT_BC", "VIT_B", "VIT_C", 0.0);
        agregarParedInvisible("Colision_VIT_CD", "VIT_C", "VIT_D", 0.0);
        agregarParedInvisible("Colision_VIT_DA", "VIT_D", "VIT_A", 0.0);

        // COLISION FUENTE BASE
        agregarParedInvisible("Colision_Fuente_LM", "FUENTE_L8", "FUENTE_M8", 0.0);
        agregarParedInvisible("Colision_Fuente_MN", "FUENTE_M8", "FUENTE_O8", 0.0);
        agregarParedInvisible("Colision_Fuente_NO", "FUENTE_O8", "FUENTE_N8", 0.0);
        agregarParedInvisible("Colision_Fuente_OL", "FUENTE_N8", "FUENTE_L8", 0.0);

        // COLISION FUENTE ESTRUCTURA (Opcional, la base ya cubre)
        agregarParedInvisible("Colision_Fuente_PQ", "FUENTE_P8", "FUENTE_Q8", 0.0);
        agregarParedInvisible("Colision_Fuente_QR", "FUENTE_Q8", "FUENTE_R8", 0.0);
        agregarParedInvisible("Colision_Fuente_RS", "FUENTE_R8", "FUENTE_S8", 0.0);
        agregarParedInvisible("Colision_Fuente_SP", "FUENTE_S8", "FUENTE_P8", 0.0);

        // COLISION W7 y Z7
        agregarParedInvisible("Colision_W7_Z7", "A9", "Z7", 0.0);

        // COLISION ESTRUCTURA CERRADA (Mini-muros)
        agregarParedInvisible("Colision_EstCerr_A9_Z7", "A9", "Z7", 0.0);
        agregarParedInvisible("Colision_EstCerr_Z7_B9", "Z7", "B9", 0.0);
        agregarParedInvisible("Colision_EstCerr_B9_Z8", "B9", "Z8", 0.0);
        agregarParedInvisible("Colision_EstCerr_Z8_A9", "Z8", "A9", 0.0);

        // COLISION FUENTE DOBLE
        agregarParedInvisible("Colision_FuenteDoble_GJ", "FUENTE_G7", "FUENTE_J7", 0.0);
        agregarParedInvisible("Colision_FuenteDoble_GH", "FUENTE_G7", "FUENTE_H7", 0.0);
        agregarParedInvisible("Colision_FuenteDoble_HM", "FUENTE_H7", "FUENTE_M7", 0.0);
        agregarParedInvisible("Colision_FuenteDoble_JM", "FUENTE_J7", "FUENTE_M7", 0.0);

        agregarParedInvisible("Colision_FuenteDoble_TW", "FUENTE_T8", "FUENTE_W8", 0.0);
        agregarParedInvisible("Colision_FuenteDoble_WV", "FUENTE_W8", "FUENTE_V8", 0.0);
        agregarParedInvisible("Colision_FuenteDoble_VU", "FUENTE_V8", "FUENTE_U8", 0.0);
        agregarParedInvisible("Colision_FuenteDoble_UT", "FUENTE_U8", "FUENTE_T8", 0.0);

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

        // ============================================
        // PUNTOS PARA COLISIONES SALA ESTAR, POZO LUZ, CLOSET PISO 2
        // ============================================

        // Sala Estar - Sillon Seccional Superior
        agregarPunto("SEP2_SS_A", 0.2, 13.0);
        agregarPunto("SEP2_SS_B", 3.1, 13.0);
        agregarPunto("SEP2_SS_C", 3.1, 13.8);
        agregarPunto("SEP2_SS_D", 0.2, 13.8);

        // Sala Estar - Sillon Seccional Izquierdo (Chaise)
        agregarPunto("SEP2_SI_A", 0.2, 10.4);
        agregarPunto("SEP2_SI_B", 1.2, 10.4);
        agregarPunto("SEP2_SI_C", 1.2, 13.0);
        agregarPunto("SEP2_SI_D", 0.2, 13.0);

        // Sala Estar - Mesa
        agregarPunto("SEP2_M_A", 1.6, 11.2);
        agregarPunto("SEP2_M_B", 3.1, 11.2);
        agregarPunto("SEP2_M_C", 3.1, 12.0);
        agregarPunto("SEP2_M_D", 1.6, 12.0);

        // Sala Estar - Mueble TV
        agregarPunto("SEP2_TV_A", 0.2, 9.6);
        agregarPunto("SEP2_TV_B", 2.8, 9.6);
        agregarPunto("SEP2_TV_C", 2.8, 10.2);
        agregarPunto("SEP2_TV_D", 0.2, 10.2);

        // Pozo Luz - Encimera
        agregarPunto("PLP2_E_A", 0.2, 8.0);
        agregarPunto("PLP2_E_B", 3.8, 8.0);
        agregarPunto("PLP2_E_C", 3.8, 8.5);
        agregarPunto("PLP2_E_D", 0.2, 8.5);

        // Closet - Modulo Izquierdo
        agregarPunto("CLP2_MI_A", 5.0, 6.8);
        agregarPunto("CLP2_MI_B", 5.6, 6.8);
        agregarPunto("CLP2_MI_C", 5.6, 9.4);
        agregarPunto("CLP2_MI_D", 5.0, 9.4);

        // Closet - Modulo Fondo
        agregarPunto("CLP2_MF_A", 5.6, 8.7);
        agregarPunto("CLP2_MF_B", 7.2, 8.7);
        agregarPunto("CLP2_MF_C", 7.2, 9.4);
        agregarPunto("CLP2_MF_D", 5.6, 9.4);

        // Closet - Modulo Derecho
        agregarPunto("CLP2_MD_A", 7.2, 6.8);
        agregarPunto("CLP2_MD_B", 7.8, 6.8);
        agregarPunto("CLP2_MD_C", 7.8, 9.4);
        agregarPunto("CLP2_MD_D", 7.2, 9.4);

        // Closet - Banco
        agregarPunto("CLP2_B_A", 6.25, 7.55);
        agregarPunto("CLP2_B_B", 6.85, 7.55);
        agregarPunto("CLP2_B_C", 6.85, 8.05);
        agregarPunto("CLP2_B_D", 6.25, 8.05);

        // Closet - Espejo (wall mounted, thin hitbox)
        agregarPunto("CLP2_ESP_A", 7.76, 6.0);
        agregarPunto("CLP2_ESP_B", 7.84, 6.0);
        agregarPunto("CLP2_ESP_C", 7.84, 6.8);
        agregarPunto("CLP2_ESP_D", 7.76, 6.8);

        // Closet - Estanterias Inferiores
        agregarPunto("CLP2_EST_A", 5.9, 5.86);
        agregarPunto("CLP2_EST_B", 7.2, 5.86);
        agregarPunto("CLP2_EST_C", 7.2, 6.14);
        agregarPunto("CLP2_EST_D", 5.9, 6.14);

        // Closet - Cajonera Interior
        agregarPunto("CLP2_CAJ_A", 5.85, 6.90);
        agregarPunto("CLP2_CAJ_B", 6.65, 6.90);
        agregarPunto("CLP2_CAJ_C", 6.65, 7.25);
        agregarPunto("CLP2_CAJ_D", 5.85, 7.25);

        // Recamara 4 - Cama
        agregarPunto("R4P2_C_A", 0.2, 5.8);
        agregarPunto("R4P2_C_B", 2.0, 5.8);
        agregarPunto("R4P2_C_C", 2.0, 7.1);
        agregarPunto("R4P2_C_D", 0.2, 7.1);

        // Recamara 4 - Buro
        agregarPunto("R4P2_B_A", 0.2, 7.2);
        agregarPunto("R4P2_B_B", 0.5, 7.2);
        agregarPunto("R4P2_B_C", 0.5, 7.5);
        agregarPunto("R4P2_B_D", 0.2, 7.5);

        // Recamara 4 - Mesa TV
        agregarPunto("R4P2_TV_A", 3.4, 5.6);
        agregarPunto("R4P2_TV_B", 3.8, 5.6);
        agregarPunto("R4P2_TV_C", 3.8, 6.9);
        agregarPunto("R4P2_TV_D", 3.4, 6.9);

        // Recamara 4 - Mesa Gamer
        agregarPunto("R4P2_MG_A", 0.6, 7.5);
        agregarPunto("R4P2_MG_B", 1.7, 7.5);
        agregarPunto("R4P2_MG_C", 1.7, 7.8);
        agregarPunto("R4P2_MG_D", 0.6, 7.8);

        // Recamara 4 - Silla Gamer
        agregarPunto("R4P2_SG_A", 0.85, 7.05);
        agregarPunto("R4P2_SG_B", 1.45, 7.05);
        agregarPunto("R4P2_SG_C", 1.45, 7.55);
        agregarPunto("R4P2_SG_D", 0.85, 7.55);

        // Recamara 4 - Closet
        agregarPunto("R4P2_CL_A", 1.8, 4.5);
        agregarPunto("R4P2_CL_B", 3.4, 4.5);
        agregarPunto("R4P2_CL_C", 3.4, 5.0);
        agregarPunto("R4P2_CL_D", 1.8, 5.0);

        // Recamara 4 - Mesa Estudio
        agregarPunto("R4P2_ME_A", 0.8, 4.5);
        agregarPunto("R4P2_ME_B", 1.8, 4.5);
        agregarPunto("R4P2_ME_C", 1.8, 5.0);
        agregarPunto("R4P2_ME_D", 0.8, 5.0);

        // Recamara 4 - Silla Estudio
        agregarPunto("R4P2_SE_A", 1.1, 4.6);
        agregarPunto("R4P2_SE_B", 1.5, 4.6);
        agregarPunto("R4P2_SE_C", 1.5, 5.1);
        agregarPunto("R4P2_SE_D", 1.1, 5.1);

        // Recamara 4 - Estanteria
        agregarPunto("R4P2_EST_A", 0.2, 4.5);
        agregarPunto("R4P2_EST_B", 0.8, 4.5);
        agregarPunto("R4P2_EST_C", 0.8, 5.0);
        agregarPunto("R4P2_EST_D", 0.2, 5.0);

        // Recamara Principal - Cama
        agregarPunto("RPP2_C_A", 1.2, 2.6);
        agregarPunto("RPP2_C_B", 2.5, 2.6);
        agregarPunto("RPP2_C_C", 2.5, 4.2);
        agregarPunto("RPP2_C_D", 1.2, 4.2);

        // Recamara Principal - Buro 1
        agregarPunto("RPP2_B1_A", 0.8, 3.9);
        agregarPunto("RPP2_B1_B", 1.1, 3.9);
        agregarPunto("RPP2_B1_C", 1.1, 4.2);
        agregarPunto("RPP2_B1_D", 0.8, 4.2);

        // Recamara Principal - Buro 2
        agregarPunto("RPP2_B2_A", 2.6, 3.9);
        agregarPunto("RPP2_B2_B", 2.9, 3.9);
        agregarPunto("RPP2_B2_C", 2.9, 4.2);
        agregarPunto("RPP2_B2_D", 2.6, 4.2);

        // Recamara Principal - Sillon Baja
        agregarPunto("RPP2_SB_A", 3.7, 1.7);
        agregarPunto("RPP2_SB_B", 6.4, 1.7);
        agregarPunto("RPP2_SB_C", 6.4, 2.4);
        agregarPunto("RPP2_SB_D", 3.7, 2.4);

        // Recamara Principal - Sillon Alta
        agregarPunto("RPP2_SA_A", 5.0, 2.4);
        agregarPunto("RPP2_SA_B", 6.4, 2.4);
        agregarPunto("RPP2_SA_C", 6.4, 3.0);
        agregarPunto("RPP2_SA_D", 5.0, 3.0);

        // Recamara Principal - Mesa Centro
        agregarPunto("RPP2_MC_A", 3.4, 2.8);
        agregarPunto("RPP2_MC_B", 4.4, 2.8);
        agregarPunto("RPP2_MC_C", 4.4, 3.4);
        agregarPunto("RPP2_MC_D", 3.4, 3.4);

        // Recamara Principal - Mesa Decorativa
        agregarPunto("RPP2_MD_A", 0.2, 1.7);
        agregarPunto("RPP2_MD_B", 1.9, 1.7);
        agregarPunto("RPP2_MD_C", 1.9, 2.1);
        agregarPunto("RPP2_MD_D", 0.2, 2.1);

        // Recamara Principal - TV (Puntos sueltos para linea)
        agregarPunto("RPP2_TV_A", 0.2, 2.4);
        agregarPunto("RPP2_TV_B", 0.9, 1.7);

        // Bano Principal - Tina
        agregarPunto("BPP2_T_A", 0.1, 14.0);
        agregarPunto("BPP2_T_B", 1.1, 14.0);
        agregarPunto("BPP2_T_C", 1.1, 15.4);
        agregarPunto("BPP2_T_D", 0.1, 15.4);

        // Bano Principal - Retrete
        agregarPunto("BPP2_R_A", 1.5, 14.0);
        agregarPunto("BPP2_R_B", 1.9, 14.0);
        agregarPunto("BPP2_R_C", 1.9, 14.6);
        agregarPunto("BPP2_R_D", 1.5, 14.6);

        // Bano Principal - Lavabo
        agregarPunto("BPP2_L_A", 2.5, 14.0);
        agregarPunto("BPP2_L_B", 3.9, 14.0);
        agregarPunto("BPP2_L_C", 3.9, 14.5);
        agregarPunto("BPP2_L_D", 2.5, 14.5);

        // Recamara 2 - Estante
        agregarPunto("R2P2_EST_A", 0.2, 15.5);
        agregarPunto("R2P2_EST_B", 0.8, 15.5);
        agregarPunto("R2P2_EST_C", 0.8, 16.0);
        agregarPunto("R2P2_EST_D", 0.2, 16.0);

        // Recamara 2 - Mesa y Silla
        agregarPunto("R2P2_MS_A", 0.8, 15.5);
        agregarPunto("R2P2_MS_B", 1.5, 15.5);
        agregarPunto("R2P2_MS_C", 1.5, 16.3);
        agregarPunto("R2P2_MS_D", 0.8, 16.3);

        // Recamara 2 - Closet
        agregarPunto("R2P2_CL_A", 1.5, 15.5);
        agregarPunto("R2P2_CL_B", 3.7, 15.5);
        agregarPunto("R2P2_CL_C", 3.7, 16.0);
        agregarPunto("R2P2_CL_D", 1.5, 16.0);

        // Recamara 2 - Cama
        agregarPunto("R2P2_C_A", 0.2, 16.8);
        agregarPunto("R2P2_C_B", 2.0, 16.8);
        agregarPunto("R2P2_C_C", 2.0, 18.0);
        agregarPunto("R2P2_C_D", 0.2, 18.0);

        // Recamara 2 - Buro 1
        agregarPunto("R2P2_B1_A", 0.2, 16.4);
        agregarPunto("R2P2_B1_B", 0.5, 16.4);
        agregarPunto("R2P2_B1_C", 0.5, 16.7);
        agregarPunto("R2P2_B1_D", 0.2, 16.7);

        // Recamara 2 - Buro 2
        agregarPunto("R2P2_B2_A", 0.2, 18.2);
        agregarPunto("R2P2_B2_B", 0.5, 18.2);
        agregarPunto("R2P2_B2_C", 0.5, 18.5);
        agregarPunto("R2P2_B2_D", 0.2, 18.5);

        // Recamara 2 - Buro TV
        agregarPunto("R2P2_BTV_A", 3.3, 17.3);
        agregarPunto("R2P2_BTV_B", 3.8, 17.3);
        agregarPunto("R2P2_BTV_C", 3.8, 18.3);
        agregarPunto("R2P2_BTV_D", 3.3, 18.3);

        // Recamara 3 - Cama
        agregarPunto("R3P2_C_A", 6.0, 15.1);
        agregarPunto("R3P2_C_B", 7.7, 15.1);
        agregarPunto("R3P2_C_C", 7.7, 16.3);
        agregarPunto("R3P2_C_D", 6.0, 16.3);

        // Recamara 3 - Closet
        agregarPunto("R3P2_CL_A", 5.9, 16.8);
        agregarPunto("R3P2_CL_B", 7.8, 16.8);
        agregarPunto("R3P2_CL_C", 7.8, 17.2);
        agregarPunto("R3P2_CL_D", 5.9, 17.2);

        // Recamara 3 - Mesa
        agregarPunto("R3P2_M_A", 5.0, 14.0);
        agregarPunto("R3P2_M_B", 5.4, 14.0);
        agregarPunto("R3P2_M_C", 5.4, 15.1);
        agregarPunto("R3P2_M_D", 5.0, 15.1);

        // Recamara 3 - Silla
        agregarPunto("R3P2_S_A", 5.58, 14.35);
        agregarPunto("R3P2_S_B", 5.95, 14.35);
        agregarPunto("R3P2_S_C", 5.95, 14.75);
        agregarPunto("R3P2_S_D", 5.58, 14.75);

        // Lavanderia - Burro Planchar
        agregarPunto("LAVP2_BP_A", 5.4, 19.2);
        agregarPunto("LAVP2_BP_B", 5.8, 19.2);
        agregarPunto("LAVP2_BP_C", 5.8, 20.2);
        agregarPunto("LAVP2_BP_D", 5.4, 20.2);

        // Lavanderia - Lavadora
        agregarPunto("LAVP2_LAV_A", 3.9, 19.7);
        agregarPunto("LAVP2_LAV_B", 4.5, 19.7);
        agregarPunto("LAVP2_LAV_C", 4.5, 20.2);
        agregarPunto("LAVP2_LAV_D", 3.9, 20.2);

        // Lavanderia - Lavabo Ropa
        agregarPunto("LAVP2_LR_A", 3.9, 18.8);
        agregarPunto("LAVP2_LR_B", 4.5, 18.8);
        agregarPunto("LAVP2_LR_C", 4.5, 19.7);
        agregarPunto("LAVP2_LR_D", 3.9, 19.7);

        // Lavanderia - Secadora
        agregarPunto("LAVP2_SEC_A", 3.9, 18.2);
        agregarPunto("LAVP2_SEC_B", 4.5, 18.2);
        agregarPunto("LAVP2_SEC_C", 4.5, 18.8);
        agregarPunto("LAVP2_SEC_D", 3.9, 18.8);

        // Balcon - Silla Izquierda
        agregarPunto("BALP2_SI_A", 0.4, 0.7);
        agregarPunto("BALP2_SI_B", 1.0, 0.7);
        agregarPunto("BALP2_SI_C", 1.0, 1.3);
        agregarPunto("BALP2_SI_D", 0.4, 1.3);

        // Balcon - Mesa
        agregarPunto("BALP2_M_A", 1.3, 0.4);
        agregarPunto("BALP2_M_B", 2.0, 0.4);
        agregarPunto("BALP2_M_C", 2.0, 1.1);
        agregarPunto("BALP2_M_D", 1.3, 1.1);

        // Balcon - Silla Derecha
        agregarPunto("BALP2_SD_A", 2.7, 0.7);
        agregarPunto("BALP2_SD_B", 3.3, 0.7);
        agregarPunto("BALP2_SD_C", 3.3, 1.3);
        agregarPunto("BALP2_SD_D", 2.7, 1.3);

        // Balcon - Planta
        agregarPunto("BALP2_P_A", 3.8, 0.7);
        agregarPunto("BALP2_P_B", 4.3, 0.7);
        agregarPunto("BALP2_P_C", 4.3, 1.3);
        agregarPunto("BALP2_P_D", 3.8, 1.3);

        // Balcon - Barandal Puntos
        agregarPunto("BALP2_BAR_1", 0.1, 1.6);
        agregarPunto("BALP2_BAR_2", 0.1, 0.2);
        agregarPunto("BALP2_BAR_3", 4.8, 0.2);
        agregarPunto("BALP2_BAR_4", 4.8, 1.6);

        // Bano P2 - Bañera
        agregarPunto("BAP2_B_A", 7.1, 4.4);
        agregarPunto("BAP2_B_B", 7.8, 4.4);
        agregarPunto("BAP2_B_C", 7.8, 5.8);
        agregarPunto("BAP2_B_D", 7.1, 5.8);

        // Bano P2 - Regadera
        agregarPunto("BAP2_R_A", 5.0, 3.6);
        agregarPunto("BAP2_R_B", 6.5, 3.6);
        agregarPunto("BAP2_R_C", 6.5, 4.3);
        agregarPunto("BAP2_R_D", 5.0, 4.3);

        // Bano P2 - Retrete
        agregarPunto("BAP2_RET_A", 6.2, 5.1);
        agregarPunto("BAP2_RET_B", 6.6, 5.1);
        agregarPunto("BAP2_RET_C", 6.6, 5.8);
        agregarPunto("BAP2_RET_D", 6.2, 5.8);

        // Bano P2 - Lavabo
        agregarPunto("BAP2_L_A", 5.0, 5.3);
        agregarPunto("BAP2_L_B", 6.1, 5.3);
        agregarPunto("BAP2_L_C", 6.1, 5.8);
        agregarPunto("BAP2_L_D", 5.0, 5.8);

        agregarPunto("P2_N1M", 4.9, 13.9);
        agregarPunto("P2_CM", 4.9, 12.0);

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

        // ============================================
        // PAREDES INVISIBLES SALA ESTAR, POZO LUZ, CLOSET PISO 2
        // ============================================

        // Sala Estar - Sillon Seccional Superior
        agregarParedInvisible("Col_SEP2_SS_1", "SEP2_SS_A", "SEP2_SS_B", 3.2);
        agregarParedInvisible("Col_SEP2_SS_2", "SEP2_SS_B", "SEP2_SS_C", 3.2);
        agregarParedInvisible("Col_SEP2_SS_3", "SEP2_SS_C", "SEP2_SS_D", 3.2);
        agregarParedInvisible("Col_SEP2_SS_4", "SEP2_SS_D", "SEP2_SS_A", 3.2);

        // Sala Estar - Sillon Seccional Izquierdo (Chaise)
        agregarParedInvisible("Col_SEP2_SI_1", "SEP2_SI_A", "SEP2_SI_B", 3.2);
        agregarParedInvisible("Col_SEP2_SI_2", "SEP2_SI_B", "SEP2_SI_C", 3.2);
        agregarParedInvisible("Col_SEP2_SI_3", "SEP2_SI_C", "SEP2_SI_D", 3.2);
        agregarParedInvisible("Col_SEP2_SI_4", "SEP2_SI_D", "SEP2_SI_A", 3.2);

        // Sala Estar - Mesa
        agregarParedInvisible("Col_SEP2_M_1", "SEP2_M_A", "SEP2_M_B", 3.2);
        agregarParedInvisible("Col_SEP2_M_2", "SEP2_M_B", "SEP2_M_C", 3.2);
        agregarParedInvisible("Col_SEP2_M_3", "SEP2_M_C", "SEP2_M_D", 3.2);
        agregarParedInvisible("Col_SEP2_M_4", "SEP2_M_D", "SEP2_M_A", 3.2);

        // Sala Estar - Mueble TV
        agregarParedInvisible("Col_SEP2_TV_1", "SEP2_TV_A", "SEP2_TV_B", 3.2);
        agregarParedInvisible("Col_SEP2_TV_2", "SEP2_TV_B", "SEP2_TV_C", 3.2);
        agregarParedInvisible("Col_SEP2_TV_3", "SEP2_TV_C", "SEP2_TV_D", 3.2);
        agregarParedInvisible("Col_SEP2_TV_4", "SEP2_TV_D", "SEP2_TV_A", 3.2);

        // Pozo Luz - Encimera
        agregarParedInvisible("Col_PLP2_E_1", "PLP2_E_A", "PLP2_E_B", 3.2);
        agregarParedInvisible("Col_PLP2_E_2", "PLP2_E_B", "PLP2_E_C", 3.2);
        agregarParedInvisible("Col_PLP2_E_3", "PLP2_E_C", "PLP2_E_D", 3.2);
        agregarParedInvisible("Col_PLP2_E_4", "PLP2_E_D", "PLP2_E_A", 3.2);

        // Closet - Modulo Izquierdo
        agregarParedInvisible("Col_CLP2_MI_1", "CLP2_MI_A", "CLP2_MI_B", 3.2);
        agregarParedInvisible("Col_CLP2_MI_2", "CLP2_MI_B", "CLP2_MI_C", 3.2);
        agregarParedInvisible("Col_CLP2_MI_3", "CLP2_MI_C", "CLP2_MI_D", 3.2);
        agregarParedInvisible("Col_CLP2_MI_4", "CLP2_MI_D", "CLP2_MI_A", 3.2);

        // Closet - Modulo Fondo
        agregarParedInvisible("Col_CLP2_MF_1", "CLP2_MF_A", "CLP2_MF_B", 3.2);
        agregarParedInvisible("Col_CLP2_MF_2", "CLP2_MF_B", "CLP2_MF_C", 3.2);
        agregarParedInvisible("Col_CLP2_MF_3", "CLP2_MF_C", "CLP2_MF_D", 3.2);
        agregarParedInvisible("Col_CLP2_MF_4", "CLP2_MF_D", "CLP2_MF_A", 3.2);

        // Closet - Modulo Derecho
        agregarParedInvisible("Col_CLP2_MD_1", "CLP2_MD_A", "CLP2_MD_B", 3.2);
        agregarParedInvisible("Col_CLP2_MD_2", "CLP2_MD_B", "CLP2_MD_C", 3.2);
        agregarParedInvisible("Col_CLP2_MD_3", "CLP2_MD_C", "CLP2_MD_D", 3.2);
        agregarParedInvisible("Col_CLP2_MD_4", "CLP2_MD_D", "CLP2_MD_A", 3.2);

        // Closet - Banco
        agregarParedInvisible("Col_CLP2_B_1", "CLP2_B_A", "CLP2_B_B", 3.2);
        agregarParedInvisible("Col_CLP2_B_2", "CLP2_B_B", "CLP2_B_C", 3.2);
        agregarParedInvisible("Col_CLP2_B_3", "CLP2_B_C", "CLP2_B_D", 3.2);
        agregarParedInvisible("Col_CLP2_B_4", "CLP2_B_D", "CLP2_B_A", 3.2);

        // Closet - Espejo
        agregarParedInvisible("Col_CLP2_ESP_1", "CLP2_ESP_A", "CLP2_ESP_B", 3.2);
        agregarParedInvisible("Col_CLP2_ESP_2", "CLP2_ESP_B", "CLP2_ESP_C", 3.2);
        agregarParedInvisible("Col_CLP2_ESP_3", "CLP2_ESP_C", "CLP2_ESP_D", 3.2);
        agregarParedInvisible("Col_CLP2_ESP_4", "CLP2_ESP_D", "CLP2_ESP_A", 3.2);

        // Closet - Estanterias Inferiores
        agregarParedInvisible("Col_CLP2_EST_1", "CLP2_EST_A", "CLP2_EST_B", 3.2);
        agregarParedInvisible("Col_CLP2_EST_2", "CLP2_EST_B", "CLP2_EST_C", 3.2);
        agregarParedInvisible("Col_CLP2_EST_3", "CLP2_EST_C", "CLP2_EST_D", 3.2);
        agregarParedInvisible("Col_CLP2_EST_4", "CLP2_EST_D", "CLP2_EST_A", 3.2);

        // Closet - Cajonera Interior
        agregarParedInvisible("Col_CLP2_CAJ_1", "CLP2_CAJ_A", "CLP2_CAJ_B", 3.2);
        agregarParedInvisible("Col_CLP2_CAJ_2", "CLP2_CAJ_B", "CLP2_CAJ_C", 3.2);
        agregarParedInvisible("Col_CLP2_CAJ_3", "CLP2_CAJ_C", "CLP2_CAJ_D", 3.2);
        agregarParedInvisible("Col_CLP2_CAJ_4", "CLP2_CAJ_D", "CLP2_CAJ_A", 3.2);

        // Recamara 4 - Cama
        agregarParedInvisible("Col_R4P2_C_1", "R4P2_C_A", "R4P2_C_B", 3.2);
        agregarParedInvisible("Col_R4P2_C_2", "R4P2_C_B", "R4P2_C_C", 3.2);
        agregarParedInvisible("Col_R4P2_C_3", "R4P2_C_C", "R4P2_C_D", 3.2);
        agregarParedInvisible("Col_R4P2_C_4", "R4P2_C_D", "R4P2_C_A", 3.2);

        // Recamara 4 - Buro
        agregarParedInvisible("Col_R4P2_B_1", "R4P2_B_A", "R4P2_B_B", 3.2);
        agregarParedInvisible("Col_R4P2_B_2", "R4P2_B_B", "R4P2_B_C", 3.2);
        agregarParedInvisible("Col_R4P2_B_3", "R4P2_B_C", "R4P2_B_D", 3.2);
        agregarParedInvisible("Col_R4P2_B_4", "R4P2_B_D", "R4P2_B_A", 3.2);

        // Recamara 4 - Mesa TV
        agregarParedInvisible("Col_R4P2_TV_1", "R4P2_TV_A", "R4P2_TV_B", 3.2);
        agregarParedInvisible("Col_R4P2_TV_2", "R4P2_TV_B", "R4P2_TV_C", 3.2);
        agregarParedInvisible("Col_R4P2_TV_3", "R4P2_TV_C", "R4P2_TV_D", 3.2);
        agregarParedInvisible("Col_R4P2_TV_4", "R4P2_TV_D", "R4P2_TV_A", 3.2);

        // Recamara 4 - Mesa Gamer
        agregarParedInvisible("Col_R4P2_MG_1", "R4P2_MG_A", "R4P2_MG_B", 3.2);
        agregarParedInvisible("Col_R4P2_MG_2", "R4P2_MG_B", "R4P2_MG_C", 3.2);
        agregarParedInvisible("Col_R4P2_MG_3", "R4P2_MG_C", "R4P2_MG_D", 3.2);
        agregarParedInvisible("Col_R4P2_MG_4", "R4P2_MG_D", "R4P2_MG_A", 3.2);

        // Recamara 4 - Silla Gamer
        agregarParedInvisible("Col_R4P2_SG_1", "R4P2_SG_A", "R4P2_SG_B", 3.2);
        agregarParedInvisible("Col_R4P2_SG_2", "R4P2_SG_B", "R4P2_SG_C", 3.2);
        agregarParedInvisible("Col_R4P2_SG_3", "R4P2_SG_C", "R4P2_SG_D", 3.2);
        agregarParedInvisible("Col_R4P2_SG_4", "R4P2_SG_D", "R4P2_SG_A", 3.2);

        // Recamara 4 - Closet
        agregarParedInvisible("Col_R4P2_CL_1", "R4P2_CL_A", "R4P2_CL_B", 3.2);
        agregarParedInvisible("Col_R4P2_CL_2", "R4P2_CL_B", "R4P2_CL_C", 3.2);
        agregarParedInvisible("Col_R4P2_CL_3", "R4P2_CL_C", "R4P2_CL_D", 3.2);
        agregarParedInvisible("Col_R4P2_CL_4", "R4P2_CL_D", "R4P2_CL_A", 3.2);

        // Recamara 4 - Mesa Estudio
        agregarParedInvisible("Col_R4P2_ME_1", "R4P2_ME_A", "R4P2_ME_B", 3.2);
        agregarParedInvisible("Col_R4P2_ME_2", "R4P2_ME_B", "R4P2_ME_C", 3.2);
        agregarParedInvisible("Col_R4P2_ME_3", "R4P2_ME_C", "R4P2_ME_D", 3.2);
        agregarParedInvisible("Col_R4P2_ME_4", "R4P2_ME_D", "R4P2_ME_A", 3.2);

        // Recamara 4 - Silla Estudio
        agregarParedInvisible("Col_R4P2_SE_1", "R4P2_SE_A", "R4P2_SE_B", 3.2);
        agregarParedInvisible("Col_R4P2_SE_2", "R4P2_SE_B", "R4P2_SE_C", 3.2);
        agregarParedInvisible("Col_R4P2_SE_3", "R4P2_SE_C", "R4P2_SE_D", 3.2);
        agregarParedInvisible("Col_R4P2_SE_4", "R4P2_SE_D", "R4P2_SE_A", 3.2);

        // Recamara 4 - Estanteria
        agregarParedInvisible("Col_R4P2_EST_1", "R4P2_EST_A", "R4P2_EST_B", 3.2);
        agregarParedInvisible("Col_R4P2_EST_2", "R4P2_EST_B", "R4P2_EST_C", 3.2);
        agregarParedInvisible("Col_R4P2_EST_3", "R4P2_EST_C", "R4P2_EST_D", 3.2);
        agregarParedInvisible("Col_R4P2_EST_4", "R4P2_EST_D", "R4P2_EST_A", 3.2);

        // Recamara Principal - Cama
        agregarParedInvisible("Col_RPP2_C_1", "RPP2_C_A", "RPP2_C_B", 3.2);
        agregarParedInvisible("Col_RPP2_C_2", "RPP2_C_B", "RPP2_C_C", 3.2);
        agregarParedInvisible("Col_RPP2_C_3", "RPP2_C_C", "RPP2_C_D", 3.2);
        agregarParedInvisible("Col_RPP2_C_4", "RPP2_C_D", "RPP2_C_A", 3.2);

        // Recamara Principal - Buro 1
        agregarParedInvisible("Col_RPP2_B1_1", "RPP2_B1_A", "RPP2_B1_B", 3.2);
        agregarParedInvisible("Col_RPP2_B1_2", "RPP2_B1_B", "RPP2_B1_C", 3.2);
        agregarParedInvisible("Col_RPP2_B1_3", "RPP2_B1_C", "RPP2_B1_D", 3.2);
        agregarParedInvisible("Col_RPP2_B1_4", "RPP2_B1_D", "RPP2_B1_A", 3.2);

        // Recamara Principal - Buro 2
        agregarParedInvisible("Col_RPP2_B2_1", "RPP2_B2_A", "RPP2_B2_B", 3.2);
        agregarParedInvisible("Col_RPP2_B2_2", "RPP2_B2_B", "RPP2_B2_C", 3.2);
        agregarParedInvisible("Col_RPP2_B2_3", "RPP2_B2_C", "RPP2_B2_D", 3.2);
        agregarParedInvisible("Col_RPP2_B2_4", "RPP2_B2_D", "RPP2_B2_A", 3.2);

        // Recamara Principal - Sillon Baja
        agregarParedInvisible("Col_RPP2_SB_1", "RPP2_SB_A", "RPP2_SB_B", 3.2);
        agregarParedInvisible("Col_RPP2_SB_2", "RPP2_SB_B", "RPP2_SB_C", 3.2);
        agregarParedInvisible("Col_RPP2_SB_3", "RPP2_SB_C", "RPP2_SB_D", 3.2);
        agregarParedInvisible("Col_RPP2_SB_4", "RPP2_SB_D", "RPP2_SB_A", 3.2);

        // Recamara Principal - Sillon Alta
        agregarParedInvisible("Col_RPP2_SA_1", "RPP2_SA_A", "RPP2_SA_B", 3.2);
        agregarParedInvisible("Col_RPP2_SA_2", "RPP2_SA_B", "RPP2_SA_C", 3.2);
        agregarParedInvisible("Col_RPP2_SA_3", "RPP2_SA_C", "RPP2_SA_D", 3.2);
        agregarParedInvisible("Col_RPP2_SA_4", "RPP2_SA_D", "RPP2_SA_A", 3.2);

        // Recamara Principal - Mesa Centro
        agregarParedInvisible("Col_RPP2_MC_1", "RPP2_MC_A", "RPP2_MC_B", 3.2);
        agregarParedInvisible("Col_RPP2_MC_2", "RPP2_MC_B", "RPP2_MC_C", 3.2);
        agregarParedInvisible("Col_RPP2_MC_3", "RPP2_MC_C", "RPP2_MC_D", 3.2);
        agregarParedInvisible("Col_RPP2_MC_4", "RPP2_MC_D", "RPP2_MC_A", 3.2);

        // Recamara Principal - Mesa Decorativa
        agregarParedInvisible("Col_RPP2_MD_1", "RPP2_MD_A", "RPP2_MD_B", 3.2);
        agregarParedInvisible("Col_RPP2_MD_2", "RPP2_MD_B", "RPP2_MD_C", 3.2);
        agregarParedInvisible("Col_RPP2_MD_3", "RPP2_MD_C", "RPP2_MD_D", 3.2);
        agregarParedInvisible("Col_RPP2_MD_4", "RPP2_MD_D", "RPP2_MD_A", 3.2);

        // Recamara Principal - TV (Pared en diagonal/linea recta)
        agregarParedInvisible("Col_RPP2_TV_1", "RPP2_TV_A", "RPP2_TV_B", 3.2);

        // Bano Principal - Tina
        agregarParedInvisible("Col_BPP2_T_1", "BPP2_T_A", "BPP2_T_B", 3.2);
        agregarParedInvisible("Col_BPP2_T_2", "BPP2_T_B", "BPP2_T_C", 3.2);
        agregarParedInvisible("Col_BPP2_T_3", "BPP2_T_C", "BPP2_T_D", 3.2);
        agregarParedInvisible("Col_BPP2_T_4", "BPP2_T_D", "BPP2_T_A", 3.2);

        // Bano Principal - Retrete
        agregarParedInvisible("Col_BPP2_R_1", "BPP2_R_A", "BPP2_R_B", 3.2);
        agregarParedInvisible("Col_BPP2_R_2", "BPP2_R_B", "BPP2_R_C", 3.2);
        agregarParedInvisible("Col_BPP2_R_3", "BPP2_R_C", "BPP2_R_D", 3.2);
        agregarParedInvisible("Col_BPP2_R_4", "BPP2_R_D", "BPP2_R_A", 3.2);

        // Bano Principal - Lavabo
        agregarParedInvisible("Col_BPP2_L_1", "BPP2_L_A", "BPP2_L_B", 3.2);
        agregarParedInvisible("Col_BPP2_L_2", "BPP2_L_B", "BPP2_L_C", 3.2);
        agregarParedInvisible("Col_BPP2_L_3", "BPP2_L_C", "BPP2_L_D", 3.2);
        agregarParedInvisible("Col_BPP2_L_4", "BPP2_L_D", "BPP2_L_A", 3.2);

        // Recamara 2 - Estante
        agregarParedInvisible("Col_R2P2_EST_1", "R2P2_EST_A", "R2P2_EST_B", 3.2);
        agregarParedInvisible("Col_R2P2_EST_2", "R2P2_EST_B", "R2P2_EST_C", 3.2);
        agregarParedInvisible("Col_R2P2_EST_3", "R2P2_EST_C", "R2P2_EST_D", 3.2);
        agregarParedInvisible("Col_R2P2_EST_4", "R2P2_EST_D", "R2P2_EST_A", 3.2);

        // Recamara 2 - Mesa y Silla
        agregarParedInvisible("Col_R2P2_MS_1", "R2P2_MS_A", "R2P2_MS_B", 3.2);
        agregarParedInvisible("Col_R2P2_MS_2", "R2P2_MS_B", "R2P2_MS_C", 3.2);
        agregarParedInvisible("Col_R2P2_MS_3", "R2P2_MS_C", "R2P2_MS_D", 3.2);
        agregarParedInvisible("Col_R2P2_MS_4", "R2P2_MS_D", "R2P2_MS_A", 3.2);

        // Recamara 2 - Closet
        agregarParedInvisible("Col_R2P2_CL_1", "R2P2_CL_A", "R2P2_CL_B", 3.2);
        agregarParedInvisible("Col_R2P2_CL_2", "R2P2_CL_B", "R2P2_CL_C", 3.2);
        agregarParedInvisible("Col_R2P2_CL_3", "R2P2_CL_C", "R2P2_CL_D", 3.2);
        agregarParedInvisible("Col_R2P2_CL_4", "R2P2_CL_D", "R2P2_CL_A", 3.2);

        // Recamara 2 - Cama
        agregarParedInvisible("Col_R2P2_C_1", "R2P2_C_A", "R2P2_C_B", 3.2);
        agregarParedInvisible("Col_R2P2_C_2", "R2P2_C_B", "R2P2_C_C", 3.2);
        agregarParedInvisible("Col_R2P2_C_3", "R2P2_C_C", "R2P2_C_D", 3.2);
        agregarParedInvisible("Col_R2P2_C_4", "R2P2_C_D", "R2P2_C_A", 3.2);

        // Recamara 2 - Buro 1
        agregarParedInvisible("Col_R2P2_B1_1", "R2P2_B1_A", "R2P2_B1_B", 3.2);
        agregarParedInvisible("Col_R2P2_B1_2", "R2P2_B1_B", "R2P2_B1_C", 3.2);
        agregarParedInvisible("Col_R2P2_B1_3", "R2P2_B1_C", "R2P2_B1_D", 3.2);
        agregarParedInvisible("Col_R2P2_B1_4", "R2P2_B1_D", "R2P2_B1_A", 3.2);

        // Recamara 2 - Buro 2
        agregarParedInvisible("Col_R2P2_B2_1", "R2P2_B2_A", "R2P2_B2_B", 3.2);
        agregarParedInvisible("Col_R2P2_B2_2", "R2P2_B2_B", "R2P2_B2_C", 3.2);
        agregarParedInvisible("Col_R2P2_B2_3", "R2P2_B2_C", "R2P2_B2_D", 3.2);
        agregarParedInvisible("Col_R2P2_B2_4", "R2P2_B2_D", "R2P2_B2_A", 3.2);

        // Recamara 2 - Buro TV
        agregarParedInvisible("Col_R2P2_BTV_1", "R2P2_BTV_A", "R2P2_BTV_B", 3.2);
        agregarParedInvisible("Col_R2P2_BTV_2", "R2P2_BTV_B", "R2P2_BTV_C", 3.2);
        agregarParedInvisible("Col_R2P2_BTV_3", "R2P2_BTV_C", "R2P2_BTV_D", 3.2);
        agregarParedInvisible("Col_R2P2_BTV_4", "R2P2_BTV_D", "R2P2_BTV_A", 3.2);

        // Recamara 3 - Cama
        agregarParedInvisible("Col_R3P2_C_1", "R3P2_C_A", "R3P2_C_B", 3.2);
        agregarParedInvisible("Col_R3P2_C_2", "R3P2_C_B", "R3P2_C_C", 3.2);
        agregarParedInvisible("Col_R3P2_C_3", "R3P2_C_C", "R3P2_C_D", 3.2);
        agregarParedInvisible("Col_R3P2_C_4", "R3P2_C_D", "R3P2_C_A", 3.2);

        // Recamara 3 - Closet
        agregarParedInvisible("Col_R3P2_CL_1", "R3P2_CL_A", "R3P2_CL_B", 3.2);
        agregarParedInvisible("Col_R3P2_CL_2", "R3P2_CL_B", "R3P2_CL_C", 3.2);
        agregarParedInvisible("Col_R3P2_CL_3", "R3P2_CL_C", "R3P2_CL_D", 3.2);
        agregarParedInvisible("Col_R3P2_CL_4", "R3P2_CL_D", "R3P2_CL_A", 3.2);

        // Recamara 3 - Mesa
        agregarParedInvisible("Col_R3P2_M_1", "R3P2_M_A", "R3P2_M_B", 3.2);
        agregarParedInvisible("Col_R3P2_M_2", "R3P2_M_B", "R3P2_M_C", 3.2);
        agregarParedInvisible("Col_R3P2_M_3", "R3P2_M_C", "R3P2_M_D", 3.2);
        agregarParedInvisible("Col_R3P2_M_4", "R3P2_M_D", "R3P2_M_A", 3.2);

        // Recamara 3 - Silla
        agregarParedInvisible("Col_R3P2_S_1", "R3P2_S_A", "R3P2_S_B", 3.2);
        agregarParedInvisible("Col_R3P2_S_2", "R3P2_S_B", "R3P2_S_C", 3.2);
        agregarParedInvisible("Col_R3P2_S_3", "R3P2_S_C", "R3P2_S_D", 3.2);
        agregarParedInvisible("Col_R3P2_S_4", "R3P2_S_D", "R3P2_S_A", 3.2);

        // Lavanderia - Burro Planchar
        agregarParedInvisible("Col_LAVP2_BP_1", "LAVP2_BP_A", "LAVP2_BP_B", 3.2);
        agregarParedInvisible("Col_LAVP2_BP_2", "LAVP2_BP_B", "LAVP2_BP_C", 3.2);
        agregarParedInvisible("Col_LAVP2_BP_3", "LAVP2_BP_C", "LAVP2_BP_D", 3.2);
        agregarParedInvisible("Col_LAVP2_BP_4", "LAVP2_BP_D", "LAVP2_BP_A", 3.2);

        // Lavanderia - Lavadora
        agregarParedInvisible("Col_LAVP2_LAV_1", "LAVP2_LAV_A", "LAVP2_LAV_B", 3.2);
        agregarParedInvisible("Col_LAVP2_LAV_2", "LAVP2_LAV_B", "LAVP2_LAV_C", 3.2);
        agregarParedInvisible("Col_LAVP2_LAV_3", "LAVP2_LAV_C", "LAVP2_LAV_D", 3.2);
        agregarParedInvisible("Col_LAVP2_LAV_4", "LAVP2_LAV_D", "LAVP2_LAV_A", 3.2);

        // Lavanderia - Lavabo Ropa
        agregarParedInvisible("Col_LAVP2_LR_1", "LAVP2_LR_A", "LAVP2_LR_B", 3.2);
        agregarParedInvisible("Col_LAVP2_LR_2", "LAVP2_LR_B", "LAVP2_LR_C", 3.2);
        agregarParedInvisible("Col_LAVP2_LR_3", "LAVP2_LR_C", "LAVP2_LR_D", 3.2);
        agregarParedInvisible("Col_LAVP2_LR_4", "LAVP2_LR_D", "LAVP2_LR_A", 3.2);

        // Lavanderia - Secadora
        agregarParedInvisible("Col_LAVP2_SEC_1", "LAVP2_SEC_A", "LAVP2_SEC_B", 3.2);
        agregarParedInvisible("Col_LAVP2_SEC_2", "LAVP2_SEC_B", "LAVP2_SEC_C", 3.2);
        agregarParedInvisible("Col_LAVP2_SEC_3", "LAVP2_SEC_C", "LAVP2_SEC_D", 3.2);
        agregarParedInvisible("Col_LAVP2_SEC_4", "LAVP2_SEC_D", "LAVP2_SEC_A", 3.2);

        // Balcon - Silla Izquierda
        agregarParedInvisible("Col_BALP2_SI_1", "BALP2_SI_A", "BALP2_SI_B", 3.2);
        agregarParedInvisible("Col_BALP2_SI_2", "BALP2_SI_B", "BALP2_SI_C", 3.2);
        agregarParedInvisible("Col_BALP2_SI_3", "BALP2_SI_C", "BALP2_SI_D", 3.2);
        agregarParedInvisible("Col_BALP2_SI_4", "BALP2_SI_D", "BALP2_SI_A", 3.2);

        // Balcon - Mesa
        agregarParedInvisible("Col_BALP2_M_1", "BALP2_M_A", "BALP2_M_B", 3.2);
        agregarParedInvisible("Col_BALP2_M_2", "BALP2_M_B", "BALP2_M_C", 3.2);
        agregarParedInvisible("Col_BALP2_M_3", "BALP2_M_C", "BALP2_M_D", 3.2);
        agregarParedInvisible("Col_BALP2_M_4", "BALP2_M_D", "BALP2_M_A", 3.2);

        // Balcon - Silla Derecha
        agregarParedInvisible("Col_BALP2_SD_1", "BALP2_SD_A", "BALP2_SD_B", 3.2);
        agregarParedInvisible("Col_BALP2_SD_2", "BALP2_SD_B", "BALP2_SD_C", 3.2);
        agregarParedInvisible("Col_BALP2_SD_3", "BALP2_SD_C", "BALP2_SD_D", 3.2);
        agregarParedInvisible("Col_BALP2_SD_4", "BALP2_SD_D", "BALP2_SD_A", 3.2);

        // Balcon - Planta
        agregarParedInvisible("Col_BALP2_P_1", "BALP2_P_A", "BALP2_P_B", 3.2);
        agregarParedInvisible("Col_BALP2_P_2", "BALP2_P_B", "BALP2_P_C", 3.2);
        agregarParedInvisible("Col_BALP2_P_3", "BALP2_P_C", "BALP2_P_D", 3.2);
        agregarParedInvisible("Col_BALP2_P_4", "BALP2_P_D", "BALP2_P_A", 3.2);

        // Balcon - Barandal (lineas rectas gruesas)
        agregarParedInvisible("Col_BALP2_BAR_1", "BALP2_BAR_1", "BALP2_BAR_2", 3.2);
        agregarParedInvisible("Col_BALP2_BAR_2", "BALP2_BAR_2", "BALP2_BAR_3", 3.2);
        agregarParedInvisible("Col_BALP2_BAR_3", "BALP2_BAR_3", "BALP2_BAR_4", 3.2);

        // Bano P2 - Bañera
        agregarParedInvisible("Col_BAP2_B_1", "BAP2_B_A", "BAP2_B_B", 3.2);
        agregarParedInvisible("Col_BAP2_B_2", "BAP2_B_B", "BAP2_B_C", 3.2);
        agregarParedInvisible("Col_BAP2_B_3", "BAP2_B_C", "BAP2_B_D", 3.2);
        agregarParedInvisible("Col_BAP2_B_4", "BAP2_B_D", "BAP2_B_A", 3.2);

        // Bano P2 - Regadera
        agregarParedInvisible("Col_BAP2_R_1", "BAP2_R_A", "BAP2_R_B", 3.2);
        agregarParedInvisible("Col_BAP2_R_2", "BAP2_R_B", "BAP2_R_C", 3.2);
        agregarParedInvisible("Col_BAP2_R_3", "BAP2_R_C", "BAP2_R_D", 3.2);
        agregarParedInvisible("Col_BAP2_R_4", "BAP2_R_D", "BAP2_R_A", 3.2);

        // Bano P2 - Retrete
        agregarParedInvisible("Col_BAP2_RET_1", "BAP2_RET_A", "BAP2_RET_B", 3.2);
        agregarParedInvisible("Col_BAP2_RET_2", "BAP2_RET_B", "BAP2_RET_C", 3.2);
        agregarParedInvisible("Col_BAP2_RET_3", "BAP2_RET_C", "BAP2_RET_D", 3.2);
        agregarParedInvisible("Col_BAP2_RET_4", "BAP2_RET_D", "BAP2_RET_A", 3.2);

        // Bano P2 - Lavabo
        agregarParedInvisible("Col_BAP2_L_1", "BAP2_L_A", "BAP2_L_B", 3.2);
        agregarParedInvisible("Col_BAP2_L_2", "BAP2_L_B", "BAP2_L_C", 3.2);
        agregarParedInvisible("Col_BAP2_L_3", "BAP2_L_C", "BAP2_L_D", 3.2);
        agregarParedInvisible("Col_BAP2_L_4", "BAP2_L_D", "BAP2_L_A", 3.2);

        // balcon
        agregarParedInvisible("Balcon", "P2_N1M", "P2_CM", 3.2);
    }

    private void crearPuntosTerceraPlanta() {
        agregarPunto("P3_C", 4.9, 12.0);
        agregarPunto("P3_D", 4.9, 10.6);
        agregarPunto("P3_E", 4.9, 9.5);
        agregarPunto("P3_F", 7.9, 12.0);
        agregarPunto("P3_G", 7.9, 9.5);
        agregarPunto("P3_H", 6.8, 10.7);
        agregarPunto("P3_I", 7.9, 4.2);
        agregarPunto("P3_J", 6.6, 4.2);
        agregarPunto("P3_K", 6.6, 1.5);
        agregarPunto("P3_L", 0.1, 1.5);
        agregarPunto("P3_semi_azul", 4.8, 1.5);
        agregarPunto("P3_semi_madera", 5.9, 1.5);
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

        // COMEDOR PISO 3
        agregarPunto("COM_P3_M1_A", 4.9, 8.6);
        agregarPunto("COM_P3_M1_B", 7.8, 8.6);
        agregarPunto("COM_P3_M1_C", 7.8, 9.4);
        agregarPunto("COM_P3_M1_D", 4.9, 9.4);

        agregarPunto("COM_P3_M2_A", 4.8, 5.8);
        agregarPunto("COM_P3_M2_B", 6.9, 5.8);
        agregarPunto("COM_P3_M2_C", 6.9, 8.1);
        agregarPunto("COM_P3_M2_D", 4.8, 8.1);

        agregarPunto("COM_P3_CH_A", 7.2, 6.4);
        agregarPunto("COM_P3_CH_B", 7.8, 6.4);
        agregarPunto("COM_P3_CH_C", 7.8, 7.7);
        agregarPunto("COM_P3_CH_D", 7.2, 7.7);

        agregarPunto("COM_P3_P1_A", 3.75, 4.15);
        agregarPunto("COM_P3_P1_B", 3.85, 4.15);
        agregarPunto("COM_P3_P1_C", 3.85, 4.25);
        agregarPunto("COM_P3_P1_D", 3.75, 4.25);

        agregarPunto("COM_P3_P2_A", 7.85, 4.15);
        agregarPunto("COM_P3_P2_B", 7.95, 4.15);
        agregarPunto("COM_P3_P2_C", 7.95, 4.25);
        agregarPunto("COM_P3_P2_D", 7.85, 4.25);

        agregarPunto("COM_P3_P3_A", 3.75, 9.45);
        agregarPunto("COM_P3_P3_B", 3.85, 9.45);
        agregarPunto("COM_P3_P3_C", 3.85, 9.55);
        agregarPunto("COM_P3_P3_D", 3.75, 9.55);

        agregarPunto("COM_P3_P4_A", 7.85, 9.45);
        agregarPunto("COM_P3_P4_B", 7.95, 9.45);
        agregarPunto("COM_P3_P4_C", 7.95, 9.55);
        agregarPunto("COM_P3_P4_D", 7.85, 9.55);

        // PERGOLA BANCO PISO 3
        agregarPunto("BAN_P3_B_A", 0.2, 3.9);
        agregarPunto("BAN_P3_B_B", 2.3, 3.9);
        agregarPunto("BAN_P3_B_C", 2.3, 4.4);
        agregarPunto("BAN_P3_B_D", 0.2, 4.4);

        agregarPunto("BAN_P3_L_A", 2.15, 3.0);
        agregarPunto("BAN_P3_L_B", 2.25, 3.0);
        agregarPunto("BAN_P3_L_C", 2.25, 4.25);
        agregarPunto("BAN_P3_L_D", 2.15, 4.25);

        agregarPunto("BAN_P3_P1_A", 0.15, 2.95);
        agregarPunto("BAN_P3_P1_B", 0.25, 2.95);
        agregarPunto("BAN_P3_P1_C", 0.25, 3.05);
        agregarPunto("BAN_P3_P1_D", 0.15, 3.05);

        agregarPunto("BAN_P3_P2_A", 2.25, 2.95);
        agregarPunto("BAN_P3_P2_B", 2.35, 2.95);
        agregarPunto("BAN_P3_P2_C", 2.35, 3.05);
        agregarPunto("BAN_P3_P2_D", 2.25, 3.05);

        // TERRAZA PISO 3
        agregarPunto("TER_P3_S1_A", 0.2, 6.1);
        agregarPunto("TER_P3_S1_B", 3.0, 6.1);
        agregarPunto("TER_P3_S1_C", 3.0, 6.9);
        agregarPunto("TER_P3_S1_D", 0.2, 6.9);

        agregarPunto("TER_P3_S2_A", 0.2, 5.6);
        agregarPunto("TER_P3_S2_B", 1.2, 5.6);
        agregarPunto("TER_P3_S2_C", 1.2, 6.1);
        agregarPunto("TER_P3_S2_D", 0.2, 6.1);

        agregarPunto("TER_P3_M_A", 1.7, 5.5);
        agregarPunto("TER_P3_M_B", 2.5, 5.5);
        agregarPunto("TER_P3_M_C", 2.5, 5.9);
        agregarPunto("TER_P3_M_D", 1.7, 5.9);

        agregarPunto("TER_P3_P_A", 2.8, 5.7);
        agregarPunto("TER_P3_P_B", 2.9, 5.7);
        agregarPunto("TER_P3_P_C", 2.9, 5.9);
        agregarPunto("TER_P3_P_D", 2.8, 5.9);

        agregarPunto("TER_P3_PL_A", 0.35, 5.25);
        agregarPunto("TER_P3_PL_B", 0.55, 5.25);
        agregarPunto("TER_P3_PL_C", 0.55, 5.45);
        agregarPunto("TER_P3_PL_D", 0.35, 5.45);

        // ALETAS (Muros Diagonales)
        agregarPunto("AL_P3_1_A", 4.9, 11.925);
        agregarPunto("AL_P3_1_B", 7.9, 11.925);
        agregarPunto("AL_P3_1_C", 7.9, 12.075);
        agregarPunto("AL_P3_1_D", 4.9, 12.075);

        agregarPunto("AL_P3_2_A", 4.9, 12.555);
        agregarPunto("AL_P3_2_B", 7.9, 12.555);
        agregarPunto("AL_P3_2_C", 7.9, 12.705);
        agregarPunto("AL_P3_2_D", 4.9, 12.705);

        agregarPunto("AL_P3_3_A", 4.9, 13.185);
        agregarPunto("AL_P3_3_B", 7.9, 13.185);
        agregarPunto("AL_P3_3_C", 7.9, 13.335);
        agregarPunto("AL_P3_3_D", 4.9, 13.335);

        agregarPunto("AL_P3_4_A", 4.9, 13.825);
        agregarPunto("AL_P3_4_B", 7.9, 13.825);
        agregarPunto("AL_P3_4_C", 7.9, 13.975);
        agregarPunto("AL_P3_4_D", 4.9, 13.975);

        // mesa regalos
        agregarPunto("MESA_P3_S1", 6.7, 16.7);
        agregarPunto("MESA_P3_V1", 7.8, 16.7);
        agregarPunto("MESA_P3_T1", 6.7, 14.6);
        agregarPunto("MESA_P3_U1", 7.8, 16.7);
    }

    private void crearParedesTerceraPlanta() {
        double alturaBase3 = 6.4; // Altura de inicio del piso 3
        double alturaSemiMuro = 1.0; // Altura de los semimuros
        // Paredes completas: solo E->G, G->F, F->C, F->O, N->O

        // --- SEMIMUROS (perímetro exterior y divisiones internas) ---
        agregarPared("P3_f", "P3_J", "P3_I", alturaBase3, alturaSemiMuro);
        agregarPared("P3_pareddeescaleras", "P3_N", "P3_C", alturaBase3, alturaSemiMuro);
        agregarPared("P3_g", "P3_I", "P3_G", alturaBase3, alturaSemiMuro);
        // P3_h: E->G = PARED COMPLETA
        agregarPared("P3_h", "P3_G", "P3_E", alturaBase3);
        agregarPared("P3_i", "P3_J", "P3_L1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_j", "P3_L1", "P3_K", alturaBase3, alturaSemiMuro);

        agregarPared("P3_k", "P3_L", "P3_M", alturaBase3, alturaSemiMuro);
        agregarPared("P3_l", "P3_M", "P3_M1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_m", "P3_M1", "P3_K1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_n", "P3_K1", "P3_J1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_p", "P3_J1", "P3_I1", alturaBase3, alturaSemiMuro);
        // agregarPared("P3_q", "P3_I1", "P3_M1", alturaBase3, alturaSemiMuro);

        
        agregarPared("P3_s", "P3_L1", "P3_M", alturaBase3, 0.5); // 1/4 muro (jardin)
        agregarPared("P3_t", "P3_I1", "P3_C1", alturaBase3, alturaSemiMuro);

        agregarPared("P3_a", "P3_C1", "P3_B1", alturaBase3, 0.5); // 1/4 muro (jardin)
        agregarPared("P3_b", "P3_B1", "P3_E1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_c", "P3_E1", "P3_D1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_d", "P3_D1", "P3_C1", alturaBase3, alturaSemiMuro);
        // agregarPared("P3_e", "P3_D1", "P3_Z", alturaBase3, alturaSemiMuro);

        agregarPared("P3_f1", "P3_Z", "P3_A1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_g1", "P3_A1", "P3_E1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_h1", "P3_Z", "P3_W", alturaBase3, alturaSemiMuro);
        agregarPared("P3_i1", "P3_W", "P3_H1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_j1", "P3_H1", "P3_N1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_r_azul", "P3_L", "P3_semi_azul", alturaBase3, alturaSemiMuro);
        agregarPared("P3_r_madera", "P3_semi_azul", "P3_semi_madera", alturaBase3, alturaSemiMuro);
        agregarPared("P3_r_resto", "P3_semi_madera", "P3_K", alturaBase3, alturaSemiMuro);
        agregarPared("P3_k1", "P3_N1", "P3_V", alturaBase3, alturaSemiMuro);
        agregarPared("P3_l1", "P3_V", "P3_W", alturaBase3, alturaSemiMuro);

        agregarPared("P3_m1", "P3_G1", "P3_H1", alturaBase3, 0.5); // 1/4 muro (jardin)
        agregarPared("P3_n1", "P3_G1", "P3_F1", alturaBase3, 0.5); // 1/4 muro (jardin)
        agregarPared("P3_p1", "P3_F1", "P3_A1", alturaBase3, alturaSemiMuro);

        agregarPared("P3_q1", "P3_R1", "P3_O1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_r1", "P3_O1", "P3_P1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_s1", "P3_P1", "P3_Q1", alturaBase3, alturaSemiMuro);
        agregarPared("P3_t1", "P3_Q1", "P3_R1", alturaBase3, alturaSemiMuro);

        // P3_a1: N->O = PARED COMPLETA (oculta la aleta trasera, la comentamos)
        // agregarPared("P3_a1", "P3_N", "P3_O", alturaBase3);
        // P3_b1: O->F = PARED COMPLETA (lateral, se queda)
        agregarPared("P3_b1", "P3_O", "P3_F", alturaBase3);
        // P3_c1: F->C = PARED COMPLETA (oculta la aleta frontal, la comentamos)
        // agregarPared("P3_c1", "P3_F", "P3_C", alturaBase3);
        // P3_e1: G->F = PARED COMPLETA
        agregarPared("P3_e1", "P3_G", "P3_F", alturaBase3);

        // agregarPared("P3_C1", "P3_F", "P3_C", alturaBase3);
        agregarPared("P3_nuevo1", "P3_D", "P3_C", alturaBase3);

        agregarPared("P3_f2", "P3_O", "P3_P", alturaBase3, alturaSemiMuro);
        agregarPared("P3_g2", "P3_P", "P3_Q", alturaBase3, alturaSemiMuro);
        agregarPared("P3_h2", "P3_Q", "P3_R", alturaBase3, alturaSemiMuro);
        agregarPared("P3_i2", "P3_R", "P3_S", alturaBase3, alturaSemiMuro);
        agregarPared("P3_j2", "P3_S", "P3_T", alturaBase3, alturaSemiMuro);
        agregarPared("P3_k2", "P3_T", "P3_U", alturaBase3, alturaSemiMuro);
        agregarPared("P3_l2", "P3_U", "P3_V", alturaBase3, alturaSemiMuro);

        // ==========================================================
        // DINTEL Cuarto (Tercer Piso)
        // ==========================================================
        agregarPared("Dintel 3er Piso", "P3_E", "P3_D", 9.1, 0.5);

        // Colisiones Invisibles - COMEDOR PISO 3
        agregarParedInvisible("Col_COM_P3_M1_1", "COM_P3_M1_A", "COM_P3_M1_B", 6.4);
        agregarParedInvisible("Col_COM_P3_M1_2", "COM_P3_M1_B", "COM_P3_M1_C", 6.4);
        agregarParedInvisible("Col_COM_P3_M1_3", "COM_P3_M1_C", "COM_P3_M1_D", 6.4);
        agregarParedInvisible("Col_COM_P3_M1_4", "COM_P3_M1_D", "COM_P3_M1_A", 6.4);

        agregarParedInvisible("Col_COM_P3_M2_1", "COM_P3_M2_A", "COM_P3_M2_B", 6.4);
        agregarParedInvisible("Col_COM_P3_M2_2", "COM_P3_M2_B", "COM_P3_M2_C", 6.4);
        agregarParedInvisible("Col_COM_P3_M2_3", "COM_P3_M2_C", "COM_P3_M2_D", 6.4);
        agregarParedInvisible("Col_COM_P3_M2_4", "COM_P3_M2_D", "COM_P3_M2_A", 6.4);

        agregarParedInvisible("Col_COM_P3_CH_1", "COM_P3_CH_A", "COM_P3_CH_B", 6.4);
        agregarParedInvisible("Col_COM_P3_CH_2", "COM_P3_CH_B", "COM_P3_CH_C", 6.4);
        agregarParedInvisible("Col_COM_P3_CH_3", "COM_P3_CH_C", "COM_P3_CH_D", 6.4);
        agregarParedInvisible("Col_COM_P3_CH_4", "COM_P3_CH_D", "COM_P3_CH_A", 6.4);

        agregarParedInvisible("Col_COM_P3_P1_1", "COM_P3_P1_A", "COM_P3_P1_B", 6.4);
        agregarParedInvisible("Col_COM_P3_P1_2", "COM_P3_P1_B", "COM_P3_P1_C", 6.4);
        agregarParedInvisible("Col_COM_P3_P1_3", "COM_P3_P1_C", "COM_P3_P1_D", 6.4);
        agregarParedInvisible("Col_COM_P3_P1_4", "COM_P3_P1_D", "COM_P3_P1_A", 6.4);

        agregarParedInvisible("Col_COM_P3_P2_1", "COM_P3_P2_A", "COM_P3_P2_B", 6.4);
        agregarParedInvisible("Col_COM_P3_P2_2", "COM_P3_P2_B", "COM_P3_P2_C", 6.4);
        agregarParedInvisible("Col_COM_P3_P2_3", "COM_P3_P2_C", "COM_P3_P2_D", 6.4);
        agregarParedInvisible("Col_COM_P3_P2_4", "COM_P3_P2_D", "COM_P3_P2_A", 6.4);

        agregarParedInvisible("Col_COM_P3_P3_1", "COM_P3_P3_A", "COM_P3_P3_B", 6.4);
        agregarParedInvisible("Col_COM_P3_P3_2", "COM_P3_P3_B", "COM_P3_P3_C", 6.4);
        agregarParedInvisible("Col_COM_P3_P3_3", "COM_P3_P3_C", "COM_P3_P3_D", 6.4);
        agregarParedInvisible("Col_COM_P3_P3_4", "COM_P3_P3_D", "COM_P3_P3_A", 6.4);

        agregarParedInvisible("Col_COM_P3_P4_1", "COM_P3_P4_A", "COM_P3_P4_B", 6.4);
        agregarParedInvisible("Col_COM_P3_P4_2", "COM_P3_P4_B", "COM_P3_P4_C", 6.4);
        agregarParedInvisible("Col_COM_P3_P4_3", "COM_P3_P4_C", "COM_P3_P4_D", 6.4);
        agregarParedInvisible("Col_COM_P3_P4_4", "COM_P3_P4_D", "COM_P3_P4_A", 6.4);

        // Colisiones Invisibles - PERGOLA BANCO PISO 3
        agregarParedInvisible("Col_BAN_P3_B_1", "BAN_P3_B_A", "BAN_P3_B_B", 6.4);
        agregarParedInvisible("Col_BAN_P3_B_2", "BAN_P3_B_B", "BAN_P3_B_C", 6.4);
        agregarParedInvisible("Col_BAN_P3_B_3", "BAN_P3_B_C", "BAN_P3_B_D", 6.4);
        agregarParedInvisible("Col_BAN_P3_B_4", "BAN_P3_B_D", "BAN_P3_B_A", 6.4);

        agregarParedInvisible("Col_BAN_P3_L_1", "BAN_P3_L_A", "BAN_P3_L_B", 6.4);
        agregarParedInvisible("Col_BAN_P3_L_2", "BAN_P3_L_B", "BAN_P3_L_C", 6.4);
        agregarParedInvisible("Col_BAN_P3_L_3", "BAN_P3_L_C", "BAN_P3_L_D", 6.4);
        agregarParedInvisible("Col_BAN_P3_L_4", "BAN_P3_L_D", "BAN_P3_L_A", 6.4);

        agregarParedInvisible("Col_BAN_P3_P1_1", "BAN_P3_P1_A", "BAN_P3_P1_B", 6.4);
        agregarParedInvisible("Col_BAN_P3_P1_2", "BAN_P3_P1_B", "BAN_P3_P1_C", 6.4);
        agregarParedInvisible("Col_BAN_P3_P1_3", "BAN_P3_P1_C", "BAN_P3_P1_D", 6.4);
        agregarParedInvisible("Col_BAN_P3_P1_4", "BAN_P3_P1_D", "BAN_P3_P1_A", 6.4);

        agregarParedInvisible("Col_BAN_P3_P2_1", "BAN_P3_P2_A", "BAN_P3_P2_B", 6.4);
        agregarParedInvisible("Col_BAN_P3_P2_2", "BAN_P3_P2_B", "BAN_P3_P2_C", 6.4);
        agregarParedInvisible("Col_BAN_P3_P2_3", "BAN_P3_P2_C", "BAN_P3_P2_D", 6.4);
        agregarParedInvisible("Col_BAN_P3_P2_4", "BAN_P3_P2_D", "BAN_P3_P2_A", 6.4);

        // Colisiones Invisibles - TERRAZA PISO 3
        agregarParedInvisible("Col_TER_P3_S1_1", "TER_P3_S1_A", "TER_P3_S1_B", 6.4);
        agregarParedInvisible("Col_TER_P3_S1_2", "TER_P3_S1_B", "TER_P3_S1_C", 6.4);
        agregarParedInvisible("Col_TER_P3_S1_3", "TER_P3_S1_C", "TER_P3_S1_D", 6.4);
        agregarParedInvisible("Col_TER_P3_S1_4", "TER_P3_S1_D", "TER_P3_S1_A", 6.4);

        agregarParedInvisible("Col_TER_P3_S2_1", "TER_P3_S2_A", "TER_P3_S2_B", 6.4);
        agregarParedInvisible("Col_TER_P3_S2_2", "TER_P3_S2_B", "TER_P3_S2_C", 6.4);
        agregarParedInvisible("Col_TER_P3_S2_3", "TER_P3_S2_C", "TER_P3_S2_D", 6.4);
        agregarParedInvisible("Col_TER_P3_S2_4", "TER_P3_S2_D", "TER_P3_S2_A", 6.4);

        agregarParedInvisible("Col_TER_P3_M_1", "TER_P3_M_A", "TER_P3_M_B", 6.4);
        agregarParedInvisible("Col_TER_P3_M_2", "TER_P3_M_B", "TER_P3_M_C", 6.4);
        agregarParedInvisible("Col_TER_P3_M_3", "TER_P3_M_C", "TER_P3_M_D", 6.4);
        agregarParedInvisible("Col_TER_P3_M_4", "TER_P3_M_D", "TER_P3_M_A", 6.4);

        agregarParedInvisible("Col_TER_P3_P_1", "TER_P3_P_A", "TER_P3_P_B", 6.4);
        agregarParedInvisible("Col_TER_P3_P_2", "TER_P3_P_B", "TER_P3_P_C", 6.4);
        agregarParedInvisible("Col_TER_P3_P_3", "TER_P3_P_C", "TER_P3_P_D", 6.4);
        agregarParedInvisible("Col_TER_P3_P_4", "TER_P3_P_D", "TER_P3_P_A", 6.4);

        agregarParedInvisible("Col_TER_P3_PL_1", "TER_P3_PL_A", "TER_P3_PL_B", 6.4);
        agregarParedInvisible("Col_TER_P3_PL_2", "TER_P3_PL_B", "TER_P3_PL_C", 6.4);
        agregarParedInvisible("Col_TER_P3_PL_3", "TER_P3_PL_C", "TER_P3_PL_D", 6.4);
        agregarParedInvisible("Col_TER_P3_PL_4", "TER_P3_PL_D", "TER_P3_PL_A", 6.4);

        // Colisiones Invisibles - ALETAS PISO 3
        agregarParedInvisible("Col_AL_P3_1_1", "AL_P3_1_A", "AL_P3_1_B", 6.4);
        agregarParedInvisible("Col_AL_P3_1_2", "AL_P3_1_B", "AL_P3_1_C", 6.4);
        agregarParedInvisible("Col_AL_P3_1_3", "AL_P3_1_C", "AL_P3_1_D", 6.4);
        agregarParedInvisible("Col_AL_P3_1_4", "AL_P3_1_D", "AL_P3_1_A", 6.4);

        agregarParedInvisible("Col_AL_P3_2_1", "AL_P3_2_A", "AL_P3_2_B", 6.4);
        agregarParedInvisible("Col_AL_P3_2_2", "AL_P3_2_B", "AL_P3_2_C", 6.4);
        agregarParedInvisible("Col_AL_P3_2_3", "AL_P3_2_C", "AL_P3_2_D", 6.4);
        agregarParedInvisible("Col_AL_P3_2_4", "AL_P3_2_D", "AL_P3_2_A", 6.4);

        agregarParedInvisible("Col_AL_P3_3_1", "AL_P3_3_A", "AL_P3_3_B", 6.4);
        agregarParedInvisible("Col_AL_P3_3_2", "AL_P3_3_B", "AL_P3_3_C", 6.4);
        agregarParedInvisible("Col_AL_P3_3_3", "AL_P3_3_C", "AL_P3_3_D", 6.4);
        agregarParedInvisible("Col_AL_P3_3_4", "AL_P3_3_D", "AL_P3_3_A", 6.4);

        agregarParedInvisible("Col_AL_P3_4_1", "AL_P3_4_A", "AL_P3_4_B", 6.4);
        agregarParedInvisible("Col_AL_P3_4_2", "AL_P3_4_B", "AL_P3_4_C", 6.4);
        agregarParedInvisible("Col_AL_P3_4_3", "AL_P3_4_C", "AL_P3_4_D", 6.4);
        agregarParedInvisible("Col_AL_P3_4_4", "AL_P3_4_D", "AL_P3_4_A", 6.4);

        agregarParedInvisible("MESA_P3_S1_1", "MESA_P3_S1", "MESA_P3_V1", 6.4);
        agregarParedInvisible("MESA_P3_S1_2", "MESA_P3_V1", "MESA_P3_U1", 6.4);
        agregarParedInvisible("MESA_P3_S1_3", "MESA_P3_U1", "MESA_P3_T1", 6.4);
        agregarParedInvisible("MESA_P3_S1_4", "MESA_P3_T1", "MESA_P3_S1", 6.4);
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
