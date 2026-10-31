package modelo;

public abstract class Carta implements Cloneable{
    private String nombre;
    private boolean descubierta = false;
    private boolean emparejada = false;

    public Carta(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void mostrar(){
        descubierta = true;
    }

    public void ocultar(){
        if(!emparejada){
            descubierta = false;
        }
    }

    public boolean isDescubierta(){
        return descubierta;
    }

    public void marcarEmparejada(){
        emparejada = true;
        descubierta = true;
    }

    public boolean isEmparejada(){
        return emparejada;
    }

    public abstract int getPuntos();

    @Override
    public Carta clone(){
        try{
            Carta copia = (Carta) super.clone();
            copia.descubierta = false;
            copia.emparejada = false;
            return copia;
        }catch(CloneNotSupportedException e){
            System.out.println("No se pudo clonar la carta: "+e.getMessage());
            return null;
        }
    }

    @Override
    public String toString(){
        return nombre;
    }
}