
package presentacion;

import excepciones.GestorExcepciones;
import encapsuladores.BaseDatos;
import encapsuladores.SistemaArchivos;
import encapsuladores.Sociedad;
import encapsuladores.Votacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import negocio.VotacionesNegocio;

public class GestorEventos extends WindowAdapter implements ActionListener, TableModelListener {

    private final Componentes componentes;
    private Sociedad sociedadCargada;
    
    public GestorEventos(Componentes componentes) {
        this.componentes = componentes;
    }

         // MÉTODO DE WindowAdapter  
    @Override
    public void windowClosing(WindowEvent e) {
       System.exit(0);
    }   
    
         // MÉTODO DE ActionListener    
    @Override
    public void actionPerformed(ActionEvent e) {       
        
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        DecimalFormat decimalFormat = new DecimalFormat("##0.000", decimalFormatSymbols);
      
        JButton jButton = (JButton) e.getSource();
        
        if (jButton == componentes.getBotonCargaSociedad())     //  BOTON Carga Votacion
         {  
            System.out.println("cargando sociedad especifica...");
            if (componentes.getSeleccionIdVotacion().getSelectedItem() != null)
             {
                Sociedad sociedad = new Sociedad();
                sociedad.setCif(componentes.getSeleccionIdSociedad().getSelectedItem().toString().substring(0, 10));
                System.out.println(sociedad.getCif());
                sociedadCargada = sociedad;
             }
         }  
        else
        if (jButton == componentes.getBotonCargaVotacion())     //  BOTON Carga Votacion
         {  
            if (componentes.getSeleccionIdVotacion().getSelectedItem() != null)
             {
                Votacion votacion = new Votacion();
                votacion.setIdVotacion(componentes.getSeleccionIdVotacion().getSelectedItem().toString().substring(15, 34));    
                
                try {
                    componentes.getModeloDatos().cargarVotacion(new VotacionesNegocio().consultarResultadoVotacion((BaseDatos)componentes.getRepositorio()[0], votacion));
                } catch (Exception exception) 
                   {  new GestorExcepciones().gestionarExcepcion(exception, componentes.getContexto()); }
                
                for (int k=0; k<=3; k++)
                   componentes.getVisualizaNumVotos(k).setText(decimalFormat.format(componentes.getContadorVotos(k)));                 
                componentes.getjTextFieldTemaVotado().setText(componentes.getSeleccionIdVotacion().getSelectedItem().toString().substring(39)); 
             }
         }  
        else
        if (jButton == componentes.getBotonNuevaVotacion() && sociedadCargada != null)     //  BOTON Nueva Votacion
         { 
            componentes.getModeloDatos().inicializarVotacion();
            for (int k=0; k<=3; k++)
               componentes.getVisualizaNumVotos(k).setText(decimalFormat.format(componentes.getContadorVotos(k)));     
      
            componentes.getjTextFieldTemaVotado().setText("");
         }  
        else
        if (jButton == componentes.getBotonGuardaVotacion() && sociedadCargada != null)    //  BOTON Guarda Votacion
         { 
            Votacion votacion = new Votacion();
            votacion.setIdVotacion(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
            votacion.setTemaVotado(componentes.getjTextFieldTemaVotado().getText());
            votacion.setCifSociedad(sociedadCargada.getCif());
            
            try {
                new VotacionesNegocio().guardarVotacion((BaseDatos)componentes.getRepositorio()[0], (SistemaArchivos)componentes.getRepositorio()[1], votacion, componentes.getModeloDatos().getDatos(), componentes.getContadorVotos());
                componentes.getPantallaJTable().cargarComboVotaciones(componentes);                
            } catch (Exception exception) 
                   {  new GestorExcepciones().gestionarExcepcion(exception, componentes.getContexto()); }                
         } else {JOptionPane.showMessageDialog(componentes.getPantallaJTable(), "Debe seleccionar y guardar previamente la sociedad");}                
    }            
    
    
         // MÉTODO DE TableModelListener      
    @Override
    public void tableChanged( TableModelEvent e ) 
    {    
         // En esta aplicación, todas las columnas actualizables son objetos Boolean. En el caso de que otras columnas con otra clase de objetos
         // asociados también fuesen actualizables, la implementación de este método debería estar sometida  a la siguiente condición:
         //      if (componentes.getColumnaActualizada() >= 3  &&  componentes.getColumnaActualizada() <=6)
         
         DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
         DecimalFormat decimalFormat = new DecimalFormat("##0.000", decimalFormatSymbols);
         
         if (!((Boolean)componentes.getModeloDatos().getDatos()[componentes.getFilaActualizada()][componentes.getColumnaActualizada()]))
          {  
             componentes.getModeloDatos().getDatos()[componentes.getFilaActualizada()][componentes.getColumnaActualizada()] = true;
          }
         else
          {                          
              componentes.setContadorVotos(componentes.getContadorVotos(componentes.getColumnaActualizada()-3) + (Double)(componentes.getModeloDatos().getDatos()[componentes.getFilaActualizada()][2]), componentes.getColumnaActualizada()-3);
              componentes.getVisualizaNumVotos(componentes.getColumnaActualizada()-3).setText(decimalFormat.format(componentes.getContadorVotos(componentes.getColumnaActualizada()-3)));
                      
             for (byte i=3; i<=6; i++)
             {
                 if (((Boolean)componentes.getModeloDatos().getDatos()[componentes.getFilaActualizada()][i])  &&  i != componentes.getColumnaActualizada())
                 {                 
                     componentes.getModeloDatos().getDatos()[componentes.getFilaActualizada()][i] = false;                    
                     componentes.setContadorVotos(componentes.getContadorVotos(i-3) - (Double)(componentes.getModeloDatos().getDatos()[componentes.getFilaActualizada()][2]), i-3);
                     componentes.getVisualizaNumVotos(i-3).setText(decimalFormat.format(Math.abs(componentes.getContadorVotos(i-3))));
                 }             
             }
          }
    }
}
