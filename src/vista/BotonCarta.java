package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;

public class BotonCarta extends JButton {

    private Carta carta;

    public BotonCarta() {

        setPreferredSize(new Dimension(85, 85));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int x = 3;
        int y = 3;
        int w = getWidth() - 6;
        int h = getHeight() - 6;

        if (carta == null || !carta.isDescubierta()) {

            // Reverso de la carta.

            g2.setColor(new Color(55, 30, 100));
            g2.fillRoundRect(x, y, w, h, 12, 12);

            g2.setColor(new Color(180, 150, 220));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(x, y, w, h, 12, 12);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 28));

            FontMetrics fm = g2.getFontMetrics();

            String signo = "?";

            g2.drawString(
                    signo,
                    (getWidth() - fm.stringWidth(signo)) / 2,
                    getHeight() / 2 + 10
            );

        } else {

            // Cara de la carta.

            g2.setColor(Color.WHITE);
            g2.fillRoundRect(x, y, w, h, 12, 12);

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 12));

            String nombre = carta.getNombre();

            FontMetrics fm = g2.getFontMetrics();

            if (fm.stringWidth(nombre) > w - 8) {
                nombre = nombre.substring(0, Math.min(7, nombre.length())) + "...";
            }

            g2.drawString(
                    nombre,
                    (getWidth() - fm.stringWidth(nombre)) / 2,
                    getHeight() / 2 + 5
            );

            if (carta instanceof CartaEspecial) {

                g2.setColor(new Color(218, 165, 32));
                g2.setStroke(new BasicStroke(4));

            } else {

                g2.setColor(Color.GRAY);
                g2.setStroke(new BasicStroke(1));

            }

            g2.drawRoundRect(x, y, w, h, 12, 12);
        }

        g2.dispose();
    }
}