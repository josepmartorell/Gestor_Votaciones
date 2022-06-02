
package negocio;

import datos.ConexionBaseDatos;
import datos.VotacionesDatos;
import datos.VotacionesPDF;
import datos.VotosEmitidosDatos;
import encapsuladores.BaseDatos;
import encapsuladores.SistemaArchivos;
import encapsuladores.Votacion;
import encapsuladores.VotoEmitido;
import java.sql.Connection;
import java.util.List;

public class VotacionesNegocio {
 
    public void guardarVotacion(BaseDatos baseDatos, SistemaArchivos sistemaArchivos, Votacion votacion, Object datos[][], double[] contadorVotos) throws Exception
    {
            Connection connection = null;
            ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();
      try {         
             connection = conexionBaseDatos.abrirConexion(baseDatos);    
             connection.setAutoCommit(false);
             new VotacionesDatos().insertar(connection, votacion);
             new VotosEmitidosDatos().insertar(connection, votacion, datos);
             new VotacionesPDF().generarPDF(sistemaArchivos, votacion, contadorVotos);
             connection.commit();
          } catch (Exception excepcion)
              { 
                connection.rollback();
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }                                
    }   



    public List<Votacion> consultarTodasVotaciones(BaseDatos baseDatos) throws Exception
     {
        Connection connection=null;       
        ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();
        List<Votacion> listaVotaciones = null;

      try {
            connection = conexionBaseDatos.abrirConexion(baseDatos);                           
            listaVotaciones = new VotacionesDatos().consultarTodas(connection);
          } catch (Exception excepcion)
              {  
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }   

         return listaVotaciones;                                 
     }      



    public List<VotoEmitido> consultarResultadoVotacion(BaseDatos baseDatos, Votacion votacion) throws Exception
     {
        Connection connection=null;       
        ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();
        List<VotoEmitido> listaVotosEmitidos = null;

      try {
            connection = conexionBaseDatos.abrirConexion(baseDatos);                           
            listaVotosEmitidos = new VotosEmitidosDatos().consultarResultadoVotacion(connection, votacion);
          } catch (Exception excepcion)
              {  
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }   

         return listaVotosEmitidos;                                 
     }      
}
