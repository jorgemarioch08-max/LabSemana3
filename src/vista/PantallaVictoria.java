package vista;

import control.ControlJuego;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import modelo.Jugador;

public class PantallaVictoria extends JFrame{
    private ControlJuego control;
    private JFrame ventanaTablero;

    public PantallaVictoria(ControlJuego control, JFrame ventanaTablero){
        this.control = control;
        this.ventanaTablero = ventanaTablero;

        setTitle("Fin de la partida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        JLabel lblTitulo = new JLabel("Fin de la partida", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15,10,0,10));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(4,1,5,5));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        JLabel lblMensaje = new JLabel(control.getMensajeFinal(), SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 16));
        lblMensaje.setForeground(new Color(46,125,50));
        panelCentro.add(lblMensaje);

        panelCentro.add(new JLabel("Resultados:", SwingConstants.CENTER));
        panelCentro.add(crearResultado(control.getJugador(0)));
        panelCentro.add(crearResultado(control.getJugador(1)));
        add(panelCentro, BorderLayout.CENTER);

        //Abajo: botones
        Acciones acciones = new Acciones();
        JButton btnNueva = new JButton("Nueva partida");
        JButton btnSalir = new JButton("Salir");
        btnNueva.addActionListener(acciones);
        btnSalir.addActionListener(acciones);

        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
        panelBotones.add(btnNueva);
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(450,300);
        setLocationRelativeTo(null);
    }

    private JLabel crearResultado(Jugador j){
        JLabel lbl = new JLabel(j.getNombre()+":  "+j.getAciertos()+" parejas,  "+j.getPuntos()+" puntos",
                SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        return lbl;
    }

    private class Acciones implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("Nueva partida")){
                control.iniciarJuego();
                ventanaTablero.dispose();
                new PantallaTablero(control).setVisible(true);
                dispose();
            }else if(e.getActionCommand().equals("Salir")){
                System.exit(0);
            }
        }
    }
}