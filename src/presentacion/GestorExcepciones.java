
package presentacion;

import encapsuladores.Contexto;
import excepciones.GenericaExcepcion;
import java.io.IOException;
import javax.swing.JOptionPane;
import negocio.IncidenciasNegocio;

public class GestorExcepciones {
    
    public void gestionarExcepcion(Exception excepcion, Contexto contexto) {
    
        int codigoError = 0;
        String mensajeError = "";
        
        if (excepcion instanceof GenericaExcepcion)      
         {
              GenericaExcepcion genericaExcepcion = (GenericaExcepcion)excepcion;
              codigoError = genericaExcepcion.getCodigoError();
              switch (genericaExcepcion.getCodigoError())
              {          
                  case 20: mensajeError = "Se ha producido una situación de error como consecuencia de problemas con la conexión a la BD";
                             break;
                  case 30: mensajeError = "Se ha producido una situación de error en la lectura del fichero XML del repositorio";      
                            break;     
                  case 31: mensajeError = "Se ha producido una situación de error de E/S al intentar acceder al fichero XML del repositorio";       
                            break;  
                  case 40: mensajeError = "Se ha producido un error al intentar consultar la relación de titulares";
                            break; 
                  case 41: mensajeError = "Se ha producido un error al intentar consultar el número de titulares existentes";
                            break; 
                  case 50: mensajeError = "Se ha producido un error al intentar insertar votación";
                            break; 
                  case 51: mensajeError = "Se ha producido un error al intentar consultar la relación de votaciones";
                            break;  
                  case 60: mensajeError = "Se ha producido un error al intentar insertar voto emitido";
                            break; 
                  case 61: mensajeError = "Se ha producido un error al intentar consultar la relación de votos emitidos en una votación";
                            break;                              
              }        
         }
        else
         {  if (excepcion instanceof IOException)      
             {
                 mensajeError = "Se ha producido una situación de error de E/S"; 
             }
            else
             {
                 mensajeError = excepcion.getMessage();                
             }
         }
        
        try {
               contexto.setFechaHora(new java.util.Date());
               new IncidenciasNegocio().escribirFichero(codigoError, mensajeError, contexto);       
            } catch(IOException iOException) { System.out.println("Se ha producido un error al intentar escribir en fichero log de errores"); }           
        
        JOptionPane.showMessageDialog(null, mensajeError, "ERROR", JOptionPane.ERROR_MESSAGE);
    }   
}
