package objetos;

public class Pared {
    private String nombre;
    private Punto2D inicio;
    private Punto2D fin;
    private double altura;
    private double grosor;
    private double alturaBase;

    public Pared(String nombre, Punto2D inicio, Punto2D fin, double altura, double grosor) {
        this.nombre = nombre;
        this.inicio = inicio;
        this.fin = fin;
        this.altura = altura;
        this.grosor = grosor;
        this.alturaBase = alturaBase;
    }

    public String getNombre() {
        return nombre;
    }

    public Punto2D getInicio() {
        return inicio;
    }

    public Punto2D getFin() {
        return fin;
    }

    public double getAltura() {
        return altura;
    }

    public double getGrosor() {
        return grosor;
    }

    public double calcularLongitud() {
        double dx = fin.getX() - inicio.getX();
        double dy = fin.getY() - inicio.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    public void mostrarInformacion() {
        System.out.println("Pared " + nombre);
        System.out.println("Desde: " + inicio);
        System.out.println("Hasta: " + fin);
        System.out.println("Longitud: " + calcularLongitud());
        System.out.println("Altura: " + altura);
        System.out.println("Grosor: " + grosor);
        System.out.println("-----------------------------");
    }
}