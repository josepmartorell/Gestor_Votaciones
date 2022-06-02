
package negocio;

import datos.ConexionBaseDatos;
import datos.TitularesDatos;
import encapsuladores.BaseDatos;
import encapsuladores.Titular;
import java.sql.Connection;
import java.util.List;

public class TitularesNegocio {
    
    public List<Titular> consultarTodos(BaseDatos baseDatos) throws Exception
     {
        Connection connection=null;       
        ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();
        List<Titular> listaCopropietarios = null;

      try {
            connection = conexionBaseDatos.abrirConexion(baseDatos);                           
            listaCopropietarios = new TitularesDatos().consultarTodos(connection);
          } catch (Exception excepcion)
              {  
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }   

         return listaCopropietarios;                                 
     }      


    public Integer consultarNumeroFilas(BaseDatos baseDatos) throws Exception
     {
        Connection connection=null;       
        ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();
        TitularesDatos copropietariosDatos = new TitularesDatos();        
        Integer numFilas = null;

      try {
            connection = conexionBaseDatos.abrirConexion(baseDatos);                           
            numFilas = copropietariosDatos.consultarNumeroFilas(connection);
          } catch (Exception excepcion)
              {  
                throw excepcion; 
              }   
            finally
              {
                 conexionBaseDatos.cerrarConexion(connection);         
              }   

         return numFilas;                                 
     }      
}
