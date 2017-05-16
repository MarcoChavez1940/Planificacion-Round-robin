package rr;
/**
 *
 * @author Marco
 */
public class Proceso {
    public String nombre;
    public int tiempoRafaga;
    public int tiempoEspera=0;
    
    public Proceso(String nombre, int tiempoRafaga) {
        this.nombre = nombre;
        this.tiempoRafaga = tiempoRafaga;
    }  
}
