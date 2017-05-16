package rr;
import java.util.Stack;
/**
 *
 * @author Marco
 */
public class Calendarizador {
    private final VentanaSalida ventana = new VentanaSalida();
    public Stack pila = new Stack();
    public Proceso[] procesos;
    private int tiempoPasado = 0;
    private final int quantum;

    public Calendarizador(int quantum, int cantidadProcesos) {
        this.quantum=quantum;
        this.procesos = new Proceso[cantidadProcesos];
    }
    
    public void añadirProceso(Proceso proceso){
        //Son añadidos al final de la pila
        pila.insertElementAt(proceso, 0);
    }
    
    public void iniciarCalendarizacion(){
        Proceso aux = (Proceso) pila.pop();
        
        if(aux.tiempoRafaga>quantum){
            aux.tiempoRafaga-=quantum;
            this.tiempoPasado+=this.quantum;
            
            ventana.dibujarProceso(aux.nombre, tiempoPasado);
            
            añadirTiempoEspera(pila, quantum);
            añadirProceso(aux);
            
            pila.copyInto(procesos);
        }else{
            this.tiempoPasado+=aux.tiempoRafaga;
            
            ventana.dibujarProceso(aux.nombre, tiempoPasado);
            
            añadirTiempoEspera(pila, aux.tiempoRafaga);
            
            pila.copyInto(procesos);
        }     
    }
    
    public void añadirTiempoEspera(Stack pila, int tiempo){
        int tamaño = pila.size();
        for(int i=0;i<tamaño;i++){
            Proceso aux2 = (Proceso) pila.pop();
            aux2.tiempoEspera+=tiempo;
            añadirProceso(aux2);   
        }
    }
    
    public void DatosEntrada(){
        ventana.dibujarDatosEntrada((Stack) pila.clone());
    }
    
    public void TiemposEspera(){
        ventana.dibujarTiemposEspera(procesos);
    }
   
}
