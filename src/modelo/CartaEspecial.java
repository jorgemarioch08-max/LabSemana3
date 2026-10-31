package modelo;

public class CartaEspecial extends Carta{

    public CartaEspecial(String nombre){
        super(nombre);
    }

    @Override
    public int getPuntos(){
        return 2;
    }
}