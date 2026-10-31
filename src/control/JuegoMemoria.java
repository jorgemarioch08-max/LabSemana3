package control;

import excepciones.NombreInvalidoException;
import excepciones.SeleccionInvalidaException;
import modelo.Carta;
import modelo.CartaEspecial;
import modelo.CartaEstandar;
import modelo.Jugador;

public class JuegoMemoria extends ControlJuego{
    public static final int TOTAL_CARTAS = 36;
    public static final int LARGO_MAXIMO = 15;

    private String[] nombres = {"Pikachu","Mewtwo","Charmander","Bulbasaur","Squirtle","Jigglypuff",
            "Meowth","Psyduck","Eevee","Snorlax","Gengar","Onix",
            "Magikarp","Gyarados","Lapras","Vulpix","Machop","Geodude"};

    private Carta[] tablero = new Carta[TOTAL_CARTAS];
    private Jugador[] jugadores = new Jugador[2];
    private int turno = 0;
    private int primera = -1;
    private int segunda = -1;
    private boolean bloqueado = false;

    public JuegoMemoria(String nombre1, String nombre2) throws NombreInvalidoException{
        validarNombre(nombre1, "Jugador 1");
        validarNombre(nombre2, "Jugador 2");
        if(nombre1.trim().equalsIgnoreCase(nombre2.trim())){
            throw new NombreInvalidoException("Los dos jugadores no pueden tener el mismo nombre.");
        }
        jugadores[0] = new Jugador(nombre1.trim());
        jugadores[1] = new Jugador(nombre2.trim());
        iniciarJuego();
    }

    private void validarNombre(String nombre, String quien) throws NombreInvalidoException{
        if(nombre==null || nombre.trim().isEmpty()){
            throw new NombreInvalidoException("Escriba el nombre del "+quien+".");
        }
        if(nombre.trim().length()>LARGO_MAXIMO){
            throw new NombreInvalidoException("El nombre del "+quien+" no puede tener mas de "+LARGO_MAXIMO+" letras.");
        }
    }


    @Override
    public void iniciarJuego(){
        for(int i=0;i<nombres.length;i++){
            Carta prototipo;
            if(i<2){
                prototipo = new CartaEspecial(nombres[i]);
            }else{
                prototipo = new CartaEstandar(nombres[i]);
            }
            Carta companera = prototipo.clone();
            if(companera==null){
                if(i<2){
                    companera = new CartaEspecial(nombres[i]);
                }else{
                    companera = new CartaEstandar(nombres[i]);
                }
            }
            tablero[i*2] = prototipo;
            tablero[i*2+1] = companera;
        }
        mezclar(tablero.length-1);

        jugadores[0].reiniciar();
        jugadores[1].reiniciar();
        turno = 0;
        primera = -1;
        segunda = -1;
        bloqueado = false;
    }

    @Override
    public void cambiarTurno(){
        if(turno==0){
            turno = 1;
        }else{
            turno = 0;
        }
    }

    @Override
    public boolean verificarPareja(int pos1, int pos2){
        try{
            return tablero[pos1].getNombre().equals(tablero[pos2].getNombre());
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
    }

    @Override
    public boolean finalizarJuego(){
        return contarEmparejadas(0)==TOTAL_CARTAS;
    }


    private void mezclar(int i){
        if(i<=0){
            return;
        }
        int j = (int)(Math.random()*(i+1));
        Carta temporal = tablero[i];
        tablero[i] = tablero[j];
        tablero[j] = temporal;
        mezclar(i-1);
    }


    private int contarEmparejadas(int pos){
        if(pos>=tablero.length){
            return 0;
        }
        int esta = 0;
        if(tablero[pos].isEmparejada()){
            esta = 1;
        }
        return esta+contarEmparejadas(pos+1);
    }


    @Override
    public int seleccionarCarta(int pos) throws SeleccionInvalidaException{
        if(bloqueado){
            throw new SeleccionInvalidaException("Espere a que se oculten las cartas.");
        }
        Carta carta;
        try{
            carta = tablero[pos];
        }catch(ArrayIndexOutOfBoundsException e){
            throw new SeleccionInvalidaException("La posicion "+pos+" no existe en el tablero.");
        }
        if(carta.isEmparejada()){
            throw new SeleccionInvalidaException("Esa carta ya fue emparejada.");
        }
        if(pos==primera){
            throw new SeleccionInvalidaException("No puede seleccionar la misma carta dos veces.");
        }
        if(carta.isDescubierta()){
            throw new SeleccionInvalidaException("Esa carta ya esta descubierta.");
        }

        carta.mostrar();
        if(primera==-1){
            primera = pos;
            return PRIMERA;
        }

        segunda = pos;
        if(verificarPareja(primera, segunda)){
            tablero[primera].marcarEmparejada();
            tablero[segunda].marcarEmparejada();
            jugadores[turno].sumarPareja(tablero[primera].getPuntos());
            primera = -1;
            segunda = -1;
            return PAREJA;
        }
        bloqueado = true;
        return NO_PAREJA;
    }

    @Override
    public void ocultarNoPareja(){
        if(primera!=-1){
            tablero[primera].ocultar();
        }
        if(segunda!=-1){
            tablero[segunda].ocultar();
        }
        primera = -1;
        segunda = -1;
        bloqueado = false;
        cambiarTurno();
    }

    @Override
    public Carta getCarta(int pos){
        try{
            return tablero[pos];
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    @Override
    public Jugador getJugador(int indice){
        try{
            return jugadores[indice];
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    @Override
    public int getTurno(){
        return turno;
    }

    @Override
    public boolean isBloqueado(){
        return bloqueado;
    }

    @Override
    public String getMensajeFinal(){
        Jugador j1 = jugadores[0];
        Jugador j2 = jugadores[1];
        if(j1.getPuntos()>j2.getPuntos()){
            return "Gana "+j1.getNombre()+" con "+j1.getPuntos()+" puntos ("+j1.getAciertos()+" parejas).";
        }
        if(j2.getPuntos()>j1.getPuntos()){
            return "Gana "+j2.getNombre()+" con "+j2.getPuntos()+" puntos ("+j2.getAciertos()+" parejas).";
        }
        return "Empate: los dos jugadores terminaron con "+j1.getPuntos()+" puntos.";
    }
}
