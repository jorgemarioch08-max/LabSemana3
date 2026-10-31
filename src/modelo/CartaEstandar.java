package modelo;

public class CartaEstandar extends Carta{

    public CartaEstandar(String nombre){
        super(nombre);
    }

    @Override
    public int getPuntos(){
        return 1;
    }
}
