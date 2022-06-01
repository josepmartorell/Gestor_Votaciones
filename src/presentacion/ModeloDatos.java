
package presentacion;

import encapsuladores.Titular;
import encapsuladores.VotoEmitido;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModeloDatos extends AbstractTableModel {
    
     private final Object datos[][];
     private final String[] nombreColumnas = {"Código","Nombre","Cuota participación","AUSENTE","ABSTENCION","SI","NO"};
     private final Componentes componentes;
     private int numeroFilas = 0;


  public ModeloDatos(Componentes componentes, int numeroFilas) {
      this.componentes = componentes;
      this.numeroFilas = numeroFilas; 
      datos = new Object[numeroFilas][7];
      inicializarFilas(0, numeroFilas-1);
      addTableModelListener(new GestorEventos(componentes));
  }



  private void inicializarFilas(int filaInicial, int filaFinal) {   
      for (int i=filaInicial; i<=filaFinal; i++)
        { datos[i][0] = "";
          datos[i][1] = "";
          datos[i][2] = 0.0;
          datos[i][3] = false;
          datos[i][4] = false;
          datos[i][5] = false;
          datos[i][6] = false;
        }        
  }

 
  
  public void cargarTitulares(List<Titular> listaCopropietarios) { 
      DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
      DecimalFormat decimalFormat = new DecimalFormat("##0.000", decimalFormatSymbols);
         
      for (int k=0; k<=3; k++)
          componentes.setContadorVotos(0, k);

      for (int i=0; i<listaCopropietarios.size(); i++)
      {
          Titular copropietario = listaCopropietarios.get(i);          
          datos[i][0] = copropietario.getCodigo();
          datos[i][1] = copropietario.getNombre();
          datos[i][2] = copropietario.getCuotaParticipacion();
      }
  
      for (int k=0; k<=3; k++)
          componentes.getVisualizaNumVotos(k).setText(decimalFormat.format(componentes.getContadorVotos(k)));

      componentes.getjTable().repaint();
  }

  
  
  public void cargarVotacion(List<VotoEmitido> listaVotosEmitidos) {
      for (int k=0; k<=3; k++)       
           componentes.setContadorVotos(0, k); 
      
      for (int i=0; i<listaVotosEmitidos.size(); i++)   
      {
         VotoEmitido votoEmitido = listaVotosEmitidos.get(i);
         for (int k=0; k<=3; k++)      
         {
            datos[i][k+3] = votoEmitido.getComponenteOpcionesVotacion(k);
            if ((votoEmitido.getComponenteOpcionesVotacion(k)))
                componentes.setContadorVotos(componentes.getContadorVotos(k) + ((Double)datos[i][2]), k);       
         }
  }

      componentes.getjTable().repaint();        
   }
  
  
  
  public void inicializarVotacion() { 
      for (int k=0; k<=3; k++)       
           componentes.setContadorVotos(0, k); 
      
         for (Object[] dato : datos) {
             dato[3] = true;
             dato[4] = false;
             dato[5] = false;
             dato[6] = false;
             componentes.setContadorVotos(componentes.getContadorVotos(0) + ((Double) dato[2]), 0);
         }

      componentes.getjTable().repaint();      
  }
  
  

  public Object[][] getDatos() {
      return datos;
  }


  @Override
  public int getColumnCount() {
      return(datos[0].length );
  }

  
  @Override
  public int getRowCount() {
      return(datos.length );
  }

  
  @Override
  public Object getValueAt(int fila, int columna) {
     return datos[fila][columna];
  }


  @Override
  public void setValueAt(Object valor, int fila, int columna ) {
    datos[fila][columna] = valor;

    // fireTableCellUpdated(fila, columna);
    componentes.setFilaActualizada(fila);
    componentes.setColumnaActualizada(columna);
    fireTableDataChanged();
  }


  @Override
  public boolean isCellEditable(int fila, int columna ) {
      if (columna < 3  || columna > 6) 
           return( false );
        else
           return( true );
    }


  @Override
  public String getColumnName(int columna) {
      return nombreColumnas[columna];
  }


  @Override
  public Class getColumnClass(int columna) {
      return getValueAt(0,columna).getClass();
  }
}
