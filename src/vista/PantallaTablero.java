package vista;

import control.ControlJuego;
import excepciones.SeleccionInvalidaException;

import javax.swing.*;
import java.awt.*;

public class PantallaTablero extends JFrame {

    private ControlJuego control;

    private BotonCarta[] botones = new BotonCarta[36];

    private JLabel lblJugador1;
    private JLabel lblJugador2;
    private JLabel lblTurno;

    private JPanel panelTablero;

    private Timer temporizador;

    public PantallaTablero(ControlJuego control) {

        this.control = control;

        setTitle("Juego de Memoria Pokémon");
        setSize(850, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        iniciarComponentes();

        actualizarPantalla();
    }

    public void iniciarComponentes() {

        setLayout(new BorderLayout(10, 10));

        // Panel superior: marcadores.

        JPanel panelSuperior = new JPanel(new GridLayout(2, 2, 10, 5));
        panelSuperior.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 5, 20)
        );

        lblJugador1 = new JLabel("", SwingConstants.CENTER);
        lblJugador2 = new JLabel("", SwingConstants.CENTER);

        lblJugador1.setFont(new Font("Arial", Font.BOLD, 16));
        lblJugador2.setFont(new Font("Arial", Font.BOLD, 16));

        panelSuperior.add(lblJugador1);
        panelSuperior.add(lblJugador2);

        lblTurno = new JLabel("", SwingConstants.CENTER);
        lblTurno.setFont(new Font("Arial", Font.BOLD, 18));

        panelSuperior.add(lblTurno);

        JLabel lblIndicacion = new JLabel(
                "Encuentra las 18 parejas",
                SwingConstants.CENTER
        );

        panelSuperior.add(lblIndicacion);

        add(panelSuperior, BorderLayout.NORTH);

        // Panel central: tablero 6 x 6.

        panelTablero = new JPanel(new GridLayout(6, 6, 6, 6));

        panelTablero.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        );

        for (int i = 0; i < botones.length; i++) {

            botones[i] = new BotonCarta();

            final int posicion = i;

            botones[i].addActionListener(e -> seleccionarCarta(posicion));

            panelTablero.add(botones[i]);
        }

        add(panelTablero, BorderLayout.CENTER);

        // Panel inferior.

        JLabel lblPie = new JLabel(
                "Juego de Memoria Pokémon",
                SwingConstants.CENTER
        );

        lblPie.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 10, 5)
        );

        add(lblPie, BorderLayout.SOUTH);
    }

    private void seleccionarCarta(int posicion) {

        try {

            int resultado = control.seleccionarCarta(posicion);

            actualizarPantalla();

            if (resultado == ControlJuego.NO_PAREJA) {

                // Bloquear el tablero durante un segundo.

                bloquearTablero();

                temporizador = new Timer(1000, e -> {

                    temporizador.stop();

                    control.ocultarNoPareja();

                    actualizarPantalla();
                });

                temporizador.setRepeats(false);
                temporizador.start();
            }

            if (control.finalizarJuego()) {

                finalizarPartida();
            }

        } catch (SeleccionInvalidaException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Selección inválida",
                    JOptionPane.WARNING_MESSAGE
            );

        }
    }

    private void actualizarPantalla() {

        lblJugador1.setText(
                control.getJugador(0).getNombre()
                        + " | Parejas: "
                        + control.getJugador(0).getAciertos()
                        + " | Puntos: "
                        + control.getJugador(0).getPuntos()
        );

        lblJugador2.setText(
                control.getJugador(1).getNombre()
                        + " | Parejas: "
                        + control.getJugador(1).getAciertos()
                        + " | Puntos: "
                        + control.getJugador(1).getPuntos()
        );

        String nombreTurno = control.getJugador(
                control.getTurno()
        ).getNombre();

        lblTurno.setText("Turno de: " + nombreTurno);

        if (control.getTurno() == 0) {

            lblTurno.setForeground(new Color(30, 100, 200));

        } else {

            lblTurno.setForeground(new Color(30, 140, 70));

        }

        for (int i = 0; i < botones.length; i++) {

            botones[i].setCarta(control.getCarta(i));

            botones[i].setEnabled(
                    !control.isBloqueado()
                            && !control.getCarta(i).isDescubierta()
                            && !control.getCarta(i).isEmparejada()
            );
        }
    }

    private void bloquearTablero() {

        for (int i = 0; i < botones.length; i++) {
            botones[i].setEnabled(false);
        }
    }

    private void finalizarPartida() {

        if (temporizador != null && temporizador.isRunning()) {
            temporizador.stop();
        }

        new PantallaVictoria(control, this).setVisible(true);

        dispose();
    }
}
