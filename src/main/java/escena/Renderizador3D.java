package main.java.escena;

import javax.swing.JPanel;

import main.java.objetos.Pared;
import main.java.objetos.Punto2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Renderizador3D extends JPanel implements KeyListener {

    private Casa casa;

    private double rotacion = 45;
    private double inclinacion = 25;
    private double zoom = 35;

    private double centroX = 4;
    private double centroZ = 10;

    public Renderizador3D(Casa casa) {
        this.casa = casa;
        setBackground(new Color(230, 230, 230));
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        dibujarPiso(g2);
        dibujarParedes(g2);
        dibujarTexto(g2);
    }

    private void dibujarParedes(Graphics2D g2) {
        ArrayList<Pared> paredes = casa.getParedes();

        for (Pared pared : paredes) {
            Punto2D a = pared.getInicio();
            Punto2D b = pared.getFin();

            double altura = pared.getAltura();

            int[] p1 = proyectar(a.getX(), 0, a.getY());
            int[] p2 = proyectar(b.getX(), 0, b.getY());
            int[] p3 = proyectar(b.getX(), altura, b.getY());
            int[] p4 = proyectar(a.getX(), altura, a.getY());

            Polygon poligono = new Polygon();
            poligono.addPoint(p1[0], p1[1]);
            poligono.addPoint(p2[0], p2[1]);
            poligono.addPoint(p3[0], p3[1]);
            poligono.addPoint(p4[0], p4[1]);

            g2.setColor(new Color(180, 180, 180));
            g2.fillPolygon(poligono);

            g2.setColor(Color.BLACK);
            g2.drawPolygon(poligono);
        }
    }

    private void dibujarPiso(Graphics2D g2) {
        int[] p1 = proyectar(0, 0, 0);
        int[] p2 = proyectar(8, 0, 0);
        int[] p3 = proyectar(8, 0, 21);
        int[] p4 = proyectar(0, 0, 21);

        Polygon piso = new Polygon();
        piso.addPoint(p1[0], p1[1]);
        piso.addPoint(p2[0], p2[1]);
        piso.addPoint(p3[0], p3[1]);
        piso.addPoint(p4[0], p4[1]);

        g2.setColor(new Color(210, 210, 210));
        g2.fillPolygon(piso);

        g2.setColor(Color.DARK_GRAY);
        g2.drawPolygon(piso);
    }

private int[] proyectar(double x, double y, double z) {

    // Medidas aproximadas del plano de GeoGebra
    double anchoPlano = 8.0;
    double largoPlano = 21.0;

    // Corrección tipo espejo
    x = anchoPlano - x;

    // Si después también la ves volteada de arriba hacia abajo,
    // activa esta línea quitando las diagonales:
    // z = largoPlano - z;

    x = x - centroX;
    z = z - centroZ;

    double angulo = Math.toRadians(rotacion);

    double xRotado = x * Math.cos(angulo) - z * Math.sin(angulo);
    double zRotado = x * Math.sin(angulo) + z * Math.cos(angulo);

    double inclinacionRad = Math.toRadians(inclinacion);

    double yPantalla = y * Math.cos(inclinacionRad) - zRotado * Math.sin(inclinacionRad);

    int pantallaX = (int) (getWidth() / 2 + xRotado * zoom);
    int pantallaY = (int) (getHeight() / 2 - yPantalla * zoom);

    return new int[]{pantallaX, pantallaY};
}
    private void dibujarTexto(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.drawString("Casa 3D - Primera planta", 20, 25);
        g2.drawString("Flechas izquierda/derecha: girar", 20, 45);
        g2.drawString("Flechas arriba/abajo: inclinar", 20, 65);
        g2.drawString("+ y - : zoom", 20, 85);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (tecla == KeyEvent.VK_LEFT) {
            rotacion -= 5;
        }

        if (tecla == KeyEvent.VK_RIGHT) {
            rotacion += 5;
        }

        if (tecla == KeyEvent.VK_UP) {
            inclinacion += 5;
        }

        if (tecla == KeyEvent.VK_DOWN) {
            inclinacion -= 5;
        }

        if (tecla == KeyEvent.VK_PLUS || tecla == KeyEvent.VK_ADD) {
            zoom += 5;
        }

        if (tecla == KeyEvent.VK_MINUS || tecla == KeyEvent.VK_SUBTRACT) {
            zoom -= 5;
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}