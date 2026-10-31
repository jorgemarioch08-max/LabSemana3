package modelo;

public class Jugador {

    public String nombre;
    public int aciertos;
    public int puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.aciertos = 0;
        this.puntos = 0;
    }

    //Getters


    public String getNombre() {
        return nombre;
    }

    public int getAciertos() {
        return aciertos;
    }

    public int getPuntos() {
        return puntos;
    }

    //Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void reiniciar() {
        aciertos = 0;
        puntos = 0;
    }

    public void sumarPareja(int puntos) {
        aciertos++;
        this.puntos+=puntos;
    }
}
