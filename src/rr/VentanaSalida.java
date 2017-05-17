package rr;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Marco
 */
public class VentanaSalida implements ActionListener{ 
    view_Salida viewSalida = new view_Salida();
    int contador=0;
    
    public VentanaSalida(){
        viewSalida.btn_otro.addActionListener(this);
        viewSalida.setVisible(true);
    }
    
    public void dibujarProceso(String nombre, int numero){
        DefaultTableModel model = (DefaultTableModel) viewSalida.tabla_representacion.getModel();
        
        model.addColumn("");
        viewSalida.tabla_representacion.setValueAt(nombre, 0, contador);
        viewSalida.tabla_representacion.setValueAt(numero, 1, contador);
              
        contador++;  
    }
    
    public void dibujarDatosEntrada(Stack pila, int quantum){
        DefaultTableModel model = (DefaultTableModel) viewSalida.tabla_entrada.getModel();
        Object[] fila = new Object[2];
        while(!pila.empty()){
            
            
            Proceso aux = (Proceso) pila.pop();
            fila[0] = aux.nombre;
            fila[1] = aux.tiempoRafaga;
            
            model.addRow(fila);
            viewSalida.tabla_entrada.setModel(model);
        }
        
        fila[0] = "Quantum";
        fila[1] = quantum;
        model.addRow(fila);
        viewSalida.tabla_entrada.setModel(model);
        
    }
    
    public void dibujarTiemposEspera(ArrayList<Proceso> procesos){
        DefaultTableModel model = (DefaultTableModel) viewSalida.tabla_esperas.getModel();
        Object[] fila = new Object[2];
        float promedio_espera=0;
        
        for(int i=0;i<procesos.size();i++){
            fila[0] = procesos.get(i).nombre;
            fila[1] = procesos.get(i).tiempoEspera;
            promedio_espera+=procesos.get(i).tiempoEspera;
            
            model.addRow(fila);
            viewSalida.tabla_esperas.setModel(model);
        }
        
        fila[0] = "Tiempo promedio de espera: ";
        fila[1] = promedio_espera/procesos.size();
        model.addRow(fila);
        viewSalida.tabla_esperas.setModel(model);  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==viewSalida.btn_otro){
            viewSalida.dispose();
            VentanaEntrada ventana = new VentanaEntrada();
        }
    }   
}
