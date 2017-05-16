package rr;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marco
 */
public class VentanaEntrada implements ActionListener{
    view_DatosEntrada viewDatos = new view_DatosEntrada();
    
    view_Procesos viewProcesos = new view_Procesos();
    int cantidadProcesos;
    int quantum;
    
    public VentanaEntrada(){
        viewDatos.setVisible(true);
        viewDatos.setBackground(Color.yellow);
        viewDatos.btn_continuar.addActionListener(this);
        viewProcesos.btn_Calcular.addActionListener(this);
    }

    public void DatosProcesos(int cantidadProcesos){
        viewProcesos.setVisible(true);
        DefaultTableModel model = (DefaultTableModel) viewProcesos.tabla_EntradaProcesos.getModel();
        Object[] fila = new Object[2];
        
        for(int i=0;i<cantidadProcesos;i++){
            fila[0] = "P" + (i+1);
            fila[1] = "";
            model.addRow(fila);
            viewProcesos.tabla_EntradaProcesos.setModel(model);   
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==viewDatos.btn_continuar){
            try {
                if(Integer.parseInt(viewDatos.txt_NProcesos.getText())>0 &&
                   Integer.parseInt(viewDatos.txt_quantum.getText())>0){

                    this.cantidadProcesos = Integer.parseInt(viewDatos.txt_NProcesos.getText());
                    this.quantum = Integer.parseInt(viewDatos.txt_quantum.getText());

                    viewDatos.dispose();
                    DatosProcesos(this.cantidadProcesos);
                }else{
                    JOptionPane.showMessageDialog(viewDatos, "Los datos no pueden ser negativos");
                }
            
            } catch (Exception NumberFormatException) {
                JOptionPane.showMessageDialog(viewDatos, "Error de los datos");
            }              
        }
        
        if(e.getSource()==viewProcesos.btn_Calcular){
            try {
                boolean bandera=true;
                //Comprobar que todos los tiempos de rafaga son no negativos.
                for(int j=0;j<this.cantidadProcesos;j++){
                    if(Integer.parseInt((String) viewProcesos.tabla_EntradaProcesos.getValueAt(j, 1))<0){
                        bandera=false;
                    }
                }
                
                if(bandera==true){
                    viewProcesos.dispose();
                    Calendarizador calendarizador = new Calendarizador(quantum, cantidadProcesos);
                    //añadiendo los procesos
                    for(int l=0;l<cantidadProcesos;l++){
                        calendarizador.añadirProceso(new Proceso("P" + (l+1),Integer.parseInt((String) viewProcesos.tabla_EntradaProcesos.getValueAt(l, 1))));
                    }
                    //Mostrar los procesos ingresados en una tabla.
                    calendarizador.DatosEntrada();
                    //Realizando todo el proceso Round Robin (RR). 
                    while(!calendarizador.pila.empty()){
                        calendarizador.iniciarCalendarizacion();
                    }
                    //Mostrar los tiempos de espera en una tabla.
                    calendarizador.TiemposEspera();   
                }else{
                    JOptionPane.showMessageDialog(viewDatos, "Los datos no pueden ser negativos");
                }
   
            } catch (Exception NumberFormatException) {
                JOptionPane.showMessageDialog(viewDatos, "Error de los datos");
            }  
        }  
    }
}
