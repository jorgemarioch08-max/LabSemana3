package vista;

import control.*;
import javax.swing.*;
import java.awt.*;

public class PantallaInicio extends JFrame {
    private JLabel lblTitulo, lblJugador1, lblJugador2;
    private JLabel lblNombre1, lblNombre2;

    private JTextField txtJugador1, txtJugador2;

    private JButton btnIniciar;

    public PantallaInicio() {

        setTitle("Juego de Memoria");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
        setVisible(true);
    }

    public void iniciarComponentes() {

        setLayout(null);

        lblTitulo = new JLabel("Juego de Memoria");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 23));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(80, 35, 440, 35);
        add(lblTitulo);

        lblJugador1 = new JLabel("Jugador 1");
        lblJugador1.setFont(new Font("Arial", Font.BOLD, 17));
        lblJugador1.setBounds(75, 120, 200, 25);
        add(lblJugador1);

        lblJugador2 = new JLabel("Jugador 2");
        lblJugador2.setFont(new Font("Arial", Font.BOLD, 17));
        lblJugador2.setBounds(325, 120, 200, 25);
        add(lblJugador2);

        lblNombre1 = new JLabel("Nombre:");
        lblNombre1.setBounds(75, 160, 200, 20);
        add(lblNombre1);

        lblNombre2 = new JLabel("Nombre:");
        lblNombre2.setBounds(325, 160, 200, 20);
        add(lblNombre2);

        txtJugador1 = new JTextField();
        txtJugador1.setBounds(75, 185, 200, 30);
        add(txtJugador1);

        txtJugador2 = new JTextField();
        txtJugador2.setBounds(325, 185, 200, 30);
        add(txtJugador2);

        btnIniciar = new JButton("Iniciar Juego");
        btnIniciar.setFont(new Font("Arial", Font.PLAIN, 15));
        btnIniciar.setBounds(210, 265, 180, 40);
        btnIniciar.addActionListener(e -> iniciarJuego());
        add(btnIniciar);
    }

    public void iniciarJuego() {

    }

}
