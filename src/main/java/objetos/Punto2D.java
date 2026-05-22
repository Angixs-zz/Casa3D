package main.java.objetos;

public class Punto2D {
    private String nombre;
    private double x;
    private double y;

    public Punto2D(String nombre, double x, double y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return nombre + " = (" + x + ", " + y + ")";
    }
}