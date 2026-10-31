package control;

import excepciones.SeleccionInvalidaException;
import modelo.Carta;
import modelo.Jugador;


public abstract class ControlJuego{

    public static final int PRIMERA = 0;
    public static final int PAREJA = 1;
    public static final int NO_PAREJA = 2;


    public abstract void iniciarJuego();
    public abstract void cambiarTurno();
    public abstract boolean verificarPareja(int pos1, int pos2);
    public abstract boolean finalizarJuego();


    public abstract int seleccionarCarta(int pos) throws SeleccionInvalidaException;
    public abstract void ocultarNoPareja();
    public abstract Carta getCarta(int pos);
    public abstract Jugador getJugador(int indice);
    public abstract int getTurno();
    public abstract boolean isBloqueado();
    public abstract String getMensajeFinal();
}
